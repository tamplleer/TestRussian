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
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    TextView t, point;
    char strToArray[];
    sound sounD;
    int screenWidth, screenHeight;
    int lengthINscore = 0;
    ConstraintLayout constraintLayout;
   static char a[] = {'А', 'И', 'Е', 'Ё', 'О', 'У', 'Ы', 'Э', 'Ю', 'Я'};
    String word1[];
Audio audio;
    DialogInMenu dialog;




    public SoundPool mSoundPool;
    public AssetManager mAssetManager;
    public int mCatSound, winSound,winALL, prav, praveso, neprav,anvilSound,aS,iS,eS,oS,yS,ieS,iaS,iyS,ieaS;
    public int mStreamID;
    public Button start;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        word1 = S.allWord.split(",");
        t = (TextView) findViewById(R.id.text);
        point = (TextView) findViewById(R.id.points);
        constraintLayout = (ConstraintLayout) findViewById(R.id.constrainlayout);
        getScreenSize();
        audio=new Audio(0);



        sounD=new sound();
        audio.playSound(16);


        start = (Button) findViewById(R.id.start);
       /* start.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                int eventAction = event.getAction();
                if (eventAction == MotionEvent.ACTION_UP) {
                    // Отпускаем палец
                    if (mStreamID > 0)
                        mSoundPool.stop(mStreamID);
                }
                if (eventAction == MotionEvent.ACTION_DOWN) {
                    // Нажимаем на кнопку
                    mStreamID = playSound(anvilSound);
                }
                if (event.getAction() == MotionEvent.ACTION_CANCEL) {
                    mSoundPool.stop(mStreamID);
                }
                return true;
            }
        });*/

    }



    protected void getScreenSize() {
        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        screenWidth = metrics.widthPixels;
        screenHeight = metrics.heightPixels;
    }


    public void exit(View view) {
        System.exit(0);
    }

    public void start(View view) {
        S.click = true;
        S.someClick = false;
        S.win = 0;
        S.steps=0;
        S.clickWord=true;
        lengthINscore = word1.length;
        S.lengsInScore= word1.length;


        word1 = S.allWord.split(",");
        point.setText(S.steps + "/" + lengthINscore);
        S.changeWord = 1;
        setWordinMas();
        constraintLayout.setBackgroundResource(R.drawable.fon);
        t.setText("");
        audio.playSound(1);
    }

    public void someword(View view) {
        S.someClick = true;
        S.click = false;
        S.win = 0;
        S.steps=0;
        S.clickWord=true;
        int maxLength = 30;
        S.lengsInScore= 30;
        int rundom = 0;
        int banWord[] = new int[maxLength];
        lengthINscore = maxLength;
        for (int i = 1; i < maxLength; i++) {
            rundom = (int) (Math.random() * word1.length);
            for (int j = 1; j < maxLength; j++) {
                if (banWord[j] == rundom) rundom = (int) (Math.random() * word1.length);
            }
            audio.playSound(1);
            word1[i] = word1[rundom];
            banWord[i] = rundom;
        }

        point.setText(S.win + "/" + lengthINscore);
        S.changeWord = 1;
        setWordinMas();
        constraintLayout.setBackgroundResource(R.drawable.fon);

        t.setText("");

    }

    public void next(View view) {
        if (S.click == true || S.someClick == true) {
            S.steps++;
            S.changeWord++;
            point.setText(S.steps + "/" + lengthINscore);
            constraintLayout.setBackgroundResource(R.drawable.fon);
            if (S.right == true) {
                if (S.pravi>0)audio.playSound(14);
                else  audio.playSound(13);
                S.pravi++;
                //point.setText(S.win + "/" + lengthINscore);

            } else {
                audio.playSound(15);
                S.pravi=0;
                constraintLayout.setBackgroundResource(R.drawable.fonred);
            }
            S.right=false;
            t.setText("" + S.wordtoscreen);
            setWordinMas();
            if (S.steps == lengthINscore) {
                t.setText(S.win + "  OF  " + lengthINscore );
                S.click = false;
                S.someClick = false;
                S.clickWord=false;
                if (S.win<10) audio.playSound(winSound);
                if (S.win>=25)audio.playSound(winSound);
                if (S.win==lengthINscore)audio.playSound(winALL);
               Intent intent = new Intent(MainActivity.this,
                      endGame.class);
              startActivity(intent);

            }
        }
    }

    public void setWordinMas() {
        String word[] = new String[word1.length];
        for (int i = 1; i < word.length; i++) {
            word[i] = word1[i];
            if (S.changeWord == i) {
                S.stringTOchar = word[i];
                S.wordtoscreen = word[i];
                createWord();
            }
        }

    }

    public void rextV(View view) {
        t.setText("!!!!!!!!!!!!");
    }

    public void createWord() {
        strToArray = S.stringTOchar.toCharArray();
        char strArray00[] = new char[strToArray.length];

        for (int i = 0; i < strToArray.length; i++) {
            if (strToArray[i] == a[0] ||
                    strToArray[i] == a[1] ||
                    strToArray[i] == a[2] ||
                    strToArray[i] == a[3] ||
                    strToArray[i] == a[4] ||
                    strToArray[i] == a[5] ||
                    strToArray[i] == a[6] ||
                    strToArray[i] == a[7] ||
                    strToArray[i] == a[8] ||
                    strToArray[i] == a[9]) S.delet = i;
            strArray00[i] = ' ';
            if (Character.isLowerCase(strToArray[i]))
                strToArray[i] = Character.toUpperCase(strToArray[i]);
            if (strToArray[i]=='Ё')strToArray[i]='Е';
        }
        Text text[] = new Text[strToArray.length + 1];
        // strToArray[999]=' ';
        //text[strToArray.length+1]= new Text(this,30,30,strToArray[999],30,999);

        int x1 = screenWidth / strToArray.length;
        int y = screenHeight / 2;
        int x = 0;
        for (int i = 0; i < strToArray.length; i++) {

            x = x1 * i + 60;
            if (i == 0) x = 60;

            text[i] = new Text(this, x, y, strToArray[i], 30, i);
        }

        for (int i = 0; i < strToArray.length; i++) {
            strToArray[i] = strArray00[i];

        }
    }



    @Override
    protected void onResume() {
        super.onResume();

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
     S.nedovol =  audio.loadSound("nedovol.mp3");
        audio.playSound(S.winALL);

    }

    @Override
    protected void onPause() {
        super.onPause();
       // Toast.makeText(this, "Не могу загрузить файл ",
        //        Toast.LENGTH_SHORT).show();
        //   mSoundPool.release();
        //  mSoundPool = null;
    }

    public void vopros(View view) {
        dialog = new DialogInMenu(this);
        dialog.ad.show();
    }
}

