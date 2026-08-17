package com.startapp;

import android.content.Context;
import android.net.ConnectivityManager;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.startapp.networkTest.data.WifiInfo;
import com.startapp.networkTest.enums.WifiStates;
import java.lang.reflect.Method;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class x {
    private static final String a = "x";
    private static final boolean b = false;
    public static final int c = -1;
    private ConnectivityManager e;
    private Context g;
    private boolean i;
    private Method j;
    private boolean f = false;
    private String h = "";
    private WifiStates d = WifiStates.Unknown;

    public x(Context context) {
        this.g = context.getApplicationContext();
        this.e = (ConnectivityManager) context.getSystemService("connectivity");
        a();
    }

    private String a(String str) {
        int ordinal;
        if (str.length() == 0 || (ordinal = s.b().WIFIINFO_BSSID_RECORDTYPE().ordinal()) == 0) {
            return str;
        }
        if (ordinal != 1) {
            return "";
        }
        if (str.length() != 17) {
            return "xx:xx:xx:xx:xx:xx";
        }
        return str.substring(0, 9) + "xx:xx:xx";
    }

    private void a() {
    }

    private String b(String str) {
        return (str.length() == 0 || s.b().WIFIINFO_SSID_RECORDTYPE().ordinal() == 0) ? str : "";
    }

    private int[] b() {
        int[] iArr = {-1, 0};
        if (this.i) {
            return iArr;
        }
        String[] a2 = z1.a("/proc/net/wireless");
        if (a2.length == 0) {
            this.i = true;
            return iArr;
        }
        if (a2.length > 2) {
            for (int i = 2; i < a2.length; i++) {
                String[] a3 = b2.a(a2[i].trim().split(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR));
                if (a3.length > 4 && a3[0].equals("wlan0:")) {
                    try {
                        return new int[]{Integer.parseInt(a3[2].replace(".", "")), Integer.parseInt(a3[3].replace(".", ""))};
                    } catch (NumberFormatException unused) {
                        return iArr;
                    }
                }
            }
        }
        return iArr;
    }

    public WifiInfo c() {
        WifiInfo wifiInfo = new WifiInfo();
        wifiInfo.MissingPermission = true;
        try {
        } catch (Throwable th) {
            h1.a(th);
        }
        return this.g.checkCallingOrSelfPermission("android.permission.ACCESS_WIFI_STATE") == -1 ? wifiInfo : wifiInfo;
    }

    public long d() {
        if (this.h.length() == 0) {
            c();
        }
        if (this.h.length() == 0) {
            return -1L;
        }
        return c2.a(this.h);
    }

    public long e() {
        String str = this.h;
        if (str == null || str.length() == 0) {
            c();
        }
        String str2 = this.h;
        if (str2 == null || str2.length() == 0) {
            return -1L;
        }
        return c2.b(this.h);
    }

    public void f() {
    }

    public void g() {
    }
}
