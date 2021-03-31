package com.prography.musicana.data.onPording;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataOnBoarding {

    @SerializedName("onboarding")
    @Expose
    private List<Onboarding> onboarding = null;

    public List<Onboarding> getOnboarding() {
        return onboarding;
    }

    public void setOnboarding(List<Onboarding> onboarding) {
        this.onboarding = onboarding;
    }

}