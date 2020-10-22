package com.prography.musicana.feature.bottomNavigationViewFragment.home.phoneFragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prography.musicana.R;
import com.prography.musicana.databinding.FragmentPhoneBinding;
import com.prography.musicana.feature.bottomNavigationViewFragment.home.phoneFragment.adapter.PhoneFragmentAdapter;


public class PhoneFragment extends Fragment {


    private FragmentPhoneBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPhoneBinding.inflate(getLayoutInflater());

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.rvPhoneFragment.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.rvPhoneFragment.setAdapter(new PhoneFragmentAdapter());


    }
}