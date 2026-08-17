package com.startapp;

import android.R;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Pair;
import com.startapp.sdk.ads.interstitials.OverlayActivity;
import com.startapp.sdk.adsbase.AdsCommonMetaData;
import com.startapp.sdk.adsbase.AdsConstants;
import com.startapp.sdk.adsbase.HtmlAd;
import com.startapp.sdk.adsbase.JsonAd;
import com.startapp.sdk.adsbase.StartAppSDKInternal;
import com.startapp.sdk.adsbase.commontracking.TrackingParams;
import com.startapp.sdk.adsbase.model.AdDetails;
import com.startapp.sdk.adsbase.model.AdPreferences;
import com.startapp.sdk.adsbase.remoteconfig.AnalyticsConfig;
import com.startapp.sdk.adsbase.remoteconfig.MetaData;
import com.startapp.sdk.common.Constants;
import com.startapp.sdk.components.ComponentLocator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class g5 {
    public static Handler a;
    public static ProgressDialog b;

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public static class a implements Runnable {
        public final /* synthetic */ Context a;
        public final /* synthetic */ String b;

        public a(Context context, String str) {
            this.a = context;
            this.b = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            z5 z5Var = new z5();
            try {
                z5Var.K = ComponentLocator.a(this.a).p().a(z5Var);
            } catch (Throwable th) {
                p7.a(this.a, th);
            }
            try {
                n7 j = ComponentLocator.a(this.a).j();
                String str = this.b;
                j.getClass();
                try {
                    j.a(str, z5Var, null);
                } catch (Throwable th2) {
                    p7.a(j.a, th2);
                }
            } catch (Throwable th3) {
                p7.a(this.a, th3);
            }
        }
    }

    public static int a(String str) {
        String[] split = str.split("&");
        return Integer.parseInt(split[split.length - 1].split("=")[1]);
    }

    public static Pair<String, String> a(Context context, String[] strArr, String str, TrackingParams trackingParams, boolean z) {
        String str2;
        if (strArr != null) {
            for (String str3 : strArr) {
                if (!TextUtils.isEmpty(str3)) {
                    a(context, str3, trackingParams);
                }
            }
        }
        String str4 = StartAppSDKInternal.a;
        StartAppSDKInternal startAppSDKInternal = StartAppSDKInternal.c.a;
        startAppSDKInternal.o = true;
        startAppSDKInternal.h = true;
        String str5 = null;
        if (z) {
            str2 = null;
        } else {
            try {
                str2 = null;
                for (String str6 : strArr) {
                    try {
                        str2 = a(str, str6);
                        if (str2 != null || aa.d(str6)) {
                            str5 = str6;
                            break;
                        }
                    } catch (Throwable th) {
                        th = th;
                        p7.a(context, th);
                        return new Pair<>(str5, str2);
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                str2 = null;
            }
        }
        return new Pair<>(str5, str2);
    }

    public static String a() {
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        for (int i = 0; i < 8; i++) {
            if (stackTrace[i].getMethodName().compareTo("doHome") == 0) {
                return "home";
            }
            if (stackTrace[i].getMethodName().compareTo("onBackPressed") == 0) {
                String str = StartAppSDKInternal.a;
                StartAppSDKInternal startAppSDKInternal = StartAppSDKInternal.c.a;
                Activity activity = startAppSDKInternal.n;
                if (!(activity != null ? activity.isTaskRoot() : true)) {
                    Map<String, String> map = startAppSDKInternal.s;
                    if (!((map == null ? null : map.get("Unity")) != null)) {
                        return "interstitial";
                    }
                }
                startAppSDKInternal.g = false;
                startAppSDKInternal.i = true;
                return "back";
            }
        }
        return "interstitial";
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v3, types: [java.lang.CharSequence] */
    public static String a(Context context, String str) {
        try {
            return context.getResources().getString(context.getApplicationInfo().labelRes);
        } catch (Throwable unused) {
            PackageManager packageManager = context.getPackageManager();
            ApplicationInfo applicationInfo = null;
            try {
                applicationInfo = packageManager.getApplicationInfo(context.getApplicationInfo().packageName, 0);
            } catch (Throwable unused2) {
            }
            if (applicationInfo != null) {
                str = packageManager.getApplicationLabel(applicationInfo);
            }
            return (String) str;
        }
    }

    public static String a(String str, String str2) {
        if (str2 != null) {
            try {
                if (!str2.equals("")) {
                    str = str2;
                }
            } catch (Exception unused) {
            }
        }
        String[] split = str.split("[?&]d=");
        if (split.length >= 2) {
            return split[1].split("[?&]")[0];
        }
        return null;
    }

    public static List<String> a(List<String> list, String str, String str2) {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (i < list.size()) {
            int i2 = i + 5;
            List<String> subList = list.subList(i, Math.min(i2, list.size()));
            StringBuilder sb = new StringBuilder();
            sb.append(AdsConstants.f);
            sb.append("?");
            sb.append(TextUtils.join("&", subList));
            sb.append("&isShown=");
            sb.append(str);
            sb.append("&appPresence=" + str2);
            arrayList.add(sb.toString());
            i = i2;
        }
        return arrayList;
    }

    public static void a(Context context, String str, TrackingParams trackingParams) {
        b(context, str, trackingParams);
        aa.a(context, false, TextUtils.isEmpty(str) ? "Closed Ad" : "Clicked Ad", true);
    }

    public static void a(Context context, String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            p7 p7Var = new p7(q7.c);
            p7Var.d = "Can not open in app browser, clickUrl is empty";
            if (str2 != null) {
                p7Var.g = str2;
            }
            p7Var.a(context);
            return;
        }
        if (b(str)) {
            b(context, str, str2);
            return;
        }
        Map<Activity, Integer> map = aa.a;
        try {
            if (Build.VERSION.SDK_INT >= 18 && MetaData.h.i() && ComponentLocator.a(context).d().getBoolean("chromeTabs", false)) {
                a(context, str, true);
                return;
            }
        } catch (Throwable th) {
            p7.a(context, th);
        }
        Intent intent = new Intent(context, (Class<?>) OverlayActivity.class);
        int i = Build.VERSION.SDK_INT;
        if (i >= 21) {
            intent.addFlags(524288);
        }
        if (i >= 11) {
            intent.addFlags(32768);
        }
        if (!(context instanceof Activity)) {
            intent.addFlags(268435456);
        }
        intent.setData(Uri.parse(str));
        intent.putExtra("placement", AdPreferences.Placement.INAPP_BROWSER.a());
        intent.putExtra("activityShouldLockOrientation", false);
        try {
            context.startActivity(intent);
        } catch (Throwable th2) {
            p7.a(context, th2);
        }
    }

    public static void a(Context context, String str, boolean z) {
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
        Bundle bundle = new Bundle();
        bundle.putBinder("android.support.customtabs.extra.SESSION", null);
        intent.putExtras(bundle);
        if (z) {
            try {
                List<ResolveInfo> queryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 0);
                if (queryIntentActivities != null && queryIntentActivities.size() > 1) {
                    intent.setPackage(queryIntentActivities.get(0).activityInfo.packageName);
                }
            } catch (Throwable th) {
                p7.a(context, th);
            }
        }
        if (!(context instanceof Activity)) {
            intent.addFlags(268435456);
        }
        try {
            context.startActivity(intent);
        } catch (Throwable th2) {
            p7.a(context, th2);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0020 A[Catch: all -> 0x0088, TryCatch #0 {all -> 0x0088, blocks: (B:3:0x000c, B:5:0x0014, B:10:0x0020, B:11:0x0033, B:14:0x003d, B:17:0x0041, B:19:0x0048, B:21:0x004e, B:23:0x005c, B:25:0x0084), top: B:2:0x000c }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void a(android.content.Context r2, java.lang.String r3, java.lang.String[] r4, com.startapp.sdk.adsbase.commontracking.TrackingParams r5, boolean r6, boolean r7) {
        /*
            android.util.Pair r4 = a(r2, r4, r3, r5, r7)
            java.lang.Object r5 = r4.first
            java.lang.String r5 = (java.lang.String) r5
            java.lang.Object r4 = r4.second
            java.lang.String r4 = (java.lang.String) r4
            com.startapp.sdk.adsbase.AdsCommonMetaData r7 = com.startapp.sdk.adsbase.AdsCommonMetaData.h     // Catch: java.lang.Throwable -> L88
            boolean r7 = r7.M()     // Catch: java.lang.Throwable -> L88
            if (r7 != 0) goto L1d
            boolean r7 = android.text.TextUtils.isEmpty(r5)     // Catch: java.lang.Throwable -> L88
            if (r7 == 0) goto L1b
            goto L1d
        L1b:
            r7 = 0
            goto L1e
        L1d:
            r7 = 1
        L1e:
            if (r7 == 0) goto L33
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L88
            r7.<init>()     // Catch: java.lang.Throwable -> L88
            r7.append(r3)     // Catch: java.lang.Throwable -> L88
            java.lang.String r3 = com.startapp.wa.c(r4)     // Catch: java.lang.Throwable -> L88
            r7.append(r3)     // Catch: java.lang.Throwable -> L88
            java.lang.String r3 = r7.toString()     // Catch: java.lang.Throwable -> L88
        L33:
            com.startapp.sdk.adsbase.remoteconfig.MetaData r7 = com.startapp.sdk.adsbase.remoteconfig.MetaData.h     // Catch: java.lang.Throwable -> L88
            boolean r7 = r7.N()     // Catch: java.lang.Throwable -> L88
            if (r7 == 0) goto L41
            if (r6 == 0) goto L41
            a(r2, r3, r4)     // Catch: java.lang.Throwable -> L88
            goto L8c
        L41:
            boolean r4 = android.text.TextUtils.isEmpty(r5)     // Catch: java.lang.Throwable -> L88
            r5 = 0
            if (r4 == 0) goto L84
            boolean r4 = a(r2)     // Catch: java.lang.Throwable -> L88
            if (r4 == 0) goto L84
            com.startapp.sdk.components.ComponentLocator r4 = com.startapp.sdk.components.ComponentLocator.a(r2)     // Catch: java.lang.Throwable -> L88
            com.startapp.p5 r4 = r4.d()     // Catch: java.lang.Throwable -> L88
            com.startapp.p5$a r4 = r4.edit()     // Catch: java.lang.Throwable -> L88
            java.lang.String r6 = "shared_prefs_CookieFeatureTS"
            long r0 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> L88
            java.lang.Long r7 = java.lang.Long.valueOf(r0)     // Catch: java.lang.Throwable -> L88
            r4.a(r6, r7)     // Catch: java.lang.Throwable -> L88
            android.content.SharedPreferences$Editor r7 = r4.a     // Catch: java.lang.Throwable -> L88
            r7.putLong(r6, r0)     // Catch: java.lang.Throwable -> L88
            r4.apply()     // Catch: java.lang.Throwable -> L88
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L88
            r4.<init>()     // Catch: java.lang.Throwable -> L88
            r4.append(r3)     // Catch: java.lang.Throwable -> L88
            java.lang.String r3 = "&cki=1"
            r4.append(r3)     // Catch: java.lang.Throwable -> L88
            java.lang.String r3 = r4.toString()     // Catch: java.lang.Throwable -> L88
            b(r2, r3, r5)     // Catch: java.lang.Throwable -> L88
            goto L8c
        L84:
            b(r2, r3, r5)     // Catch: java.lang.Throwable -> L88
            goto L8c
        L88:
            r3 = move-exception
            com.startapp.p7.a(r2, r3)
        L8c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.startapp.g5.a(android.content.Context, java.lang.String, java.lang.String[], com.startapp.sdk.adsbase.commontracking.TrackingParams, boolean, boolean):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:44:0x01bb  */
    /* JADX WARN: Removed duplicated region for block: B:46:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void a(android.content.Context r16, java.lang.String r17, java.lang.String[] r18, java.lang.String r19, com.startapp.sdk.adsbase.commontracking.TrackingParams r20, long r21, long r23, boolean r25, java.lang.Boolean r26, boolean r27, java.lang.Runnable r28) {
        /*
            Method dump skipped, instructions count: 464
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.startapp.g5.a(android.content.Context, java.lang.String, java.lang.String[], java.lang.String, com.startapp.sdk.adsbase.commontracking.TrackingParams, long, long, boolean, java.lang.Boolean, boolean, java.lang.Runnable):void");
    }

    public static void a(Context context, String[] strArr, String str, int i, String str2) {
        TrackingParams a2 = new TrackingParams(str).a(i).a(str2);
        if (strArr == null || strArr.length == 0) {
            p7 p7Var = new p7(q7.c);
            p7Var.d = "Non-impression without trackingUrls";
            p7Var.i = str2;
            p7Var.e = aa.b(a2.d());
            p7Var.a(context);
            return;
        }
        for (String str3 : strArr) {
            if (!TextUtils.isEmpty(str3)) {
                b(context, str3, a2);
            }
        }
    }

    public static void a(Context context, String[] strArr, String str, int i, String str2, JSONObject jSONObject) {
        try {
            AnalyticsConfig analyticsConfig = MetaData.h.analytics;
            if (analyticsConfig != null && analyticsConfig.j() && jSONObject != null) {
                p7 p7Var = new p7(q7.b);
                p7Var.d = "viewability_info";
                p7Var.i = str2;
                p7Var.e = aa.c(jSONObject.toString());
                p7Var.a(context);
            }
        } catch (Throwable th) {
            p7.a(context, th);
        }
        try {
            String str3 = "Dropped impression because " + str2;
            if (jSONObject != null) {
                str3 = str3 + ", view hierarchy: " + jSONObject.toString(2);
            }
            aa.a(context, true, str3, false);
        } catch (Throwable th2) {
            p7.a(context, th2);
        }
        a(context, strArr, str, i, str2);
    }

    public static void a(Runnable runnable) {
        if (runnable != null) {
            Looper mainLooper = Looper.getMainLooper();
            if (mainLooper == null || mainLooper.getThread() == Thread.currentThread()) {
                runnable.run();
                return;
            }
            Handler handler = a;
            if (handler == null) {
                handler = new Handler(mainLooper);
                a = handler;
            }
            handler.post(runnable);
        }
    }

    public static void a(String str, String str2, String str3, Context context, TrackingParams trackingParams) {
        b(context, str3, trackingParams);
        Intent a2 = aa.a(context, str);
        if (str2 != null) {
            try {
                JSONObject jSONObject = new JSONObject(str2);
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String valueOf = String.valueOf(keys.next());
                    a2.putExtra(valueOf, String.valueOf(jSONObject.get(valueOf)));
                }
            } catch (JSONException unused) {
            }
        }
        try {
            context.startActivity(a2);
        } catch (Throwable th) {
            p7.a(context, th);
        }
    }

    public static boolean a(Activity activity) {
        boolean z = activity.getTheme().obtainStyledAttributes(new int[]{R.attr.windowFullscreen}).getBoolean(0, false);
        if ((activity.getWindow().getAttributes().flags & 1024) != 0) {
            return true;
        }
        return z;
    }

    public static boolean a(Context context) {
        ComponentLocator a2 = ComponentLocator.a(context);
        if (a2.a().a().d) {
            return false;
        }
        long j = a2.d().getLong("shared_prefs_CookieFeatureTS", 0L);
        return j == 0 || j + (((long) AdsCommonMetaData.h.e()) * 86400000) <= System.currentTimeMillis();
    }

    public static boolean a(Context context, Intent intent) {
        for (ResolveInfo resolveInfo : context.getPackageManager().queryIntentActivities(intent, 0)) {
            if (resolveInfo.activityInfo.packageName.equalsIgnoreCase(Constants.a)) {
                ActivityInfo activityInfo = resolveInfo.activityInfo;
                intent.setComponent(new ComponentName(activityInfo.packageName, activityInfo.name));
                return true;
            }
        }
        return false;
    }

    public static boolean a(Context context, AdPreferences.Placement placement) {
        if (placement.equals(AdPreferences.Placement.INAPP_SPLASH) || !AdsCommonMetaData.h.a()) {
            return false;
        }
        return a(context);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static String[] a(n5 n5Var) {
        return n5Var instanceof HtmlAd ? ((HtmlAd) n5Var).trackingUrls : n5Var instanceof JsonAd ? a(((JsonAd) n5Var).g()) : new String[0];
    }

    public static String[] a(List<AdDetails> list) {
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            Iterator<AdDetails> it = list.iterator();
            while (it.hasNext()) {
                arrayList.addAll(Arrays.asList(it.next().v()));
            }
        }
        return (String[]) arrayList.toArray(new String[0]);
    }

    public static void b(Context context) {
        if (context != null && (context instanceof Activity)) {
            Activity activity = (Activity) context;
            Map<Activity, Integer> map = aa.a;
            aa.a(activity, activity.getResources().getConfiguration().orientation, false);
        }
        ProgressDialog progressDialog = b;
        if (progressDialog != null) {
            synchronized (progressDialog) {
                ProgressDialog progressDialog2 = b;
                if (progressDialog2 != null && progressDialog2.isShowing()) {
                    try {
                        b.cancel();
                    } catch (Throwable th) {
                        p7.a(context, th);
                    }
                    b = null;
                }
            }
        }
    }

    public static void b(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            return;
        }
        ComponentLocator.a(context).o().execute(new a(context, str));
    }

    public static void b(Context context, String str, TrackingParams trackingParams) {
        if (context == null || TextUtils.isEmpty(str)) {
            return;
        }
        StringBuilder sb = new StringBuilder(str);
        String a2 = a(str, (String) null);
        if (a2 != null) {
            sb.append(wa.c(a2));
        }
        if (trackingParams != null && (a2 != null || aa.d(str))) {
            sb.append(trackingParams.e());
        }
        b(context, sb.toString());
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x0080, code lost:
    
        r1.setPackage(r5);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void b(android.content.Context r8, java.lang.String r9, java.lang.String r10) {
        /*
            boolean r10 = c(r9)
            if (r8 != 0) goto L8
            goto Laa
        L8:
            r0 = 76021760(0x4880000, float:3.1973446E-36)
            com.startapp.sdk.adsbase.AdsCommonMetaData r1 = com.startapp.sdk.adsbase.AdsCommonMetaData.h
            boolean r1 = r1.J()
            if (r1 != 0) goto L16
            boolean r1 = r8 instanceof android.app.Activity
            if (r1 != 0) goto L18
        L16:
            r0 = 344457216(0x14880000, float:1.373249E-26)
        L18:
            android.content.Intent r1 = new android.content.Intent
            android.net.Uri r2 = android.net.Uri.parse(r9)
            java.lang.String r3 = "android.intent.action.VIEW"
            r1.<init>(r3, r2)
            r1.addFlags(r0)
            boolean r2 = a(r8, r1)
            r3 = 0
            if (r2 != 0) goto L51
            int r4 = android.os.Build.VERSION.SDK_INT     // Catch: java.lang.Throwable -> L4f
            r5 = 18
            if (r4 < r5) goto L51
            com.startapp.sdk.adsbase.remoteconfig.MetaData r4 = com.startapp.sdk.adsbase.remoteconfig.MetaData.h     // Catch: java.lang.Throwable -> L4f
            boolean r4 = r4.h()     // Catch: java.lang.Throwable -> L4f
            if (r4 == 0) goto L51
            com.startapp.sdk.components.ComponentLocator r4 = com.startapp.sdk.components.ComponentLocator.a(r8)     // Catch: java.lang.Throwable -> L4f
            com.startapp.p5 r4 = r4.d()     // Catch: java.lang.Throwable -> L4f
            java.lang.String r5 = "chromeTabs"
            boolean r4 = r4.getBoolean(r5, r3)     // Catch: java.lang.Throwable -> L4f
            if (r4 == 0) goto L51
            a(r8, r9, r10)     // Catch: java.lang.Throwable -> L4f
            goto Laa
        L4f:
            r10 = move-exception
            goto L8f
        L51:
            if (r10 == 0) goto L8b
            if (r2 != 0) goto L8b
            r10 = 5
            java.lang.String r2 = "com.android.chrome"
            java.lang.String r4 = "com.android.browser"
            java.lang.String r5 = "com.opera.mini.native"
            java.lang.String r6 = "org.mozilla.firefox"
            java.lang.String r7 = "com.opera.browser"
            java.lang.String[] r2 = new java.lang.String[]{r2, r4, r5, r6, r7}     // Catch: java.lang.Throwable -> L4f
            android.content.pm.PackageManager r4 = r8.getPackageManager()     // Catch: java.lang.Throwable -> L87
            java.util.List r4 = r4.queryIntentActivities(r1, r0)     // Catch: java.lang.Throwable -> L87
            if (r4 == 0) goto L8b
            int r4 = r4.size()     // Catch: java.lang.Throwable -> L87
            r5 = 1
            if (r4 <= r5) goto L8b
            r4 = 0
        L76:
            if (r4 >= r10) goto L8b
            r5 = r2[r4]     // Catch: java.lang.Throwable -> L87
            boolean r6 = com.startapp.ya.a(r8, r5, r3)     // Catch: java.lang.Throwable -> L87
            if (r6 == 0) goto L84
            r1.setPackage(r5)     // Catch: java.lang.Throwable -> L87
            goto L8b
        L84:
            int r4 = r4 + 1
            goto L76
        L87:
            r10 = move-exception
            com.startapp.p7.a(r8, r10)     // Catch: java.lang.Throwable -> L4f
        L8b:
            r8.startActivity(r1)     // Catch: java.lang.Throwable -> L4f
            goto Laa
        L8f:
            com.startapp.p7.a(r8, r10)
            android.content.Intent r9 = android.content.Intent.parseUri(r9, r0)     // Catch: java.lang.Throwable -> La6
            a(r8, r9)     // Catch: java.lang.Throwable -> La6
            boolean r10 = r8 instanceof android.app.Activity     // Catch: java.lang.Throwable -> La6
            if (r10 != 0) goto La2
            r10 = 268435456(0x10000000, float:2.5243549E-29)
            r9.addFlags(r10)     // Catch: java.lang.Throwable -> La6
        La2:
            r8.startActivity(r9)     // Catch: java.lang.Throwable -> La6
            goto Laa
        La6:
            r9 = move-exception
            com.startapp.p7.a(r8, r9)
        Laa:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.startapp.g5.b(android.content.Context, java.lang.String, java.lang.String):void");
    }

    public static boolean b(String str) {
        return str.startsWith("market") || str.startsWith("http://play.google.com") || str.startsWith("https://play.google.com");
    }

    public static boolean c(String str) {
        return str != null && (str.startsWith("http://") || str.startsWith("https://"));
    }
}
