package com.prography.musicana.feature.onboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.prography.musicana.R;
import com.prography.musicana.databinding.ActivityOnboardingBinding;
import com.prography.musicana.feature.MainActivity;
import com.prography.musicana.feature.login.LoginActivity;

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
            Toast.makeText(this, "الاستمرار من دون رؤية هذا الكلام وشكرا غلى تسجيل الدوال", Toast.LENGTH_SHORT).show();
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
            startActivity(new Intent(Onboarding.this, LoginActivity.class));
            finish();
        });
    }
}