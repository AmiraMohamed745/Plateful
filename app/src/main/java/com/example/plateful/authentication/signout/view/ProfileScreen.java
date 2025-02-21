package com.example.plateful.authentication.signout.view;

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
import com.example.plateful.authentication.model.User;
import com.example.plateful.authentication.signout.presenter.ProfileScreenPresenter;
import com.example.plateful.authentication.signout.presenter.ProfileScreenPresenterImpl;
import com.example.plateful.view.DestinationNavigator;


public class ProfileScreen extends Fragment implements ProfileScreenView{

    // For debugging
    private static final String TAG = ProfileScreen.class.getSimpleName();

    private ProfileScreenPresenter profileScreenPresenter;

    private TextView textView_Username;
    private TextView textView_Email;

    private Button buttonSignOut;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile_screen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textView_Username = view.findViewById(R.id.textView_username);
        textView_Email = view.findViewById(R.id.textView_email);

        buttonSignOut = view.findViewById(R.id.button_sign_out);

        profileScreenPresenter = new ProfileScreenPresenterImpl(this);
        profileScreenPresenter.getCurrentUserData();

        buttonSignOut.setOnClickListener(onSignOutButtonClicked -> {
            profileScreenPresenter.signOutUser();
        });
    }

    @Override
    public void onSignOut() {
        DestinationNavigator.navigateToWelcomeScreen(requireView());
    }

    @Override
    public void displayUserData(User user) {
        textView_Username.setText(user.getUsername());
        textView_Email.setText(user.getEmail());
    }

}

