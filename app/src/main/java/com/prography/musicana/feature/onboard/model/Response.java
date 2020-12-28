package com.prography.musicana.feature.onboard.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Response
{

@SerializedName("data")
@Expose
private BodyPalycey data;
@SerializedName("role")
@Expose
private Role role;
private final static long serialVersionUID = -4148892202965681470L;

public BodyPalycey getData() {
return data;
}

public void setData(BodyPalycey data) {
this.data = data;
}

public Role getRole() {
return role;
}

public void setRole(Role role) {
this.role = role;
}

}