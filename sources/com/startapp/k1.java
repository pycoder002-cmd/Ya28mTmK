package com.startapp;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class k1 implements Runnable {
    private final Runnable a;

    public k1(Runnable runnable) {
        this.a = runnable;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            this.a.run();
        } catch (Throwable th) {
            h1.c(th);
        }
    }
}
