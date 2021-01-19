package com.prography.musicana.feature.forgotPassword;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.prography.musicana.R;
import com.prography.musicana.custem.SWDialog;
import com.prography.musicana.databinding.ActivityForgotPasswordBinding;
import com.prography.musicana.databinding.ActivityLoginBinding;
import com.prography.musicana.feature.regester.model.resendverification.ResendVerification;
import com.prography.musicana.feature.regester.view.VerificationCode;
import com.prography.musicana.feature.regester.viewModel.RegesterViewModel;
import com.prography.musicana.utils.SWStaticMethods;

public class ForgotPasswordActivity extends AppCompatActivity {
    private ActivityForgotPasswordBinding binding;
    private RegesterViewModel regesterViewModel;
    private static final String TAG = ForgotPasswordActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityForgotPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        regesterViewModel = new ViewModelProvider(this).get(RegesterViewModel.class);

        setSupportActionBar(binding.toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);
        binding.toolbar.setNavigationOnClickListener(v -> onBackPressed());


    }


}