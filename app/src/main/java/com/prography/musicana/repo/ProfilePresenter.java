package com.prography.musicana.repo;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.prography.musicana.data.DataSettings;
import com.prography.musicana.data.DataProfile;
import com.prography.musicana.data.DataUpdateProfile;
import com.prography.musicana.model.DataModel;
import com.prography.musicana.network.NetworkInit;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Type;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilePresenter {

    private static final String TAG = ProfilePresenter.class.getSimpleName();
    private NetworkInit networkInit;
    private MutableLiveData<String> logoutMutableLiveData;
    private MutableLiveData<DataProfile> profileDataMutableLiveData;
    private MutableLiveData<DataUpdateProfile> updateProfileResponseMutableLiveData;
    private MutableLiveData<DataSettings> settingsResponseMutableLiveData;

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


    public LiveData<DataProfile> getProfileData() {
        networkInit.getRetrofitApis().getProfileData().enqueue(new Callback<DataModel>() {
            @Override
            public void onResponse(Call<DataModel> call, Response<DataModel> response) {
                if (response.isSuccessful()) {
                    Gson gson = new Gson();
                    Type type = new TypeToken<DataProfile>() {
                    }.getType();
                    DataProfile data = gson.fromJson(gson.toJson(response.body().getResponse().getData()), type);
                    profileDataMutableLiveData.setValue(data);
                } else {
                    Log.d(TAG, "onResponse: nodata");
                    profileDataMutableLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<DataModel> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                profileDataMutableLiveData.setValue(null);
            }
        });
        return profileDataMutableLiveData;
    }

    public LiveData<String> logoutUser() {

        networkInit.getRetrofitApis().logout().enqueue(new Callback<DataModel>() {
            @Override
            public void onResponse(@NotNull Call<DataModel> call, @NotNull Response<DataModel> response) {

                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: " + response.body().getResponse().getMessage());

                    logoutMutableLiveData.setValue(response.body().getResponse().getMessage());
                } else {
                    logoutMutableLiveData.setValue(null);
                    Log.d(TAG, "onResponse:  some thing wrong");
                }
            }

            @Override
            public void onFailure(@NotNull Call<DataModel> call, @NotNull Throwable t) {
                logoutMutableLiveData.setValue(null);
                Log.d(TAG, "onResponse:  some thing wrong : " + t.getMessage());
            }
        });
        return logoutMutableLiveData;
    }

    public LiveData<DataUpdateProfile> updateProfile(RequestBody first_name, RequestBody middle_name, RequestBody last_name, RequestBody phone, RequestBody gender, RequestBody country, MultipartBody.Part image) {

        networkInit.getRetrofitApis().updateProfile(first_name, middle_name, last_name, phone, gender, country, image)
                .enqueue(new Callback<DataModel>() {
                    @Override
                    public void onResponse(@NotNull Call<DataModel> call, @NotNull Response<DataModel> response) {
                        if (response.isSuccessful()) {
                            Gson gson = new Gson();
                            Type type = new TypeToken<DataUpdateProfile>() {
                            }.getType();
                            DataUpdateProfile data = gson.fromJson(gson.toJson(response.body().getResponse().getData()), type);
                            updateProfileResponseMutableLiveData.setValue(data);
                        } else {
                            updateProfileResponseMutableLiveData.setValue(null);
                            Log.d(TAG, "onResponse:  some thing wrong");
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<DataModel> call, @NotNull Throwable t) {
                        updateProfileResponseMutableLiveData.setValue(null);
                        Log.d(TAG, "onResponse:  some thing wrong : " + t.getMessage());
                    }
                });
        return updateProfileResponseMutableLiveData;
    }

    public LiveData<DataSettings> getAllSettings() {
        networkInit.getRetrofitApis().getAllSettings().enqueue(new Callback<DataModel>() {
            @Override
            public void onResponse(Call<DataModel> call, Response<DataModel> response) {
                if (response.isSuccessful()) {
                    Gson gson = new Gson();
                    Type type = new TypeToken<DataSettings>() {
                    }.getType();
                    DataSettings data = gson.fromJson(gson.toJson(response.body().getResponse().getData()), type);
                    settingsResponseMutableLiveData.setValue(data);
                } else {
                    settingsResponseMutableLiveData.setValue(null);
                    Log.d(TAG, "onResponse:  some thing wrong");
                }
            }

            @Override
            public void onFailure(Call<DataModel> call, Throwable t) {
                settingsResponseMutableLiveData.setValue(null);
            }
        });
        return settingsResponseMutableLiveData;
    }

    public LiveData<DataSettings> changeSettings(String mood, String language, String additional_screen, String auto_update, String background, String audio, String location) {
        networkInit.getRetrofitApis().changeSettings(mood, language, additional_screen, auto_update, background, audio, location).enqueue(new Callback<DataModel>() {
            @Override
            public void onResponse(Call<DataModel> call, Response<DataModel> response) {
                if (response.isSuccessful()) {
                    Gson gson = new Gson();
                    Type type = new TypeToken<DataSettings>() {
                    }.getType();
                    DataSettings data = gson.fromJson(gson.toJson(response.body().getResponse().getData()), type);
                    settingsResponseMutableLiveData.setValue(data);
                } else {
                    settingsResponseMutableLiveData.setValue(null);
                    Log.d(TAG, "onResponse:  some thing wrong");
                }
            }

            @Override
            public void onFailure(Call<DataModel> call, Throwable t) {
                settingsResponseMutableLiveData.setValue(null);
            }
        });
        return settingsResponseMutableLiveData;
    }
}
