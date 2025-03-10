package com.example.plateful.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.plateful.model.Meal;
import com.example.plateful.utils.UserSession;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import java.util.List;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

@Dao
public interface MealDAO {

    @Query("SELECT * FROM meal_table WHERE user_id = :userId")
    Flowable<List<Meal>> getAllFavoriteMeals(String userId);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Completable insertMeal(Meal meal);

    @Delete
    Completable deleteMeal(Meal meal);
}
