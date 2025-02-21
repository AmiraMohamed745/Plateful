package com.example.plateful.authentication.signup.view;

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


public class WelcomeScreen extends Fragment {

    // For debugging
    private static final String TAG = WelcomeScreen.class.getSimpleName();

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
    }
}