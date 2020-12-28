package com.prography.musicana.feature.onboard.model;

import java.util.ArrayList;

public class SendDtatToActivity {
    private int id;
    private String name;
    private String det;
    private String img;
    private static SendDtatToActivity sendDtatToActivity;
    ArrayList<OnPordingData> items;


    public SendDtatToActivity() {
    }

    public static SendDtatToActivity getInstance() {
        if (sendDtatToActivity == null) {
            sendDtatToActivity = new SendDtatToActivity();
        }
        return sendDtatToActivity;
    }

    public ArrayList<OnPordingData> getItems() {
        return items;
    }

    public void setItems(ArrayList<OnPordingData> items) {
        this.items = items;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDet() {
        return det;
    }

    public void setDet(String det) {
        this.det = det;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
