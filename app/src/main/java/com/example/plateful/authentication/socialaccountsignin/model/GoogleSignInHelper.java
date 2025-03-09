package com.example.plateful.authentication.socialaccountsignin.model;

import android.content.Context;
import android.content.Intent;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;


public class GoogleSignInHelper {

    private final GoogleSignInClient googleSignInClient;


    public GoogleSignInHelper(Context context, String webClientId) {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(webClientId)
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(context, gso);
    }


    public Intent getSignInIntent() {
        return googleSignInClient.getSignInIntent();
    }


    public GoogleSignInAccount getSignedInAccountFromIntent(Intent data) throws ApiException {
        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
        return task.getResult(ApiException.class);
    }


    public void signOutGoogleAccount() {
        googleSignInClient.signOut();
        googleSignInClient.revokeAccess();
    }
}
