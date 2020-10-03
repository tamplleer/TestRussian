package com.tamplleer.testrussian.activities.database.components.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.tamplleer.testrussian.R;
import com.tamplleer.testrussian.activities.database.components.ICallBackFragment;

import java.util.UUID;


public class Edit_data_base extends AppCompatDialogFragment {
    private View v;
    private EditText editText;
    private String word;
    private Button backButton, confirmButton;
    private ICallBackFragment callBack;
    private String type;
    private UUID uuid;
    private AlertDialog alert;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.fragment_edit_data_base, null);
        builder.setView(v);
        editText = v.findViewById(R.id.edit_data_base_dialog);
        editText.requestFocus();
        editText.setText(word);
        backButton = v.findViewById(R.id.back_button_dialog);
        confirmButton = v.findViewById(R.id.edit_button_dialog);
        backButton.setOnClickListener(this::onBackButton);
        confirmButton.setOnClickListener(this::onConfirmButton);
        alert = builder.create();


        return alert;
    }

    public void setParams(String word, ICallBackFragment callBack, String type, UUID uuid) {
        this.word = word;
        this.callBack = callBack;
        this.type = type;
        this.uuid = uuid;

    }

    public void onBackButton(View v) {
        alert.cancel();
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    public void onConfirmButton(View v) {
        callBack.execute(word, editText.getText().toString(), type, uuid);
        alert.cancel();
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

    }
}
