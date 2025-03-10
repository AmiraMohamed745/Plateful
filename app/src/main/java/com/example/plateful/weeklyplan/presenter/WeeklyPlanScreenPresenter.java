package com.example.plateful.weeklyplan.presenter;

import com.example.plateful.model.Meal;
import com.example.plateful.weeklyplan.model.PlannedMeal;

public interface WeeklyPlanScreenPresenter {
    void loadPlannedMeals(long date);
    void deleteMealFromPlan(PlannedMeal plannedMeal);
    void cleanUpDisposables();
}
