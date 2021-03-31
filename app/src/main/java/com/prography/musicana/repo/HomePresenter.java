package com.prography.musicana.repo;

import android.util.Log;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.prography.musicana.data.home.HomeModel;
import com.prography.musicana.data.createplaylist.CreatePlayList;
import com.prography.musicana.data.search.SearchMolde;
import com.prography.musicana.network.NetworkInit;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePresenter {
    private static final String TAG = HomePresenter.class.getSimpleName();
    private static HomePresenter mInstance;
    private NetworkInit networkInit;

    //home page
    MutableLiveData<HomeModel> homeModelMutableLiveData;
    //search
    MutableLiveData<SearchMolde> searchMoldeMutableLiveData;

    //playlist

    MutableLiveData<CreatePlayList> createPlayListMutableLiveData;
    public HomePresenter() {
        networkInit = NetworkInit.getInstance(true);
        //hone page
        homeModelMutableLiveData = new MutableLiveData<>();
        //Search page
        searchMoldeMutableLiveData = new MutableLiveData<>();

    }

    public static HomePresenter getInstance() {
        if (mInstance == null) {
            mInstance = new HomePresenter();
        }
        return mInstance;
    }

    public LiveData<HomeModel> getHomeData(String vPage, String cPage) {
        networkInit.getRetrofitApis().homeData(vPage, cPage).enqueue(new Callback<HomeModel>() {
            @Override
            public void onResponse(@NotNull Call<HomeModel> call, Response<HomeModel> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: " + response.body().getResponse().getData().getVideos().get(0).getId());
                    homeModelMutableLiveData.setValue(response.body());
                } else {
                    Log.d(TAG, "onResponse: no data");
                    homeModelMutableLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(@NotNull Call<HomeModel> call, @NotNull Throwable t) {

                Log.d(TAG, "onFailure: " + t.getMessage());
                homeModelMutableLiveData.setValue(null);
            }
        });


        return homeModelMutableLiveData;
    }


    public LiveData<SearchMolde> getDataSearch(String q, String nextPage) {

        networkInit.getRetrofitApis().search(q, nextPage).enqueue(new Callback<SearchMolde>() {
            @Override
            public void onResponse(Call<SearchMolde> call, Response<SearchMolde> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: " + response.body().getResponse().getData().getResults().get(0).getName());

                    searchMoldeMutableLiveData.setValue(response.body());
                } else {
                    searchMoldeMutableLiveData.setValue(null);
                    Log.d(TAG, "onResponse: no data");
                }
            }

            @Override
            public void onFailure(Call<SearchMolde> call, Throwable t) {

                Log.d(TAG, "onFailure: " + t.getMessage());
                searchMoldeMutableLiveData.setValue(null);
            }
        });


        return searchMoldeMutableLiveData;
    }

    public void removeObserver(LifecycleOwner owner) {
        searchMoldeMutableLiveData.removeObservers(owner);
    }
}
