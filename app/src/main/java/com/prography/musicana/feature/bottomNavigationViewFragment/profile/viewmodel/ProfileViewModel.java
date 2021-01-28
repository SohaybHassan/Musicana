package com.prography.musicana.feature.bottomNavigationViewFragment.profile.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.prography.musicana.feature.bottomNavigationViewFragment.profile.model.logout.Logout;
import com.prography.musicana.feature.bottomNavigationViewFragment.profile.presenter.ProfilePresenter;

public class ProfileViewModel extends AndroidViewModel {

    ProfilePresenter profilePresenter;

    public ProfileViewModel(@NonNull Application application) {
        super(application);
        profilePresenter = ProfilePresenter.getInstance();
    }


    public LiveData<Logout> logout() {
        return profilePresenter.logoutUser();
    }
}
