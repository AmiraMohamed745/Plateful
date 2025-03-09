package com.example.plateful.authentication.socialaccountsignin.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.plateful.R;
import com.example.plateful.authentication.signout.model.MealCloudDataSourceImpl;
import com.example.plateful.authentication.socialaccountsignin.model.GoogleSignInHelper;
import com.example.plateful.authentication.socialaccountsignin.presenter.WelcomeScreenPresenter;
import com.example.plateful.authentication.socialaccountsignin.presenter.WelcomeScreenPresenterImpl;
import com.example.plateful.database.MealLocalDataSourceImpl;
import com.example.plateful.model.MealRepositoryImpl;
import com.example.plateful.model.SessionManager;
import com.example.plateful.network.MealRemoteDataSourceImpl;
import com.example.plateful.utils.DestinationNavigator;


public class WelcomeScreen extends Fragment implements WelcomeScreenClickListeners, WelcomeScreenView {

    // For debugging
    private static final String TAG = WelcomeScreen.class.getSimpleName();

    private static final int RC_SIGN_IN = 100;
    private WelcomeScreenPresenter presenter;

    // Views
    private ImageView bgWelcome;

    private Button buttonSignInAsGuest;
    private Button buttonSignUpWithEmail;
    private Button buttonContinueWithGoogle;
    private Button buttonContinueWithFacebook;

    private TextView textViewOrUseSocialMediaSignUp;
    private TextView textViewAlreadyHaveAnAccount;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_welcome_screen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bgWelcome = view.findViewById(R.id.bg_welcome);

        buttonSignInAsGuest = view.findViewById(R.id.button_sing_in_as_guest);
        buttonSignUpWithEmail = view.findViewById(R.id.button_sign_up_with_email);
        buttonContinueWithGoogle = view.findViewById(R.id.button_continue_with_Goggle);
        buttonContinueWithFacebook = view.findViewById(R.id.button_continue_with_Facebook);

        textViewOrUseSocialMediaSignUp = view.findViewById(R.id.textView_or_use_social_media_sign_up);
        textViewAlreadyHaveAnAccount = view.findViewById(R.id.textView_already_have_an_account);

        String webClientId = "611080036624-fbcvsud6if4ejq93mp0438ob9u74upfd.apps.googleusercontent.com";
        GoogleSignInHelper googleSignInHelper = new GoogleSignInHelper(requireContext(), webClientId);
        presenter = new WelcomeScreenPresenterImpl(
                this,
                this,
                googleSignInHelper,
                MealRepositoryImpl.getInstance(
                        new MealRemoteDataSourceImpl(requireContext()),
                        MealLocalDataSourceImpl.getInstance(requireContext()),
                        new MealCloudDataSourceImpl())
        );

        buttonContinueWithGoogle.setOnClickListener(v -> {
            presenter.onGoogleSignInButtonClicked();
            SessionManager sessionManager = new SessionManager(requireContext());
            sessionManager.setGuestMode(false);
        });
        buttonSignUpWithEmail.setOnClickListener(onButtonSignUpWithEmail -> onSignUpWithEmail());
        textViewAlreadyHaveAnAccount.setOnClickListener(onLoginText -> onAlreadyHaveAnAccountLogIn());
        buttonSignInAsGuest.setOnClickListener(view1 -> {
            presenter.onSignInAsGuestButtonClicked(requireContext());
            DestinationNavigator.navigateToHomeScreen(requireView());
        });
    }

    @Override
    public void launchGoogleSignInIntent(Intent signInIntent) {
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            presenter.handleGoogleSignInResult(data);
        }
    }

    @Override
    public void showError(String errorMessage) {
        // Not handled: no actual errors are shown on the UI
    }

    @Override
    public void onSignUpWithEmail() {
        DestinationNavigator.navigateToSignUpScreen(requireView());
    }


    @Override
    public void onSuccessfulGoogleSignIn() {
        DestinationNavigator.navigateToHomeScreen(requireView());
    }

    @Override
    public void onAlreadyHaveAnAccountLogIn() {
        DestinationNavigator.navigateToSignInScreen(requireView());
    }

    @Override
    public void onDestroyView() {
        presenter.cleanUpDisposables();
        super.onDestroyView();
    }

}