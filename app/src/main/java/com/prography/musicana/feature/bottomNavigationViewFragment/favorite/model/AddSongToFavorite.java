package com.prography.musicana.feature.bottomNavigationViewFragment.favorite.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddSongToFavorite {

    @SerializedName("response")
    @Expose
    private String response;
    @SerializedName("status")
    @Expose
    private boolean status;
    @SerializedName("Content-Language")
    @Expose
    private String contentLanguage;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
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