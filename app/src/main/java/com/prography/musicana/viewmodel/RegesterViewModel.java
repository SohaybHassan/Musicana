package com.prography.musicana.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.prography.musicana.data.country.DataCountries;
import com.prography.musicana.data.gender.DataGenders;
import com.prography.musicana.data.verification.DataVerificationEmail;
import com.prography.musicana.repo.RegesterPresenter;

public class RegesterViewModel extends AndroidViewModel {

    RegesterPresenter regesterPresenter;

    public RegesterViewModel(@NonNull Application application) {
        super(application);
        regesterPresenter = RegesterPresenter.getInstance();
    }

    public LiveData<String> newUser(String firdName, String lastName, String phone, String email
            , String password, String country, String gender) {
        return regesterPresenter.newUser(firdName, lastName, phone, email, password, country, gender);

    }

    public LiveData<DataVerificationEmail> verificationResponeLiveData(String verify_code, String password, String email, String device
            , String uuis, String devicename) {
        return regesterPresenter.verification(verify_code, password, email, device, uuis, devicename);
    }

    public LiveData<DataCountries> getCountry() {
        return regesterPresenter.getCountry();
    }

    public LiveData<DataGenders> getGender() {
        return regesterPresenter.getGender();
    }

    public LiveData<String> resendVerificationLiveData(String email) {
        return regesterPresenter.resendVerificationLiveData(email);
    }
}
