package com.example.plateful.authentication.socialaccountsignin.view;

import android.content.Intent;

public interface WelcomeScreenView {
    void launchGoogleSignInIntent(Intent signInIntent);
    void showError(String errorMessage);
}
