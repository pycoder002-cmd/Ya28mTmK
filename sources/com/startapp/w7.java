package com.startapp;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class w7 implements Runnable {
    public final /* synthetic */ p7 a;
    public final /* synthetic */ int b;
    public final /* synthetic */ long c;
    public final /* synthetic */ v7 d;

    public w7(v7 v7Var, p7 p7Var, int i, long j) {
        this.d = v7Var;
        this.a = p7Var;
        this.b = i;
        this.c = j;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            this.d.a(this.a, this.b, this.c);
        } catch (Throwable unused) {
        }
    }
}
