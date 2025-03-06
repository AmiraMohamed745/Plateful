package com.example.plateful.weeklyplan;

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
import com.google.android.material.chip.ChipGroup;

public class WeeklyPlanScreen extends Fragment {

    private ImageView imageViewProfile;

    private TextView textViewDateChosen;

    private ChipGroup chipGroupDays;

    private RecyclerView recyclerViewWeeklyMeals;

    private WeeklyPlanAdapter weeklyPlanAdapter;

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


    }
}