package com.example.plateful.model;

import com.example.plateful.network.MealRemoteDataSource;
import com.example.plateful.network.NetworkCallBack;

import java.util.List;

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
    public void getRandomMeal(NetworkCallBack networkCallBack) {
        mealRemoteDataSource.getRandomMeal(networkCallBack);
    }

}
