package com.tamplleer.testrussian;

import android.view.View;

public class RandomWord {
    public static final int RUNDOMARRAY = 30;
    private String[] word1;
    private int rundom = 0;

    public void rundomWord() {

    }
    public void createArray(){
        word1 = S.allWord.split(",");
        String[] wordMassive = new String[30];
        S.wordMassive = new String[30];
        String[] wordTmprArray = word1;
        rundom = 0;
        S.vernoORno = new boolean[30];
        S.lengsInScore = 30;

        for (int i = 0; i < RUNDOMARRAY; i++) {
            rundom = (int) (Math.random() * word1.length);
            while (wordTmprArray[rundom].equals("0"))
                rundom = (int) (Math.random() * word1.length);
            S.wordMassive[i] = wordTmprArray[rundom];
            wordTmprArray[rundom] = "0";
        }
    }
}

