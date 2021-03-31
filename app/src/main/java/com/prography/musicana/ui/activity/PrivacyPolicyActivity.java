package com.prography.musicana.ui.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.prography.musicana.data.privacypolicy.Data;
import com.prography.musicana.data.privacypolicy.Privacy;
import com.prography.musicana.data.privacypolicy.ResponsePrivacyPolicy;
import com.prography.musicana.databinding.ActivityPrivacyPolicyBinding;
import com.prography.musicana.data.privacypolicy.DataPoalycey;
import com.prography.musicana.model.DataModel;
import com.prography.musicana.viewmodel.OnPoardingViewmodel;

import java.lang.reflect.Type;

public class PrivacyPolicyActivity extends AppCompatActivity {
    private ActivityPrivacyPolicyBinding binding;
    private OnPoardingViewmodel onPoardingViewmodel;

    private static final String TAG = "PrivacyPolicyActivity";

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

        onPoardingViewmodel.getprivacypolicy().observe(this, new Observer<ResponsePrivacyPolicy>() {
            @Override
            public void onChanged(ResponsePrivacyPolicy dataPoalycey) {
                if (dataPoalycey != null) {

                    Log.d("TAG", "onChanged: " + "we have a data");
                    String text = dataPoalycey.getData().getPrivacy().getData();
                    Log.d(TAG, "onChanged: "+text);
                    binding.tvTextPlacy.setText(text);
                }
            }
        });

    }
}