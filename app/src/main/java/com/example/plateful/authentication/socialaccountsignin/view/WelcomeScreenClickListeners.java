package com.example.plateful.authentication.socialaccountsignin.view;

public interface WelcomeScreenClickListeners {
    void onSignUpWithEmail();
    void onSignInAsGuest();
    void onSuccessfulGoogleSignIn(); // this navigates to home screen
    // void onSuccessfulFacebookSignIn(); // not implemented
    void onAlreadyHaveAnAccountLogIn();
}
