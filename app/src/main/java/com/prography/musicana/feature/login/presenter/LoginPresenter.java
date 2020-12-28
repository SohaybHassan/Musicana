package com.prography.musicana.feature.login.presenter;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.JsonObject;
import com.prography.musicana.feature.login.model.DataLogin;
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
    private MutableLiveData<DataLogin> loginMutableLiveData;

    public LoginPresenter() {
        networkInit = NetworkInit.getInstance(false);
        loginMutableLiveData = new MutableLiveData<>();
    }

    public static LoginPresenter getInstance() {
        if (mInstance == null) {
            mInstance = new LoginPresenter();

        }
        return mInstance;
    }

    public LiveData<DataLogin> login(String email, String pass) {

        JsonObject req = new JsonObject();
        req.addProperty("email", email);
        req.addProperty("password", pass);
        req.addProperty("device", "Android");
        req.addProperty("UUID", "g4a58asg456asg846asg");
        req.addProperty("device_name", "Abu Fares");
        RequestBody requestBody = RequestBody.create(String.valueOf(req), MediaType.parse("application/json"));

        networkInit.getRetrofitApis().login(requestBody).enqueue(new Callback<DataLogin>() {
            @Override
            public void onResponse(@NotNull Call<DataLogin> call, @NotNull Response<DataLogin> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: " + response.body().getResponse().getMessage().getData().getEmail());
                    loginMutableLiveData.setValue(response.body());
                } else {
                    loginMutableLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(@NotNull Call<DataLogin> call, @NotNull Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                loginMutableLiveData.setValue(null);
            }
        });
        return loginMutableLiveData;
    }


}
