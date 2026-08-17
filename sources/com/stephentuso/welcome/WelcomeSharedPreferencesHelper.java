package com.stephentuso.welcome;

import android.content.Context;
import android.content.SharedPreferences;

/* loaded from: classes.dex */
public class WelcomeSharedPreferencesHelper {
    private static final String KEY_HAS_RUN = "welcome_screen_has_run";
    private static final String KEY_SHARED_PREFS = "com.stephentuso.welcome";

    private static boolean getCompletedFromPreferences(SharedPreferences sharedPreferences, String str) {
        return sharedPreferences.getBoolean(getKey(str), false);
    }

    private static String getKey(String str) {
        return KEY_HAS_RUN + str;
    }

    private static SharedPreferences getSharedPrefs(Context context) {
        return context.getSharedPreferences("com.stephentuso.welcome", 0);
    }

    public static void removeWelcomeCompleted(Context context, String str) {
        getSharedPrefs(context).edit().remove(getKey(str)).commit();
    }

    public static void storeWelcomeCompleted(Context context, String str) {
        getSharedPrefs(context).edit().putBoolean(getKey(str), true).commit();
    }

    public static boolean welcomeScreenCompleted(Context context, String str) {
        return getCompletedFromPreferences(getSharedPrefs(context), str);
    }
}
