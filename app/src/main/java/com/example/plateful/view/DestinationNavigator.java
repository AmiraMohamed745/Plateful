package com.example.plateful.view;

import android.view.View;

import androidx.navigation.Navigation;

import com.example.plateful.R;

public class DestinationNavigator {

    public static void navigateToHomeScreen(View view) {
        Navigation.findNavController(view).navigate(R.id.action_global_homeScreen);
    }

    public static void navigateToWelcomeScreen(View view) {
        Navigation.findNavController(view).navigate(R.id.action_global_welcomeScreen);
    }

    public static void navigateToProfileScreen(View view) {
        Navigation.findNavController(view).navigate(R.id.action_global_profileScreen);
    }

}
