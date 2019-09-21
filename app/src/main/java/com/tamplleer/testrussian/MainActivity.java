package com.tamplleer.testrussian;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    TextView t, point;
    GetScreenSize getScreenSize;
    int screenWidth;
    int screenHeight;
    int lengthINscore = 0;
    Button button, bmstart30, bmnext;
    ImageButton bminfo;
    ConstraintLayout constraintLayout;
    String[] word1;
    static char[] a = {'А', 'И', 'Е', 'Ё', 'О', 'У', 'Ы', 'Э', 'Ю', 'Я'};
    Audio audio;
    DialogInMenu dialog;
    ImageView picture;
    AdRequest adRequest;
    private static final String TAG = "MainActivity";

    private AdView mAdView;

    public Button start;
    ImageButton exit;
    private Handler handler = new Handler();
    int esootstup = 0;
    //Typeface font1 = null;
    MakeWord makeWord;
    // new variables
    String wordtoscreen = "";
    boolean click = false;
    Font font;
    TestWords testWords;
    boolean allWords = false;
    boolean rundomWords = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        S.mSettings = getSharedPreferences(S.APP_PREFERENCES1, Context.MODE_PRIVATE);
        getScreenSize = new GetScreenSize(this);
        screenWidth = getScreenSize.getScreenWidth();
        screenHeight = getScreenSize.getScreenHeight();
        font = new Font(this);
        word1 = S.allWord.split(",");
        t = findViewById(R.id.text);
        point = findViewById(R.id.points);
        constraintLayout = findViewById(R.id.constrainlayout);
        t.setTextSize(screenWidth / 25);
        esootstup = screenWidth / 8;
        audio = new Audio(0);
        picture = findViewById(R.id.imageView);
        picture.setX(0);
        picture.setY(0);
        button = findViewById(R.id.startGA);
        bminfo = findViewById(R.id.vopros);
        bmstart30 = findViewById(R.id.start30);
        start = findViewById(R.id.start);
        exit = findViewById(R.id.exit);
        bmnext = findViewById(R.id.next);
        bmnext.setVisibility(View.INVISIBLE);
        makeWord = new MakeWord(this, screenWidth, screenHeight, picture);
        testWords = new TestWords();

        start.setTypeface(font.getFont1());
        t.setTypeface(font.getFont1());
        point.setTypeface(font.getFont1());
        button.setTypeface(font.getFont1());
        bmnext.setTypeface(font.getFont1());
        bmstart30.setTypeface(font.getFont1());
        button.setVisibility(View.VISIBLE);
        AppRater.app_launched(this);

        MobileAds.initialize(this, "ca-app-pub-8909727970839097~4345378585");
        mAdView = (AdView) findViewById(R.id.adView);
        adRequest = new AdRequest.Builder()
                .addTestDevice("E60B48CB85290BEBD01F19B878272929")
                .build();
    }

    public void exit(View view) {// audio button
        if (S.volL == 1) {
            S.volL = 0;
            exit.setBackgroundResource(R.drawable.thetyo);
        } else {
            S.volL = 1;
            exit.setBackgroundResource(R.drawable.soubdee);

        }
    }

    public void start(View view) {
        YoYo.with(Techniques.Bounce)
                .duration(700)
                .repeat(0)
                .playOn(findViewById(R.id.start));
        // testWords.start(allWords);


        bmnext.setVisibility(View.VISIBLE);
        t.setTextColor(getResources().getColor(R.color.DarkSlateGray));
        click = true;
        //S.someClick = false;
        S.win = 0;
        S.steps = 0;
        S.clickWord = true;
        lengthINscore = word1.length;
        S.lengsInScore = word1.length;
        S.wordMassive = new String[word1.length];
        S.vernoORno = new boolean[word1.length];

        S.wordMassive = S.allWord.split(",");
        point.setText(S.steps + 1 + "/" + lengthINscore);
        S.changeWord = 1;
        // setWordinMas();
        wordtoscreen = makeWord.setWordinMas();
        constraintLayout.setBackgroundResource(R.drawable.fon);
        t.setText("");
        audio.playSound(1);
        button.setVisibility(View.INVISIBLE);


    }

    public void someword(View view) {
        bmnext.setVisibility(View.VISIBLE);
        YoYo.with(Techniques.Bounce)
                .duration(1000)
                .repeat(0)
                .playOn(findViewById(R.id.start30));
        // testWords.start(allWords);
        rundomWords();
    }

    /**
     * Get 30 words from list
     */
    public void rundomWords() {
        t.setTextColor(getResources().getColor(R.color.DarkSlateGray));
        String[] wordMassive = new String[30];
        S.wordMassive = new String[30];
        String[] wordTmprArray = word1;
        int maxLength = 30;
        int rundom = 0;

        S.vernoORno = new boolean[30];
        //S.someClick = true;
        click = true;
        S.win = 0;
        S.steps = 0;
        S.clickWord = true;
        S.lengsInScore = 30;
        lengthINscore = maxLength;

        for (int i = 0; i < maxLength; i++) {
            rundom = (int) (Math.random() * word1.length);
            while (wordTmprArray[rundom].equals("0")) rundom = (int) (Math.random() * word1.length);
            S.wordMassive[i] = wordTmprArray[rundom];
            wordTmprArray[rundom] = "0";
        }


        audio.playSound(1);
        handler.post(() -> point.setText(S.steps + 1 + "/" + lengthINscore));

        S.changeWord = 1;
        wordtoscreen = makeWord.setWordinMas();
        constraintLayout.setBackgroundResource(R.drawable.fon);
        button.setVisibility(View.INVISIBLE);
    }

    public void next(View view) {
        handler.post(() -> YoYo.with(Techniques.BounceInUp)
                .duration(500)
                .repeat(0)
                .playOn(findViewById(R.id.next)));

        //bmnext.setVisibility(View.VISIBLE);
        if (click) {
            S.steps++;
            S.changeWord++;
            handler.post(() -> point.setText(S.steps + 1 + "/" + lengthINscore));
            constraintLayout.setBackgroundResource(R.drawable.fon);
            if (S.right) {
                S.win++;
                t.setTextColor(getResources().getColor(R.color.DarkSlateGray));
                if (S.pravi > 0) audio.playSound(14);
                else audio.playSound(13);
                S.pravi++;
                S.vernoORno[S.steps - 1] = true;
                //point.setText(S.win + "/" + lengthINscore);
                YoYo.with(Techniques.StandUp)
                        .duration(700)
                        .repeat(0)
                        .playOn(findViewById(R.id.text));

            } else {
                t.setTextColor(getResources().getColor(R.color.Khaki));
                YoYo.with(Techniques.Shake)
                        .duration(700)
                        .repeat(0)
                        .playOn(findViewById(R.id.text));
                audio.playSound(15);
                S.pravi = 0;
                constraintLayout.setBackgroundResource(R.drawable.fonred);
                S.vernoORno[S.steps - 1] = false;
            }


            t.setText("" + wordtoscreen);


            //setWordinMas();
            wordtoscreen = makeWord.setWordinMas();
            S.right = false;
            if (S.steps == lengthINscore) {
                // t.setText(S.win + "  OF  " + lengthINscore );
                click = false;
                // S.someClick = false;
                S.clickWord = false;
                // if (S.win<10) audio.playSound(winSound);
                // if (S.win>=25)audio.playSound(winSound);
                //  if (S.win==lengthINscore)audio.playSound(winALL);
                Intent intent = new Intent(MainActivity.this,
                        endGame.class);
                startActivity(intent);
                finish();

            }
        }
    }

    public void rextV(View view) {
        t.setText("!!!!!!!!!!!!");
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (S.mSettings.contains(S.APP_PREFERENCES_silence)) {
            S.volL = S.mSettings.getInt(S.APP_PREFERENCES_silence, 0);

            if (S.volL == 1) {
                exit.setBackgroundResource(R.drawable.soubdee);
            } else exit.setBackgroundResource(R.drawable.thetyo);
        }
        if (S.mSettings.contains(S.APP_PREFERENCES_ADD)) {
            S.reclam = S.mSettings.getBoolean(S.APP_PREFERENCES_ADD, false);
        }
        if (S.reclam) mAdView.loadAd(adRequest);
        Log.e(TAG, "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAaa " + S.reclam);

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
        S.aS = audio.loadSound("a.mp3");

        S.iS = audio.loadSound("i.mp3");
        S.eS = audio.loadSound("e.mp3");
        S.oS = audio.loadSound("o.mp3");
        S.yS = audio.loadSound("y.mp3");
        S.ieS = audio.loadSound("ie.mp3");
        S.ieaS = audio.loadSound("iea.mp3");
        S.iyS = audio.loadSound("iy.mp3");
        S.iaS = audio.loadSound("ia.mp3");


        S.prav = audio.loadSound("right.mp3");
        S.praveso = audio.loadSound("righteso.mp3");
        S.neprav = audio.loadSound("nea.mp3");
        S.nedovol = audio.loadSound("nedovol.mp3");


    }

    @Override
    protected void onPause() {
        super.onPause();
        save();
        // Toast.makeText(this, "Не могу загрузить файл ",
        //        Toast.LENGTH_SHORT).show();
        //   mSoundPool.release();
        //  mSoundPool = null;
    }

    public void save() {
        SharedPreferences.Editor editor = S.mSettings.edit();
        editor.putInt(S.APP_PREFERENCES_silence, S.volL);
        editor.putBoolean(S.APP_PREFERENCES_ADD, S.reclam);
        editor.apply();

    }

    public void vopros(View view) {
        YoYo.with(Techniques.Bounce)
                .duration(1000)
                .repeat(0)
                .playOn(findViewById(R.id.vopros));

        dialog = new DialogInMenu(this, 0);
        dialog.ad.show();
    }

    //  public void updateTime1() {
    //  men.move();
    // }

    public void starGA(View view) {
        bmnext.setVisibility(View.VISIBLE);
        //testWords.start(allWords);
        rundomWords();
    }

    // class MyTimer extends CountDownTimer {
    //    MyTimer() {
    //       super(Integer.MAX_VALUE, 100);// продолжительность работы таймера в милисекундах, интервал срабатывания

    //     }

    //  @Override
    //  public void onTick(long millisUntilFinished) {
    //      updateTime1(); //если не пауза, то обновляем ERROR!!!!!!!!!!!!!!!!!!!
    //   }

    //  @Override
    //   public void onFinish() {

    //      }
    //   }
