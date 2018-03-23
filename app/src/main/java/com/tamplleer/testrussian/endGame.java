package com.tamplleer.testrussian;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class endGame extends AppCompatActivity {
TextView t,tt;
    String textend="";
    Audio audio;
    int screenWidth,screenHeight;
    ConstraintLayout constraintLayout;
    int lose=30-S.win;
    int textplus=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
       requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
       getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_end_game);
        getScreenSize();
        setTitle(" ");
        constraintLayout= (ConstraintLayout) findViewById(R.id.endloy);
       // constraintLayout.setMinHeight(350);
      //  constraintLayout.setMaxWidth(100);
        audio=new Audio(2);
        t = (TextView) findViewById(R.id.endText);
        tt = (TextView) findViewById(R.id.tt);
        //t.setText(R.string.GOVER);
        int x=screenWidth/3+screenWidth/10;
        int y=screenHeight/30;
        TextSlabak text1[]=new TextSlabak[S.wordMassive.length];
        for (int i = 0; i < S.wordMassive.length; i++) {

            text1[i]=new TextSlabak(this,x,y, S.wordMassive[i],screenHeight/30, S.vernoORno[i]);
            y+=screenHeight/43;

        }
        S.coins=S.coins+S.win;
        tt.setText(""+S.win+" / "+S.lengsInScore);
        if (S.win<20 && S.steps==S.lengsInScore) audio.playSound(S.nedovol);
        if (S.win>=20)audio.playSound(S.winSound);
        if (S.win==S.lengsInScore)audio.playSound(S.winALL);
        Log.d("шибка","coins = "+S.coins);



    }

    public void endexit(View view) {
        S.secund=5;
        Intent intent = new Intent(endGame.this,
               MainActivity.class);
        startActivity(intent);
        finish();
    }

    protected void getScreenSize() {
        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        screenWidth = metrics.widthPixels;
        screenHeight = metrics.heightPixels;
    }

    @Override
    protected void onPause() {
        super.onPause();
        save();
    }
    public void save(){
        SharedPreferences.Editor editor = S.mSettings.edit();
        editor.putInt(S.APP_PREFERENCES_coins,S.coins );
        editor.apply();

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (S.mSettings.contains(S.APP_PREFERENCES_coins)) {
            S.coins = S.mSettings.getInt(S.APP_PREFERENCES_coins, 0);
    }}
}
