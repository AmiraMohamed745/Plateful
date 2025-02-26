package com.example.plateful.model;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public interface MealRepository {
    Single<List<Meal>> fetchTenRandomMealsForDailyInspiration();
}
