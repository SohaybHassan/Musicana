package com.prography.musicana.feature.onboard.view;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.prography.musicana.R;
import com.prography.musicana.custem.SWInterface.SendDataOnBording;
import com.prography.musicana.databinding.ActivityOnboardingBinding;
import com.prography.musicana.databinding.FragmentOnboardBinding;
import com.prography.musicana.databinding.FragmentOnpoardFourBinding;
import com.prography.musicana.feature.onboard.model.SendDtatToActivity;


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

           Glide.with(getActivity()).load(sendDtatToActivity.getItems().get(3).getImg()).into(binding.imgOnpoardOne);
        } else {

        }

    }

}