package com.example.plateful.network;

public interface ConnectivityListener {
    void onNetworkAvailable();
    void onNetworkLost();
}
