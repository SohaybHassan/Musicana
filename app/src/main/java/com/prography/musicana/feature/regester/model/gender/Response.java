package com.prography.musicana.feature.regester.model.gender;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Response
{

@SerializedName("data")
@Expose
private ArrayList<Datum> data = null;
private final static long serialVersionUID = 5425821651980206974L;

public ArrayList<Datum> getData() {
return data;
}

public void setData(ArrayList<Datum> data) {
this.data = data;
}

}