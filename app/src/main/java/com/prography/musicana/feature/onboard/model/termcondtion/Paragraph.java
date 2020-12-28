package com.prography.musicana.feature.onboard.model.termcondtion;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Paragraph
{

@SerializedName("start")
@Expose
private String start;
@SerializedName("end")
@Expose
private String end;
private final static long serialVersionUID = 3285609888213519974L;

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