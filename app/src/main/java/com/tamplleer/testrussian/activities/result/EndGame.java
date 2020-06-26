package com.tamplleer.testrussian.activities.result;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.tamplleer.testrussian.R;
import com.tamplleer.testrussian.SharedPreference;
import com.tamplleer.testrussian.activities.main.MainActivity;
import com.tamplleer.testrussian.activities.result.components.Advertisement;
import com.tamplleer.testrussian.utils.Audio;
import com.tamplleer.testrussian.utils.DialogInMenu;
import com.tamplleer.testrussian.utils.Font;
import com.tamplleer.testrussian.utils.GetScreenSize;

import java.util.Objects;

public class EndGame extends AppCompatActivity {
    TextView t, tt;
    Audio audio;
    GetScreenSize getScreenSize;
    int screenWidth, screenHeight;
    ConstraintLayout constraintLayout;
    private int winResult;
    private ImageButton advertisementButton;
    private boolean[] answerArray;

    Font font;
    private int lengthInScore;
    private String[] wordMassive;
    private Advertisement advertisement;
    private SharedPreference sharedPreference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_end_game);

        sharedPreference = new SharedPreference(this);
        getScreenSize = new GetScreenSize(this);
        screenWidth = getScreenSize.getScreenWidth();
        screenHeight = getScreenSize.getScreenHeight();
        font = new Font(this);

        winResult = getIntent().getExtras().getInt("rightAnswer");
        answerArray = getIntent().getExtras().getBooleanArray("arrayRight");
        lengthInScore = getIntent().getExtras().getInt("lengthInScore");
        wordMassive = getIntent().getExtras().getStringArray("wordArray");

        advertisementButton = findViewById(R.id.advertisement_button);
        audio = new Audio(this, getAssets());

        setTitle(" ");
        constraintLayout = findViewById(R.id.endloy);
        t = findViewById(R.id.endText);
        tt = findViewById(R.id.tt);
        t.setTypeface(font.getFont1());
        tt.setTypeface(font.getFont1());


        makeListWords();

        tt.setText("" + winResult + " / " + lengthInScore);


    }

    @Override
    protected void onStart() {
        super.onStart();
        audio.setVolume(sharedPreference.getVolume());
        try {
            Thread.sleep(300); //Приостанавливает поток на 1 секунду
        } catch (Exception ignored) {
        }

        if (winResult < 20)
            audio.playSound(audio.getSoundNumber("disappointed"));

        if (winResult >= 20) audio.playSound(audio.getSoundNumber("winSound"));
        if (winResult == lengthInScore) audio.playSound(audio.getSoundNumber("TotalWin"));
    }

    private void makeListWords() {
        SpannableStringBuilder ss = new SpannableStringBuilder();
        int start = 0;
        for (int i = 0; i < wordMassive.length; i++) {
            ss.append(wordMassive[i]).append("\n");
            if (answerArray[i]) {
                ss.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.Green)),
                        start, start + wordMassive[i].length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
                start += wordMassive[i].length() + 1;
            } else {
                start += wordMassive[i].length() + 1;
            }


        }
        t.setText(ss);
    }

    public void endExit(View view) {
        Intent intent = new Intent(EndGame.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (sharedPreference.getAdd()) {
            advertisement = new Advertisement(this);
        } else {
            advertisementButton.setEnabled(false);
            advertisementButton.setVisibility(View.INVISIBLE);
        }
    }


    public void mButton(View view) {
        DialogInMenu dialog;
        dialog = new DialogInMenu(this, 1);
        dialog.ad.show();
    }

    public void loadAd() {
        advertisement.show();
    }
}
