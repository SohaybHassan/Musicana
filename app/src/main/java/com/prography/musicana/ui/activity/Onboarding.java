package com.prography.musicana.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.prography.musicana.R;
import com.prography.musicana.databinding.ActivityOnboardingBinding;
import com.prography.musicana.ui.fragment.OnboardFragment;
import com.prography.musicana.ui.fragment.OnboardTowFragment;
import com.prography.musicana.ui.fragment.OnpoardFourFragment;
import com.prography.musicana.ui.fragment.OnpoardThreeFragment;
import com.prography.musicana.utils.SWStaticMethods;

public class Onboarding extends AppCompatActivity {
    private int conter = 1;
    private ActivityOnboardingBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding = ActivityOnboardingBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_swatsh, new OnboardFragment()).commit();

        binding.dotsIndicator.setDotSelection(0);
        binding.btnSkip.setOnClickListener(view1 -> {
            SWStaticMethods.intentWithoutData(Onboarding.this, LoginActivity.class);
        });

        binding.btnNext.setOnClickListener(view2 -> {
            switch (conter) {
                case 1:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_swatsh, new OnboardTowFragment()).commit();
                    binding.dotsIndicator.setDotSelection(1);
                    conter++;
                    break;
                case 2:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_swatsh, new OnpoardThreeFragment()).commit();
                    binding.dotsIndicator.setDotSelection(2);
                    conter++;
                    break;
                case 3:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_swatsh, new OnpoardFourFragment()).commit();
                    binding.dotsIndicator.setDotSelection(3);
                    binding.btnNext.setVisibility(View.GONE);
                    binding.btnSkip.setVisibility(View.GONE);
                    binding.btnStart.setVisibility(View.VISIBLE);

                    break;
            }

        });

        binding.btnStart.setOnClickListener(view2 -> {

            SWStaticMethods.intentWithoutData(Onboarding.this, LoginActivity.class);
            
        });
    }


}