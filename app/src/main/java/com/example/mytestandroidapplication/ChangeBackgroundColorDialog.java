package com.example.mytestandroidapplication;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import java.util.Objects;

public class ChangeBackgroundColorDialog extends DialogFragment {


    public interface ChangeBackgroundColorDialogListener {
        public void onDialogWhiteClick(DialogFragment dialog);

        public void onDialogBlueClick(DialogFragment dialog);

        public void onDialogGreenClick(DialogFragment dialog);
    }


    ChangeBackgroundColorDialogListener mListener;
    public Button btnBlue, btnWhite, btnGreen;

    @Override
    public void onAttach(@NonNull Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (ChangeBackgroundColorDialogListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement ChangeBackgroundColorDialogListener");
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Objects.requireNonNull(getDialog()).setTitle("Title!");
        View v = inflater.inflate(R.layout.change_background_color_dialog, null);
        btnWhite = v.findViewById(R.id.btnWhite);
        btnWhite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onDialogWhiteClick(ChangeBackgroundColorDialog.this);
            }
        });
        btnBlue = v.findViewById(R.id.btnBlue);
        btnBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onDialogBlueClick(ChangeBackgroundColorDialog.this);
            }
        });
        btnGreen = v.findViewById(R.id.btnGreen);
        btnGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onDialogGreenClick(ChangeBackgroundColorDialog.this);
            }
        });
        return v;
    }

}


