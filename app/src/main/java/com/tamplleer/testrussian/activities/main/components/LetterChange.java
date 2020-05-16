package com.tamplleer.testrussian.activities.main.components;

import android.app.Activity;
import android.content.Context;
import android.widget.Button;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.tamplleer.testrussian.R;
import com.tamplleer.testrussian.utils.Audio;
import com.tamplleer.testrussian.word.Letter;

import java.util.Vector;

public class LetterChange {
    private Audio audio;
    private TextView informText;
    private Button buttonNext;
    private Context context;
    private AnimationObject animationObject;
    private boolean letterColor = false;
    private boolean letterAnswer = false;
    //todo use new class to clean code

    public void setLetterColor(boolean letterColor) {
        this.letterColor = letterColor;
    }

    private final static char[] BIG_LETTER = {'А', 'И', 'Е', 'Ё', 'О', 'У', 'Ы', 'Э', 'Ю', 'Я'};

    LetterChange(Context context, Audio audio) {
        this.audio = audio;
        informText = ((Activity) context).findViewById(R.id.text);
        buttonNext = ((Activity) context).findViewById(R.id.next);
        animationObject = new AnimationObject();
        this.context = context;
    }

    void clickOnLetter(Letter letter, Vector<Letter> word) {
        for (Letter l : word) {
            paintWord(l.getLetterTextV());

        }
        buttonNext.setClickable(true);
        TextView textView = letter.getLetterTextV();
        for (char c : BIG_LETTER) {
            if (letter.getCount() == c) {
                paintLetter(letter.getLetterTextV());
                animationObject.swing(textView);
                letter.setViewParam();
                touched(letter.getCount(), letter.getUpperCaseLetter(), letter.getType());
                break;
            }
        }

    }

    public boolean isLetterColor() {
        return letterAnswer;
    }

    private void touched(char count, int upperCaseLetter, int type) {
        informText.setText(String.format("%c ?", count));
        switch (count) {
            case 'А':
                audio.playSound(audio.getSoundNumber("a"));
                break;
            case 'И':
                audio.playSound(audio.getSoundNumber("i"));
                break;
            case 'Е':
                audio.playSound(audio.getSoundNumber("e"));
                break;
            case 'О':
                audio.playSound(audio.getSoundNumber("o"));
                break;
            case 'У':
                audio.playSound(audio.getSoundNumber("y"));
                break;
            case 'Ы':
                audio.playSound(audio.getSoundNumber("ie"));
                break;
            case 'Э':
                audio.playSound(audio.getSoundNumber("iea"));
                break;
            case 'Ю':
                audio.playSound(audio.getSoundNumber("iy"));
                break;
            case 'Я':
                audio.playSound(audio.getSoundNumber("ia"));
                break;
            default:
                audio.playSound(1);
                break;
        }
        letterAnswer = (type == upperCaseLetter);
    }

    private void paintLetter(TextView letter) {
        if (letterColor) {
            letter.setTextColor(context.getResources().getColor(R.color.FireBrick));
        } else {
            letter.setTextColor(context.getResources().getColor(R.color.DarkSlateGray));
        }
    }

    public void paintWord(TextView letter) {
        if (letterColor) {
            letter.setTextColor(context.getResources().getColor(R.color.DarkSlateGray));
        } else {
            letter.setTextColor(context.getResources().getColor(R.color.Moccasin));
        }
    }

}
