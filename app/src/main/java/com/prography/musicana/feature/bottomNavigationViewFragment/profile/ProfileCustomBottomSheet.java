package com.prography.musicana.feature.bottomNavigationViewFragment.profile;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.prography.musicana.R;

public class ProfileCustomBottomSheet {

    private BottomSheetListener mListener;
    private Context application;
    private BottomSheetDialog mDialog;

    public ProfileCustomBottomSheet(Context application, BottomSheetDialog dialog, BottomSheetListener listener) {
        this.application = application;
        this.mListener = listener;
        this.mDialog = dialog;


    }

    public void openDialog(String ttl, Boolean isChecked1, String lbl1, Boolean isChecked2, String lbl2, Boolean isChecked3, String lbl3, boolean lbl3visible) {

        View v = LayoutInflater.from(application).inflate(R.layout.profie_custom_bottom_sheet, null);
        mDialog = new BottomSheetDialog(application);
        mDialog.setContentView(v);

        TextView title = v.findViewById(R.id.profile_sheet_title);
        TextView label1 = v.findViewById(R.id.label1);
        TextView label2 = v.findViewById(R.id.label2);
        TextView label3 = v.findViewById(R.id.label3);

        title.setText(ttl);
        label1.setText(lbl1);
        label2.setText(lbl2);
        label3.setText(lbl3);

        Switch aSwitch1 = v.findViewById(R.id.label1_switch);
        Switch aSwitch2 = v.findViewById(R.id.label2_switch);
        Switch aSwitch3 = v.findViewById(R.id.label3_switch);

        if (isChecked1 != null)
            aSwitch1.setChecked(isChecked1);
        if (isChecked2 != null)
            aSwitch2.setChecked(isChecked2);
        if (isChecked3 != null)
            aSwitch3.setChecked(isChecked3);

        aSwitch1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            mListener.onSwitchClicked(1, true, aSwitch1, aSwitch2, aSwitch3);
        });
        aSwitch2.setOnCheckedChangeListener((buttonView, isChecked) -> {
            mListener.onSwitchClicked(2, isChecked, aSwitch1, aSwitch2, aSwitch3);
        });

        aSwitch3.setOnCheckedChangeListener((buttonView, isChecked) -> {
            mListener.onSwitchClicked(3, isChecked, aSwitch1, aSwitch2, aSwitch3);
        });

        if (!lbl3visible) {
            label3.setVisibility(View.GONE);
            aSwitch3.setVisibility(View.GONE);
        }

        mDialog.show();
    }

    public interface BottomSheetListener {
        void onSwitchClicked(int id, boolean checked, Switch s1, Switch s2, Switch s3);
    }

}
