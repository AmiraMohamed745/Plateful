package com.example.plateful.weeklyplan.view;

import com.example.plateful.weeklyplan.model.PlannedMeal;

import java.util.List;

public interface WeeklyPlanScreenView {
    void showPlannedMeals(List<PlannedMeal> plannedMeals);
    void showErrorMessage(String errorMessage);
}
