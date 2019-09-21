package com.tamplleer.testrussian;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import static java.security.AccessController.getContext;

public class GetScreenSize {
    Context context;
    Display display;
    DisplayMetrics metrics;
    int screenWidth = 0;
    int screenHeight = 0;

    GetScreenSize(Context context) {
        display = ((Activity) context).getWindowManager().getDefaultDisplay();
        metrics = new DisplayMetrics();
        display.getMetrics(metrics);
    }

    public int getScreenWidth() {
        return metrics.widthPixels;
    }

    public int getScreenHeight() {
        return metrics.heightPixels;
    }

}
