package com.tamplleer.testrussian.activities.main;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.tamplleer.testrussian.AnimationObject;
import com.tamplleer.testrussian.R;
import com.tamplleer.testrussian.S;
import com.tamplleer.testrussian.SharedPreference;
import com.tamplleer.testrussian.activities.main.components.Advertisement;
import com.tamplleer.testrussian.activities.main.components.ObjectsInLayout;
import com.tamplleer.testrussian.activities.main.components.TestOperations;
import com.tamplleer.testrussian.utils.Audio;
import com.tamplleer.testrussian.utils.DialogInMenu;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private Audio audio;
    private DialogInMenu dialog;
    private Handler handler = new Handler();
    private ObjectsInLayout objectsInLayout;
    private TestOperations testOperations;
    private final boolean ALL_WORDS = false;
    private final boolean RANDOM_WORLD = true;
    Advertisement advertisement;
    AnimationObject animationObject;
    private SharedPreference sharedPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        sharedPreference = new SharedPreference(this);
        objectsInLayout = new ObjectsInLayout(this);
        audio = new Audio(this, getAssets());
        animationObject = new AnimationObject();
        advertisement = new Advertisement(this);
        testOperations = new TestOperations(this, audio, objectsInLayout);
  
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

        audio.setVolume(sharedPreference.getVolume());
        if (audio.getVolume() == 1) {
            objectsInLayout.getSoundButton().setBackgroundResource(R.drawable.ic_sound_on);
        } else objectsInLayout.getSoundButton().setBackgroundResource(R.drawable.ic_sound_off);
        S.reclam = sharedPreference.getAdd();
        if (S.reclam)advertisement.show();


    }

    @Override
    protected void onPause() {
        super.onPause();
        sharedPreference.save(audio.getVolume());
    }

    public void questionsButton(View view) {
        animationObject.bounce(objectsInLayout.getButtonInfo());
        dialog = new DialogInMenu(this, 0);
        dialog.ad.show();
    }


}

