package com.startapp;

import com.startapp.sdk.ads.video.VideoMode;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class q4 implements Runnable {
    public final /* synthetic */ VideoMode a;

    public q4(VideoMode videoMode) {
        this.a = videoMode;
    }

    @Override // java.lang.Runnable
    public void run() {
        VideoMode videoMode = this.a;
        if (videoMode.L == null) {
            return;
        }
        videoMode.F++;
        videoMode.M.setVisibility(0);
        VideoMode videoMode2 = this.a;
        videoMode2.U = false;
        videoMode2.R = 0;
        videoMode2.getClass();
        aa.a(videoMode2.w, true, "videoApi.setVideoCurrentPosition", 0);
        aa.a(videoMode2.w, true, "videoApi.setSkipTimer", 0);
        this.a.J();
    }
}
