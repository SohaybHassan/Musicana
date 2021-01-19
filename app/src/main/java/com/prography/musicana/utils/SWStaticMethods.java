package com.prography.musicana.utils;

import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.TextView;

import com.prography.musicana.feature.MainActivity;
import com.prography.musicana.feature.bottomNavigationViewFragment.home.phoneFragment.model.MusicService;
import com.prography.musicana.feature.bottomNavigationViewFragment.home.phoneFragment.model.PhoneModelFragmentList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class SWStaticMethods {
    private static ArrayList<PhoneModelFragmentList> SWLtems = new ArrayList<>();


    public static void intentWithoutData(Activity firstActivity, Class<?> call) {
        Intent intentWithoutData = new Intent(firstActivity, call);
        firstActivity.startActivity(intentWithoutData);
        firstActivity.finish();
    }

    public static void intentWithData(Activity firstActivity, Class<?> call, Bundle bundle) {
        Intent intentWithData = new Intent(firstActivity, call);
        intentWithData.putExtra("data", bundle);
        firstActivity.startActivity(intentWithData);
        firstActivity.finish();
    }

    public static void intentWithoutDataAndFinish(Activity firstActivity, Class<?> call) {
        Intent intentWithoutData = new Intent(firstActivity, call);
        firstActivity.startActivity(intentWithoutData);

    }


}

