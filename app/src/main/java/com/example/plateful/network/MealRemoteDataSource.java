package com.example.plateful.network;

import com.example.plateful.model.Meal;
import com.example.plateful.model.MealResponse;

import io.reactivex.rxjava3.core.Single;

public interface MealRemoteDataSource {
    Single<MealResponse> getRandomMeal();
}
