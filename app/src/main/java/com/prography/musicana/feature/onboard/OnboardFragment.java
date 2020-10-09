package com.prography.musicana.feature.onboard;

import android.graphics.Color;
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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.prography.musicana.R;
import com.prography.musicana.databinding.ActivityOnboardingBinding;
import com.prography.musicana.databinding.FragmentOnboardBinding;
import com.prography.musicana.databinding.FragmentOnpoardFourBinding;


public class OnboardFragment extends Fragment {
FragmentOnboardBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= FragmentOnboardBinding.inflate(getLayoutInflater());
        return   binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        SpannableStringBuilder  spannableString=new SpannableStringBuilder ("By clicking on Get started button you accept Our terms and conditions and privacy policy of Musicana");


     //   ForegroundColorSpan foregroundColorSpan=new ForegroundColorSpan(getResources().getColor(R.color.textColor_or_w,null));


//        ClickableSpan span1=new ClickableSpan() {
//            @Override
//            public void onClick(@NonNull View view) {
//                Toast.makeText(getContext(), "text one ", Toast.LENGTH_SHORT).show();
//            }
//        };
//        ClickableSpan span2=new ClickableSpan() {
//            @Override
//            public void onClick(@NonNull View view) {
//                Toast.makeText(getContext(), "text Tow ", Toast.LENGTH_SHORT).show();
//            }
//        };



       // ss.setSpan(span1, 49, 68, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

//SPAN_EXCLUSIVE_INCLUSIVE
        //ss.setSpan(span2, 74, 87, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
       // ss.setSpan(foregroundColorSpan, 74, 87, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


    }
}