package com.example.plateful.search.category.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class Category implements Parcelable {

    @SerializedName("idCategory")
    private String categoryId;
    
    @SerializedName("strCategory")
    private String categoryName;
    
    @SerializedName("strCategoryThumb")
    private String categoryImageUrl;


    public Category() {
    }

    public Category(String categoryId, String categoryName, String categoryImageUrl) {
        super();
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryImageUrl = categoryImageUrl;
    }

    protected Category(Parcel in) {
        categoryId = in.readString();
        categoryName = in.readString();
        categoryImageUrl = in.readString();
    }

    public static final Creator<Category> CREATOR = new Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel in) {
            return new Category(in);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryImageUrl() {
        return categoryImageUrl;
    }

    public void setCategoryImageUrl(String categoryImageUrl) {
        this.categoryImageUrl = categoryImageUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(categoryId);
        parcel.writeString(categoryName);
        parcel.writeString(categoryImageUrl);
    }
}
