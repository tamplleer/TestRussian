package com.tamplleer.testrussian.activities.splesh;


  import android.content.Intent;
  import android.os.Bundle;

  import androidx.appcompat.app.AppCompatActivity;

  import com.tamplleer.testrussian.activities.main.MainActivity;

public class SpleshScreenActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

                Intent intent = new Intent(SpleshScreenActivity.this,
                        MainActivity.class);
                startActivity(intent);
                finish();

    }

}