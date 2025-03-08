package com.example.plateful;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.plateful.network.ConnectivityHelper;
import com.example.plateful.network.ConnectivityListener;
import com.example.plateful.utils.DestinationNavigator;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationBar;

    private ConnectivityHelper connectivityHelper;
    private TextView textViewNoInternetConnection;

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

        connectivityHelper = new ConnectivityHelper(this);
        bottomNavigationBar = findViewById(R.id.bottom_navigation_bar);
        textViewNoInternetConnection = findViewById(R.id.textView_NoInternetConnection);

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

        connectivityHelper.registerConnectivityCallback(new ConnectivityListener() {
            @Override
            public void onNetworkAvailable() {
                runOnUiThread(() -> {
                        textViewNoInternetConnection.setVisibility(View.GONE);
                });
            }

            @Override
            public void onNetworkLost() {
                runOnUiThread(() -> {
                        textViewNoInternetConnection.setVisibility(View.VISIBLE);
                });
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        connectivityHelper.unregisterConnectivityCallback();
    }
}