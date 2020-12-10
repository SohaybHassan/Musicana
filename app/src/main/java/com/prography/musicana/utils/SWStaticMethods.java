package com.prography.musicana.utils;

import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.TextView;

import com.prography.musicana.feature.MainActivity;
import com.prography.musicana.feature.bottomNavigationViewFragment.home.phoneFragment.model.MusicService;
import com.prography.musicana.feature.bottomNavigationViewFragment.home.phoneFragment.model.PhoneModelFragmentList;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class SWStaticMethods {
    private static ArrayList<PhoneModelFragmentList> SWLtems;
    private static MediaPlayer SWmp;

    public static void intentWithoutData(Activity firstActivity, Class<?> call) {
        Intent intentWithoutData = new Intent(firstActivity, call);
        firstActivity.startActivity(intentWithoutData);
    }

    public static void intentWithData(Activity firstActivity, Class<?> call, Bundle bundle) {
        Intent intentWithData = new Intent(firstActivity, call);

        intentWithData.putExtra("data", bundle);
        firstActivity.startActivity(intentWithData);
    }

    public static void NextMusic(int position, ArrayList<?> items) {
        if (position == items.size() - 1) {
            Log.d("TAG", "NextMusic: " + position);
            return;
        } else {
            Log.d("TAG", "NextMusic SH: " + position);
            MusicService.setSong(position);
            Log.d("TAG", "NextMusic SH: " + position);
            MusicService.playSong(MusicaApp.getIstant());

        }
    }

    public static void backMusic(int position) {
        if (position == 0) {
            Log.d("TAG", "onClick: " + position);
            return;
        } else {
            MusicService.setSong(position);
            MusicService.playSong(MusicaApp.getIstant());
        }
    }

    public static void stopAndStartMusic(MediaPlayer mediaPlayer) {

        if (mediaPlayer.isPlaying()) {
            Log.d("TAG", "onClick:  play");
            mediaPlayer.pause();
        } else {
            Log.d("TAG", "onClick:  stop");
            mediaPlayer.start();

        }
    }

    public static int getPosition() {
        int myListPostion = MusicService.getSongPosn();
        return myListPostion;

    }

    public static void setLsit(ArrayList<PhoneModelFragmentList> items) {
        SWLtems = items;
    }

    public static ArrayList<PhoneModelFragmentList> getList() {
        return SWLtems;
    }

    public static MediaPlayer getMediaPlayer() {
        return MusicService.getMyMediaPlayer();
    }


    public static Runnable updateSeekPar(SeekBar seekBar, Handler handler, TextView start) {
        Runnable UpdateSongTime = new Runnable() {
            public void run() {
                int startTime = MusicService.getCurrentPosition();
            start.setText(String.format("%02d:%02d ", TimeUnit.MILLISECONDS.toMinutes((long) startTime), TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
                    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) startTime)))
            );
                seekBar.setProgress((int) startTime);
                handler.postDelayed(this, 100);
            }
        };
        return UpdateSongTime;
    }
}
