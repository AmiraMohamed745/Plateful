package com.example.plateful.search.view;

import com.example.plateful.model.Meal;

import java.util.List;

public interface SearchResultsScreenView {
    void displaySearchResults(List<Meal> meals);
    void showError(String errorMessage);
}
