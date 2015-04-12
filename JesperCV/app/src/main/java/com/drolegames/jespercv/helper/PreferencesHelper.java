package com.drolegames.jespercv.helper;

import android.content.Context;
import android.content.SharedPreferences;

import com.drolegames.jespercv.activities.MainActivity;

/**
 * Created by Jesper Turesson on 2015-01-23.
 */
public class PreferencesHelper {
    public static final String PREF_FILE_NAME = "testpref";

    public static void saveToPreferences(Context context, String preferenceName, String preferenceValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(preferenceName, preferenceValue);
        if (MainActivity.SDK_VERSION > 8) {
            editor.apply();
        } else {
            editor.commit();
        }
    }


    public static String readFromPreferences(Context context, String preferenceName, String defaultValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(preferenceName, defaultValue);
    }
}
