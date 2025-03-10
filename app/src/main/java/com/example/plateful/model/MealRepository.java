package com.example.plateful.model;

import com.example.plateful.home.model.Cuisine;
import com.example.plateful.home.model.CuisineResponse;
import com.example.plateful.search.category.model.Category;
import com.example.plateful.search.category.model.CategoryResponse;
import com.example.plateful.search.ingredients.model.IngredientResponse;
import com.example.plateful.weeklyplan.model.PlannedMeal;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

public interface MealRepository {

    // For remote data source
    Single<MealResponse> fetchTenRandomMealsForDailyInspiration();

    Single<CategoryResponse> fetchMealCategories();

    Single<MealResponse> fetchMealsByCategory(String categoryName);

    Single<MealResponse> fetchMealsByNameForCategory(String searchQuery, String categoryName);

    Single<MealResponse> fetchMealsByNameForIngredient(String searchQuery, String ingredientName);

    Single<CuisineResponse> fetchMealCuisines();

    Single<MealResponse> fetchMealsByCuisine(String cuisineName);

    Single<IngredientResponse> fetchAllIngredients();

    Single<MealResponse> fetchMealsByIngredient(String ingredientName);


    // For local data source
    Flowable<List<Meal>> fetchStoredFavoriteMeals(String userId);

    Completable insertMeal(Meal meal);

    Completable deleteMeal(Meal meal);

    Flowable<List<PlannedMeal>> fetchPlannedMealsForDate(long date, String userId);

    Flowable<List<PlannedMeal>> fetchAllPlannedMeals(String userId);

    Completable insertPlannedMeal(PlannedMeal plannedMeal);

    Completable deletePlannedMeal(PlannedMeal plannedMeal);

    // For cloud data source
    Completable backUpFavoriteMeals(List<Meal> favoriteMeals);

    Completable backUpPlannedMeals(List<PlannedMeal> plannedMeals);

    Completable deleteFavoriteMealFromBackup(Meal favoriteMeal);

    Completable deletePlannedMealFromBackup(PlannedMeal plannedMeal);

    Single<List<Meal>> provideBackedUpFavoriteMeals();

    Single<List<PlannedMeal>> provideBackedUpPlannedMeals();

}
