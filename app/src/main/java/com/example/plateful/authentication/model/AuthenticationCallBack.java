package com.example.plateful.authentication.model;

public interface AuthenticationCallBack {
    void onSuccess(String userId);
    void onFailure(String failureMessage);
}
