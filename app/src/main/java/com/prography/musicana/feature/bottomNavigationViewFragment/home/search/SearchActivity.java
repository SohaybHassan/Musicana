package com.prography.musicana.feature.bottomNavigationViewFragment.home.search;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.prography.musicana.R;
import com.prography.musicana.databinding.ActivitySearchBinding;

public class SearchActivity extends AppCompatActivity {


    private ActivitySearchBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}