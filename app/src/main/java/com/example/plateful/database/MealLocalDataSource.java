package com.example.plateful.database;

import com.example.plateful.model.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

public interface MealLocalDataSource {

    Flowable<List<Meal>> getStoredFavoriteMeals();

    Completable deleteMealFromFavorites(Meal meal);

    Completable insertMealIntoFavorites(Meal meal);

}
