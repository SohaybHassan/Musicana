package com.prography.musicana.feature.regester;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.prography.musicana.R;
import com.prography.musicana.databinding.ActivityRegesterBinding;
import com.prography.musicana.feature.login.LoginActivity;

public class RegesterActivity extends AppCompatActivity {
    private ActivityRegesterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegesterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.tvLoginNow.setOnClickListener(view -> {
            startActivity(new Intent(RegesterActivity.this, LoginActivity.class));
        });
    }
}