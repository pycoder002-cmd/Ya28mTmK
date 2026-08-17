package com.startapp;

import android.content.Context;
import com.startapp.sdk.components.ComponentLocator;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class p7 {
    public final q7 a;
    public final long b;
    public String c;
    public String d;
    public String e;
    public Object f;
    public String g;
    public Long h;
    public String i;
    public boolean j;
    public String k;

    public p7(q7 q7Var) {
        if (q7Var != q7.d) {
            this.a = q7Var;
        } else {
            this.a = q7.c;
        }
        q7 q7Var2 = this.a;
        if (q7Var2 == q7.c || q7Var2 == q7.b) {
            this.i = aa.a(aa.a(0));
        }
        this.b = 0L;
    }

    public p7(q7 q7Var, long j) {
        this.a = q7Var;
        this.b = j;
    }

    public p7(Throwable th) {
        this.a = q7.d;
        this.e = aa.b(th);
        this.d = aa.a(aa.a(th));
        this.i = aa.a(aa.a(1));
        this.b = 0L;
    }

    public p7(Throwable th, q7 q7Var) {
        boolean z = q7Var == q7.f;
        this.a = q7Var;
        this.e = aa.b(th);
        this.d = aa.a(aa.a(th));
        this.i = z ? th.getClass().getName() : aa.a(aa.a(1));
        this.b = 0L;
    }

    public static void a(Context context, Throwable th) {
        try {
            new p7(th).a(context);
        } catch (Throwable unused) {
        }
    }

    public static void a(Context context, Throwable th, q7 q7Var) {
        try {
            new p7(th, q7Var).a(context);
        } catch (Throwable unused) {
        }
    }

    public void a(Context context) {
        Context a = y8.a(context);
        if (a == null) {
            return;
        }
        try {
            ComponentLocator.a(a).k().a(this, null);
        } catch (Throwable unused) {
        }
    }

    public void a(Context context, u7 u7Var) {
        Context a = y8.a(context);
        if (a == null) {
            u7Var.a(this, 3);
            return;
        }
        try {
            ComponentLocator.a(a).k().a(this, u7Var);
        } catch (Throwable unused) {
            u7Var.a(this, 0);
        }
    }

    public String toString() {
        return super.toString();
    }
}
