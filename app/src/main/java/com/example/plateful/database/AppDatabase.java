package com.example.plateful.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.plateful.model.Meal;
import com.example.plateful.weeklyplan.model.PlannedMeal;

@Database(entities = {Meal.class, PlannedMeal.class}, version = 3)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance = null;

    public abstract MealDAO getMealDAO();

    public abstract MealPlanDAO getMealPlanDAO();

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance =
                    Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "productDB")
                            .fallbackToDestructiveMigration()
                            .build();
        }
        return instance;
    }
}
