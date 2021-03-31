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
import com.prography.musicana.databinding.ActivityRegesterBinding;
import com.prography.musicana.data.country.Countries;
import com.prography.musicana.data.gender.Genders;
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
    private ArrayList<String> contryName;
    private ArrayList<String> contryId;
    private String cont_id;
    private ArrayList<String> gendername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegesterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        contryName = new ArrayList<>();
        contryId = new ArrayList<>();
        gendername = new ArrayList<>();


        regesterViewModel = new ViewModelProvider(this).get(RegesterViewModel.class);


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


        binding.tvLoginNow.setOnClickListener(view -> {
            // androidnetwork();
            startActivity(new Intent(RegesterActivity.this, LoginActivity.class));
        });

        regesterViewModel.getCountry().observe(this, new Observer<Countries>() {
            @Override
            public void onChanged(Countries requesBody) {
                if (requesBody != null) {
                    Log.d(TAG, "onChanged: " + requesBody.getResponse().getData().getCountries().get(0).getName());
                    for (int i = 0; i < requesBody.getResponse().getData().getCountries().size(); i++) {
                        contryName.add(requesBody.getResponse().getData().getCountries().get(i).getName());
                        contryId.add(requesBody.getResponse().getData().getCountries().get(i).getId());
                    }
                } else {
                    Log.d(TAG, "onChanged: " + "no data");
                }
            }
        });
        regesterViewModel.getGender().observe(this, new Observer<Genders>() {
            @Override
            public void onChanged(Genders requesBody) {
                if (requesBody != null) {
                    Log.d(TAG, "onChanged: " + requesBody.getResponse().getData().getGenders().get(0).getGender());

                    for (int i = 0; i < requesBody.getResponse().getData().getGenders().size(); i++) {
                        gendername.add(requesBody.getResponse().getData().getGenders().get(i).getGender());
                    }
                } else {
                    Log.d(TAG, "onChanged: " + "no data");
                }
            }
        });
        binding.edCountry.setOnClickListener(v -> {
            BottomSheetDialog dialog = new BottomSheetDialog(this);
            dialog.setContentView(R.layout.list_bottom_sheet);
            BottomSheetListView bottomSheetListView = dialog.findViewById(R.id.listViewBtmSheet);
            TextView tv_title = dialog.findViewById(R.id.tv_title_bottom_sheet);

            bottomSheetListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    binding.edCountry.setText(contryName.get(position));
                    cont_id = contryId.get(position);
                    dialog.dismiss();
                }

            });

            tv_title.setText(R.string.Title);
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contryName);
            bottomSheetListView.setAdapter(arrayAdapter);
            dialog.show();


        });

        binding.edGender.setOnClickListener(v -> {

            BottomSheetDialog dialog = new BottomSheetDialog(this);
            dialog.setContentView(R.layout.list_bottom_sheet);
            BottomSheetListView bottomSheetListView = dialog.findViewById(R.id.listViewBtmSheet);
            TextView tv_title = dialog.findViewById(R.id.tv_title_bottom_sheet);

            bottomSheetListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    binding.edGender.setText(gendername.get(position));

                    dialog.dismiss();
                }

            });

            tv_title.setText(R.string.genger);
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, gendername);
            bottomSheetListView.setAdapter(arrayAdapter);
            dialog.show();


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
        AndroidNetworking.post("https://try.musicaa.app/api/v1/user/register")
                .addBodyParameter("firstname", "ahmed")
                .addBodyParameter("lastname", "salheia")
                .addBodyParameter("phone", "0597847916")
                .addBodyParameter("email", "ahmedalaa.as2001@gmail.com")
                .addBodyParameter("password", "123456789")
                .addBodyParameter("country", "PS")
                .addBodyParameter("gender", "male")
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


    /*
     binding.edGovernorate.setOnClickListener(view15 -> {

            Toast.makeText(getContext(), "hi", Toast.LENGTH_SHORT).show();
            BottomSheetDialog dialog = new BottomSheetDialog(getContext());
            dialog.setContentView(R.layout.bottom_sheet_view);

            BottomSheetListView listView = dialog.findViewById(R.id.listViewBtmSheet);

            listView.setOnItemClickListener((adapterView, view12, i, l) -> {
                binding.edGovernorate.setText(AllRegion.get(i));
                region_id = AllRegionID.get(i);
                dialog.dismiss();
            });
            MaterialTextView titleTv = dialog.findViewById(R.id.tv_spinner_title_bottom_sheet);
            titleTv.setText(R.string.governorate);
            ArrayAdapter<String> itemsAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, AllRegion);
            listView.setAdapter(itemsAdapter);
            dialog.show();
        });
     */

}