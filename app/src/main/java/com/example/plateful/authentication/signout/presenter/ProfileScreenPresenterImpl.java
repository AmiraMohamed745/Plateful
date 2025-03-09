package com.example.plateful.authentication.signout.presenter;

import android.util.Log;

import com.example.plateful.authentication.model.AuthenticationRepository;
import com.example.plateful.authentication.model.AuthenticationRepositoryImpl;
import com.example.plateful.authentication.model.GetUserCallback;
import com.example.plateful.authentication.model.User;
import com.example.plateful.authentication.model.UserRepository;
import com.example.plateful.authentication.model.UserRepositoryImpl;
import com.example.plateful.authentication.signout.view.ProfileScreenView;
import com.example.plateful.authentication.socialaccountsignin.model.GoogleSignInHelper;
import com.example.plateful.model.Meal;
import com.example.plateful.model.MealRepository;
import com.example.plateful.network.RXSchedulers;
import com.example.plateful.utils.UserSession;
import com.example.plateful.weeklyplan.model.PlannedMeal;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ProfileScreenPresenterImpl implements ProfileScreenPresenter {

    private static final String TAG = ProfileScreenPresenterImpl.class.getSimpleName();

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final ProfileScreenView profileScreenView;
    private final GoogleSignInHelper googleSignInHelper;
    private final AuthenticationRepository authenticationRepository;
    private final UserRepository userRepository;
    private final MealRepository mealRepository;


    public ProfileScreenPresenterImpl(ProfileScreenView profileScreenView, GoogleSignInHelper googleSignInHelper, MealRepository mealRepository) {
        this.profileScreenView = profileScreenView;
        this.googleSignInHelper = googleSignInHelper;
        this.authenticationRepository = new AuthenticationRepositoryImpl();
        this.userRepository = new UserRepositoryImpl();
        this.mealRepository = mealRepository;
    }


    @Override
    public void getCurrentUserData() {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            String uid = currentUser.getUid();
            userRepository.getUser(uid, new GetUserCallback() {
                @Override
                public void onSuccess(User user) {
                    profileScreenView.displayUserData(user);
                }

                @Override
                public void onFailure(String failureMessage) {
                    Log.i(TAG, "onFailure: Failed to get current user data" + failureMessage);
                }
            });
        } else {
            Log.i(TAG, "No user is currently signed in.");
        }
    }

    @Override
    public void backUpUserFavoriteAndPlannedMeals() {
        compositeDisposable.add(
                mealRepository.fetchStoredFavoriteMeals(UserSession.getCurrentUserId())
                        .doOnNext(favorites -> Log.d(TAG, "Favorite meals count: " + favorites.size()))
                        .firstOrError()
                        .flatMap(favoriteMeals ->
                                mealRepository.backUpFavoriteMeals(favoriteMeals)
                                        .andThen(Single.just(favoriteMeals))
                        )
                        .flatMap(ignored ->
                                mealRepository.fetchAllPlannedMeals(UserSession.getCurrentUserId()).firstOrError()
                        )
                        .flatMap(plannedMeals ->
                                mealRepository.backUpPlannedMeals(plannedMeals)
                                        .andThen(Single.just(plannedMeals))
                        )
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                result -> profileScreenView.onBackUpDataSuccess(),
                                error -> Log.e(TAG, "backUpUserFavoriteAndPlannedMeals: failed"+ error.getMessage(), error)
                        )
        );
    }

    @Override
    public void signOutUser() {
        authenticationRepository.signOutUser();
        if (googleSignInHelper != null) {
            googleSignInHelper.signOutGoogleAccount();
        }
        profileScreenView.onSignOut();
    }

    @Override
    public void cleanUpDisposables() {
        compositeDisposable.clear();
    }
}



