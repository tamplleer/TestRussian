package com.tamplleer.testrussian;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;

public class Font {
    private Typeface font1 = null;
    Context context;

    Font(Context context) {
        this.context = context;


    }

    public Typeface getFont1() {
        try {
            font1 = Typeface.createFromAsset(((Activity) context).getAssets(), "font12.ttf");
        } catch (Exception e) {
            Log.e("cat", "Could not get typeface: " + e.getMessage());

        }
        return font1;
    }

}
