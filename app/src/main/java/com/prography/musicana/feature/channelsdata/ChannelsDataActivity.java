package com.prography.musicana.feature.channelsdata;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.prography.musicana.R;
import com.prography.musicana.databinding.ActivityChannelsDataBinding;

public class ChannelsDataActivity extends AppCompatActivity {

    private ActivityChannelsDataBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChannelsDataBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        setSupportActionBar(binding.toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);

        binding.rvChannelDataList.setAdapter(new ChannelDataAdapter());
        binding.rvChannelDataList.setLayoutManager(new LinearLayoutManager(this));


    }
}