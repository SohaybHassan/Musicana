package com.prography.musicana.repo;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.prography.musicana.data.createplaylist.PlaylistData;
import com.prography.musicana.data.getallplaylist.Data;
import com.prography.musicana.data.PlaylistSongData;
import com.prography.musicana.model.DataModel;
import com.prography.musicana.network.NetworkInit;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Type;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlaylistPresenter {

    private static final String TAG = PlaylistPresenter.class.getSimpleName();
    private NetworkInit networkInit;
    MutableLiveData<PlaylistData> createPlayListMutableLiveData;
    MutableLiveData<Data> getAllPlayListMutableLiveData;
    MutableLiveData<String> addSongToPlayListMutableLiveData;
    MutableLiveData<ArrayList<PlaylistSongData>> viewAllSongToPlaylistMutableLiveData;
    MutableLiveData<String> deleteSongFromPLaylsitMutableLiveData;
    private static PlaylistPresenter instance;


    public PlaylistPresenter() {

        networkInit = NetworkInit.getInstance(true);
        createPlayListMutableLiveData = new MutableLiveData<>();
        getAllPlayListMutableLiveData = new MutableLiveData<>();
        addSongToPlayListMutableLiveData = new MutableLiveData<>();
        viewAllSongToPlaylistMutableLiveData = new MutableLiveData<>();
        deleteSongFromPLaylsitMutableLiveData = new MutableLiveData<>();

    }

    public static PlaylistPresenter getInstance() {
        if (instance == null) {
            instance = new PlaylistPresenter();
        }
        return instance;
    }

    public LiveData<PlaylistData> createPlaylsit(String plsylistName) {

        networkInit.getRetrofitApis().createPlaylist(plsylistName).enqueue(new Callback<DataModel>() {
            @Override
            public void onResponse(Call<DataModel> call, Response<DataModel> response) {
                if (response.isSuccessful()) {
                    Gson gson = new Gson();
                    Type type = new TypeToken<PlaylistData>() {
                    }.getType();
                    PlaylistData data = gson.fromJson(gson.toJson(response.body().getResponse().getData()), type);
                    Log.d(TAG, "onResponse: " + data.getPlaylist().getName());
                    createPlayListMutableLiveData.setValue(data);
                } else {
                    Log.d(TAG, "onResponse:  no data");
                    createPlayListMutableLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<DataModel> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                createPlayListMutableLiveData.setValue(null);
            }
        });
        return createPlayListMutableLiveData;
    }

    public LiveData<Data> getAllPlaylsit() {
        networkInit.getRetrofitApis().getAllPlayList().enqueue(new Callback<DataModel>() {
            @Override
            public void onResponse(Call<DataModel> call, Response<DataModel> response) {
                if (response.isSuccessful()) {
                    Gson gson = new Gson();
                    Type type = new TypeToken<Data>() {
                    }.getType();
                    Data data = gson.fromJson(gson.toJson(response.body().getResponse().getData()), type);
                    Log.d(TAG, "onResponse: " + data.getPlaylists().get(0).getName());
                    getAllPlayListMutableLiveData.setValue(data);

                } else {
                    Log.d(TAG, "onResponse:  no data");
                    getAllPlayListMutableLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<DataModel> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                getAllPlayListMutableLiveData.setValue(null);

            }
        });

        return getAllPlayListMutableLiveData;
    }

    public LiveData<String> addSong(String songID, String playlistID) {
        networkInit.getRetrofitApis().addToPlayList(songID, playlistID).enqueue(new Callback<DataModel>() {
            @Override
            public void onResponse(@NotNull Call<DataModel> call, @NotNull Response<DataModel> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: " + response.body().getResponse().getMessage());
                    addSongToPlayListMutableLiveData.setValue(response.body().getResponse().getMessage());
                } else {
                    Log.d(TAG, "onResponse:  no data");
                    addSongToPlayListMutableLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<DataModel> call, @NotNull Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                addSongToPlayListMutableLiveData.setValue(null);
            }
        });

        return addSongToPlayListMutableLiveData;

    }

    public LiveData<ArrayList<PlaylistSongData>> showAllSong(String playlistid) {
        networkInit.getRetrofitApis().ViewPliatListSong(playlistid).enqueue(new Callback<DataModel>() {
            @Override
            public void onResponse(Call<DataModel> call, Response<DataModel> response) {
                if (response.isSuccessful()) {
                    Gson gson = new Gson();
                    Type type = new TypeToken<ArrayList<PlaylistSongData>>() {
                    }.getType();
                    ArrayList<PlaylistSongData> data = gson.fromJson(gson.toJson(response.body().getResponse().getData()), type);

                    Log.d(TAG, "onResponse: " + data.get(0).getName());
                    viewAllSongToPlaylistMutableLiveData.setValue(data);
                } else {
                    Log.d(TAG, "onResponse:  no data");
                    viewAllSongToPlaylistMutableLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<DataModel> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                viewAllSongToPlaylistMutableLiveData.setValue(null);
            }
        });
        return viewAllSongToPlaylistMutableLiveData;
    }

    public LiveData<String> DeleteSong(String songid, String listid) {
        networkInit.getRetrofitApis().deleteSong(songid, listid).enqueue(new Callback<DataModel>() {
            @Override
            public void onResponse(Call<DataModel> call, Response<DataModel> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: " + response.body().getResponse().getMessage());
                    deleteSongFromPLaylsitMutableLiveData.setValue(response.body().getResponse().getMessage());
                } else {
                    Log.d(TAG, "onResponse:  no data");
                    deleteSongFromPLaylsitMutableLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<DataModel> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                deleteSongFromPLaylsitMutableLiveData.setValue(null);
            }
        });

        return deleteSongFromPLaylsitMutableLiveData;
    }
}
