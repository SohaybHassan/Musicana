package com.prography.musicana.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.jacksonandroidnetworking.JacksonParserFactory;
import com.prography.musicana.R;
import com.prography.musicana.custem.bottomsheet.BottomSheetListView;
import com.prography.musicana.data.country.DataCountries;
import com.prography.musicana.data.gender.DataGenders;
import com.prography.musicana.databinding.ActivityRegesterBinding;
import com.prography.musicana.viewmodel.RegesterViewModel;
import com.prography.musicana.utils.SWStaticMethods;

import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class RegesterActivity extends AppCompatActivity {
    public static final String TAG = RegesterActivity.class.getSimpleName();
    private ActivityRegesterBinding binding;
    private RegesterViewModel regesterViewModel;
    private String name, phone, email, password, confarmPassword, country, gender, lastNmae;
    private ArrayList<String> contryName = new ArrayList<>();
    private ArrayList<String> contryId = new ArrayList<>();
    private String cont_id;
    private ArrayList<String> gendername = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegesterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        regesterViewModel = new ViewModelProvider(this).get(RegesterViewModel.class);
        regester();
        tvLogin();
        getCountry();
        getGender();
        getCountryBottomSheet();
        getGenderBottomSheet();


    }

    public void regester() {
        binding.btnRegester.setOnClickListener(v -> {

            name = binding.edName.getText().toString();
            phone = binding.edPhone.getText().toString();
            email = binding.edEmail.getText().toString();
            password = binding.edPassword.getText().toString();
            confarmPassword = binding.edConfirmPassword.getText().toString();
            country = binding.edCountry.getText().toString();
            gender = binding.edGender.getText().toString();
            lastNmae = binding.edLastName.getText().toString();


            if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(lastNmae) && !TextUtils.isEmpty(phone) && !TextUtils.isEmpty(email)
                    && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(cont_id) && !TextUtils.isEmpty(gender)) {
                binding.progressBar.setVisibility(View.VISIBLE);
                binding.btnRegester.setVisibility(View.GONE);
                regesterReques(name, lastNmae, phone, email, password, cont_id, gender);
            } else {
                Toast.makeText(this, "plese enter All felied", Toast.LENGTH_SHORT).show();
            }

        });
    }

    public void tvLogin() {
        binding.tvLoginNow.setOnClickListener(view -> {
            startActivity(new Intent(RegesterActivity.this, LoginActivity.class));
        });
    }

    private void getGenderBottomSheet() {

        binding.edGender.setOnClickListener(v -> {

            BottomSheetDialog dialog = new BottomSheetDialog(this);
            dialog.setContentView(R.layout.list_bottom_sheet);
            BottomSheetListView bottomSheetListView = dialog.findViewById(R.id.listViewBtmSheet);
            TextView tv_title = dialog.findViewById(R.id.tv_title_bottom_sheet);

            bottomSheetListView.setOnItemClickListener((parent, view, position, id) -> {
                binding.edGender.setText(gendername.get(position));

                dialog.dismiss();
            });


            tv_title.setText(R.string.genger);
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, gendername);
            bottomSheetListView.setAdapter(arrayAdapter);
            dialog.show();

        });

    }

    private void getCountryBottomSheet() {
        binding.edCountry.setOnClickListener(v -> {
            BottomSheetDialog dialog = new BottomSheetDialog(this);
            dialog.setContentView(R.layout.list_bottom_sheet);
            BottomSheetListView bottomSheetListView = dialog.findViewById(R.id.listViewBtmSheet);
            TextView tv_title = dialog.findViewById(R.id.tv_title_bottom_sheet);

            bottomSheetListView.setOnItemClickListener((parent, view, position, id) -> {
                binding.edCountry.setText(contryName.get(position));
                cont_id = contryId.get(position);
                dialog.dismiss();
            });

            tv_title.setText(R.string.Title);
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contryName);
            bottomSheetListView.setAdapter(arrayAdapter);
            dialog.show();


        });


    }

    private void getGender() {
        regesterViewModel.getGender().observe(this, requesBody -> {
            if (requesBody != null) {
                Log.d(TAG, "onChanged: " + requesBody.getGenders().get(0).getGender());

                for (int i = 0; i < requesBody.getGenders().size(); i++) {
                    gendername.add(requesBody.getGenders().get(i).getGender());
                }
            } else {
                Log.d(TAG, "onChanged: " + "no data");
            }
        });
    }

    private void getCountry() {
        regesterViewModel.getCountry().observe(this, requesBody -> {
            if (requesBody != null) {
                Log.d(TAG, "onChanged: " + requesBody.getCountries().get(0).getName());
                for (int i = 0; i < requesBody.getCountries().size(); i++) {
                    contryName.add(requesBody.getCountries().get(i).getName());
                    contryId.add(requesBody.getCountries().get(i).getId());
                }
            } else {
                Log.d(TAG, "onChanged: " + "no data");
            }
        });
    }

    public void regesterReques(String firdName, String lastNmae, String phone, String email
            , String password, String country, String gender) {

        regesterViewModel.newUser(firdName, lastNmae, phone, email, password, country, gender).observe(this, new Observer<String>() {
            @Override
            public void onChanged(String regesterModel) {
                if (regesterModel != null) {
                    binding.btnRegester.setVisibility(View.VISIBLE);
                    binding.progressBar.setVisibility(View.GONE);
                    Log.d(TAG, "onChanged: " + regesterModel);
                    Bundle bundle = new Bundle();
                    bundle.putString("password", password);
                    bundle.putString("email", email);

                    SWStaticMethods.intentWithData(RegesterActivity.this, VerificationCode.class, bundle);
                } else {
                    binding.btnRegester.setVisibility(View.VISIBLE);
                    binding.progressBar.setVisibility(View.GONE);
                    Log.d(TAG, "onChanged: no data");
                }
            }
        });
    }

}