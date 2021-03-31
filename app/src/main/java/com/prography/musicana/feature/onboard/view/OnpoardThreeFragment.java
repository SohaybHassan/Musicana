package com.prography.musicana.feature.onboard.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.prography.musicana.databinding.FragmentOnpoardThreeBinding;
import com.prography.musicana.feature.onboard.model.onPording.SendDtatToActivity;


public class OnpoardThreeFragment extends Fragment {

    private FragmentOnpoardThreeBinding binding;
    private SendDtatToActivity sendDtatToActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentOnpoardThreeBinding.inflate(getLayoutInflater());
        sendDtatToActivity=SendDtatToActivity.getInstance();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (sendDtatToActivity.getItems()!=null){
            binding.det.setText(sendDtatToActivity.getItems().get(1).getDetails());
            binding.txt1.setText(sendDtatToActivity.getItems().get(1).getTitle());
            //Glide.with(getActivity()).load(sendDtatToActivity.getItems().get(1).getImg()).into(binding.imgOnpoardOne);
        }else{

        }

    }
}