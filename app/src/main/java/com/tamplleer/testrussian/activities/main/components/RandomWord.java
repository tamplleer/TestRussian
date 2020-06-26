package com.tamplleer.testrussian.activities.main.components;

import com.google.firebase.database.annotations.NotNull;

public class RandomWord {
    private static final int RANDOM_ARRAY = 30;


    /**
     *
     */
    public String[] createArrayRandWords(@NotNull String[] allWords){
        String[] wordMassive = new String[RANDOM_ARRAY];
        int random = 0;

        for (int i = 0; i < RANDOM_ARRAY; i++) {
            random = (int) (Math.random() * allWords.length);
            while (allWords[random].equals("0"))
                random = (int) (Math.random() * allWords.length);
            wordMassive[i] = allWords[random];
            allWords[random] = "0";
        }
       return wordMassive;
    }
}

