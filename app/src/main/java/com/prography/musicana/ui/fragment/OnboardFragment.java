package com.prography.musicana.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prography.musicana.databinding.FragmentOnboardBinding;
import com.prography.musicana.data.onPording.SendDtatToActivity;


public class OnboardFragment extends Fragment {
    private static final String TAG = OnboardFragment.class.getSimpleName();
    private SendDtatToActivity sendDtatToActivity;
    FragmentOnboardBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentOnboardBinding.inflate(getLayoutInflater());

        sendDtatToActivity = SendDtatToActivity.getInstance();
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (sendDtatToActivity.getItems() != null) {
            binding.detels.setText(sendDtatToActivity.getItems().get(3).getDetails());
            binding.txt1.setText(sendDtatToActivity.getItems().get(3).getTitle());


        } else {
            Log.d(TAG, "onViewCreated:  No Data");
        }

    }

}