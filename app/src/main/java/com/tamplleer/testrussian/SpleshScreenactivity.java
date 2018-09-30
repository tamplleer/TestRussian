package com.tamplleer.testrussian;


  import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import android.view.Window;
import android.view.WindowManager;

public class SpleshScreenactivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

                Intent intent = new Intent(SpleshScreenactivity.this,
                        MainActivity.class);
                startActivity(intent);
                finish();

    }

}