package com.example.plateful.authentication.signin.presenter;

import com.example.plateful.authentication.signin.model.SignInAuthenticationData;
import com.example.plateful.authentication.signup.model.SignUpAuthenticationData;

public interface SignInScreenPresenter {
    void signIn(SignInAuthenticationData data);
}
