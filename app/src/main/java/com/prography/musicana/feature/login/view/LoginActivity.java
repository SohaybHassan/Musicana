package com.prography.musicana.feature.login.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;

import com.prography.musicana.databinding.ActivityLoginBinding;
import com.prography.musicana.feature.MainActivity;
import com.prography.musicana.feature.forgotPassword.ForgotPasswordActivity;
import com.prography.musicana.feature.login.model.DataLogin;
import com.prography.musicana.feature.login.viewmodel.LoginViewmodel;
import com.prography.musicana.feature.regester.view.RegesterActivity;
import com.prography.musicana.utils.SWStaticMethods;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = LoginActivity.class.getSimpleName();
    private ActivityLoginBinding binding;
    private LoginViewmodel loginViewmodel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loginViewmodel = new ViewModelProvider(this).get(LoginViewmodel.class);
        binding.btnLogin.setOnClickListener(view -> {

            loginViewmodel.login("ghost199716@gmail.com", "123456789").observe(this, dataLogin -> {

                if (dataLogin != null) {
                    Log.d(TAG, "onCreate: " + dataLogin.getResponse().getMessage().getData().getEmail());
                } else {
                    Log.d(TAG, "onCreate: " + "we dont have a data");
                }
            });


            SWStaticMethods.intentWithoutData(LoginActivity.this, MainActivity.class);

        });

        binding.tvRegesterNow.setOnClickListener(view -> {

            SWStaticMethods.intentWithoutData(LoginActivity.this, RegesterActivity.class);
        });

        binding.tvForgotPassword.setOnClickListener(view -> {
            SWStaticMethods.intentWithoutData(LoginActivity.this, ForgotPasswordActivity.class);
        });



    }
}