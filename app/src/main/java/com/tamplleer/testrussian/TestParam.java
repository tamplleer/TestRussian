package com.tamplleer.testrussian;

public class TestParam {
    public int getLengthInScore() {
        return lengthInScore;
    }

    public void setLengthInScore(int lengthInScore) {
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

    public void setWordMassive(String[] wordMassive) {
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

    public void setAnswerArray(boolean[] answerArray) {
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
    private int selectWordNumber;
    private boolean answerRight;
    private String wordToScreen;

    public String getWordToScreen() {
        return wordToScreen;
    }

    public void setWordToScreen(String wordToScreen) {
        this.wordToScreen = wordToScreen;
    }

    public boolean isAnswerRight() {
        return answerRight;
    }

    public void setAnswerRight(boolean answerRight) {
        this.answerRight = answerRight;
    }

    public int getSelectWordNumber() {
        return selectWordNumber;
    }

    public void setSelectWordNumber(int selectWordNumber) {
        this.selectWordNumber = selectWordNumber;
    }

    public TestParam() {

    }

}
