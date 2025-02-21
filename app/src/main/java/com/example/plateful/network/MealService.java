package com.example.plateful.network;

import com.example.plateful.model.MealResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MealService {

    @GET("random.php")
    Call<MealResponse> getRandomMealForDailyInspiration();
    /*
    * Should I put: random.php after php?
    * What is @Query("i")? Why not path like in WebService course?
     */
    @GET("lookup.php")
    Call<MealResponse> getMealById(@Query("i") String mealId);
}
