package com.example.plateful.home.cuisines.presenter;

import com.example.plateful.home.cuisines.view.ViewAllCuisinesScreenView;
import com.example.plateful.home.model.Cuisine;
import com.example.plateful.home.presenter.CuisineImageMapper;
import com.example.plateful.home.presenter.HomeScreenPresenterImpl;
import com.example.plateful.home.view.HomeScreenView;
import com.example.plateful.model.MealRepository;
import com.example.plateful.network.RXSchedulers;

import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;

public class ViewAllCuisinesPresenterImpl implements ViewAllCuisinesPresenter{

    private static final String TAG = ViewAllCuisinesPresenterImpl.class.getName();

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final ViewAllCuisinesScreenView viewAllCuisinesScreenView;
    private final MealRepository mealRepository;

    public ViewAllCuisinesPresenterImpl(ViewAllCuisinesScreenView viewAllCuisinesScreenView, MealRepository mealRepository) {
        this.viewAllCuisinesScreenView = viewAllCuisinesScreenView;
        this.mealRepository = mealRepository;
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
                        viewAllCuisinesScreenView::displayCuisines,
                        error -> viewAllCuisinesScreenView.showError(error.getMessage()))
        );
    }

    @Override
    public void cleanUpDisposables() {

    }
}
