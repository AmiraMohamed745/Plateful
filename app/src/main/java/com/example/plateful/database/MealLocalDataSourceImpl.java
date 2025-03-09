package com.example.plateful.database;

import android.content.Context;

import com.example.plateful.model.Meal;
import com.example.plateful.utils.UserSession;
import com.example.plateful.weeklyplan.model.PlannedMeal;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

public class MealLocalDataSourceImpl implements MealLocalDataSource{

    private final MealDAO mealDAO;
    private final MealPlanDAO mealPlanDAO;
    private static MealLocalDataSourceImpl mealLocalDataSource;

    private MealLocalDataSourceImpl(Context context) {
        AppDatabase appDatabase = AppDatabase.getInstance(context.getApplicationContext());
        mealDAO = appDatabase.getMealDAO();
        mealPlanDAO = appDatabase.getMealPlanDAO();
    }

    public static MealLocalDataSourceImpl getInstance(Context context) {
        if (mealLocalDataSource == null) {
            mealLocalDataSource = new MealLocalDataSourceImpl(context);
        }
        return mealLocalDataSource;
    }

    @Override
    public Flowable<List<Meal>> getStoredFavoriteMeals(String userId) {
        return mealDAO.getAllFavoriteMeals(userId);
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
    public Flowable<List<PlannedMeal>> getMealPlansForDate(long date, String userId) {
        return mealPlanDAO.getMealPlansForDate(date, userId);
    }

    @Override
    public Flowable<List<PlannedMeal>> getAllMealPlans(String userId) {
        return mealPlanDAO.getAllMealPlans(userId);
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
