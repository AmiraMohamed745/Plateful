package com.example.plateful.authentication.signin.view;

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
import com.example.plateful.authentication.signin.model.SignInAuthenticationData;
import com.example.plateful.authentication.signin.presenter.SignInScreenPresenter;
import com.example.plateful.authentication.signin.presenter.SignInScreenPresenterImpl;
import com.example.plateful.authentication.signout.model.MealCloudDataSourceImpl;
import com.example.plateful.authentication.utils.EditableToStringConverter;
import com.example.plateful.database.MealLocalDataSourceImpl;
import com.example.plateful.model.MealRepositoryImpl;
import com.example.plateful.model.SessionManager;
import com.example.plateful.network.MealRemoteDataSourceImpl;
import com.example.plateful.utils.DestinationNavigator;
import com.google.android.material.textfield.TextInputEditText;


public class SignInScreen extends Fragment implements SignInScreenView{

    // For debugging
    private static final String TAG = SignInScreen.class.getSimpleName();

    private SignInScreenPresenter signInScreenPresenter;

    // Views
    private TextView textViewSignIn;
    private TextView textViewSignIn_Subtitle;

    private TextInputEditText editTextEmail;
    private TextInputEditText editTextPassword;

    private Button buttonSignIn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_in_screen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textViewSignIn = view.findViewById(R.id.textView_SignIn);
        textViewSignIn_Subtitle = view.findViewById(R.id.textView_SignIn_Subtext);

        editTextEmail = view.findViewById(R.id.textInputEditText_Email);
        editTextPassword = view.findViewById(R.id.textInputEditText_Password);

        buttonSignIn = view.findViewById(R.id.button_signIn);

        signInScreenPresenter = new SignInScreenPresenterImpl(
                this,
                MealRepositoryImpl.getInstance(
                        new MealRemoteDataSourceImpl(requireContext()),
                        MealLocalDataSourceImpl.getInstance(requireContext()),
                        new MealCloudDataSourceImpl()
                ));

        buttonSignIn.setOnClickListener(onSignUpButtonClicked -> {
            signInScreenPresenter.signIn(extractUserData());
            SessionManager sessionManager = new SessionManager(requireContext());
            sessionManager.setGuestMode(false);
        });
    }

    @Override
    public void showError(String error) {
        if (error.equals("Email is required")) {
            editTextEmail.setError(getString(R.string.email_is_required));
        } else {
            editTextPassword.setError(getString(R.string.password_is_required));
        }
    }

    @Override
    public void onUserSignedIn() {
        DestinationNavigator.navigateToHomeScreen(requireView());
    }

    private SignInAuthenticationData extractUserData() {
        SignInAuthenticationData data = new SignInAuthenticationData(
                EditableToStringConverter.convertEditableToString(editTextEmail.getText()),
                EditableToStringConverter.convertEditableToString(editTextPassword.getText())
        );
        return data;
    }

    @Override
    public void onDestroyView() {
        signInScreenPresenter.cleanUpDisposables();
        super.onDestroyView();
    }
}