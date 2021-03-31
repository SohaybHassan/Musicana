package com.prography.musicana.feature.bottomNavigationViewFragment.home.PlayListFragment.model.deletesong;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Response {

    @SerializedName("massage")
    @Expose
    private String massage;

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }

}