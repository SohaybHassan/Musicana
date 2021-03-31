package com.prography.musicana.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.prography.musicana.data.User;

public class DataProfile {

    @SerializedName("user")
    @Expose
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}