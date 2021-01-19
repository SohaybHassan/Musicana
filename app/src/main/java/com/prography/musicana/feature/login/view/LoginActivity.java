package com.prography.musicana.feature.login.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.jacksonandroidnetworking.JacksonParserFactory;
import com.prography.musicana.databinding.ActivityLoginBinding;
import com.prography.musicana.feature.MainActivity;
import com.prography.musicana.feature.forgotPassword.ForgotPasswordActivity;
import com.prography.musicana.feature.login.model.Example;
import com.prography.musicana.feature.login.viewmodel.LoginViewmodel;
import com.prography.musicana.feature.regester.view.RegesterActivity;
import com.prography.musicana.utils.SWStaticMethods;

import org.json.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = LoginActivity.class.getSimpleName();
    private ActivityLoginBinding binding;
    private LoginViewmodel loginViewmodel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        String str = Build.VERSION.RELEASE;
        Log.d(TAG, "1997 android :" + str);

        String uuid = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        Log.d(" 1997 android :", uuid);

        String model = android.os.Build.MODEL;
        Log.d(TAG, "1997 android :" + model);

//        TelephonyManager tManager = (TelephonyManager)getSystemService(TELEPHONY_SERVICE);
//        tManager.getDeviceId();
//        String uuid = tManager.getDeviceId();

        // Log.d(TAG, "1997 android :" + uuid);


        loginViewmodel = new ViewModelProvider(this).get(LoginViewmodel.class);
        binding.btnLogin.setOnClickListener(view -> {

            String mEmail = binding.edEmail.getText().toString();
            String mPass = binding.edPassword.getText().toString();
            if (!TextUtils.isEmpty(mEmail) && !TextUtils.isEmpty(mPass)) {
                binding.btnLogin.setVisibility(View.GONE);
                binding.progressBar.setVisibility(View.VISIBLE);
                binding.tvForgotPassword.setVisibility(View.GONE);
                binding.tvRegesterNow.setVisibility(View.GONE);
                reqLogin(mEmail, mPass, uuid, model);
            } else {
                Toast.makeText(this, "plese enter all felid", Toast.LENGTH_SHORT).show();
            }


        });

        binding.tvRegesterNow.setOnClickListener(view -> {

            SWStaticMethods.intentWithoutData(LoginActivity.this, RegesterActivity.class);

        });

        binding.tvForgotPassword.setOnClickListener(view -> {
            androidnetwork();
            // SWStaticMethods.intentWithoutData(LoginActivity.this, ForgotPasswordActivity.class);
        });


    }


    public void androidnetwork() {
        AndroidNetworking.initialize(this);
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .addNetworkInterceptor(httpLoggingInterceptor)
                .build();
        AndroidNetworking.initialize(getApplicationContext(), okHttpClient);


        AndroidNetworking.setParserFactory(new JacksonParserFactory());

        //
        AndroidNetworking.post("https://try.musicaa.app/api/v1/user/login/delete")
                .addBodyParameter("email", "ahmedsalheia.as@gmail.com")
                .addBodyParameter("password", "123456789")
                .addBodyParameter("device", "Android")
                .addBodyParameter("UUID", "g4a58asg456asg846asg")
                .addBodyParameter("device_name", "Abu Fares")
                .addBodyParameter("refresh", "Abu Fares")
                .setTag("test")
                .addHeaders("Accept-Language", "en")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if (response != null) {
                            Log.d(TAG, "onResponse:  الامور تمام ");
                        }
                    }

                    @Override
                    public void onError(ANError error) {
                        // handle error
                        Log.d(TAG, "onResponse:  الامور مش تمام " + error.getErrorBody());
                        Log.d(TAG, "onResponse:  الامور مش تمام " + error.getErrorDetail());
                        Log.d(TAG, "onResponse:  الامور مش تمام " + error.getResponse());
                        Log.d(TAG, "onResponse:  الامور مش تمام " + error.getErrorCode());
                    }
                });
    }


    public void reqLogin(String mEmail, String mPass, String uuid, String model) {
        loginViewmodel.login(mEmail, mPass, "android", uuid, model).observe(this, new Observer<Example>() {
            @Override
            public void onChanged(Example dataLogin) {
                if (dataLogin != null) {
                    binding.btnLogin.setVisibility(View.VISIBLE);
                    binding.progressBar.setVisibility(View.GONE);
                    binding.tvForgotPassword.setVisibility(View.VISIBLE);
                    binding.tvRegesterNow.setVisibility(View.VISIBLE);
                    Log.d(TAG, "onChanged: " + "we have a data");
                    Log.d(TAG, "onChanged: " + dataLogin.getResponse().getData().getEmail());
                    SWStaticMethods.intentWithoutData(LoginActivity.this, MainActivity.class);
                } else {
                    binding.btnLogin.setVisibility(View.VISIBLE);
                    binding.progressBar.setVisibility(View.GONE);
                    binding.tvForgotPassword.setVisibility(View.VISIBLE);
                    binding.tvRegesterNow.setVisibility(View.VISIBLE);
                    Toast.makeText(LoginActivity.this, "you must register  in the app before login", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onChanged: " + " you must register  in the app before login");
                }
            }
        });
    }
}