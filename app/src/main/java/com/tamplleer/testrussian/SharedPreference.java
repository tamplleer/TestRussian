package com.tamplleer.testrussian;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreference {
    private SharedPreferences sharedPreferences;
    private final static String APP_PREFERENCES1 = "mySettings";
    private final static String APP_PREFERENCES_SILENCE = "silence";
    private final static String APP_PREFERENCES_ADD = "ads";
    private final static String APP_PREFERENCE_FIRST_BOOT="firstBoot";
    public SharedPreference(Context context) {
        sharedPreferences = context.getSharedPreferences(APP_PREFERENCES1, Context.MODE_PRIVATE);
    }

    public void save(final int volume) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(APP_PREFERENCES_SILENCE, volume);
        editor.putBoolean(APP_PREFERENCES_ADD, getAdd());
        editor.apply();
    }
    public boolean getFirstBoot(){
        if (sharedPreferences.contains(APP_PREFERENCE_FIRST_BOOT)) {
            return sharedPreferences.getBoolean(APP_PREFERENCE_FIRST_BOOT, false);
        }
        return false;
    }
    public void setFirstBoot(){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(APP_PREFERENCE_FIRST_BOOT, true);
        editor.putBoolean(APP_PREFERENCES_ADD, true);
        editor.apply();
    }

    public int getVolume() {
        if (sharedPreferences.contains(APP_PREFERENCES_SILENCE)) {
            return sharedPreferences.getInt(APP_PREFERENCES_SILENCE, 0);
        }
        return 0;

    }

    public boolean getAdd() {
        if (sharedPreferences.contains(APP_PREFERENCES_ADD)) {
            return sharedPreferences.getBoolean(APP_PREFERENCES_ADD, true);
        }
        return true;
    }
    public void saveEndGame(boolean isShowAdvertisement){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(APP_PREFERENCES_ADD, isShowAdvertisement);
        editor.apply();
    }
}
