package com.tamplleer.testrussian.data.firbase;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.tamplleer.testrussian.EducationActivity;
import com.tamplleer.testrussian.SharedPreference;
import com.tamplleer.testrussian.data.DataBaseWords;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DataFireBase {

    private DatabaseReference myRef;
    private DataBaseWords dataBaseWords;
    ArrayList<String> words;
    String[] allWords;
    Map<Integer, String> wordMap;
    int i;


    public DataFireBase(Context context) {
        myRef = FirebaseDatabase.getInstance().getReference("words");
        wordMap = new HashMap<>();
        dataBaseWords = new DataBaseWords(context);
        // load();

    }

    public void load(Context context, SharedPreference sharedPreference) {
        if (!isOnline(context)){
            Toast.makeText(context, "Отсутствует подключение к интернету",
                    Toast.LENGTH_LONG).show();
        }
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                GenericTypeIndicator<ArrayList<String>> t = new GenericTypeIndicator<ArrayList<String>>() {
                };
                words = dataSnapshot.getValue(t);
                dataBaseWords.insertFromFireBase(words.toArray(new String[0]));
                Log.d("cat", "Value is: " + words.toString());
                startEducationActivity();
            }

            private void startEducationActivity() {
                sharedPreference.setFirstBoot();
                Intent intent = new Intent(context, EducationActivity.class);
                context.startActivity(intent);
                ((Activity) context).finish();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.d("cat", "Failed to read value.", error.toException());
            }
        });
    }

    public void set() {
        myRef.push().setValue("подУшка");
    }//todo delete подУшка

    public String[] get() {
        return allWords;
    }

    protected boolean isOnline(Context context) {
        String cs = Context.CONNECTIVITY_SERVICE;
        ConnectivityManager cm = (ConnectivityManager)
                context.getSystemService(cs);
        if (cm.getActiveNetworkInfo() == null) {
            return false;
        } else {
            return true;
        }
    }

}
