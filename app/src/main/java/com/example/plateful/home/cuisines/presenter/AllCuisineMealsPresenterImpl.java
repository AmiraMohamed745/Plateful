package com.example.plateful.home.cuisines.presenter;

import com.example.plateful.home.cuisines.view.AllCuisineMealsView;
import com.example.plateful.model.MealRepository;
import com.example.plateful.model.MealResponse;
import com.example.plateful.network.RXSchedulers;
import com.example.plateful.search.category.presenter.AllCategoryMealsPresenterImpl;
import com.example.plateful.search.category.view.AllCategoryMealsView;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class AllCuisineMealsPresenterImpl implements AllCuisineMealsPresenter {

    private static final String TAG = AllCuisineMealsPresenterImpl.class.getName();

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final AllCuisineMealsView allCuisineMealsView;
    private final MealRepository mealRepository;

    public AllCuisineMealsPresenterImpl(AllCuisineMealsView allCuisineMealsView, MealRepository mealRepository) {
        this.allCuisineMealsView = allCuisineMealsView;
        this.mealRepository = mealRepository;
    }

    @Override
    public void loadCuisineMeals(String cuisineName) {
        compositeDisposable.add(
                mealRepository.fetchMealsByCuisine(cuisineName)
                        .map(MealResponse::getMeals)
                        .compose(RXSchedulers.applySchedulersSingle())
                        .subscribe(
                                allCuisineMealsView::displayCuisineMeals,
                                error -> allCuisineMealsView.showError(error.getMessage())
                        )
        );
    }

    @Override
    public void cleanUpDisposables() {
        compositeDisposable.clear();
    }
}
