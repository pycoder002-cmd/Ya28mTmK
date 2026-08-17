package com.startapp;

import android.os.HandlerThread;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class n9 extends HandlerThread {
    public final Object a;

    public n9(String str) {
        super(str);
        this.a = new Object();
    }

    @Override // android.os.HandlerThread
    public void onLooperPrepared() {
        synchronized (this.a) {
            this.a.notifyAll();
        }
    }

    @Override // java.lang.Thread
    public void start() {
        synchronized (this.a) {
            super.start();
            try {
                this.a.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
