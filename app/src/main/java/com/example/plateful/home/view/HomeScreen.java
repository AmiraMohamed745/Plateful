package com.example.plateful.home.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.plateful.R;
import com.example.plateful.home.presenter.HomeScreenPresenter;
import com.example.plateful.home.presenter.HomeScreenPresenterImpl;
import com.example.plateful.model.Meal;
import com.example.plateful.model.MealRepository;
import com.example.plateful.model.MealRepositoryImpl;
import com.example.plateful.network.MealRemoteDataSource;
import com.example.plateful.network.MealRemoteDataSourceImpl;
import com.google.android.material.carousel.CarouselLayoutManager;

import java.util.List;


public class HomeScreen extends Fragment implements HomeScreenView {

    private static final String TAG = HomeScreen.class.getSimpleName();

    private TextView textViewDailyInspiration;
    private TextView textViewBrowseCuisines;

    private RecyclerView recyclerViewDailyInspiration;
    private DailyInspirationAdapter dailyInspirationAdapter;

    private ImageView imageViewProfile;

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
        textViewBrowseCuisines = view.findViewById(R.id.textView_Browse_Cuisines);

        imageViewProfile = view.findViewById(R.id.imageView_ic_profile_photo);

        recyclerViewDailyInspiration = view.findViewById(R.id.recyclerView_Daily_Inspiration);
        recyclerViewDailyInspiration.setHasFixedSize(true);
        CarouselLayoutManager carouselLayoutManager = new CarouselLayoutManager();
        carouselLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerViewDailyInspiration.setLayoutManager(carouselLayoutManager);

        textViewDailyInspiration.setOnClickListener(view1 -> {
            Navigation.findNavController(view).navigate(R.id.action_homeScreen_to_profileScreen);
        });

        setUpPresenter();
        setUpDailyInspirationAdapter();
        homeScreenPresenter.loadRandomMeal();
    }

    @Override
    public void showError(String errorMessage) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setMessage(errorMessage).setTitle("An error occurred");
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public void displayRandomMeals(List<Meal> meals) {
        dailyInspirationAdapter.populateDailyInspirationRecyclerView(meals);
    }


    private void setUpDailyInspirationAdapter() {
        dailyInspirationAdapter = new DailyInspirationAdapter(requireContext());
        recyclerViewDailyInspiration.setAdapter(dailyInspirationAdapter);
    }

    @Override
    public void onDestroyView() {
        homeScreenPresenter.cleanUpDisposables();
        super.onDestroyView();
    }
}