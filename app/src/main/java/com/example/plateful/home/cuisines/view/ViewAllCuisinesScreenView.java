package com.example.plateful.home.cuisines.view;

import com.example.plateful.home.model.Cuisine;

import java.util.List;

public interface ViewAllCuisinesScreenView {
    void displayCuisines(List<Cuisine> cuisines);
    void showError(String errorMessage);
}
