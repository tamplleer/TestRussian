package com.tamplleer.testrussian;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;

import androidx.constraintlayout.widget.ConstraintLayout;

import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by tampl on 22.01.2018.
 */

public class Text {
    Audio audio;
    Font font;
    private static char[] a = {'А', 'И', 'Е', 'Ё', 'О', 'У', 'Ы', 'Э', 'Ю', 'Я'};
    TextView t;
    char ru;
    Context context;
    int x, y, type;
    char count;
    private TextView charTV;//charTextV
    int shir;
    //int idWord = 0;
    ConstraintLayout constraintLayout;
    float alpha = 1;
    double size; // размер картинки, врещение, прозрачность

    Text(Context context, int x, int y, final char count, int size, final int type) {
        font = new Font(context);
        audio = new Audio(0);
        this.x = x;
        this.y = y;
        this.size = size;
        this.type = type;
        this.count = count;
        charTV = new TextView(context);
        charTV.setTypeface(null, Typeface.BOLD);
        charTV.setAlpha(alpha);
        charTV.setX(x); // координаты
        charTV.setY(y);
        //charTV.setBackgroundResource(R.color.DarkSlateGray);
        charTV.setScaleX(2f); // изменение размеров в 1.5 раза
        charTV.setScaleY(2f);
        // charTV.setTypeface(S.fon2);
        ru = count;
        S.type = type;


        t = ((Activity) context).findViewById(R.id.text);
        constraintLayout = (ConstraintLayout) ((Activity) context).findViewById(R.id.constrainlayout);
        shir = size * 3;

        if (size > 50) {
            shir = shir - size;
        }
        if (type == 100) shir = 1000;
        ((Activity) context).addContentView(charTV, new RelativeLayout.LayoutParams(shir, size));
        charTV.setOnClickListener(new View.OnClickListener() {  // вешаем слушателя на клик
            public void onClick(View v) {
                for (int i = 0; i < a.length; i++) {
                    if (count == a[i] && S.clickWord) {
                        touched();
                        paintLetter();
                    }
                }
                // метод-обработчик нажатия
            }
        });
    }

    public void setPick() {
        charTV.setText("" + count);
    }

    public void deletPick() {
        FrameLayout parent = (FrameLayout) charTV.getParent();
        parent.removeView(charTV);
    }

    private void paintLetter() {
        if (S.right) {
            charTV.setTextColor(Color.rgb(178, 34, 34));
        } else {
            charTV.setTextColor(Color.rgb(47, 79, 79));
        }
    }

    public void paintWord() {
        if (S.right) {
            charTV.setTextColor(Color.rgb(47, 79, 79));
        } else {
            charTV.setTextColor(Color.rgb(255, 228, 181));
        }
    }

//public void setSize(){
    //  if (screenWidth>10 || screenHeight>1280){
    //     size=size*2;
    //      shir=shir-20;
    //   }

    //}
    void touched() {
        t.setText("" + count + " ?");
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
        //  FrameLayout parent = (FrameLayout) charTV.getParent();
        // parent.removeView(charTV);
    }


}
