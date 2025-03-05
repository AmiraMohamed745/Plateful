package com.example.plateful.home.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CuisineResponse {

    @SerializedName("meals")
    private List<Cuisine> cuisines;

    public CuisineResponse() {
    }

    public CuisineResponse(List<Cuisine> cuisines) {
        this.cuisines = cuisines;
    }

    public List<Cuisine> getCuisines() {
        return cuisines;
    }

    public void setCuisines(List<Cuisine> cuisines) {
        this.cuisines = cuisines;
    }

}
