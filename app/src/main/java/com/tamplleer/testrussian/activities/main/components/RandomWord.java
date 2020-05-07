package com.tamplleer.testrussian.activities.main.components;

import com.tamplleer.testrussian.S;
import com.tamplleer.testrussian.TestParam;

public class RandomWord {
    private static final int RANDOM_ARRAY = 30;
    private String[] word1;
    private int random = 0;



    /**
     *
     */
    public String[] createArrayRandWords(){



        word1 = S.allWord.split(",");
        String[] wordMassive = new String[RANDOM_ARRAY];

        String[] wordTmprArray = word1;
        random = 0;

        for (int i = 0; i < RANDOM_ARRAY; i++) {
            random = (int) (Math.random() * word1.length);
            while (wordTmprArray[random].equals("0"))
                random = (int) (Math.random() * word1.length);
            wordMassive[i] = wordTmprArray[random];
            wordTmprArray[random] = "0";
        }
       return wordMassive;
    }
}

