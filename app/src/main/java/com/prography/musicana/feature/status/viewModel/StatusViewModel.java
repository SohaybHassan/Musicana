package com.prography.musicana.feature.status.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.prography.musicana.feature.status.changestatus.ChangeStatus;
import com.prography.musicana.feature.status.closestatus.CloseStatus;
import com.prography.musicana.feature.status.newstatus.NewStatus;
import com.prography.musicana.feature.status.presenter.StatusPresenter;

public class StatusViewModel extends AndroidViewModel {

    private static final String TAG = StatusViewModel.class.getSimpleName();
    private StatusPresenter statusPresenter;

    public StatusViewModel(@NonNull Application application) {
        super(application);
        statusPresenter = StatusPresenter.getInstenc();
    }

    public LiveData<NewStatus> setnewStatus(String uuid) {
        return statusPresenter.setnewStatus(uuid);
    }

    public LiveData<ChangeStatus> setChangeStatus(String change_to) {
        return statusPresenter.setChangeStatus(change_to);
    }

    public LiveData<CloseStatus> setCloseStatus() {
        return statusPresenter.setCloseStatus();
    }

}
