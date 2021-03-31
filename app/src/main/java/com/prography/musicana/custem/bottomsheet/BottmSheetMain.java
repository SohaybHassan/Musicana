package com.prography.musicana.custem.bottomsheet;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.prography.musicana.R;
import com.prography.musicana.databinding.MusicProfrilLayoutSheetBinding;

public class BottmSheetMain {


    Context context;
    BottomSheetDialog bottomSheetDialog;

    public BottmSheetMain(Context context, BottomSheetDialog bottomSheetDialog) {
        this.context = context;
        this.bottomSheetDialog = bottomSheetDialog;
    }


    public void opendialog() {

        View root = LayoutInflater.from(context).inflate(R.layout.music_profril_layout_sheet, null);
        bottomSheetDialog = new BottomSheetDialog(context);
        bottomSheetDialog.setContentView(root);

        bottomSheetDialog.show();


    }

}
