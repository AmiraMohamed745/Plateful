package com.example.plateful.details.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.plateful.R;
import com.example.plateful.database.MealLocalDataSourceImpl;
import com.example.plateful.details.model.Ingredient;
import com.example.plateful.details.presenter.MealDetailsScreenPresenter;
import com.example.plateful.details.presenter.MealDetailsScreenPresenterImpl;
import com.example.plateful.model.Meal;
import com.example.plateful.model.MealRepository;
import com.example.plateful.model.MealRepositoryImpl;
import com.example.plateful.model.SessionManager;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateValidatorPointForward;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;


public class MealDetailsScreen extends Fragment {

    private ImageView imageViewBackArrow;
    private ImageView imageViewMealImage;

    private TextView textViewMealName;
    private TextView textViewCuisine;
    private TextView textViewCuisineValue;
    private TextView textViewCategory;
    private TextView textViewCategoryValue;
    private TextView textViewInstructions;
    private TextView textViewInstructionsValue;
    private TextView textViewIngredients;
    private TextView textViewVideo;

    private RecyclerView recyclerViewIngredients;
    private IngredientsAdapter ingredientsAdapter;

    private YouTubePlayerView youTubePlayerView;

    private Button buttonAddToFavorites;
    private Button buttonAddToPlan;

    private MealDetailsScreenPresenter mealDetailsScreenPresenter;

    private void setUpPresenter() {
        mealDetailsScreenPresenter = new MealDetailsScreenPresenterImpl(MealRepositoryImpl.getInstance(null, MealLocalDataSourceImpl.getInstance(requireContext())));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_meal_details_screen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Meal meal = MealDetailsScreenArgs.fromBundle(getArguments()).getMeal();

        imageViewBackArrow = view.findViewById(R.id.imageView_BackArrow);
        imageViewMealImage = view.findViewById(R.id.imageView_Meal_Image);

        textViewMealName = view.findViewById(R.id.textView_Meal_Name);
        textViewCuisine = view.findViewById(R.id.textView_Cuisine);
        textViewCuisineValue = view.findViewById(R.id.textView_Cuisine_Value);
        textViewCategory = view.findViewById(R.id.textView_Category);
        textViewCategoryValue = view.findViewById(R.id.textView_Category_Value);
        textViewInstructions = view.findViewById(R.id.textView_Instructions);
        textViewInstructionsValue = view.findViewById(R.id.textView_Instructions_Value);
        textViewIngredients = view.findViewById(R.id.textView_Ingredients);
        textViewVideo = view.findViewById(R.id.textView_Video);

        recyclerViewIngredients = view.findViewById(R.id.recyclerView_Ingredients);
        recyclerViewIngredients.setNestedScrollingEnabled(false);
        recyclerViewIngredients.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerViewIngredients.setLayoutManager(linearLayoutManager);

        youTubePlayerView = view.findViewById(R.id.youtube_player_view);
        getLifecycle().addObserver(youTubePlayerView);
        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                String videoId = extractVideoId(meal.getVideoUrl());
                youTubePlayer.cueVideo(videoId, 0);
            }

            @Override
            public void onStateChange(@NonNull YouTubePlayer youTubePlayer, @NonNull PlayerConstants.PlayerState state) {
                super.onStateChange(youTubePlayer, state);
            }
        });

        buttonAddToFavorites = view.findViewById(R.id.button_addToFavorites);
        buttonAddToPlan = view.findViewById(R.id.button_addToPlan);

        setUpPresenter();
        setUpIngredientsAdapter();
        displayMealDetails(meal);

        SessionManager sessionManager = new SessionManager(requireContext());
        if (sessionManager.isGuestMode()) {
            buttonAddToFavorites.setEnabled(false);
            buttonAddToFavorites.setBackgroundColor(ContextCompat.getColor(requireContext(),
                    R.color.hintTextColor));
            buttonAddToPlan.setEnabled(false);
            buttonAddToPlan.setBackgroundColor(ContextCompat.getColor(requireContext(),
                    R.color.hintTextColor));
        }
        buttonAddToPlan.setOnClickListener(view1 -> {
            showDatePicker(meal);
        });
    }

    private void setUpIngredientsAdapter() {
        ingredientsAdapter = new IngredientsAdapter(requireContext());
        recyclerViewIngredients.setAdapter(ingredientsAdapter);
    }

    private void displayMealDetails(Meal meal) {
        textViewMealName.setText(meal.getName());
        textViewCuisineValue.setText(meal.getCuisine());
        textViewCategoryValue.setText(meal.getCategory());
        textViewInstructionsValue.setText(meal.getInstructions());
        Glide.with(requireContext())
                .load(meal.getImageUrl())
                .placeholder(R.drawable.bg_placeholder)
                .error(R.drawable.bg_placeholder)
                .into(imageViewMealImage);
        List<Ingredient> ingredients = ingredientsAdapter.extractMealIngredients(meal);
        ingredientsAdapter.setIngredients(ingredients);
    }

    private String extractVideoId(String url) {
        if (url != null && url.contains("v=")) {
            String[] parts = url.split("v=");
            if (parts.length > 1) {
                String id = parts[1];
                if (id.contains("&")) {
                    id = id.split("&")[0];
                }
                return id;
            }
        }
        return "";
    }

    private void showDatePicker(Meal meal) {
        MaterialDatePicker.Builder<Long> builder = MaterialDatePicker.Builder.datePicker();
        builder.setTitleText(R.string.select_a_date);
        builder.setSelection(MaterialDatePicker.todayInUtcMilliseconds());
        CalendarConstraints.Builder constraintsBuilder = new CalendarConstraints.Builder();
        constraintsBuilder.setValidator(DateValidatorPointForward.now());
        builder.setCalendarConstraints(constraintsBuilder.build());
        MaterialDatePicker<Long> datePicker = builder.build();
        datePicker.addOnPositiveButtonClickListener(selection -> {
            Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
            cal.setTimeInMillis(selection);
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
            long chosenDate = cal.getTimeInMillis();
            mealDetailsScreenPresenter.addMealToPlan(
                    meal,
                    chosenDate);
        });
        datePicker.show(getChildFragmentManager(), "DATE_PICKER");
    }
}