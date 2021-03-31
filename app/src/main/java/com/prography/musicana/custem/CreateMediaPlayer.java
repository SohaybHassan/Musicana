package com.prography.musicana.custem;

import android.content.ContentUris;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.TextView;

import com.prography.musicana.model.MusicService;
import com.prography.musicana.model.PhoneModelFragmentList;
import com.prography.musicana.utils.MusicaApp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class CreateMediaPlayer {
    private MediaPlayer mediaPlayer;
    private ArrayList<PhoneModelFragmentList> mList;
    private static CreateMediaPlayer instance;

    private CreateMediaPlayer() {
        mList = new ArrayList<>();
        mediaPlayer = new MediaPlayer();
    }

    public static CreateMediaPlayer getInstance() {
        if (instance == null) {
            instance = new CreateMediaPlayer();
        }
        return instance;
    }


    public void getplayListMusicFomDivice() {
        Cursor cursor;
        Uri allsongUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String selection = MediaStore.Audio.Media.IS_MUSIC + " != 0";
        String[] STAR = {"*"};
        Log.d("TAG", "listAllSong: " + allsongUri.toString());
        if (isSdPresent()) {
            cursor = MusicaApp.getIstant().getContentResolver().query(allsongUri, null, selection, null, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {

                        String songName = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));
                        int songId = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media._ID));
                        String albumname = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM));


                        Long albumId = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM_ID));
                        Uri sArtworkUri = Uri.parse("content://media/external/audio/albumart");
                        Uri albumArtUri = ContentUris.withAppendedId(sArtworkUri, albumId);

                        try {
                            mediaPlayer.addTimedTextSource(MusicaApp.getIstant(), allsongUri, ".mp3");
                        } catch (IOException e) {
                            Log.d("TAG", "listAllSong: " + e.getMessage());
                            e.printStackTrace();
                        }
                        if (!cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM)).equals("WhatsApp Audio")
                                && !cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM)).equals("call_rec")
                                && !cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM)).equals("Voice Messages")) {
                            mList.add(new PhoneModelFragmentList(songId, songName, albumname, albumArtUri));
                        }


                    } while (cursor.moveToNext());
                }
                cursor.close();
            }

        }
    }

    static {
        CreateMediaPlayer.getInstance().getplayListMusicFomDivice();

    }

    public ArrayList<PhoneModelFragmentList> getLsi() {
        return mList;
    }


    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public void NextMusic(int position, ArrayList<PhoneModelFragmentList> items) {
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

    public void backMusic(int position) {
        if (position == 0) {
            Log.d("TAG", "onClick: " + position);
            return;
        } else {
            MusicService.setSong(position);
            MusicService.playSong(MusicaApp.getIstant());
        }
    }

    public void stopAndStartMusic(MediaPlayer mediaPlayer) {

        if (mediaPlayer.isPlaying()) {
            Log.d("TAG", "onClick:  play");
            mediaPlayer.pause();
        } else {
            Log.d("TAG", "onClick:  stop");
            mediaPlayer.start();

        }
    }

    public int getPosition() {
        int myListPostion = MusicService.getSongPosn();
        return myListPostion;
    }

    public static boolean isSdPresent() {
        return android.os.Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
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
