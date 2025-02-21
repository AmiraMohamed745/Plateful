package com.example.plateful.network;

public interface MealRemoteDataSource {
    // void makeNetworkCall(NetworkCallBack networkCallBack);
    void getRandomMeal(NetworkCallBack callback);
    // Add more methods later for the other API endpoints (country - category - ingredients thumbnails, etc)
    // void getMealsByCuisine(NetworkCallBack callback)
    // void getMealsByCategory(NetworkCallBack callback)
}
