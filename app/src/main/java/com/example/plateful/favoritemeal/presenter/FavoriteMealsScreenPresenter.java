package com.example.plateful.favoritemeal.presenter;

import com.example.plateful.model.Meal;

public interface FavoriteMealsScreenPresenter {
    void loadFavoriteMeals();
    void deleteFavoriteMeal(Meal meal);
    void reInsertFavoriteMeal(Meal meal);
    void cleanUpDisposables();
}
