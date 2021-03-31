package com.prography.musicana.data.termcondtion;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Role {

    @SerializedName("paragraph")
    @Expose
    private Paragraph paragraph;
    @SerializedName("underline")
    @Expose
    private Underline underline;
    @SerializedName("bold")
    @Expose
    private Bold bold;

    public Paragraph getParagraph() {
        return paragraph;
    }

    public void setParagraph(Paragraph paragraph) {
        this.paragraph = paragraph;
    }

    public Underline getUnderline() {
        return underline;
    }

    public void setUnderline(Underline underline) {
        this.underline = underline;
    }

    public Bold getBold() {
        return bold;
    }

    public void setBold(Bold bold) {
        this.bold = bold;
    }

}