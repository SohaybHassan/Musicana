package com.prography.musicana.repo;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.prography.musicana.model.AddSongToFavorite;
import com.prography.musicana.model.DataModel;
import com.prography.musicana.network.NetworkInit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavoritePresenter {

    private static final String TAG = FavoritePresenter.class.getSimpleName();

    private NetworkInit networkInit;
    MutableLiveData<String> addSongToFavoriteMutableLiveData;
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

    public LiveData<String> addSongToFavorite(String songid) {
        networkInit.getRetrofitApis().addToFavorite(songid).enqueue(new Callback<DataModel>() {
            @Override
            public void onResponse(Call<DataModel> call, Response<DataModel> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: " + response.body().getResponse().getMessage());
                    addSongToFavoriteMutableLiveData.setValue(response.body().getResponse().getMessage());
                } else {
                    Log.d(TAG, "onResponse:  no data");
                    addSongToFavoriteMutableLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<DataModel> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                addSongToFavoriteMutableLiveData.setValue(null);
            }
        });
        return addSongToFavoriteMutableLiveData;
    }
}

