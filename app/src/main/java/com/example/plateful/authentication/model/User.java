package com.example.plateful.authentication.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

// Will probably un-implement Parcelable as I am not using SafeArgs
public class User {

    private String userId;
    private String username;
    private String email;
    private String profileImageUrl;

    public User() {}


    public User(String userId, String username, String email) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.profileImageUrl = "";
    }

    protected User(Parcel in) {
        userId = in.readString();
        username = in.readString();
        email = in.readString();
        profileImageUrl = in.readString();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

}
