package com.tamplleer.testrussian.activities.main.components;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.tamplleer.testrussian.AnimationObject;
import com.tamplleer.testrussian.R;
import com.tamplleer.testrussian.TestParam;
import com.tamplleer.testrussian.activities.result.EndGame;
import com.tamplleer.testrussian.utils.Audio;

public class TestOperations {
    private MakeWord makeWord;
    private Context context;
    private Audio audio;
    private LetterChange letterChange;
    private int rightStrike = 0;
    private TestParam testParams;
    private AnimationObject animationObject;
    private MakeTest makeTest;
    private ObjectsInLayout objectsInLayout;


    /**
     * This class make operations with word
     * if you click button start test or next word
     *
     * @param context
     * @param audio
     */
    public TestOperations(Context context, Audio audio, ObjectsInLayout objectsInLayout) {
        this.context = context;
        this.audio = audio;
        this.objectsInLayout = objectsInLayout;
        int screenWidth = objectsInLayout.getGetScreenSize().getScreenWidth();
        int screenHeight = objectsInLayout.getGetScreenSize().getScreenHeight();


        letterChange = new LetterChange(context, audio);
        testParams = new TestParam();
        makeWord = new MakeWord(context, screenWidth, screenHeight, letterChange, testParams);
        animationObject = new AnimationObject();
        makeTest = new MakeTest(testParams, audio, objectsInLayout, context, makeWord);
    }

    /**
     * Start test user 30/all words
     *
     * @param typeTest
     */
    //todo person can change how many word learn?!
    public void start(boolean typeTest) {
        letterChange.setLetterColor(true);
        if (typeTest) {
            makeTest.crateRandomTest();
        } else {
            makeTest.createAllWordsTest();
        }
    }

    public void next() {
        setDefaultNextParam();

        if (testParams.isRightAnswer()) rightAnswer();
        else wrongAnswer();

        if (testParams.getSteps() != testParams.getLengthInScore()) makeNextWord();
        else changeActivitySaveParam();


    }

    private void makeNextWord() {
        letterChange.setLetterColor(testParams.isRightAnswer());
        testParams.setWordToScreen(makeWord.showWordCreateNewWord(testParams.getSteps()));
        objectsInLayout.getButtonNext().setClickable(false);
    }

    private void setDefaultNextParam() {
        testParams.setRightAnswer(letterChange.isLetterColor());
        letterChange.setLetterColor(false);
        testParams.setSteps(testParams.getSteps() + 1);
        if (testParams.getSteps() < testParams.getWordMassive().length) {
            objectsInLayout.getScore().setText(String.format("%d/%d", testParams.getSteps() + 1, testParams.getLengthInScore()));
        }
        objectsInLayout.getMainInformText().setText(testParams.getWordToScreen());
    }

    private void changeActivitySaveParam() {
        Intent intent = new Intent(context, EndGame.class);
        intent.putExtra("rightAnswer", testParams.getWinResult());
        intent.putExtra("arrayRight", testParams.getAnswerArray());
        intent.putExtra("lengthInScore", testParams.getLengthInScore());
        intent.putExtra("wordArray", testParams.getWordMassive());
        // audio.releaseSound();
        context.startActivity(intent);
        ((Activity) context).finish();
    }

    private void rightAnswer() {
        objectsInLayout.getMainInformText().setTextColor(context.getResources().getColor(R.color.DarkSlateGray));
        objectsInLayout.getConstraintLayout().setBackgroundResource(R.drawable.background);
        animationObject.standUp(objectsInLayout.getMainInformText());
        testParams.setWinResult(testParams.getWinResult() + 1);
        if (rightStrike > 0) audio.playSound(audio.getSoundNumber("rightMore"));
        else audio.playSound(audio.getSoundNumber("right"));
        rightStrike++;

        testParams.setAnswerInArray(true, testParams.getSteps() - 1);
    }

    private void wrongAnswer() {
        objectsInLayout.getMainInformText().setTextColor(context.getResources().getColor(R.color.Khaki));
        objectsInLayout.getConstraintLayout().setBackgroundResource(R.drawable.background_red_2);
        animationObject.shake(((Activity) context).findViewById(R.id.text));

        audio.playSound(audio.getSoundNumber("wrong"));
        rightStrike = 0;

        testParams.setAnswerInArray(false, testParams.getSteps() - 1);
    }
}
