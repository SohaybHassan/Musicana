package com.prography.musicana.ui.fragment;

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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Environment;
import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.prography.musicana.custem.bottomsheet.BottomSheetAddToPlayList;
import com.prography.musicana.custem.bottomsheet.BottomSheetMore;
import com.prography.musicana.custem.dialog.SWDialog;
import com.prography.musicana.data.getallplaylist.Data;
import com.prography.musicana.databinding.FragmentPhoneBinding;
import com.prography.musicana.custem.CreateMediaPlayer;
import com.prography.musicana.listener.ListItemClick;
import com.prography.musicana.viewmodel.FavoiteViewModel;
import com.prography.musicana.data.getallplaylist.GetAllPlayList;
import com.prography.musicana.data.getallplaylist.Playlist;
import com.prography.musicana.viewmodel.PlaylsitViewModel;
import com.prography.musicana.adapter.PhoneFragmentAdapter;
import com.prography.musicana.model.MusicService;
import com.prography.musicana.model.PhoneModelFragmentList;
import com.prography.musicana.utils.SWStaticMethods;

import java.util.ArrayList;


public class PhoneFragment extends Fragment {

    public static final String TAG = PhoneFragment.class.getSimpleName();
    private FragmentPhoneBinding binding;
    private ArrayList<PhoneModelFragmentList> items;

    private ListItemClick listener;
    private CreateMediaPlayer createMediaPlayer;
    private FavoiteViewModel favoiteViewModel;
    //
    private boolean musicBound;
    private MusicService musicService;
    private Intent playIntent;
    private BottomSheetMore bottomSheetMore;
    private BottomSheetDialog bottomSheetDialog;
    private BottomSheetAddToPlayList bottomSheetAddToPlayList;
    private SWDialog swDialog;
    private ArrayList<String> addItem;
    private ArrayList<Playlist> getPlaylistName;
    private PlaylsitViewModel playlsitViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPhoneBinding.inflate(getLayoutInflater());
        createMediaPlayer = CreateMediaPlayer.getInstance();
        items = createMediaPlayer.getLsi();
        playlsitViewModel = new ViewModelProvider(this).get(PlaylsitViewModel.class);
        favoiteViewModel = new ViewModelProvider(this).get(FavoiteViewModel.class);
        bottomSheetDialog = new BottomSheetDialog(getContext());
        addItem = new ArrayList<>();
        getPlaylistName = new ArrayList<>();

        return binding.getRoot();


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getPlaylistName = getAllplaylist();
        binding.rvPhoneFragment.setLayoutManager(new LinearLayoutManager(getActivity()));
        phoneFragmentAdapter();
        Log.d(TAG, "onViewCreated: " + Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC));

    }

    public void phoneFragmentAdapter() {
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
                        Log.d(TAG, "addtoplayList: addtoplayList ");
                        bottomSheetAddToPlayList = new BottomSheetAddToPlayList(getContext(), bottomSheetDialog, new BottomSheetAddToPlayList.BottomSheetAddToPlayListMethode() {
                            @Override
                            public void addtoplayList() {
                                swDialog = new SWDialog(lsitName -> {
                                    addItem.clear();
                                    createPLayList(lsitName.getText().toString());
                                    getPlaylistName = getAllplaylist();
                                    bottomSheetAddToPlayList.setList(getPlaylistName);
                                    swDialog.dismiss();
                                });
                                swDialog.show(getParentFragmentManager(), "hi thir");
                            }

                            @Override
                            public void addsongToplayList(String playListid) {
                                addsong(String.valueOf(phoneModel.getId()), playListid);
                            }
                        });

                        bottomSheetAddToPlayList.openDialog();

                        Log.d(TAG, "addtoplayList: " + getPlaylistName.size());
                        bottomSheetAddToPlayList.setList(getPlaylistName);
                    }

                    @Override
                    public void downlode() {
                        Toast.makeText(getContext(), " انت تمتلك هذه الاغنية بلفعل ", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void share() {
                        Log.d(TAG, "share:");
                        SWStaticMethods.shareApp(getActivity());
                    }

                    @Override
                    public void addToFavorite() {
                        Log.d(TAG, "addToFavorite: ");
                        addTofavorite(String.valueOf(phoneModel.getId()));
                    }
                });
                bottomSheetMore.openDialog();
            }
        }));
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


    public void addsong(String songid, String playlistid) {
        playlsitViewModel.addSong(songid, playlistid).observe(getViewLifecycleOwner(), s -> {
            if (s != null) {
                Log.d("TAG", "onChanged: " + s);

            } else {
                Log.d("TAG", "onChanged: no data");
            }
        });
    }


    public ArrayList<Playlist> getAllplaylist() {
        Log.d(TAG, "getAllplaylist: ");
        playlsitViewModel.getAllPlayListLiveData().observe(getViewLifecycleOwner(), new Observer<Data>() {
            @Override
            public void onChanged(Data getAllPlayList) {
                if (getAllPlayList != null) {
                    getPlaylistName.clear();
                    for (int i = 0; i < getAllPlayList.getPlaylists().size(); i++) {
                        getPlaylistName.add(getAllPlayList.getPlaylists().get(i));
                        Log.d("TAG", "onChanged getAllplaylist: " + getAllPlayList.getPlaylists().get(i).getName());
                    }
                    Log.d(TAG, "onChanged: " + getPlaylistName.size());

                } else {
                    Log.d("TAG", "onChanged: no data");
                }

            }
        });
        return getPlaylistName;
    }


    public void createPLayList(String name) {
        playlsitViewModel.creatPlaylist(name).observe(getActivity(), createPlayList -> {
            if (createPlayList != null) {
                Log.d(TAG, "onChanged: " + createPlayList.getPlaylist().getName());
                Log.d(TAG, "onChanged: ");
            } else {
                Log.d(TAG, "onChanged:  no data");
            }
        });
    }


    public void addSong(String songid, String playlsiyid) {
        playlsitViewModel.addSong(songid, playlsiyid).observe(getViewLifecycleOwner(), s -> {

            if (s != null) {

                Log.d(TAG, "onChanged: '" + s);


            } else {
                Log.d(TAG, "onChanged: no data");
            }
        });
    }

    public void addTofavorite(String songid) {
        favoiteViewModel.addSong(songid).observe(getViewLifecycleOwner(), s -> {
            if (s != null) {
                Log.d(TAG, "onChanged: " + s);

            } else {
                Log.d(TAG, "onChanged: no data");
            }
        });
    }

}