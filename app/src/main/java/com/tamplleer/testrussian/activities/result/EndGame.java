package com.tamplleer.testrussian.activities.result;

import android.content.Intent;
import android.content.SharedPreferences;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.tamplleer.testrussian.utils.Audio;
import com.tamplleer.testrussian.utils.DialogInMenu;
import com.tamplleer.testrussian.utils.Font;
import com.tamplleer.testrussian.utils.GetScreenSize;
import com.tamplleer.testrussian.activities.main.MainActivity;
import com.tamplleer.testrussian.R;
import com.tamplleer.testrussian.S;
import com.tamplleer.testrussian.word.LetterEnd;

import java.util.Objects;

public class EndGame extends AppCompatActivity implements RewardedVideoAdListener {
    TextView t, tt;
    Audio audio;
    GetScreenSize getScreenSize;
    int screenWidth, screenHeight;
    ConstraintLayout constraintLayout;
    private int winResult;
    private boolean[] answerArray;
    int y, x;
    LetterEnd text1[];
    int wordpokaz = 0;
    int s = 0;
    boolean reclam;
    public static RewardedVideoAd mAd;
    ImageButton mButton;
    // new variables
    Font font;
    private int lengthInScore;
    private String [] wordMassive;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_end_game);
        getScreenSize = new GetScreenSize(this);
        screenWidth = getScreenSize.getScreenWidth();
        screenHeight = getScreenSize.getScreenHeight();
        font = new Font(this);
        audio = new Audio(this, getAssets());
        winResult = getIntent().getExtras().getInt("rightAnswer");
        answerArray = getIntent().getExtras().getBooleanArray("arrayRight");
        lengthInScore = getIntent().getExtras().getInt("lengthInScore");
        wordMassive=getIntent().getExtras().getStringArray("wordArray");

        MobileAds.initialize(this, "ca-app-pub-8909727970839097/9184677267");
        mAd = MobileAds.getRewardedVideoAdInstance(this);
        mAd.setRewardedVideoAdListener(this);
        mAd.loadAd("ca-app-pub-8909727970839097/9184677267",
                new AdRequest.Builder()
                        .addTestDevice("E60B48CB85290BEBD01F19B878272929")
                        .build());
        //"ca-app-pub-3940256099942544/5224354917",


        setTitle(" ");
        constraintLayout = (ConstraintLayout) findViewById(R.id.endloy);
        // constraintLayout.setMinHeight(350);
        //  constraintLayout.setMaxWidth(100);



        t = findViewById(R.id.endText);
        tt = findViewById(R.id.tt);
        mButton = findViewById(R.id.mButton);
        mButton.setEnabled(false);
        t.setTypeface(font.getFont1());
        tt.setTypeface(font.getFont1());
        x = screenWidth / 3 + screenWidth / 10;
        y = screenHeight / 30;
        text1 = new LetterEnd[wordMassive.length];
        for (int i = 0; i < 30; i++) {

            text1[i] = new LetterEnd(this, x, y, wordMassive[i], screenHeight / 30, answerArray[i], false);
            y += screenHeight / 43;


        }

        tt.setText("" + winResult + " / " + lengthInScore);

        if (winResult < 20)
            audio.playSound(audio.getSoundNumber("disappointed"));
        if (winResult >= 20) audio.playSound(audio.getSoundNumber("winSound"));
        if (winResult == lengthInScore) audio.playSound(audio.getSoundNumber("TotalWin"));


    }

    public void endExit(View view) {
        Intent intent = new Intent(EndGame.this,
                MainActivity.class);


        startActivity(intent);
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        save();
    }

    public void save() {
        SharedPreferences.Editor editor = S.mSettings.edit();
        editor.putBoolean(S.APP_PREFERENCES_ADD, reclam);
        editor.apply();

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (S.mSettings.contains(S.APP_PREFERENCES_ADD)) {
            S.reclam = S.mSettings.getBoolean(S.APP_PREFERENCES_ADD, true);
        }
    }

    void nextBack() {
        s = 0;
        for (int i = 0; i < 30; i++) {
            text1[i] = new LetterEnd(this, x, y, "        ", screenHeight / 30, answerArray[s], true);
            y += screenHeight / 43;
        }
        y = screenHeight / 30;
        for (int i = 0; i < 30; i++) {
            s = i + wordpokaz * 30;
            // if (s>S.wordMassive.length)
            if (wordMassive.length > s) {

                text1[i] = new LetterEnd(this, x, y, wordMassive[s], screenHeight / 30, answerArray[s], false);
                y += screenHeight / 43;
            }

        }
    }

    public void nextClick(View view) {
        YoYo.with(Techniques.FadeInRight)
                .duration(200)
                .repeat(0)
                .playOn(findViewById(R.id.right));
        if (30 < wordMassive.length && s < wordMassive.length) {
            y = screenHeight / 30;
            wordpokaz += 1;
            nextBack();
        }
    }

    public void iBack(View view) {
        YoYo.with(Techniques.FadeInLeft)
                .duration(200)
                .repeat(0)
                .playOn(findViewById(R.id.left));
        if (30 < wordMassive.length && wordpokaz != 0) {
            y = screenHeight / 30;
            wordpokaz -= 1;
            nextBack();
        }
    }

    public void mButton(View view) {
        DialogInMenu dialog;
        dialog = new DialogInMenu(this, 1);
        dialog.ad.show();
    }

    public void loadAd() {
        mAd.show();
    }

    //todo make new class for adds
    @Override
    public void onRewardedVideoAdLoaded() {
        Toast.makeText(this, "Load", Toast.LENGTH_SHORT).show();
        mButton.setEnabled(true);
    }

    @Override
    public void onRewardedVideoAdOpened() {
    }

    @Override
    public void onRewardedVideoStarted() {
    }

    @Override
    public void onRewardedVideoAdClosed() {
        mButton.setEnabled(false);
        reclam = false;
        Toast.makeText(this, "Вы больше не увидите рекламму!",
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRewarded(RewardItem rewardItem) {
    }

    @Override
    public void onRewardedVideoAdLeftApplication() {
    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int i) {
        Toast.makeText(this, "Вы не посмотрели рекламму!",
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRewardedVideoCompleted() {
    }


}
