package com.example.plateful.home.presenter;

import com.example.plateful.home.model.Cuisine;
import com.example.plateful.home.view.HomeScreenView;
import com.example.plateful.model.Meal;
import com.example.plateful.model.MealRepository;
import com.example.plateful.network.RXSchedulers;
import com.example.plateful.utils.UserSession;

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
                        homeScreenView::displayRandomMeals,
                        error -> homeScreenView.showError(showUserFriendlyErrorMessage(error))
                );
        compositeDisposable.add(disposable);
    }

    @Override
    public void addMealToFavorites(Meal meal) {
        meal.setUserId(UserSession.getCurrentUserId());
        compositeDisposable.add(
                mealRepository.insertMeal(meal)
                        .subscribe(
                                () -> homeScreenView.onMealAddedToFavorites(meal),
                                error -> homeScreenView.showError(error.getMessage())
                        )
        );
    }

    @Override
    public void loadCuisines() {
        compositeDisposable.add(mealRepository.fetchMealCuisines()
                .map(cuisines -> {
                    for (Cuisine cuisine : cuisines) {
                        int imageRes = CuisineImageMapper.getImageForCuisine(cuisine.getCuisineName());
                        cuisine.setImageResId(imageRes);
                    }
                    return cuisines;
                })
                .compose(RXSchedulers.applySchedulersSingle())
                .subscribe(
                        homeScreenView::displayCuisines,
                        error -> homeScreenView.showError(error.getMessage()))
        );
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
