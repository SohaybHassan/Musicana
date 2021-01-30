package com.prography.musicana.feature.bottomNavigationViewFragment.home.phoneFragment.model;

import java.util.ArrayList;

public class AddToList {
    private ArrayList<String> additem;
    static AddToList mInstance;


    public static AddToList getInstance() {
        if (mInstance == null) {
            mInstance = new AddToList();
        }
        return mInstance;
    }

    public ArrayList<String> getAdditem() {
        return additem;
    }

    public void setAdditem(ArrayList<String> additem) {
        this.additem = additem;
    }
}
