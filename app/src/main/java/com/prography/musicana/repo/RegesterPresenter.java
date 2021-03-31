package com.prography.musicana.repo;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.prography.musicana.data.country.DataCountries;
import com.prography.musicana.data.gender.DataGenders;
import com.prography.musicana.data.verification.DataVerificationEmail;
import com.prography.musicana.model.DataModel;
import com.prography.musicana.network.NetworkInit;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Type;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegesterPresenter {
    public static final String TAG = RegesterPresenter.class.getSimpleName();
    private NetworkInit networkInit;
    private MutableLiveData<String> newUserModelMutableLiveData;
    private MutableLiveData<DataGenders> requesBodyMutableLiveData;
    private MutableLiveData<DataCountries> requesBodycountryMutableLiveData;
    private MutableLiveData<DataVerificationEmail> verificationResponeMutableLiveData;
    private MutableLiveData<String> resendVerificationMutableLiveData;
    private static RegesterPresenter instance;

    public RegesterPresenter() {
        networkInit = NetworkInit.getInstance(true);
        newUserModelMutableLiveData = new MutableLiveData<>();
        requesBodyMutableLiveData = new MutableLiveData<>();
        requesBodycountryMutableLiveData = new MutableLiveData<>();
        verificationResponeMutableLiveData = new MutableLiveData<>();
        resendVerificationMutableLiveData = new MutableLiveData<>();
    }

    public static RegesterPresenter getInstance() {
        if (instance == null) {
            instance = new RegesterPresenter();
        }
        return instance;
    }


    public LiveData<String> resendVerificationLiveData(String email) {
        networkInit.getRetrofitApis().resendVerificationCode(email).enqueue(new Callback<DataModel>() {
            @Override
            public void onResponse(@NotNull Call<DataModel> call, @NotNull Response<DataModel> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: " + response.body().getResponse());
                    resendVerificationMutableLiveData.setValue(response.body().getResponse().getMessage());
                } else {
                    resendVerificationMutableLiveData.setValue(null);
                    Log.d(TAG, "onResponse:  some thing rounge -_-");
                }
            }

            @Override
            public void onFailure(@NotNull Call<DataModel> call, @NotNull Throwable t) {
                resendVerificationMutableLiveData.setValue(null);
                Log.d(TAG, "onResponse:  some thing rounge -_-" + t.getMessage());
            }
        });

        return resendVerificationMutableLiveData;
    }

    public LiveData<DataVerificationEmail> verification(String verify_code, String password, String email, String device
            , String uuis, String devicename) {

        networkInit.getRetrofitApis().verificationCode(verify_code, password, email, device, uuis, devicename).enqueue(new Callback<DataModel>() {
            @Override
            public void onResponse(@NotNull Call<DataModel> call, @NotNull Response<DataModel> response) {
                if (response.isSuccessful()) {
                    Gson gson = new Gson();
                    Type type = new TypeToken<DataVerificationEmail>() {
                    }.getType();
                    DataVerificationEmail data = gson.fromJson(gson.toJson(response.body().getResponse().getData()), type);
                    verificationResponeMutableLiveData.setValue(data);
                } else {
                    Log.d(TAG, "onResponse:  name data");
                    verificationResponeMutableLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(@NotNull Call<DataModel> call, @NotNull Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                verificationResponeMutableLiveData.setValue(null);
            }
        });
        return verificationResponeMutableLiveData;
    }


    public LiveData<String> newUser(String firdName, String lastName, String phone, String email
            , String password, String country, String gender) {

        networkInit.getRetrofitApis().newUser(firdName, lastName, phone, email, password, country, gender).enqueue(new Callback<DataModel>() {
            @Override
            public void onResponse(@NotNull Call<DataModel> call, @NotNull Response<DataModel> response) {
                if (response.isSuccessful()) {
//                    Gson gson = new Gson();
//                    Type type = new TypeToken<ResponseRegester>() {
//                    }.getType();
//                    ResponseRegester data = gson.fromJson(gson.toJson(response.body().getResponse().getMessage()), type);
                    Log.d(TAG, "onResponse: " + response.body().getResponse());
                    newUserModelMutableLiveData.setValue(response.body().getResponse().getMessage());
                } else {
                    Log.d(TAG, "onResponse: " + " new data");
                    newUserModelMutableLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(@NotNull Call<DataModel> call, @NotNull Throwable t) {
                Log.d(TAG, "onFailure : " + t.getMessage());
                Log.d(TAG, "onFailure : " + t.getLocalizedMessage());
                newUserModelMutableLiveData.setValue(null);
            }
        });
        return newUserModelMutableLiveData;
    }


    public LiveData<DataGenders> getGender() {
        networkInit.getRetrofitApis().getGender().enqueue(new Callback<DataModel>() {
            @Override
            public void onResponse(Call<DataModel> call, Response<DataModel> response) {
                if (response.isSuccessful()) {
                    Gson gson = new Gson();
                    Type type = new TypeToken<DataGenders>() {
                    }.getType();
                    DataGenders data = gson.fromJson(gson.toJson(response.body().getResponse().getData()), type);
                    requesBodyMutableLiveData.setValue(data);
                } else {
                    requesBodyMutableLiveData.setValue(null);
                    Log.d(TAG, "onResponse: " + "no data");
                }
            }

            @Override
            public void onFailure(Call<DataModel> call, Throwable t) {
                requesBodyMutableLiveData.setValue(null);
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
        return requesBodyMutableLiveData;
    }

    public LiveData<DataCountries> getCountry() {
        networkInit.getRetrofitApis().getCountry().enqueue(new Callback<DataModel>() {
            @Override
            public void onResponse(Call<DataModel> call, Response<DataModel> response) {
                if (response.isSuccessful()) {
                    Gson gson = new Gson();
                    Type type = new TypeToken<DataCountries>() {
                    }.getType();
                    DataCountries data = gson.fromJson(gson.toJson(response.body().getResponse().getData()), type);
                    requesBodycountryMutableLiveData.setValue(data);
                } else {
                    Log.d(TAG, "onResponse: " + "no have a data");
                    requesBodycountryMutableLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<DataModel> call, Throwable t) {
                Log.d(TAG, "onResponse: " + t.getMessage());
                requesBodycountryMutableLiveData.setValue(null);
            }
        });
        return requesBodycountryMutableLiveData;
    }


}
