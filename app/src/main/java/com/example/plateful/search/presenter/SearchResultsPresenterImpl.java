package com.example.plateful.search.presenter;

import com.example.plateful.model.MealRepository;
import com.example.plateful.search.category.presenter.AllCategoryMealsPresenterImpl;
import com.example.plateful.search.category.view.AllCategoryMealsView;
import com.example.plateful.search.view.SearchResultsScreenView;

import java.util.concurrent.TimeUnit;

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
    public void loadSearchResults(String categoryName) {
        compositeDisposable.add(
            searchQuerySubject
                    .debounce(300, TimeUnit.MILLISECONDS)
                    .distinctUntilChanged()
                    .switchMap(query ->
                            mealRepository.fetchMealsByName(query, categoryName)
                                    .toObservable())
                    .subscribe(
                            searchResultsScreenView::displaySearchResults,
                            error -> searchResultsScreenView.showError(error.getMessage())
                    )
        );
    }

    @Override
    public void cleanUpDisposables() {
        compositeDisposable.clear();
    }
}
