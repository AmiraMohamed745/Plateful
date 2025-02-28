package com.example.plateful.search.mainsearch.view;

import com.example.plateful.search.category.model.Category;

import java.util.List;

public interface MainSearchScreenView {
    void displayCategories(List<Category> categories);
    void showError(String errorMessage);
}
