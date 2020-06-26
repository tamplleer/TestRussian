package com.tamplleer.testrussian;

import com.google.firebase.database.annotations.NotNull;

public class TestParam {
    public int getLengthInScore() {
        return lengthInScore;
    }

    public void setLengthInScore(@NotNull int lengthInScore) {
        this.lengthInScore = lengthInScore;
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }

    public String[] getWordMassive() {
        return wordMassive;
    }

    public String getWordFromArray(int number) {
        return wordMassive[number];
    }

    public void setWordMassive(@NotNull String[] wordMassive) {
        this.wordMassive = wordMassive;
    }


    public int getWinResult() {
        return winResult;
    }

    public void setWinResult(int winResult) {
        this.winResult = winResult;
    }

    public boolean[] getAnswerArray() {
        return answerArray;
    }

    public void setAnswerArray(@NotNull boolean[] answerArray) {
        this.answerArray = answerArray;
    }

    public void setAnswerInArray(boolean answer, int number) {
        answerArray[number] = answer;
    }

    private int lengthInScore; //need to determine which audio play
    private int steps = 0;
    private String[] wordMassive;
    private int winResult;
    private boolean[] answerArray;
    private boolean rightAnswer;
    private String wordToScreen;

    public String getWordToScreen() {
        return wordToScreen;
    }

    public void setWordToScreen(String wordToScreen) {
        this.wordToScreen = wordToScreen;
    }

    public boolean isRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(boolean rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public TestParam() {

    }

}
