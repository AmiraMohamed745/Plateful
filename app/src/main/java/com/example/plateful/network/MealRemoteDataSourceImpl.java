package com.example.plateful.network;

import android.content.Context;
import android.util.Log;

import com.example.plateful.model.Meal;
import com.example.plateful.model.MealResponse;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.core.Single;
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

    private static MealService mealService;

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
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
    }

    @Override
    public Single<MealResponse> getRandomMeal() {
        return mealService.getRandomMealsForDailyInspiration();
    }
}
