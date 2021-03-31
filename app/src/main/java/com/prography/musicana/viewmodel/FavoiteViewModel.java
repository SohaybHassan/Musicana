package com.prography.musicana.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.prography.musicana.repo.FavoritePresenter;

public class FavoiteViewModel extends AndroidViewModel {
    FavoritePresenter favoritePresenter;

    public FavoiteViewModel(@NonNull Application application) {
        super(application);
        favoritePresenter = FavoritePresenter.getInstance();
    }


    public LiveData<String> addSong(String songid) {

        return favoritePresenter.addSongToFavorite(songid);
    }
}
