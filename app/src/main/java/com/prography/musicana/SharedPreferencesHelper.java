package com.prography.musicana;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.prography.musicana.utils.MusicaApp;

public class SharedPreferencesHelper {

    private String SAVE_DATA_ADDRESS = "com.misknet.tabseet.marketer.Network.asp.models.SAVE_DATA_ADDRESS";
    private String SAVE_DATA_ADDRESS_FIRST = "com.misknet.tabseet.marketer.Network.asp.models.SAVE_DATA_ADDRESS_FIRST";
    private SharedPreferences saveDataAddress;
    private SharedPreferences saveDataAddressisfirst;
    public static final String TOKEN = "TOKEN";
    public static final String ISFIRSTTIME = "ISFIRSTTIME";

    public static int getMode(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(AppConstants.Mode, Context.MODE_PRIVATE);
        return preferences.getInt(AppConstants.Mode, AppConstants.MoonMode);
    }

    public SharedPreferencesHelper() {
        saveDataAddress = MusicaApp.getIstant().getSharedPreferences(SAVE_DATA_ADDRESS, Context.MODE_PRIVATE);
        saveDataAddressisfirst = MusicaApp.getIstant().getSharedPreferences(SAVE_DATA_ADDRESS_FIRST, Context.MODE_PRIVATE);

    }

    public void saveData(String token) {
        SharedPreferences.Editor editor = saveDataAddress.edit();
        Log.e("Prefrins", "saveData TOKEN in preferins helper");
        editor.putString(TOKEN, token);
        editor.apply();

    }

    public void isFirstTime(boolean isFirst) {
        SharedPreferences.Editor editor = saveDataAddressisfirst.edit();
        Log.e("Prefrins", "saveData TOKEN in preferins helper");
        editor.putBoolean(ISFIRSTTIME, isFirst);
        editor.apply();

    }

    public boolean getisFirst() {
        boolean isfirst = saveDataAddressisfirst.getBoolean(ISFIRSTTIME, true);
        return isfirst;
    }

    public String getToken() {
        // TODO I will to send  token  like   this ""
        String Token = saveDataAddress.getString(TOKEN, "");
        return Token;
    }


    public void clerData() {
        SharedPreferences.Editor editor = saveDataAddress.edit();
        editor.clear().apply();
    }


}
