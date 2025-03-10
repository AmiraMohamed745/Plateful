package com.example.plateful.home.view;

import static android.view.View.VISIBLE;

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
import com.example.plateful.model.SessionManager;
import com.example.plateful.utils.DestinationNavigator;

import java.util.ArrayList;
import java.util.List;

public class DailyInspirationAdapter extends RecyclerView.Adapter<DailyInspirationAdapter.ViewHolder> {

    private static final String TAG = DailyInspirationAdapter.class.getSimpleName();

    private final Context context;
    private final List<Meal> meals = new ArrayList<>();
    private OnAddToFavoriteClickListener onAddToFavoriteClickListener;

    public DailyInspirationAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup recyclerView, int viewType) {
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
        if(meal.isFavorite()){
            holder.imageButtonAddToFavorite.setImageResource(R.drawable.ic_filled_heart);
        } else {
            holder.imageButtonAddToFavorite.setImageResource(R.drawable.ic_outlined_heart);
        }
        holder.imageButtonAddToFavorite.setOnClickListener(view -> {
            SessionManager sessionManager = new SessionManager(context);
            if(sessionManager.isGuestMode()) {
                holder.textViewCannotAddToFavorite.setVisibility(View.VISIBLE);
                holder.imageButtonAddToFavorite.setVisibility(View.GONE);
            } else {
                view.setClickable(true);
                holder.textViewCannotAddToFavorite.setVisibility(View.GONE);
                onAddToFavoriteClickListener.onAddToFavoriteImageButtonClicked(meal);
            }

        });
        holder.textView_MealName.setOnClickListener(view -> {
            DestinationNavigator.navigateToMealDetailsScreen(view, meal);
        });
        Log.i(TAG, "onBindViewHolder: ");
    }

    public void updateMealFavoriteStatus(Meal meal, boolean isFavorite) {
        for (int i = 0; i < meals.size(); i++) {
            if (meals.get(i).getIdMeal().equals(meal.getIdMeal())) {
                meals.get(i).setFavorite(isFavorite);
                notifyItemChanged(i);
                break;
            }
        }
    }

    public void setOnAddToFavoriteClickListener(OnAddToFavoriteClickListener listener) {
        this.onAddToFavoriteClickListener = listener;
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
        public  TextView textViewCannotAddToFavorite;
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
            textViewCannotAddToFavorite = view.findViewById(R.id.textView_CannotAddToFavorite);
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
