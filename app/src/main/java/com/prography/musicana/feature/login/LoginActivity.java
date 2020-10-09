package com.prography.musicana.feature.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.prography.musicana.R;
import com.prography.musicana.databinding.ActivityLoginBinding;
import com.prography.musicana.feature.MainActivity;
import com.prography.musicana.feature.forgotPassword.ForgotPasswordActivity;
import com.prography.musicana.feature.regester.RegesterActivity;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        binding.btnLogin.setOnClickListener(view -> {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        });

        binding.tvRegesterNow.setOnClickListener(view -> {
            startActivity(new Intent(LoginActivity.this, RegesterActivity.class));
        });

        binding.tvForgotPassword.setOnClickListener(view->{
            startActivity(new Intent(LoginActivity.this, ForgotPasswordActivity.class));
        });
    }
}