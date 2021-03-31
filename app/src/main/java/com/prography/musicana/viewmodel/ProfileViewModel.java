package com.prography.musicana.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.prography.musicana.data.DataSettings;
import com.prography.musicana.data.DataProfile;
import com.prography.musicana.data.DataUpdateProfile;
import com.prography.musicana.repo.ProfilePresenter;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class ProfileViewModel extends AndroidViewModel {

    ProfilePresenter profilePresenter;

    public ProfileViewModel(@NonNull Application application) {
        super(application);
        profilePresenter = ProfilePresenter.getInstance();
    }


    public LiveData<String> logout() {
        return profilePresenter.logoutUser();
    }

    public LiveData<DataProfile> getData() {
        return profilePresenter.getProfileData();
    }

    public LiveData<DataUpdateProfile> updateProfile(RequestBody first_name, RequestBody middle_name, RequestBody last_name, RequestBody phone, RequestBody gender, RequestBody country, MultipartBody.Part image) {
        return profilePresenter.updateProfile(first_name, middle_name, last_name, phone, gender, country, image);
    }

    public LiveData<DataSettings> getAllSettings() {
        return profilePresenter.getAllSettings();
    }

    public LiveData<DataSettings> changeSettings(String mood, String language, String additional_screen, String auto_update, String background, String audio, String location) {
        return profilePresenter.changeSettings(mood, language, additional_screen, auto_update, background, audio, location);
    }

}
