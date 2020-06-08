package com.tamplleer.testrussian.firbase.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.tamplleer.testrussian.data.WordDbHelper;
import com.tamplleer.testrussian.data.WordsContract;

import java.util.ArrayList;

public class DataBaseWords {
    private WordDbHelper mDbHelper;


    public DataBaseWords(Context context) {
        mDbHelper = new WordDbHelper(context);

    }

    public ArrayList<String> readFromDataBase(String type) {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        ArrayList<String> list = new ArrayList<>();
        String[] projection = {
                WordsContract.WordsEntry._ID,
                WordsContract.WordsEntry.COLUMN_WORD,
                WordsContract.WordsEntry.COLUMN_LIST_NAME};
        String selection = WordsContract.WordsEntry.COLUMN_LIST_NAME + "=?";
        try (Cursor cursor = db.query(
                WordsContract.WordsEntry.TABLE_NAME,   // таблица
                projection,            // столбцы
                selection,                  // столбцы для условия WHERE
                new String[]{type},                  // значения для условия WHERE
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                null)) {

            while (cursor.moveToNext()) {
                list.add(cursor.getString(cursor.getColumnIndex(WordsContract.WordsEntry.COLUMN_WORD)));
                Log.d("cat", "Data base word : " +
                        cursor.getString(cursor.getColumnIndex(WordsContract.WordsEntry.COLUMN_WORD)));
            }
        }
        return list;
    }

    public void insertWords(String word, String type) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(WordsContract.WordsEntry.COLUMN_WORD, word);
        values.put(WordsContract.WordsEntry.COLUMN_LIST_NAME, type);
        long newRowId = db.insert(WordsContract.WordsEntry.TABLE_NAME, null, values);
    }

    public void insertFromFireBase() {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        DataBase dataBase = new DataBase();
        try {
            Thread.sleep(6000); //Приостанавливает поток на 1 секунду
        } catch (Exception e) {

        }
        for (String s : dataBase.get()) {
            Log.d("cat", "Data base firebase : " + s);
            values.put(WordsContract.WordsEntry.COLUMN_WORD, s);
            values.put(WordsContract.WordsEntry.COLUMN_LIST_NAME, "main");
            db.insert(WordsContract.WordsEntry.TABLE_NAME, null, values);
        }

    }
    //todo delete update, update table first time
}
