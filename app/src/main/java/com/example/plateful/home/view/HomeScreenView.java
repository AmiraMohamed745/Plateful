package com.example.plateful.home.view;

import com.example.plateful.home.model.Cuisine;
import com.example.plateful.model.Meal;

import java.util.List;

public interface HomeScreenView {
    void displayRandomMeals(List<Meal> meals);
    void displayCuisines(List<Cuisine> cuisines);
    void onMealAddedToFavorites(Meal meal);
    void showError(String errorMessage);
}
