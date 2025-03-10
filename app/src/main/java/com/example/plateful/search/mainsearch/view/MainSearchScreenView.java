package com.example.plateful.search.mainsearch.view;

import com.example.plateful.search.category.model.Category;
import com.example.plateful.search.ingredients.model.Ingredient;

import java.util.List;

public interface MainSearchScreenView {
    void displayCategories(List<Category> categories);
    void displayIngredients(List<Ingredient> ingredients);
    void showError(String errorMessage);
}
