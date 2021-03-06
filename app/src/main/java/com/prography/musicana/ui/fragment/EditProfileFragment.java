package com.prography.musicana.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prography.musicana.data.DataProfile;
import com.prography.musicana.databinding.FragmentEditProfileBinding;
import com.prography.musicana.listener.OnFragmentInteractionListener;
import com.prography.musicana.viewmodel.ProfileViewModel;
import com.prography.musicana.ui.activity.ForgotPasswordActivity;
import com.prography.musicana.utils.SWStaticMethods;

//import com.yalantis.ucrop.UCrop;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

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
            SWStaticMethods.intentWithOutDataAndFinish(getActivity(), ForgotPasswordActivity.class);
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
        getData();
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


    public void getData() {
        profileViewModel.getData().observe(getActivity(), new Observer<DataProfile>() {
            @Override
            public void onChanged(DataProfile profileData) {
                if (profileData != null) {

                    binding.edName.setText(profileData.getUser().getFirstname());
                    binding.edEmail.setText(profileData.getUser().getEmail());
                    binding.edPhone.setText(profileData.getUser().getPhone());
                    binding.edCountry.setText(profileData.getUser().getCountry());
                    binding.edGender.setText(profileData.getUser().getGender());
                    binding.edLastName.setText(profileData.getUser().getLastname());

                    Log.d(TAG, "onChanged: " + profileData.getUser().getFirstname());
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

//        CropImage.activity()
//                .setGuidelines(CropImageView.Guidelines.ON)
//                .setAspectRatio(1,1)
//                .start(getActivity());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
//            CropImage.ActivityResult result = CropImage.getActivityResult(data);
//            if (resultCode == RESULT_OK) {
//                outputFileUri = result.getUri();
//                if (outputFileUri != null) {
//                    file = new File(outputFileUri.getPath());
//                    Glide.with(getContext()).load(outputFileUri).into(binding.editProfileUserImage);
////                    binding.editProfileUserImage.setImageURI(outputFileUri);
//                    Toast.makeText(getContext(), ""+outputFileUri, Toast.LENGTH_SHORT).show();
//                    Log.d(TAG, "onActivityResult: "+outputFileUri);
//                }
//            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
//                Toast.makeText(getContext(), "you failed", Toast.LENGTH_SHORT).show();
////                binding.send.setEnabled(true);
//            }
//        }
    }

}