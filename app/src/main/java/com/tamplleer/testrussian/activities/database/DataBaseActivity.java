package com.tamplleer.testrussian.activities.database;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.tamplleer.testrussian.R;
import com.tamplleer.testrussian.firbase.database.DataBaseWords;

public class DataBaseActivity extends AppCompatActivity {
    DataBaseWords dataBaseWords;
    TextView textView;
    Button buttonLoadFireBase;
    EditText editTextAddWord, editTextAddListType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_base);
        dataBaseWords = new DataBaseWords(this);
        textView = findViewById(R.id.databasewords);
        buttonLoadFireBase = findViewById(R.id.button_load_from_firebase);
        editTextAddListType = findViewById(R.id.list_type);
        editTextAddWord = findViewById(R.id.add_word);
        showWords("main");
    }

    private void showWords(String type) {

        textView.setText(dataBaseWords.readFromDataBase(type).toString());
    }

    public void loadFireBase(View view) {
        dataBaseWords.insertFromFireBase();
        showWords(editTextAddListType.getText().toString());
    }

    public void addWord(View view) {
        dataBaseWords.insertWords(editTextAddWord.getText().toString(), editTextAddListType.getText().toString());
    }

    public void showWords(View view) {
        showWords(editTextAddListType.getText().toString());
    }
}
