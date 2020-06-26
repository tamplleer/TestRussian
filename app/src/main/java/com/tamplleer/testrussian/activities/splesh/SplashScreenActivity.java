package com.tamplleer.testrussian.activities.splesh;


import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.tamplleer.testrussian.SharedPreference;
import com.tamplleer.testrussian.activities.main.MainActivity;
import com.tamplleer.testrussian.data.firbase.DataFireBase;

public class SplashScreenActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startFirstBootOrDefault();

    }

    private void startFirstBootOrDefault() {
        SharedPreference sharedPreference = new SharedPreference(this);
        if (!sharedPreference.getFirstBoot() ) {
            DataFireBase dataBase = new DataFireBase(this);
            dataBase.load(this,sharedPreference);

        } else {
            Intent intent = new Intent(SplashScreenActivity.this,
                    MainActivity.class);
            startActivity(intent);
            finish();
        }
    }


}