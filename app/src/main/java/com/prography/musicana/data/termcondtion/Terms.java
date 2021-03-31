package com.prography.musicana.data.termcondtion;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Terms {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("data")
    @Expose
    private String data;
    @SerializedName("lastModified")
    @Expose
    private String lastModified;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getLastModified() {
        return lastModified;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

}