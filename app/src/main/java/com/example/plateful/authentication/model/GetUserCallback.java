package com.example.plateful.authentication.model;

public interface GetUserCallback {
    void onSuccess(User user);
    void onFailure(String failureMessage);
}
