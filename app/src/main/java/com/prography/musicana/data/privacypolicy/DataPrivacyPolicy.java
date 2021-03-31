package com.prography.musicana.data.privacypolicy;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataPrivacyPolicy {

    @SerializedName("privacy")
    @Expose
    private Privacy privacy;
    @SerializedName("role")
    @Expose
    private Role role;

    public Privacy getPrivacy() {
        return privacy;
    }

    public void setPrivacy(Privacy privacy) {
        this.privacy = privacy;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}