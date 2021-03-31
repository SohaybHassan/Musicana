package com.prography.musicana.repo;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.prography.musicana.data.changestatus.ChangeStatus;
import com.prography.musicana.data.closestatus.CloseStatus;
import com.prography.musicana.data.newstatus.NewStatus;
import com.prography.musicana.network.NetworkInit;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StatusPresenter {

    private static StatusPresenter instenc;
    private NetworkInit networkInit;
    public static final String TAG = StatusPresenter.class.getSimpleName();
    private MutableLiveData<NewStatus> newStatusMutableLiveData;
    private MutableLiveData<CloseStatus> closeStatusMutableLiveData;
    private MutableLiveData<ChangeStatus> changeStatusMutableLiveData;

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


    public LiveData<NewStatus> setnewStatus(String uuid) {

        networkInit.getRetrofitApis().newStatus(uuid).enqueue(new Callback<NewStatus>() {
            @Override
            public void onResponse(@NotNull Call<NewStatus> call, @NotNull Response<NewStatus> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: " + response.body().getResponse().getData().getStatus().getStatus());
                    newStatusMutableLiveData.setValue(response.body());
                } else {
                    Log.d(TAG, "onResponse:  no data");
                    newStatusMutableLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(@NotNull Call<NewStatus> call, @NotNull Throwable t) {
                Log.d(TAG, "onFailure:  no data" + t.getMessage());
                newStatusMutableLiveData.setValue(null);
            }
        });
        return newStatusMutableLiveData;
    }

    public LiveData<ChangeStatus> setChangeStatus(String status_to) {
        networkInit.getRetrofitApis().changeStatus(status_to).enqueue(new Callback<ChangeStatus>() {
            @Override
            public void onResponse(@NotNull Call<ChangeStatus> call, @NotNull Response<ChangeStatus> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: " + response.body().getResponse().getData().getActiveStatus().getStatus());
                    changeStatusMutableLiveData.setValue(response.body());
                } else {
                    Log.d(TAG, "onResponse:  new Data");
                    changeStatusMutableLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(@NotNull Call<ChangeStatus> call, @NotNull Throwable t) {
                Log.d(TAG, "onFailure:  new Data" + t.getMessage());
                changeStatusMutableLiveData.setValue(null);
            }
        });
        return changeStatusMutableLiveData;
    }

    public LiveData<CloseStatus> setCloseStatus() {
        networkInit.getRetrofitApis().colseStatus().enqueue(new Callback<CloseStatus>() {
            @Override
            public void onResponse(Call<CloseStatus> call, Response<CloseStatus> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: " + response.body().getResponse().getMessage());
                    closeStatusMutableLiveData.setValue(response.body());
                } else {
                    Log.d(TAG, "onResponse: new data");
                    closeStatusMutableLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<CloseStatus> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                closeStatusMutableLiveData.setValue(null);
            }
        });
        return closeStatusMutableLiveData;
    }


}
