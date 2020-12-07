package com.prography.musicana.feature.bottomNavigationViewFragment.home.phoneFragment.model;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.os.PowerManager;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class MusicService extends Service implements MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener, MediaPlayer.OnCompletionListener {

    private static MediaPlayer mediaPlayer;
    private static ArrayList<PhoneModelFragmentList> songList;
    private static int songPosn;
    static String startTime;
    static String endTime;

    static int mCurrentPosition;
    static int mDuration;
    private final IBinder musicBind = new MusicBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return musicBind;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        mediaPlayer.stop();
        mediaPlayer.release();
        return false;
    }


    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        return false;
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onPrepared(MediaPlayer mp) {
        //start playback
        mp.start();
        int timeEnd = mp.getDuration();
        int timestart = mp.getCurrentPosition();
        setDuration(timeEnd);
        setCurrentPosition(timestart);
        endTime = String.format("%02d:%02d ", TimeUnit.MILLISECONDS.toMinutes(timeEnd), TimeUnit.MILLISECONDS.toSeconds(timeEnd) -
                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(timeEnd)));


        startTime = String.format("%02d:%02d ", TimeUnit.MILLISECONDS.toMinutes(timestart), TimeUnit.MILLISECONDS.toSeconds(timestart) -
                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(timestart)));
        Log.d("TAG", "End Time test : " + endTime);
        Log.d("TAG", "Start Time  test: " + startTime);

    }

    public static String EndTime() {
        return endTime;
    }

    public static String startTime() {
        return startTime;
    }

    public void setDuration(int Duration) {
        this.mDuration = Duration;
    }

    public void setCurrentPosition(int CurrentPosition) {
        this.mCurrentPosition = CurrentPosition;
    }

    public static int getDuration() {
        return mDuration;
    }

    public static int getCurrentPosition() {
        return mCurrentPosition;
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        songPosn = getSongPosn();
        setSong(++songPosn);
        playSong(getApplicationContext());

    }

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = new MediaPlayer();
        initMusicPlayer();
    }

    public void initMusicPlayer() {

        mediaPlayer.setWakeMode(getApplicationContext(), PowerManager.PARTIAL_WAKE_LOCK);
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.setOnPreparedListener(this);
        mediaPlayer.setOnCompletionListener(this);
        mediaPlayer.setOnErrorListener(this);
    }

    public void setList(ArrayList<PhoneModelFragmentList> theSongs) {
        songList = theSongs;
    }

    public static ArrayList<PhoneModelFragmentList> getListSong() {

        return songList;
    }


    public class MusicBinder extends Binder {

        public MusicService getService() {
            return MusicService.this;

        }
    }

    public static void playSong(Context context) {

        mediaPlayer.reset();
        //get Song
        PhoneModelFragmentList song = songList.get(songPosn);
        //get is
        long carrSong = song.getId();
        //set uri
        Uri trakUri = ContentUris.withAppendedId(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, carrSong);


        try {
            mediaPlayer.setDataSource(context, trakUri);

        } catch (IOException e) {
            e.printStackTrace();

        }

        mediaPlayer.prepareAsync();

    }

    public static void setSong(int songIndex) {
        Log.d("TAG", "setSong: " + songIndex);
        songPosn = songIndex;
        Log.d("TAG", "setSong: " + songIndex);
    }

    public static ArrayList<PhoneModelFragmentList> getSongList() {
        return songList;
    }

    public static int getSongPosn() {
        return songPosn;
    }

    public static MediaPlayer getMyMediaPlayer() {
        return mediaPlayer;
    }


}
