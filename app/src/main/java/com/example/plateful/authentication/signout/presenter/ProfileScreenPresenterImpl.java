package com.example.plateful.authentication.signout.presenter;

import android.util.Log;

import com.example.plateful.authentication.model.AuthenticationRepository;
import com.example.plateful.authentication.model.AuthenticationRepositoryImpl;
import com.example.plateful.authentication.model.GetUserCallback;
import com.example.plateful.authentication.model.User;
import com.example.plateful.authentication.model.UserRepository;
import com.example.plateful.authentication.model.UserRepositoryImpl;
import com.example.plateful.authentication.signout.view.ProfileScreenView;
import com.example.plateful.authentication.socialaccountsignin.model.GoogleSignInHelper;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileScreenPresenterImpl implements ProfileScreenPresenter {

    private static final String TAG = ProfileScreenPresenterImpl.class.getSimpleName();

    private final ProfileScreenView profileScreenView;
    private final GoogleSignInHelper googleSignInHelper;
    private final AuthenticationRepository authenticationRepository;
    private final UserRepository userRepository;


    public ProfileScreenPresenterImpl(ProfileScreenView profileScreenView, GoogleSignInHelper googleSignInHelper) {
        this.profileScreenView = profileScreenView;
        this.googleSignInHelper = googleSignInHelper;
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
        if (googleSignInHelper != null) {
            googleSignInHelper.signOutGoogleAccount();
        }
        profileScreenView.onSignOut();
    }
}



