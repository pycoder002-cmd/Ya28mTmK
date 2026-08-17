package com.startapp;

import android.os.Handler;
import com.startapp.sdk.ads.video.VideoMode;
import com.startapp.sdk.ads.video.player.NativeVideoPlayer;
import com.startapp.sdk.ads.video.player.VideoPlayerInterface;
import com.startapp.sdk.adsbase.AdsCommonMetaData;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class k4 implements Runnable {
    public boolean a;
    public final int b;
    public final /* synthetic */ VideoMode c;

    public k4(VideoMode videoMode) {
        this.c = videoMode;
        this.b = videoMode.d(AdsCommonMetaData.k().G().k());
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            VideoPlayerInterface videoPlayerInterface = this.c.L;
            int currentPosition = videoPlayerInterface != null ? ((NativeVideoPlayer) videoPlayerInterface).g.getCurrentPosition() : 0;
            int i = currentPosition + 50;
            long c = this.c.c(i);
            long j = 0;
            if (c >= 0 && !this.a) {
                if (c != 0) {
                    if (r8.R < this.c.v().e()) {
                        aa.a(this.c.w, true, "videoApi.setSkipTimer", Long.valueOf(c));
                    }
                }
                this.a = true;
                aa.a(this.c.w, true, "videoApi.setSkipTimer", 0);
            }
            VideoMode videoMode = this.c;
            if (videoMode.f0 && currentPosition >= this.b) {
                videoMode.s();
            }
            int i2 = i / 1000;
            aa.a(this.c.w, true, "videoApi.setVideoCurrentPosition", Integer.valueOf(i2));
            if (i2 < ((NativeVideoPlayer) this.c.L).g.getDuration() / 1000) {
                VideoMode videoMode2 = this.c;
                Handler handler = videoMode2.n0;
                VideoPlayerInterface videoPlayerInterface2 = videoMode2.L;
                if (videoPlayerInterface2 != null) {
                    j = 1000 - (((NativeVideoPlayer) videoPlayerInterface2).g.getCurrentPosition() % 1000);
                }
                handler.postDelayed(this, j);
            }
        } catch (Throwable th) {
            p7.a(this.c.b, th);
        }
    }
}
