package com.prography.musicana.feature.login.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Response {

    @SerializedName("message")
    @Expose
    private Message message;
    private final static long serialVersionUID = -8868860173677751506L;

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

}