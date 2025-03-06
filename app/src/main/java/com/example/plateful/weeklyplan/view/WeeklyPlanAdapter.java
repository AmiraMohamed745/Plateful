package com.example.plateful.weeklyplan.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.plateful.R;
import com.example.plateful.model.Meal;
import com.example.plateful.weeklyplan.model.PlannedMeal;

import java.util.ArrayList;
import java.util.List;

public class WeeklyPlanAdapter extends RecyclerView.Adapter<WeeklyPlanAdapter.ViewHolder> {

    private static final String TAG = WeeklyPlanAdapter.class.getSimpleName();

    private final Context context;
    private final OnDeletePlannedMealClickListener onDeletePlannedMealClickListener;
    private final List<PlannedMeal> mealsAddedToPlan = new ArrayList<>();

    public WeeklyPlanAdapter(Context context, OnDeletePlannedMealClickListener onDeletePlannedMealClickListener) {
        this.context = context;
        this.onDeletePlannedMealClickListener = onDeletePlannedMealClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup recyclerView, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(recyclerView.getContext());
        View view = inflater.inflate(R.layout.weekly_plan_meal_row, recyclerView, false);
        ViewHolder viewHolder = new ViewHolder(view);
        Log.i(TAG, "onCreateViewHolder: ");
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PlannedMeal plannedMeal = mealsAddedToPlan.get(position);
        Glide.with(context).load(plannedMeal.getMealImageUrl())
                .placeholder(R.drawable.bg_placeholder)
                .error(R.drawable.bg_placeholder)
                .into(holder.imageViewMealImage);
        holder.textView_MealName.setText(plannedMeal.getMealName());
        holder.imageButtonDeleteMealFromPlan.setOnClickListener(view -> {
            onDeletePlannedMealClickListener.onDeletePlannedMeal(plannedMeal);
        });
        Log.i(TAG, "onBindViewHolder: ");
    }


    @Override
    public int getItemCount() {
        return mealsAddedToPlan.size();
    }

    public void setMealsAddedToPlanList(List<PlannedMeal> mealsReceived){
        mealsAddedToPlan.clear();
        mealsAddedToPlan.addAll(mealsReceived);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public CardView cardViewWeeklyPlanMeals;
        public ImageView imageViewMealImage;
        public TextView textView_MealName;
        public ImageButton imageButtonDeleteMealFromPlan;
        public ConstraintLayout constraintLayoutWeeklyPlanMeals;
        public View layout;

        public ViewHolder(View view) {
            super(view);
            layout = view;
            cardViewWeeklyPlanMeals = view.findViewById(R.id.cardView_WeeklyPlanMeal);
            imageViewMealImage = view.findViewById(R.id.imageView_Meal_Image);
            imageButtonDeleteMealFromPlan = view.findViewById(R.id.imageButton_delete_meal_from_plan);
            textView_MealName = view.findViewById(R.id.textView_Meal_Name);
            constraintLayoutWeeklyPlanMeals = view.findViewById(R.id.constraintLayout_WeeklyPlanMeal);
        }
    }
}
