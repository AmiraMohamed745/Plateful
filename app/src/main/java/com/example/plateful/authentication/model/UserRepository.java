package com.example.plateful.authentication.model;

public interface UserRepository {
    void saveUser(User user, SaveUserCallback saveUserCallBack);
    void getUser(String userId, GetUserCallback getUserCallback);
}
