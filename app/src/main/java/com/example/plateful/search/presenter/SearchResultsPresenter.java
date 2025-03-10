package com.example.plateful.search.presenter;

public interface SearchResultsPresenter {
    void onSearchQueryChanged(String query);
    void loadSearchResultsForCategoryMeals(String categoryName);
    void loadSearchResultsForIngredientMeals(String ingredientName);
    void cleanUpDisposables();
}
