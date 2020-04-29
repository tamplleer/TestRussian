package com.tamplleer.testrussian.word;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;

import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by tampl on 22.01.2018.
 */

public class Letter implements ILetter {

    private int type;
    private char count;
    private TextView letterTextV;
    private final static float ALPHA = 1;
    private int upperCaseLetter;
    private int x, y;

    public Letter(Context context, int x, int y, final char count, int size, final int type, final int upperCaseLetter) {
        this.type = type;
        this.count = count;
        this.x = x;
        this.y = y;
        this.upperCaseLetter = upperCaseLetter;
        letterTextV = new TextView(context);
        letterTextV.setTypeface(null, Typeface.BOLD);
        letterTextV.setAlpha(ALPHA);
        letterTextV.setX(x); // координаты
        letterTextV.setY(y);
        letterTextV.setScaleX(2f); // изменение размеров в 1.5 раза
        letterTextV.setScaleY(2f);
        int shir = size * 3;
        if (size > 50) {
            shir = shir - size;
        }
        ((Activity) context).addContentView(letterTextV, new RelativeLayout.LayoutParams(shir, size));
        letterTextV.setText(String.valueOf(count));
    }

    public TextView getLetterTextV() {
        return letterTextV;
    }

    public void setViewParam() {
        letterTextV.setX(x); // координаты
        letterTextV.setY(y);
        letterTextV.setScaleX(2f); // изменение размеров в 1.5 раза
        letterTextV.setScaleY(2f);
        letterTextV.setTypeface(null, Typeface.BOLD);
        letterTextV.setAlpha(ALPHA);
    }

    public char getCount() {
        return count;
    }

    public int getUpperCaseLetter() {
        return upperCaseLetter;
    }

    public int getType() {
        return type;
    }

    public void deletePick() {
        FrameLayout parent = (FrameLayout) letterTextV.getParent();
        parent.removeView(letterTextV);
    }
}
