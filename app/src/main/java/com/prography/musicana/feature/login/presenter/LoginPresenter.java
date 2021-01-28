package com.prography.musicana.feature.login.presenter;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.google.gson.JsonObject;
import com.prography.musicana.feature.login.model.Example;
import com.prography.musicana.network.NetworkInit;

import org.jetbrains.annotations.NotNull;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter {
    private static final String TAG = LoginPresenter.class.getSimpleName();
    private static LoginPresenter mInstance;
    private NetworkInit networkInit;
    private MutableLiveData<Example> loginMutableLiveData;

    public LoginPresenter() {
        networkInit = NetworkInit.getInstance(true);
        loginMutableLiveData = new MutableLiveData<>();
    }

    public static LoginPresenter getInstance() {
        if (mInstance == null) {
            mInstance = new LoginPresenter();

        }
        return mInstance;
    }

    public LiveData<Example> login(String email, String pass,String device,String uuid,String deviceName) {
//        JsonObject req = new JsonObject();
//        req.addProperty("email", email);
//        req.addProperty("password", pass);
//        req.addProperty("device", "Android");
//        req.addProperty("UUID", "g4a58asg456asg846asg");
//        req.addProperty("device_name", "Abu Fares");
//        RequestBody requestBody = RequestBody.create(String.valueOf(req), MediaType.parse("application/json"));
//        Log.d(TAG, "login: " + requestBody.getClass().getName());

        networkInit.getRetrofitApis().login(email,pass,device,uuid,deviceName).enqueue(new Callback<Example>() {
            @Override
            public void onResponse(@NotNull Call<Example> call, @NotNull Response<Example> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: " + "isSuccessful");
                    if (response != null)
                        Log.d(TAG, "onResponse: " + response.body().getResponse().getSettings());
                    loginMutableLiveData.setValue(response.body());
                } else {
                    Log.d(TAG, "onResponse: " + "null");
                    loginMutableLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(@NotNull Call<Example> call, @NotNull Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                loginMutableLiveData.setValue(null);
            }
        });
        return loginMutableLiveData;
    }


}
