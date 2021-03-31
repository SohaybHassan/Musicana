package com.prography.musicana.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.prography.musicana.data.DataLogin;
import com.prography.musicana.repo.LoginPresenter;

public class LoginViewmodel extends AndroidViewModel {

    LoginPresenter loginPresenter;

    public LoginViewmodel(@NonNull Application application) {
        super(application);
        loginPresenter = LoginPresenter.getInstance();
    }


    public LiveData<DataLogin> login(String email, String pass, String device, String uuid, String deviceName) {
        return loginPresenter.login(email,pass,device,uuid,deviceName);
    }


}
