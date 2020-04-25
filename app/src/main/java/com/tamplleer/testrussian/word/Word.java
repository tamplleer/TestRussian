package com.tamplleer.testrussian.word;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;

import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tamplleer.testrussian.utils.Audio;
import com.tamplleer.testrussian.R;
import com.tamplleer.testrussian.S;

/**
 * Created by tampl on 22.01.2018.
 */

public class Word implements IWord{
    private Audio audio;
    private final static char[] BIG_LETTER = {'А', 'И', 'Е', 'Ё', 'О', 'У', 'Ы', 'Э', 'Ю', 'Я'};
    private TextView t;
    Context context;
    private int type;
    private char count;
    private TextView charTV;//charTextV
    private final static float ALPHA = 1;

    public Word(Context context, int x, int y, final char count, int size, final int type) {
        audio = new Audio(0);
        this.type = type;
        this.count = count;
        charTV = new TextView(context);
        charTV.setTypeface(null, Typeface.BOLD);
        charTV.setAlpha(ALPHA);
        charTV.setX(x); // координаты
        charTV.setY(y);
        charTV.setScaleX(2f); // изменение размеров в 1.5 раза
        charTV.setScaleY(2f);
        S.type = type;


        t = ((Activity) context).findViewById(R.id.text);
        int shir = size * 3;

        if (size > 50) {
            shir = shir - size;
        }
        if (type == 100) shir = 1000;
        ((Activity) context).addContentView(charTV, new RelativeLayout.LayoutParams(shir, size));
        // вешаем слушателя на клик
        charTV.setOnClickListener(v -> {
            for (char c : BIG_LETTER) {
                if (count == c && S.clickWord) {
                    paintLetter();
                    touched();
                }
            }
        });
    }

    public void setPick() {
        charTV.setText(String.valueOf(count));
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

    private void touched() {
        t.setText("" + count + " ?");
        audio = new Audio(0);
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
        S.wordRight = type == S.delet;
    }
}
