package com.example.plateful.authentication.socialaccountsignin.presenter;

import android.content.Context;
import android.content.Intent;

public interface WelcomeScreenPresenter {
    void onGoogleSignInButtonClicked();
    void restoreUserBackUpOfFavoriteMeal();
    void restoreUserBackUpOfPlannedMeal();
    void onSignInAsGuestButtonClicked(Context context);
    void handleGoogleSignInResult(Intent data);
    void cleanUpDisposables();
}
