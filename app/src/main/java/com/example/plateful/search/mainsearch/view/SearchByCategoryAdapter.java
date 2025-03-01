package com.example.plateful.search.mainsearch.view;

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

import java.util.ArrayList;
import java.util.List;

public class SearchByCategoryAdapter extends RecyclerView.Adapter<SearchByCategoryAdapter.ViewHolder> {

    private static final String TAG = SearchByCategoryAdapter.class.getSimpleName();

    private final Context context;
    private List<Category> categories = new ArrayList<>();

    public SearchByCategoryAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.search_by_category_row, parent, false);
        Log.i(TAG, "onCreateViewHolder: ");
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
        Log.i(TAG, "onBindViewHolder: ");
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public void setCategories(List<Category> categoriesReceived) {
        categories.clear();
        if (categoriesReceived.size() > 5) {
            categories.addAll(categoriesReceived.subList(0, 5));
        } else {
            categories.addAll(categoriesReceived);
        }
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
            cardView = view.findViewById(R.id.cardView_SearchByCategory);
            imageViewCategory = view.findViewById(R.id.imageView_CategoryImage);
            textViewCategoryName = view.findViewById(R.id.textView_Category_Name);
            constraintLayout = view.findViewById(R.id.constraintLayout_SearchByCategory);
        }
    }
}
