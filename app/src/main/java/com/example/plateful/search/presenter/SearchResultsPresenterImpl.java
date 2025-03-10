package com.example.plateful.search.presenter;

import com.example.plateful.model.Meal;
import com.example.plateful.model.MealRepository;
import com.example.plateful.network.RXSchedulers;
import com.example.plateful.search.view.SearchResultsScreenView;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.subjects.PublishSubject;

public class SearchResultsPresenterImpl implements SearchResultsPresenter {

    private static final String TAG = SearchResultsPresenterImpl.class.getName();

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final SearchResultsScreenView searchResultsScreenView;
    private final MealRepository mealRepository;
    private final PublishSubject<String> searchQuerySubject = PublishSubject.create();

    public SearchResultsPresenterImpl(SearchResultsScreenView searchResultsScreenView, MealRepository mealRepository) {
        this.searchResultsScreenView = searchResultsScreenView;
        this.mealRepository = mealRepository;
    }

    @Override
    public void onSearchQueryChanged(String query) {
        searchQuerySubject.onNext(query);
    }

    @Override
    public void loadSearchResultsForCategoryMeals(String categoryName) {
        compositeDisposable.add(
                searchQuerySubject
                        .debounce(300, TimeUnit.MILLISECONDS)
                        .distinctUntilChanged()
                        .switchMap(query ->
                                mealRepository.fetchMealsByNameForCategory(query, categoryName)
                                        .toObservable())
                        .subscribe(
                                searchResultsScreenView::displaySearchResults,
                                error -> searchResultsScreenView.showError(error.getMessage())
                        )
        );
    }

    @Override
    public void loadSearchResultsForIngredientMeals(String ingredientName) {
        compositeDisposable.add(
                searchQuerySubject
                        .debounce(300, TimeUnit.MILLISECONDS)
                        .distinctUntilChanged()
                        .switchMap(query ->
                                mealRepository.fetchMealsByNameForIngredient(query, ingredientName)
                                        .compose(RXSchedulers.applySchedulersSingle())
                                        .toObservable()
                                        .map(mealResponse -> {
                                            List<Meal> filteredMeals = mealResponse.getMeals().stream()
                                                    .filter(meal -> meal.getIngredientList().stream()
                                                            .anyMatch(ingredient -> ingredient.equalsIgnoreCase(ingredientName)))
                                                    .collect(Collectors.toList());
                                            mealResponse.setMeals(filteredMeals);
                                            return mealResponse;
                                        })

                        )
                        .subscribe(
                                mealResponse -> searchResultsScreenView.displaySearchResults(mealResponse.getMeals()),
                                error -> searchResultsScreenView.showError(error.getMessage())
                        )
        );
    }

    @Override
    public void cleanUpDisposables() {
        compositeDisposable.clear();
    }
}
