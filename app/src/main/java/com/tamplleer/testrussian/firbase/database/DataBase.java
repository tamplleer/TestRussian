package com.tamplleer.testrussian.firbase.database;

import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class DataBase {

   private DatabaseReference myRef;
    ArrayList<String> words;
    String[] allWords;
    Map<Integer, String> wordMap;
    ObjectMapper mapper;
    public DataBase() {
        myRef = FirebaseDatabase.getInstance().getReference("words");
/*
        mapper = new ObjectMapper();
        wordMap = new HashMap<>();
        try {
            wordMap = mapper.readValue(new File(".//allwords.json"), Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        wordMap.forEach((k,v)->{
            int i=0;
            allWords[0][i]=v;
            i++;
        });
*/

// Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                GenericTypeIndicator<ArrayList<String>>t= new GenericTypeIndicator<ArrayList<String>>() {};
               words = dataSnapshot.getValue(t);
               updateUI();
                Log.d("cat", "Value is: " + words.toString());
            }
private void updateUI(){
    allWords= words.toArray(new String[0]);
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
    }

    public String[] get() {
        return allWords;
    }

}
