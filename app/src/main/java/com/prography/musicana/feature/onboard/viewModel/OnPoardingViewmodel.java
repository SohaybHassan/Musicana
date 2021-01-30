package com.prography.musicana.feature.onboard.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.prography.musicana.feature.onboard.model.onPording.OnpordingModel;
import com.prography.musicana.feature.onboard.model.privacypolicy.DataPoalycey;
import com.prography.musicana.feature.onboard.model.termcondtion.TermsAndConditions;
import com.prography.musicana.feature.onboard.presenter.OnpordingPresenter;

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

    public LiveData<DataPoalycey> getprivacypolicy() {
        return onpordingPresenter.getprivacypolicy();
    }

    public LiveData<TermsAndConditions> gettermandCondtion() {
        return onpordingPresenter.getermsAndConditions();
    }
}
