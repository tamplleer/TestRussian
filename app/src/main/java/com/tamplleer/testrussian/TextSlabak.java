package com.tamplleer.testrussian;

import android.content.Context;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by tampl on 13.02.2018.
 */

public class TextSlabak extends endGame {

    TextView t;

    Context context;
    int x, y;
    boolean type=false;
    String count;
    int shir;
    ConstraintLayout constraintLayout;

    //MainActivity main;
    float alpha = 1;
    double  size; // размер картинки, врещение, прозрачность
    TextSlabak(endGame endGame, int x, int y,  String count, int size, final boolean type) {
        //  main= new MainActivity();
        // if (activity==0)
        this.x = x;
        context = endGame;
        this.y = y;
        this.size = size;
        this.type = type;
        this.count = count;
        S.charTextV = new TextView(context);
        S.charTextV.setAlpha(alpha);
        S.charTextV.setX(x); // координаты
        S.charTextV.setY(y);
       // S.charTextV.setBackgroundResource(R.drawable.wordshape);
        if (type==true)S.charTextV.setTextColor(Color.rgb(50,205, 50));

        else S.charTextV.setTextColor(Color.rgb(255, 0, 0));
       // S.charTextV.setScaleX(2f); // изменение размеров в 1.5 раза
      //  S.charTextV.setScaleY(2f);
        S.charTextV.setText("" + count);


        shir=size*8;
        endGame.addContentView(S.charTextV, new RelativeLayout.LayoutParams(shir , size));
  }

    public void setSize(){
        if (screenWidth>10 || screenHeight>1280){
            size=size*2;
            shir=shir-20;
        }

    }






}

