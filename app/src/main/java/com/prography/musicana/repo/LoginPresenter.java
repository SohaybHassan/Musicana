package com.prography.musicana.repo;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.prography.musicana.data.country.DataCountries;
import com.prography.musicana.data.loginmodel.DataLogin;
import com.prography.musicana.model.DataModel;
import com.prography.musicana.network.NetworkInit;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Type;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter {
    private static final String TAG = LoginPresenter.class.getSimpleName();
    private static LoginPresenter mInstance;
    private NetworkInit networkInit;
    private MutableLiveData<DataLogin> loginMutableLiveData;

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

    public LiveData<DataLogin> login(String email, String pass, String device, String uuid, String deviceName) {

        networkInit.getRetrofitApis().login(email, pass, device, uuid, deviceName).enqueue(new Callback<DataModel>() {
            @Override
            public void onResponse(@NotNull Call<DataModel> call, @NotNull Response<DataModel> response) {
                if (response.isSuccessful()) {
                    Gson gson = new Gson();
                    Type type = new TypeToken<DataLogin>() {
                    }.getType();
                    DataLogin data = gson.fromJson(gson.toJson(response.body().getResponse().getData()), type);
                    loginMutableLiveData.setValue(data);
                } else {
                    Log.d(TAG, "onResponse: " + "null");
                    loginMutableLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(@NotNull Call<DataModel> call, @NotNull Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                loginMutableLiveData.setValue(null);
            }
        });
        return loginMutableLiveData;
    }


}
