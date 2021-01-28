package com.prography.musicana.feature.bottomNavigationViewFragment.profile.presenter;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.prography.musicana.feature.bottomNavigationViewFragment.profile.model.logout.Logout;
import com.prography.musicana.network.NetworkInit;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilePresenter {

    private static final String TAG = ProfilePresenter.class.getSimpleName();
    private NetworkInit networkInit;
    private MutableLiveData<Logout> logoutMutableLiveData;
    private static ProfilePresenter instance;


    public ProfilePresenter() {
        networkInit = NetworkInit.getInstance(true);
        Log.d(TAG, "ProfilePresenter:  11111111111111111111111111111111111111111111111111111111111111 ");
        logoutMutableLiveData = new MutableLiveData<>();
    }

    public static ProfilePresenter getInstance() {
        if (instance == null) {
            instance = new ProfilePresenter();
        }
        return instance;
    }


    public LiveData<Logout> logoutUser() {

        networkInit.getRetrofitApis().logout().enqueue(new Callback<Logout>() {
            @Override
            public void onResponse(@NotNull Call<Logout> call, @NotNull Response<Logout> response) {

                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: " + response.body().getResponse().getMessage());

                    logoutMutableLiveData.setValue(response.body());
                } else {
                    logoutMutableLiveData.setValue(null);
                    Log.d(TAG, "onResponse:  some thing wrong");
                }
            }

            @Override
            public void onFailure(@NotNull Call<Logout> call, @NotNull Throwable t) {
                logoutMutableLiveData.setValue(null);
                Log.d(TAG, "onResponse:  some thing wrong : " + t.getMessage());
            }
        });
        return logoutMutableLiveData;
    }


}
