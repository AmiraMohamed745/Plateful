package com.example.plateful.model;

import com.example.plateful.home.model.Cuisine;
import com.example.plateful.search.category.model.Category;
import com.example.plateful.weeklyplan.model.PlannedMeal;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

public interface MealRepository {

    Single<List<Meal>> fetchTenRandomMealsForDailyInspiration();

    Single<List<Category>> fetchMealCategories();

    Single<List<Meal>> fetchMealsByCategory(String categoryName);

    Single<List<Meal>> fetchMealsByName(String searchQuery, String categoryName);

    Single<List<Cuisine>> fetchMealCuisines();

    Single<List<Meal>> fetchMealsByCuisine(String cuisineName);

    Flowable<List<Meal>> fetchStoredFavoriteMeals();

    Completable insertMeal(Meal meal);

    Completable deleteMeal(Meal meal);

    Flowable<List<PlannedMeal>> fetchPlannedMealsForDate(long date);

    Completable insertPlannedMeal(PlannedMeal plannedMeal);

    Completable deletePlannedMeal(PlannedMeal plannedMeal);

}
