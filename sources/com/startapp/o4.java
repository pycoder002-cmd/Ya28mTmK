package com.startapp;

import com.startapp.sdk.ads.video.VideoMode;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class o4 implements Runnable {
    public final /* synthetic */ VideoMode a;

    public o4(VideoMode videoMode) {
        this.a = videoMode;
    }

    @Override // java.lang.Runnable
    public void run() {
        VideoMode videoMode = this.a;
        if (videoMode.L == null) {
            return;
        }
        videoMode.Q = !videoMode.Q;
        videoMode.I();
        VideoMode videoMode2 = this.a;
        videoMode2.a(videoMode2.Q);
    }
}
