package com.example.plateful.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkRequest;

import androidx.annotation.NonNull;

public class ConnectivityHelper {

    private final ConnectivityManager connectivityManager;
    private ConnectivityManager.NetworkCallback networkCallback;

    public ConnectivityHelper(Context context) {
        connectivityManager = (ConnectivityManager) context.getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    public void registerConnectivityCallback(final ConnectivityListener listener) {
        NetworkRequest networkRequest = new NetworkRequest.Builder().build();
        networkCallback = new ConnectivityManager.NetworkCallback() {
            @Override
            public void onAvailable(@NonNull Network network) {
                listener.onNetworkAvailable();
            }
            @Override
            public void onLost(@NonNull Network network) {
                listener.onNetworkLost();
            }
        };
        connectivityManager.registerNetworkCallback(networkRequest, networkCallback);
    }

    public void unregisterConnectivityCallback() {
        if (connectivityManager != null && networkCallback != null) {
            connectivityManager.unregisterNetworkCallback(networkCallback);
        }
    }


}
