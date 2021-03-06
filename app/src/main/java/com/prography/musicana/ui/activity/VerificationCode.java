package com.prography.musicana.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.prography.musicana.data.verification.DataVerificationEmail;
import com.prography.musicana.utils.SharedPreferencesHelper;
import com.prography.musicana.databinding.ActivityVerificationCodeBinding;
import com.prography.musicana.viewmodel.RegesterViewModel;
import com.prography.musicana.utils.SWStaticMethods;

public class VerificationCode extends AppCompatActivity {
    private ActivityVerificationCodeBinding binding;
    private RegesterViewModel regesterViewModel;
    private static final String TAG = VerificationCode.class.getSimpleName();
    private SharedPreferencesHelper sharedPreferencesHelper;

    private String pass;
    private String mEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVerificationCodeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        regesterViewModel = new ViewModelProvider(this).get(RegesterViewModel.class);

        sharedPreferencesHelper = new SharedPreferencesHelper();
        getdataIntent();
        resendCode();
        verification();


    }

    public void verification() {
        binding.btnVerifie.setOnClickListener(v -> {
            String code = binding.pinView.getText().toString();
            if (!TextUtils.isEmpty(binding.pinView.getText()) && binding.pinView.getText().toString().length() == 6) {
                binding.progressBar.setVisibility(View.VISIBLE);
                binding.btnVerifie.setVisibility(View.GONE);
                binding.tvResendCode.setVisibility(View.GONE);
                verificationRequest(code, SWStaticMethods.getMolde(), SWStaticMethods.getUUID(this), pass, mEmail);
            } else {
                Toast.makeText(this, "Plese Enter The  Full Code ", Toast.LENGTH_SHORT).show();
            }

        });
    }

    private void resendCode() {
        binding.tvResendCode.setOnClickListener(v -> {
            if (!TextUtils.isEmpty(mEmail)) {
                binding.btnVerifie.setVisibility(View.GONE);
                binding.progressBar.setVisibility(View.VISIBLE);
                resendVerification(mEmail);
            }
        });
    }

    private void getdataIntent() {
        if (getIntent() != null) {
            Bundle bundle = getIntent().getBundleExtra("data");
            pass = bundle.getString("password");
            mEmail = bundle.getString("email");
            Log.d(TAG, "1997 android :" + pass + "_" + mEmail);
        }
    }


    public void verificationRequest(String code, String deciceName, String uuid, String pass, String mEmail) {

        regesterViewModel.verificationResponeLiveData(code, pass, mEmail, "android", uuid, deciceName).observe(this, new Observer<DataVerificationEmail>() {
            @Override
            public void onChanged(DataVerificationEmail verificationRespone) {
                if (verificationRespone != null) {
                    binding.progressBar.setVisibility(View.GONE);
                    binding.tvResendCode.setVisibility(View.VISIBLE);
                    binding.btnVerifie.setVisibility(View.VISIBLE);

                    sharedPreferencesHelper.saveData(verificationRespone.getUser().getToken());
                    Log.d(TAG, "sohaib Token: " + sharedPreferencesHelper.getToken());
                    SWStaticMethods.intentWithoutData(VerificationCode.this, MainActivity.class);
                } else {
                    binding.btnVerifie.setVisibility(View.VISIBLE);
                    binding.tvResendCode.setVisibility(View.VISIBLE);
                    binding.progressBar.setVisibility(View.GONE);
                    Log.d(TAG, "onChanged:  no data here");
                }
            }
        });
    }


    public void resendVerification(String email) {

        regesterViewModel.resendVerificationLiveData(email).observe(this, resendVerification -> {
            if (resendVerification != null) {
                binding.btnVerifie.setVisibility(View.VISIBLE);
                binding.progressBar.setVisibility(View.GONE);
                Log.d(TAG, "onChanged: " + resendVerification);
                Toast.makeText(VerificationCode.this, resendVerification, Toast.LENGTH_SHORT).show();
            } else {
                binding.btnVerifie.setVisibility(View.VISIBLE);
                binding.progressBar.setVisibility(View.GONE);
                Toast.makeText(VerificationCode.this, "wrong email", Toast.LENGTH_SHORT).show();
            }
        });


    }

}