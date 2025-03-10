package com.example.plateful.utils;

import com.google.firebase.auth.FirebaseAuth;

public class UserSession {
    public static String getCurrentUserId() {
        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }
}
