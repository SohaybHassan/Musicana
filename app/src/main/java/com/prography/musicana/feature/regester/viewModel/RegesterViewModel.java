package com.prography.musicana.feature.regester.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.prography.musicana.feature.regester.model.RegesterModel;
import com.prography.musicana.feature.regester.model.gender.RequesBody;
import com.prography.musicana.feature.regester.model.resendverification.ResendVerification;
import com.prography.musicana.feature.regester.model.verification.VerificationRespone;
import com.prography.musicana.feature.regester.presenter.RegesterPresenter;

public class RegesterViewModel extends AndroidViewModel {

    RegesterPresenter regesterPresenter;

    public RegesterViewModel(@NonNull Application application) {
        super(application);
        regesterPresenter = RegesterPresenter.getInstance();
    }

    public LiveData<RegesterModel> newUser(String firdName, String lastName, String phone, String email
            , String password, String country, String gender) {
        return regesterPresenter.newUser(firdName, lastName, phone, email, password, country, gender);

    }

    public LiveData<VerificationRespone> verificationResponeLiveData(String verify_code, String password, String email, String device
            , String uuis, String devicename) {
        return regesterPresenter.verification(verify_code, password, email, device, uuis, devicename);
    }

    public LiveData<com.prography.musicana.feature.regester.model.country.RequesBody> getCountry() {
        return regesterPresenter.getCountry();
    }

    public LiveData<RequesBody> getGender() {
        return regesterPresenter.getGender();
    }

    public LiveData<ResendVerification> resendVerificationLiveData(String email) {
        return regesterPresenter.resendVerificationLiveData(email);
    }
}
