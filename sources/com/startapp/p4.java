package com.startapp;

import com.startapp.sdk.ads.video.VideoMode;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class p4 implements Runnable {
    public final /* synthetic */ VideoMode a;

    public p4(VideoMode videoMode) {
        this.a = videoMode;
    }

    @Override // java.lang.Runnable
    public void run() {
        VideoMode videoMode = this.a;
        if (videoMode.L == null) {
            return;
        }
        videoMode.O();
    }
}
