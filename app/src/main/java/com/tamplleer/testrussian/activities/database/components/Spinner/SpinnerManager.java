package com.tamplleer.testrussian.activities.database.components.Spinner;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import androidx.fragment.app.FragmentManager;

import com.tamplleer.testrussian.R;
import com.tamplleer.testrussian.activities.database.components.Edit_data_base;
import com.tamplleer.testrussian.activities.database.components.ICallBackFragment;
import com.tamplleer.testrussian.activities.database.components.WordFragmentManager;
import com.tamplleer.testrussian.data.DataBaseWords;

import java.util.UUID;

public class SpinnerManager implements ICallBackFragment {
    private Context context;
    private Spinner spinnerType;
    private FragmentManager fragmentManager;
    private String typeBefore = "";
    private DataBaseWords dataBaseWords;
    private WordFragmentManager wordFragmentManager;
    private static final String DEFAULT_WORD = "гЕнезис";

    private SpinnerTypeAdapter spinnerTypeAdapter;

    public SpinnerManager(Context context, DataBaseWords dataBaseWords, WordFragmentManager wordFragmentManager, FragmentManager fragmentManager) {
        this.context = context;
        this.fragmentManager = fragmentManager;
        this.dataBaseWords = dataBaseWords;
        this.wordFragmentManager = wordFragmentManager;
        spinnerType = ((Activity) context).findViewById(R.id.spinner_type_list);
        setSpinner();

    }


    @Override
    public void execute(String message, String newType, String type, UUID id) {
        dataBaseWords.insertWords(DEFAULT_WORD, newType);
        spinnerTypeAdapter.getTypesList().add(newType);
        spinnerTypeAdapter.notifyDataSetChanged();
        selectSpinnerType(newType);

    }

    private void onSelectedAdd() {
        Edit_data_base editDataBaseWord = new Edit_data_base();
        editDataBaseWord.setParams(null, this, null, null);
        editDataBaseWord.show(fragmentManager, "dialog");
    }

    private void setSpinner() {

        spinnerTypeAdapter = new SpinnerTypeAdapter(context, dataBaseWords.findAllTypes());
        spinnerType.setAdapter(spinnerTypeAdapter);
        spinnerType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectSpinnerType(parent.getSelectedItem());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void selectSpinnerType(Object type) {
        if (type == null) {
            onSelectedAdd();
        } else {
            if (!typeBefore.equals(type)) {
                typeBefore = type.toString();
                wordFragmentManager.createFragment(type.toString());
            }
        }
    }

    public String getTypeBefore() {
        return typeBefore;
    }

}
