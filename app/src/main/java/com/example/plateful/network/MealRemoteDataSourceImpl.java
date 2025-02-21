package com.example.plateful.network;

import android.content.Context;
import android.util.Log;

import com.example.plateful.model.Meal;
import com.example.plateful.model.MealResponse;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MealRemoteDataSourceImpl implements MealRemoteDataSource {

    // For debug messages
    private static final String TAG = MealRemoteDataSourceImpl.class.getName();

    private static final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";

    public static MealService mealService; /* should it remain public? */

    public MealRemoteDataSourceImpl(Context context) {
        OkHttpClient okHttpClient = createHttpClientWithCache(context);
        Retrofit retrofit = createRetrofit(okHttpClient);
        mealService = retrofit.create(MealService.class);
    }

    private OkHttpClient createHttpClientWithCache(Context context) {
        int cacheSize = 10 * 1024 * 1024;
        Cache cache = new Cache(context.getCacheDir(), cacheSize);
        return new OkHttpClient.Builder()
                .cache(cache)
                .build();
    }

    private Retrofit createRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    @Override
    public void getRandomMeal(NetworkCallBack networkCallBack) {
        mealService.getRandomMealForDailyInspiration().enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    networkCallBack.onSuccessResult(response.body().getMeals());
                    for (Meal meal: response.body().getMeals()) {
                        Log.d(TAG, "Meal Name: " + meal.getName());
                        Log.d(TAG, "Category: " + meal.getCategory());
                        Log.d(TAG, "Country: " + meal.getArea());
                        Log.d(TAG, "Image URL: " + meal.getImageUrl());
                        Log.d(TAG, "Instructions: " + meal.getInstructions());
                        Log.d(TAG, "Video URL: " + meal.getVideoUrl());
                    }
                }
            }

            @Override
            public void onFailure(Call<MealResponse> call, Throwable throwable) {
                Log.e(TAG, "API Call Failed: " + throwable.getMessage());
                networkCallBack.onFailureResult("API Call Failed: " + throwable.getMessage());
            }
        });
    }
}
