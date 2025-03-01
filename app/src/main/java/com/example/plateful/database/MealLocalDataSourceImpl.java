package com.example.plateful.database;

import android.content.Context;

import com.example.plateful.model.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

public class MealLocalDataSourceImpl implements MealLocalDataSource{

    private final MealDAO mealDAO;
    private final Flowable<List<Meal>> favoriteMeals;
    private static MealLocalDataSourceImpl mealLocalDataSource;

    private MealLocalDataSourceImpl(Context context) {
        AppDatabase appDatabase = AppDatabase.getInstance(context.getApplicationContext());
        mealDAO = appDatabase.getMealDAO();
        favoriteMeals = mealDAO.getAllFavoriteMeals();
    }

    public static MealLocalDataSourceImpl getInstance(Context context) {
        if (mealLocalDataSource == null) {
            mealLocalDataSource = new MealLocalDataSourceImpl(context);
        }
        return mealLocalDataSource;
    }

    @Override
    public Flowable<List<Meal>> getStoredFavoriteMeals() {
        return favoriteMeals;
    }

    @Override
    public Completable deleteMealFromFavorites(Meal meal) {
        return mealDAO.deleteMeal(meal);
    }

    @Override
    public Completable insertMealIntoFavorites(Meal meal) {
        return mealDAO.insertMeal(meal);
    }
}
