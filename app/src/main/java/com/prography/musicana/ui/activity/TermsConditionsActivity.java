package com.prography.musicana.ui.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.prography.musicana.databinding.ActivityTermsConditionsBinding;
import com.prography.musicana.data.termcondtion.TermsAndConditions;
import com.prography.musicana.viewmodel.OnPoardingViewmodel;

public class TermsConditionsActivity extends AppCompatActivity {
    private ActivityTermsConditionsBinding binding;
    OnPoardingViewmodel onPoardingViewmodel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTermsConditionsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        setSupportActionBar(binding.toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);
        binding.toolbar.setNavigationOnClickListener(v -> onBackPressed());

        onPoardingViewmodel = new ViewModelProvider(this).get(OnPoardingViewmodel.class);
        onPoardingViewmodel.gettermandCondtion().observe(this, new Observer<TermsAndConditions>() {
            @Override
            public void onChanged(TermsAndConditions generlClass) {
                if (generlClass != null) {
                    Log.d("TAG", "onChanged: " + "we ahve a data");
                    String data = generlClass.getResponse().getData().getTerms().getData();
                    binding.tvTextData.setText(data);
                }
            }
        });
    }
}