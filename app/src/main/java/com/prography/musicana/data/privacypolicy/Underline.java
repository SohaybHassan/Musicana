package com.prography.musicana.data.privacypolicy;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Underline {

    @SerializedName("start")
    @Expose
    private String start;
    @SerializedName("end")
    @Expose
    private String end;

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

}