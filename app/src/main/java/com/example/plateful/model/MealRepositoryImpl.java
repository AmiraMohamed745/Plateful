package com.example.plateful.model;

import com.example.plateful.database.MealLocalDataSource;
import com.example.plateful.home.model.Cuisine;
import com.example.plateful.home.model.CuisineResponse;
import com.example.plateful.network.MealRemoteDataSource;
import com.example.plateful.network.RXSchedulers;
import com.example.plateful.search.category.model.Category;
import com.example.plateful.search.category.model.CategoryResponse;

import java.util.List;
import java.util.stream.Collectors;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

public class MealRepositoryImpl implements MealRepository {

    private static final String TAG = MealRepositoryImpl.class.getSimpleName();

    private MealRemoteDataSource mealRemoteDataSource;
    private MealLocalDataSource mealLocalDataSource;
    private static MealRepositoryImpl mealRepositoryImplInstance = null;

    private MealRepositoryImpl(MealRemoteDataSource mealRemoteDataSource, MealLocalDataSource mealLocalDataSource) {
        this.mealRemoteDataSource = mealRemoteDataSource;
        this.mealLocalDataSource = mealLocalDataSource;
    }

    public static MealRepositoryImpl getInstance(MealRemoteDataSource mealRemoteDataSource, MealLocalDataSource mealLocalDataSource) {
        if (mealRepositoryImplInstance == null) {
            mealRepositoryImplInstance = new MealRepositoryImpl(mealRemoteDataSource, mealLocalDataSource);
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

    @Override
    public Single<List<Cuisine>> fetchMealCuisines() {
        return mealRemoteDataSource.getAllCuisines("list")
                .map(CuisineResponse::getCuisines);
    }

    @Override
    public Single<List<Meal>> fetchMealsByCuisine(String cuisineName) {
        return mealRemoteDataSource.getMealsByCuisine(cuisineName)
                .map(MealResponse::getMeals);
    }

    @Override
    public Flowable<List<Meal>> fetchStoredFavoriteMeals() {
        return mealLocalDataSource.getStoredFavoriteMeals()
                .compose(RXSchedulers.applySchedulersFlowable());
    }

    @Override
    public Completable insertMeal(Meal meal) {
        return mealLocalDataSource.insertMealIntoFavorites(meal)
                .compose(RXSchedulers.applySchedulersCompletable());
    }

    @Override
    public Completable deleteMeal(Meal meal) {
        return mealLocalDataSource.deleteMealFromFavorites(meal)
                .compose(RXSchedulers.applySchedulersCompletable());
    }
}
