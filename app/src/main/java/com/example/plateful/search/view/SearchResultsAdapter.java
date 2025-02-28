package com.example.plateful.search.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.plateful.R;
import com.example.plateful.model.Meal;

import java.util.ArrayList;
import java.util.List;

public class SearchResultsAdapter extends RecyclerView.Adapter<SearchResultsAdapter.ViewHolder> {

    // For debugging
    private static final String TAG = SearchResultsAdapter.class.getSimpleName();

    private final List<Meal> searchResultOutputList = new ArrayList<>();

    public SearchResultsAdapter(Context context) {}

    @NonNull
    @Override
    public SearchResultsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup recyclerView, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(recyclerView.getContext());
        View view = inflater.inflate(R.layout.search_result_row, recyclerView, false);
        ViewHolder viewHolder = new ViewHolder(view);
        Log.i(TAG, "onCreateViewHolder: Search result row in SearchResultsAdapter");
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView_MealName.setText(searchResultOutputList.get(position).getName());
        Log.i(TAG, "onBindViewHolder: Search result row in SearchResultsAdapter");
    }

    @Override
    public int getItemCount() {
        return searchResultOutputList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textView_MealName;
        public ImageView imageView_ic_Meal;
        public View divider_SearchResultsSeparator;
        public ConstraintLayout constraintLayout_SearchResultRow;
        public View searchResultLayout;

        public ViewHolder(View view) {
            super(view);
            searchResultLayout = view;
            textView_MealName = view.findViewById(R.id.textView_SearchResultOutput);
            imageView_ic_Meal = view.findViewById(R.id.imageView_ic_meal);
            divider_SearchResultsSeparator = view.findViewById(R.id.divider_SearchResultsSeparator);
            constraintLayout_SearchResultRow = view.findViewById(R.id.constraintLayout_searchResultRow);
        }
    }

    public void setSearchResultOutputList(List<Meal> meals) {
        searchResultOutputList.clear();
        searchResultOutputList.addAll(meals);
        notifyDataSetChanged();
    }


}
