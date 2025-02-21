package com.example.plateful.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MealResponse {

    @SerializedName("meals")
    @Expose /* do I need expose? */
    private List<Meal> meals;

    /* Do I need the constructors? */
    public MealResponse() {
    }

    public MealResponse(List<Meal> meals) {
        super();
        this.meals = meals;
    }

    public List<Meal> getMeals() {
        return meals;
    }

    /* Do I need a setter? */
    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }
}
