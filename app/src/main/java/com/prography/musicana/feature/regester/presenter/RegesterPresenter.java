package com.prography.musicana.feature.regester.presenter;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.prography.musicana.feature.regester.model.RegesterModel;
import com.prography.musicana.feature.regester.model.country.Countries;
import com.prography.musicana.feature.regester.model.gender.Genders;
import com.prography.musicana.feature.regester.model.resendverification.ResendVerificationCode;
import com.prography.musicana.feature.regester.model.verification.VerificatioEmail;
import com.prography.musicana.network.NetworkInit;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegesterPresenter {
    public static final String TAG = RegesterPresenter.class.getSimpleName();
    private NetworkInit networkInit;
    private MutableLiveData<RegesterModel> newUserModelMutableLiveData;
    private MutableLiveData<Genders> requesBodyMutableLiveData;
    private MutableLiveData<Countries> requesBodycountryMutableLiveData;
    private MutableLiveData<VerificatioEmail> verificationResponeMutableLiveData;
    private MutableLiveData<ResendVerificationCode> resendVerificationMutableLiveData;
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


    public LiveData<ResendVerificationCode> resendVerificationLiveData(String email) {
        networkInit.getRetrofitApis().resendVerificationCode(email).enqueue(new Callback<ResendVerificationCode>() {
            @Override
            public void onResponse(@NotNull Call<ResendVerificationCode> call, @NotNull Response<ResendVerificationCode> response) {
                if (response.isSuccessful()) {
                    resendVerificationMutableLiveData.setValue(response.body());
                    Log.d(TAG, "onResponse: " + response.body().getResponse().getMessage());
                } else {
                    resendVerificationMutableLiveData.setValue(null);
                    Log.d(TAG, "onResponse:  some thing rounge -_-");
                }
            }

            @Override
            public void onFailure(@NotNull Call<ResendVerificationCode> call, @NotNull Throwable t) {
                resendVerificationMutableLiveData.setValue(null);
                Log.d(TAG, "onResponse:  some thing rounge -_-" + t.getMessage());
            }
        });

        return resendVerificationMutableLiveData;
    }

    public LiveData<VerificatioEmail> verification(String verify_code, String password, String email, String device
            , String uuis, String devicename) {

        networkInit.getRetrofitApis().verificationCode(verify_code, password, email, device, uuis, devicename).enqueue(new Callback<VerificatioEmail>() {
            @Override
            public void onResponse(@NotNull Call<VerificatioEmail> call, @NotNull Response<VerificatioEmail> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: " + response.body().getResponse().getData().getUser().getEmail());
                    verificationResponeMutableLiveData.setValue(response.body());
                } else {
                    Log.d(TAG, "onResponse:  name data");
                    verificationResponeMutableLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(@NotNull Call<VerificatioEmail> call, @NotNull Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                verificationResponeMutableLiveData.setValue(null);
            }
        });
        return verificationResponeMutableLiveData;
    }


    public LiveData<RegesterModel> newUser(String firdName, String lastName, String phone, String email
            , String password, String country, String gender) {

        networkInit.getRetrofitApis().newUser(firdName, lastName, phone, email, password, country, gender).enqueue(new Callback<RegesterModel>() {
            @Override
            public void onResponse(@NotNull Call<RegesterModel> call, @NotNull Response<RegesterModel> response) {

                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: " + response.body().getResponse());
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


    public LiveData<Genders> getGender() {
        networkInit.getRetrofitApis().getGender().enqueue(new Callback<Genders>() {
            @Override
            public void onResponse(Call<Genders> call, Response<Genders> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: " + response.body().getResponse().getData().getGenders().get(0).getGender());
                    requesBodyMutableLiveData.setValue(response.body());
                } else {
                    requesBodyMutableLiveData.setValue(null);
                    Log.d(TAG, "onResponse: " + "no data");
                }
            }

            @Override
            public void onFailure(Call<Genders> call, Throwable t) {
                requesBodyMutableLiveData.setValue(null);
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
        return requesBodyMutableLiveData;
    }

    public LiveData<Countries> getCountry() {
        networkInit.getRetrofitApis().getCuntry().enqueue(new Callback<Countries>() {
            @Override
            public void onResponse(Call<Countries> call, Response<Countries> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: " + response.body().getResponse().getData().getCountries().get(0).getName());
                    requesBodycountryMutableLiveData.setValue(response.body());
                } else {
                    Log.d(TAG, "onResponse: " + "no have a data");
                    requesBodycountryMutableLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<Countries> call, Throwable t) {
                Log.d(TAG, "onResponse: " + t.getMessage());
                requesBodycountryMutableLiveData.setValue(null);
            }
        });
        return requesBodycountryMutableLiveData;
    }


}
