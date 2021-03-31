package com.prography.musicana.repo;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.prography.musicana.model.AddSongToFavorite;
import com.prography.musicana.network.NetworkInit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavoritePresenter {

    private static final String TAG = FavoritePresenter.class.getSimpleName();

    private NetworkInit networkInit;
    MutableLiveData<AddSongToFavorite> addSongToFavoriteMutableLiveData;
    private static FavoritePresenter instance;


    public FavoritePresenter() {
        networkInit = NetworkInit.getInstance(true);
        addSongToFavoriteMutableLiveData = new MutableLiveData<>();

    }

    public static FavoritePresenter getInstance() {
        if (instance == null) {
            instance = new FavoritePresenter();
        }
        return instance;
    }

    public LiveData<AddSongToFavorite> addSongToFavorite(String songid) {
        networkInit.getRetrofitApis().addToFavorite(songid).enqueue(new Callback<AddSongToFavorite>() {
            @Override
            public void onResponse(Call<AddSongToFavorite> call, Response<AddSongToFavorite> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: " + response.body().getResponse());
                    addSongToFavoriteMutableLiveData.setValue(response.body());
                } else {
                    Log.d(TAG, "onResponse:  no data");
                    addSongToFavoriteMutableLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<AddSongToFavorite> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                addSongToFavoriteMutableLiveData.setValue(null);
            }
        });
        return addSongToFavoriteMutableLiveData;
    }
}

