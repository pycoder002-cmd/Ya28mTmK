package com.startapp;

import com.startapp.sdk.ads.video.VideoMode;
import com.startapp.sdk.ads.video.player.NativeVideoPlayer;
import com.startapp.sdk.ads.video.player.VideoPlayerInterface;
import com.startapp.sdk.adsbase.AdsCommonMetaData;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class t4 implements VideoPlayerInterface.a {
    public final /* synthetic */ VideoMode a;

    public t4(VideoMode videoMode) {
        this.a = videoMode;
    }

    public void a(int i) {
        VideoPlayerInterface videoPlayerInterface;
        VideoMode videoMode;
        VideoPlayerInterface videoPlayerInterface2;
        VideoMode videoMode2 = this.a;
        if (!videoMode2.f0 || !videoMode2.g0 || (videoPlayerInterface = videoMode2.L) == null || ((NativeVideoPlayer) videoPlayerInterface).g.getDuration() == 0) {
            return;
        }
        VideoMode videoMode3 = this.a;
        videoMode3.e0 = i;
        int currentPosition = (((NativeVideoPlayer) videoMode3.L).g.getCurrentPosition() * 100) / ((NativeVideoPlayer) this.a.L).g.getDuration();
        if (!this.a.C()) {
            int i2 = this.a.e0;
            if (i2 >= 100 || i2 - currentPosition > AdsCommonMetaData.h.G().i() || (videoPlayerInterface2 = (videoMode = this.a).L) == null) {
                return;
            }
            ((NativeVideoPlayer) videoPlayerInterface2).g.pause();
            videoMode.L();
            return;
        }
        VideoMode videoMode4 = this.a;
        if (!videoMode4.h0 && videoMode4.A()) {
            this.a.M();
            return;
        }
        int i3 = this.a.e0;
        if (i3 == 100 || i3 - currentPosition > AdsCommonMetaData.h.G().h()) {
            VideoMode videoMode5 = this.a;
            VideoPlayerInterface videoPlayerInterface3 = videoMode5.L;
            if (videoPlayerInterface3 != null) {
                ((NativeVideoPlayer) videoPlayerInterface3).g.start();
                videoMode5.w.setBackgroundColor(33554431);
            }
            videoMode5.N();
        }
    }
}
