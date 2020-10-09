package com.prography.musicana.feature.onboard;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.prography.musicana.R;
import com.prography.musicana.databinding.ActivityTermsConditionsBinding;

public class TermsConditionsActivity extends AppCompatActivity {
ActivityTermsConditionsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityTermsConditionsBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);


        setSupportActionBar(binding.toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);
        binding.toolbar.setNavigationOnClickListener(v -> onBackPressed());
    }
}