package com.tamplleer.testrussian;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    ImageButton bminfo;
    Audio audio;
    DialogInMenu dialog;
    AdRequest adRequest;
    private AdView mAdView;
    public Button start;
    ImageButton exit;
    private Handler handler = new Handler();

    // new variables
    Font font;
    TestWords testWords;
    boolean allWords = false;
    boolean rundomWords = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        S.mSettings = getSharedPreferences(S.APP_PREFERENCES1, Context.MODE_PRIVATE);

        font = new Font(this);
        audio = new Audio(0);
        bminfo = findViewById(R.id.vopros);
        start = findViewById(R.id.start);
        exit = findViewById(R.id.exit);


        testWords = new TestWords(this, audio);

        start.setTypeface(font.getFont1());
        AppRater.app_launched(this);

        MobileAds.initialize(this, "ca-app-pub-8909727970839097~4345378585");
        mAdView = (AdView) findViewById(R.id.adView);
        adRequest = new AdRequest.Builder()
                .addTestDevice("E60B48CB85290BEBD01F19B878272929")
                .build();
    }


    public void exit(View view) {// audio button
        if (S.volL == 1) {
            S.volL = 0;
            exit.setBackgroundResource(R.drawable.thetyo);
        } else {
            S.volL = 1;
            exit.setBackgroundResource(R.drawable.soubdee);

        }
    }

    public void start(View view) {
        YoYo.with(Techniques.Bounce)
                .duration(700)
                .repeat(0)
                .playOn(findViewById(R.id.start));
        testWords.start(allWords);
    }

    public void starGA(View view) {
        testWords.start(rundomWords);
    }

    public void randomWords(View view) {
        YoYo.with(Techniques.Bounce)
                .duration(1000)
                .repeat(0)
                .playOn(findViewById(R.id.start30));
        testWords.start(rundomWords);
    }

    public void next(View view) {
        handler.post(() -> YoYo.with(Techniques.BounceInUp)
                .duration(500)
                .repeat(0)
                .playOn(findViewById(R.id.next)));
        testWords.next();

    }

    public void rextV(View view) {
        //t.setText("!!!!!!!!!!!!");
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (S.mSettings.contains(S.APP_PREFERENCES_silence)) {
            S.volL = S.mSettings.getInt(S.APP_PREFERENCES_silence, 0);

            if (S.volL == 1) {
                exit.setBackgroundResource(R.drawable.soubdee);
            } else exit.setBackgroundResource(R.drawable.thetyo);
        }
        if (S.mSettings.contains(S.APP_PREFERENCES_ADD)) {
            S.reclam = S.mSettings.getBoolean(S.APP_PREFERENCES_ADD, false);
        }
        if (S.reclam) mAdView.loadAd(adRequest);
        if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            // Для устройств до Android 5
            audio.createOldSoundPool();
        } else {
            // Для новых устройств
            audio.createNewSoundPool();
        }

        S.mAssetManager = getAssets();

        // получим идентификаторы
        S.anvilSound = audio.loadSound("anvil.mp3");
        S.winSound = audio.loadSound("uhuu.mp3");
        S.winALL = audio.loadSound("eea.mp3");
        S.aS = audio.loadSound("a.mp3");
        S.iS = audio.loadSound("i.mp3");
        S.eS = audio.loadSound("e.mp3");
        S.oS = audio.loadSound("o.mp3");
        S.yS = audio.loadSound("y.mp3");
        S.ieS = audio.loadSound("ie.mp3");
        S.ieaS = audio.loadSound("iea.mp3");
        S.iyS = audio.loadSound("iy.mp3");
        S.iaS = audio.loadSound("ia.mp3");
        S.prav = audio.loadSound("right.mp3");
        S.praveso = audio.loadSound("righteso.mp3");
        S.neprav = audio.loadSound("nea.mp3");
        S.nedovol = audio.loadSound("nedovol.mp3");


    }

    @Override
    protected void onPause() {
        super.onPause();
        save();
        // Toast.makeText(this, "Не могу загрузить файл ",
        //        Toast.LENGTH_SHORT).show();
        //   mSoundPool.release();
        //  mSoundPool = null;
    }

    public void save() {
        SharedPreferences.Editor editor = S.mSettings.edit();
        editor.putInt(S.APP_PREFERENCES_silence, S.volL);
        editor.putBoolean(S.APP_PREFERENCES_ADD, S.reclam);
        editor.apply();

    }

    public void vopros(View view) {
        YoYo.with(Techniques.Bounce)
                .duration(1000)
                .repeat(0)
                .playOn(findViewById(R.id.vopros));

        dialog = new DialogInMenu(this, 0);
        dialog.ad.show();
    }
}

