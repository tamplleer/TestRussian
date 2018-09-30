package com.tamplleer.testrussian;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
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

public class MainActivity extends AppCompatActivity {
    TextView t, point;
    char strToArray[];
    int screenWidth, screenHeight;
    int lengthINscore = 0;
    Button button, bmstart30, bmnext;
    ImageButton bminfo;
    ConstraintLayout constraintLayout;
    static char a[] = {'А', 'И', 'Е', 'Ё', 'О', 'У', 'Ы', 'Э', 'Ю', 'Я'};
    String word1[];
    Audio audio;
    DialogInMenu dialog;
    ImageView picture;
    AdRequest adRequest;

    int size;
    private static final String TAG = "MainActivity";

    private AdView mAdView;

    public Button start;
    ImageButton exit;
    private Handler handler = new Handler();

    int esootstup = 0;
    Typeface  font1=null;
    // TextView texNew;
    int polozenie = 0;
    int otstup;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        S.mSettings = getSharedPreferences(S.APP_PREFERENCES1, Context.MODE_PRIVATE);
        word1 = S.allWord.split(",");
        t = (TextView) findViewById(R.id.text);
        point = (TextView) findViewById(R.id.points);
        constraintLayout = (ConstraintLayout) findViewById(R.id.constrainlayout);
        getScreenSize();
        t.setTextSize(screenWidth / 25);
        esootstup = screenWidth / 8;
        audio = new Audio(0);
        picture = (ImageView) findViewById(R.id.imageView);
        picture.setX(0);
        picture.setY(0);
        button = (Button) findViewById(R.id.startGA);
        bminfo = (ImageButton) findViewById(R.id.vopros);
        bmstart30 = (Button) findViewById(R.id.start30);
        start = (Button) findViewById(R.id.start);
        exit = (ImageButton) findViewById(R.id.exit);
        bmnext = (Button) findViewById(R.id.next);
        bmnext.setVisibility(View.INVISIBLE);


        try {
            font1 = Typeface.createFromAsset(getAssets(), "font12.ttf");
            S.fon2=font1;
        } catch (Exception e) {
            Log.e(TAG, "Could not get typeface: "+e.getMessage());

        }
        start.setTypeface(font1);
        t.setTypeface(font1);
        point.setTypeface(font1);
        button.setTypeface(font1);
        bmnext.setTypeface(font1);
        bmstart30.setTypeface(font1);

        // button.setX(screenWidth/2);
        // button.setY(screenHeight/2);

        if (5 != S.secund) {
            //audio.playSound(16);
        }

        // texNew=(TextView)findViewById(R.id.newWorld);


        AppRater.app_launched(this);

        MobileAds.initialize(this, "ca-app-pub-8909727970839097~4345378585");
        mAdView = (AdView) findViewById(R.id.adView);
         adRequest = new AdRequest.Builder()
                .addTestDevice("E60B48CB85290BEBD01F19B878272929")
                .build();
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

