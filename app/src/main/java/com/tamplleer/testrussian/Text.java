package com.tamplleer.testrussian;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;

import androidx.constraintlayout.widget.ConstraintLayout;

import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by tampl on 22.01.2018.
 */

public class Text extends MainActivity {


    TextView t;
    char ru;
    Context context;
    int x, y, type;
    char count;
    int shir;
    Typeface fon2=null;
    //int idWord = 0;
    ConstraintLayout constraintLayout;
    float alpha = 1;
    double size; // размер картинки, врещение, прозрачность

    Text(MainActivity main, int x, int y, final char count, int size, final int type,int idWord) {
        try {
            fon2 = font1;
        } catch (Exception e) {
            Log.e("scot", "Could not get typeface: "+e.getMessage());

        }
        this.x = x;
        context = main;
        this.y = y;
        this.size = size;
        this.type = type;
        this.count = count;
        S.charTextV = new TextView(context);
        S.charTextV.setAlpha(alpha);
        S.charTextV.setX(x); // координаты
        S.charTextV.setY(y);
        S.charTextV.setId(idWord);
        S.charTextV.setBackgroundResource(R.color.DarkSlateGray);
        S.charTextV.setTextColor(Color.rgb(255, 228, 181));
        S.charTextV.setScaleX(2f); // изменение размеров в 1.5 раза
        S.charTextV.setScaleY(2f);
        S.charTextV.setText("" + count);
       // S.charTextV.setTypeface(S.fon2);
        ru = count;
        S.type = type;



        t = (TextView) main.findViewById(R.id.text);
        constraintLayout = (ConstraintLayout) main.findViewById(R.id.constrainlayout);
        //      constraintLayout = (ConstraintLayout) findViewById(R.id.constrainlayout);
        // setSize();

        shir = size * 3;

        if (size > 50) {
            shir = shir - size;
        }
        if (type == 100) shir = 1000;
        main.addContentView(S.charTextV, new RelativeLayout.LayoutParams(shir, size));
        S.charTextV.setOnClickListener(new View.OnClickListener() {  // вешаем слушателя на клик
            public void onClick(View v) {
                for (int i = 0; i < a.length; i++) {
                    if (count == a[i] && S.clickWord == true){
                    touched();

                   }
                }
                // метод-обработчик нажатия
            }
        });
    }

//public void setSize(){
    //  if (screenWidth>10 || screenHeight>1280){
    //     size=size*2;
    //      shir=shir-20;
    //   }

    //}
    void touched() {

        //     MainActivity main1 = new MainActivity();
        //    main1.getWindow().setBackgroundDrawableResource(R.drawable.fotodena);
        t.setText("" + count + " ?");
        // S.charTextV.setBackgroundResource(R.color.Khaki);
        //  S.charTextV.setTextColor(Color.rgb(47,79,79));
        // main= new MainActivity();
        audio = new Audio(0);
        //  audio.playSound(5);
        switch (count) {
            case 'А':
                audio.playSound(4);
                break;
            case 'И':
                audio.playSound(5);
                break;
            case 'Е':
                audio.playSound(6);
                break;
            case 'О':
                audio.playSound(7);
                break;
            case 'У':
                audio.playSound(8);
                break;
            case 'Ы':
                audio.playSound(9);
                break;
            case 'Э':
                audio.playSound(10);
                break;
            case 'Ю':
                audio.playSound(11);
                break;
            case 'Я':
                audio.playSound(12);
                break;
            default:
                audio.playSound(1);
                break;
        }

        //constraintLayout.setBackgroundColor(ContextCompat
//    .getColor(context, R.color.CHETO));
        S.right = type == S.delet;
        //  FrameLayout parent = (FrameLayout) S.charTextV.getParent();
        // parent.removeView(S.charTextV);
    }


}
