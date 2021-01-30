package com.prography.musicana.custem.SWInterface;

import android.media.MediaPlayer;

import com.prography.musicana.feature.bottomNavigationViewFragment.home.phoneFragment.model.PhoneModelFragmentList;

import java.util.ArrayList;

public interface ListItemClick {
        void itemClick( ArrayList<PhoneModelFragmentList> items ,int position ,PhoneModelFragmentList phoneModel);
    }