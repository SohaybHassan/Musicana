package com.prography.musicana.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.prography.musicana.data.addsongtoplaylist.AddSongToPlayList;
import com.prography.musicana.data.createplaylist.CreatePlayList;
import com.prography.musicana.data.deletesong.DeleteSongFromPLaylsit;
import com.prography.musicana.data.getallplaylist.GetAllPlayList;
import com.prography.musicana.data.viewallsongtoplaylist.ViewAllSongToPlaylist;
import com.prography.musicana.repo.PlaylistPresenter;

public class PlaylsitViewModel extends AndroidViewModel {

    PlaylistPresenter playlistPresenter;


    public PlaylsitViewModel(@NonNull Application application) {
        super(application);
        playlistPresenter = PlaylistPresenter.getInstance();

    }


    public LiveData<CreatePlayList> creatPlaylist(String playlistName) {
        return playlistPresenter.createPlaylsit(playlistName);
    }

    public LiveData<GetAllPlayList> getAllPlayListLiveData() {
        return playlistPresenter.getAllPlaylsit();
    }

    public LiveData<AddSongToPlayList> addSong(String songID, String playlistID) {
        return playlistPresenter.addSong(songID, playlistID);
    }

    public LiveData<ViewAllSongToPlaylist> getAllsongtoplaylist(String playllistid) {
        return playlistPresenter.showAllSong(playllistid);
    }

    public LiveData<DeleteSongFromPLaylsit> deleteSong(String songid, String listid) {
        return playlistPresenter.DeleteSong(songid, listid);
    }
}
