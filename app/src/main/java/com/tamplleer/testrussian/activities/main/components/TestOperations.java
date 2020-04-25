package com.tamplleer.testrussian.activities.main.components;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.tamplleer.testrussian.R;
import com.tamplleer.testrussian.S;
import com.tamplleer.testrussian.activities.result.endGame;
import com.tamplleer.testrussian.utils.Audio;
import com.tamplleer.testrussian.utils.Font;
import com.tamplleer.testrussian.utils.GetScreenSize;

public class TestOperations {
    TextView t, score;
    Button button, bmstart30, bmnext;
    GetScreenSize getScreenSize;
    ImageView picture;
    int screenWidth;
    int screenHeight;
    private static final int RUNDOMARRAY = 30;
    private int lengthINscore = 0;
    private RandomWord randomWord;
    private boolean click = false;
    private String[] word1;
    private String wordtoscreen = "";
    private Handler handler = new Handler();
    private MakeWord makeWord;
    private ConstraintLayout constraintLayout;
    Context context;
    private Audio audio;
    private Font font;

    /**
     * This class make operations with word
     * if you click button start test or next word
     * @param context
     * @param audio
     */
    public TestOperations(Context context, Audio audio) {
        click = false;
        word1 = S.allWord.split(",");
        this.context = context;
        this.audio = audio;
        font = new Font(context);
        getScreenSize = new GetScreenSize(context);
        screenWidth = getScreenSize.getScreenWidth();
        screenHeight = getScreenSize.getScreenHeight();
        constraintLayout = ((Activity) context).findViewById(R.id.constrainlayout);
        picture = ((Activity) context).findViewById(R.id.imageView);
        picture.setX(0);
        picture.setY(0);
        button = ((Activity) context).findViewById(R.id.startGA);

        bmstart30 = ((Activity) context).findViewById(R.id.start30);

        bmnext = ((Activity) context).findViewById(R.id.next);
        bmnext.setVisibility(View.INVISIBLE);
        t = ((Activity) context).findViewById(R.id.text);
        score = ((Activity) context).findViewById(R.id.points);
        t.setTextSize(screenWidth / 25);
        t.setTypeface(font.getFont1());
        score.setTypeface(font.getFont1());
        button.setTypeface(font.getFont1());
        bmnext.setTypeface(font.getFont1());
        bmstart30.setTypeface(font.getFont1());
        button.setVisibility(View.VISIBLE);
        makeWord = new MakeWord(context, screenWidth, screenHeight, picture);
    }

    /**
     * Start test user 30/all words
     * @param typeTest
     */
    //todo person can change how many word learn?!
    public void start(boolean typeTest) {//typeTest 30 or all word of array 30 -true all -false
        randomWord = new RandomWord();
        bmnext.setVisibility(View.VISIBLE);
        t.setTextColor(context.getResources().getColor(R.color.DarkSlateGray));
        click = true;
        S.win = 0;
        S.steps = 0;
        S.clickWord = true;
        S.lengsInScore = lengthINscore;
        S.vernoORno = new boolean[word1.length];
        S.right = true;
        if (typeTest) {
            lengthINscore = RUNDOMARRAY;
            S.wordMassive = new String[RUNDOMARRAY];
            randomWord.createArrayRandWords();
        } else {
            lengthINscore = word1.length;
            S.wordMassive = new String[word1.length];
            S.wordMassive = S.allWord.split(",");
        }

        handler.post(() -> score.setText(S.steps + 1 + "/" + lengthINscore));
        S.changeWord = 1;
        wordtoscreen = makeWord.showWord(S.changeWord);
        constraintLayout.setBackgroundResource(R.drawable.fon);
        t.setText("");
        audio.playSound(1);
        button.setVisibility(View.INVISIBLE);
    }

    public void next() {
        //bmnext.setVisibility(View.VISIBLE);
        if (click) {
            S.right = S.wordRight;
            S.wordRight = false;
            S.steps++;
            S.changeWord++;
            handler.post(() -> score.setText(S.steps + 1 + "/" + lengthINscore));
            constraintLayout.setBackgroundResource(R.drawable.fon);
            if (S.right) {
                S.win++;
                t.setTextColor(context.getResources().getColor(R.color.DarkSlateGray));
                if (S.pravi > 0) audio.playSound(14);
                else audio.playSound(13);
                S.pravi++;
                S.vernoORno[S.steps - 1] = true;
                //point.setText(S.win + "/" + lengthINscore);
                YoYo.with(Techniques.StandUp)
                        .duration(700)
                        .repeat(0)
                        .playOn(((Activity) context).findViewById(R.id.text));

            } else {
                t.setTextColor(context.getResources().getColor(R.color.Khaki));
                YoYo.with(Techniques.Shake)
                        .duration(700)
                        .repeat(0)
                        .playOn(((Activity) context).findViewById(R.id.text));
                audio.playSound(15);
                S.pravi = 0;
                constraintLayout.setBackgroundResource(R.drawable.fonred);
                S.vernoORno[S.steps - 1] = false;
            }


            t.setText("" + wordtoscreen);


            //setWordinMas();
            wordtoscreen = makeWord.showWord(S.changeWord);
            // S.right = false;
            if (S.steps == lengthINscore) {
                // t.setText(S.win + "  OF  " + lengthINscore );
                click = false;
                // S.someClick = false;
                S.clickWord = false;
                // if (S.win<10) audio.playSound(winSound);
                // if (S.win>=25)audio.playSound(winSound);
                //  if (S.win==lengthINscore)audio.playSound(winALL);
                Intent intent = new Intent(context,
                        endGame.class);
                context.startActivity(intent);
                ((Activity) context).finish();

            }
        }
    }
}
