package com.tamplleer.testrussian;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by tampl on 12.03.2018.
 */

public class AppRater {
    private final static int DAYS_UNTIL_PROMPT = 4;
    private final static int LAUNCHES_UNTIL_PROMPT = 4;

    AppRater(){

    }



    public static void showRateDialog(final Context context,
                                      final SharedPreferences.Editor editor) {
        final Dialog dialog = new Dialog(context);
        dialog.setTitle("Оценка: Ударения ");

        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setBackgroundResource(R.drawable.ratefone);

        TextView tvInfo = new TextView(context);
        tvInfo.setText("         Знаете что произошло?\n\n Наше приложение установлено уже несколько дней. Вы бы очень помогли оценив его на Google play.\n    Заранее большое спасибо!");
        tvInfo.setWidth(240);
        tvInfo.setTextSize(18);
        tvInfo.setTextColor(Color.BLACK);

        tvInfo.setPadding(40, 0, 1, 0);
        linearLayout.addView(tvInfo);

        Button rateButton = new Button(context);
        rateButton.setText("Оценить Ударения");
        rateButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri
                        .parse("https://play.google.com/store/apps/details?id=com.tamplleer.testrussian")));
                dialog.dismiss();
                if (editor != null) {
                    editor.putBoolean("dontshowagain", true);
                    editor.commit();
                }
            }
        });
        linearLayout.addView(rateButton);

        Button remindLaterButton = new Button(context);
        remindLaterButton.setText("Напомнить позже");
        remindLaterButton.setTextColor(Color.rgb(47, 79, 79));

        remindLaterButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (editor != null) {
                    editor.putLong("launch_count", 0);
                    editor.putLong("date_firstlaunch", 0);
                    editor.commit();
                }
                dialog.dismiss();
            }
        });
        linearLayout.addView(remindLaterButton);

        Button noButton = new Button(context);
        noButton.setText("Спасибо, не надо");
        noButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (editor != null) {
                    editor.putBoolean("dontshowagain", true);
                    editor.commit();
                }
                dialog.dismiss();
            }
        });
        linearLayout.addView(noButton);

        dialog.setContentView(linearLayout);
        dialog.show();
    }
    public static void app_launched(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("apprater", 0);
        if (prefs.getBoolean("dontshowagain", false)) {
            return;
        }

        SharedPreferences.Editor editor = prefs.edit();

        long launch_count = prefs.getLong("launch_count", 0) + 1;
        editor.putLong("launch_count", launch_count);

        Long date_firstLaunch = prefs.getLong("date_firstlaunch", 0);
        if (date_firstLaunch == 0) {
            date_firstLaunch = System.currentTimeMillis();
            editor.putLong("date_firstlaunch", date_firstLaunch);
        }

        if (launch_count >= LAUNCHES_UNTIL_PROMPT) {
            if (System.currentTimeMillis() >= date_firstLaunch
                    + (DAYS_UNTIL_PROMPT * 24 * 60 * 60 * 1000)) {
                showRateDialog(context, editor);
            }
        }
        editor.commit();
    }


}
