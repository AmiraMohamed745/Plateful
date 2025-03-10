package com.example.plateful.search.mainsearch.presenter;

import java.util.List;

public interface MainSearchScreenPresenter {
    void loadCategories();
    void loadIngredients();
    void cleanUpDisposables();
}
