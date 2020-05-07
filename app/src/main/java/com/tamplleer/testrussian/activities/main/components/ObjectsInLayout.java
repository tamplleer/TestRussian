package com.tamplleer.testrussian.activities.main.components;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.ads.AdView;
import com.tamplleer.testrussian.R;
import com.tamplleer.testrussian.utils.Font;
import com.tamplleer.testrussian.utils.GetScreenSize;

public class ObjectsInLayout {
    private ImageButton bminfo;
    private AdView mAdView;
    private Button start;
    private ImageButton soundButton;
    private LottieAnimationView lottieAnimationView;
    private Font font;


    private TextView t, score;
    private Button middleButton, bmstart30, bmnext;
    private GetScreenSize getScreenSize;
    private ImageView picture;
    private int screenWidth;
    private int screenHeight;
    private ConstraintLayout constraintLayout;

    public ImageButton getBminfo() {
        return bminfo;
    }

    public AdView getmAdView() {
        return mAdView;
    }

    public Button getStart() {
        return start;
    }

    public ImageButton getSoundButton() {
        return soundButton;
    }

    public LottieAnimationView getLottieAnimationView() {
        return lottieAnimationView;
    }

    public Font getFont() {
        return font;
    }

    public TextView getT() {
        return t;
    }

    public TextView getScore() {
        return score;
    }

    public Button getMiddleButton() {
        return middleButton;
    }

    public Button getBmstart30() {
        return bmstart30;
    }

    public Button getBmnext() {
        return bmnext;
    }

    public GetScreenSize getGetScreenSize() {
        return getScreenSize;
    }

    public ImageView getPicture() {
        return picture;
    }

    public ConstraintLayout getConstraintLayout() {
        return constraintLayout;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public ObjectsInLayout(Context context) {
        font = new Font(context);
        bminfo = ((Activity) context).findViewById(R.id.vopros);
        start = ((Activity) context).findViewById(R.id.start);
        soundButton = ((Activity) context).findViewById(R.id.exit);
        lottieAnimationView = ((Activity) context).findViewById(R.id.loadingAnim);


        getScreenSize = new GetScreenSize(context);
        screenWidth = getScreenSize.getScreenWidth();
        screenHeight = getScreenSize.getScreenHeight();
        constraintLayout = ((Activity) context).findViewById(R.id.constrainlayout);
        picture = ((Activity) context).findViewById(R.id.imageView);
        picture.setX(0);
        picture.setY(0);
        middleButton = ((Activity) context).findViewById(R.id.startGA);

        bmstart30 = ((Activity) context).findViewById(R.id.start30);

        bmnext = ((Activity) context).findViewById(R.id.next);
        bmnext.setVisibility(View.INVISIBLE);
        t = ((Activity) context).findViewById(R.id.text);
        score = ((Activity) context).findViewById(R.id.points);
        t.setTextSize(screenWidth / 25);
        t.setTypeface(font.getFont1());
        score.setTypeface(font.getFont1());
        middleButton.setTypeface(font.getFont1());
        bmnext.setTypeface(font.getFont1());
        bmstart30.setTypeface(font.getFont1());
        middleButton.setVisibility(View.VISIBLE);
        bmnext.setClickable(false);
    }

    void setClickable(Button button, boolean clickable) {
        button.setClickable(clickable);
    }
}
