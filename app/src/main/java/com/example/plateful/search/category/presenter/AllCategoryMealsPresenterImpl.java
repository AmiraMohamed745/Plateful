package com.example.plateful.search.category.presenter;

import com.example.plateful.model.MealRepository;
import com.example.plateful.model.MealResponse;
import com.example.plateful.network.RXSchedulers;
import com.example.plateful.search.category.view.AllCategoryMealsView;
import com.example.plateful.search.mainsearch.presenter.MainSearchScreenPresenterImpl;
import com.example.plateful.search.mainsearch.view.MainSearchScreenView;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class AllCategoryMealsPresenterImpl implements AllCategoryMealsPresenter{

    private static final String TAG = AllCategoryMealsPresenterImpl.class.getName();

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final AllCategoryMealsView allCategoryMealsView;
    private final MealRepository mealRepository;

    public AllCategoryMealsPresenterImpl(AllCategoryMealsView allCategoryMealsView, MealRepository mealRepository) {
        this.allCategoryMealsView = allCategoryMealsView;
        this.mealRepository = mealRepository;
    }


    @Override
    public void loadCategoryMeals(String categoryName) {
        compositeDisposable.add(
            mealRepository.fetchMealsByCategory(categoryName)
                    .map(MealResponse::getMeals)
                    .compose(RXSchedulers.applySchedulersSingle())
                    .subscribe(
                            allCategoryMealsView::displayCategoryMeals,
                            error -> allCategoryMealsView.showError(error.getMessage())
                    )
        );
    }

    @Override
    public void cleanUpDisposables() {
        compositeDisposable.clear();
    }
}
