package com.example.plateful.utils;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;

import com.example.plateful.R;
import com.example.plateful.details.view.MealDetailsScreenDirections;
import com.example.plateful.home.cuisines.view.ViewAllCuisinesScreenDirections;
import com.example.plateful.home.model.Cuisine;
import com.example.plateful.model.Meal;
import com.example.plateful.search.category.model.Category;
import com.example.plateful.search.category.view.ViewAllCategoriesScreenDirections;
import com.example.plateful.search.ingredients.model.Ingredient;
import com.example.plateful.search.ingredients.view.ViewAllIngredientsScreenDirections;
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

    public static void navigateToSearchResultsScreen(View view, Category category, Ingredient ingredient) {
        NavDirections action =
                SearchResultsScreenDirections.actionGlobalSearchResultsScreen(category, ingredient);
        Navigation.findNavController(view).navigate(action);
    }

    public static void navigateToHomeScreen(NavController navController) {
        navController.navigate(R.id.action_global_homeScreen);
    }

    public static void navigateToMainSearchScreen(NavController navController) {
        navController.navigate(R.id.action_global_mainSearchScreen);
    }

    public static void navigateToFavoriteMealsScreen(NavController navController) {
        navController.navigate(R.id.action_global_favoriteMealsScreen);
    }

    public static void navigateToWeeklyPlanScreen(NavController navController) {
        navController.navigate(R.id.action_global_weeklyPlanScreen);
    }

    public static void navigateToMealDetailsScreen(View view, Meal meal) {
        NavDirections action =
                MealDetailsScreenDirections.actionGlobalMealDetailsScreen(meal);
        Navigation.findNavController(view).navigate(action);
    }

    public static void navigateToViewAllCuisinesScreen(View view) {
        Navigation.findNavController(view).navigate(R.id.action_homeScreen_to_viewAllCuisinesScreen);
    }

    public static void navigateToAllCuisineMealsScreen(View view, Cuisine cuisine) {
        ViewAllCuisinesScreenDirections.ActionViewAllCuisinesScreenToAllCuisineMealsScreen action =
                ViewAllCuisinesScreenDirections.actionViewAllCuisinesScreenToAllCuisineMealsScreen(cuisine);
        Navigation.findNavController(view).navigate(action);
    }

    public static void navigateToViewAllIngredientsScreen(View view) {
        Navigation.findNavController(view).navigate(R.id.action_mainSearchScreen_to_viewAllIngredientsScreen);
    }

    public static void navigateToAllIngredientsMealsScreen(View view, Ingredient ingredient) {
        ViewAllIngredientsScreenDirections.ActionViewAllIngredientsScreenToAllIngredientsMeals action =
                ViewAllIngredientsScreenDirections.actionViewAllIngredientsScreenToAllIngredientsMeals(ingredient);
        Navigation.findNavController(view).navigate(action);
    }

}
