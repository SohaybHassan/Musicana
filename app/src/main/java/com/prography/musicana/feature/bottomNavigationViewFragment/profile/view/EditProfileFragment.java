package com.prography.musicana.feature.bottomNavigationViewFragment.profile.view;

import android.content.Context;
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

import com.prography.musicana.databinding.FragmentEditProfileBinding;
import com.prography.musicana.custem.SWInterface.OnFragmentInteractionListener;
import com.prography.musicana.feature.bottomNavigationViewFragment.profile.model.profiledata.ProfileData;
import com.prography.musicana.feature.bottomNavigationViewFragment.profile.viewmodel.ProfileViewModel;

public class EditProfileFragment extends Fragment {

    private FragmentEditProfileBinding binding;
    private OnFragmentInteractionListener mListener;
    private ProfileViewModel profileViewModel;
    public static final String TAG = EditProfileFragment.class.getSimpleName();

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

                    Log.d(TAG, "onChanged: " + profileData.getResponse().getData().getUser().getFirstname());
                    Log.d(TAG, "onChanged:  we have data here");
                } else {
                    Log.d(TAG, "onChanged:  we  dont have data here");
                }
            }
        });
    }
}