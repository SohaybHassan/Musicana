package com.prography.musicana.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prography.musicana.databinding.FragmentOnboardTowBinding;
import com.prography.musicana.data.onPording.SendDtatToActivity;


public class OnboardTowFragment extends Fragment {

private FragmentOnboardTowBinding binding;
private SendDtatToActivity sendDtatToActivity;
    private static final String TAG = "OnboardTowFragment";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentOnboardTowBinding.inflate(getLayoutInflater());
        sendDtatToActivity=SendDtatToActivity.getInstance();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (sendDtatToActivity.getItems()!=null){
            binding.det.setText(sendDtatToActivity.getItems().get(2).getDetails());
            binding.txt1.setText(sendDtatToActivity.getItems().get(2).getTitle());

        }else{
            Log.d(TAG, "onViewCreated:  No Data");
        }
    }
}