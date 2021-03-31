package com.prography.musicana.feature.home.model.home;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.prography.musicana.feature.home.model.home.Data;

public class Response {

    @SerializedName("data")
    @Expose
    private Data data;

    @SerializedName("message")
    @Expose
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

}