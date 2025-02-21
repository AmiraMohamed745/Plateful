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


public class SignInScreenPresenterImpl implements SignInScreenPresenter {

    // For debugging
    private static final String TAG = SignInScreenPresenterImpl.class.getSimpleName();

    private SignInScreenView signinScreenView;
    private AuthenticationRepository authenticationRepository;

    public SignInScreenPresenterImpl(SignInScreenView signinScreenView) {
        this.signinScreenView = signinScreenView;
        this.authenticationRepository = new AuthenticationRepositoryImpl();
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

}
