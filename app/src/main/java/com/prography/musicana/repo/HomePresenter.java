package com.prography.musicana.repo;

import android.util.Log;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.prography.musicana.data.home.HomeData;
import com.prography.musicana.data.search.SearchData;
import com.prography.musicana.data.search.SearchMolde;
import com.prography.musicana.model.DataModel;
import com.prography.musicana.network.NetworkInit;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Type;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePresenter {
    private static final String TAG = HomePresenter.class.getSimpleName();
    private static HomePresenter mInstance;
    private NetworkInit networkInit;

    //home page
    MutableLiveData<HomeData> homeModelMutableLiveData;
    //search
    MutableLiveData<SearchData> searchMoldeMutableLiveData;

    //playlist

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

    public LiveData<HomeData> getHomeData(String vPage, String cPage) {
        networkInit.getRetrofitApis().homeData(vPage, cPage).enqueue(new Callback<DataModel>() {
            @Override
            public void onResponse(@NotNull Call<DataModel> call, Response<DataModel> response) {
                if (response.isSuccessful()) {
                    Gson gson = new Gson();
                    Type type = new TypeToken<HomeData>() {
                    }.getType();
                    HomeData homeData = gson.fromJson(gson.toJson(response.body().getResponse().getData()), type);
                    Log.d(TAG, "onResponse: " + homeData.getVideos().get(0).getId());
                    homeModelMutableLiveData.setValue(homeData);
                } else {
                    Log.d(TAG, "onResponse: no data");
                    homeModelMutableLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(@NotNull Call<DataModel> call, @NotNull Throwable t) {

                Log.d(TAG, "onFailure: " + t.getMessage());
                homeModelMutableLiveData.setValue(null);
            }
        });


        return homeModelMutableLiveData;
    }


    public LiveData<SearchData> getDataSearch(String q, String nextPage) {

        networkInit.getRetrofitApis().search(q, nextPage).enqueue(new Callback<DataModel>() {
            @Override
            public void onResponse(Call<DataModel> call, Response<DataModel> response) {
                if (response.isSuccessful()) {
                    Gson gson = new Gson();
                    Type type = new TypeToken<SearchData>() {
                    }.getType();
                    SearchData data = gson.fromJson(gson.toJson(response.body().getResponse().getData()), type);
                    Log.d(TAG, "onResponse: " + data.getResults().get(0).getName());

                    searchMoldeMutableLiveData.setValue(data);
                } else {
                    searchMoldeMutableLiveData.setValue(null);
                    Log.d(TAG, "onResponse: no data");
                }
            }

            @Override
            public void onFailure(Call<DataModel> call, Throwable t) {

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
