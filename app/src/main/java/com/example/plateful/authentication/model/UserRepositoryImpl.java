package com.example.plateful.authentication.model;

import android.util.Log;

import com.google.firebase.firestore.FirebaseFirestore;

public class UserRepositoryImpl implements UserRepository{

    // For debugging
    private static final String TAG = UserRepositoryImpl.class.getSimpleName();

    private static final String USER_COLLECTION = "users";
    private FirebaseFirestore firestoreDatabase;

    public UserRepositoryImpl() {
        firestoreDatabase = FirebaseFirestore.getInstance();
    }

    @Override
    public void saveUser(User user, SaveUserCallback saveUserCallBack) {
        firestoreDatabase.collection(USER_COLLECTION)
                .document(user.getUserId())
                .set(user)
                .addOnSuccessListener((aVoid) -> saveUserCallBack.onSuccess())
                .addOnFailureListener(e -> saveUserCallBack.onFailure(e.getMessage()));
    }

    @Override
    public void getUser(String userId, GetUserCallback getUserCallback) {
        firestoreDatabase.collection(USER_COLLECTION)
                .document(userId)
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        User user = documentSnapshot.toObject(User.class);
                        getUserCallback.onSuccess(user);
                    } else {
                        Log.i(TAG, "getUser: failure ");
                    }
                })
                .addOnFailureListener(e -> getUserCallback.onFailure(e.getMessage()));
    }

}
