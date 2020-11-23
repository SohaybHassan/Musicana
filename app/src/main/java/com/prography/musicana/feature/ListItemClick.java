package com.prography.musicana.feature;

import android.media.MediaPlayer;

import com.prography.musicana.feature.bottomNavigationViewFragment.home.phoneFragment.model.PhoneModelFragmentList;

import java.util.ArrayList;

public interface ListItemClick {
        void itemClick(MediaPlayer mediaPlayer, ArrayList<PhoneModelFragmentList> items);
    }