package com.example.plateful.search.ingredients.view;

import com.example.plateful.model.Meal;

import java.util.List;

public interface AllIngredientsMealsView {
    void displayIngredientMeals(List<Meal> ingredientMeals);
    void showError(String errorMessage);
}
