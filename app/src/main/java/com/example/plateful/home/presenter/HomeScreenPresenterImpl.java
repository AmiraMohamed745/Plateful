package com.example.plateful.home.presenter;

import android.util.Log;

import com.example.plateful.home.view.HomeScreenView;
import com.example.plateful.model.Meal;
import com.example.plateful.model.MealRepository;
import com.example.plateful.network.NetworkCallBack;

import java.util.List;

public class HomeScreenPresenterImpl implements HomeScreenPresenter {

    private static final String TAG = HomeScreenPresenterImpl.class.getName();

    private HomeScreenView homeScreenView;
    private MealRepository mealRepository;

    public HomeScreenPresenterImpl(HomeScreenView homeScreenView, MealRepository mealRepository) {
        this.homeScreenView = homeScreenView;
        this.mealRepository = mealRepository;
    }

    @Override
    public void loadRandomMeal() {
        mealRepository.getRandomMeal(new NetworkCallBack() {
            @Override
            public void onSuccessResult(List<Meal> meals) {
                if (meals != null && !meals.isEmpty()) {
                    Meal randomMeal = meals.get(0);
                    homeScreenView.displayRandomMeal(randomMeal);
                    Log.d(TAG, "Meal Name: " + randomMeal.getName());
                    Log.d(TAG, "Category: " + randomMeal.getCategory());
                    Log.d(TAG, "Country: " + randomMeal.getArea());
                    Log.d(TAG, "Image URL: " + randomMeal.getImageUrl());
                    Log.d(TAG, "Instructions: " + randomMeal.getInstructions());
                    Log.d(TAG, "Video URL: " + randomMeal.getVideoUrl());
                } else {
                    homeScreenView.showError("No meals received from API.");
                }
            }

            @Override
            public void onFailureResult(String errorMessage) {
                homeScreenView.showError(errorMessage);
            }
        });
    }

    @Override
    public void loadMealByCuisine(String area) {

    }

    @Override
    public void loadMealByCategory(String category) {

    }

    @Override
    public void onAddMealToWeeklyPlan(Meal meal) {

    }

    @Override
    public void onAddMealToFavorites(Meal meal) {

    }


}
