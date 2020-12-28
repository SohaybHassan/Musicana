package com.prography.musicana.feature.onboard.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OnpordingModel {

    @SerializedName("response")
    @Expose
    private DataBody response;
    @SerializedName("status")
    @Expose
    private boolean status;
    @SerializedName("Content-Language")
    @Expose
    private String contentLanguage;
    private final static long serialVersionUID = -517650579533454607L;

    public DataBody getResponse() {
        return response;
    }

    public void setResponse(DataBody response) {
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




