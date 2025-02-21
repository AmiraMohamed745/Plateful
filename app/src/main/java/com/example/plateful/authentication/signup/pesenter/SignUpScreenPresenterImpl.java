package com.example.plateful.authentication.signup.pesenter;

import android.util.Log;

import com.example.plateful.authentication.model.AuthenticationCallBack;
import com.example.plateful.authentication.model.AuthenticationRepository;
import com.example.plateful.authentication.model.AuthenticationRepositoryImpl;
import com.example.plateful.authentication.model.SaveUserCallback;
import com.example.plateful.authentication.model.User;
import com.example.plateful.authentication.model.UserRepository;
import com.example.plateful.authentication.model.UserRepositoryImpl;
import com.example.plateful.authentication.signup.model.SignUpAuthenticationData;
import com.example.plateful.authentication.signup.view.SignUpScreenView;
import com.example.plateful.authentication.utils.InputValidator;
import com.example.plateful.authentication.utils.StringTrimmer;


public class SignUpScreenPresenterImpl implements SignUpScreenPresenter {

    // For debugging
    private static final String TAG = SignUpScreenPresenterImpl.class.getSimpleName();

    private SignUpScreenView signUpScreenView;
    private AuthenticationRepository authenticationRepository;
    private UserRepository userRepository;

    public SignUpScreenPresenterImpl(SignUpScreenView signUpScreenView) {
        this.signUpScreenView = signUpScreenView;
        this.authenticationRepository = new AuthenticationRepositoryImpl();
        this.userRepository = new UserRepositoryImpl();
    }

    private void trimData(SignUpAuthenticationData data) {
        data.setUsername(StringTrimmer.trimString(data.getUsername()));
        data.setEmail(StringTrimmer.trimString(data.getEmail()));
        data.setPassword(StringTrimmer.trimString(data.getPassword()));
        data.setConfirmedPassword(StringTrimmer.trimString(data.getConfirmedPassword()));
    }

    private boolean validateData(SignUpAuthenticationData data) {

        boolean isValid = true;

        if (!InputValidator.checkUsernameNotEmpty(data.getUsername())) {
            signUpScreenView.showError("Username is required");
            isValid = false;
        }

        if (!InputValidator.checkEmailNotEmpty(data.getEmail())) {
            signUpScreenView.showError("Email is required");
            isValid = false;
        }

        if (!InputValidator.checkPasswordNotEmpty(data.getPassword())) {
            signUpScreenView.showError("Password is required");
            isValid = false;
        }

        if (!InputValidator.checkConfirmedPasswordNotEmpty(data.getConfirmedPassword())) {
            signUpScreenView.showError("Confirm password is required");
            isValid = false;
        }

        return isValid;
    }

    // consider changing the name to "signUpWithAuthenticationProvider()"
    private void signUpUser(SignUpAuthenticationData data) {
        authenticationRepository.createUser(data.getEmail(), data.getPassword(), new AuthenticationCallBack() {
            @Override
            public void onSuccess(String userId) {
                saveUserData(userId, data);
            }

            @Override
            public void onFailure(String errorMessage) {
                Log.i(TAG, "onFailure: failed to sign up user");
            }
        });
    }

    private void saveUserData(String userId, SignUpAuthenticationData data) {
        User user = new User(userId, data.getUsername(), data.getEmail());
        userRepository.saveUser(user, new SaveUserCallback() {
            @Override
            public void onSuccess() {
                signUpScreenView.onUserDataSaved();
            }

            @Override
            public void onFailure(String errorMessage) {
                Log.i(TAG, "onFailure: failed to save user data");
            }
        });
    }

    // consider changing the name to "completeSignUpByValidatingData()"
    @Override
    public void signUp(SignUpAuthenticationData data) {
        trimData(data);
        if (!validateData(data)) {
            return;
        }
        signUpUser(data);
    }

}
