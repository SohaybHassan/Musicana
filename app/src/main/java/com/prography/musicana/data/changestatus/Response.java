package com.prography.musicana.data.changestatus;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Response {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private ChangeStatusData data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ChangeStatusData getData() {
        return data;
    }

    public void setData(ChangeStatusData data) {
        this.data = data;
    }

}