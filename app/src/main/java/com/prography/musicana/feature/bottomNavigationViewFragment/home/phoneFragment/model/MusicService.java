package com.prography.musicana.feature.bottomNavigationViewFragment.home.phoneFragment.model;

import android.app.Service;
import android.content.ContentUris;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.os.PowerManager;
import android.provider.MediaStore;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class MusicService extends Service implements MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener, MediaPlayer.OnCompletionListener {

    private MediaPlayer mediaPlayer;
    private ArrayList<PhoneModelFragmentList> songList;
    private int songPosn;
    static String startTime;
    static String endTime;
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

    @Override
    public void onPrepared(MediaPlayer mp) {
        //start playback
        mp.start();
        int timeEnd = mp.getDuration();
        int timestart =  mp.getCurrentPosition();

        endTime = String.format("%02d:%02d ", TimeUnit.MILLISECONDS.toMinutes(timeEnd), TimeUnit.MILLISECONDS.toSeconds(timeEnd) -
                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(timeEnd)));
        startTime = String.format("%02d:%02d ", TimeUnit.MILLISECONDS.toMinutes(timestart), TimeUnit.MILLISECONDS.toSeconds(timestart) -
                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(timestart)));
        Log.d("TAG", "End Time : " +endTime );
        Log.d("TAG", "Start Time : " +startTime );

    }
    public static  String EndTime(){
        return endTime;
    }
    public static  String startTime(){
        return startTime;
    }

    @Override
    public void onCompletion(MediaPlayer mp) {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = new MediaPlayer();
        Log.d("mediaPlayer", "onCreate: serves  ");
        initMusicPlayer();
    }

    public void initMusicPlayer() {
        Log.d("mediaPlayer", "initMusicPlayer: serves  ");
        mediaPlayer.setWakeMode(getApplicationContext(), PowerManager.PARTIAL_WAKE_LOCK);
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.setOnPreparedListener(this);
        mediaPlayer.setOnCompletionListener(this);
        mediaPlayer.setOnErrorListener(this);
    }

    public void setList(ArrayList<PhoneModelFragmentList> theSongs) {
        songList = theSongs;
    }

    public class MusicBinder extends Binder {

        public MusicService getService() {
            Log.d("mediaPlayer", "getService: serves  ");
            return MusicService.this;

        }
    }

    public void playSong() {
        Log.d("mediaPlayer", "playSong: serves  ");
        mediaPlayer.reset();
        //get Song
        PhoneModelFragmentList song = songList.get(songPosn);
        //get is
        long carrSong = song.getId();
        //set uri
        Uri trakUri = ContentUris.withAppendedId(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, carrSong);



        try {
            mediaPlayer.setDataSource(getApplicationContext(), trakUri);
            Log.d("mediaPlayer", "playSong: serves  ");
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("MUSIC SERVICE", "Error setting data source", e);
            Log.e("MUSIC SERVICE", e.getMessage());
        }

        mediaPlayer.prepareAsync();

    }

    public void setSong(int songIndex) {
        this.songPosn = songIndex;
    }

}
