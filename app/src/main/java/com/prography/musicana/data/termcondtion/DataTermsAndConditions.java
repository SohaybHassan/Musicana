package com.prography.musicana.data.termcondtion;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataTermsAndConditions {

    @SerializedName("terms")
    @Expose
    private Terms terms;
    @SerializedName("role")
    @Expose
    private Role role;

    public Terms getTerms() {
        return terms;
    }

    public void setTerms(Terms terms) {
        this.terms = terms;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}