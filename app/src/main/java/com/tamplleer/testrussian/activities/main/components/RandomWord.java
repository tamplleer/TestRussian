package com.tamplleer.testrussian.activities.main.components;

import com.tamplleer.testrussian.S;
import com.tamplleer.testrussian.Statistic;

public class RandomWord {
    private static final int RANDOM_ARRAY = 30;
    private String[] word1;
    private int random = 0;



    /**
     *
     */
    public void createArrayRandWords(Statistic statistic){
        statistic.setLengthInScore(30);
        statistic.setAnswerArray(new boolean[30]);


        word1 = S.allWord.split(",");
        String[] wordMassive = new String[30];
        S.wordMassive = new String[30];
        String[] wordTmprArray = word1;
        random = 0;
        S.vernoORno = new boolean[30];
        S.lengsInScore = 30;

        for (int i = 0; i < RANDOM_ARRAY; i++) {
            random = (int) (Math.random() * word1.length);
            while (wordTmprArray[random].equals("0"))
                random = (int) (Math.random() * word1.length);
            S.wordMassive[i] = wordTmprArray[random];
            wordTmprArray[random] = "0";
        }
        statistic.setWordMassive(wordTmprArray);
    }
}

