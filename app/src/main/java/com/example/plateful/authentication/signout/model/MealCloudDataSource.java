package com.example.plateful.authentication.signout.model;

import com.example.plateful.model.Meal;
import com.example.plateful.weeklyplan.model.PlannedMeal;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

public interface MealCloudDataSource {

    Completable backUpFavoriteMeals(List<Meal> favoriteMeals);

    Completable backUpPlannedMeals(List<PlannedMeal> plannedMeals);

    Completable deleteFavoriteMealFromBackup(Meal favoriteMeal);

    Completable deletePlannedMealFromBackup(PlannedMeal plannedMeal);

    Single<List<Meal>> provideBackedUpFavoriteMeals();

    Single<List<PlannedMeal>> provideBackedUpPlannedMeals();

}
