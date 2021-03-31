package com.prography.musicana.data.onPording;

import java.util.List;

public class SendDtatToActivity {


    private static SendDtatToActivity instance;

    private List<Onboarding> items;

    public SendDtatToActivity() {

    }

    public static SendDtatToActivity getInstance() {
        if (instance == null) {
            instance = new SendDtatToActivity();
        }
        return instance;
    }


    public List<Onboarding> getItems() {
        return items;
    }

    public void setItems(List<Onboarding> items) {
        this.items = items;
    }
}
