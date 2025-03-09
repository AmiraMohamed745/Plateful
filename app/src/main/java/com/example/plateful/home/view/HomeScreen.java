package com.example.plateful.home.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.plateful.R;
import com.example.plateful.authentication.signout.model.MealCloudDataSourceImpl;
import com.example.plateful.database.MealLocalDataSource;
import com.example.plateful.database.MealLocalDataSourceImpl;
import com.example.plateful.home.model.Cuisine;
import com.example.plateful.home.presenter.HomeScreenPresenter;
import com.example.plateful.home.presenter.HomeScreenPresenterImpl;
import com.example.plateful.model.Meal;
import com.example.plateful.model.MealRepository;
import com.example.plateful.model.MealRepositoryImpl;
import com.example.plateful.network.MealRemoteDataSource;
import com.example.plateful.network.MealRemoteDataSourceImpl;
import com.example.plateful.utils.AlertDialogMessage;
import com.example.plateful.utils.DestinationNavigator;
import com.example.plateful.utils.UserSession;
import com.google.android.material.carousel.CarouselLayoutManager;

import java.util.List;


public class HomeScreen extends Fragment implements HomeScreenView {

    private static final String TAG = HomeScreen.class.getSimpleName();

    private TextView textViewDailyInspiration;
    private TextView textViewBrowseCuisines;
    private TextView textViewViewAll;

    private RecyclerView recyclerViewDailyInspiration;
    private RecyclerView recyclerViewBrowseCuisines;
    private DailyInspirationAdapter dailyInspirationAdapter;
    private BrowseCuisinesAdapter browseCuisinesAdapter;

    private ImageView imageViewProfile;

    private HomeScreenPresenter homeScreenPresenter;

    private void setUpPresenter() {
        MealRepository mealRepository = MealRepositoryImpl.getInstance(
                new MealRemoteDataSourceImpl(requireContext()),
                MealLocalDataSourceImpl.getInstance(requireContext()),
                new MealCloudDataSourceImpl()
        );
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
        textViewViewAll = view.findViewById(R.id.textView_ViewAll);

        imageViewProfile = view.findViewById(R.id.imageView_ic_profile_photo);

        recyclerViewDailyInspiration = view.findViewById(R.id.recyclerView_Daily_Inspiration);
        recyclerViewDailyInspiration.setHasFixedSize(true);
        recyclerViewDailyInspiration.setNestedScrollingEnabled(false);
        CarouselLayoutManager carouselLayoutManager = new CarouselLayoutManager();
        carouselLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerViewDailyInspiration.setLayoutManager(carouselLayoutManager);

        recyclerViewBrowseCuisines = view.findViewById(R.id.recyclerView_Browse_Cuisines);
        recyclerViewBrowseCuisines.setHasFixedSize(true);
        recyclerViewBrowseCuisines.setNestedScrollingEnabled(false);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(requireContext(), 2);
        gridLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerViewBrowseCuisines.setLayoutManager(gridLayoutManager);

        textViewDailyInspiration.setOnClickListener(view1 -> {
            Navigation.findNavController(view).navigate(R.id.action_homeScreen_to_profileScreen);
        });

        setUpPresenter();
        setUpDailyInspirationAdapter();
        homeScreenPresenter.loadRandomMeal();

        dailyInspirationAdapter.setOnAddToFavoriteClickListener(meal -> {
            homeScreenPresenter.addMealToFavorites(meal);
        });

        setUBrowseCuisinesAdapter();
        homeScreenPresenter.loadCuisines();
        textViewViewAll.setOnClickListener(view1 -> {
            DestinationNavigator.navigateToViewAllCuisinesScreen(requireView());
        });

        imageViewProfile.setOnClickListener(DestinationNavigator::navigateToProfileScreen);
    }

    @Override
    public void showError(String errorMessage) {
        //AlertDialogMessage.makeAlertDialog(errorMessage, requireContext());
    }

    @Override
    public void displayRandomMeals(List<Meal> meals) {
        dailyInspirationAdapter.populateDailyInspirationRecyclerView(meals);
    }

    @Override
    public void displayCuisines(List<Cuisine> cuisines) {
        browseCuisinesAdapter.setCuisinesList(cuisines);
    }

    @Override
    public void onMealAddedToFavorites(Meal meal) {
        dailyInspirationAdapter.updateMealFavoriteStatus(meal, true);
    }


    private void setUpDailyInspirationAdapter() {
        dailyInspirationAdapter = new DailyInspirationAdapter(requireContext());
        recyclerViewDailyInspiration.setAdapter(dailyInspirationAdapter);
    }

    private void setUBrowseCuisinesAdapter() {
        browseCuisinesAdapter = new BrowseCuisinesAdapter(requireContext());
        recyclerViewBrowseCuisines.setAdapter(browseCuisinesAdapter);
    }

    @Override
    public void onDestroyView() {
        homeScreenPresenter.cleanUpDisposables();
        super.onDestroyView();
    }

}