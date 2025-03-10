package com.example.plateful.search.ingredients.presenter;

import com.example.plateful.model.MealRepository;
import com.example.plateful.model.MealResponse;
import com.example.plateful.network.RXSchedulers;
import com.example.plateful.search.ingredients.view.AllIngredientsMealsView;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class AllIngredientMealsPresenterImpl implements AllIngredientMealsPresenter{

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final AllIngredientsMealsView allIngredientsMealsView;
    private final MealRepository mealRepository;

    public AllIngredientMealsPresenterImpl(AllIngredientsMealsView allIngredientsMealsView, MealRepository mealRepository) {
        this.allIngredientsMealsView = allIngredientsMealsView;
        this.mealRepository = mealRepository;
    }

    @Override
    public void loadIngredientMeals(String ingredientName) {
        compositeDisposable.add(
                mealRepository.fetchMealsByIngredient(ingredientName)
                        .map(MealResponse::getMeals)
                        .compose(RXSchedulers.applySchedulersSingle())
                        .subscribe(
                                allIngredientsMealsView::displayIngredientMeals,
                                error -> allIngredientsMealsView.showError(error.getMessage())
                        )
        );
    }

    @Override
    public void cleanUpDisposables() {
        compositeDisposable.clear();
    }
}
