package com.prography.musicana.custem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.prography.musicana.R;


public class BottomSheetSearsh {

    private Context application;

    private BottomSheetDialog mDialog;
    private bottomSheetSearsh mListener;

    public BottomSheetSearsh(Context application, BottomSheetDialog dialog, bottomSheetSearsh listener) {
        this.application = application;
        this.mListener = listener;
        this.mDialog = dialog;


    }


    public void openDialog(String title, String text) {

        View view = LayoutInflater.from(application).inflate(R.layout.music_profril_layout_sheet, null);
        mDialog = new BottomSheetDialog(application);
        mDialog.setContentView(view);

        mDialog.show();


    }


    public interface bottomSheetSearsh {

        void searshByNumber(String num_facility);

    }


}
