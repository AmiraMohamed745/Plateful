package com.example.plateful.authentication.signup.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.plateful.R;
import com.example.plateful.authentication.signup.model.SignUpAuthenticationData;
import com.example.plateful.authentication.signup.pesenter.SignUpScreenPresenter;
import com.example.plateful.authentication.signup.pesenter.SignUpScreenPresenterImpl;
import com.example.plateful.authentication.utils.EditableToStringConverter;
import com.example.plateful.model.SessionManager;
import com.example.plateful.utils.DestinationNavigator;
import com.google.android.material.textfield.TextInputEditText;


public class SignUpScreen extends Fragment implements SignUpScreenView {

    // For debugging
    private static final String TAG = SignUpScreen.class.getSimpleName();

    private SignUpScreenPresenter signUpScreenPresenter;

    // Views
    private TextView textViewSignUp;
    private TextView textViewSignUp_Subtitle;

    private TextInputEditText editTextUsername;
    private TextInputEditText editTextEmail;
    private TextInputEditText editTextPassword;
    private TextInputEditText editTextConfirmPassword;

    private Button buttonSignUp;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up_screen, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Finding views by id
        textViewSignUp = view.findViewById(R.id.textView_Signup);
        textViewSignUp_Subtitle = view.findViewById(R.id.textView_Signup_Subtext);

        editTextUsername = view.findViewById(R.id.textInputEditText_Username);
        editTextEmail = view.findViewById(R.id.textInputEditText_Email);
        editTextPassword = view.findViewById(R.id.textInputEditText_Password);
        editTextConfirmPassword = view.findViewById(R.id.textInputEditText_ConfirmPassword);

        buttonSignUp = view.findViewById(R.id.button_signup);

        signUpScreenPresenter = new SignUpScreenPresenterImpl(this);

        buttonSignUp.setOnClickListener(onSignUpButtonClicked -> {
            signUpScreenPresenter.signUp(extractUserData());
            SessionManager sessionManager = new SessionManager(requireContext());
            sessionManager.setGuestMode(false);
        });
    }

    @Override
    public void showError(String error) {
        switch (error) {
            case "Username is required":
                editTextUsername.setError(getString(R.string.username_is_required));
                editTextUsername.requestFocus();
                break;
            case "Email is required":
                editTextEmail.setError(getString(R.string.email_is_required));
                break;
            case "Password is required":
                editTextPassword.setError(getString(R.string.password_is_required));
                break;
            default: // is it correct to leave the last check to default or should I add a case for it and put something else in the default?
                editTextConfirmPassword.setError(getString(R.string.confirm_password_is_required));
                break;
        }
    }

    @Override
    public void onUserDataSaved() {
        DestinationNavigator.navigateToHomeScreen(requireView());
    }

    private SignUpAuthenticationData extractUserData() {
        SignUpAuthenticationData data = new SignUpAuthenticationData(
                EditableToStringConverter.convertEditableToString(editTextUsername.getText()),
                EditableToStringConverter.convertEditableToString(editTextEmail.getText()),
                EditableToStringConverter.convertEditableToString(editTextPassword.getText()),
                EditableToStringConverter.convertEditableToString(editTextConfirmPassword.getText())
        );
        return data;
    }

}