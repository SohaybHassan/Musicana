package com.prography.musicana.feature.bottomNavigationViewFragment.profile.model.status;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CloseStatus {

    @SerializedName("response")
    @Expose
    private String id;
    @SerializedName("status")
    @Expose
    private boolean firstname;

    @SerializedName("Content-Language")
    @Expose
    private String ContentLanguage;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isFirstname() {
        return firstname;
    }

    public void setFirstname(boolean firstname) {
        this.firstname = firstname;
    }

    public String getContentLanguage() {
        return ContentLanguage;
    }

    public void setContentLanguage(String contentLanguage) {
        ContentLanguage = contentLanguage;
    }
}
