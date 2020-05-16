package com.tamplleer.testrussian.activities.main.components;

import com.tamplleer.testrussian.S;

public class RandomWord {
    private static final int RANDOM_ARRAY = 30;


    /**
     *
     */
    public String[] createArrayRandWords(){
        String[] wordMassive = new String[RANDOM_ARRAY];
        String[] wordTmprArray =S.allWord.split(",");
        int random = 0;

        for (int i = 0; i < RANDOM_ARRAY; i++) {
            random = (int) (Math.random() * wordTmprArray.length);
            while (wordTmprArray[random].equals("0"))
                random = (int) (Math.random() * wordTmprArray.length);
            wordMassive[i] = wordTmprArray[random];
            wordTmprArray[random] = "0";
        }
       return wordMassive;
    }
}

