package com.prography.musicana.feature.onboard.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.prography.musicana.databinding.FragmentOnboardTowBinding;
import com.prography.musicana.feature.onboard.model.onPording.SendDtatToActivity;


public class OnboardTowFragment extends Fragment {

private FragmentOnboardTowBinding binding;
private SendDtatToActivity sendDtatToActivity;
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
            Glide.with(getActivity()).load(sendDtatToActivity.getItems().get(2).getImg()).into(binding.imgOnpoardOne);
        }else{

        }
    }
}