package com.tamplleer.testrussian.activities.database;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.tamplleer.testrussian.R;
import com.tamplleer.testrussian.activities.database.components.Spinner.SpinnerManager;
import com.tamplleer.testrussian.activities.database.components.WordFragmentManager;
import com.tamplleer.testrussian.activities.main.MainActivity;
import com.tamplleer.testrussian.data.DataBaseWords;

public class DataBaseActivity extends AppCompatActivity {
    DataBaseWords dataBaseWords;
    Button buttonLoadFireBase;
    EditText editTextAddWord;
    private WordFragmentManager wordFragmentManager;
    private Handler handler = new Handler();
    private LottieAnimationView lottieAnimationView;


    private SpinnerManager spinnerManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_base);
        lottieAnimationView = findViewById(R.id.loadingAnim);
        editTextAddWord = findViewById(R.id.add_word);

        dataBaseWords = new DataBaseWords(this);
        wordFragmentManager = new WordFragmentManager(this, dataBaseWords, getSupportFragmentManager());

        spinnerManager = new SpinnerManager(this,dataBaseWords,wordFragmentManager, getSupportFragmentManager());
        Thread thread = new Thread(() -> {
            handler.post(() -> {
                showWordsInFragments("main");

                // lottieAnimationView.playAnimation();
            });

        });
        thread.start();
        if (!thread.isAlive()) {
            Toast.makeText(this, "thread Interrupted", Toast.LENGTH_LONG).show();
        }

       // setSpinnerList();


    }

    @Override
    protected void onStart() {
        super.onStart();


    }

    private void showWordsInFragments(String type) {

        // wordFragment.createFragment(dataBaseWords.readFromDataBase(type));

        wordFragmentManager.createFragment(type);
    }

/*    public void loadFireBase(View view) {
        // dataBaseWords.insertFromFireBase();
        showWords(editTextAddListType.getText().toString());
    }*/

    public void addWord(View view) {
        dataBaseWords.insertWords(editTextAddWord.getText().toString(), spinnerManager.getTypeBefore());
        wordFragmentManager.addWord(editTextAddWord.getText().toString(), spinnerManager.getTypeBefore());
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(DataBaseActivity.this,
                MainActivity.class);
        startActivity(intent);
        finish();
    }

  /*  private void setSpinnerList() {

    }*/


}
