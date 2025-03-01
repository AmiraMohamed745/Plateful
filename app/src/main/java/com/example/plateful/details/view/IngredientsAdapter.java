package com.example.plateful.details.view;

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

import com.bumptech.glide.Glide;
import com.example.plateful.R;
import com.example.plateful.details.model.Ingredient;
import com.example.plateful.model.Meal;

import java.util.ArrayList;
import java.util.List;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.ViewHolder> {

    private static final String TAG = IngredientsAdapter.class.getSimpleName();

    private final Context context;
    private List<Ingredient> ingredients = new ArrayList<>();

    public IngredientsAdapter(Context context) {
        this.context = context;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
        notifyDataSetChanged();
    }

    public List<Ingredient> extractMealIngredients(Meal meal) {
        List<Ingredient> ingredientList = new ArrayList<>();

        if (meal.getStrIngredient1() != null && !meal.getStrIngredient1().isEmpty()) {
            ingredientList.add(new Ingredient(meal.getStrIngredient1(), meal.getStrMeasure1()));
        }
        if (meal.getStrIngredient2() != null && !meal.getStrIngredient2().isEmpty()) {
            ingredientList.add(new Ingredient(meal.getStrIngredient2(), meal.getStrMeasure2()));
        }
        if (meal.getStrIngredient3() != null && !meal.getStrIngredient3().isEmpty()) {
            ingredientList.add(new Ingredient(meal.getStrIngredient3(), meal.getStrMeasure3()));
        }
        if (meal.getStrIngredient4() != null && !meal.getStrIngredient4().isEmpty()) {
            ingredientList.add(new Ingredient(meal.getStrIngredient4(), meal.getStrMeasure4()));
        }
        if (meal.getStrIngredient5() != null && !meal.getStrIngredient5().isEmpty()) {
            ingredientList.add(new Ingredient(meal.getStrIngredient5(), meal.getStrMeasure5()));
        }
        if (meal.getStrIngredient6() != null && !meal.getStrIngredient6().isEmpty()) {
            ingredientList.add(new Ingredient(meal.getStrIngredient6(), meal.getStrMeasure6()));
        }
        if (meal.getStrIngredient7() != null && !meal.getStrIngredient7().isEmpty()) {
            ingredientList.add(new Ingredient(meal.getStrIngredient7(), meal.getStrMeasure7()));
        }
        if (meal.getStrIngredient8() != null && !meal.getStrIngredient8().isEmpty()) {
            ingredientList.add(new Ingredient(meal.getStrIngredient8(), meal.getStrMeasure8()));
        }
        if (meal.getStrIngredient9() != null && !meal.getStrIngredient9().isEmpty()) {
            ingredientList.add(new Ingredient(meal.getStrIngredient9(), meal.getStrMeasure9()));
        }
        if (meal.getStrIngredient10() != null && !meal.getStrIngredient10().isEmpty()) {
            ingredientList.add(new Ingredient(meal.getStrIngredient10(), meal.getStrMeasure10()));
        }
        if (meal.getStrIngredient11() != null && !meal.getStrIngredient11().isEmpty()) {
            ingredientList.add(new Ingredient(meal.getStrIngredient11(), meal.getStrMeasure11()));
        }
        if (meal.getStrIngredient12() != null && !meal.getStrIngredient12().isEmpty()) {
            ingredientList.add(new Ingredient(meal.getStrIngredient12(), meal.getStrMeasure12()));
        }
        if (meal.getStrIngredient13() != null && !meal.getStrIngredient13().isEmpty()) {
            ingredientList.add(new Ingredient(meal.getStrIngredient13(), meal.getStrMeasure13()));
        }
        if (meal.getStrIngredient14() != null && !meal.getStrIngredient14().isEmpty()) {
            ingredientList.add(new Ingredient(meal.getStrIngredient14(), meal.getStrMeasure14()));
        }
        if (meal.getStrIngredient15() != null && !meal.getStrIngredient15().isEmpty()) {
            ingredientList.add(new Ingredient(meal.getStrIngredient15(), meal.getStrMeasure15()));
        }
        if (meal.getStrIngredient16() != null && !meal.getStrIngredient16().toString().isEmpty()) {
            ingredientList.add(new Ingredient(meal.getStrIngredient16().toString(), meal.getStrMeasure16() != null ? meal.getStrMeasure16().toString() : ""));
        }
        if (meal.getStrIngredient17() != null && !meal.getStrIngredient17().toString().isEmpty()) {
            ingredientList.add(new Ingredient(meal.getStrIngredient17().toString(), meal.getStrMeasure17() != null ? meal.getStrMeasure17().toString() : ""));
        }
        if (meal.getStrIngredient18() != null && !meal.getStrIngredient18().toString().isEmpty()) {
            ingredientList.add(new Ingredient(meal.getStrIngredient18().toString(), meal.getStrMeasure18() != null ? meal.getStrMeasure18().toString() : ""));
        }
        if (meal.getStrIngredient19() != null && !meal.getStrIngredient19().toString().isEmpty()) {
            ingredientList.add(new Ingredient(meal.getStrIngredient19().toString(), meal.getStrMeasure19() != null ? meal.getStrMeasure19().toString() : ""));
        }
        if (meal.getStrIngredient20() != null && !meal.getStrIngredient20().toString().isEmpty()) {
            ingredientList.add(new Ingredient(meal.getStrIngredient20().toString(), meal.getStrMeasure20() != null ? meal.getStrMeasure20().toString() : ""));
        }
        return ingredientList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup recyclerView, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(recyclerView.getContext());
        View view = inflater.inflate(R.layout.ingredients_row, recyclerView, false);
        ViewHolder viewHolder = new ViewHolder(view);
        Log.i(TAG, "onCreateViewHolder: ");
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Ingredient ingredient = ingredients.get(position);
        String imageUrl = "https://www.themealdb.com/images/ingredients/" + ingredient.getName() + ".png";
        Glide.with(context)
                .load(imageUrl)
                .placeholder(R.drawable.bg_placeholder)
                .error(R.drawable.bg_placeholder)
                .into(holder.imageViewIngredientImage);
        holder.textView_IngredientName.setText(ingredient.getName());
        holder.textView_IngredientMeasurement.setText(ingredient.getMeasurement());
        Log.i(TAG, "onBindViewHolder: ");
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageViewIngredientImage;
        public TextView textView_IngredientName;
        public TextView textView_IngredientMeasurement;
        public ConstraintLayout constraintLayoutIngredients;
        public View layout;

        public ViewHolder(View view) {
            super(view);
            layout = view;
            imageViewIngredientImage = view.findViewById(R.id.imageView_ingredient);
            textView_IngredientName = view.findViewById(R.id.textView_Ingredient_Name);
            textView_IngredientMeasurement = view.findViewById(R.id.textView_Ingredient_Measurement);
            constraintLayoutIngredients = view.findViewById(R.id.constraintLayout_Ingredients);
        }
    }

}
