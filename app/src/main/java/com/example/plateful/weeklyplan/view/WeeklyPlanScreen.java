package com.example.plateful.weeklyplan.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.plateful.R;
import com.example.plateful.database.MealLocalDataSourceImpl;
import com.example.plateful.model.MealRepositoryImpl;
import com.example.plateful.view.AlertDialogMessage;
import com.example.plateful.weeklyplan.model.PlannedMeal;
import com.example.plateful.weeklyplan.presenter.WeeklyPlanScreenPresenter;
import com.example.plateful.weeklyplan.presenter.WeeklyPlanScreenPresenterImpl;
import com.google.android.material.chip.ChipGroup;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class WeeklyPlanScreen extends Fragment implements WeeklyPlanScreenView, OnDeletePlannedMealClickListener {

    private ImageView imageViewProfile;

    private TextView textViewDateChosen;

    private ChipGroup chipGroupDays;

    private RecyclerView recyclerViewWeeklyMeals;

    private WeeklyPlanAdapter weeklyPlanAdapter;

    private WeeklyPlanScreenPresenter weeklyPlanScreenPresenter;

    private void setUpPresenter() {
        weeklyPlanScreenPresenter = new WeeklyPlanScreenPresenterImpl(this, MealRepositoryImpl.getInstance(null, MealLocalDataSourceImpl.getInstance(requireContext())));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_weekly_plan_screen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imageViewProfile = view.findViewById(R.id.imageView_ic_profile_photo);

        textViewDateChosen = view.findViewById(R.id.textView_DateChosen);

        chipGroupDays = view.findViewById(R.id.chipGroupDays);

        recyclerViewWeeklyMeals = view.findViewById(R.id.recyclerView_Weekly_Meals);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(requireContext(), 2);
        recyclerViewWeeklyMeals.setLayoutManager(gridLayoutManager);

        setUpPresenter();
        setUpWeeklyPlanAdapter();

        chipGroupDays.setOnCheckedStateChangeListener((group, checkedIds) -> {

            if (checkedIds.isEmpty()) {
                return;
            }

            int checkedId = checkedIds.get(0);

            Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
            calendar.add(Calendar.DAY_OF_MONTH, -(dayOfWeek - 1));
            int offset = 0;

            if (checkedId == R.id.chipSunday) {
                offset = 0;
            } else if (checkedId == R.id.chipMonday) {
                offset = 1;
            } else if (checkedId == R.id.chipTuesday) {
                offset = 2;
            } else if (checkedId == R.id.chipWednesday) {
                offset = 3;
            } else if (checkedId == R.id.chipThursday) {
                offset = 4;
            } else if (checkedId == R.id.chipFriday) {
                offset = 5;
            } else if (checkedId == R.id.chipSaturday) {
                offset = 6;
            }

            calendar.add(Calendar.DAY_OF_MONTH, offset);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);

            Date selectedDate = calendar.getTime();

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE, MMMM d, yyyy", Locale.getDefault());
            String formattedDate = simpleDateFormat.format(selectedDate);

            textViewDateChosen.setText(formattedDate);

            long chosenDate  = calendar.getTimeInMillis();
            weeklyPlanScreenPresenter.loadPlannedMeals(chosenDate);

        });

    }

    private void setUpWeeklyPlanAdapter() {
        weeklyPlanAdapter = new WeeklyPlanAdapter(requireContext(), this);
        recyclerViewWeeklyMeals.setAdapter(weeklyPlanAdapter);
    }


    @Override
    public void showPlannedMeals(List<PlannedMeal> plannedMeals) {
        weeklyPlanAdapter.setMealsAddedToPlanList(plannedMeals);
    }

    @Override
    public void onDeletePlannedMeal(PlannedMeal plannedMeal) {
        weeklyPlanScreenPresenter.deleteMealFromPlan(plannedMeal);
    }


    @Override
    public void showErrorMessage(String errorMessage) {
        AlertDialogMessage.makeAlertDialog(errorMessage, requireContext());
    }

    @Override
    public void onDestroyView() {
        weeklyPlanScreenPresenter.cleanUpDisposables();
        super.onDestroyView();
    }

}