package com.example.plateful.authentication.signout.model;

import com.example.plateful.model.Meal;
import com.example.plateful.utils.UserSession;
import com.example.plateful.weeklyplan.model.PlannedMeal;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

public class MealCloudDataSourceImpl implements MealCloudDataSource {

    private static final String USER_COLLECTION = "users";
    private static final String FAVORITE_MEAL_COLLECTION = "favorite_meals";
    private static final String PLANNED_MEAL_COLLECTION = "planned_meals";

    private final FirebaseFirestore firestore;


    public MealCloudDataSourceImpl() {
        firestore = FirebaseFirestore.getInstance();
    }

    private CollectionReference getFavoriteMealsReference() {
        return firestore.collection(USER_COLLECTION).document(UserSession.getCurrentUserId()).collection(FAVORITE_MEAL_COLLECTION);
    }

    private CollectionReference getPlannedMealsReference() {
        return firestore.collection(USER_COLLECTION).document(UserSession.getCurrentUserId()).collection(PLANNED_MEAL_COLLECTION);
    }


    @Override
    public Completable backUpFavoriteMeals(List<Meal> favoriteMeals) {
        List<Task<Void>> tasks = new ArrayList<>();
        for (Meal meal : favoriteMeals) {
            Task<Void> task = getFavoriteMealsReference().document(meal.getIdMeal()).set(meal);
            tasks.add(task);
        }
        return Completable.create(emitter -> {
            Tasks.whenAll(tasks).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    emitter.onComplete();
                } else {
                    emitter.onError(task.getException());
                }
            });
        });
    }


    @Override
    public Completable backUpPlannedMeals(List<PlannedMeal> plannedMeals) {
        List<Task<Void>> tasks = new ArrayList<>();
        for (PlannedMeal plannedMeal : plannedMeals) {
            Task<Void> task = getPlannedMealsReference().document(plannedMeal.getMealId()).set(plannedMeal);
            tasks.add(task);
        }
        return Completable.create(emitter -> {
            Tasks.whenAll(tasks).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    emitter.onComplete();
                } else {
                    emitter.onError(task.getException());
                }
            });
        });
    }

    @Override
    public Completable deleteFavoriteMealFromBackup(Meal favoriteMeal) {
        return Completable.create(emitter -> {
            getFavoriteMealsReference()
                    .document(favoriteMeal.getIdMeal())
                    .delete()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            emitter.onComplete();
                        } else {
                            emitter.onError(task.getException());
                        }
                    });
        });
    }

    @Override
    public Completable deletePlannedMealFromBackup(PlannedMeal plannedMeal) {
        return Completable.create(emitter -> {
            getPlannedMealsReference()
                    .document(plannedMeal.getMealId())
                    .delete()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            emitter.onComplete();
                        } else {
                            emitter.onError(task.getException());
                        }
                    });
        });
    }

    @Override
    public Single<List<Meal>> provideBackedUpFavoriteMeals() {
        return Single.create(emitter -> {
            getFavoriteMealsReference().get().addOnCompleteListener(task -> {
                if (task.isSuccessful() && task.getResult() != null) {
                    QuerySnapshot snapshot = task.getResult();
                    List<Meal> meals = new ArrayList<>();
                    for (DocumentSnapshot doc : snapshot.getDocuments()) {
                        Meal meal = doc.toObject(Meal.class);
                        if (meal != null) {
                            meals.add(meal);
                        }
                    }
                    emitter.onSuccess(meals);
                } else {
                    emitter.onError(task.getException());
                }
            });
        });
    }

    @Override
    public Single<List<PlannedMeal>> provideBackedUpPlannedMeals() {
        return Single.create(emitter -> {
            getPlannedMealsReference().get().addOnCompleteListener(task -> {
                if (task.isSuccessful() && task.getResult() != null) {
                    QuerySnapshot snapshot = task.getResult();
                    List<PlannedMeal> plannedMeals = new ArrayList<>();
                    for (DocumentSnapshot doc : snapshot.getDocuments()) {
                        PlannedMeal plannedMeal = doc.toObject(PlannedMeal.class);
                        if (plannedMeal != null) {
                            plannedMeals.add(plannedMeal);
                        }
                    }
                    emitter.onSuccess(plannedMeals);
                } else {
                    emitter.onError(task.getException());
                }
            });
        });
    }
}

