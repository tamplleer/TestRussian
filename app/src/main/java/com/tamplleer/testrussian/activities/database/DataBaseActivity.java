package com.tamplleer.testrussian.activities.database;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.tamplleer.testrussian.R;
import com.tamplleer.testrussian.activities.database.components.Spinner.SpinnerManager;
import com.tamplleer.testrussian.activities.database.components.WordFragmentManager;
import com.tamplleer.testrussian.activities.main.MainActivity;
import com.tamplleer.testrussian.activities.main.components.LetterChange;
import com.tamplleer.testrussian.data.DataBaseWords;

public class DataBaseActivity extends AppCompatActivity {
    DataBaseWords dataBaseWords;
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
        spinnerManager = new SpinnerManager(this, dataBaseWords, wordFragmentManager, getSupportFragmentManager());
        startThreadAndLoadFragments();

    }

    private void startThreadAndLoadFragments() {
        Thread thread = new Thread(() -> {
            handler.post(() -> {
                showWordsInFragments("Банк заданий ЕГЭ");

                // lottieAnimationView.playAnimation();
            });

        });
        thread.start();
        if (!thread.isAlive()) {
            Toast.makeText(this, "thread Interrupted", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();


    }

    private void showWordsInFragments(String type) {

        wordFragmentManager.createFragments(type);
    }

    public void addWord(View view) {
        if (testIsBigLetterInWord()){
            dataBaseWords.insertWords(editTextAddWord.getText().toString(), spinnerManager.getTypeBefore());
            wordFragmentManager.addWord(editTextAddWord.getText().toString(), spinnerManager.getTypeBefore());
            editTextAddWord.setText("");
            editTextAddWord.clearFocus();
        }
        else {
            Toast.makeText(this, "В слове должна быть большая гласная бука, чтобы указать на ударение!", Toast.LENGTH_LONG).show();
            editTextAddWord.setHighlightColor(R.color.Green);
        }

    }

    private boolean testIsBigLetterInWord() {

        for (char c : editTextAddWord.getText().toString().toCharArray()) {
            if (Character.isUpperCase(c)) {
                for (char c1 : LetterChange.BIG_LETTER) {
                    if (c1 == c) {
                        return true;

                    }
                }
            }

        }
        return false;
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(DataBaseActivity.this,
                MainActivity.class);
        startActivity(intent);
        finish();
    }


}
