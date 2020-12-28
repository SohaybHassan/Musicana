package com.prography.musicana.feature.channels;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Bundle;

import com.prography.musicana.R;
import com.prography.musicana.databinding.ActivityChannelsBinding;

public class ChannelsActivity extends AppCompatActivity {
private ActivityChannelsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding=ActivityChannelsBinding.inflate(getLayoutInflater());
        setContentView( binding.getRoot());


        setSupportActionBar(binding.toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);



        binding.rvChannels.setLayoutManager(new GridLayoutManager(this,2));
        binding.rvChannels.setAdapter(new ChannelsAdapter());

    }
}