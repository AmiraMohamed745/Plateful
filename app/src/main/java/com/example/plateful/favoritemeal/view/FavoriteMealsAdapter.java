package com.example.plateful.favoritemeal.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.plateful.R;
import com.example.plateful.model.Meal;
import com.example.plateful.view.DestinationNavigator;

import java.util.ArrayList;
import java.util.List;

public class FavoriteMealsAdapter extends RecyclerView.Adapter<FavoriteMealsAdapter.ViewHolder> {

    private static final String TAG = FavoriteMealsAdapter.class.getSimpleName();

    private final Context context;
    private final List<Meal> favoriteMeals = new ArrayList<>();

    public FavoriteMealsAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup recyclerView, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(recyclerView.getContext());
        View view = inflater.inflate(R.layout.favorite_meal_row, recyclerView, false);
        ViewHolder viewHolder = new ViewHolder(view);
        Log.i(TAG, "onCreateViewHolder: ");
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Meal meal = favoriteMeals.get(position);
        Glide.with(context).load(meal.getImageUrl())
                .placeholder(R.drawable.bg_placeholder)
                .error(R.drawable.bg_placeholder)
                .into(holder.imageViewMealImage);
        holder.textView_MealName.setText(meal.getName());
        holder.cardViewFavoriteMeal.setOnClickListener(view -> {
            DestinationNavigator.navigateToMealDetailsScreen(view, meal);
        });
        Log.i(TAG, "onBindViewHolder: ");
    }


    @Override
    public int getItemCount() {
        return favoriteMeals.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public CardView cardViewFavoriteMeal;
        public ImageView imageViewMealImage;
        public TextView textView_MealName;
        public ConstraintLayout constraintLayoutFavoriteMeal;
        public View layout;

        public ViewHolder(View view) {
            super(view);
            layout = view;
            cardViewFavoriteMeal = view.findViewById(R.id.cardView_FavoriteMeal);
            imageViewMealImage = view.findViewById(R.id.imageView_Meal_Image);
            textView_MealName = view.findViewById(R.id.textView_Meal_Name);
            constraintLayoutFavoriteMeal = view.findViewById(R.id.constraintLayout_FavoriteMeal);
        }
    }

    public Meal getMealAtPosition(int position) {
        return favoriteMeals.get(position);
    }

    public Meal removeMealAtPosition(int position) {
        Meal removedMeal = favoriteMeals.remove(position);
        notifyItemRemoved(position);
        return removedMeal;
    }

    public void addMealAtPosition(Meal meal, int position) {
        favoriteMeals.add(position, meal);
        notifyItemInserted(position);
    }

    public void setFavoriteMealsList(List<Meal> mealsReceived){
        favoriteMeals.clear();
        favoriteMeals.addAll(mealsReceived);
        notifyDataSetChanged();
    }

}
