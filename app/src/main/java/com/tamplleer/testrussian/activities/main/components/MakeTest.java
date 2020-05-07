package com.tamplleer.testrussian.activities.main.components;

import android.content.Context;
import android.view.View;

import com.tamplleer.testrussian.R;
import com.tamplleer.testrussian.S;
import com.tamplleer.testrussian.TestParam;
import com.tamplleer.testrussian.utils.Audio;

public class MakeTest {

    private static final int RANDOM_ARRAY = 30;
    private String[] allWord;
    private TestParam statistic;
    private ObjectsInLayout objectsInLayout;
    private Audio audio;
    Context context;
    private MakeWord makeWord;

    MakeTest(TestParam statistic, Audio audio, ObjectsInLayout objectsInLayout, Context context, MakeWord makeWord) {
        this.statistic = statistic;
        allWord = S.allWord.split(",");
        this.objectsInLayout = objectsInLayout;
        this.audio = audio;
        this.context = context;
        this.makeWord = makeWord;

    }

    public void crateRandomTest() {
        RandomWord randomWord = new RandomWord();
        statistic.setAnswerArray(new boolean[RANDOM_ARRAY]);
        statistic.setLengthInScore(RANDOM_ARRAY);
        statistic.setWordMassive(randomWord.createArrayRandWords());
        setDefaultParam();
    }

    public void createAllWordsTest() {
        statistic.setAnswerArray(new boolean[allWord.length]); //NEED&???
        statistic.setLengthInScore(allWord.length);
        statistic.setWordMassive(S.allWord.split(","));
        setDefaultParam();
    }

    private void setDefaultParam() {
        objectsInLayout.getBmnext().setVisibility(View.VISIBLE);
        objectsInLayout.getMiddleButton().setVisibility(View.INVISIBLE);
        objectsInLayout.getT().setTextColor(context.getResources().getColor(R.color.DarkSlateGray));
        objectsInLayout.getT().setText("");
        statistic.setWinResult(0);
        statistic.setSteps(27);
        statistic.setSelectWordNumber(0);
        statistic.setAnswerRight(true);
        objectsInLayout.getScore().setText(statistic.getSteps() + 1 + "/" + statistic.getLengthInScore());

        audio.playSound(audio.getSoundNumber("anvil"));
        statistic.setWordToScreen(makeWord.showWord(statistic.getSelectWordNumber()));
        objectsInLayout.getConstraintLayout().setBackgroundResource(R.drawable.background);
    }
}
