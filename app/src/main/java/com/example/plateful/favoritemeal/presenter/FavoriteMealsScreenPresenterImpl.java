package com.example.plateful.favoritemeal.presenter;

import com.example.plateful.favoritemeal.view.FavoriteMealsScreenView;
import com.example.plateful.home.presenter.HomeScreenPresenterImpl;
import com.example.plateful.home.view.HomeScreenView;
import com.example.plateful.model.Meal;
import com.example.plateful.model.MealRepository;
import com.example.plateful.network.RXSchedulers;
import com.example.plateful.utils.UserSession;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class FavoriteMealsScreenPresenterImpl implements FavoriteMealsScreenPresenter {

    private static final String TAG = FavoriteMealsScreenPresenterImpl.class.getName();

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final FavoriteMealsScreenView favoriteMealsScreenView;
    private final MealRepository mealRepository;

    public FavoriteMealsScreenPresenterImpl(FavoriteMealsScreenView favoriteMealsScreenView, MealRepository mealRepository) {
        this.favoriteMealsScreenView = favoriteMealsScreenView;
        this.mealRepository = mealRepository;
    }

    @Override
    public void loadFavoriteMeals() {
        compositeDisposable.add(
                mealRepository.fetchStoredFavoriteMeals(UserSession.getCurrentUserId())
                        .compose(RXSchedulers.applySchedulersFlowable())
                        .subscribe(
                                favoriteMealsScreenView::displayFavoriteMeals,
                                error -> favoriteMealsScreenView.showError(error.getMessage())
                        )
        );
    }

    @Override
    public void deleteFavoriteMeal(Meal meal) {
        compositeDisposable.add(
                mealRepository.deleteMeal(meal)
                        .andThen(mealRepository.deleteFavoriteMealFromBackup(meal))
                        .compose(RXSchedulers.applySchedulersCompletable())
                        .subscribe(
                                this::loadFavoriteMeals,
                                error -> favoriteMealsScreenView.showError(error.getMessage())
                        )
        );
    }

    @Override
    public void reInsertFavoriteMeal(Meal meal) {
        compositeDisposable.add(
                mealRepository.insertMeal(meal)
                        .subscribe(
                                this::loadFavoriteMeals,
                                error -> favoriteMealsScreenView.showError(error.getMessage())
                        )
        );
    }

    @Override
    public void cleanUpDisposables() {
        compositeDisposable.clear();
    }
}
