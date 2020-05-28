package com.tamplleer.testrussian.activities.main.components;

import android.content.Context;
import android.view.View;

import com.tamplleer.testrussian.R;
import com.tamplleer.testrussian.TestParam;
import com.tamplleer.testrussian.firbase.database.DataBase;
import com.tamplleer.testrussian.utils.Audio;

public class MakeTest {

    private static final int RANDOM_ARRAY = 30;
    private String[] allWord;
    private TestParam statistic;
    private ObjectsInLayout objectsInLayout;
    private Audio audio;
    Context context;
    private MakeWord makeWord;
    private DataBase dataBase;
    private String sss;

    MakeTest(TestParam statistic, Audio audio, ObjectsInLayout objectsInLayout, Context context, MakeWord makeWord, DataBase dataBase) {
        this.statistic = statistic;

        this.objectsInLayout = objectsInLayout;
        this.audio = audio;
        this.context = context;
        this.makeWord = makeWord;
        this.dataBase = dataBase;
    }

    public void crateRandomTest() {
        allWord = dataBase.get();
        RandomWord randomWord = new RandomWord();
        statistic.setAnswerArray(new boolean[RANDOM_ARRAY]);
        statistic.setLengthInScore(RANDOM_ARRAY);
        statistic.setWordMassive(randomWord.createArrayRandWords(allWord));
        setDefaultParam();
    }

    public void createAllWordsTest() {
        allWord = dataBase.get();
        statistic.setAnswerArray(new boolean[allWord.length]); //NEED&???
        statistic.setLengthInScore(allWord.length);
        // statistic.setWordMassive(S.allWord.split(","));
        statistic.setWordMassive(allWord);
        setDefaultParam();
    }


    private void setDefaultParam() {
        objectsInLayout.getButtonNext().setVisibility(View.VISIBLE);
        objectsInLayout.getButtonMiddle().setVisibility(View.INVISIBLE);
        objectsInLayout.getMainInformText().setTextColor(context.getResources().getColor(R.color.DarkSlateGray));
        objectsInLayout.getMainInformText().setText("");
        statistic.setWinResult(0);
        statistic.setSteps(28);

        statistic.setRightAnswer(true);
        objectsInLayout.getScore().setText(statistic.getSteps() + 1 + "/" + statistic.getLengthInScore());

        audio.playSound(audio.getSoundNumber("anvil"));
        statistic.setWordToScreen(makeWord.showWordCreateNewWord(statistic.getSteps()));
        objectsInLayout.getConstraintLayout().setBackgroundResource(R.drawable.background);
    }
}
