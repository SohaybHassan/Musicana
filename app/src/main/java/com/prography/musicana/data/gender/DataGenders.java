package com.prography.musicana.data.gender;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataGenders {

    @SerializedName("genders")
    @Expose
    private List<Gender> genders = null;

    public List<Gender> getGenders() {
        return genders;
    }

    public void setGenders(List<Gender> genders) {
        this.genders = genders;
    }

}