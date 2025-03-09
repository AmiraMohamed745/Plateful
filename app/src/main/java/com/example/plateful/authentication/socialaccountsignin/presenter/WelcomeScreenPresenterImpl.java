package com.example.plateful.authentication.socialaccountsignin.presenter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.plateful.authentication.model.AuthenticationCallBack;
import com.example.plateful.authentication.model.AuthenticationRepository;
import com.example.plateful.authentication.model.AuthenticationRepositoryImpl;
import com.example.plateful.authentication.model.SaveUserCallback;
import com.example.plateful.authentication.model.User;
import com.example.plateful.authentication.model.UserRepository;
import com.example.plateful.authentication.model.UserRepositoryImpl;
import com.example.plateful.authentication.socialaccountsignin.model.GoogleSignInHelper;
import com.example.plateful.authentication.socialaccountsignin.view.WelcomeScreenClickListeners;
import com.example.plateful.authentication.socialaccountsignin.view.WelcomeScreenView;
import com.example.plateful.model.Meal;
import com.example.plateful.model.MealRepository;
import com.example.plateful.model.SessionManager;
import com.example.plateful.network.RXSchedulers;
import com.example.plateful.utils.UserSession;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class WelcomeScreenPresenterImpl implements WelcomeScreenPresenter{

    // For debugging
    private static final String TAG = WelcomeScreenPresenterImpl.class.getSimpleName();

    private final WelcomeScreenView welcomeScreenView;
    private final WelcomeScreenClickListeners welcomeScreenClickListeners;
    private final AuthenticationRepository authenticationRepository;
    private final UserRepository userRepository;
    private final GoogleSignInHelper googleSignInHelper;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final MealRepository mealRepository;


    public WelcomeScreenPresenterImpl(WelcomeScreenView welcomeScreenView, WelcomeScreenClickListeners welcomeScreenClickListeners, GoogleSignInHelper googleSignInHelper, MealRepository mealRepository) {
        this.welcomeScreenView = welcomeScreenView;
        this.welcomeScreenClickListeners = welcomeScreenClickListeners;
        this.googleSignInHelper = googleSignInHelper;
        this.authenticationRepository = new AuthenticationRepositoryImpl();
        userRepository = new UserRepositoryImpl();
        this.mealRepository = mealRepository;
    }

    @Override
    public void onGoogleSignInButtonClicked() {
        Intent signInIntent = googleSignInHelper.getSignInIntent();
        welcomeScreenView.launchGoogleSignInIntent(signInIntent);
    }

    @Override
    public void onSignInAsGuestButtonClicked(Context context) {
        SessionManager sessionManager = new SessionManager(context);
        sessionManager.setGuestMode(true);
    }

    @Override
    public void handleGoogleSignInResult(Intent data) {
        try {
            GoogleSignInAccount account = googleSignInHelper.getSignedInAccountFromIntent(data);
            if (account != null) {
                authenticationRepository.signInWithGoogle(account.getIdToken(), new AuthenticationCallBack() {
                    @Override
                    public void onSuccess(String userId) {
                        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                        if (firebaseUser != null) {
                            User user = new User(firebaseUser.getUid(),
                                    firebaseUser.getDisplayName(),
                                    firebaseUser.getEmail());
                            userRepository.saveUser(user, new SaveUserCallback() {
                                @Override
                                public void onSuccess() {
                                    restoreUserBackUpOfFavoriteMeal();
                                    restoreUserBackUpOfPlannedMeal();
                                    welcomeScreenClickListeners.onSuccessfulGoogleSignIn();
                                }

                                @Override
                                public void onFailure(String failureMessage) {
                                    welcomeScreenView.showError("Failed to save user data: " + failureMessage);
                                }
                            });
                        } else {
                            welcomeScreenView.showError("Firebase user is null after sign in.");
                        }
                    }
                    @Override
                    public void onFailure(String failureMessage) {
                        welcomeScreenView.showError(failureMessage);
                    }
                });
            }
        } catch (ApiException e) {
            Log.e(TAG, "Google sign in failed", e);
            welcomeScreenView.showError("Google sign-in failed: " + e.getMessage());
        }
    }

    @Override
    public void restoreUserBackUpOfFavoriteMeal() {
        compositeDisposable.add(
                mealRepository.provideBackedUpFavoriteMeals()
                        .compose(RXSchedulers.applySchedulersSingle())
                        .subscribe(
                                backedUpMeals -> {
                                    for (Meal meal : backedUpMeals) {
                                        meal.setUserId(UserSession.getCurrentUserId());
                                        mealRepository.insertMeal(meal)
                                                .subscribe();
                                    }
                                },
                                error -> welcomeScreenView.showError("Failed to restore favorite meals: " + error.getMessage())
                        )
        );
    }

    @Override
    public void restoreUserBackUpOfPlannedMeal() {
        compositeDisposable.add(
                mealRepository.provideBackedUpPlannedMeals()
                        .compose(RXSchedulers.applySchedulersSingle())
                        .subscribe(
                                backedUpPlannedMeals -> {
                                    for (com.example.plateful.weeklyplan.model.PlannedMeal plannedMeal : backedUpPlannedMeals) {
                                        plannedMeal.setUserId(UserSession.getCurrentUserId());
                                        mealRepository.insertPlannedMeal(plannedMeal)
                                                .subscribe();
                                    }
                                },
                                error -> welcomeScreenView.showError("Failed to restore planned meals: " + error.getMessage())
                        )
        );
    }

    @Override
    public void cleanUpDisposables() {
        compositeDisposable.clear();
    }
}
