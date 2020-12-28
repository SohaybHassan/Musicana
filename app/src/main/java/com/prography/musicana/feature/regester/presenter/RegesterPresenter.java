package com.prography.musicana.feature.regester.presenter;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.google.gson.JsonObject;
import com.prography.musicana.feature.regester.model.RegesterModel;
import com.prography.musicana.feature.regester.model.gender.RequesBody;
import com.prography.musicana.network.NetworkInit;

import org.jetbrains.annotations.NotNull;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegesterPresenter {
    public static final String TAG = RegesterPresenter.class.getSimpleName();
    private NetworkInit networkInit;
    MutableLiveData<RegesterModel> newUserModelMutableLiveData;
    MutableLiveData<RequesBody> requesBodyMutableLiveData;
    MutableLiveData<com.prography.musicana.feature.regester.model.country.RequesBody> requesBodycountryMutableLiveData;
    private static RegesterPresenter instance;

    public RegesterPresenter() {
        networkInit = NetworkInit.getInstance(false);
        newUserModelMutableLiveData = new MutableLiveData<>();
        requesBodyMutableLiveData = new MutableLiveData<>();
        requesBodycountryMutableLiveData = new MutableLiveData<>();

    }

    public static RegesterPresenter getInstance() {
        if (instance == null) {
            instance = new RegesterPresenter();
        }
        return instance;
    }


    public LiveData<RegesterModel> newUser(String firdName, String lastName, String phone, String email
            , String password, String country, String gender) {

        JsonObject req = new JsonObject();
        req.addProperty("firstname", firdName);
        req.addProperty("lastname", lastName);
        req.addProperty("phone", phone);
        req.addProperty("email", email);
        req.addProperty("password", password);
        req.addProperty("country", country);
        req.addProperty("gender", gender);
        RequestBody requestBody = RequestBody.create(String.valueOf(req), MediaType.parse("application/json"));

        networkInit.getRetrofitApis().newUser(requestBody).enqueue(new Callback<RegesterModel>() {
            @Override
            public void onResponse(@NotNull Call<RegesterModel> call, @NotNull Response<RegesterModel> response) {

                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: " + response.body().getResponse().getMessage());
                    newUserModelMutableLiveData.setValue(response.body());
                } else {
                    Log.d(TAG, "onResponse: " + " new data");
                    newUserModelMutableLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(@NotNull Call<RegesterModel> call, @NotNull Throwable t) {
                Log.d(TAG, "onFailure : " + t.getMessage());
                Log.d(TAG, "onFailure : " + t.getLocalizedMessage());
                newUserModelMutableLiveData.setValue(null);
            }
        });
        return newUserModelMutableLiveData;
    }


    public LiveData<RequesBody> getGender() {
        networkInit.getRetrofitApis().getGender().enqueue(new Callback<RequesBody>() {
            @Override
            public void onResponse(Call<RequesBody> call, Response<RequesBody> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: " + response.body().getResponse().getData().get(0).getGender());
                    requesBodyMutableLiveData.setValue(response.body());
                } else {
                    requesBodyMutableLiveData.setValue(null);
                    Log.d(TAG, "onResponse: " + "no data");
                }
            }

            @Override
            public void onFailure(Call<RequesBody> call, Throwable t) {
                requesBodyMutableLiveData.setValue(null);
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
        return requesBodyMutableLiveData;
    }

    public LiveData<com.prography.musicana.feature.regester.model.country.RequesBody> getCountry() {
        networkInit.getRetrofitApis().getCuntry().enqueue(new Callback<com.prography.musicana.feature.regester.model.country.RequesBody>() {
            @Override
            public void onResponse(Call<com.prography.musicana.feature.regester.model.country.RequesBody> call, Response<com.prography.musicana.feature.regester.model.country.RequesBody> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: " + response.body().getResponse().getData().get(0).getName());
                    requesBodycountryMutableLiveData.setValue(response.body());
                } else {
                    Log.d(TAG, "onResponse: " + "no have a data");
                    requesBodycountryMutableLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<com.prography.musicana.feature.regester.model.country.RequesBody> call, Throwable t) {
                Log.d(TAG, "onResponse: " + t.getMessage());
                requesBodycountryMutableLiveData.setValue(null);
            }
        });
        return requesBodycountryMutableLiveData;
    }


}
