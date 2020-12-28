package com.prography.musicana.feature.regester.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.prography.musicana.R;
import com.prography.musicana.custem.BottomSheetListView;
import com.prography.musicana.databinding.ActivityRegesterBinding;
import com.prography.musicana.feature.regester.model.RegesterModel;
import com.prography.musicana.feature.regester.model.country.RequesBody;
import com.prography.musicana.feature.regester.viewModel.RegesterViewModel;

import java.util.ArrayList;

public class RegesterActivity extends AppCompatActivity {
    public static final String TAG = RegesterActivity.class.getSimpleName();
    private ActivityRegesterBinding binding;
    private RegesterViewModel regesterViewModel;
    private String name, phone, email, password, confarmPassword, country, gender;
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
        name = binding.edName.getText().toString();
        phone = binding.edPhone.getText().toString();
        email = binding.edEmail.getText().toString();
        password = binding.edPassword.getText().toString();
        confarmPassword = binding.edConfirmPassword.getText().toString();
        country = binding.edCountry.getText().toString();
        gender = binding.edGender.getText().toString();


        binding.tvLoginNow.setOnClickListener(view -> {
            regesterViewModel.newUser("Sohaib", "Hassan", "0597847916", "ghost199716@gmail.com", "123456789", cont_id, binding.edGender.getText().toString()).observe(this, new Observer<RegesterModel>() {
                @Override
                public void onChanged(RegesterModel regesterModel) {
                    if (regesterModel != null) {
                        Log.d(TAG, "onChanged: " + regesterModel.getResponse().getMessage());

                    } else {
                        Toast.makeText(RegesterActivity.this, "no tata", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            // startActivity(new Intent(RegesterActivity.this, LoginActivity.class));
        });

        regesterViewModel.getCountry().observe(this, new Observer<RequesBody>() {
            @Override
            public void onChanged(RequesBody requesBody) {
                if (requesBody != null) {
                    Log.d(TAG, "onChanged: " + requesBody.getResponse().getData().get(0).getName());
                    for (int i = 0; i < requesBody.getResponse().getData().size(); i++) {
                        Log.d(TAG, "onChanged: " + requesBody.getResponse().getData().get(i).getName());
                        contryName.add(requesBody.getResponse().getData().get(i).getName());
                        contryId.add(requesBody.getResponse().getData().get(i).getId());
                    }
                } else {
                    Log.d(TAG, "onChanged: " + "no data");
                }
            }
        });
        Log.d(TAG, "onChanged: " + contryName.size());
        Log.d(TAG, "onChanged: " + contryId.size());
        regesterViewModel.getGender().observe(this, new Observer<com.prography.musicana.feature.regester.model.gender.RequesBody>() {
            @Override
            public void onChanged(com.prography.musicana.feature.regester.model.gender.RequesBody requesBody) {
                if (requesBody != null) {
                    Log.d(TAG, "onChanged: " + requesBody.getResponse().getData().get(0).getGender());

                    for (int i = 0; i < requesBody.getResponse().getData().size(); i++) {
                        gendername.add(requesBody.getResponse().getData().get(i).getGender());
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