package com.example.plateful.favoritemeal.view;

import com.example.plateful.model.Meal;

import java.util.List;

public interface FavoriteMealsScreenView {
    void displayFavoriteMeals(List<Meal> meals);
    void showError(String errorMessage);
}
