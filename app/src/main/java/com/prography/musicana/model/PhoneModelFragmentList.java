package com.prography.musicana.model;

import android.net.Uri;

public class PhoneModelFragmentList {
    private String name;
    private String alpom;
    private int id;
    private Uri uri;


    public PhoneModelFragmentList(int id, String name, String alpom, Uri uri) {
        this.name = name;
        this.alpom = alpom;
        this.id = id;
        this.uri = uri;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
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
