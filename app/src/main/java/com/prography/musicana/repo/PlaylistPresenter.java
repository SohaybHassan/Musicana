package com.prography.musicana.repo;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.prography.musicana.data.addsongtoplaylist.AddSongToPlayList;
import com.prography.musicana.data.createplaylist.CreatePlayList;
import com.prography.musicana.data.deletesong.DeleteSongFromPLaylsit;
import com.prography.musicana.data.getallplaylist.GetAllPlayList;
import com.prography.musicana.data.viewallsongtoplaylist.ViewAllSongToPlaylist;
import com.prography.musicana.network.NetworkInit;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlaylistPresenter {

    private static final String TAG = PlaylistPresenter.class.getSimpleName();
    private NetworkInit networkInit;
    MutableLiveData<CreatePlayList> createPlayListMutableLiveData;
    MutableLiveData<GetAllPlayList> getAllPlayListMutableLiveData;
    MutableLiveData<AddSongToPlayList> addSongToPlayListMutableLiveData;
    MutableLiveData<ViewAllSongToPlaylist> viewAllSongToPlaylistMutableLiveData;
    MutableLiveData<DeleteSongFromPLaylsit> deleteSongFromPLaylsitMutableLiveData;
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

    public LiveData<CreatePlayList> createPlaylsit(String plsylistName) {

        networkInit.getRetrofitApis().createPlaylist(plsylistName).enqueue(new Callback<CreatePlayList>() {
            @Override
            public void onResponse(Call<CreatePlayList> call, Response<CreatePlayList> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: " + response.body().getResponse().getData().getPlaylist().getName());
                    createPlayListMutableLiveData.setValue(response.body());
                } else {
                    Log.d(TAG, "onResponse:  no data");
                    createPlayListMutableLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<CreatePlayList> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                createPlayListMutableLiveData.setValue(null);
            }
        });
        return createPlayListMutableLiveData;
    }

    public LiveData<GetAllPlayList> getAllPlaylsit() {
        networkInit.getRetrofitApis().getAllPlayList().enqueue(new Callback<GetAllPlayList>() {
            @Override
            public void onResponse(Call<GetAllPlayList> call, Response<GetAllPlayList> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: " + response.body().getResponse().getData().getPlaylists().get(0).getName());
                    getAllPlayListMutableLiveData.setValue(response.body());

                } else {
                    Log.d(TAG, "onResponse:  no data");
                    getAllPlayListMutableLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<GetAllPlayList> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                getAllPlayListMutableLiveData.setValue(null);

            }
        });

        return getAllPlayListMutableLiveData;
    }

    public LiveData<AddSongToPlayList> addSong(String songID, String playlistID) {
        networkInit.getRetrofitApis().addToPlayList(songID, playlistID).enqueue(new Callback<AddSongToPlayList>() {
            @Override
            public void onResponse(@NotNull Call<AddSongToPlayList> call, @NotNull Response<AddSongToPlayList> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: " + response.body().getResponse().getMessage());
                    addSongToPlayListMutableLiveData.setValue(response.body());
                } else {
                    Log.d(TAG, "onResponse:  no data");
                    addSongToPlayListMutableLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<AddSongToPlayList> call, @NotNull Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                addSongToPlayListMutableLiveData.setValue(null);
            }
        });

        return addSongToPlayListMutableLiveData;

    }

    public LiveData<ViewAllSongToPlaylist> showAllSong(String playlistid) {
        networkInit.getRetrofitApis().ViewPliatListSong(playlistid).enqueue(new Callback<ViewAllSongToPlaylist>() {
            @Override
            public void onResponse(Call<ViewAllSongToPlaylist> call, Response<ViewAllSongToPlaylist> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: " + response.body().getResponse().getData().get(0).getName());
                    viewAllSongToPlaylistMutableLiveData.setValue(response.body());
                } else {
                    Log.d(TAG, "onResponse:  no data");
                    viewAllSongToPlaylistMutableLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<ViewAllSongToPlaylist> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                viewAllSongToPlaylistMutableLiveData.setValue(null);
            }
        });
        return viewAllSongToPlaylistMutableLiveData;
    }

    public LiveData<DeleteSongFromPLaylsit> DeleteSong(String songid, String listid) {
        networkInit.getRetrofitApis().deleteSong(songid, listid).enqueue(new Callback<DeleteSongFromPLaylsit>() {
            @Override
            public void onResponse(Call<DeleteSongFromPLaylsit> call, Response<DeleteSongFromPLaylsit> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: " + response.body().getResponse().getMassage());
                    deleteSongFromPLaylsitMutableLiveData.setValue(response.body());
                } else {
                    Log.d(TAG, "onResponse:  no data");
                    deleteSongFromPLaylsitMutableLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<DeleteSongFromPLaylsit> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                deleteSongFromPLaylsitMutableLiveData.setValue(null);
            }
        });

        return deleteSongFromPLaylsitMutableLiveData;
    }
}
