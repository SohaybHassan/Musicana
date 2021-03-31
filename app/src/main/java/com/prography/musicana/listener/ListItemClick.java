package com.prography.musicana.listener;

import com.prography.musicana.model.PhoneModelFragmentList;

import java.util.ArrayList;

public interface ListItemClick {
        void itemClick( ArrayList<PhoneModelFragmentList> items ,int position ,PhoneModelFragmentList phoneModel);
    }