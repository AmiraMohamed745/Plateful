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
import com.example.plateful.database.MealLocalDataSource;
import com.example.plateful.database.MealLocalDataSourceImpl;
import com.example.plateful.home.presenter.HomeScreenPresenter;
import com.example.plateful.home.presenter.HomeScreenPresenterImpl;
import com.example.plateful.model.Meal;
import com.example.plateful.model.MealRepository;
import com.example.plateful.model.MealRepositoryImpl;
import com.example.plateful.network.MealRemoteDataSource;
import com.example.plateful.network.MealRemoteDataSourceImpl;
import com.example.plateful.view.AlertDialogMessage;
import com.example.plateful.view.DestinationNavigator;
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
        MealLocalDataSource mealLocalDataSource = MealLocalDataSourceImpl.getInstance(requireContext());
        MealRepository mealRepository = MealRepositoryImpl.getInstance(mealRemoteDataSource, mealLocalDataSource);
        homeScreenPresenter = new HomeScreenPresenterImpl(this, mealRepository);
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

        dailyInspirationAdapter.setOnAddToFavoriteClickListener(meal -> {
            homeScreenPresenter.addMealToFavorites(meal);
        });

        imageViewProfile.setOnClickListener(DestinationNavigator::navigateToProfileScreen);
    }

    @Override
    public void showError(String errorMessage) {
        AlertDialogMessage.makeAlertDialog(errorMessage, requireContext());
    }

    @Override
    public void displayRandomMeals(List<Meal> meals) {
        dailyInspirationAdapter.populateDailyInspirationRecyclerView(meals);
    }

    @Override
    public void onMealAddedToFavorites(Meal meal) {
        dailyInspirationAdapter.updateMealFavoriteStatus(meal, true);
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