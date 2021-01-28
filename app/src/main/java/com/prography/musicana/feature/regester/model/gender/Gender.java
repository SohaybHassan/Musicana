package com.prography.musicana.feature.regester.model.gender;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Gender {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("gender")
    @Expose
    private String gender;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

}