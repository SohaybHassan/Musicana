package com.prography.musicana.feature.onboard.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


public class DataBody {

    @SerializedName("data")
    @Expose
    private ArrayList<OnPordingData> data = null;
    private final static long serialVersionUID = 5425821651980206974L;

    public ArrayList<OnPordingData> getData() {
        return data;
    }

    public void setData(ArrayList<OnPordingData> data) {
        this.data = data;
    }

}