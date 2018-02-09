package com.tamplleer.testrussian;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

/**
 * Created by tampl on 22.01.2018.
 */

public class Text extends MainActivity {

    public SoundPool mSoundPoolQ;
    public AssetManager mAssetManagerQ;
    public int mCatSound, winSound,winALL, prav, praveso, neprav,anvilSound,aS,iS,eS,oS,yS,ieS,iaS,iyS,ieaS;
    public int mStreamIDQ;

    TextView t;
    char ru;
    Context context;
    int x, y, size, type;
    char count;
    ConstraintLayout constraintLayout;
    float alpha = 1; // размер картинки, врещение, прозрачность
    Text(MainActivity main, int x, int y, final char count, int size, final int type) {
        this.x = x;
        context = main;
        this.y = y;
        this.size = size;
        this.type = type;
        this.count = count;
        S.charTextV = new TextView(main);
        S.charTextV.setAlpha(alpha);
        S.charTextV.setX(x); // координаты
        S.charTextV.setY(y);
        S.charTextV.setBackgroundResource(R.drawable.wordshape);
        S.charTextV.setTextColor(Color.rgb(255, 228, 181));
        S.charTextV.setScaleX(2f); // изменение размеров в 1.5 раза
        S.charTextV.setScaleY(2f);
        S.charTextV.setText("" + count);
        ru = count;
        S.type = type;


        t = (TextView) main.findViewById(R.id.text);
        constraintLayout = (ConstraintLayout) main.findViewById(R.id.constrainlayout);
        //      constraintLayout = (ConstraintLayout) findViewById(R.id.constrainlayout);
        main.addContentView(S.charTextV, new RelativeLayout.LayoutParams(size * 4, size));
       S.charTextV.setOnClickListener(new View.OnClickListener() {  // вешаем слушателя на клик
            public void onClick(View v) {
                for (int i = 0; i < a.length; i++) {
                    if (count==a[i] && S.clickWord==true)
                        touched();
                }
                // метод-обработчик нажатия
            }
        });}


    void touched() {
        //     MainActivity main1 = new MainActivity();
        //    main1.getWindow().setBackgroundDrawableResource(R.drawable.fotodena);
        t.setText("" + count + " ?");
        MainActivity m;
        m= new MainActivity();
        playSound(aS);
        switch (count) {
            case 'А':  playSoundQ(aS);
                break;
            case 'И': playSound(iS);
                break;
            case 'Е': playSound(eS);
                break;
            case  'О':  playSound(oS);
                break;
            case  'У':  playSound(yS);
                break;
            case 'Ы':  playSound(iaS);
                break;
            case 'Э': playSound(ieS);
                break;
            case 'Ю': playSound(iyS);
                break;
            case 'Я': playSound(ieaS);
                break;
            default: playSound(winALL);
                break;
        }
        if (count=='А'){
            playSound(aS);}
        if (type == S.delet) {
            S.win++;
            S.right = true;
            //constraintLayout.setBackgroundColor(ContextCompat
            //    .getColor(context, R.color.CHETO));
        } else S.right = false;
        //  FrameLayout parent = (FrameLayout) S.charTextV.getParent();
        // parent.removeView(S.charTextV);
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void createNewSoundPoolQ() {
        AudioAttributes attributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();
        mSoundPoolQ = new SoundPool.Builder()
                .setAudioAttributes(attributes)
                .build();
    }

    @SuppressWarnings("deprecation")
    public void createOldSoundPoolQ() {
        mSoundPoolQ = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
    }

    public int playSoundQ(int sound) {
        if (sound > 0) {
            mStreamIDQ = mSoundPoolQ.play(sound, 1, 1, 1, 0, 1);
        }
        return mStreamIDQ;
    }

    public int loadSoundQ(String fileName) {
        AssetFileDescriptor afd;
        try {
            afd = mAssetManagerQ.openFd(fileName);
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Не могу загрузить файл " + fileName,
                    Toast.LENGTH_SHORT).show();
            return -1;
        }
        return mSoundPoolQ.load(afd, 1);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            // Для устройств до Android 5
            createOldSoundPoolQ();
        } else {
            // Для новых устройств
            createNewSoundPoolQ();
        }

        mAssetManagerQ = getAssets();

        // получим идентификаторы
        anvilSound = loadSound("anvil.mp3");
        winSound = loadSound("uhuu.mp3");
        winALL = loadSound("eea.mp3");
        //   mDogSound = loadSound("dog.ogg");
        //   mDuckSound = loadSound("duck.ogg");
        //   mSheepSound = loadSound("sheep.ogg");
        aS = loadSoundQ("a.mp3");
        S.x=aS;
        iS = loadSoundQ("i.mp3");
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
        mSoundPoolQ.release();
        mSoundPoolQ = null;
    }
}
