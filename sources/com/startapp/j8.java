package com.startapp;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class j8 implements Runnable {
    public final /* synthetic */ String a;
    public final /* synthetic */ String b;
    public final /* synthetic */ long c;
    public final /* synthetic */ i8 d;

    public j8(i8 i8Var, String str, String str2, long j) {
        this.d = i8Var;
        this.a = str;
        this.b = str2;
        this.c = j;
    }

    @Override // java.lang.Runnable
    public void run() {
        i8 i8Var = this.d;
        String str = this.a;
        String str2 = this.b;
        long j = this.c;
        i8Var.getClass();
        try {
            i8Var.c.a(str, str2, System.currentTimeMillis(), j);
        } catch (Throwable th) {
            if (i8Var.a(1)) {
                p7.a(i8Var.a, th);
            }
        }
    }
}
