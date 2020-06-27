package com.tamplleer.testrussian.activities.database.components;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.tamplleer.testrussian.R;

import java.util.Objects;

public class WordFragment extends Fragment {
    private String stringWord = "";

    @SuppressLint("ResourceAsColor")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Context context = Objects.requireNonNull(getActivity()).getApplicationContext();
        LinearLayout layout = new LinearLayout(context);
        layout.setBackgroundColor(R.color.DarkSlateGray);
        TextView text = new TextView(context);
        text.setText(stringWord);
        Button delete = new Button(context);
        Button rewriteWord = new Button(context);
        layout.addView(text);
        layout.addView(delete);
        layout.addView(rewriteWord);

        return layout;
    }

    public void addString(final String stringWord) {
        this.stringWord = stringWord;
    }


}