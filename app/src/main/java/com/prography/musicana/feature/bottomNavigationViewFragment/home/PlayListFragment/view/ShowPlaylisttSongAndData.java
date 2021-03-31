package com.prography.musicana.feature.bottomNavigationViewFragment.home.PlayListFragment.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.bluetooth.BluetoothGattDescriptor;
import android.os.Bundle;
import android.util.Log;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.prography.musicana.R;
import com.prography.musicana.custem.BottomSheetplaylistSongMore;
import com.prography.musicana.databinding.ActivityShowPlaylisttSongAndDataBinding;
import com.prography.musicana.feature.bottomNavigationViewFragment.home.PlayListFragment.adapter.PlayListSongAdapter;
import com.prography.musicana.feature.bottomNavigationViewFragment.home.PlayListFragment.model.deletesong.DeleteSongFromPLaylsit;
import com.prography.musicana.feature.bottomNavigationViewFragment.home.PlayListFragment.model.getallplaylist.Playlist;
import com.prography.musicana.feature.bottomNavigationViewFragment.home.PlayListFragment.model.viewallsongtoplaylist.Datum;
import com.prography.musicana.feature.bottomNavigationViewFragment.home.PlayListFragment.model.viewallsongtoplaylist.ViewAllSongToPlaylist;
import com.prography.musicana.feature.bottomNavigationViewFragment.home.PlayListFragment.viewmodel.PlaylsitViewModel;

import java.util.ArrayList;

public class ShowPlaylisttSongAndData extends AppCompatActivity {

    private ActivityShowPlaylisttSongAndDataBinding binding;
    private final static String TAG = ShowPlaylisttSongAndData.class.getSimpleName();
    private PlaylsitViewModel playlsitViewModel;
    private ArrayList<Datum> items;
    private PlayListSongAdapter playListSongAdapter;
    private String id;
    private BottomSheetplaylistSongMore bottomSheetplaylistSongMore;
    private BottomSheetDialog bottomSheetDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShowPlaylisttSongAndDataBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        id = getDataintent();
        bottomSheetDialog = new BottomSheetDialog(this);
        playlsitViewModel = new ViewModelProvider(this).get(PlaylsitViewModel.class);
        items = new ArrayList<>();
        playListSongAdapter = new PlayListSongAdapter(items, new PlayListSongAdapter.ItemsSongClicked() {
            @Override
            public void clickedSongRun(int position, Datum data) {


            }

            @Override
            public void more(Datum data) {
                bottomSheetplaylistSongMore = new BottomSheetplaylistSongMore(ShowPlaylisttSongAndData.this, bottomSheetDialog, new BottomSheetplaylistSongMore.BottomSheetPlaylistSongMoreMethode() {
                    @Override
                    public void delete() {
                        Log.d(TAG, "delete: ");
//                        deleteSong(data.getId(), id);
                    }

                    @Override
                    public void share() {
                        Log.d(TAG, "share: ");
                    }

                    @Override
                    public void addToFavorite() {
                        Log.d(TAG, "addToFavorite: ");
                    }
                });
                bottomSheetplaylistSongMore.openDialog();
            }
        });
        binding.rvChannelDataList.setAdapter(playListSongAdapter);
        binding.rvChannelDataList.setLayoutManager(new LinearLayoutManager(this));
        getlsitSongtoplaylist(id);
    }

    public String getDataintent() {

        String id = "";
        if (getIntent() != null) {
            Bundle bundle = getIntent().getBundleExtra("data");
            Playlist playlist = (Playlist) bundle.getSerializable("playlist");
            id = playlist.getId();
            Log.d(TAG, "onCreate: " + playlist.getName());
            binding.playlistname.setText(playlist.getName());

        }
        return id;
    }

    public void getlsitSongtoplaylist(String playlistid) {
        playlsitViewModel.getAllsongtoplaylist(playlistid).observe(this, new Observer<ViewAllSongToPlaylist>() {
            @Override
            public void onChanged(ViewAllSongToPlaylist viewAllSongToPlaylist) {
                if (viewAllSongToPlaylist != null) {
                    for (int i = 0; i < viewAllSongToPlaylist.getResponse().getData().size(); i++) {

                        items.add(viewAllSongToPlaylist.getResponse().getData().get(i));
                        Log.d(TAG, "onChanged: " + viewAllSongToPlaylist.getResponse().getData().get(i).getName());
                    }
                    binding.rvChannelDataList.setLayoutManager(new LinearLayoutManager(ShowPlaylisttSongAndData.this));
                    binding.rvChannelDataList.setAdapter(playListSongAdapter);
                    playListSongAdapter.notifyDataSetChanged();


                } else {
                    Log.d(TAG, "onChanged: no data");
                }
            }
        });
    }

    public void deleteSong(String songid, String listid) {
        playlsitViewModel.deleteSong(songid, listid).observe(this, new Observer<DeleteSongFromPLaylsit>() {
            @Override
            public void onChanged(DeleteSongFromPLaylsit deleteSongFromPLaylsit) {
                if (deleteSongFromPLaylsit != null) {
                    Log.d(TAG, "onChanged:" + deleteSongFromPLaylsit.getResponse().getMassage());
                } else {
                    Log.d(TAG, "onChanged:  no data");
                }
            }
        });
    }
}