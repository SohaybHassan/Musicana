package com.prography.musicana.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.prography.musicana.data.onPording.OnpordingModel;
import com.prography.musicana.data.privacypolicy.DataPrivacyPolicy;
import com.prography.musicana.data.privacypolicy.ResponsePrivacyPolicy;
import com.prography.musicana.data.termcondtion.TermsAndConditions;
import com.prography.musicana.repo.OnpordingPresenter;

public class OnPoardingViewmodel extends AndroidViewModel {
    public static final String TAG = OnPoardingViewmodel.class.getSimpleName();
    OnpordingPresenter onpordingPresenter;


    public OnPoardingViewmodel(@NonNull Application application) {
        super(application);
        onpordingPresenter = OnpordingPresenter.getInstance();
    }

    public LiveData<OnpordingModel> getData() {
        return onpordingPresenter.getDataView();
    }

    public LiveData<DataPrivacyPolicy> getprivacypolicy() {
        return onpordingPresenter.getprivacypolicy();
    }

    public LiveData<TermsAndConditions> gettermandCondtion() {
        return onpordingPresenter.getermsAndConditions();
    }
}
