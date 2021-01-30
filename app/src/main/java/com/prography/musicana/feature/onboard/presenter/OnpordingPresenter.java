package com.prography.musicana.feature.onboard.presenter;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.prography.musicana.feature.onboard.model.onPording.OnpordingModel;
import com.prography.musicana.feature.onboard.model.privacypolicy.DataPoalycey;
import com.prography.musicana.feature.onboard.model.termcondtion.TermsAndConditions;
import com.prography.musicana.network.NetworkInit;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OnpordingPresenter {
    public static final String TAG = OnpordingPresenter.class.getSimpleName();
    private NetworkInit networkInit;
    MutableLiveData<OnpordingModel> getdataMutableLiveData;
    MutableLiveData<DataPoalycey> getprivacypolicyMutableLiveData;
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


    public LiveData<DataPoalycey> getprivacypolicy() {
        networkInit.getRetrofitApis().getprivacypolicy().enqueue(new Callback<DataPoalycey>() {
            @Override
            public void onResponse(@NotNull Call<DataPoalycey> call, @NotNull Response<DataPoalycey> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: " + response.body().getResponse().getData().getPrivacy().getData());
                    getprivacypolicyMutableLiveData.setValue(response.body());
                } else {
                    getprivacypolicyMutableLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(@NotNull Call<DataPoalycey> call, @NotNull Throwable t) {
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
