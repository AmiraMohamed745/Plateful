package com.example.plateful.authentication.model;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AuthenticationRepositoryImpl implements AuthenticationRepository {

    // For debugging
    private static final String TAG = AuthenticationRepositoryImpl.class.getSimpleName();

    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    @Override
    public void createUser(String email, String password, AuthenticationCallBack callback) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        if (user != null) {
                            callback.onSuccess(user.getUid());
                        } else {
                            Log.i(TAG, "createUser: Failure");
                        }
                    } else {
                        callback.onFailure(task.getException().getMessage());
                    }
                });
    }

    @Override
    public void signInUser(String email, String password, AuthenticationCallBack callback) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            if (user != null) {
                                callback.onSuccess(user.getUid());
                            }
                        } else {
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            callback.onFailure("Failed to sign in user");
                        }
                });
    }

    @Override
    public void signOutUser() {
        firebaseAuth.signOut();
        Log.d(TAG, "Sign out: ");
    }

}
