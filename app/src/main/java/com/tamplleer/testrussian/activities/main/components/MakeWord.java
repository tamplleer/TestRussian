package com.tamplleer.testrussian.activities.main.components;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.tamplleer.testrussian.S;
import com.tamplleer.testrussian.TestParam;
import com.tamplleer.testrussian.word.Letter;

import java.util.Vector;

class MakeWord {
    private char[] charArray;
    private int screenWidth, screenHeight;
    private ImageView picture;
    private Context context;
    private Vector<Letter> word = new Vector<>();
    private int size;
    private LetterChange letterChange;
    // new variables
    private String stringToChar;
    private String wordtoscreen = "";
    private TestParam testParam;

    MakeWord(Context context, int screenWidth, int screenHeight, LetterChange letterChange, TestParam testParam) {
        this.context = context;
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
        this.letterChange = letterChange;
        this.testParam = testParam;

    }

    String showWord(int wordNumber) {
        stringToChar = testParam.getWordFromArray(wordNumber);
        wordtoscreen = testParam.getWordFromArray(wordNumber);
        createWord();
        return wordtoscreen;
    }

    /**
     * Get char array and create word
     * Find witch letter big and make it small
     */
    private void createWord() {
        charArray = stringToChar.toCharArray();
        clearLetterArray();
        int y = screenHeight / 2;
        int x;
        int upperCaseLetter = -1;
        size = screenHeight / 30;
        for (int i = 0; i < charArray.length; i++) {
            if (Character.isUpperCase(charArray[i])) {
                upperCaseLetter = i;
            }
            if (Character.isLowerCase(charArray[i])) {
                charArray[i] = Character.toUpperCase(charArray[i]);
            }
            if (charArray[i] == 'Ё') {
                charArray[i] = 'Е';
            }
            x = setXPosition(screenWidth / charArray.length, i);
            word.addElement(new Letter(context, x, y, charArray[i], size, i, upperCaseLetter));

            int finalI = i;
            word.get(i).getLetterTextV().setOnClickListener(v -> {
                letterChange.clickOnLetter(word.get(finalI), word);
            });
            letterChange.paintWord(word.get(finalI).getLetterTextV());
        }

    }

    /**
     * set position of letter
     *
     * @param letterWidth
     * @param i
     * @return
     */
    private int setXPosition(int letterWidth, int i) {
        int x;
        if (charArray.length > 9) {
            x = letterWidth * i + screenWidth / (screenHeight / 140);
        } else if (charArray.length < 5) {
            x = letterWidth * i + screenWidth / (screenHeight / 250);
        } else {
            x = letterWidth * i + screenWidth / (screenHeight / 180);
        }
        if (screenWidth < 500) {
            x = letterWidth * i + 55;
        }
        if (screenWidth < 500 && charArray.length > 9) {
            x = letterWidth * i + 45;
        }
        return x;
    }

    /**
     * clear array and delete text view
     */
    private void clearLetterArray() {
        if (!word.isEmpty()) {
            for (Letter w : word) {
                w.deletePick();
            }
        }
        word.clear();
    }
}
