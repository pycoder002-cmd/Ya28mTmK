package com.startapp.sdk.adsbase;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Pair;
import com.startapp.aa;
import com.startapp.d;
import com.startapp.p5;
import com.startapp.p7;
import com.startapp.sdk.adsbase.remoteconfig.MetaData;
import com.startapp.sdk.adsbase.remoteconfig.MetaDataRequest;
import com.startapp.sdk.common.Constants;
import com.startapp.sdk.components.ComponentLocator;
import com.startapp.t5;
import com.startapp.t8;
import com.startapp.y8;
import com.startapp.ya;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class SimpleTokenUtils {
    public static List<PackageInfo> a = null;
    public static List<PackageInfo> b = null;
    public static long c = 0;
    public static volatile Pair<TokenType, String> d = null;
    public static volatile Pair<TokenType, String> e = null;
    public static boolean f = true;
    public static boolean g;
    public static TokenType h = TokenType.UNDEFINED;

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public enum TokenType {
        T1("token"),
        T2("token2"),
        UNDEFINED("");

        private final String text;

        TokenType(String str) {
            this.text = str;
        }

        public static TokenType a(String str) {
            TokenType tokenType = T1;
            if (tokenType.text.equals(str)) {
                return tokenType;
            }
            TokenType tokenType2 = T2;
            return tokenType2.text.equals(str) ? tokenType2 : UNDEFINED;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.text;
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public static class a extends BroadcastReceiver {
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            SimpleTokenUtils.d = null;
            SimpleTokenUtils.e = null;
            SimpleTokenUtils.f(context);
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public static class b implements t8 {
        public final /* synthetic */ Context a;

        public b(Context context) {
            this.a = context;
        }

        @Override // com.startapp.t8
        public void a(MetaDataRequest.RequestReason requestReason) {
            MetaData.h.a(this);
        }

        @Override // com.startapp.t8
        public void a(MetaDataRequest.RequestReason requestReason, boolean z) {
            if (z) {
                SimpleTokenUtils.d = null;
                SimpleTokenUtils.e = null;
                SimpleTokenUtils.f(this.a);
            }
            MetaData.h.a(this);
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public static class c implements Runnable {
        public final /* synthetic */ Context a;

        public c(Context context) {
            this.a = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            SimpleTokenUtils.e(this.a);
        }
    }

    public static Pair<String, String> a() {
        return d != null ? new Pair<>(((TokenType) d.first).toString(), d.second) : new Pair<>(TokenType.T1.toString(), "");
    }

    public static Pair<TokenType, String> a(Context context) {
        if (d == null) {
            e(context);
        }
        p5.a edit = ComponentLocator.a(context).d().edit();
        String str = (String) d.second;
        edit.a("shared_prefs_simple_token", str);
        edit.a.putString("shared_prefs_simple_token", str);
        edit.apply();
        f = false;
        h = TokenType.UNDEFINED;
        return new Pair<>(TokenType.T1, d.second);
    }

    public static List<PackageInfo> a(List<PackageInfo> list) {
        if (list.size() <= 100) {
            return list;
        }
        ArrayList arrayList = new ArrayList(list);
        if (Build.VERSION.SDK_INT >= 9) {
            Collections.sort(arrayList, new t5());
        }
        return arrayList.subList(0, 100);
    }

    public static long b() {
        return c;
    }

    public static Pair<TokenType, String> b(Context context) {
        if (e == null) {
            e(context);
        }
        p5.a edit = ComponentLocator.a(context).d().edit();
        String str = (String) e.second;
        edit.a("shared_prefs_simple_token2", str);
        edit.a.putString("shared_prefs_simple_token2", str);
        edit.apply();
        f = false;
        h = TokenType.UNDEFINED;
        return new Pair<>(TokenType.T2, e.second);
    }

    public static List<String> b(List<PackageInfo> list) {
        ArrayList arrayList = new ArrayList();
        Iterator<PackageInfo> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().packageName);
        }
        return arrayList;
    }

    public static void c(Context context) {
        Context b2 = y8.b(context);
        f(b2);
        f = true;
        g = false;
        h = TokenType.UNDEFINED;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
        intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
        b2.registerReceiver(new a(), intentFilter);
        MetaData.h.a(new b(b2));
    }

    public static void d(Context context) {
        PackageManager packageManager = context.getPackageManager();
        Set<String> p = MetaData.h.p();
        Set<String> x = MetaData.h.x();
        a = new CopyOnWriteArrayList();
        b = new CopyOnWriteArrayList();
        try {
            List<PackageInfo> a2 = ya.a(packageManager);
            c = System.currentTimeMillis();
            PackageInfo packageInfo = null;
            for (PackageInfo packageInfo2 : a2) {
                if (!ya.a(packageInfo2)) {
                    if (Build.VERSION.SDK_INT >= 9) {
                        long j = packageInfo2.firstInstallTime;
                        if (j < c && j >= 1291593600000L) {
                            c = j;
                        }
                    }
                    a.add(packageInfo2);
                    try {
                        String str = packageInfo2.packageName;
                        String b2 = aa.b(context);
                        if (p != null && p.contains(b2)) {
                            b.add(packageInfo2);
                        }
                    } catch (Throwable th) {
                        p7.a(context, th);
                    }
                } else if (x.contains(packageInfo2.packageName)) {
                    a.add(packageInfo2);
                } else if (packageInfo2.packageName.equals(Constants.a)) {
                    packageInfo = packageInfo2;
                }
            }
            a = a(a);
            b = a(b);
            if (packageInfo != null) {
                a.add(0, packageInfo);
            }
        } catch (Throwable th2) {
            p7.a(context, th2);
        }
    }

    public static void e(Context context) {
        boolean a2 = MetaData.h.D().a(context);
        synchronized (SimpleTokenUtils.class) {
            if ((d == null || e == null) && a2) {
                try {
                    d(context);
                    d = new Pair<>(TokenType.T1, d.a(b(a)));
                    e = new Pair<>(TokenType.T2, d.a(b(b)));
                } catch (Throwable th) {
                    p7.a(context, th);
                }
            }
        }
    }

    public static void f(Context context) {
        Context b2 = y8.b(context);
        try {
            if ((d == null || e == null) && MetaData.h.D().a(b2)) {
                ComponentLocator.a(b2).i().execute(new c(b2));
            }
        } catch (Throwable th) {
            p7.a(b2, th);
        }
    }
}
