package com.prography.musicana.feature.login.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.prography.musicana.feature.login.model.Example;
import com.prography.musicana.feature.login.presenter.LoginPresenter;

public class LoginViewmodel extends AndroidViewModel {

    LoginPresenter loginPresenter;

    public LoginViewmodel(@NonNull Application application) {
        super(application);
        loginPresenter = LoginPresenter.getInstance();
    }


    public LiveData<Example> login(String email, String pass,String device,String uuid,String deviceName) {
        return loginPresenter.login(email,pass,device,uuid,deviceName);
    }


}
