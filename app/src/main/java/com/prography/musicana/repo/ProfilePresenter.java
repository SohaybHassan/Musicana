package com.prography.musicana.repo;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.prography.musicana.data.allsettings.SettingsResponse;
import com.prography.musicana.data.logout.Logout;
import com.prography.musicana.data.profiledata.ProfileData;
import com.prography.musicana.data.updataprofile.UpdateProfileResponse;
import com.prography.musicana.network.NetworkInit;

import org.jetbrains.annotations.NotNull;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilePresenter {

    private static final String TAG = ProfilePresenter.class.getSimpleName();
    private NetworkInit networkInit;
    private MutableLiveData<Logout> logoutMutableLiveData;
    private MutableLiveData<ProfileData> profileDataMutableLiveData;
    private MutableLiveData<UpdateProfileResponse> updateProfileResponseMutableLiveData;
    private MutableLiveData<SettingsResponse> settingsResponseMutableLiveData;

    private static ProfilePresenter instance;


    public ProfilePresenter() {
        networkInit = NetworkInit.getInstance(true);

        logoutMutableLiveData = new MutableLiveData<>();
        profileDataMutableLiveData = new MutableLiveData<>();
        updateProfileResponseMutableLiveData = new MutableLiveData<>();
        settingsResponseMutableLiveData = new MutableLiveData<>();
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

    public LiveData<UpdateProfileResponse> updateProfile(RequestBody first_name, RequestBody middle_name, RequestBody last_name, RequestBody phone, RequestBody gender, RequestBody country, MultipartBody.Part image) {

        networkInit.getRetrofitApis().updateProfile(first_name, middle_name, last_name, phone, gender, country, image)
                .enqueue(new Callback<UpdateProfileResponse>() {
                    @Override
                    public void onResponse(@NotNull Call<UpdateProfileResponse> call, @NotNull Response<UpdateProfileResponse> response) {

                        if (response.isSuccessful()) {
                            Log.d(TAG, "onResponse: " + response.body().getResponse().getMessage());

                            updateProfileResponseMutableLiveData.setValue(response.body());
                        } else {
                            updateProfileResponseMutableLiveData.setValue(null);
                            Log.d(TAG, "onResponse:  some thing wrong");
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<UpdateProfileResponse> call, @NotNull Throwable t) {
                        updateProfileResponseMutableLiveData.setValue(null);
                        Log.d(TAG, "onResponse:  some thing wrong : " + t.getMessage());
                    }
                });
        return updateProfileResponseMutableLiveData;
    }

    public LiveData<SettingsResponse> getAllSettings() {
        networkInit.getRetrofitApis().getAllSettings().enqueue(new Callback<SettingsResponse>() {
            @Override
            public void onResponse(Call<SettingsResponse> call, Response<SettingsResponse> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: " + response.body().getResponse().getMessage());
                    settingsResponseMutableLiveData.setValue(response.body());
                } else {
                    settingsResponseMutableLiveData.setValue(null);
                    Log.d(TAG, "onResponse:  some thing wrong");
                }
            }

            @Override
            public void onFailure(Call<SettingsResponse> call, Throwable t) {
                settingsResponseMutableLiveData.setValue(null);
            }
        });
        return settingsResponseMutableLiveData;
    }

    public LiveData<SettingsResponse> changeSettings(String mood, String language, String additional_screen, String auto_update, String background, String audio, String location) {
        networkInit.getRetrofitApis().changeSettings(mood, language, additional_screen, auto_update, background, audio, location).enqueue(new Callback<SettingsResponse>() {
            @Override
            public void onResponse(Call<SettingsResponse> call, Response<SettingsResponse> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: " + response.body().getResponse().getMessage());
                    settingsResponseMutableLiveData.setValue(response.body());
                } else {
                    settingsResponseMutableLiveData.setValue(null);
                    Log.d(TAG, "onResponse:  some thing wrong");
                }
            }

            @Override
            public void onFailure(Call<SettingsResponse> call, Throwable t) {
                settingsResponseMutableLiveData.setValue(null);
            }
        });
        return settingsResponseMutableLiveData;
    }
}
