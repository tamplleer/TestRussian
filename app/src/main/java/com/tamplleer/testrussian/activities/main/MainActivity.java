package com.tamplleer.testrussian.activities.main;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.tamplleer.testrussian.activities.main.components.AnimationObject;
import com.tamplleer.testrussian.activities.main.components.ObjectsInLayout;
import com.tamplleer.testrussian.utils.AppRater;
import com.tamplleer.testrussian.utils.Audio;
import com.tamplleer.testrussian.utils.DialogInMenu;
import com.tamplleer.testrussian.R;
import com.tamplleer.testrussian.S;
import com.tamplleer.testrussian.activities.main.components.TestOperations;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    Audio audio;
    DialogInMenu dialog;
    AdRequest adRequest;
    private AdView mAdView;

    private Handler handler = new Handler();
    private ObjectsInLayout objectsInLayout;
    private TestOperations testOperations;
    private final boolean ALL_WORDS = false;
    private final boolean RANDOM_WORLD = true;

    AnimationObject animationObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        S.mSettings = getSharedPreferences(S.APP_PREFERENCES1, Context.MODE_PRIVATE);
        objectsInLayout = new ObjectsInLayout(this);
        audio = new Audio(this, getAssets());
        animationObject = new AnimationObject();

        testOperations = new TestOperations(this, audio, objectsInLayout);

        AppRater.app_launched(this);
        MobileAds.initialize(this, "ca-app-pub-8909727970839097~4345378585");
        mAdView = findViewById(R.id.adView);
        adRequest = new AdRequest.Builder()
                .addTestDevice("90B58081B8CCAA1A4130F5271615A282")
                .build();
    }


    public void changeVolume(View view) {
        if (audio.getVolume() == 1) {
            audio.setVolume(0);
            objectsInLayout.getSoundButton().setBackgroundResource(R.drawable.ic_sound_off);
        } else {
            audio.setVolume(1);
            objectsInLayout.getSoundButton().setBackgroundResource(R.drawable.ic_sound_on);

        }
    }

    public void start(View view) {
        objectsInLayout.getLottieAnimationView().playAnimation();
        animationObject.bounce(objectsInLayout.getStartTestButton());
        testOperations.start(ALL_WORDS);
    }

    public void starGA(View view) {
        testOperations.start(RANDOM_WORLD);
    }

    public void randomWords(View view) {
        animationObject.bounce((Button) findViewById(R.id.start30));
        testOperations.start(RANDOM_WORLD);
    }

    public void next(View view) {
        handler.post(() -> animationObject.bounceInUp(findViewById(R.id.next)));
        testOperations.next();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (S.mSettings.contains(S.APP_PREFERENCES_silence)) {
            audio.setVolume(S.mSettings.getInt(S.APP_PREFERENCES_silence, 0));
            if (audio.getVolume() == 1) {
                objectsInLayout.getSoundButton().setBackgroundResource(R.drawable.ic_sound_on);
            } else objectsInLayout.getSoundButton().setBackgroundResource(R.drawable.ic_sound_off);
        }
        if (S.mSettings.contains(S.APP_PREFERENCES_ADD)) {
            S.reclam = S.mSettings.getBoolean(S.APP_PREFERENCES_ADD, true);
        }
        if (S.reclam) mAdView.loadAd(adRequest);


    }

    @Override
    protected void onPause() {
        super.onPause();
        save();
    }

    public void save() {
        SharedPreferences.Editor editor = S.mSettings.edit();
        editor.putInt(S.APP_PREFERENCES_silence, audio.getVolume());
        editor.putBoolean(S.APP_PREFERENCES_ADD, S.reclam);
        editor.apply();

    }

    public void questionsButton(View view) {
        animationObject.bounce(objectsInLayout.getButtonInfo());
        dialog = new DialogInMenu(this, 0);
        dialog.ad.show();
    }
}

