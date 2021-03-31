package com.prography.musicana.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.prography.musicana.data.createplaylist.CreatePlayList;
import com.prography.musicana.data.getallplaylist.Data;
import com.prography.musicana.data.getallplaylist.GetAllPlayList;
import com.prography.musicana.data.PlaylistSongData;
import com.prography.musicana.repo.PlaylistPresenter;

import java.util.ArrayList;

public class PlaylsitViewModel extends AndroidViewModel {

    PlaylistPresenter playlistPresenter;


    public PlaylsitViewModel(@NonNull Application application) {
        super(application);
        playlistPresenter = PlaylistPresenter.getInstance();

    }


    public LiveData<CreatePlayList> creatPlaylist(String playlistName) {
        return playlistPresenter.createPlaylsit(playlistName);
    }

    public LiveData<Data> getAllPlayListLiveData() {
        return playlistPresenter.getAllPlaylsit();
    }

    public LiveData<String> addSong(String songID, String playlistID) {
        return playlistPresenter.addSong(songID, playlistID);
    }

    public LiveData<ArrayList<PlaylistSongData>> getAllsongtoplaylist(String playllistid) {
        return playlistPresenter.showAllSong(playllistid);
    }

    public LiveData<String> deleteSong(String songid, String listid) {
        return playlistPresenter.DeleteSong(songid, listid);
    }
}
