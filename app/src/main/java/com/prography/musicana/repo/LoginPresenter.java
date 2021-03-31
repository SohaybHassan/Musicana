package com.prography.musicana.repo;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.prography.musicana.data.loginmodel.Login;
import com.prography.musicana.network.NetworkInit;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter {
    private static final String TAG = LoginPresenter.class.getSimpleName();
    private static LoginPresenter mInstance;
    private NetworkInit networkInit;
    private MutableLiveData<Login> loginMutableLiveData;

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

    public LiveData<Login> login(String email, String pass,String device,String uuid,String deviceName) {

        networkInit.getRetrofitApis().login(email,pass,device,uuid,deviceName).enqueue(new Callback<Login>() {
            @Override
            public void onResponse(@NotNull Call<Login> call, @NotNull Response<Login> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: " + "isSuccessful");
                    if (response != null)
                        Log.d(TAG, "onResponse: " + response.body().getResponse().getData().getUser().getEmail());
                    loginMutableLiveData.setValue(response.body());
                } else {
                    Log.d(TAG, "onResponse: " + "null");
                    loginMutableLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(@NotNull Call<Login> call, @NotNull Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                loginMutableLiveData.setValue(null);
            }
        });
        return loginMutableLiveData;
    }


}
