package com.example.plateful;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.plateful.view.DestinationNavigator;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationBar;

    private final List<Integer> bottomNavDestinations = Arrays.asList(
            R.id.homeScreen,
            R.id.favoriteMealsScreen,
            R.id.mainSearchScreen,
            R.id.weeklyPlanScreen
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragmentContainerView);
        NavController navController = navHostFragment.getNavController();

        bottomNavigationBar = findViewById(R.id.bottom_navigation_bar);

        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            if (bottomNavDestinations.contains(destination.getId())) {
                bottomNavigationBar.setVisibility(View.VISIBLE);
            } else {
                bottomNavigationBar.setVisibility(View.GONE);
            }
        });

        bottomNavigationBar.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.menu_home) {
                DestinationNavigator.navigateToHomeScreen(navController);
            } else if (itemId == R.id.menu_search) {
                DestinationNavigator.navigateToMainSearchScreen(navController);
            } else if (itemId == R.id.menu_favorite) {
                DestinationNavigator.navigateToFavoriteMealsScreen(navController);
            } else if (itemId == R.id.menu_calendar) {
                DestinationNavigator.navigateToWeeklyPlanScreen(navController);
            }
            return true;
        });
    }
}