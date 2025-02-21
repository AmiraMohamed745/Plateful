package com.example.plateful;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.plateful.model.Meal;
import com.example.plateful.model.MealResponse;
import com.example.plateful.network.MealRemoteDataSourceImpl;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MealAPIResponse";
    // private MealRemoteDataSourceImpl mealRemoteDataSourceImpl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        /*setContentView(R.layout.fragment_sign_up_screen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.fragment_signup), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });*/
        
        /*setContentView(R.layout.fragment_sign_in_screen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.fragment_sign_in), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });*/

/*        mealRemoteDataSourceImpl = new MealRemoteDataSourceImpl(this);

        if (MealRemoteDataSourceImpl.mealService != null) {
            MealRemoteDataSourceImpl.mealService.getRandomMealForDailyInspiration().enqueue(new Callback<MealResponse>() {
                @Override
                public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        for (Meal meal: response.body().getMeals()) {
                            Log.d(TAG, "Meal Name: " + meal.getName());
                            Log.d(TAG, "Category: " + meal.getCategory());
                            Log.d(TAG, "Country: " + meal.getArea());
                            Log.d(TAG, "Image URL: " + meal.getImageUrl());
                            Log.d(TAG, "Instructions: " + meal.getInstructions());
                            Log.d(TAG, "Video URL: " + meal.getVideoUrl());
                        }
                    } else {
                        Log.e(TAG, "Response unsuccessful or empty");
                    }
                }

                @Override
                public void onFailure(Call<MealResponse> call, Throwable throwable) {
                    Log.e(TAG, "API Call Failed: " + throwable.getMessage());
                }
            });
        } else {
            Log.e(TAG, "MealService is null!");
        }*/
    }
}