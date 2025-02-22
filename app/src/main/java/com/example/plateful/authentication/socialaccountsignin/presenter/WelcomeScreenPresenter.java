package com.example.plateful.authentication.socialaccountsignin.presenter;

import android.content.Intent;

public interface WelcomeScreenPresenter {
    void onGoogleSignInButtonClicked();
    void handleGoogleSignInResult(Intent data);
}
