package com.tamplleer.testrussian.activities.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.tamplleer.testrussian.AnimationObject;
import com.tamplleer.testrussian.R;
import com.tamplleer.testrussian.SharedPreference;
import com.tamplleer.testrussian.activities.database.DataBaseActivity;
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
        setMenuStrips();

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


    public void starGA(View view) {
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
        if (sharedPreference.getAdd()) advertisement.show();


    }

    @Override
    protected void onPause() {
        super.onPause();
        sharedPreference.save(audio.getVolume());
    }

    private void setMenuStrips() {
        BoomMenuButton bmb;
        int[] drawableIcon = {R.drawable.ads,
                R.drawable.ic_sound_on, R.drawable.ic_alienbig, R.drawable.ic_sound_off};
        int[] stringName = {R.string.popup_button,
                R.string.popup_button2, R.string.popup_button3, R.string.popup_button4};
        int[] stringDescription = {R.string.popup_button_description,
                R.string.popup_button2_description, R.string.popup_button3_description, R.string.popup_button3_description};
        // int[] buttonColor={R.color.DarkSlateGray};
        bmb = findViewById(R.id.bmb);
        for (int i = 0; i < bmb.getPiecePlaceEnum().pieceNumber(); i++) {
            HamButton.Builder builder = new HamButton.Builder()
                    .normalColorRes(R.color.Khaki)
                    .normalTextColor(getResources().getColor(R.color.DarkSlateGray))
                    .subNormalTextColorRes(R.color.DarkSlateGray)
                    .normalImageRes(drawableIcon[i])
                    .normalTextRes(stringName[i])
                    .subNormalTextRes(stringDescription[i])
                    .listener(index -> {
                        execute(index);
                    });

            bmb.addBuilder(builder);
        }
    }

    private void execute(int index) {
        final int allWords = 0;
        final int randomWords = 1;
        final int makeListWords = 2;
        final int questions = 3;
        switch (index) {
            case allWords: {
                testOperations.start(ALL_WORDS);
                objectsInLayout.getLottieAnimationView().playAnimation();
            }
            break;
            case randomWords:
                testOperations.start(RANDOM_WORLD);
                break;
            case makeListWords: {
                Intent intent = new Intent(MainActivity.this,
                        DataBaseActivity.class);
                startActivity(intent);
                finish();
            }
            break;
            case questions: {
                dialog = new DialogInMenu(this, 0);
                dialog.ad.show();
            }
            break;
            default:
                testOperations.start(RANDOM_WORLD);
        }
    }
}

