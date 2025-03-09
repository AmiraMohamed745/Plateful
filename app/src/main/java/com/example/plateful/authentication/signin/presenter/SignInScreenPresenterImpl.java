package com.example.plateful.authentication.signin.presenter;

import android.util.Log;

import com.example.plateful.authentication.model.AuthenticationCallBack;
import com.example.plateful.authentication.model.AuthenticationRepository;
import com.example.plateful.authentication.model.AuthenticationRepositoryImpl;
import com.example.plateful.authentication.model.UserRepository;
import com.example.plateful.authentication.model.UserRepositoryImpl;
import com.example.plateful.authentication.signin.model.SignInAuthenticationData;
import com.example.plateful.authentication.signin.view.SignInScreenView;
import com.example.plateful.authentication.signup.model.SignUpAuthenticationData;
import com.example.plateful.authentication.utils.InputValidator;
import com.example.plateful.authentication.utils.StringTrimmer;
import com.example.plateful.model.Meal;
import com.example.plateful.model.MealRepository;
import com.example.plateful.network.RXSchedulers;
import com.example.plateful.utils.UserSession;
import com.example.plateful.weeklyplan.model.PlannedMeal;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class SignInScreenPresenterImpl implements SignInScreenPresenter {

    // For debugging
    private static final String TAG = SignInScreenPresenterImpl.class.getSimpleName();

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final SignInScreenView signinScreenView;
    private final AuthenticationRepository authenticationRepository;
    private final MealRepository mealRepository;

    public SignInScreenPresenterImpl(SignInScreenView signinScreenView, MealRepository mealRepository) {
        this.signinScreenView = signinScreenView;
        this.authenticationRepository = new AuthenticationRepositoryImpl();
        this.mealRepository = mealRepository;
    }

    private void trimData(SignInAuthenticationData data) {
        data.setEmail(StringTrimmer.trimString(data.getEmail()));
        data.setPassword(StringTrimmer.trimString(data.getPassword()));
    }

    private boolean validateData(SignInAuthenticationData data) {

        boolean isValid = true;

        if (!InputValidator.checkEmailNotEmpty(data.getEmail())) {
            signinScreenView.showError("Email is required");
            isValid = false;
        }

        if (!InputValidator.checkPasswordNotEmpty(data.getPassword())) {
            signinScreenView.showError("Password is required");
            isValid = false;
        }

        return isValid;
    }


    public void signInUser(SignInAuthenticationData data) {
        authenticationRepository.signInUser(data.getEmail(), data.getPassword(), new AuthenticationCallBack() {
            @Override
            public void onSuccess(String userId) {
                restoreUserBackUpOfFavoriteMeal();
                restoreUserBackUpOfPlannedMeal();
                signinScreenView.onUserSignedIn();
            }

            @Override
            public void onFailure(String errorMessage) {
                Log.i(TAG, "onFailure: failed to sign up user");
            }
        });
    }

    @Override
    public void signIn(SignInAuthenticationData data) {
        trimData(data);
        if (!validateData(data)) {
            return;
        }
        signInUser(data);
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
                                error -> signinScreenView.showError("Failed to restore favorite meals: " + error.getMessage())
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
                                    for (PlannedMeal plannedMeal : backedUpPlannedMeals) {
                                        plannedMeal.setUserId(UserSession.getCurrentUserId());
                                        mealRepository.insertPlannedMeal(plannedMeal)
                                                .subscribe();
                                    }
                                },
                                error -> signinScreenView.showError("Failed to restore planned meals: " + error.getMessage())
                        )
        );
    }

    @Override
    public void cleanUpDisposables() {
        compositeDisposable.clear();
    }

}
