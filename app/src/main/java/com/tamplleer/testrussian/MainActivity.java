package com.tamplleer.testrussian;

import android.annotation.TargetApi;
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



        sounD=new sound();



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
        word1 = S.allWord.split(",");
        point.setText(S.steps + "/" + lengthINscore);
        S.changeWord = 1;
        setWordinMas();
        constraintLayout.setBackgroundColor(ContextCompat
                .getColor(this, R.color.Khaki));
        t.setText("");
        playSound(anvilSound);
    }

    public void someword(View view) {
        S.someClick = true;
        S.click = false;
        S.win = 0;
        S.steps=0;
        S.clickWord=true;
        int maxLength = 30;
        int rundom = 0;
        int banWord[] = new int[maxLength];
        lengthINscore = maxLength;
        for (int i = 1; i < maxLength; i++) {
            rundom = (int) (Math.random() * word1.length);
            for (int j = 1; j < maxLength; j++) {
                if (banWord[j] == rundom) rundom = (int) (Math.random() * word1.length);
            }
            playSound(winALL);
            word1[i] = word1[rundom];
            banWord[i] = rundom;
        }

        point.setText(S.win + "/" + lengthINscore);
        S.changeWord = 1;
        setWordinMas();
        constraintLayout.setBackgroundColor(ContextCompat
                .getColor(this, R.color.Khaki));

        t.setText("");

    }

    public void next(View view) {

        if (S.click == true || S.someClick == true) {
            S.steps++;
            S.changeWord++;
            point.setText(S.steps + "/" + lengthINscore);
            constraintLayout.setBackgroundColor(ContextCompat
                    .getColor(this, R.color.Khaki));
            if (S.right == true) {
                if (S.pravi>0)playSound(praveso);
                else  playSound(prav);
                S.pravi++;
                //point.setText(S.win + "/" + lengthINscore);

            } else {
                playSound(neprav);
                S.pravi=0;
                constraintLayout.setBackgroundColor(ContextCompat
                        .getColor(this, R.color.FireBrick));
            }
            S.right=false;
            t.setText("" + S.wordtoscreen);
            setWordinMas();
            if (S.steps == lengthINscore) {
                t.setText(""+R.string.GOVER+ S.win + "  OF  " + lengthINscore + R.string.TRAG);
                S.click = false;
                S.someClick = false;
                S.clickWord=false;
                if (S.win>=25)playSound(winSound);
                if (S.win==lengthINscore)playSound(winALL);
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
    protected void onPause() {
        super.onPause();
        mSoundPool.release();
        mSoundPool = null;
    }
public void asdf(){
    playSound(aS);
}
}

