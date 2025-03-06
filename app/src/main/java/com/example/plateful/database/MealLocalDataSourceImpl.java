package com.example.plateful.database;

import android.content.Context;

import com.example.plateful.model.Meal;
import com.example.plateful.weeklyplan.model.PlannedMeal;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

public class MealLocalDataSourceImpl implements MealLocalDataSource{

    private final MealDAO mealDAO;
    private final MealPlanDAO mealPlanDAO;
    private final Flowable<List<Meal>> favoriteMeals;
    private static MealLocalDataSourceImpl mealLocalDataSource;

    private MealLocalDataSourceImpl(Context context) {
        AppDatabase appDatabase = AppDatabase.getInstance(context.getApplicationContext());
        mealDAO = appDatabase.getMealDAO();
        mealPlanDAO = appDatabase.getMealPlanDAO();
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

    @Override
    public Flowable<List<PlannedMeal>> getMealPlansForDate(long date) {
        return mealPlanDAO.getMealPlansForDate(date);
    }

    @Override
    public Completable insertMealIntoPlan(PlannedMeal plannedMeal) {
        return mealPlanDAO.insertPlannedMeal(plannedMeal);
    }

    @Override
    public Completable deleteMealFromPlan(PlannedMeal plannedMeal) {
        return mealPlanDAO.deletePlannedMeal(plannedMeal);
    }
}
