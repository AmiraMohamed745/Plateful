package com.example.plateful.authentication.model;

public interface SaveUserCallback {
    void onSuccess();
    void onFailure(String failureMessage);
}
