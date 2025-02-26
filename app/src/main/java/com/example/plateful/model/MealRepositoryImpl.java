package com.example.plateful.model;

import com.example.plateful.network.MealRemoteDataSource;
import com.example.plateful.network.RXSchedulers;

import java.util.List;

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
}
