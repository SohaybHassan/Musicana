package com.prography.musicana.repo;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.prography.musicana.data.onPording.OnpordingModel;
import com.prography.musicana.data.privacypolicy.DataPrivacyPolicy;
import com.prography.musicana.data.privacypolicy.ResponsePrivacyPolicy;
import com.prography.musicana.data.termcondtion.TermsAndConditions;
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
    MutableLiveData<OnpordingModel> getdataMutableLiveData;
    MutableLiveData<DataPrivacyPolicy> getprivacypolicyMutableLiveData;
    MutableLiveData<TermsAndConditions> termsAndConditionsMutableLiveData;
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
    public LiveData<OnpordingModel> getDataView() {
        networkInit.getRetrofitApis().getOnpordingData().enqueue(new Callback<OnpordingModel>() {
            @Override
            public void onResponse(@NotNull Call<OnpordingModel> call, @NotNull Response<OnpordingModel> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: " + response.body().getResponse().getData().getOnboarding().get(1).getDetails());
                    getdataMutableLiveData.setValue(response.body());
                } else {
                    Log.e(TAG, "onResponse:  null data heir");
                    getdataMutableLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(@NotNull Call<OnpordingModel> call, @NotNull Throwable t) {
                Log.d(TAG, "onFailure:  " + t.getMessage());
                getdataMutableLiveData.setValue(null);
            }
        });
        return getdataMutableLiveData;
    }


    public LiveData<DataPrivacyPolicy> getprivacypolicy() {
        networkInit.getRetrofitApis().getprivacypolicy().enqueue(new Callback<DataModel>() {
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


    public LiveData<TermsAndConditions> getermsAndConditions() {
        networkInit.getRetrofitApis().getTermsAndConditions().enqueue(new Callback<TermsAndConditions>() {
            @Override
            public void onResponse(Call<TermsAndConditions> call, Response<TermsAndConditions> response) {

                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: " + response.body().getResponse().getData().getTerms().getData());
                    termsAndConditionsMutableLiveData.setValue(response.body());
                } else {
                    termsAndConditionsMutableLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<TermsAndConditions> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                termsAndConditionsMutableLiveData.setValue(null);
            }
        });
        return termsAndConditionsMutableLiveData;
    }


}
