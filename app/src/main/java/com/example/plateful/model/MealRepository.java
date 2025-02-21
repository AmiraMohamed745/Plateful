package com.example.plateful.model;

import com.example.plateful.network.NetworkCallBack;

import java.util.List;

public interface MealRepository {
    void getRandomMeal(NetworkCallBack callback);

}
