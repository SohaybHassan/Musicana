package com.prography.musicana.repo;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.prography.musicana.data.changestatus.ChangeStatusData;
import com.prography.musicana.data.newstatus.NewStatusData;
import com.prography.musicana.model.DataModel;
import com.prography.musicana.network.NetworkInit;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Type;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StatusPresenter {

    private static StatusPresenter instenc;
    private NetworkInit networkInit;
    public static final String TAG = StatusPresenter.class.getSimpleName();
    private MutableLiveData<NewStatusData> newStatusMutableLiveData;
    private MutableLiveData<String> closeStatusMutableLiveData;
    private MutableLiveData<ChangeStatusData> changeStatusMutableLiveData;

    private StatusPresenter() {
        networkInit = NetworkInit.getInstance(true);
        newStatusMutableLiveData = new MutableLiveData<>();
        closeStatusMutableLiveData = new MutableLiveData<>();
        changeStatusMutableLiveData = new MutableLiveData<>();
    }

    public static StatusPresenter getInstenc() {
        if (instenc == null) {

            instenc = new StatusPresenter();
        }
        return instenc;
    }


    public LiveData<NewStatusData> setnewStatus(String uuid) {

        networkInit.getRetrofitApis().newStatus(uuid).enqueue(new Callback<DataModel>() {
            @Override
            public void onResponse(@NotNull Call<DataModel> call, @NotNull Response<DataModel> response) {
                if (response.isSuccessful()) {
                    Gson gson = new Gson();
                    Type type = new TypeToken<NewStatusData>() {
                    }.getType();
                    NewStatusData data = gson.fromJson(gson.toJson(response.body().getResponse().getData()), type);
                    Log.d(TAG, "onResponse: " + data.getStatus().getStatus());
                    newStatusMutableLiveData.setValue(data);
                } else {
                    Log.d(TAG, "onResponse:  no data");
                    newStatusMutableLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(@NotNull Call<DataModel> call, @NotNull Throwable t) {
                Log.d(TAG, "onFailure:  no data" + t.getMessage());
                newStatusMutableLiveData.setValue(null);
            }
        });
        return newStatusMutableLiveData;
    }

    public LiveData<ChangeStatusData> setChangeStatus(String status_to) {
        networkInit.getRetrofitApis().changeStatus(status_to).enqueue(new Callback<DataModel>() {
            @Override
            public void onResponse(@NotNull Call<DataModel> call, @NotNull Response<DataModel> response) {
                if (response.isSuccessful()) {
                    Gson gson = new Gson();
                    Type type = new TypeToken<ChangeStatusData>() {
                    }.getType();
                    ChangeStatusData data = gson.fromJson(gson.toJson(response.body().getResponse().getData()), type);
                    Log.d(TAG, "onResponse: " + data.getActiveStatus().getStatus());
                    changeStatusMutableLiveData.setValue(data);
                } else {
                    Log.d(TAG, "onResponse:  new Data");
                    changeStatusMutableLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(@NotNull Call<DataModel> call, @NotNull Throwable t) {
                Log.d(TAG, "onFailure:  new Data" + t.getMessage());
                changeStatusMutableLiveData.setValue(null);
            }
        });
        return changeStatusMutableLiveData;
    }

    public LiveData<String> setCloseStatus() {
        networkInit.getRetrofitApis().colseStatus().enqueue(new Callback<DataModel>() {
            @Override
            public void onResponse(Call<DataModel> call, Response<DataModel> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: " + response.body().getResponse().getMessage());
                    closeStatusMutableLiveData.setValue(response.body().getResponse().getMessage());
                } else {
                    Log.d(TAG, "onResponse: new data");
                    closeStatusMutableLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<DataModel> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                closeStatusMutableLiveData.setValue(null);
            }
        });
        return closeStatusMutableLiveData;
    }


}
