package com.example.plateful.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.plateful.model.Meal;
import com.example.plateful.weeklyplan.model.PlannedMeal;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

@Dao
public interface MealPlanDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Completable insertPlannedMeal(PlannedMeal plannedMeal);

    @Delete
    Completable deletePlannedMeal(PlannedMeal plannedMeal);

    @Query("SELECT * FROM meal_plan_table WHERE planned_date = :date")
    Flowable<List<PlannedMeal>> getMealPlansForDate(long date);
}
