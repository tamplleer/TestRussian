package com.tamplleer.testrussian.activities.main.components;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.widget.Button;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.tamplleer.testrussian.R;
import com.tamplleer.testrussian.S;
import com.tamplleer.testrussian.utils.Audio;
import com.tamplleer.testrussian.word.Letter;

import java.util.Vector;

public class LetterChange {
    private Audio audio;
    private TextView informText;
    private Button bmnext;
private Context context;
    private boolean letterColor = false;
    private boolean letterAnswer = false;

    public void setLetterColor(boolean letterColor) {
        this.letterColor = letterColor;
    }

    private final static char[] BIG_LETTER = {'А', 'И', 'Е', 'Ё', 'О', 'У', 'Ы', 'Э', 'Ю', 'Я'};

    LetterChange(Context context) {
        audio = new Audio(0);
        informText = ((Activity) context).findViewById(R.id.text);
        bmnext = ((Activity) context).findViewById(R.id.next);
        this.context=context;
    }

    void clickOnLetter(Letter letter, Vector<Letter> word) {
        for (Letter l : word) {
            paintWord(l.getLetterTextV());

        }
        bmnext.setClickable(true);
       TextView textView= letter.getLetterTextV();
        for (char c : BIG_LETTER) {
            if (letter.getCount() == c && S.clickWord) {
                paintLetter(letter.getLetterTextV());
                YoYo.with(Techniques.Swing)
                        .duration(700)
                        .repeat(0)
                        .playOn(textView);
                letter.setViewParam();
                touched(letter.getCount(), letter.getUpperCaseLetter(), letter.getType());
            }
        }

    }

    public boolean isLetterColor() {
        return letterAnswer;
    }

    private void touched(char count, int upperCaseLetter, int type) {
        informText.setText("" + count + " ?");
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
        letterAnswer = type == upperCaseLetter;
    }

    private void paintLetter(TextView letter) {
        if (letterColor) {
            letter.setTextColor(Color.rgb(178, 34, 34));
        } else {
            letter.setTextColor(Color.rgb(47, 79, 79));
        }
    }

    public void paintWord(TextView letter) {
        if (letterColor) {
            letter.setTextColor(Color.rgb(47, 79, 79));
        } else {
            letter.setTextColor(Color.rgb(255, 228, 181));
        }
    }

}
