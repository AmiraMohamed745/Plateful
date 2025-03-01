package com.example.plateful.search.mainsearch.presenter;

import com.example.plateful.home.presenter.HomeScreenPresenterImpl;
import com.example.plateful.home.view.HomeScreenView;
import com.example.plateful.model.MealRepository;
import com.example.plateful.search.category.model.Category;
import com.example.plateful.search.mainsearch.view.MainSearchScreenView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class MainSearchScreenPresenterImpl implements MainSearchScreenPresenter {

    private static final String TAG = MainSearchScreenPresenterImpl.class.getName();

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final MainSearchScreenView mainSearchScreenView;
    private final MealRepository mealRepository;

    public MainSearchScreenPresenterImpl(MainSearchScreenView mainSearchScreenView, MealRepository mealRepository) {
        this.mainSearchScreenView = mainSearchScreenView;
        this.mealRepository = mealRepository;
    }


    @Override
    public void loadCategories() {
        compositeDisposable.add(
                mealRepository.fetchMealCategories()
                        .subscribe(
                                mainSearchScreenView::displayCategories,
                                error -> mainSearchScreenView.showError(error.getMessage()))
        );
    }

    @Override
    public void cleanUpDisposables() {
        compositeDisposable.clear();
    }
}
