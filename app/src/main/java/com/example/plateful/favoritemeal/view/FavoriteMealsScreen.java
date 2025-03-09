package com.example.plateful.favoritemeal.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
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
import com.example.plateful.favoritemeal.presenter.FavoriteMealsScreenPresenter;
import com.example.plateful.favoritemeal.presenter.FavoriteMealsScreenPresenterImpl;
import com.example.plateful.model.Meal;
import com.example.plateful.model.MealRepository;
import com.example.plateful.model.MealRepositoryImpl;
import com.example.plateful.model.SessionManager;
import com.example.plateful.network.MealRemoteDataSource;
import com.example.plateful.network.MealRemoteDataSourceImpl;
import com.example.plateful.utils.AlertDialogMessage;
import com.example.plateful.utils.DestinationNavigator;

import java.util.List;


public class FavoriteMealsScreen extends Fragment implements FavoriteMealsScreenView {

    private TextView textViewStillHaveNotAddedMeals;
    private TextView textViewNoFavoritesForGuests;

    private RecyclerView recyclerViewFavoriteMeals;
    private FavoriteMealsAdapter favoriteMealsAdapter;

    private ImageView imageViewProfile;

    private FavoriteMealsScreenPresenter favoriteMealsScreenPresenter;

    private void setUpPresenter() {
        MealRemoteDataSource mealRemoteDataSource = new MealRemoteDataSourceImpl(requireContext());
        MealLocalDataSource mealLocalDataSource = MealLocalDataSourceImpl.getInstance(requireContext());
        MealRepository mealRepository = MealRepositoryImpl.getInstance(mealRemoteDataSource, mealLocalDataSource);
        favoriteMealsScreenPresenter = new FavoriteMealsScreenPresenterImpl(this, mealRepository);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_meals_screen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imageViewProfile = view.findViewById(R.id.imageView_ic_profile_photo);
        textViewNoFavoritesForGuests = view.findViewById(R.id.textView_NoFavoritesAvailableForGuest);
        textViewStillHaveNotAddedMeals = view.findViewById(R.id.textView_You_Still_have_not_added_meals);

        recyclerViewFavoriteMeals = view.findViewById(R.id.recyclerView_Favorite_Meals);
        recyclerViewFavoriteMeals.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerViewFavoriteMeals.setLayoutManager(linearLayoutManager);

        setUpPresenter();
        setUpFavoriteMealsAdapter();
        favoriteMealsScreenPresenter.loadFavoriteMeals();
        enableSwipeToDeleteAndUndo();
        imageViewProfile.setOnClickListener(DestinationNavigator::navigateToProfileScreen);
    }

    private void setUpFavoriteMealsAdapter() {
        favoriteMealsAdapter = new FavoriteMealsAdapter(requireContext());
        recyclerViewFavoriteMeals.setAdapter(favoriteMealsAdapter);
    }

    private void enableSwipeToDeleteAndUndo() {
        SwipeToDeleteCallback swipeCallback = new SwipeToDeleteCallback(
                requireContext(),
                requireView(),
                favoriteMealsAdapter,
                favoriteMealsScreenPresenter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(swipeCallback);
        itemTouchHelper.attachToRecyclerView(recyclerViewFavoriteMeals);
    }

    private void controlVisibilityOfTextViewStillHaveNotAddedMeals(List<Meal> meals) {
        SessionManager sessionManager = new SessionManager(requireContext());
        if (sessionManager.isGuestMode()) {
            textViewStillHaveNotAddedMeals.setVisibility(View.GONE);
            textViewNoFavoritesForGuests.setVisibility(View.VISIBLE);
        } else {
            if (meals == null || meals.isEmpty()) {
                textViewStillHaveNotAddedMeals.setVisibility(View.VISIBLE);
            } else {
                textViewStillHaveNotAddedMeals.setVisibility(View.GONE);
            }
            textViewNoFavoritesForGuests.setVisibility(View.GONE);
        }
    }

    @Override
    public void displayFavoriteMeals(List<Meal> meals) {
        favoriteMealsAdapter.setFavoriteMealsList(meals);
        controlVisibilityOfTextViewStillHaveNotAddedMeals(meals);
    }

    @Override
    public void showError(String errorMessage) {
        AlertDialogMessage.makeAlertDialog(errorMessage, requireContext());
    }

    @Override
    public void onDestroyView() {
        favoriteMealsScreenPresenter.cleanUpDisposables();
        super.onDestroyView();
    }

}