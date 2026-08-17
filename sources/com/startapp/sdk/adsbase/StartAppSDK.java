package com.startapp.sdk.adsbase;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import com.github.mikephil.charting.utils.Utils;
import com.startapp.aa;
import com.startapp.p5;
import com.startapp.p7;
import com.startapp.q7;
import com.startapp.sdk.adsbase.StartAppSDKInternal;
import com.startapp.sdk.adsbase.remoteconfig.MetaDataRequest;
import com.startapp.sdk.components.ComponentLocator;
import com.startapp.v5;
import com.startapp.x9;
import com.startapp.y8;
import java.util.Map;
import java.util.TreeMap;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class StartAppSDK {
    public static void addWrapper(Context context, String str, String str2) {
        String str3 = StartAppSDKInternal.a;
        StartAppSDKInternal startAppSDKInternal = StartAppSDKInternal.c.a;
        if (startAppSDKInternal.s == null) {
            startAppSDKInternal.s = new TreeMap();
        }
        startAppSDKInternal.s.put(str, str2);
        p5 d = ComponentLocator.a(context).d();
        Map<String, String> map = startAppSDKInternal.s;
        Map<Activity, Integer> map2 = aa.a;
        String jSONObject = new JSONObject(map).toString();
        p5.a edit = d.edit();
        edit.a("sharedPrefsWrappers", jSONObject);
        edit.a.putString("sharedPrefsWrappers", jSONObject);
        edit.apply();
    }

    public static void enableReturnAds(boolean z) {
        String str = StartAppSDKInternal.a;
        StartAppSDKInternal.c.a.a(z);
    }

    public static SharedPreferences getExtras(Context context) {
        return ComponentLocator.a(context).H.b();
    }

    public static String getVersion() {
        return "4.9.1";
    }

    public static void inAppPurchaseMade(Context context) {
        inAppPurchaseMade(context, Utils.DOUBLE_EPSILON);
    }

    public static void inAppPurchaseMade(Context context, double d) {
        p5 d2 = ComponentLocator.a(context).d();
        float f = d2.getFloat("inAppPurchaseAmount", 0.0f);
        p5.a edit = d2.edit();
        float f2 = (float) (f + d);
        edit.a("inAppPurchaseAmount", (String) Float.valueOf(f2));
        edit.a.putFloat("inAppPurchaseAmount", f2);
        edit.a("payingUser", (String) Boolean.TRUE);
        edit.a.putBoolean("payingUser", true);
        edit.apply();
        String str = StartAppSDKInternal.a;
        StartAppSDKInternal.c.a.b(context, MetaDataRequest.RequestReason.IN_APP_PURCHASE);
    }

    public static void init(Context context, String str) {
        init(context, str, new SDKAdPreferences());
    }

    public static void init(Context context, String str, SDKAdPreferences sDKAdPreferences) {
        init(context, (String) null, str, sDKAdPreferences);
    }

    public static void init(Context context, String str, SDKAdPreferences sDKAdPreferences, boolean z) {
        init(context, null, str, sDKAdPreferences, z);
    }

    public static void init(Context context, String str, String str2) {
        init(context, str, str2, new SDKAdPreferences());
    }

    public static void init(Context context, String str, String str2, SDKAdPreferences sDKAdPreferences) {
        init(context, str, str2, sDKAdPreferences, true);
    }

    public static void init(Context context, String str, String str2, SDKAdPreferences sDKAdPreferences, boolean z) {
        String str3 = StartAppSDKInternal.a;
        StartAppSDKInternal startAppSDKInternal = StartAppSDKInternal.c.a;
        startAppSDKInternal.getClass();
        Context b = y8.b(context);
        StartAppSDKInternal.a(b, new v5(startAppSDKInternal, b, str, str2, sDKAdPreferences, z));
    }

    public static void init(Context context, String str, String str2, boolean z) {
        init(context, str, str2, new SDKAdPreferences(), z);
    }

    public static void init(Context context, String str, boolean z) {
        init(context, (String) null, str, z);
    }

    public static void setTestAdsEnabled(boolean z) {
        String str = StartAppSDKInternal.a;
        StartAppSDKInternal.c.a.F = z;
    }

    public static void setUserConsent(Context context, String str, long j, boolean z) {
        String str2 = StartAppSDKInternal.a;
        StartAppSDKInternal.c.a.getClass();
        if ("pas".equalsIgnoreCase(str)) {
            p5 d = ComponentLocator.a(context).d();
            String string = d.getString("USER_CONSENT_PERSONALIZED_ADS_SERVING", null);
            if (string != null) {
                if (string.equals(z ? "1" : "0")) {
                    return;
                }
            }
            StringBuilder sb = new StringBuilder();
            sb.append(z ? "1" : "0");
            sb.append("M");
            String sb2 = sb.toString();
            p7 p7Var = new p7(q7.b);
            p7Var.d = "User consent: " + str;
            p7Var.e = sb2;
            p7Var.a(context);
            p5.a edit = d.edit();
            String str3 = z ? "1" : "0";
            edit.a("USER_CONSENT_PERSONALIZED_ADS_SERVING", str3);
            edit.a.putString("USER_CONSENT_PERSONALIZED_ADS_SERVING", str3);
            edit.apply();
            x9 x9Var = x9.a;
            x9.a.a(context, MetaDataRequest.RequestReason.PAS);
        }
    }

    public static void startNewSession(Context context) {
        String str = StartAppSDKInternal.a;
        StartAppSDKInternal.c.a.b(context, MetaDataRequest.RequestReason.CUSTOM);
    }
}
