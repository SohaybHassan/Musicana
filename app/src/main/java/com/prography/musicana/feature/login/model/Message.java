package com.prography.musicana.feature.login.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Message {

    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("settings")
    @Expose
    private Settings settings;
    private final static long serialVersionUID = 3425010191723731240L;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Settings getSettings() {
        return settings;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }

}