package com.tamplleer.testrussian.utils;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;

public class GetScreenSize {
    private DisplayMetrics metrics;

    public GetScreenSize(Context context) {
        Display display = ((Activity) context).getWindowManager().getDefaultDisplay();
        metrics = new DisplayMetrics();
        display.getMetrics(metrics);
    }

    /**
     *
     * @return screenWidth
     */
    public int getScreenWidth() {
        return metrics.widthPixels;
    }

    /**
     *
     * @return screenHeight
     */
    public int getScreenHeight() {
        return metrics.heightPixels;
    }

}
