package com.startapp;

import android.content.Context;
import android.os.Build;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class x1 {
    public static boolean a(Context context) {
        return Build.VERSION.SDK_INT < 16 || context.checkCallingOrSelfPermission("android.permission.READ_CALL_LOG") == 0;
    }

    public static boolean b(Context context) {
        return context.checkCallingOrSelfPermission("android.permission.READ_PHONE_STATE") == 0;
    }
}
