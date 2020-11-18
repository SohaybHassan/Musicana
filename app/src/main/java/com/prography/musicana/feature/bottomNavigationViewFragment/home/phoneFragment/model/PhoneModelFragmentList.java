package com.prography.musicana.feature.bottomNavigationViewFragment.home.phoneFragment.model;

public class PhoneModelFragmentList {
    private String name;
    private String Alpom;

    public PhoneModelFragmentList(String name, String alpom) {
        this.name = name;
        Alpom = alpom;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlpom() {
        return Alpom;
    }

    public void setAlpom(String alpom) {
        Alpom = alpom;
    }
}
