package com.example.plateful.home.presenter;

import com.example.plateful.model.Meal;

public interface HomeScreenPresenter {
    void loadRandomMeal();
    void loadMealByCuisine(String area);
    void loadMealByCategory(String category);
    void onAddMealToWeeklyPlan(Meal meal);
    void onAddMealToFavorites(Meal meal);
}
