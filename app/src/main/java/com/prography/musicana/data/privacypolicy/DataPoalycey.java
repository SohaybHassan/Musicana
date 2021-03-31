package com.prography.musicana.data.privacypolicy;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataPoalycey {

    @SerializedName("response")
    @Expose
    private ResponsePrivacyPolicy responsePrivacyPolicy;
    @SerializedName("status")
    @Expose
    private boolean status;
    @SerializedName("Content-Language")
    @Expose
    private String contentLanguage;

    public ResponsePrivacyPolicy getResponsePrivacyPolicy() {
        return responsePrivacyPolicy;
    }

    public void setResponsePrivacyPolicy(ResponsePrivacyPolicy responsePrivacyPolicy) {
        this.responsePrivacyPolicy = responsePrivacyPolicy;
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