package com.prography.musicana.feature.bottomNavigationViewFragment.home.phoneFragment;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Environment;
import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.prography.musicana.custem.BottomSheetAddToPlayList;
import com.prography.musicana.custem.BottomSheetMore;
import com.prography.musicana.custem.SWDialog;
import com.prography.musicana.databinding.FragmentPhoneBinding;
import com.prography.musicana.feature.CreateMediaPlayer;
import com.prography.musicana.custem.SWInterface.ListItemClick;
import com.prography.musicana.feature.bottomNavigationViewFragment.home.phoneFragment.adapter.PhoneFragmentAdapter;
import com.prography.musicana.feature.bottomNavigationViewFragment.home.phoneFragment.model.MusicService;
import com.prography.musicana.feature.bottomNavigationViewFragment.home.phoneFragment.model.PhoneModelFragmentList;

import java.util.ArrayList;


public class PhoneFragment extends Fragment {

    public static final String TAG = PhoneFragment.class.getSimpleName();
    private FragmentPhoneBinding binding;
    private String[] STAR = {"*"};
    private ArrayList<PhoneModelFragmentList> items;
    MediaPlayer mediaPlayer;
    private ListItemClick listener;
    private CreateMediaPlayer createMediaPlayer;
    //
    private boolean musicBound;
    private MusicService musicService;
    private Intent playIntent;
    private BottomSheetMore bottomSheetMore;
    private BottomSheetDialog bottomSheetDialog;
    private BottomSheetAddToPlayList bottomSheetAddToPlayList;
private SWDialog swDialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPhoneBinding.inflate(getLayoutInflater());

        mediaPlayer = new MediaPlayer();
        createMediaPlayer = CreateMediaPlayer.getInstance();
        items = createMediaPlayer.getLsi();
        bottomSheetDialog = new BottomSheetDialog(getContext());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        binding.rvPhoneFragment.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.rvPhoneFragment.setAdapter(new PhoneFragmentAdapter(items, new PhoneFragmentAdapter.ClickItems() {
            @Override
            public void onClickItem(int position, PhoneModelFragmentList phoneModel) {
                musicService.setSong(position);
                musicService.playSong(getContext());
                listener.itemClick(items, position, phoneModel);
            }

            @Override
            public void onClickMore(PhoneModelFragmentList phoneModel) {
                bottomSheetMore = new BottomSheetMore(getContext(), bottomSheetDialog, new BottomSheetMore.BottomSheetMoreMethode() {
                    @Override
                    public void addtoplayList() {
                        Log.d(TAG, "addtoplayList: ");
                        bottomSheetAddToPlayList = new BottomSheetAddToPlayList(getContext(), bottomSheetDialog, new BottomSheetAddToPlayList.BottomSheetAddToPlayListMethode() {
                            @Override
                            public void addtoplayList() {
                                Log.d(TAG, "addtoplayList: ");
                                swDialog=new SWDialog(new SWDialog.Dilogclicked() {
                                    @Override
                                    public void OK(String LsitName) {
                                        Log.d(TAG, "OK: ");

                                        swDialog.dismiss();
                                    }
                                });
                                swDialog.show(getParentFragmentManager(), "hi thir");
                            }
                        });
                        bottomSheetAddToPlayList.openDialog();

                    }

                    @Override
                    public void downlode() {
                        Toast.makeText(getContext(), " انت تمتلك هذه الاغنية بلفعل ", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void share() {
                        Log.d(TAG, "share:");

                    }

                    @Override
                    public void addToFavorite() {
                        Log.d(TAG, "addToFavorite: ");

                    }
                });
                bottomSheetMore.openDialog();
            }
        }));


        Log.d(TAG, "onViewCreated: " + Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC));

    }


    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            MusicService.MusicBinder binder = (MusicService.MusicBinder) service;
            musicService = binder.getService();
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