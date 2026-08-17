package com.startapp;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class o5 {
    public static final String a = "o5";
    public String b;
    public boolean c;

    public o5(Context context) {
        this.c = true;
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            return;
        }
        try {
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 128);
            Bundle bundle = applicationInfo.metaData;
            if (bundle == null) {
                return;
            }
            if (!bundle.containsKey("com.startapp.sdk.APPLICATION_ID")) {
                Log.i(a, "appId hasn't been provided in the Manifest");
                return;
            }
            int i = applicationInfo.metaData.getInt("com.startapp.sdk.APPLICATION_ID");
            this.b = i != 0 ? String.valueOf(i) : applicationInfo.metaData.getString("com.startapp.sdk.APPLICATION_ID");
            String str = a;
            Log.i(str, "appId is " + this.b);
            if (applicationInfo.metaData.containsKey("com.startapp.sdk.RETURN_ADS_ENABLED")) {
                this.c = applicationInfo.metaData.getBoolean("com.startapp.sdk.RETURN_ADS_ENABLED");
                Log.i(str, "returnAds enabled: " + this.c);
            }
        } catch (Throwable th) {
            p7.a(context, th);
        }
    }
}
