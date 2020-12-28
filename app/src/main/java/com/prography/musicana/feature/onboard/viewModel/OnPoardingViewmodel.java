package com.prography.musicana.feature.onboard.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.prography.musicana.feature.onboard.model.DataPoalycey;
import com.prography.musicana.feature.onboard.model.OnPordingData;
import com.prography.musicana.feature.onboard.model.OnpordingModel;
import com.prography.musicana.feature.onboard.model.termcondtion.GenerlClass;
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

    public LiveData<GenerlClass> gettermandCondtion() {
        return onpordingPresenter.getermsAndConditions();
    }
}
