package com.startapp;

import com.startapp.sdk.ads.video.VideoMode;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class m4 implements Runnable {
    public final /* synthetic */ int a;
    public final /* synthetic */ VideoMode b;

    public m4(VideoMode videoMode, int i) {
        this.b = videoMode;
        this.a = i;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            this.b.e(this.a);
        } catch (Throwable th) {
            p7.a(this.b.b, th);
        }
    }
}
