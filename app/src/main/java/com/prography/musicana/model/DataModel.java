package com.prography.musicana.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataModel {

    @SerializedName("response")
    @Expose
    private ResponseModel response;
    @SerializedName("status")
    @Expose
    private boolean status;
    @SerializedName("Content-Language")
    @Expose
    private String contentLanguage;

    public ResponseModel getResponse() {
        return response;
    }

    public void setResponse(ResponseModel response) {
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
