package com.prography.musicana.feature.login.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.prography.musicana.feature.login.model.DataLogin;
import com.prography.musicana.feature.login.presenter.LoginPresenter;

public class LoginViewmodel extends AndroidViewModel {

    LoginPresenter loginPresenter;

    public LoginViewmodel(@NonNull Application application) {
        super(application);
        loginPresenter = LoginPresenter.getInstance();
    }


    public LiveData<DataLogin> login(String email, String pass) {
        return loginPresenter.login(email, pass);
    }


}
