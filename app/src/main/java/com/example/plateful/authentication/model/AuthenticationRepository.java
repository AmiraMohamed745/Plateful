package com.example.plateful.authentication.model;

public interface AuthenticationRepository {
    void createUser(String email, String password, AuthenticationCallBack callback);
    void signInUser(String email, String password, AuthenticationCallBack callback);
    void signOutUser();
}
