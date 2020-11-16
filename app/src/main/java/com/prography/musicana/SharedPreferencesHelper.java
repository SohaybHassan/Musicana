package com.prography.musicana;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesHelper {
    public static int getMode(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(AppConstants.Mode, Context.MODE_PRIVATE);
        return preferences.getInt(AppConstants.Mode, AppConstants.MoonMode);
    }
}
