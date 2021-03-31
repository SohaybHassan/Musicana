package com.prography.musicana.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.prography.musicana.data.country.Countries;
import com.prography.musicana.data.gender.Genders;
import com.prography.musicana.data.resendverification.ResendVerificationCode;
import com.prography.musicana.data.verification.VerificatioEmail;
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

    public LiveData<VerificatioEmail> verificationResponeLiveData(String verify_code, String password, String email, String device
            , String uuis, String devicename) {
        return regesterPresenter.verification(verify_code, password, email, device, uuis, devicename);
    }

    public LiveData<Countries> getCountry() {
        return regesterPresenter.getCountry();
    }

    public LiveData<Genders> getGender() {
        return regesterPresenter.getGender();
    }

    public LiveData<ResendVerificationCode> resendVerificationLiveData(String email) {
        return regesterPresenter.resendVerificationLiveData(email);
    }
}
