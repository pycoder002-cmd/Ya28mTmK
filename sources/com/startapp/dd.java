package com.startapp;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class dd implements Runnable {
    public final /* synthetic */ cd a;

    public dd(cd cdVar) {
        this.a = cdVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            this.a.c();
        } catch (Throwable th) {
            p7.a(this.a.b, th);
        }
    }
}