     /*   texNew.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction()==MotionEvent.ACTION_DOWN) {
                        Log.d("шибка","x"+motionEvent.getRawX());
                    polozenie=(int)motionEvent.getRawX();

                    touched1();
                }
                return true;
            }
        });*/
       // MyTimer timer = new MyTimer();
      //  timer.start();
    }


    protected void getScreenSize() {
        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        screenWidth = metrics.widthPixels;
        screenHeight = metrics.heightPixels;
    }


    public void exit(View view) {
        if (S.volL == 1) {
            S.volL = 0;
            exit.setBackgroundResource(R.drawable.thetyo);
        } else {
            S.volL = 1;
            exit.setBackgroundResource(R.drawable.soubdee);

        }
    }

    public void start(View view) {
        bmnext.setVisibility(View.VISIBLE);
        YoYo.with(Techniques.Bounce)
                .duration(700)
                .repeat(0)
                .playOn(findViewById(R.id.start));
        t.setTextColor(getResources().getColor(R.color.DarkSlateGray));
        S.click = true;
        S.someClick = false;
        S.win = 0;
        S.steps = 0;
        S.clickWord = true;
        lengthINscore = word1.length;
        S.lengsInScore = word1.length;
        S.wordMassive = new String[word1.length];
        S.bukvMassive = new int[word1.length];
        S.vernoORno = new boolean[word1.length];

        S.wordMassive = S.allWord.split(",");
        point.setText(S.steps + "/" + lengthINscore);
        S.changeWord = 1;
        setWordinMas();
        constraintLayout.setBackgroundResource(R.drawable.fon);
        t.setText("");
        audio.playSound(1);
        button.setX(4000);
        button.setY(4000);

    }

    public void someword(View view) {
        bmnext.setVisibility(View.VISIBLE);
        YoYo.with(Techniques.Bounce)
                .duration(1000)
                .repeat(0)
                .playOn(findViewById(R.id.start30));

        trisiWORD();
    }

    public void trisiWORD() {
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
        int banWord[] = new int[maxLength];
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
        handler.post(new Runnable() {
            @Override
            public void run() {
                point.setText(S.steps + 1 + "/" + lengthINscore);
            }
        });

        S.changeWord = 1;
        setWordinMas();
        constraintLayout.setBackgroundResource(R.drawable.fon);

        t.setText("");
        button.setX(4000);
        button.setY(4000);
    }

    public void next(View view) {
        bmnext.setVisibility(View.VISIBLE);
        handler.post(new Runnable() {
            @Override
            public void run() {
                YoYo.with(Techniques.BounceInUp)
                        .duration(500)
                        .repeat(0)
                        .playOn(findViewById(R.id.next));
            }
        });


        if (S.click == true || S.someClick == true) {
            S.steps++;
            S.changeWord++;
            handler.post(new Runnable() {
                @Override
                public void run() {
                    point.setText(S.steps + 1 + "/" + lengthINscore);
                }
            });
            constraintLayout.setBackgroundResource(R.drawable.fon);
            if (S.right == true) {
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
            S.right = false;

            t.setText("" + S.wordtoscreen);


            setWordinMas();
            if (S.steps == lengthINscore) {
                // t.setText(S.win + "  OF  " + lengthINscore );
                S.click = false;
                S.someClick = false;
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


    public void setWordinMas() {
        String word[] = new String[S.wordMassive.length];
        for (int i = 0; i < S.wordMassive.length; i++) {
            word[i] = S.wordMassive[i];
            if (S.changeWord == i + 1) {
                S.stringTOchar = S.wordMassive[i];
                S.wordtoscreen = S.wordMassive[i];
                createWord();
                S.bukvMassive[i] = S.bukvLEngth;
            }
        }

    }

    public void rextV(View view) {
        t.setText("!!!!!!!!!!!!");
    }

    public void createWord() {
        strToArray = S.stringTOchar.toCharArray();
        S.bukvLEngth = strToArray.length;
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
            if (Character.isLowerCase(strToArray[i]))
                strToArray[i] = Character.toUpperCase(strToArray[i]);
            if (strToArray[i] == 'Ё') strToArray[i] = 'Е';
        }
        Text text[] = new Text[strToArray.length + 1];
        Text textpusto[] = new Text[1];

        int x1 = screenWidth / strToArray.length;
        int y = screenHeight / 2;
        int x = 0;
        size = screenHeight / 30;
        textpusto[0] = new Text(this, screenWidth / (screenHeight / 128), y, ' ', size, 100, 30);
        for (int i = 0; i < strToArray.length; i++) {

            if (strToArray.length > 9) {
                x = x1 * i + screenWidth / (screenHeight / 128);
            } else {
                x = x1 * i + screenWidth / (screenHeight / 160);
            }  //screenHeight/160 -otstup
            if (screenWidth < 500) {
                x = x1 * i + 55;
            }
            if (screenWidth < 500 && strToArray.length > 9) {
                x = x1 * i + 45;
            }
            // if (i == 0) x =  esootstup;

            text[i] = new Text(this, x, y, strToArray[i], size, i, i);


        }
        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) picture.getLayoutParams();
        picture.setX(-400);
        picture.setY(screenHeight / 2 - (screenHeight / 64) - 1);
        params.width = screenWidth;
        params.height = size * 2;
        // setNewWord();


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
            S.reclam = S.mSettings.getBoolean(S.APP_PREFERENCES_ADD,false);
        }
        if (S.reclam==true) mAdView.loadAd(adRequest);
        Log.e(TAG, "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAaa "+S.reclam);

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
        editor.putBoolean(S.APP_PREFERENCES_ADD,S.reclam);
        editor.apply();

    }

    public void vopros(View view) {
        YoYo.with(Techniques.Bounce)
                .duration(1000)
                .repeat(0)
                .playOn(findViewById(R.id.vopros));

        dialog = new DialogInMenu(this,0);
        dialog.ad.show();
    }

  //  public void updateTime1() {
        //  men.move();
   // }

    public void starGA(View view) {
        bmnext.setVisibility(View.VISIBLE);
        trisiWORD();
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
}

