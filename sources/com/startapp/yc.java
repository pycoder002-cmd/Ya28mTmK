package com.startapp;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class yc implements Runnable {
    public final /* synthetic */ xc a;

    public yc(xc xcVar) {
        this.a = xcVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        xc xcVar = this.a;
        xcVar.getClass();
        try {
            xcVar.d();
        } catch (Throwable th) {
            p7.a(xcVar.b, th);
        }
    }
}
