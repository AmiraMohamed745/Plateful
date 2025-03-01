package com.example.plateful.search.category.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.plateful.R;
import com.example.plateful.model.Meal;
import com.example.plateful.model.MealRepository;
import com.example.plateful.model.MealRepositoryImpl;
import com.example.plateful.network.MealRemoteDataSource;
import com.example.plateful.network.MealRemoteDataSourceImpl;
import com.example.plateful.search.category.model.Category;
import com.example.plateful.search.category.presenter.AllCategoryMealsPresenter;
import com.example.plateful.search.category.presenter.AllCategoryMealsPresenterImpl;
import com.example.plateful.search.mainsearch.presenter.MainSearchScreenPresenterImpl;
import com.example.plateful.search.mainsearch.view.MainSearchScreenView;
import com.example.plateful.view.AlertDialogMessage;
import com.example.plateful.view.DestinationNavigator;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;


public class AllCategoryMeals extends Fragment implements AllCategoryMealsView{

    private TextInputEditText textInputEditTextMainSearchBar;

    private RecyclerView recyclerViewAllCategoryMeal;
    private AllCategoryMealsAdapter allCategoryMealsAdapter;

    private AllCategoryMealsPresenter allCategoryMealsPresenter;

    private void setUpPresenter() {
        MealRemoteDataSource mealRemoteDataSource = new MealRemoteDataSourceImpl(requireContext());
        MealRepository mealRepository = MealRepositoryImpl.getInstance(mealRemoteDataSource, null);
        allCategoryMealsPresenter = new AllCategoryMealsPresenterImpl((AllCategoryMealsView) this, mealRepository);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_all_category_meals, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Category category = AllCategoryMealsArgs.fromBundle(getArguments()).getCategory();

        textInputEditTextMainSearchBar = view.findViewById(R.id.textInputEditText_CategoryMealsSearchResults);

        recyclerViewAllCategoryMeal = view.findViewById(R.id.recyclerView_CategoryMealSearchResults);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(requireContext(), 2);
        recyclerViewAllCategoryMeal.setLayoutManager(gridLayoutManager);

        setUpPresenter();
        setUpViewAllCategoryMealsAdapter();
        allCategoryMealsPresenter.loadCategoryMeals(category.getCategoryName());

        textInputEditTextMainSearchBar.setOnClickListener(view1 -> {
            DestinationNavigator.navigateToSearchResultsScreen(view1, category);
        });
    }

    @Override
    public void displayCategoryMeals(List<Meal> categoryMeals) {
        allCategoryMealsAdapter.setCategoryMeals(categoryMeals);
    }

    private void setUpViewAllCategoryMealsAdapter() {
        allCategoryMealsAdapter = new AllCategoryMealsAdapter(requireContext());
        recyclerViewAllCategoryMeal.setAdapter(allCategoryMealsAdapter);
    }

    @Override
    public void showError(String errorMessage) {
        AlertDialogMessage.makeAlertDialog(errorMessage, requireContext());
    }

    @Override
    public void onDestroyView() {
        allCategoryMealsPresenter.cleanUpDisposables();
        super.onDestroyView();
    }
}