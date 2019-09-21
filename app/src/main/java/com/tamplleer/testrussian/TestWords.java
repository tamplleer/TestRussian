package com.tamplleer.testrussian;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TestWords {
    public static final int RUNDOMARRAY = 30;
    int lengthINscore = 0;
    RandomWord randomWord;

    TestWords() { //typeTest 30 or all word of array 30 -true all -false

    }

    /*public void start(boolean typeTest, Context context, Button bmnext, TextView t) {
        randomWord = new RandomWord();
        bmnext.setVisibility(View.VISIBLE);
        t.setTextColor(((Activity) context).getResources().getColor(R.color.DarkSlateGray));
        click = true;
        S.win = 0;
        S.steps = 0;
        S.clickWord = true;
        if (typeTest) {
            lengthINscore = RUNDOMARRAY;
        } else {
            lengthINscore = word1.length;
        }

        S.lengsInScore = lengthINscore;
        S.vernoORno = new boolean[word1.length];
        if (typeTest) {
            S.wordMassive = new String[RUNDOMARRAY];
            randomWord.createArray();
        } else {
            S.wordMassive = new String[word1.length];
            S.wordMassive = S.allWord.split(",");
        }

        handler.post(() -> point.setText(S.steps + 1 + "/" + lengthINscore));
        S.changeWord = 1;
        // setWordinMas();
        wordtoscreen = makeWord.setWordinMas();
        constraintLayout.setBackgroundResource(R.drawable.fon);
        t.setText("");
        audio.playSound(1);
        button.setVisibility(View.INVISIBLE);
    }*/
}
