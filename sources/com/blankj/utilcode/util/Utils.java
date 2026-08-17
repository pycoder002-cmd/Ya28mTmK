package com.blankj.utilcode.util;

import android.annotation.SuppressLint;
import android.content.Context;

/* loaded from: classes.dex */
public final class Utils {

    @SuppressLint({"StaticFieldLeak"})
    private static Context context;

    private Utils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static Context getContext() {
        if (context != null) {
            return context;
        }
        throw new NullPointerException("u should init first");
    }

    public static void init(Context context2) {
        context = context2.getApplicationContext();
    }
}
