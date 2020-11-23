package com.prography.musicana.feature.bottomNavigationViewFragment.home.phoneFragment.model;

public class PhoneModelFragmentList {
    private String name;
    private String alpom;
    private int id;

    public PhoneModelFragmentList(int id, String name, String alpom) {
        this.name = name;
        this.alpom = alpom;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlpom() {
        return alpom;
    }

    public void setAlpom(String alpom) {
        alpom = alpom;
    }
}
