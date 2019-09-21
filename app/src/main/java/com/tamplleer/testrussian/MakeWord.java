package com.tamplleer.testrussian;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.Vector;

public class MakeWord {
    TextView t, point;
    private char[] strToArray;
    private int screenWidth, screenHeight;
    private static char[] a = {'А', 'И', 'Е', 'Ё', 'О', 'У', 'Ы', 'Э', 'Ю', 'Я'};
    String[] word1;
    private ImageView picture;
    private Context context;
    private Vector<Text> textt = new Vector<Text>();
    private int size;
    private static final String TAG = "MainActivity";
    // new variables
    String wordtoscreen = "";

    MakeWord(Context context, int screenWidth, int screenHeight, ImageView picture) {
        this.context = context;
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
        this.picture = picture;

    }

    public String setWordinMas() {
        String word[] = new String[S.wordMassive.length];
        for (int i = 0; i < S.wordMassive.length; i++) {
            word[i] = S.wordMassive[i];
            if (S.changeWord == i + 1) {
                S.stringTOchar = S.wordMassive[i];
                wordtoscreen = S.wordMassive[i];
                createWord();
            }
        }
        return wordtoscreen;
    }

    public void createWord() {
        strToArray = S.stringTOchar.toCharArray();
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
        if (!textt.isEmpty()) {
            for (int i = 0; i < textt.size(); i++) {
                textt.elementAt(i).deletPick();
            }
        }
        textt.clear();
        int x1 = screenWidth / strToArray.length;
        int y = screenHeight / 2;
        int x = 0;
        size = screenHeight / 30;
        for (int i = 0; i < strToArray.length; i++) {

            if (strToArray.length > 9) {
                x = x1 * i + screenWidth / (screenHeight / 128);
            } else {
                x = x1 * i + screenWidth / (screenHeight / 160);
            }
            if (screenWidth < 500) {
                x = x1 * i + 55;
            }
            if (screenWidth < 500 && strToArray.length > 9) {
                x = x1 * i + 45;
            }
            textt.addElement(new Text(context, x, y, strToArray[i], size, i));
            textt.elementAt(i).setPick();
            textt.elementAt(i).paintWord();
        }
      /*  ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) picture.getLayoutParams();
        picture.setX(-400);
        picture.setY(screenHeight / 2 - (screenHeight / 64) - 1);
        params.width = screenWidth;
        params.height = size * 2;*/
        // setNewWord();


    }
}
