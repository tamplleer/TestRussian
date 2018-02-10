package com.tamplleer.testrussian;


  import android.content.Intent;
          import android.os.Handler;
          import android.support.v7.app.AppCompatActivity;
          import android.os.Bundle;
          import android.view.Window;
          import android.view.WindowManager;

public class SpleshScreenactivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 3000;
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
          setContentView(R.layout.activity_splesh_screenactivity);
        handler();

    }
    private void handler(){
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent intent = new Intent(SpleshScreenactivity.this,
                        MainActivity.class);
                startActivity(intent);

                finish();
            }
        }, SPLASH_TIME_OUT);
    }

}