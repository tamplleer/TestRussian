package com.tamplleer.testrussian;


  import android.annotation.TargetApi;
  import android.content.Context;
  import android.content.Intent;
  import android.content.res.AssetFileDescriptor;
  import android.content.res.AssetManager;
  import android.media.AudioAttributes;
  import android.media.AudioManager;
  import android.media.SoundPool;
  import android.os.Build;
  import android.os.Handler;
          import android.support.v7.app.AppCompatActivity;
          import android.os.Bundle;
          import android.view.Window;
          import android.view.WindowManager;
  import android.widget.Toast;

  import java.io.IOException;

public class SpleshScreenactivity extends AppCompatActivity {
    Audio audio;
    private static int SPLASH_TIME_OUT = 3000;
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
          setContentView(R.layout.activity_splesh_screenactivity);
       // S.mAssetManager =getAssets();
        audio=new Audio(1);
        S.mSettings = getSharedPreferences(S.APP_PREFERENCES1, Context.MODE_PRIVATE);
     //   audio.playSound(1);
        handler1();
        handler();


    }
    private void handler(){
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent intent = new Intent(SpleshScreenactivity.this,
                        MainActivity.class);
                startActivity(intent);

                finish();
            }
        }, SPLASH_TIME_OUT);
    }
    private void handler1(){
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
               // audio.playSound(15);
            }
        }, 10);
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (S.mSettings.contains(S.APP_PREFERENCES_silence)) {
            S.volL = S.mSettings.getInt(S.APP_PREFERENCES_silence, 0);
        }
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
        //   mDogSound = loadSound("dog.ogg");
        //   mDuckSound = loadSound("duck.ogg");
        //   mSheepSound = loadSound("sheep.ogg");
        S.aS =  audio.loadSound("a.mp3");

        S.iS =  audio.loadSound("i.mp3");
        S.eS =  audio.loadSound("e.mp3");
        S.oS =  audio.loadSound("o.mp3");
        S.yS =  audio.loadSound("y.mp3");
        S.ieS =  audio.loadSound("ie.mp3");
        S.ieaS =  audio.loadSound("iea.mp3");
        S.iyS =  audio.loadSound("iy.mp3");
        S.iaS =  audio.loadSound("ia.mp3");


        S.prav =  audio.loadSound("right.mp3");
        S.praveso =  audio.loadSound("righteso.mp3");
        S.neprav =  audio.loadSound("nea.mp3");
       S.hor=audio.loadSound("hor.ogg");
      //  audio.playSound(S.winALL);
      //  audio.playSound(1);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //   mSoundPool.release();
        //  mSoundPool = null;
    }

}