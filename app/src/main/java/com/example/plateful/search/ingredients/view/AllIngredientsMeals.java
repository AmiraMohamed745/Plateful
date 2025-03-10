package com.example.plateful.search.ingredients.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.plateful.R;
import com.example.plateful.authentication.signout.model.MealCloudDataSourceImpl;
import com.example.plateful.database.MealLocalDataSourceImpl;
import com.example.plateful.model.Meal;
import com.example.plateful.model.MealRepository;
import com.example.plateful.model.MealRepositoryImpl;
import com.example.plateful.network.MealRemoteDataSourceImpl;
import com.example.plateful.search.category.model.Category;
import com.example.plateful.search.category.presenter.AllCategoryMealsPresenter;
import com.example.plateful.search.category.presenter.AllCategoryMealsPresenterImpl;
import com.example.plateful.search.category.view.AllCategoryMealsAdapter;
import com.example.plateful.search.category.view.AllCategoryMealsArgs;
import com.example.plateful.search.ingredients.model.Ingredient;
import com.example.plateful.search.ingredients.presenter.AllIngredientMealsPresenter;
import com.example.plateful.search.ingredients.presenter.AllIngredientMealsPresenterImpl;
import com.example.plateful.utils.DestinationNavigator;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;


public class AllIngredientsMeals extends Fragment implements AllIngredientsMealsView{

    private TextInputEditText textInputEditTextMainSearchBar;

    private RecyclerView recyclerViewAllIngredientsMeals;
    private AllIngredientsMealsAdapter allIngredientsMealsAdapter;

    private AllIngredientMealsPresenter allIngredientMealsPresenter;

    private void setUpPresenter() {
        MealRepository mealRepository = MealRepositoryImpl.getInstance(
                new MealRemoteDataSourceImpl(requireContext()),
                MealLocalDataSourceImpl.getInstance(requireContext()),
                new MealCloudDataSourceImpl()
        );
        allIngredientMealsPresenter = new AllIngredientMealsPresenterImpl(this, mealRepository);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_all_ingredients_meals, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Ingredient ingredient = AllIngredientsMealsArgs.fromBundle(getArguments()).getIngredient();

        textInputEditTextMainSearchBar = view.findViewById(R.id.textInputEditText_IngredientsMealsSearchResults);
        recyclerViewAllIngredientsMeals = view.findViewById(R.id.recyclerView_IngredientsMealSearchResults);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(requireContext(), 2);
        recyclerViewAllIngredientsMeals.setLayoutManager(gridLayoutManager);

        textInputEditTextMainSearchBar.setOnClickListener(view1 -> {
            DestinationNavigator.navigateToSearchResultsScreen(view1, null ,ingredient);
        });

        setUpPresenter();
        setUpAllIngredientsMealsAdapter();
        allIngredientMealsPresenter.loadIngredientMeals(ingredient.getName());
    }

    private void setUpAllIngredientsMealsAdapter() {
        allIngredientsMealsAdapter = new AllIngredientsMealsAdapter(requireContext());
       recyclerViewAllIngredientsMeals.setAdapter(allIngredientsMealsAdapter);
    }

    @Override
    public void displayIngredientMeals(List<Meal> ingredientMeals) {
        allIngredientsMealsAdapter.setIngredientMeals(ingredientMeals);
    }

    @Override
    public void showError(String errorMessage) {
        //
    }

    @Override
    public void onDestroyView() {
        allIngredientMealsPresenter.cleanUpDisposables();
        super.onDestroyView();
    }
}