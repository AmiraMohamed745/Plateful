package com.example.plateful.authentication.signout.view;

import com.example.plateful.authentication.model.User;

public interface ProfileScreenView {
    void onSignOut();
    void displayUserData(User user);
}
