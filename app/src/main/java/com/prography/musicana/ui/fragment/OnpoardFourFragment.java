package com.prography.musicana.ui.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prography.musicana.R;
import com.prography.musicana.databinding.FragmentOnpoardFourBinding;
import com.prography.musicana.data.onPording.SendDtatToActivity;
import com.prography.musicana.ui.activity.PrivacyPolicyActivity;
import com.prography.musicana.ui.activity.TermsConditionsActivity;


public class OnpoardFourFragment extends Fragment {

    private FragmentOnpoardFourBinding binding;
    private SendDtatToActivity sendDtatToActivity;
    private static final String TAG = "OnpoardFourFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentOnpoardFourBinding.inflate(getLayoutInflater());
        sendDtatToActivity = SendDtatToActivity.getInstance();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (sendDtatToActivity.getItems()!=null){

            binding.det.setText(sendDtatToActivity.getItems().get(0).getDetails());
            binding.txt1.setText(sendDtatToActivity.getItems().get(0).getTitle());

        }else{
            Log.d(TAG, "onViewCreated:  No Data");
        }

        SpannableStringBuilder spannableString = new SpannableStringBuilder("By clicking on Get started button you accept Our terms and conditions and privacy policy of Musicana");


        spannableString.setSpan(new ClickableSpan() {
                                    @Override
                                    public void onClick(@NonNull View view) {
                                        getActivity().startActivity(new Intent(getActivity(), TermsConditionsActivity.class));
                                    }
                                },
                49,
                69,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        spannableString.setSpan(new ClickableSpan() {
                                    @Override
                                    public void onClick(@NonNull View view) {
                                        getActivity().startActivity(new Intent(getActivity(), PrivacyPolicyActivity.class));
                                    }
                                },
                74,
                88,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.textColor_or_w, null)),
                49,
                69,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.textColor_or_w, null)),
                74,
                88,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        binding.tvPageOne.setText(spannableString);
        binding.tvPageOne.setMovementMethod(LinkMovementMethod.getInstance());


    }
}