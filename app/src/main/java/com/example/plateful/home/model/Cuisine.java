package com.example.plateful.home.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class Cuisine implements Parcelable {

    @SerializedName("strArea")
    private String cuisineName;

    private int imageResId;

    public Cuisine(String cuisineName, int imageResId) {
        this.cuisineName = cuisineName;
        this.imageResId = imageResId;
    }

    protected Cuisine(Parcel in) {
        cuisineName = in.readString();
        imageResId = in.readInt();
    }

    public static final Creator<Cuisine> CREATOR = new Creator<Cuisine>() {
        @Override
        public Cuisine createFromParcel(Parcel in) {
            return new Cuisine(in);
        }

        @Override
        public Cuisine[] newArray(int size) {
            return new Cuisine[size];
        }
    };

    public String getCuisineName() {
        return cuisineName;
    }

    public void setCuisineName(String cuisineName) {
        this.cuisineName = cuisineName;
    }

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(cuisineName);
        parcel.writeInt(imageResId);
    }
}
