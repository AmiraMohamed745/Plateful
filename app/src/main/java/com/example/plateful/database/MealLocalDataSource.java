package com.example.plateful.database;

import com.example.plateful.model.Meal;
import com.example.plateful.weeklyplan.model.PlannedMeal;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

public interface MealLocalDataSource {

    Flowable<List<Meal>> getStoredFavoriteMeals(String userId);

    Completable deleteMealFromFavorites(Meal meal);

    Completable insertMealIntoFavorites(Meal meal);

    Flowable<List<PlannedMeal>> getMealPlansForDate(long date, String UserId);

    Flowable<List<PlannedMeal>> getAllMealPlans(String userId);

    Completable insertMealIntoPlan(PlannedMeal plannedMeal);

    Completable deleteMealFromPlan(PlannedMeal plannedMeal);

}