/*    public void rundomWords1() {
        t.setTextColor(getResources().getColor(R.color.DarkSlateGray));
        S.wordMassive = new String[30];
        S.bukvMassive = new int[30];
        S.vernoORno = new boolean[30];
        S.someClick = true;
        S.click = false;
        S.win = 0;
        S.steps = 0;
        S.clickWord = true;
        int maxLength = 30;
        S.lengsInScore = 30;
        int rundom = 0;
        int[] banWord = new int[maxLength];
        lengthINscore = maxLength;
        for (int i = 0; i < maxLength; i++) {
            rundom = (int) (Math.random() * word1.length);

            for (int j = 1; j < maxLength; j++) {
                if (banWord[j] == rundom) rundom = (int) (Math.random() * word1.length);
            }
            for (int j = 1; j < maxLength; j++) {
                if (banWord[j] == rundom) rundom = (int) (Math.random() * word1.length);
            }


            word1[i] = word1[rundom];
            S.wordMassive[i] = word1[i];
            banWord[i] = rundom;
        }
        audio.playSound(1);
        handler.post(() -> point.setText(S.steps + 1 + "/" + lengthINscore));

        S.changeWord = 1;
        //setWordinMas();
        makeWord.setWordinMas();
        constraintLayout.setBackgroundResource(R.drawable.fon);

        t.setText("");
        button.setVisibility(View.INVISIBLE);
    }*/
}

