package com.example.plateful.home.presenter;

import com.example.plateful.model.Meal;

public interface HomeScreenPresenter {
    void loadRandomMeal();
    void addMealToFavorites(Meal meal);
    void cleanUpDisposables();
}
