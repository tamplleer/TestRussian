package com.tamplleer.testrussian;

import android.content.Context;
import android.widget.ImageView;

import com.tamplleer.testrussian.word.Word;

import java.util.Vector;

class MakeWord {
    private char[] charArray;
    private int screenWidth, screenHeight;
    private static char[] bigLetter = {'А', 'И', 'Е', 'Ё', 'О', 'У', 'Ы', 'Э', 'Ю', 'Я'};
    private ImageView picture;
    private Context context;
    private Vector<Word> textt = new Vector<>();
    private int size;
    private static final String TAG = "MainActivity";
    // new variables
    private String stringToChar;
    private String wordtoscreen = "";

    MakeWord(Context context, int screenWidth, int screenHeight, ImageView picture) {
        this.context = context;
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
        this.picture = picture;

    }

    String setWordinMas() {
        String[] word = new String[S.wordMassive.length];
        for (int i = 0; i < S.wordMassive.length; i++) {
            word[i] = S.wordMassive[i];
            if (S.changeWord == i + 1) {
                stringToChar = S.wordMassive[i];
                wordtoscreen = S.wordMassive[i];
                createWord();
            }
        }
        return wordtoscreen;
    }

    /**
     * Get char array and create word
     */
    private void createWord() {
        charArray = stringToChar.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            if (charArray[i] == bigLetter[0] ||
                    charArray[i] == bigLetter[1] ||
                    charArray[i] == bigLetter[2] ||
                    charArray[i] == bigLetter[3] ||
                    charArray[i] == bigLetter[4] ||
                    charArray[i] == bigLetter[5] ||
                    charArray[i] == bigLetter[6] ||
                    charArray[i] == bigLetter[7] ||
                    charArray[i] == bigLetter[8] ||
                    charArray[i] == bigLetter[9]) S.delet = i;
            if (Character.isLowerCase(charArray[i]))
                charArray[i] = Character.toUpperCase(charArray[i]);
            if (charArray[i] == 'Ё') charArray[i] = 'Е';
        }
        if (!textt.isEmpty()) {
            for (int i = 0; i < textt.size(); i++) {
                textt.elementAt(i).deletPick();
            }
        }
        textt.clear();
        int x1 = screenWidth / charArray.length;
        int y = screenHeight / 2;
        int x;
        size = screenHeight / 30;
        for (int i = 0; i < charArray.length; i++) {

            if (charArray.length > 9) {
                x = x1 * i + screenWidth / (screenHeight / 140);
            } else if(charArray.length <5){
                x = x1 * i + screenWidth / (screenHeight / 250);
            }
            else {
                x = x1 * i + screenWidth / (screenHeight / 180);
            }
            if (screenWidth < 500) {
                x = x1 * i + 55;
            }
            if (screenWidth < 500 && charArray.length > 9) {
                x = x1 * i + 45;
            }
            textt.addElement(new Word(context, x, y, charArray[i], size, i));
            textt.elementAt(i).setPick();
            textt.elementAt(i).paintWord();
        }
    }
}
