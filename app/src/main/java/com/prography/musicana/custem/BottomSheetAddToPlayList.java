package com.prography.musicana.custem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.prography.musicana.R;

import java.util.ArrayList;

public class BottomSheetAddToPlayList {

    private Context application;
    private BottomSheetDialog mDialog;
    private BottomSheetAddToPlayList.BottomSheetAddToPlayListMethode mListener;

    private EditText editText;

    private TextView tv_add_to_play_list;
    private RecyclerView recyclerView;
    ArrayList<String> myListItem;


    public BottomSheetAddToPlayList(Context context, BottomSheetDialog dialog, BottomSheetAddToPlayList.BottomSheetAddToPlayListMethode listener) {
        this.application = context;
        this.mListener = listener;
        this.mDialog = dialog;
        myListItem = new ArrayList<>();
    }


    public void openDialog() {

        View root = LayoutInflater.from(application).inflate(R.layout.add_to_play_list_bottom_sheet, null);
        mDialog = new BottomSheetDialog(application);
        mDialog.setContentView(root);

        find(root);
        lisener();
        mDialog.show();

    }


    public void find(View v) {
        tv_add_to_play_list = v.findViewById(R.id.tv_new_list);
        recyclerView = v.findViewById(R.id.rv_all_my_play_list);
    }

    public void lisener() {
        tv_add_to_play_list.setOnClickListener(v -> {
            mListener.addtoplayList();
            mDialog.dismiss();
        });

    }

    public void createRV() {
        recyclerView.setLayoutManager(new LinearLayoutManager(application));

    }


    public void setTextView(EditText tv_name) {
        this.editText = tv_name;
    }

    public EditText getEditText() {
        return editText;
    }

    public interface BottomSheetAddToPlayListMethode {

        void addtoplayList();


    }

}
