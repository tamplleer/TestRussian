package com.tamplleer.testrussian.activities.main.components;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.ads.AdView;
import com.tamplleer.testrussian.R;
import com.tamplleer.testrussian.utils.Font;
import com.tamplleer.testrussian.utils.GetScreenSize;

public class ObjectsInLayout {
    private ImageButton ButtonInfo;
    private AdView mAdView;
    private Button startTestButton;
    private ImageButton soundButton;
    private LottieAnimationView lottieAnimationView;
    private Font font;


    private TextView mainInformText, score;
    private Button ButtonMiddle, buttonStartRandom, buttonNext;
    private GetScreenSize getScreenSize;
    private int screenWidth;
    private int screenHeight;
    private ConstraintLayout constraintLayout;

    public ImageButton getButtonInfo() {
        return ButtonInfo;
    }

    public AdView getmAdView() {
        return mAdView;
    }

    public Button getStartTestButton() {
        return startTestButton;
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

    public TextView getMainInformText() {
        return mainInformText;
    }

    public TextView getScore() {
        return score;
    }

    public Button getButtonMiddle() {
        return ButtonMiddle;
    }

    public Button getButtonStartRandom() {
        return buttonStartRandom;
    }

    public Button getButtonNext() {
        return buttonNext;
    }

    public GetScreenSize getGetScreenSize() {
        return getScreenSize;
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
        ButtonInfo = ((Activity) context).findViewById(R.id.vopros);
        startTestButton = ((Activity) context).findViewById(R.id.start);
        soundButton = ((Activity) context).findViewById(R.id.exit);
        lottieAnimationView = ((Activity) context).findViewById(R.id.loadingAnim);


        getScreenSize = new GetScreenSize(context);
        screenWidth = getScreenSize.getScreenWidth();
        screenHeight = getScreenSize.getScreenHeight();
        constraintLayout = ((Activity) context).findViewById(R.id.constrainlayout);

        ButtonMiddle = ((Activity) context).findViewById(R.id.startGA);

        buttonStartRandom = ((Activity) context).findViewById(R.id.start30);

        buttonNext = ((Activity) context).findViewById(R.id.next);
        buttonNext.setVisibility(View.INVISIBLE);
        mainInformText = ((Activity) context).findViewById(R.id.text);
        score = ((Activity) context).findViewById(R.id.points);
        mainInformText.setTextSize(screenWidth / 25);
        mainInformText.setTypeface(font.getFont1());
        score.setTypeface(font.getFont1());
        ButtonMiddle.setTypeface(font.getFont1());
        buttonNext.setTypeface(font.getFont1());
        buttonStartRandom.setTypeface(font.getFont1());
        ButtonMiddle.setVisibility(View.VISIBLE);
        buttonNext.setClickable(false);
    }

    void setClickable(Button button, boolean clickable) {
        button.setClickable(clickable);
    }
}
