package com.prography.musicana.feature.onboard.view;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.prography.musicana.R;
import com.prography.musicana.databinding.ActivityPrivacyPolicyBinding;
import com.prography.musicana.feature.onboard.model.DataPoalycey;
import com.prography.musicana.feature.onboard.viewModel.OnPoardingViewmodel;

public class PrivacyPolicyActivity extends AppCompatActivity {
    private ActivityPrivacyPolicyBinding binding;
    private OnPoardingViewmodel onPoardingViewmodel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPrivacyPolicyBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        setSupportActionBar(binding.toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);
        binding.toolbar.setNavigationOnClickListener(v -> onBackPressed());
        onPoardingViewmodel = new ViewModelProvider(this).get(OnPoardingViewmodel.class);

        onPoardingViewmodel.getprivacypolicy().observe(this, new Observer<DataPoalycey>() {
            @Override
            public void onChanged(DataPoalycey dataPoalycey) {
                if (dataPoalycey != null) {
                    Log.d("TAG", "onChanged: " + "we have a data");
                    String text = dataPoalycey.getResponse().getData().getData();
                    binding.tvTextPlacy.setText(text);
                }
            }
        });

    }
}