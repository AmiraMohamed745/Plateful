package com.example.plateful.authentication.signout.presenter;

public interface ProfileScreenPresenter {
    void getCurrentUserData();
    void backUpUserFavoriteAndPlannedMeals();
    void signOutUser();
    void cleanUpDisposables();
}
