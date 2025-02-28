package com.example.plateful.search.category.view;

import com.example.plateful.model.Meal;
import com.example.plateful.search.category.model.Category;

import java.util.List;

public interface AllCategoryMealsView {
    void displayCategoryMeals(List<Meal> categoryMeals);
    void showError(String errorMessage);
}
