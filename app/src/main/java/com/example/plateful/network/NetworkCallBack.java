package com.example.plateful.network;

import com.example.plateful.model.Meal;

import java.util.List;

public interface NetworkCallBack {
    void onSuccessResult(List<Meal> meals);
    void onFailureResult(String errorMessage);
}
