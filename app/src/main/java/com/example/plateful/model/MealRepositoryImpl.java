package com.example.plateful.model;

import com.example.plateful.authentication.signout.model.MealCloudDataSource;
import com.example.plateful.database.MealLocalDataSource;
import com.example.plateful.home.model.Cuisine;
import com.example.plateful.home.model.CuisineResponse;
import com.example.plateful.network.MealRemoteDataSource;
import com.example.plateful.network.RXSchedulers;
import com.example.plateful.search.category.model.Category;
import com.example.plateful.search.category.model.CategoryResponse;
import com.example.plateful.search.ingredients.model.IngredientResponse;
import com.example.plateful.weeklyplan.model.PlannedMeal;

import java.util.List;
import java.util.stream.Collectors;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

public class MealRepositoryImpl implements MealRepository {

    private static final String TAG = MealRepositoryImpl.class.getSimpleName();

    private final MealRemoteDataSource mealRemoteDataSource;
    private final MealLocalDataSource mealLocalDataSource;
    private final MealCloudDataSource mealCloudDataSource;
    private static MealRepositoryImpl mealRepositoryImplInstance = null;

    private MealRepositoryImpl(MealRemoteDataSource mealRemoteDataSource, MealLocalDataSource mealLocalDataSource, MealCloudDataSource mealCloudDataSource ) {
        this.mealRemoteDataSource = mealRemoteDataSource;
        this.mealLocalDataSource = mealLocalDataSource;
        this.mealCloudDataSource = mealCloudDataSource;
    }

    public static MealRepositoryImpl getInstance(MealRemoteDataSource mealRemoteDataSource, MealLocalDataSource mealLocalDataSource, MealCloudDataSource mealCloudDataSource) {
        if (mealRepositoryImplInstance == null) {
            mealRepositoryImplInstance = new MealRepositoryImpl(mealRemoteDataSource, mealLocalDataSource, mealCloudDataSource);
        }
        return mealRepositoryImplInstance;
    }

    @Override
    public Single<MealResponse> fetchTenRandomMealsForDailyInspiration() {
        return mealRemoteDataSource.getRandomMeal();
    }

    @Override
    public Single<CategoryResponse> fetchMealCategories() {
        return mealRemoteDataSource.getAllCategories();
    }

    @Override
    public Single<MealResponse> fetchMealsByCategory(String categoryName) {
        return mealRemoteDataSource.getMealsByCategory(categoryName);
    }

    @Override
    public Single<MealResponse> fetchMealsByNameForCategory(String searchQuery, String categoryName) {
        return mealRemoteDataSource.searchMealByName(searchQuery);
    }

    @Override
    public Single<MealResponse> fetchMealsByNameForIngredient(String searchQuery, String ingredientName) {
        return mealRemoteDataSource.searchMealByName(searchQuery);
    }


    @Override
    public Single<CuisineResponse> fetchMealCuisines() {
        return mealRemoteDataSource.getAllCuisines("list");
    }


    @Override
    public Single<MealResponse> fetchMealsByCuisine(String cuisineName) {
        return mealRemoteDataSource.getMealsByCuisine(cuisineName);
    }

    @Override
    public Single<IngredientResponse> fetchAllIngredients() {
        return mealRemoteDataSource.getAllIngredients();
    }

    @Override
    public Single<MealResponse> fetchMealsByIngredient(String ingredientName) {
        return mealRemoteDataSource.getMealsByIngredient(ingredientName);
    }

    @Override
    public Flowable<List<Meal>> fetchStoredFavoriteMeals(String uerId) {
        return mealLocalDataSource.getStoredFavoriteMeals(uerId);
    }

    @Override
    public Completable insertMeal(Meal meal) {
        return mealLocalDataSource.insertMealIntoFavorites(meal);
    }

    @Override
    public Completable deleteMeal(Meal meal) {
        return mealLocalDataSource.deleteMealFromFavorites(meal);
    }

    @Override
    public Flowable<List<PlannedMeal>> fetchPlannedMealsForDate(long date, String uerId) {
        return mealLocalDataSource.getMealPlansForDate(date, uerId);
    }

    @Override
    public Flowable<List<PlannedMeal>> fetchAllPlannedMeals(String uerId) {
        return mealLocalDataSource.getAllMealPlans(uerId);
    }

    @Override
    public Completable insertPlannedMeal(PlannedMeal plannedMeal) {
        return mealLocalDataSource.insertMealIntoPlan(plannedMeal);
    }

    @Override
    public Completable deletePlannedMeal(PlannedMeal plannedMeal) {
        return mealLocalDataSource.deleteMealFromPlan(plannedMeal);
    }

    @Override
    public Completable backUpFavoriteMeals(List<Meal> favoriteMeals) {
        return mealCloudDataSource.backUpFavoriteMeals(favoriteMeals);
    }

    @Override
    public Completable backUpPlannedMeals(List<PlannedMeal> plannedMeals) {
        return mealCloudDataSource.backUpPlannedMeals(plannedMeals);
    }

    @Override
    public Completable deleteFavoriteMealFromBackup(Meal favoriteMeal) {
        return mealCloudDataSource.deleteFavoriteMealFromBackup(favoriteMeal);
    }

    @Override
    public Completable deletePlannedMealFromBackup(PlannedMeal plannedMeal) {
        return mealCloudDataSource.deletePlannedMealFromBackup(plannedMeal);
    }

    @Override
    public Single<List<Meal>> provideBackedUpFavoriteMeals() {
        return mealCloudDataSource.provideBackedUpFavoriteMeals();
    }

    @Override
    public Single<List<PlannedMeal>> provideBackedUpPlannedMeals() {
        return mealCloudDataSource.provideBackedUpPlannedMeals();
    }


}
