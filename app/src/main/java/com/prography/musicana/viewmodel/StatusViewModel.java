package com.prography.musicana.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.prography.musicana.data.changestatus.ChangeStatusData;
import com.prography.musicana.data.newstatus.NewStatusData;
import com.prography.musicana.repo.StatusPresenter;

public class StatusViewModel extends AndroidViewModel {

    private static final String TAG = StatusViewModel.class.getSimpleName();
    private StatusPresenter statusPresenter;

    public StatusViewModel(@NonNull Application application) {
        super(application);
        statusPresenter = StatusPresenter.getInstenc();
    }

    public LiveData<NewStatusData> setnewStatus(String uuid) {
        return statusPresenter.setnewStatus(uuid);
    }

    public LiveData<ChangeStatusData> setChangeStatus(String change_to) {
        return statusPresenter.setChangeStatus(change_to);
    }

    public LiveData<String> setCloseStatus() {
        return statusPresenter.setCloseStatus();
    }

}
