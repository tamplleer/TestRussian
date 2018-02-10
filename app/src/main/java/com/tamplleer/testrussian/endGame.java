package com.tamplleer.testrussian;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class endGame extends AppCompatActivity {
TextView t,tt;

    public SoundPool mSoundPool;
    public AssetManager mAssetManager;
    public static int mCatSound, winSound,winALL, prav, praveso, neprav,anvilSound,aS,iS,eS,oS,yS,ieS,iaS,iyS,ieaS;
    public int mStreamID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // getSupportActionBar().hide();
        setContentView(R.layout.activity_end_game);
        t = (TextView) findViewById(R.id.endText);
        tt = (TextView) findViewById(R.id.tt);
        t.setText(R.string.GOVER);
        tt.setText(""+S.win+" / "+S.lengsInScore);
        if (S.win<10) playSound(winSound);
        if (S.win>=25)playSound(winSound);
        if (S.win==S.lengsInScore)playSound(winALL);
    }

    public void endexit(View view) {
     //   Intent intent = new Intent(endGame.this,
    //            MainActivity.class);
     //   startActivity(intent);
        finish();
    }





    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void createNewSoundPool() {
        AudioAttributes attributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();
        mSoundPool = new SoundPool.Builder()
                .setAudioAttributes(attributes)
                .build();
    }

    @SuppressWarnings("deprecation")
    public void createOldSoundPool() {
        mSoundPool = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
    }

    public int playSound(int sound) {
        if (sound > 0) {
            mStreamID = mSoundPool.play(sound, 1, 1, 1, 0, 1);
        }
        return mStreamID;
    }

    public int loadSound(String fileName) {
        AssetFileDescriptor afd;
        try {
            afd = mAssetManager.openFd(fileName);
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Не могу загрузить файл " + fileName,
                    Toast.LENGTH_SHORT).show();
            return -1;
        }
        return mSoundPool.load(afd, 1);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            // Для устройств до Android 5
            createOldSoundPool();
        } else {
            // Для новых устройств
            createNewSoundPool();
        }

        mAssetManager = getAssets();

        // получим идентификаторы
        anvilSound = loadSound("anvil.mp3");
        winSound = loadSound("uhuu.mp3");
        winALL = loadSound("eea.mp3");
        //   mDogSound = loadSound("dog.ogg");
        //   mDuckSound = loadSound("duck.ogg");
        //   mSheepSound = loadSound("sheep.ogg");
        aS = loadSound("a.mp3");
        S.x=aS;
        iS = loadSound("i.mp3");
        eS = loadSound("e.mp3");
        oS = loadSound("o.mp3");
        yS = loadSound("y.mp3");
        ieS = loadSound("ie.mp3");
        iaS = loadSound("ia.mp3");
        iyS = loadSound("iy.mp3");
        ieaS = loadSound("iea.mp3");

        prav = loadSound("right.mp3");
        praveso = loadSound("righteso.mp3");
        neprav = loadSound("nea.mp3");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
     //   mSoundPool.release();
      //  mSoundPool = null;
    }
}
