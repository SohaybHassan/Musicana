package com.prography.musicana.utils;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class MusicaApp extends Application {
    public static final String CHANNEL_ID = "MUSIC_ID";
    public static final String CHANNEL_NAME = "MUSIC";
    public static final String CHANNEL_DESCRIPTION = " to run your music";
    private static MusicaApp instans;

    @Override
    public void onCreate() {
        super.onCreate();
        instans = this;
        createChannel();
    }

    public static MusicaApp getIstant() {
        return instans;
    }

    public void createChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.setDescription(CHANNEL_DESCRIPTION);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(notificationChannel);
        }


    }
}
