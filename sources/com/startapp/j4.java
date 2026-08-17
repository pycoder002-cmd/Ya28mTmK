package com.startapp;

import com.startapp.sdk.ads.video.VideoMode;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class j4 implements Runnable {
    public final /* synthetic */ VideoMode a;

    public j4(VideoMode videoMode) {
        this.a = videoMode;
    }

    @Override // java.lang.Runnable
    public void run() {
        int K = this.a.K();
        if (K >= 1000) {
            long j = K % 1000;
            this.a.n0.postDelayed(this, (j != 0 ? j : 1000L) + 50);
        }
    }
}
