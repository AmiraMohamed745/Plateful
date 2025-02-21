package com.example.plateful.home.view;

import com.example.plateful.model.Meal;

public interface HomeScreenView {
    void showError(String errorMessage);
    void displayRandomMeal(Meal meal);
    void displayMealByCuisine(Meal meal);
    void displayMealByCategory(Meal meal);
    void addMealToWeeklyPlan(Meal meal);
    void addMealToFavorites(Meal meal);
}
