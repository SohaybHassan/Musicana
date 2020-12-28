package com.prography.musicana.feature.onboard.view;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.prography.musicana.R;
import com.prography.musicana.databinding.ActivityTermsConditionsBinding;
import com.prography.musicana.feature.onboard.model.termcondtion.GenerlClass;
import com.prography.musicana.feature.onboard.viewModel.OnPoardingViewmodel;

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
        onPoardingViewmodel.gettermandCondtion().observe(this, new Observer<GenerlClass>() {
            @Override
            public void onChanged(GenerlClass generlClass) {
                if (generlClass != null) {
                    Log.d("TAG", "onChanged: " + "we ahve a data");
                    String data = generlClass.getResponse().getData().getData();
                    binding.tvTextData.setText(data);
                }
            }
        });
    }
}