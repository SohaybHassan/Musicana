package com.prography.musicana.feature.bottomNavigationViewFragment.home.phoneFragment;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Environment;
import android.os.IBinder;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.TextView;


import com.prography.musicana.R;
import com.prography.musicana.databinding.FragmentPhoneBinding;
import com.prography.musicana.feature.ListItemClick;
import com.prography.musicana.feature.OnFragmentInteractionListener;
import com.prography.musicana.feature.bottomNavigationViewFragment.home.phoneFragment.adapter.PhoneFragmentAdapter;
import com.prography.musicana.feature.bottomNavigationViewFragment.home.phoneFragment.model.MusicService;
import com.prography.musicana.feature.bottomNavigationViewFragment.home.phoneFragment.model.PhoneModelFragmentList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.TimeUnit;


public class PhoneFragment extends Fragment {

    public static final String TAG = PhoneFragment.class.getSimpleName();
    private FragmentPhoneBinding binding;
    private String[] STAR = {"*"};
    private ArrayList<PhoneModelFragmentList> items;
    MediaPlayer mediaPlayer;
    private ListItemClick listener;
    //
    private MusicService musicService;
    private Intent playIntent;
    private boolean musicBound;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPhoneBinding.inflate(getLayoutInflater());
        items = new ArrayList<>();
        mediaPlayer = new MediaPlayer();

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listAllSong();

        Collections.sort(items, new Comparator<PhoneModelFragmentList>() {
            @Override
            public int compare(PhoneModelFragmentList o1, PhoneModelFragmentList o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });

        binding.rvPhoneFragment.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.rvPhoneFragment.setAdapter(new PhoneFragmentAdapter(items, new PhoneFragmentAdapter.ClickItems() {
            @Override
            public void onClickItem(int position,PhoneModelFragmentList phoneModel) {
                musicService.setSong(position);
                musicService.playSong(getContext());
                Log.d("mediaPlayer", "onClickItem: fragment  ");
                listener.itemClick(items,position,phoneModel);
            }
        }));


        Log.d(TAG, "onViewCreated: " + Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC));

    }

    public void listAllSong() {
        Cursor cursor;
        Uri allsongUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
         String selection = MediaStore.Audio.Media.IS_MUSIC + " != 0";
         String [] STAR={"*"};
        Log.d(TAG, "listAllSong: " + allsongUri.toString());
        if (isSdPresent()) {
            cursor = getContext().getContentResolver().query(allsongUri,null , selection, null, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {

                        String songName = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));
                        int songId = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media._ID));
                        String albumname = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM));

                        Log.d(TAG, "listAllSong: " + songName + "_####### _" + songId + "_############### _" + albumname);
                        try {
                            mediaPlayer.addTimedTextSource(getContext(), allsongUri, ".mp3");
                        } catch (IOException e) {
                            Log.d(TAG, "listAllSong: " + e.getMessage());
                            e.printStackTrace();
                        }
                        items.add(new PhoneModelFragmentList(songId, songName, albumname));

                    } while (cursor.moveToNext());
                }
                cursor.close();
            }

        }
    }

    public static boolean isSdPresent() {
        return android.os.Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }


    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            MusicService.MusicBinder binder = (MusicService.MusicBinder) service;
            musicService = binder.getService();
            musicService.setList(items);
            musicBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            musicBound = false;
        }
    };

    @Override
    public void onStart() {
        super.onStart();
        if (playIntent == null) {
            playIntent = new Intent(getActivity(), MusicService.class);
            getActivity().bindService(playIntent, serviceConnection, ContextThemeWrapper.BIND_AUTO_CREATE);
            getActivity().startService(playIntent);
        }
    }


    @Override
    public void onDestroy() {
        getActivity().stopService(playIntent);
        musicService = null;
        super.onDestroy();
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof ListItemClick) {
            listener = (ListItemClick) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }



}