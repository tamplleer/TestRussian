package com.tamplleer.testrussian.data;

import android.provider.BaseColumns;

public class WordsContract {

    public static class WordsEntry implements BaseColumns {
        public final static String TABLE_NAME = "words";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_WORD = "word";
        public final static String COLUMN_LIST_NAME = "list";


        public static final int GENDER_FEMALE = 0;
        public static final int GENDER_MALE = 1;
        public static final int GENDER_UNKNOWN = 2;
    }
}
