package com.prography.musicana.feature.bottomNavigationViewFragment.home.PlayListFragment.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.prography.musicana.feature.bottomNavigationViewFragment.home.PlayListFragment.model.addsongtoplaylist.AddSongToPlayList;
import com.prography.musicana.feature.bottomNavigationViewFragment.home.PlayListFragment.model.createplaylist.CreatePlayList;
import com.prography.musicana.feature.bottomNavigationViewFragment.home.PlayListFragment.model.deletesong.DeleteSongFromPLaylsit;
import com.prography.musicana.feature.bottomNavigationViewFragment.home.PlayListFragment.model.getallplaylist.GetAllPlayList;
import com.prography.musicana.feature.bottomNavigationViewFragment.home.PlayListFragment.model.viewallsongtoplaylist.ViewAllSongToPlaylist;
import com.prography.musicana.feature.bottomNavigationViewFragment.home.PlayListFragment.presenter.PlaylistPresenter;

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
