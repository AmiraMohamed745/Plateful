package com.example.plateful.home.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.plateful.R;
import com.example.plateful.home.presenter.HomeScreenPresenter;
import com.example.plateful.home.presenter.HomeScreenPresenterImpl;
import com.example.plateful.model.Meal;
import com.example.plateful.model.MealRepository;
import com.example.plateful.model.MealRepositoryImpl;
import com.example.plateful.network.MealRemoteDataSource;
import com.example.plateful.network.MealRemoteDataSourceImpl;


public class HomeScreen extends Fragment implements HomeScreenView {

    private static final String TAG = HomeScreen.class.getSimpleName();

    private TextView textViewDailyInspiration;

    private HomeScreenPresenter homeScreenPresenter;

    private void setUpPresenter() {
        MealRemoteDataSource mealRemoteDataSource = new MealRemoteDataSourceImpl(requireContext());
        MealRepository mealRepository = MealRepositoryImpl.getInstance(mealRemoteDataSource);
        homeScreenPresenter = new HomeScreenPresenterImpl(this, mealRepository); // not sure about the casting (HomeScreenView)
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_screen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textViewDailyInspiration = view.findViewById(R.id.textView_DailyInspiration);

        textViewDailyInspiration.setOnClickListener(view1 -> {
            Navigation.findNavController(view).navigate(R.id.action_homeScreen_to_profileScreen);
        });

        setUpPresenter();
        homeScreenPresenter.loadRandomMeal(); // logging on LogCat to ensure connection is working
    }

    @Override
    public void showError(String errorMessage) {

    }

    @Override
    public void displayRandomMeal(Meal meal) {
        textViewDailyInspiration.setText(meal.getName());
    }

    @Override
    public void displayMealByCuisine(Meal meal) {

    }

    @Override
    public void displayMealByCategory(Meal meal) {

    }

    @Override
    public void addMealToWeeklyPlan(Meal meal) {

    }

    @Override
    public void addMealToFavorites(Meal meal) {

    }
}