package com.example.plateful.model;

import android.content.SharedPreferences;
import android.content.Context;

public class SessionManager {

    private static final String PREFS_NAME = "auth_prefs";
    private static final String KEY_IS_GUEST = "is_guest";
    private SharedPreferences preferences;

    public SessionManager(Context context) {
        preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public void setGuestMode(boolean isGuest) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(KEY_IS_GUEST, isGuest);
        editor.apply();
    }

    public boolean isGuestMode() {
        return preferences.getBoolean(KEY_IS_GUEST, false);
    }

    public void clearSession() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
    }


}
