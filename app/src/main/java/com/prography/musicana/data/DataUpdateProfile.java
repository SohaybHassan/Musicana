
package com.prography.musicana.data;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.prography.musicana.data.User;


public class DataUpdateProfile implements Serializable
{

    @SerializedName("user")
    @Expose
    private User user;
    private final static long serialVersionUID = -7375977541143036830L;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
