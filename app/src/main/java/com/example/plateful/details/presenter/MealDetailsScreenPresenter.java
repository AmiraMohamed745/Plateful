package com.example.plateful.details.presenter;

import com.example.plateful.model.Meal;

public interface MealDetailsScreenPresenter {
    void addMealToPlan(Meal meal, long date);
    void cleanUpDisposables();
}
