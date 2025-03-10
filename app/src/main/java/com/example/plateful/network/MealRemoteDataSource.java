package com.example.plateful.network;

import com.example.plateful.home.model.CuisineResponse;
import com.example.plateful.model.Meal;
import com.example.plateful.model.MealResponse;
import com.example.plateful.search.category.model.CategoryResponse;
import com.example.plateful.search.ingredients.model.IngredientResponse;

import io.reactivex.rxjava3.core.Single;

public interface MealRemoteDataSource {
    Single<MealResponse> getRandomMeal();

    Single<CategoryResponse> getAllCategories();

    Single<MealResponse> getMealsByCategory(String categoryName);

    Single<MealResponse> searchMealByName(String searchQuery);

    Single<CuisineResponse> getAllCuisines(String cuisineParameter);

    Single<MealResponse> getMealsByCuisine(String cuisineName);

    Single<IngredientResponse> getAllIngredients();

    Single<MealResponse> getMealsByIngredient(String ingredientName);

}
