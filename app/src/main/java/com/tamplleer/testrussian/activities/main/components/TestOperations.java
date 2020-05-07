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

import com.tamplleer.testrussian.R;
import com.tamplleer.testrussian.S;
import com.tamplleer.testrussian.TestParam;
import com.tamplleer.testrussian.activities.result.EndGame;
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
    private RandomWord randomWord;
    private String[] word1;
    private String wordtoscreen = "";
    private Handler handler = new Handler();
    private MakeWord makeWord;
    private ConstraintLayout constraintLayout;
    Context context;
    private Audio audio;
    private Font font;
    //boolean answerRight;
    LetterChange letterChange;
    private int rightStrike = 0;
    private boolean[] answerArray;
    private TestParam statistic;
    private AnimationObject animationObject;

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
        statistic= new TestParam();
        makeWord = new MakeWord(context, screenWidth, screenHeight, letterChange,statistic);
        letterChange.setLetterColor(true);
        bmnext.setClickable(false);

        animationObject = new AnimationObject();
    }

    /**
     * Start test user 30/all words
     *
     * @param typeTest
     */
    //todo person can change how many word learn?!
    public void start(boolean typeTest) {//typeTest 30 or all word of array 30 -true all -false

        bmnext.setVisibility(View.VISIBLE);
        t.setTextColor(context.getResources().getColor(R.color.DarkSlateGray));

      statistic.setWinResult(0);
        statistic.setSteps(27);
        statistic.setWordMassive(null);
        statistic.setSelectWordNumber(0);
        answerArray = new boolean[word1.length];
        statistic.setAnswerRight(true);
        if (typeTest) {
            RandomWord randomWord = new RandomWord();
            statistic.setAnswerArray(new boolean[30]);
            statistic.setLengthInScore(30);
            statistic.setWordMassive(randomWord.createArrayRandWords());
        } else {
            statistic.setLengthInScore(word1.length);
            statistic.setWordMassive(S.allWord.split(","));
        }

        handler.post(() -> score.setText(statistic.getSteps() + 1 + "/" + statistic.getLengthInScore()));
        makeWord.showWord(statistic.getSelectWordNumber());
        constraintLayout.setBackgroundResource(R.drawable.background);
        t.setText("");
        audio.playSound(audio.getSoundNumber("anvil"));
        button.setVisibility(View.INVISIBLE);
    }
    public void next() {
        statistic.setAnswerRight(letterChange.isLetterColor());
            letterChange.setLetterColor(false);
           statistic.setSteps(statistic.getSteps()+1);
            statistic.setSelectWordNumber(statistic.getSelectWordNumber()+1);
            handler.post(() -> score.setText(statistic.getSteps() + 1 + "/" + statistic.getLengthInScore()));
            if (statistic.isAnswerRight()) {
                constraintLayout.setBackgroundResource(R.drawable.background);
               statistic.setWinResult(statistic.getWinResult()+1);
                t.setTextColor(context.getResources().getColor(R.color.DarkSlateGray));
                if (rightStrike > 0) audio.playSound(audio.getSoundNumber("rightMore"));
                else audio.playSound(audio.getSoundNumber("right"));
                rightStrike++;
                answerArray[statistic.getSteps() - 1] = true;
                animationObject.standUp(t);


            } else {
                t.setTextColor(context.getResources().getColor(R.color.Khaki));
                animationObject.shake(((Activity) context).findViewById(R.id.text));
                audio.playSound(audio.getSoundNumber("wrong"));
                rightStrike = 0;
                constraintLayout.setBackgroundResource(R.drawable.background_red_2);
                answerArray[statistic.getSteps() - 1] = false;
            }


            t.setText("" + wordtoscreen);

            if (statistic.getSteps() == statistic.getLengthInScore()) {
                Intent intent = new Intent(context,
                        EndGame.class);
                intent.putExtra("rightAnswer",statistic.getWinResult());
                intent.putExtra("arrayRight",answerArray);
                intent.putExtra("lengthInScore", statistic.getLengthInScore());
                intent.putExtra("wordArray",statistic.getWordMassive());
                context.startActivity(intent);
                ((Activity) context).finish();

            } else {
                letterChange.setLetterColor(statistic.isAnswerRight());
                wordtoscreen = makeWord.showWord(statistic.getSelectWordNumber());
            }
            bmnext.setClickable(false);

    }
}
