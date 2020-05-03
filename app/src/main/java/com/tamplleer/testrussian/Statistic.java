package com.tamplleer.testrussian;

public class Statistic {
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

    private int lengthInScore; //need to determine which audio play
    private int steps = 0;
    private  String[] wordMassive;
    private int winResult;
    private boolean[] answerArray;
    Statistic(){

    }

}
