package com.prography.musicana.data.registermodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseRegester {

    @SerializedName("message")
    @Expose
    private String message;

    private final static long serialVersionUID = -6997180442959505678L;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}