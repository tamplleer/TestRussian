package com.tamplleer.testrussian.activities.main;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.tamplleer.testrussian.utils.AppRater;
import com.tamplleer.testrussian.utils.Audio;
import com.tamplleer.testrussian.utils.DialogInMenu;
import com.tamplleer.testrussian.utils.Font;
import com.tamplleer.testrussian.R;
import com.tamplleer.testrussian.S;
import com.tamplleer.testrussian.activities.main.components.TestOperations;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    ImageButton bminfo;
    Audio audio;
    DialogInMenu dialog;
    AdRequest adRequest;
    private AdView mAdView;
    public Button start;
    ImageButton soundButton;
    private Handler handler = new Handler();
    LottieAnimationView lottieAnimationView;

    // new variables
    Font font;
    TestOperations testOperations;
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
        audio = new Audio(this, getAssets());
        bminfo = findViewById(R.id.vopros);
        start = findViewById(R.id.start);
        soundButton = findViewById(R.id.exit);
        lottieAnimationView = findViewById(R.id.loadingAnim);

        testOperations = new TestOperations(this, audio);
        start.setTypeface(font.getFont1());

        AppRater.app_launched(this);
        MobileAds.initialize(this, "ca-app-pub-8909727970839097~4345378585");
        mAdView = findViewById(R.id.adView);
        adRequest = new AdRequest.Builder()
                .addTestDevice("90B58081B8CCAA1A4130F5271615A282")
                .build();
    }


    public void changeVolume(View view) {
        if (S.volL == 1) {
            S.volL = 0;
            soundButton.setBackgroundResource(R.drawable.ic_sound_off);
        } else {
            S.volL = 1;
            soundButton.setBackgroundResource(R.drawable.ic_sound_on);

        }
    }

    public void start(View view) {
        lottieAnimationView.playAnimation();
        YoYo.with(Techniques.Bounce)
                .duration(700)
                .repeat(0)
                .playOn(findViewById(R.id.start));
        testOperations.start(allWords);
    }

    public void starGA(View view) {
        testOperations.start(rundomWords);
    }

    public void randomWords(View view) {
        YoYo.with(Techniques.Bounce)
                .duration(1000)
                .repeat(0)
                .playOn(findViewById(R.id.start30));
        testOperations.start(rundomWords);
    }

    public void next(View view) {

        handler.post(() -> YoYo.with(Techniques.BounceInUp)
                .duration(500)
                .repeat(0)
                .playOn(findViewById(R.id.next)));
        testOperations.next();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (S.mSettings.contains(S.APP_PREFERENCES_silence)) {
            S.volL = S.mSettings.getInt(S.APP_PREFERENCES_silence, 0);

            if (S.volL == 1) {
                soundButton.setBackgroundResource(R.drawable.ic_sound_on);
            } else soundButton.setBackgroundResource(R.drawable.ic_sound_off);
        }
        if (S.mSettings.contains(S.APP_PREFERENCES_ADD)) {
            S.reclam = S.mSettings.getBoolean(S.APP_PREFERENCES_ADD, false);
        }
        if (S.reclam) mAdView.loadAd(adRequest);


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
                .playOn(bminfo);

        dialog = new DialogInMenu(this, 0);
        dialog.ad.show();
    }
}

