package com.example.plateful.details.presenter;

import android.util.Log;

import com.example.plateful.favoritemeal.presenter.FavoriteMealsScreenPresenterImpl;
import com.example.plateful.model.Meal;
import com.example.plateful.model.MealRepository;
import com.example.plateful.network.RXSchedulers;
import com.example.plateful.weeklyplan.model.PlannedMeal;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class MealDetailsScreenPresenterImpl implements MealDetailsScreenPresenter {

    private static final String TAG = MealDetailsScreenPresenterImpl.class.getName();

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final MealRepository mealRepository;

    public MealDetailsScreenPresenterImpl(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }


    @Override
    public void addMealToPlan(Meal meal, long date) {
        PlannedMeal plannedMeal = new PlannedMeal(meal.getIdMeal(), date, meal.getName(), meal.getImageUrl());
        compositeDisposable.add(
                mealRepository.insertPlannedMeal(plannedMeal)
                        .compose(RXSchedulers.applySchedulersCompletable())
                        .subscribe(
                                () -> Log.i(TAG, "addMealToPlan: Success"),
                                error -> Log.i(TAG, "addMealToPlan: Failure")
                        )
        );
    }

    @Override
    public void cleanUpDisposables() {
        compositeDisposable.clear();
    }
}
