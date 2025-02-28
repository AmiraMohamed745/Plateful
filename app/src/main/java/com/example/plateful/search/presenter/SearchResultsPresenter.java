package com.example.plateful.search.presenter;

public interface SearchResultsPresenter {
    void onSearchQueryChanged(String query);
    void loadSearchResults(String categoryName);
    void cleanUpDisposables();
}
