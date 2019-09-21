package com.tamplleer.testrussian;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;

class GetScreenSize {
    Context context;
    private DisplayMetrics metrics;

    GetScreenSize(Context context) {
        Display display = ((Activity) context).getWindowManager().getDefaultDisplay();
        metrics = new DisplayMetrics();
        display.getMetrics(metrics);
    }

    int getScreenWidth() {
        return metrics.widthPixels; //screenWidth
    }

    int getScreenHeight() {
        return metrics.heightPixels; //screenHeight
    }

}
