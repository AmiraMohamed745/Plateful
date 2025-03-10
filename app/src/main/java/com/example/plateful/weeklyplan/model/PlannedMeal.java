package com.example.plateful.weeklyplan.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "meal_plan_table")
public class PlannedMeal {

    @PrimaryKey(autoGenerate = true)
    private int planId;

    @NonNull
    @ColumnInfo(name = "meal_id")
    private String mealId;

    @ColumnInfo(name = "user_id")
    private String userId;

    @ColumnInfo(name = "meal_name")
    private String mealName;

    @ColumnInfo(name = "meal_image")
    private String mealImageUrl;

    @ColumnInfo(name = "planned_date")
    private long plannedDate;

    @Ignore
    public PlannedMeal() {
    }

    public PlannedMeal(@NonNull String mealId, String userId, long plannedDate, String mealName, String mealImageUrl) {
        this.mealId = mealId;
        this.userId = userId;
        this.plannedDate = plannedDate;
        this.mealName = mealName;
        this.mealImageUrl = mealImageUrl;

    }


    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    @NonNull
    public String getMealId() {
        return mealId;
    }

    public void setMealId(@NonNull String mealId) {
        this.mealId = mealId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public long getPlannedDate() {
        return plannedDate;
    }

    public void setPlannedDate(long plannedDate) {
        this.plannedDate = plannedDate;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public String getMealImageUrl() {
        return mealImageUrl;
    }

    public void setMealImageUrl(String mealImageUrl) {
        this.mealImageUrl = mealImageUrl;
    }
}
