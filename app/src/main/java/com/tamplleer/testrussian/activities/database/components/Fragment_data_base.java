package com.tamplleer.testrussian.activities.database.components;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.tamplleer.testrussian.R;

import java.util.UUID;


public class Fragment_data_base extends Fragment {
    private String stringWord = "";



    private TextView textView;
    View v;
    CheckBox checkBox;
    private boolean checked = true;
    // private DataBaseWords dataBaseWords;
    private String type;
    private Button buttonDelete, buttonEdit;
    private ICallBackFragment callBack;
    private UUID key;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_data_base, container, false);
        textView = v.findViewById(R.id.data_base_text_fragment);
        checkBox = v.findViewById(R.id.checkBox_fragment);
        buttonDelete = v.findViewById(R.id.fragment_database_delete);
        buttonEdit = v.findViewById(R.id.fragment_database_edit);
        textView.setText(stringWord);
        checkBox.setChecked(checked);
        checkBox.setOnClickListener(this::onCheck);
        buttonDelete.setOnClickListener(this::onDelete);
        buttonEdit.setOnClickListener(this::onEdit);
        return v;
    }


    public void setParams(Pair<String, Boolean> params, String type, ICallBackFragment callBack, UUID key) {
        this.type = type;
        this.stringWord = params.first;
        this.checked = params.second;
        //  this.dataBaseWords = dataBaseWords;
        this.callBack = callBack;
        this.key = key;


    }

    public void onCheck(View v) {
        if (checkBox.isChecked()) {
            callBack.execute("enableTrue", stringWord, type, key);

        } else {
            callBack.execute("enableFalse", stringWord, type, key);

        }
    }

    public void onEdit(View v) {
        callBack.execute("edit", stringWord, type, key);
    }

    public void onDelete(View v) {

        callBack.execute("delete", stringWord, type, key);
    }
    public void setText(String text){
       textView.setText(text);
    }

}
