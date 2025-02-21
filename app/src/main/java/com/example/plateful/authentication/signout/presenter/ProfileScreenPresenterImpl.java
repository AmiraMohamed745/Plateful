package com.example.plateful.authentication.signout.presenter;

import android.util.Log;

import com.example.plateful.authentication.model.AuthenticationRepository;
import com.example.plateful.authentication.model.AuthenticationRepositoryImpl;
import com.example.plateful.authentication.model.GetUserCallback;
import com.example.plateful.authentication.model.User;
import com.example.plateful.authentication.model.UserRepository;
import com.example.plateful.authentication.model.UserRepositoryImpl;
import com.example.plateful.authentication.signout.view.ProfileScreenView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileScreenPresenterImpl implements ProfileScreenPresenter {

    private static final String TAG = ProfileScreenPresenterImpl.class.getSimpleName();

    private ProfileScreenView profileScreenView;
    private AuthenticationRepository authenticationRepository;
    private UserRepository userRepository;

    public ProfileScreenPresenterImpl(ProfileScreenView profileScreenView) {
        this.profileScreenView = profileScreenView;
        this.authenticationRepository = new AuthenticationRepositoryImpl();
        this.userRepository = new UserRepositoryImpl();
    }


    @Override
    public void getCurrentUserData() {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            String uid = currentUser.getUid();
            userRepository.getUser(uid, new GetUserCallback() {
                @Override
                public void onSuccess(User user) {
                    profileScreenView.displayUserData(user);
                }

                @Override
                public void onFailure(String failureMessage) {
                    Log.i(TAG, "onFailure: Failed to get current user data" + failureMessage);
                }
            });
        } else {
            Log.i(TAG, "No user is currently signed in.");
        }
    }

    @Override
    public void signOutUser() {
        authenticationRepository.signOutUser();
        profileScreenView.onSignOut();
    }
}



