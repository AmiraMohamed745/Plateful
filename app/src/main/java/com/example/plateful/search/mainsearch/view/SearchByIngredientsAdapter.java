package com.example.plateful.search.mainsearch.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.plateful.R;
import com.example.plateful.search.ingredients.model.Ingredient;
import java.util.ArrayList;
import java.util.List;

public class SearchByIngredientsAdapter extends RecyclerView.Adapter<SearchByIngredientsAdapter.ViewHolder>{

    private final Context context;
    private final List<Ingredient> ingredients = new ArrayList<>();

    public SearchByIngredientsAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.ingredient_row_on_search, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Ingredient ingredient = ingredients.get(position);
        String ingredientImageUrl = "https://www.themealdb.com/images/ingredients/" + ingredient.getName() + ".png";
        Glide.with(context).load(ingredientImageUrl)
                .placeholder(R.drawable.bg_placeholder)
                .error(R.drawable.bg_placeholder)
                .into(holder.imageViewIngredientImage);
        holder.textViewIngredientName.setText(ingredient.getName());
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    public void setIngredientsList(List<Ingredient> ingredientsReceived) {
        ingredients.clear();
        if (ingredientsReceived.size() > 7) {
            ingredients.addAll(ingredientsReceived.subList(0, 6));
        } else {
            ingredients.addAll(ingredientsReceived);
        }
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public CardView cardView;
        public ImageView imageViewIngredientImage;
        public TextView textViewIngredientName;
        public View layout;

        public ViewHolder(View view) {
            super(view);
            layout = view;
            cardView = view.findViewById(R.id.cardView_Ingredient);
            imageViewIngredientImage = view.findViewById(R.id.imageView_IngredientImage);
            textViewIngredientName = view.findViewById(R.id.textView_Ingredient_Name);
        }
    }
}

