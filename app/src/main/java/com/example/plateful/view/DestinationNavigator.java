package com.example.plateful.view;

import android.view.View;

import androidx.navigation.NavDirections;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;

import com.example.plateful.R;
import com.example.plateful.search.category.model.Category;
import com.example.plateful.search.category.view.ViewAllCategoriesScreenDirections;
import com.example.plateful.search.view.SearchResultsScreenDirections;

public class DestinationNavigator {

    public static void navigateToHomeScreen(View view) {
        Navigation.findNavController(view).navigate(R.id.action_global_homeScreen);
    }

    public static void navigateToWelcomeScreen(View view) {
        Navigation.findNavController(view).navigate(R.id.action_global_welcomeScreen);
    }

    public static void navigateToWelcomeScreenWithAnimation(View view, NavOptions navOptions) {
        Navigation.findNavController(view).navigate(R.id.action_splashScreen_to_welcomeScreen, null, navOptions);
    }

    public static void navigateToProfileScreen(View view) {
        Navigation.findNavController(view).navigate(R.id.action_global_profileScreen);
    }

    public static void navigateToSignUpScreen(View view) {
        Navigation.findNavController(view).navigate(R.id.action_welcomeScreen_to_signUpScreen);
    }

    public static void navigateToSignInScreen(View view) {
        Navigation.findNavController(view).navigate(R.id.action_welcomeScreen_to_signInScreen);
    }

    public static void navigateToViewAllCategoriesScreen(View view) {
        Navigation.findNavController(view).navigate(R.id.action_mainSearchScreen_to_viewAllCategoriesScreen);
    }

    public static void navigateToAllCategoryMealsScreen(View view, Category category) {
        ViewAllCategoriesScreenDirections.ActionViewAllCategoriesScreenToAllCategoryMeals action =
                ViewAllCategoriesScreenDirections.actionViewAllCategoriesScreenToAllCategoryMeals(category);
        Navigation.findNavController(view).navigate(action);
    }

    public static void navigateToSearchResultsScreen(View view, Category category) {
        NavDirections action =
                SearchResultsScreenDirections.actionGlobalSearchResultsScreen(category);
        Navigation.findNavController(view).navigate(action);
    }

}
