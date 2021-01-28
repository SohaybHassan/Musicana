package com.prography.musicana.feature.bottomNavigationViewFragment.profile.model.profiledata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProfileData {

    @SerializedName("response")
    @Expose
    private Response response;
    @SerializedName("status")
    @Expose
    private boolean status;
    @SerializedName("language")
    @Expose
    private String language;

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

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

}