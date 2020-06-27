package com.tamplleer.testrussian.activities.database.components.Spinner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.tamplleer.testrussian.R;

import java.util.ArrayList;

public class SpinnerTypeAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private ArrayList<String> typesList;

    public SpinnerTypeAdapter(Context context, ArrayList<String> typesList) {
        this.typesList = typesList;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return typesList.size() + 1;
    }

    @Override
    public Object getItem(int position) {
        if (position == typesList.size()) {
            return null;
        }
        return typesList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v;

        if (position == typesList.size()) {
            v = layoutInflater.inflate(R.layout.spinner_type_add_item, parent, false);
        } else {
            String type = (String) getItem(position);
            v = layoutInflater.inflate(R.layout.spinner_type_word_item, parent, false);
            TextView textView = v.findViewById(R.id.text_type_spinner);
            textView.setText(type);
        }

        return v;
    }

    public ArrayList<String> getTypesList() {
        return typesList;
    }
}
