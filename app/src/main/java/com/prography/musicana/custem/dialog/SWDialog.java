package com.prography.musicana.custem.dialog;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.prography.musicana.R;


public class SWDialog extends DialogFragment {

    private Button btn_ok;
    private EditText playList_name;
    private Dilogclicked dilogclicked;


    public SWDialog(Dilogclicked dilogclicked) {
        this.dilogclicked = dilogclicked;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        Dialog builder = new Dialog(requireContext());

        builder.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        View root = requireActivity().getLayoutInflater().inflate(R.layout.sw_add_to_play_list_dialog, null);
        init(root);
        clicked(playList_name);

        builder.setCanceledOnTouchOutside(false);
        builder.setContentView(root);
        return builder;
    }

    public void init(View root) {

        btn_ok = root.findViewById(R.id.btn_OK);
        playList_name = root.findViewById(R.id.playList_name);
        Log.d("TAG", "init: " + playList_name.getText().toString());
    }

    public void clicked(EditText text) {

        btn_ok.setOnClickListener(view -> dilogclicked.OK(playList_name));
    }

    public interface Dilogclicked {

        void OK(EditText platListName);
    }
}
