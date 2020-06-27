package com.tamplleer.testrussian.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.util.Pair;

import java.util.ArrayList;

public class DataBaseWords {
    private WordDbHelper mDbHelper;
    String word;
    Boolean enable;
    SQLiteDatabase db;
    String[] projection = {
            WordsContract.WordsEntry._ID,
            WordsContract.WordsEntry.COLUMN_WORD,
            WordsContract.WordsEntry.COLUMN_LIST_NAME,
            WordsContract.WordsEntry.COLUMN_LIST_ENABLE};
    Cursor cursor;
    private ArrayList<String> typesList = new ArrayList<>();


    public DataBaseWords(Context context) {
        mDbHelper = new WordDbHelper(context);
        db = mDbHelper.getReadableDatabase();
        ArrayList<String> list = new ArrayList<>();


    }

    public ArrayList<String> readFromDataBase(String type) {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        ArrayList<String> list = new ArrayList<>();
        String[] projection = {
                WordsContract.WordsEntry._ID,
                WordsContract.WordsEntry.COLUMN_WORD,
                WordsContract.WordsEntry.COLUMN_LIST_NAME,
                WordsContract.WordsEntry.COLUMN_LIST_ENABLE};
        String selection = WordsContract.WordsEntry.COLUMN_LIST_NAME + "=?";
        String enable = WordsContract.WordsEntry.COLUMN_LIST_ENABLE + "=?";
        String columnWhere = selection + " AND " + enable;

        try (Cursor cursor = db.query(
                WordsContract.WordsEntry.TABLE_NAME,   // таблица
                projection,            // столбцы
                columnWhere,                  // столбцы для условия WHERE
                new String[]{type, String.valueOf(1)},                  // значения для условия WHERE
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

    public void startRead(String type, boolean isAllWordInDataBase) {
        String selection = null;
        String[] whereTerms = null;
        if (!isAllWordInDataBase) {
            selection = WordsContract.WordsEntry.COLUMN_LIST_NAME + "=?";
            whereTerms = new String[]{type};
        }

        cursor = db.query(
                WordsContract.WordsEntry.TABLE_NAME,   // таблица
                projection,            // столбцы
                selection,                  // столбцы для условия WHERE
                whereTerms,                  // значения для условия WHERE
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                null);
    }

    public boolean isFinishDataBase() {
        return cursor.moveToNext();
    }

    public void closeDataBase() {
        cursor.close();
    }

    public Pair<String, Boolean> readFromDataBaseWord() {
        word = cursor.getString(cursor.getColumnIndex(WordsContract.WordsEntry.COLUMN_WORD));
        enable = cursor.getInt(cursor.getColumnIndex(WordsContract.WordsEntry.COLUMN_LIST_ENABLE)) > 0;

        return new Pair<>(word, enable);
    }

    public String readFromDataBaseAllWord() {
        return cursor.getString(cursor.getColumnIndex(WordsContract.WordsEntry.COLUMN_LIST_NAME));
    }

    public void insertWords(String word, String type) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(WordsContract.WordsEntry.COLUMN_WORD, word);
        values.put(WordsContract.WordsEntry.COLUMN_LIST_NAME, type);
        long newRowId = db.insert(WordsContract.WordsEntry.TABLE_NAME, null, values);
    }


    public void insertFromFireBase(String[] fireBaseData) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        if (fireBaseData != null) {
            for (String s : fireBaseData) {
                Log.d("cat", "Data base firebase : " + s);
                values.put(WordsContract.WordsEntry.COLUMN_WORD, s);
                values.put(WordsContract.WordsEntry.COLUMN_LIST_NAME, "main");
                db.insert(WordsContract.WordsEntry.TABLE_NAME, null, values);
            }


        }
    }

    public void updateEnable(String word, String type, Boolean e) {
        ContentValues values = new ContentValues();
        values.put(WordsContract.WordsEntry.COLUMN_LIST_ENABLE, e);
        String where = WordsContract.WordsEntry.COLUMN_WORD + " =? AND " + WordsContract.WordsEntry.COLUMN_LIST_NAME + " =? ";
        db.update(WordsContract.WordsEntry.TABLE_NAME,
                values,
                where,
                new String[]{word, type});
    }

    public void updateWord(String word, String type, String wordBefore) {
        ContentValues values = new ContentValues();
        values.put(WordsContract.WordsEntry.COLUMN_WORD, word);
        String where = WordsContract.WordsEntry.COLUMN_WORD + " =? AND " + WordsContract.WordsEntry.COLUMN_LIST_NAME + " =? ";
        db.update(WordsContract.WordsEntry.TABLE_NAME,
                values,
                where,
                new String[]{wordBefore, type});
    }

    public void deleteElem(String word, String type) {
        String values = WordsContract.WordsEntry.COLUMN_WORD +
                " =? AND " +
                WordsContract.WordsEntry.COLUMN_LIST_NAME +
                "=?";
        db.delete(WordsContract.WordsEntry.TABLE_NAME, values, new String[]{word, type});
    }

    public ArrayList<String> findAllTypes() {
        typesList.clear();
        boolean coincidence;
        startRead(null, true);
        String type = "";
        while (isFinishDataBase()) {
            coincidence = false;
            type = readFromDataBaseAllWord();
            if (typesList.size() == 0) {
                typesList.add(type);
            } else {
                for (String t : typesList) {
                    if (type.equals(t)) {
                        coincidence = true;
                        break;
                    }
                }
                if (!coincidence) typesList.add(type);
            }


        }
        closeDataBase();
        return typesList;
    }


}
