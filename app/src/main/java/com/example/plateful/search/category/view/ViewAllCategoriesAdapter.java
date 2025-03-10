package com.example.plateful.search.category.view;

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
import com.example.plateful.search.category.model.Category;
import com.example.plateful.utils.DestinationNavigator;

import java.util.ArrayList;
import java.util.List;

public class ViewAllCategoriesAdapter extends RecyclerView.Adapter<ViewAllCategoriesAdapter.ViewHolder> {

    private static final String TAG = ViewAllCategoriesAdapter.class.getSimpleName();

    private final Context context;
    private final List<Category> categories = new ArrayList<>();

    public ViewAllCategoriesAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewAllCategoriesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.view_all_categories_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Category category = categories.get(position);
        holder.textViewCategoryName.setText(category.getCategoryName());
        Glide.with(context)
                .load(category.getCategoryImageUrl())
                .placeholder(R.drawable.bg_placeholder)
                .error(R.drawable.bg_placeholder)
                .into(holder.imageViewCategory);
        holder.cardView.setOnClickListener(view -> {
            DestinationNavigator.navigateToAllCategoryMealsScreen(view, category);
        });
        Log.i(TAG, "onBindViewHolder: ");
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public void setCategories(List<Category> categoriesReceived) {
        categories.clear();
        categories.addAll(categoriesReceived);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public CardView cardView;
        public ImageView imageViewCategory;
        public TextView textViewCategoryName;
        public ConstraintLayout constraintLayout;
        public View layout;

        public ViewHolder(@NonNull View view) {
            super(view);
            layout = view;
            cardView = itemView.findViewById(R.id.cardView_ViewAllCategories);
            imageViewCategory = itemView.findViewById(R.id.imageView_CategoryImage);
            textViewCategoryName = itemView.findViewById(R.id.textView_Category_Name);
            constraintLayout = itemView.findViewById(R.id.constraintLayout_ViewAllCategories);
        }
    }
}
