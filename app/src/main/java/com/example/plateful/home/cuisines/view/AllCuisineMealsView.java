package com.example.plateful.home.cuisines.view;

import com.example.plateful.model.Meal;

import java.util.List;

public interface AllCuisineMealsView {
    void displayCuisineMeals(List<Meal> cuisineMeals);
    void showError(String errorMessage);
}
