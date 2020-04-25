package com.tamplleer.testrussian.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.widget.Toast;

import com.tamplleer.testrussian.S;
import com.tamplleer.testrussian.activities.main.MainActivity;
import com.tamplleer.testrussian.activities.result.endGame;
import com.tamplleer.testrussian.activities.splesh.SpleshScreenactivity;

import java.io.IOException;

/**
 * Created by tampl on 11.02.2018.
 */

public class Audio {
    Context context;
public Audio(int activity){
    MainActivity main=new MainActivity();
    endGame endGamE=new endGame();
    SpleshScreenactivity spleshScreenactivity=new SpleshScreenactivity();
    if (activity==0)context=main;
    if (activity==2)context=endGamE;
   if (activity==1)context=spleshScreenactivity;

/*

    if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
        // Для устройств до Android 5
        createOldSoundPool();
    } else {
        // Для новых устройств
       createNewSoundPool();
    }



    // получим идентификаторы
    S.anvilSound = loadSound("anvil.mp3");
    S.winSound = loadSound("uhuu.mp3");
    S.winALL = loadSound("eea.mp3");
    //   mDogSound = loadSound("dog.ogg");
    //   mDuckSound = loadSound("duck.ogg");
    //   mSheepSound = loadSound("sheep.ogg");
    S.aS =  loadSound("a.mp3");

    S.iS =  loadSound("i.mp3");
    S.eS =  loadSound("e.mp3");
    S.oS =  loadSound("o.mp3");
    S.yS =  loadSound("y.mp3");
    S.ieS =  loadSound("ie.mp3");
    S.ieaS = loadSound("iea.mp3");
    S.iyS =  loadSound("iy.mp3");
    S.iaS =  loadSound("ia.mp3");


    S.prav =  loadSound("right.mp3");
    S.praveso = loadSound("righteso.mp3");
    S.neprav =  loadSound("nea.mp3");
*/


}
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void createNewSoundPool() {
        AudioAttributes attributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();
        S.mSoundPool = new SoundPool.Builder()
                .setAudioAttributes(attributes)
                .build();
    }

    @SuppressWarnings("deprecation")
    public void createOldSoundPool() {
        S.mSoundPool = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
    }

    public int playSound(int sound) {
        if (sound > 0) {
            S.mStreamID = S.mSoundPool.play(sound, S.volL,S.volL, 1, 0, 1);


        }
        return S.mStreamID;
    }

    public int loadSound(String fileName) {
        AssetFileDescriptor afd;
        try {
            afd = S.mAssetManager.openFd(fileName);
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(context, "Не могу загрузить файл " + fileName,
                    Toast.LENGTH_SHORT).show();
            return -1;
        }
        return S.mSoundPool.load(afd, 1);
    }


}
