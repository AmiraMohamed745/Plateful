package com.example.plateful.home.presenter;

import com.example.plateful.home.view.HomeScreenView;
import com.example.plateful.model.Meal;
import com.example.plateful.model.MealRepository;

import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;

public class HomeScreenPresenterImpl implements HomeScreenPresenter {

    private static final String TAG = HomeScreenPresenterImpl.class.getName();

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final HomeScreenView homeScreenView;
    private final MealRepository mealRepository;

    public HomeScreenPresenterImpl(HomeScreenView homeScreenView, MealRepository mealRepository) {
        this.homeScreenView = homeScreenView;
        this.mealRepository = mealRepository;
    }


    @Override
    public void loadRandomMeal() {
        Disposable disposable = mealRepository.fetchTenRandomMealsForDailyInspiration()
                .subscribe(
                        meals -> homeScreenView.displayRandomMeals(meals),
                        error -> homeScreenView.showError(showUserFriendlyErrorMessage(error))
                );
        compositeDisposable.add(disposable);
    }

    private String showUserFriendlyErrorMessage(Throwable error) {
        if (error.getMessage().contains("Unable to resolve host")) {
            return "Please check your internet connection.";
        } else {
            return "Something went wrong. Please try again.";
        }
    }


    @Override
    public void cleanUpDisposables() {
        compositeDisposable.clear();
    }

}
