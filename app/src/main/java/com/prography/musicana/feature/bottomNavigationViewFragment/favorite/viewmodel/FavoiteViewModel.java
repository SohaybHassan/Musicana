package com.prography.musicana.feature.bottomNavigationViewFragment.favorite.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.prography.musicana.feature.bottomNavigationViewFragment.favorite.model.AddSongToFavorite;
import com.prography.musicana.feature.bottomNavigationViewFragment.favorite.presenter.FavoritePresenter;

public class FavoiteViewModel extends AndroidViewModel {
    FavoritePresenter favoritePresenter;

    public FavoiteViewModel(@NonNull Application application) {
        super(application);
        favoritePresenter = FavoritePresenter.getInstance();
    }


    public LiveData<AddSongToFavorite> addSong(String songid) {

        return favoritePresenter.addSongToFavorite(songid);
    }
}
