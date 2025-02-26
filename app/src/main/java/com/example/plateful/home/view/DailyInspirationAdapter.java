package com.example.plateful.home.view;

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
import com.bumptech.glide.request.RequestOptions;
import com.example.plateful.R;
import com.example.plateful.model.Meal;

import java.util.ArrayList;
import java.util.List;

public class DailyInspirationAdapter extends RecyclerView.Adapter<DailyInspirationAdapter.ViewHolder> {

    private static final String TAG = DailyInspirationAdapter.class.getSimpleName();

    private final Context context;
    private List<Meal> meals = new ArrayList<>();

    public DailyInspirationAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public DailyInspirationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup recyclerView, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(recyclerView.getContext());
        View view = inflater.inflate(R.layout.daily_inspiration_row, recyclerView, false);
        ViewHolder viewHolder = new ViewHolder(view);
        Log.i(TAG, "onCreateViewHolder: ");
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Meal meal = meals.get(position);
        Glide.with(context).load(meal.getImageUrl())
                .placeholder(R.drawable.bg_placeholder)
                .error(R.drawable.bg_placeholder)
                .into(holder.imageViewMealImage);
        holder.textView_MealName.setText(meal.getName());
        Log.i(TAG, "onBindViewHolder: ");
    }


    @Override
    public int getItemCount() {
        return meals.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public CardView cardViewDailyInspiration;
        public ImageView imageViewMealImage;
        public ImageButton imageButtonAddToFavorite;
        public TextView textView_MealName;
        public Button buttonAddToPlan;
        public ConstraintLayout constraintLayoutDailyInspiration;
        public View layout;

        public ViewHolder(View view) {
            super(view);
            layout = view;
            cardViewDailyInspiration = view.findViewById(R.id.cardView_DailyInspiration);
            imageViewMealImage = view.findViewById(R.id.imageView_Meal_Image);
            imageButtonAddToFavorite = view.findViewById(R.id.imageButton_add_to_favorite_icon);
            textView_MealName = view.findViewById(R.id.textView_Meal_Name);
            buttonAddToPlan = view.findViewById(R.id.button_Add_To_Plan);
            constraintLayoutDailyInspiration = view.findViewById(R.id.constraintLayout_DailyInspiration);
        }
    }

    public void populateDailyInspirationRecyclerView(List<Meal> mealsReceived){
        meals.clear();
        meals.addAll(mealsReceived);
        notifyDataSetChanged();
    }


}
