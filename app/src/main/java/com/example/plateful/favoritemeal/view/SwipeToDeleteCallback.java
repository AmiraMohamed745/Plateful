package com.example.plateful.favoritemeal.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.plateful.R;
import com.example.plateful.favoritemeal.presenter.FavoriteMealsScreenPresenter;
import com.example.plateful.model.Meal;
import com.google.android.material.snackbar.Snackbar;

public class SwipeToDeleteCallback extends ItemTouchHelper.SimpleCallback {

    private final Context context;
    private final FavoriteMealsAdapter favoriteMealsAdapter;
    private final FavoriteMealsScreenPresenter favoriteMealsScreenPresenter;
    private final View view;

    public SwipeToDeleteCallback(Context context, View view, FavoriteMealsAdapter favoriteMealsAdapter,
                                 FavoriteMealsScreenPresenter favoriteMealsScreenPresenter) {
        super(0, ItemTouchHelper.LEFT);
        this.context = context;
        this.view = view;
        this.favoriteMealsAdapter = favoriteMealsAdapter;
        this.favoriteMealsScreenPresenter = favoriteMealsScreenPresenter;

    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView,
                          @NonNull RecyclerView.ViewHolder viewHolder,
                          @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        int position = viewHolder.getAdapterPosition();
        Meal swipedMeal = favoriteMealsAdapter.getMealAtPosition(position);
        favoriteMealsAdapter.removeMealAtPosition(position);
        favoriteMealsScreenPresenter.deleteFavoriteMeal(swipedMeal);
        showSnackBarToUndoMealDeletion(view, swipedMeal, position);
    }

    @Override
    public void onChildDraw(@NonNull Canvas c,
                            @NonNull RecyclerView recyclerView,
                            @NonNull RecyclerView.ViewHolder viewHolder,
                            float dX, float dY,
                            int actionState,
                            boolean isCurrentlyActive) {


        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);

        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE && dX < 0) {
            Paint paint = new Paint();
            paint.setColor(ContextCompat.getColor(context, R.color.red));

            float itemViewTop = viewHolder.itemView.getTop();
            float itemViewBottom = viewHolder.itemView.getBottom();
            float itemViewRight = viewHolder.itemView.getRight();
            float itemViewLeft = itemViewRight + dX;
            c.drawRect(itemViewLeft, itemViewTop, itemViewRight, itemViewBottom, paint);

            Drawable deleteIcon = ContextCompat.getDrawable(context, R.drawable.ic_delete);
            if (deleteIcon != null) {
                int iconMargin = (int) (16 * context.getResources().getDisplayMetrics().density);
                int iconWidth = deleteIcon.getIntrinsicWidth();
                int iconHeight = deleteIcon.getIntrinsicHeight();
                int iconTop = (int) (itemViewTop + (float) (viewHolder.itemView.getHeight() - iconHeight) / 2);
                int iconBottom = iconTop + iconHeight;
                int iconRight = (int) (itemViewRight - iconMargin);
                int iconLeft = iconRight - iconWidth;
                deleteIcon.setBounds(iconLeft, iconTop, iconRight, iconBottom);
                deleteIcon.draw(c);
            }
        }
    }

    private void showSnackBarToUndoMealDeletion(View view, Meal meal, int mealPosition) {
        Snackbar snackbar = Snackbar.make(view, R.string.meal_deleted, Snackbar.LENGTH_LONG);
        snackbar.setActionTextColor(ContextCompat.getColor(context, R.color.primaryColor));
        snackbar.setAction(R.string.undo, v -> {
            favoriteMealsAdapter.addMealAtPosition(meal, mealPosition);
            favoriteMealsScreenPresenter.reInsertFavoriteMeal(meal);
        });
        snackbar.show();
    }

}
