package com.tamplleer.testrussian.activities.main.components;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.tamplleer.testrussian.R;
import com.tamplleer.testrussian.S;
import com.tamplleer.testrussian.activities.result.EndGame;
import com.tamplleer.testrussian.utils.Audio;
import com.tamplleer.testrussian.utils.Font;
import com.tamplleer.testrussian.utils.GetScreenSize;

import java.io.Serializable;

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
   // private boolean click = false;
    private String[] word1;
    private String wordtoscreen = "";
    private Handler handler = new Handler();
    private MakeWord makeWord;
    private ConstraintLayout constraintLayout;
    Context context;
    private Audio audio;
    private Font font;
    boolean answerRight;
    LetterChange letterChange;
    private int rightStrike = 0;
    private int selectWordNumber = 0;
    private int winResult;
    private boolean[] answerArray;

    /**
     * This class make operations with word
     * if you click button start test or next word
     *
     * @param context
     * @param audio
     */
    public TestOperations(Context context, Audio audio) {
        this.context = context;
        this.audio = audio;

        word1 = S.allWord.split(",");

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
        letterChange = new LetterChange(context,audio);
        makeWord = new MakeWord(context, screenWidth, screenHeight, picture, letterChange);
        letterChange.setLetterColor(true);
        bmnext.setClickable(false);
    }

    /**
     * Start test user 30/all words
     *
     * @param typeTest
     */
    //todo person can change how many word learn?!
    public void start(boolean typeTest) {//typeTest 30 or all word of array 30 -true all -false
        randomWord = new RandomWord();
        bmnext.setVisibility(View.VISIBLE);
        t.setTextColor(context.getResources().getColor(R.color.DarkSlateGray));

        winResult = 0;
        S.steps = 29;
        S.wordMassive = null;
        selectWordNumber = 0;
        S.lengsInScore = lengthINscore;
        answerArray = new boolean[word1.length];
        answerRight = true;
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
        wordtoscreen = makeWord.showWord(selectWordNumber);
        constraintLayout.setBackgroundResource(R.drawable.background);
        t.setText("");
        audio.playSound(audio.getSoundNumber("anvil"));
        button.setVisibility(View.INVISIBLE);
    }
    public void next() {
            answerRight = letterChange.isLetterColor();
            letterChange.setLetterColor(false);
            S.steps++;
            selectWordNumber++;
            handler.post(() -> score.setText(S.steps + 1 + "/" + lengthINscore));
            if (answerRight) {
                constraintLayout.setBackgroundResource(R.drawable.background);
                winResult++;
                t.setTextColor(context.getResources().getColor(R.color.DarkSlateGray));
                if (rightStrike > 0) audio.playSound(audio.getSoundNumber("rightMore"));
                else audio.playSound(audio.getSoundNumber("right"));
                rightStrike++;
                answerArray[S.steps - 1] = true;
                YoYo.with(Techniques.StandUp)
                        .duration(700)
                        .repeat(0)
                        .playOn(t);

            } else {
                t.setTextColor(context.getResources().getColor(R.color.Khaki));
                YoYo.with(Techniques.Shake)
                        .duration(700)
                        .repeat(0)
                        .playOn(((Activity) context).findViewById(R.id.text));
                audio.playSound(audio.getSoundNumber("wrong"));
                rightStrike = 0;
                constraintLayout.setBackgroundResource(R.drawable.background_red_2);
                answerArray[S.steps - 1] = false;
            }


            t.setText("" + wordtoscreen);

            if (S.steps == lengthINscore) {
                Intent intent = new Intent(context,
                        EndGame.class);
                intent.putExtra("rightAnswer",winResult);
                intent.putExtra("arrayRight",answerArray);
                context.startActivity(intent);
                ((Activity) context).finish();

            } else {
                letterChange.setLetterColor(answerRight);
                wordtoscreen = makeWord.showWord(selectWordNumber);
            }
            bmnext.setClickable(false);

    }
}
