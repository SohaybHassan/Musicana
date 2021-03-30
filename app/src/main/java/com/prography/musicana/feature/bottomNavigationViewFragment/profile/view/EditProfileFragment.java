package com.prography.musicana.feature.bottomNavigationViewFragment.profile.view;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.prography.musicana.BuildConfig;
import com.prography.musicana.R;
import com.prography.musicana.databinding.FragmentEditProfileBinding;
import com.prography.musicana.custem.SWInterface.OnFragmentInteractionListener;
import com.prography.musicana.feature.bottomNavigationViewFragment.profile.model.profiledata.ProfileData;
import com.prography.musicana.feature.bottomNavigationViewFragment.profile.viewmodel.ProfileViewModel;
import com.prography.musicana.feature.forgotPassword.ForgotPasswordActivity;
import com.prography.musicana.utils.SWStaticMethods;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;
//import com.yalantis.ucrop.UCrop;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static android.app.Activity.RESULT_OK;

public class EditProfileFragment extends Fragment {
    private static final int REQUEST_PERMISSION_CODE_CAMERA = 799;
    private static final int REQUEST_CODE_CAMERA = 999;
    private static final int REQUEST_PERMISSION_CODE_GALLERY = 788;
    private static final int REQUEST_CODE_GALLERY = 888;

    private File file;
    private int count = 0;
    private Uri outputFileUri = null;
    private static final String TAG = "AddCategoryActivity";

    private FragmentEditProfileBinding binding;
    private OnFragmentInteractionListener mListener;
    private ProfileViewModel profileViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentEditProfileBinding.inflate(inflater, container, false);
        profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.editProfileBackArrow.setOnClickListener(v -> {
            // go back
            mListener.onFragmentInteraction(0);
        });

        binding.edEmail.setEnabled(false);
        binding.edPassword.setEnabled(false);
        binding.edEmail.setOnClickListener(v -> {
            SWStaticMethods.intentWithoutDataAndFinish(getActivity(), ForgotPasswordActivity.class);
        });
        binding.editProfileUserImage.setOnClickListener(v -> {
            getPhoto();
        });
        binding.btnUpdate.setOnClickListener(v -> {
            RequestBody profile_name = RequestBody.create(MediaType.parse("text/plain"), binding.edName.getText().toString().trim());
            RequestBody profile_middle_name = RequestBody.create(MediaType.parse("text/plain"), "mohammed");
            RequestBody profile_last_name = RequestBody.create(MediaType.parse("text/plain"), binding.edLastName.getText().toString().trim());
            RequestBody profile_phone = RequestBody.create(MediaType.parse("text/plain"), binding.edPhone.getText().toString().trim());
            RequestBody profile_gender = RequestBody.create(MediaType.parse("text/plain"), binding.edGender.getText().toString().trim());
            RequestBody profile_country = RequestBody.create(MediaType.parse("text/plain"), binding.edCountry.getText().toString().trim());


            RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpg"), file);
            MultipartBody.Part profile_image = null;
                    profile_image =
                            MultipartBody.Part.createFormData("files[0]", file.getName(), requestBody);

            updateProfile(profile_name,
                    profile_middle_name,
                    profile_last_name,
                    profile_phone,
                    profile_gender,
                    profile_country,
                    profile_image
            );
        });
        getdata();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }


    public void getdata() {
        profileViewModel.getData().observe(getActivity(), new Observer<ProfileData>() {
            @Override
            public void onChanged(ProfileData profileData) {
                if (profileData != null) {

                    binding.edName.setText(profileData.getResponse().getData().getUser().getFirstname());
                    binding.edEmail.setText(profileData.getResponse().getData().getUser().getEmail());
                    binding.edPhone.setText(profileData.getResponse().getData().getUser().getPhone());
                    binding.edCountry.setText(profileData.getResponse().getData().getUser().getCountry());
                    binding.edGender.setText(profileData.getResponse().getData().getUser().getGender());
                    binding.edLastName.setText(profileData.getResponse().getData().getUser().getLastname());

                    Log.d(TAG, "onChanged: " + profileData.getResponse().getData().getUser().getFirstname());
                    Log.d(TAG, "onChanged:  we have data here");
                } else {
                    Log.d(TAG, "onChanged:  we  dont have data here");
                }
            }
        });
    }


    public void updateProfile(RequestBody first_name, RequestBody middle_name, RequestBody last_name, RequestBody phone, RequestBody gender, RequestBody country, MultipartBody.Part image) {
        profileViewModel.updateProfile(first_name, middle_name, last_name, phone, gender, country, image)
                .observe(getViewLifecycleOwner(), updateProfileResponse -> {
                    if (updateProfileResponse != null) {
                        Log.d("updateProfile", "update profile Not null");
                    } else {
                        Log.d("updateProfile", "update profile null");
                    }
                });
    }


    // image methods
    public void getPhoto() {

        CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .setAspectRatio(1,1)
                .start(getActivity());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                outputFileUri = result.getUri();
                if (outputFileUri != null) {
                    file = new File(outputFileUri.getPath());
                    Glide.with(getContext()).load(outputFileUri).into(binding.editProfileUserImage);
//                    binding.editProfileUserImage.setImageURI(outputFileUri);
                    Toast.makeText(getContext(), ""+outputFileUri, Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onActivityResult: "+outputFileUri);
                }
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Toast.makeText(getContext(), "you failed", Toast.LENGTH_SHORT).show();
//                binding.send.setEnabled(true);
            }
        }
    }

}