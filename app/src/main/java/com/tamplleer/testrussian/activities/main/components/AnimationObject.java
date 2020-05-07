package com.tamplleer.testrussian.activities.main.components;

import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.tamplleer.testrussian.R;

public class AnimationObject {
    public AnimationObject() {

    }

    public void bounce(Button button) {
        YoYo.with(Techniques.Bounce)
                .duration(1000)
                .repeat(0)
                .playOn(button);

    }
    public void bounce(ImageButton button) {
        YoYo.with(Techniques.Bounce)
                .duration(1000)
                .repeat(0)
                .playOn(button);

    }
    public void bounceInUp(Button button) {
        YoYo.with(Techniques.BounceInUp)
                .duration(500)
                .repeat(0)
                .playOn(button);

    }
    public void standUp(TextView textView) {
        YoYo.with(Techniques.StandUp)
                .duration(700)
                .repeat(0)
                .playOn(textView);

    }
    public void shake(TextView textView) {
        YoYo.with(Techniques.Shake)
                .duration(700)
                .repeat(0)
                .playOn(textView);

    }
}
