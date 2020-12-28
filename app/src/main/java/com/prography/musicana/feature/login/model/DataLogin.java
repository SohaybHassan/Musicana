package com.prography.musicana.feature.login.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataLogin {

    @SerializedName("response")
    @Expose
    private Response response;
    @SerializedName("status")
    @Expose
    private boolean status;
    @SerializedName("Content-Language")
    @Expose
    private String contentLanguage;
    private final static long serialVersionUID = -8231478074487592462L;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getContentLanguage() {
        return contentLanguage;
    }

    public void setContentLanguage(String contentLanguage) {
        this.contentLanguage = contentLanguage;
    }

}