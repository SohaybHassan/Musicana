package com.prography.musicana.data.verification;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.prography.musicana.data.Settings;
import com.prography.musicana.data.User;

public class DataVerificationEmail {

    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("settings")
    @Expose
    private Settings settings;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Settings getSettings() {
        return settings;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }

}