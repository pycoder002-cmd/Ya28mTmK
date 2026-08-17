package com.startapp;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.KeyEvent;
import com.startapp.sdk.adsbase.Ad;
import com.startapp.sdk.adsbase.adinformation.AdInformationObject;
import com.startapp.sdk.adsbase.adinformation.AdInformationOverrides;
import com.startapp.sdk.adsbase.model.AdPreferences;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public abstract class p2 {
    public Intent a;
    public Activity b;
    public String[] e;
    public boolean[] f;
    public String h;
    public String[] i;
    public String[] j;
    public String[] k;
    public Ad l;
    public String m;
    public AdPreferences.Placement n;
    public AdInformationOverrides o;
    public String p;
    public Long q;
    public AdInformationObject c = null;
    public BroadcastReceiver d = new a();
    public boolean[] g = {true};
    public Boolean[] r = null;
    public int s = 0;
    public boolean t = false;
    public boolean u = false;

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class a extends BroadcastReceiver {
        public a() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            p2.this.b();
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            p2.this.b.finish();
        }
    }

    public String a() {
        try {
            String[] strArr = this.i;
            return (strArr == null || strArr.length <= 0) ? "" : g5.a(strArr[0], (String) null);
        } catch (Throwable th) {
            p7.a(this.b, th);
            return "";
        }
    }

    public void a(Configuration configuration) {
    }

    public abstract void a(Bundle bundle);

    public void a(String str) {
        String str2;
        if (str == null || (str2 = this.p) == null || str2.length() <= 0) {
            this.m = str;
        } else {
            this.m = str.replaceAll("startapp_adtag_placeholder", this.p);
        }
    }

    public boolean a(int i) {
        boolean[] zArr = this.g;
        if (zArr == null || i < 0 || i >= zArr.length) {
            return true;
        }
        return zArr[i];
    }

    public boolean a(int i, KeyEvent keyEvent) {
        return false;
    }

    public void b() {
        this.b.runOnUiThread(new b());
    }

    public void b(Bundle bundle) {
    }

    public boolean c() {
        return false;
    }

    public void d() {
        if (this.d != null) {
            la.a(this.b).a(this.d);
        }
        this.d = null;
    }

    public abstract void e();

    public abstract void f();

    public void g() {
    }

    public void h() {
        la.a(this.b).a(new Intent("com.startapp.android.HideDisplayBroadcastListener"));
    }
}
