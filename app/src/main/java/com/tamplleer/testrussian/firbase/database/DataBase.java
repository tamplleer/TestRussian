package com.tamplleer.testrussian.firbase.database;

import android.util.Log;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DataBase {

    private DatabaseReference myRef;
    ArrayList<String> words;
    String[] allWords;
    Map<Integer, String> wordMap;
    int i;

    public DataBase() {
        myRef = FirebaseDatabase.getInstance().getReference("words");
        wordMap = new HashMap<>();
         load();



    }

    public void load() {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                GenericTypeIndicator<ArrayList<String>> t = new GenericTypeIndicator<ArrayList<String>>() {
                };
                words = dataSnapshot.getValue(t);
                updateUI();
                Log.d("cat", "Value is: " + words.toString());
            }

            private void updateUI() {
                allWords = words.toArray(new String[0]);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("cat", "Failed to read value.", error.toException());
            }
        });
    }

    public void set() {
        myRef.push().setValue("подУшка");
    }//todo delete подУшка

    public String[] get() {
        return allWords;
    }

}
