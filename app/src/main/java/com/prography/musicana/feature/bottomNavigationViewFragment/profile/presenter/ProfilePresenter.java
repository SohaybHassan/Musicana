package com.prography.musicana.feature.bottomNavigationViewFragment.profile.presenter;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.prography.musicana.feature.bottomNavigationViewFragment.profile.model.logout.Logout;
import com.prography.musicana.feature.bottomNavigationViewFragment.profile.model.profiledata.ProfileData;
import com.prography.musicana.network.NetworkInit;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilePresenter {

    private static final String TAG = ProfilePresenter.class.getSimpleName();
    private NetworkInit networkInit;
    private MutableLiveData<Logout> logoutMutableLiveData;
    private MutableLiveData<ProfileData> profileDataMutableLiveData;

    private static ProfilePresenter instance;


    public ProfilePresenter() {
        networkInit = NetworkInit.getInstance(true);

        logoutMutableLiveData = new MutableLiveData<>();
        profileDataMutableLiveData = new MutableLiveData<>();
    }

    public static ProfilePresenter getInstance() {
        if (instance == null) {
            instance = new ProfilePresenter();
        }
        return instance;
    }


    public LiveData<ProfileData> getProfiledata() {
        networkInit.getRetrofitApis().getProfileData().enqueue(new Callback<ProfileData>() {
            @Override
            public void onResponse(Call<ProfileData> call, Response<ProfileData> response) {
                if (response.isSuccessful()) {

                    Log.d(TAG, "onResponse: " + response.body().getResponse().getData().getUser().getFirstname());
                    profileDataMutableLiveData.setValue(response.body());
                } else {
                    Log.d(TAG, "onResponse: nodata");
                    profileDataMutableLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<ProfileData> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                profileDataMutableLiveData.setValue(null);
            }
        });
        return profileDataMutableLiveData;
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
