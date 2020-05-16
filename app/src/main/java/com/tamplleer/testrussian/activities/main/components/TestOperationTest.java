package com.tamplleer.testrussian.activities.main.components;

import android.content.Context;
import android.os.Handler;

import com.tamplleer.testrussian.TestParam;
import com.tamplleer.testrussian.utils.Audio;
@Deprecated
public class TestOperationTest {



    private static final int RUNDOMARRAY = 30;
    private int lengthINscore = 0;
    private RandomWord randomWord;
    private String[] word1;
    private String wordtoscreen = "";
    private Handler handler = new Handler();
    private MakeWord makeWord;
    Context context;
    private Audio audio;
    boolean answerRight;
    LetterChange letterChange;
    private int rightStrike = 0;
    private int selectWordNumber = 0;
    private int winResult;
    private boolean[] answerArray;
    private TestParam statistic;

    ObjectsInLayout objectsInLayout;

    /**
     * This class make operations with word
     * if you click button start test or next word
     *
     * @param context
     * @param audio
     */
/*    public TestOperationTest(Context context, Audio audio) {
        this.context = context;
        this.audio = audio;
        objectsInLayout = new ObjectsInLayout(context);
        word1 = S.allWord.split(",");
        letterChange = new LetterChange(context,audio);
        makeWord = new MakeWord(context, objectsInLayout.getScreenWidth(),
                objectsInLayout.getScreenHeight(), letterChange);

        letterChange.setLetterColor(true);
        statistic= new Statistic();
    }*/

    /**
     * Start test user 30/all words
     *
     * @param typeTest
    */

    //todo person can change how many word learn?!
    public void start(boolean typeTest) {//typeTest 30 or all word of array 30 -true all -false

    }
/*    public void next() {
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

    }*/
}
