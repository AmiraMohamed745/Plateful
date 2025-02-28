package com.example.plateful.model;

import com.example.plateful.network.MealRemoteDataSource;
import com.example.plateful.network.RXSchedulers;
import com.example.plateful.search.category.model.Category;
import com.example.plateful.search.category.model.CategoryResponse;

import java.util.List;
import java.util.stream.Collectors;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

public class MealRepositoryImpl implements MealRepository {

    private static final String TAG = MealRepositoryImpl.class.getSimpleName();

    private MealRemoteDataSource mealRemoteDataSource;
    private static MealRepositoryImpl mealRepositoryImplInstance = null;

    private MealRepositoryImpl(MealRemoteDataSource mealRemoteDataSource) {
        this.mealRemoteDataSource = mealRemoteDataSource;
    }

    public static MealRepositoryImpl getInstance(MealRemoteDataSource mealRemoteDataSource) {
        if (mealRepositoryImplInstance == null) {
            mealRepositoryImplInstance = new MealRepositoryImpl(mealRemoteDataSource);
        }
        return mealRepositoryImplInstance;
    }


    @Override
    public Single<List<Meal>> fetchTenRandomMealsForDailyInspiration() {
        return Observable.range(1, 10)
                .flatMap(item -> mealRemoteDataSource.getRandomMeal().toObservable())
                .map(mealResponse -> mealResponse.getMeals().get(0))
                .toList()
                .compose(RXSchedulers.applySchedulersSingle());
    }

    @Override
    public Single<List<Category>> fetchMealCategories() {
        return mealRemoteDataSource.getAllCategories()
                .map(CategoryResponse::getCategories)
                .compose(RXSchedulers.applySchedulersSingle());
    }

    @Override
    public Single<List<Meal>> fetchMealsByCategory(String categoryName) {
        return mealRemoteDataSource.getMealsByCategory(categoryName)
                .map(MealResponse::getMeals)
                .compose(RXSchedulers.applySchedulersSingle());
    }

    @Override
    public Single<List<Meal>> fetchMealsByName(String searchQuery, String categoryName) {
        return mealRemoteDataSource.searchMealByName(searchQuery)
                .map(
                        mealResponse -> {
                            return mealResponse.getMeals().stream()
                                    .filter(meal -> meal.getCategory().equalsIgnoreCase(categoryName))
                                    .collect(Collectors.toList());
                        })
                .compose(RXSchedulers.applySchedulersSingle());
    }
}
