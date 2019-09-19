package com.tamplleer.testrussian;


  import android.content.Intent;
  import android.os.Bundle;

  import androidx.appcompat.app.AppCompatActivity;

public class SpleshScreenactivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

                Intent intent = new Intent(SpleshScreenactivity.this,
                        MainActivity.class);
                startActivity(intent);
                finish();

    }

}