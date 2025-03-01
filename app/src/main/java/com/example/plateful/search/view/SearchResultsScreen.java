package com.example.plateful.search.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.plateful.R;
import com.example.plateful.authentication.utils.EditableToStringConverter;
import com.example.plateful.model.Meal;
import com.example.plateful.model.MealRepository;
import com.example.plateful.model.MealRepositoryImpl;
import com.example.plateful.network.MealRemoteDataSource;
import com.example.plateful.network.MealRemoteDataSourceImpl;
import com.example.plateful.search.category.model.Category;
import com.example.plateful.search.category.view.AllCategoryMealsArgs;
import com.example.plateful.search.mainsearch.presenter.MainSearchScreenPresenterImpl;
import com.example.plateful.search.mainsearch.view.MainSearchScreenView;
import com.example.plateful.search.mainsearch.view.SearchByCategoryAdapter;
import com.example.plateful.search.presenter.SearchResultsPresenter;
import com.example.plateful.search.presenter.SearchResultsPresenterImpl;
import com.example.plateful.view.AlertDialogMessage;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;


public class SearchResultsScreen extends Fragment implements SearchResultsScreenView{

    private TextInputEditText textInputEditTextMainSearchBar;

    private RecyclerView recyclerViewSearchResults;
    private SearchResultsAdapter searchResultsAdapter;

    private SearchResultsPresenter searchResultsPresenter;

    private void setUpPresenter() {
        MealRemoteDataSource mealRemoteDataSource = new MealRemoteDataSourceImpl(requireContext());
        MealRepository mealRepository = MealRepositoryImpl.getInstance(mealRemoteDataSource, null);
        searchResultsPresenter = new SearchResultsPresenterImpl(this, mealRepository);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_results_screen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Category category = AllCategoryMealsArgs.fromBundle(getArguments()).getCategory();

        textInputEditTextMainSearchBar = view.findViewById(R.id.textInputEditText_SearchResults);

        recyclerViewSearchResults = view.findViewById(R.id.recyclerView_SearchResults);
        recyclerViewSearchResults.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerViewSearchResults.setLayoutManager(linearLayoutManager);

        setUpPresenter();
        setUpSearchResultsAdapter();
        searchResultsPresenter.loadSearchResults(category.getCategoryName());

        textInputEditTextMainSearchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                searchResultsPresenter.onSearchQueryChanged(EditableToStringConverter.convertEditableToString(editable));
            }
        });
    }

    private void setUpSearchResultsAdapter() {
        searchResultsAdapter = new SearchResultsAdapter(requireContext());
        recyclerViewSearchResults.setAdapter(searchResultsAdapter);
    }

    @Override
    public void displaySearchResults(List<Meal> meals) {
        searchResultsAdapter.setSearchResultOutputList(meals);
    }

    @Override
    public void showError(String errorMessage) {
        AlertDialogMessage.makeAlertDialog(errorMessage, requireContext());
    }

    @Override
    public void onDestroyView() {
        searchResultsPresenter.cleanUpDisposables();
        super.onDestroyView();
    }
}