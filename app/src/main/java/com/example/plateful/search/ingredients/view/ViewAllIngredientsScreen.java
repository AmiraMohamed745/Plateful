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
import com.example.plateful.model.MealRepository;
import com.example.plateful.model.MealRepositoryImpl;
import com.example.plateful.network.MealRemoteDataSourceImpl;
import com.example.plateful.search.category.model.Category;
import com.example.plateful.search.category.view.ViewAllCategoriesAdapter;
import com.example.plateful.search.ingredients.model.Ingredient;
import com.example.plateful.search.mainsearch.presenter.MainSearchScreenPresenter;
import com.example.plateful.search.mainsearch.presenter.MainSearchScreenPresenterImpl;
import com.example.plateful.search.mainsearch.view.MainSearchScreenView;

import java.util.List;


public class ViewAllIngredientsScreen extends Fragment implements MainSearchScreenView {

    private RecyclerView recyclerViewViewAllIngredients;
    private ViewAllIngredientsAdapter viewAllIngredientsAdapter;

    private MainSearchScreenPresenter mainSearchScreenPresenter;

    private void setUpPresenter() {
        MealRepository mealRepository = MealRepositoryImpl.getInstance(
                new MealRemoteDataSourceImpl(requireContext()),
                MealLocalDataSourceImpl.getInstance(requireContext()/*, UserSession.getCurrentUserId()*/),
                new MealCloudDataSourceImpl()
        );
        mainSearchScreenPresenter = new MainSearchScreenPresenterImpl(
                this, mealRepository);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_all_ingredients_screen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerViewViewAllIngredients = view.findViewById(R.id.recyclerView_ViewAllIngredients);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(requireContext(), 2);
        recyclerViewViewAllIngredients.setLayoutManager(gridLayoutManager);

        setUpPresenter();
        setUpViewAllIngredientsAdapter();
        mainSearchScreenPresenter.loadIngredients();

    }

    private void setUpViewAllIngredientsAdapter() {
       viewAllIngredientsAdapter = new ViewAllIngredientsAdapter(requireContext());
        recyclerViewViewAllIngredients.setAdapter(viewAllIngredientsAdapter);
    }


    @Override
    public void displayCategories(List<Category> categories) {
        //
    }

    @Override
    public void displayIngredients(List<Ingredient> ingredients) {
        viewAllIngredientsAdapter.setIngredientsList(ingredients);
    }

    @Override
    public void showError(String errorMessage) {
        //AlertDialogMessage.makeAlertDialog(errorMessage, requireContext());
    }

    @Override
    public void onDestroyView() {
        mainSearchScreenPresenter.cleanUpDisposables();
        super.onDestroyView();
    }
}