package com.tamplleer.testrussian.word;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tamplleer.testrussian.utils.Font;
import com.tamplleer.testrussian.R;

/**
 * Created by tampl on 13.02.2018.
 */

public class LetterEnd implements ILetter {
    private final static float ALPHA = 1;
    public LetterEnd(Context context, int x, int y, String count, int size, final boolean trueOrFalse, boolean zero) {
        Font font = new Font(context);
        TextView charTextV = new TextView(context);
        if (zero) charTextV.setBackgroundResource(R.color.DarkSlateGray);
        charTextV.setAlpha(ALPHA);
        charTextV.setX(x); // координаты
        charTextV.setY(y);
        charTextV.setTypeface(font.getFont1());
        if (trueOrFalse) charTextV.setTextColor(Color.rgb(50, 205, 50));
        else charTextV.setTextColor(Color.rgb(255, 0, 0));
        charTextV.setText(String.valueOf(count));
        int shir = size * 8;
        ((Activity) context).addContentView(charTextV, new RelativeLayout.LayoutParams(shir, size));
    }
}

