package com.example.plateful.home.cuisines.view;

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
import com.example.plateful.home.model.Cuisine;
import com.example.plateful.utils.DestinationNavigator;

import java.util.ArrayList;
import java.util.List;

public class ViewAllCuisinesAdapter extends RecyclerView.Adapter<ViewAllCuisinesAdapter.ViewHolder> {

    private static final String TAG = ViewAllCuisinesAdapter.class.getSimpleName();

    private final Context context;
    private final List<Cuisine> cuisines = new ArrayList<>();

    public ViewAllCuisinesAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.browse_cuisines_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Cuisine cuisine = cuisines.get(position);
        Glide.with(context).load(cuisine.getImageResId())
                .placeholder(R.drawable.bg_placeholder)
                .error(R.drawable.bg_placeholder)
                .into(holder.imageViewCuisineImage);
        holder.textView_CuisineName.setText(cuisine.getCuisineName());
        holder.cardViewBrowseCuisines.setOnClickListener(view -> {
            DestinationNavigator.navigateToAllCuisineMealsScreen(view, cuisine);
        });
        Log.i(TAG, "onBindViewHolder: ");
    }

    @Override
    public int getItemCount() {
        return cuisines.size();
    }

    public void setCuisinesList(List<Cuisine> cuisinesReceived) {
        cuisines.clear();
        cuisines.addAll(cuisinesReceived);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public CardView cardViewBrowseCuisines;
        public ImageView imageViewCuisineImage;
        public TextView textView_CuisineName;
        public ConstraintLayout constraintLayoutBrowseCuisines;
        public View layout;

        public ViewHolder(@NonNull View view) {
            super(view);
            layout = view;
            cardViewBrowseCuisines = view.findViewById(R.id.cardView_BrowseCuisines);
            imageViewCuisineImage = view.findViewById(R.id.imageView_CuisineImage);
            textView_CuisineName = view.findViewById(R.id.textView_Cuisine_Name);
            constraintLayoutBrowseCuisines = view.findViewById(R.id.constraintLayout_BrowseCuisines);
        }
    }
}
