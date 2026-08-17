package com.startapp;

import com.startapp.sdk.ads.video.VideoMode;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class n4 implements Runnable {
    public final /* synthetic */ VideoMode a;

    public n4(VideoMode videoMode) {
        this.a = videoMode;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            this.a.s();
        } catch (Throwable th) {
            p7.a(this.a.b, th);
        }
    }
}
