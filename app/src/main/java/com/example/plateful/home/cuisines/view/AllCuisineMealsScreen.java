package com.example.plateful.home.cuisines.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.plateful.R;
import com.example.plateful.authentication.signout.model.MealCloudDataSourceImpl;
import com.example.plateful.database.MealLocalDataSourceImpl;
import com.example.plateful.home.cuisines.presenter.AllCuisineMealsPresenter;
import com.example.plateful.home.cuisines.presenter.AllCuisineMealsPresenterImpl;
import com.example.plateful.home.model.Cuisine;
import com.example.plateful.model.Meal;
import com.example.plateful.model.MealRepository;
import com.example.plateful.model.MealRepositoryImpl;
import com.example.plateful.network.MealRemoteDataSourceImpl;
import com.example.plateful.utils.AlertDialogMessage;
import com.example.plateful.utils.UserSession;

import java.util.List;


public class AllCuisineMealsScreen extends Fragment implements AllCuisineMealsView{

    private ImageView imageView_BackArrow;

    private RecyclerView recyclerViewAllCuisineMeals;
    private AllCuisineMealsAdapter allCuisineMealsAdapter;

    private AllCuisineMealsPresenter allCuisineMealsPresenter;

    private void setUpPresenter() {
        MealRepository mealRepository = MealRepositoryImpl.getInstance(
                new MealRemoteDataSourceImpl(requireContext()),
                MealLocalDataSourceImpl.getInstance(requireContext()),
                new MealCloudDataSourceImpl()
        );
        allCuisineMealsPresenter = new AllCuisineMealsPresenterImpl(this, mealRepository);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_all_cuisine_meals_screen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Cuisine cuisine = AllCuisineMealsScreenArgs.fromBundle(getArguments()).getCuisine();

        imageView_BackArrow = view.findViewById(R.id.imageView_BackArrow);

        recyclerViewAllCuisineMeals = view.findViewById(R.id.recyclerView_AllCuisineMeals);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(requireContext(), 2);
        recyclerViewAllCuisineMeals.setLayoutManager(gridLayoutManager);

        setUpPresenter();
        setUpViewAllCategoryMealsAdapter();
        allCuisineMealsPresenter.loadCuisineMeals(cuisine.getCuisineName());
    }

    private void setUpViewAllCategoryMealsAdapter() {
        allCuisineMealsAdapter = new AllCuisineMealsAdapter(requireContext());
        recyclerViewAllCuisineMeals.setAdapter(allCuisineMealsAdapter);
    }

    @Override
    public void displayCuisineMeals(List<Meal> cuisineMeals) {
        allCuisineMealsAdapter.setCuisineMeals(cuisineMeals);
    }

    @Override
    public void showError(String errorMessage) {
        AlertDialogMessage.makeAlertDialog(errorMessage, requireContext());
    }
}