package com.tamplleer.testrussian.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class WordDbHelper extends SQLiteOpenHelper {
    public static final String LOG_TAG = SQLiteOpenHelper.class.getSimpleName();
    private static final String DATABASE_NAME = "words.db";
    private static final int DATABASE_VERSION = 1;

    public WordDbHelper(@Nullable Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    /**
     * {@link WordDbHelper}.
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_WORDS_TABLE = "CREATE TABLE " + WordsContract.WordsEntry.TABLE_NAME + " ("
                + WordsContract.WordsEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + WordsContract.WordsEntry.COLUMN_WORD+ " TEXT NOT NULL, "
                + WordsContract.WordsEntry.COLUMN_LIST_NAME + " TEXT NOT NULL); ";

        // Запускаем создание таблицы
        db.execSQL(SQL_CREATE_WORDS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
