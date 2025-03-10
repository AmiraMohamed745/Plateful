package com.example.plateful.home.presenter;

import com.example.plateful.home.model.Cuisine;
import com.example.plateful.home.model.CuisineResponse;
import com.example.plateful.home.view.HomeScreenView;
import com.example.plateful.model.Meal;
import com.example.plateful.model.MealRepository;
import com.example.plateful.network.RXSchedulers;
import com.example.plateful.utils.UserSession;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

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
        Disposable disposable = Observable.range(1, 10)
                .flatMap(i -> mealRepository.fetchTenRandomMealsForDailyInspiration().toObservable())
                .map(mealResponse -> mealResponse.getMeals().get(0))
                .toList()
                .compose(RXSchedulers.applySchedulersSingle())
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
                        .compose(RXSchedulers.applySchedulersCompletable())
                        .subscribe(
                                () -> homeScreenView.onMealAddedToFavorites(meal),
                                error -> homeScreenView.showError(error.getMessage())
                        )
        );
    }

    @Override
    public void loadCuisines() {
        compositeDisposable.add(mealRepository.fetchMealCuisines()
                .map(CuisineResponse::getCuisines)
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
