package com.example.plateful.search.ingredients.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import com.example.plateful.search.category.view.AllCategoryMealsAdapter;

import java.util.ArrayList;
import java.util.List;

public class AllIngredientsMealsAdapter extends RecyclerView.Adapter<AllIngredientsMealsAdapter.ViewHolder> {

    private static final String TAG = AllIngredientsMealsAdapter.class.getSimpleName();

    private final Context context;
    private final List<Meal> ingredientMeals = new ArrayList<>();

    public AllIngredientsMealsAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.category_meal_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Meal meal = ingredientMeals.get(position);
        Glide.with(context).load(meal.getImageUrl())
                .placeholder(R.drawable.bg_placeholder)
                .error(R.drawable.bg_placeholder)
                .into(holder.imageViewMealImage);
        holder.textView_MealName.setText(meal.getName());
        Log.i(TAG, "onBindViewHolder: ");
    }

    @Override
    public int getItemCount() {
        return ingredientMeals.size();
    }

    public void setIngredientMeals(List<Meal> mealsReceived) {
        ingredientMeals.clear();
        ingredientMeals.addAll(mealsReceived);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public CardView cardViewMeal;
        public ImageView imageViewMealImage;
        public ImageButton imageButtonAddToFavorite;
        public TextView textView_MealName;
        public Button buttonAddToPlan;
        public View layout;

        public ViewHolder(@NonNull View view) {
            super(view);
            layout = view;
            cardViewMeal = view.findViewById(R.id.cardView_CategoryMeal);
            imageViewMealImage = view.findViewById(R.id.imageView_Meal_Image);
            imageButtonAddToFavorite = view.findViewById(R.id.imageButton_add_to_favorite_icon);
            textView_MealName = view.findViewById(R.id.textView_Meal_Name);
            buttonAddToPlan = view.findViewById(R.id.button_Add_To_Plan);
        }

    }
}
