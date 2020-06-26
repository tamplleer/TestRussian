package com.tamplleer.testrussian.activities.database;

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

import java.util.UUID;


public class Edit_data_base extends AppCompatDialogFragment {
    private View v;
    private EditText editText;
    private String word;
    private Button backButton, confirmButton;
    private CallBack callBack;
    private String type;
    private UUID uuid;
    private AlertDialog alert;

/*
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_edit_data_base, container, false);
        editText = v.findViewById(R.id.edit_data_base_dialog);
        editText.requestFocus();
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        return v;
    }
*/

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
             /*     .setTitle("Новое слово")
                .setNegativeButton("Отмена", (dialog, which) -> {

                })
                .setPositiveButton("Добавить", (dialog, which) -> {

                });*/
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

    public void setParams(String word, CallBack callBack, String type, UUID uuid) {
        this.word = word;
        this.callBack = callBack;
        this.type = type;
        this.uuid = uuid;

    }

    public void onBackButton(View v) {
        alert.cancel();

    }

    public void onConfirmButton(View v) {
        callBack.execute(word, editText.getText().toString(), type, uuid);
        alert.cancel();

    }
}
