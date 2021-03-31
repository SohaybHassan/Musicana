package com.prography.musicana.repo;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.prography.musicana.data.onPording.DataOnBoarding;
import com.prography.musicana.data.privacypolicy.DataPrivacyPolicy;
import com.prography.musicana.data.termcondtion.DataTermsAndConditions;
import com.prography.musicana.model.DataModel;
import com.prography.musicana.network.NetworkInit;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Type;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OnpordingPresenter {
    public static final String TAG = OnpordingPresenter.class.getSimpleName();
    private NetworkInit networkInit;
    MutableLiveData<DataOnBoarding> getdataMutableLiveData;
    MutableLiveData<DataPrivacyPolicy> getprivacypolicyMutableLiveData;
    MutableLiveData<DataTermsAndConditions> termsAndConditionsMutableLiveData;
    private static OnpordingPresenter instance;

    public OnpordingPresenter() {
        networkInit = NetworkInit.getInstance(true);
        getdataMutableLiveData = new MutableLiveData<>();
        getprivacypolicyMutableLiveData = new MutableLiveData<>();
        termsAndConditionsMutableLiveData = new MutableLiveData<>();
    }

    public static OnpordingPresenter getInstance() {
        if (instance == null) {
            instance = new OnpordingPresenter();
        }
        return instance;
    }

    //
    public LiveData<DataOnBoarding> getDataView() {
        networkInit.getRetrofitApis().getOnBoardingData().enqueue(new Callback<DataModel>() {
            @Override
            public void onResponse(@NotNull Call<DataModel> call, @NotNull Response<DataModel> response) {
                if (response.isSuccessful()) {
                    Gson gson = new Gson();
                    Type type = new TypeToken<DataOnBoarding>() {
                    }.getType();
                    DataOnBoarding data = gson.fromJson(gson.toJson(response.body().getResponse().getData()), type);
                    getdataMutableLiveData.setValue(data);
                } else {
                    Log.e(TAG, "onResponse:  null data heir");
                    getdataMutableLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(@NotNull Call<DataModel> call, @NotNull Throwable t) {
                Log.d(TAG, "onFailure:  " + t.getMessage());
                getdataMutableLiveData.setValue(null);
            }
        });
        return getdataMutableLiveData;
    }


    public LiveData<DataPrivacyPolicy> getPrivacyPolicy() {
        networkInit.getRetrofitApis().getPrivacyPolicy().enqueue(new Callback<DataModel>() {
            @Override
            public void onResponse(@NotNull Call<DataModel> call, @NotNull Response<DataModel> response) {
                if (response.isSuccessful()) {
                    Gson gson = new Gson();
                    Type type = new TypeToken<DataPrivacyPolicy>() {
                    }.getType();
                    DataPrivacyPolicy data = gson.fromJson(gson.toJson(response.body().getResponse().getData()), type);
                    getprivacypolicyMutableLiveData.setValue(data);
                } else {
                    getprivacypolicyMutableLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(@NotNull Call<DataModel> call, @NotNull Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                getprivacypolicyMutableLiveData.setValue(null);
            }
        });
        return getprivacypolicyMutableLiveData;
    }


    public LiveData<DataTermsAndConditions> getTermsAndConditions() {
        networkInit.getRetrofitApis().getTermsAndConditions().enqueue(new Callback<DataModel>() {
            @Override
            public void onResponse(Call<DataModel> call, Response<DataModel> response) {

                if (response.isSuccessful()) {
                    Gson gson = new Gson();
                    Type type = new TypeToken<DataTermsAndConditions>() {
                    }.getType();
                    DataTermsAndConditions data = gson.fromJson(gson.toJson(response.body().getResponse().getData()), type);
                    termsAndConditionsMutableLiveData.setValue(data);
                } else {
                    termsAndConditionsMutableLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<DataModel> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                termsAndConditionsMutableLiveData.setValue(null);
            }
        });
        return termsAndConditionsMutableLiveData;
    }


}
