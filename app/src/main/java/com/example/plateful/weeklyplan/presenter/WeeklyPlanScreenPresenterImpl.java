package com.example.plateful.weeklyplan.presenter;

import android.util.Log;

import com.example.plateful.database.MealLocalDataSource;
import com.example.plateful.database.MealLocalDataSourceImpl;
import com.example.plateful.details.presenter.MealDetailsScreenPresenterImpl;
import com.example.plateful.home.presenter.HomeScreenPresenter;
import com.example.plateful.home.presenter.HomeScreenPresenterImpl;
import com.example.plateful.model.MealRepository;
import com.example.plateful.model.MealRepositoryImpl;
import com.example.plateful.network.MealRemoteDataSource;
import com.example.plateful.network.MealRemoteDataSourceImpl;
import com.example.plateful.network.RXSchedulers;
import com.example.plateful.weeklyplan.model.PlannedMeal;
import com.example.plateful.weeklyplan.view.WeeklyPlanScreenView;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class WeeklyPlanScreenPresenterImpl implements WeeklyPlanScreenPresenter {

    private static final String TAG = WeeklyPlanScreenPresenterImpl.class.getName();

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final WeeklyPlanScreenView weeklyPlanScreenView;
    private final MealRepository mealRepository;


    public WeeklyPlanScreenPresenterImpl(WeeklyPlanScreenView weeklyPlanScreenView, MealRepository mealRepository) {
        this.weeklyPlanScreenView = weeklyPlanScreenView;
        this.mealRepository = mealRepository;
    }

    @Override
    public void loadPlannedMeals(long date) {
        compositeDisposable.add(
                mealRepository.fetchPlannedMealsForDate(date)
                        .compose(RXSchedulers.applySchedulersFlowable())
                        .subscribe(
                                weeklyPlanScreenView::showPlannedMeals,
                                error -> weeklyPlanScreenView.showErrorMessage(error.getMessage())
                        )
        );
    }

    @Override
    public void deleteMealFromPlan(PlannedMeal plannedMeal) {
        compositeDisposable.add(
                mealRepository.deletePlannedMeal(plannedMeal)
                        .compose(RXSchedulers.applySchedulersCompletable())
                        .subscribe(
                                () -> Log.i(TAG, "Delete meal: Success"),
                                error -> Log.i(TAG, "Delete meal: Failure")
                        )
        );
    }

    @Override
    public void cleanUpDisposables() {
        compositeDisposable.clear();
    }
}
