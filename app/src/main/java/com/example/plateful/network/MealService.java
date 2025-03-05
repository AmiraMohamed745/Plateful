package com.example.plateful.network;

import com.example.plateful.home.model.CuisineResponse;
import com.example.plateful.model.MealResponse;
import com.example.plateful.search.category.model.CategoryResponse;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface MealService {

    @GET("random.php")
    Single<MealResponse> getRandomMealsForDailyInspiration();

    @GET("categories.php")
    Single<CategoryResponse> getAllCategories();

    @GET("filter.php")
    Single<MealResponse> getMealsByCategory(@Query("c") String categoryName);

    @GET("search.php")
    Single<MealResponse> searchMealByName(@Query("s") String searchQuery);

    @GET("list.php")
    Single<CuisineResponse> getAllCuisines(@Query("a") String cuisineParameter);

    @GET("filter.php")
    Single<MealResponse> getMealsByCuisine(@Query("a") String cuisineName);

}
