package com.tamplleer.testrussian;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class endGame extends AppCompatActivity {
TextView t,tt;
    Audio audio;
    int screenWidth,screenHeight;
    ConstraintLayout constraintLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
    //    requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
       // getSupportActionBar().hide();
    //    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
   //             WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_end_game);
        getScreenSize();
        setTitle(" ");
        constraintLayout= (ConstraintLayout) findViewById(R.id.endloy);
       // constraintLayout.setMinHeight(350);
      //  constraintLayout.setMaxWidth(100);
        audio=new Audio(2);
        t = (TextView) findViewById(R.id.endText);
        tt = (TextView) findViewById(R.id.tt);
        t.setText(R.string.GOVER);
        tt.setText(""+S.win+" / "+S.lengsInScore);
        if (S.win<20) audio.playSound(S.nedovol);
        if (S.win>=20)audio.playSound(S.winSound);
        if (S.win==S.lengsInScore)audio.playSound(S.winALL);
    }

    public void endexit(View view) {
     //   Intent intent = new Intent(endGame.this,
    //            MainActivity.class);
     //   startActivity(intent);
        finish();
    }

    protected void getScreenSize() {
        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        screenWidth = metrics.widthPixels;
        screenHeight = metrics.heightPixels;
    }





}
