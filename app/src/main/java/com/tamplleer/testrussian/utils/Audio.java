package com.tamplleer.testrussian.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import com.tamplleer.testrussian.activities.result.EndGame;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tampl on 11.02.2018.
 */

public class Audio {
    private Context context;
    private static SoundPool mSoundPool;
    private AssetManager assetManager;
    private Map<String, Integer> soundMap;



    private  int volume = 1;

    public Audio(Context context, AssetManager assetManager) {
        this.assetManager = assetManager;
        soundMap = new HashMap<>();
        this.context = context;
        addSound(context);
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

    public void playSound(int sound) {
        Log.w("cat","play = "+sound);
        if (sound > 0) {
            mSoundPool.play(sound, volume, volume, 1, 0, 1);
        }
    }

    private int loadSound(String fileName) {
        AssetFileDescriptor afd;
        Log.w("cat","load = "+fileName);
        try {
            afd = assetManager.openFd(fileName);
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(context, "Не могу загрузить файл " + fileName,
                    Toast.LENGTH_SHORT).show();
            return -1;
        }
        return mSoundPool.load(afd, 1);
    }
    public void releaseSound(){
        mSoundPool.release();
    }

    private void addSound(Context context) {
        createNewSoundPool();
        Log.w("cat","add = ");
        soundMap.put("anvil", loadSound("anvil.mp3"));
        if (context instanceof EndGame) {
            soundMap.put("winSound", loadSound("uhuu.mp3"));
            soundMap.put("TotalWin", loadSound("eea.mp3"));
            soundMap.put("disappointed", loadSound("nedovol.mp3"));
        } else {

            soundMap.put("a", loadSound("a.mp3"));
            soundMap.put("i", loadSound("i.mp3"));
            soundMap.put("e", loadSound("e.mp3"));
            soundMap.put("o", loadSound("o.mp3"));
            soundMap.put("y", loadSound("y.mp3"));
            soundMap.put("ie", loadSound("ie.mp3"));
            soundMap.put("iea", loadSound("iea.mp3"));
            soundMap.put("iy", loadSound("iy.mp3"));
            soundMap.put("ia", loadSound("ia.mp3"));

            soundMap.put("right", loadSound("right.mp3"));
            soundMap.put("rightMore", loadSound("righteso.mp3"));
            soundMap.put("wrong", loadSound("nea.mp3"));
        }


    }

    public int getSoundNumber(String soundName) {
        return soundMap.getOrDefault(soundName, 1);
    }
    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }


}
