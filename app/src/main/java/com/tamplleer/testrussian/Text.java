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
        audio=new Audio(0);
      //  audio.playSound(5);
        switch (count) {
            case 'А':    audio.playSound(4);
                break;
            case 'И':   audio.playSound(5);
                break;
            case 'Е':   audio.playSound(6);
                break;
            case  'О':   audio.playSound(7);
                break;
            case  'У':    audio.playSound(8);
                break;
            case 'Ы':    audio.playSound(9);
                break;
            case 'Э':   audio.playSound(10);
                break;
            case 'Ю':   audio.playSound(11);
                break;
            case 'Я':   audio.playSound(12);
                break;
            default:   audio.playSound(1);
                break;
        }

        if (type == S.delet) {
            S.win++;
            S.right = true;
            //constraintLayout.setBackgroundColor(ContextCompat
            //    .getColor(context, R.color.CHETO));
        } else S.right = false;
        //  FrameLayout parent = (FrameLayout) S.charTextV.getParent();
        // parent.removeView(S.charTextV);
    }





}
