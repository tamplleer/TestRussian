package com.tamplleer.testrussian;

import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView t, point;
    char strToArray[];
    int screenWidth, screenHeight;
    int lengthINscore = 0;
    ConstraintLayout constraintLayout;
   static char a[] = {'А', 'И', 'Е', 'Ё', 'О', 'У', 'Ы', 'Э', 'Ю', 'Я'};
    String word1[];
    int steps = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        word1 = S.allWord.split(",");
        t = (TextView) findViewById(R.id.text);
        point = (TextView) findViewById(R.id.points);
        constraintLayout = (ConstraintLayout) findViewById(R.id.constrainlayout);
        getScreenSize();


    }

    protected void getScreenSize() {
        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        screenWidth = metrics.widthPixels;
        screenHeight = metrics.heightPixels;
    }


    public void exit(View view) {
        System.exit(0);
    }

    public void start(View view) {
        S.click = true;
        S.someClick = false;
        S.win = 0;
        steps=0;
        lengthINscore = word1.length;
        word1 = S.allWord.split(",");
        point.setText(S.win + "/" + lengthINscore);
        S.changeWord = 1;
        setWordinMas();
        constraintLayout.setBackgroundColor(ContextCompat
                .getColor(this, R.color.Khaki));
        t.setText("");
    }

    public void someword(View view) {
        S.someClick = true;
        S.click = false;
        S.win = 0;
        steps=0;
        int maxLength = 30;
        int rundom = 0;
        int banWord[] = new int[maxLength];
        lengthINscore = maxLength;
        for (int i = 1; i < maxLength; i++) {
            rundom = (int) (Math.random() * word1.length);
            for (int j = 1; j < maxLength; j++) {
                if (banWord[j] == rundom) rundom = (int) (Math.random() * word1.length);
            }

            word1[i] = word1[rundom];
            banWord[i] = rundom;
        }

        point.setText(S.win + "/" + lengthINscore);
        S.changeWord = 1;
        setWordinMas();
        constraintLayout.setBackgroundColor(ContextCompat
                .getColor(this, R.color.Khaki));
        t.setText("");

    }

    public void next(View view) {

        if (S.click == true || S.someClick == true) {
            steps++;
            S.changeWord++;
            constraintLayout.setBackgroundColor(ContextCompat
                    .getColor(this, R.color.Khaki));
            if (S.right == true) {
                point.setText(S.win + "/" + lengthINscore);
            } else {
                constraintLayout.setBackgroundColor(ContextCompat
                        .getColor(this, R.color.FireBrick));
            }
            t.setText("" + S.wordtoscreen);
            setWordinMas();
            if (steps == lengthINscore) {
                t.setText("GAME OVEr  " + S.win + "  OF  " + lengthINscore + "    Try again");
                S.click = false;
                S.someClick = false;
            }
        }
    }

    public void setWordinMas() {
        String word[] = new String[word1.length];
        for (int i = 1; i < word.length; i++) {
            word[i] = word1[i];
            if (S.changeWord == i) {
                S.stringTOchar = word[i];
                S.wordtoscreen = word[i];
                createWord();
            }
        }

    }

    public void rextV(View view) {
        t.setText("!!!!!!!!!!!!");
    }

    public void createWord() {
        strToArray = S.stringTOchar.toCharArray();
        char strArray00[] = new char[strToArray.length];

        for (int i = 0; i < strToArray.length; i++) {
            if (strToArray[i] == a[0] ||
                    strToArray[i] == a[1] ||
                    strToArray[i] == a[2] ||
                    strToArray[i] == a[3] ||
                    strToArray[i] == a[4] ||
                    strToArray[i] == a[5] ||
                    strToArray[i] == a[6] ||
                    strToArray[i] == a[7] ||
                    strToArray[i] == a[8] ||
                    strToArray[i] == a[9]) S.delet = i;
            strArray00[i] = ' ';
            if (Character.isLowerCase(strToArray[i]))
                strToArray[i] = Character.toUpperCase(strToArray[i]);
        }
        Text text[] = new Text[strToArray.length + 1];
        // strToArray[999]=' ';
        //text[strToArray.length+1]= new Text(this,30,30,strToArray[999],30,999);

        int x1 = screenWidth / strToArray.length;
        int y = screenHeight / 2;
        int x = 0;
        for (int i = 0; i < strToArray.length; i++) {

            x = x1 * i + 60;
            if (i == 0) x = 60;

            text[i] = new Text(this, x, y, strToArray[i], 30, i);
        }

        for (int i = 0; i < strToArray.length; i++) {
            strToArray[i] = strArray00[i];

        }
    }
}
