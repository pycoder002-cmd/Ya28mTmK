package com.startapp;

import android.content.Context;
import java.security.PublicKey;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class s {
    public static final String a = "insight Core SDK";
    public static final String b = "© 2014 - 2020 umlaut insight GmbH";
    public static final String c = "20211123190300";
    private static s d;
    private q e;
    private n1 f;
    private t g;
    private Context h;
    private PublicKey i;
    private a j;

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public interface a {
        void a(String str);
    }

    private s(Context context) {
        this.h = context;
    }

    public static String a() {
        return d.g.p();
    }

    public static void a(Context context, int i) {
        try {
            a(context, a2.a(context.getResources().openRawResource(i)));
        } catch (Exception unused) {
            throw new IllegalArgumentException("Error while opening the raw resource");
        }
    }

    public static void a(Context context, byte[] bArr) {
        if (context == null) {
            throw new IllegalArgumentException("context is null");
        }
        if (bArr == null) {
            throw new IllegalArgumentException("config is null");
        }
        if (d != null) {
            return;
        }
        try {
            r a2 = r.a(bArr);
            s sVar = new s(context);
            d = sVar;
            sVar.i = a2.a;
            sVar.e = a2.b;
            sVar.g();
        } catch (Exception unused) {
            throw new IllegalArgumentException("configuration is invalid");
        }
    }

    public static void a(a aVar) {
        d.j = aVar;
    }

    public static q b() {
        return d.e;
    }

    public static t c() {
        return d.g;
    }

    public static a d() {
        return d.j;
    }

    public static PublicKey e() {
        return d.i;
    }

    public static synchronized n1 f() {
        n1 n1Var;
        synchronized (s.class) {
            n1Var = d.f;
        }
        return n1Var;
    }

    private void g() {
        this.f = new n1();
        this.g = new t(this.h);
    }

    public static boolean h() {
        return d != null;
    }
}
