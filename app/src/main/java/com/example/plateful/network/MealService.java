package com.example.plateful.network;

import com.example.plateful.model.MealResponse;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface MealService {

    @GET("random.php")
    Single<MealResponse> getRandomMealsForDailyInspiration();

}
