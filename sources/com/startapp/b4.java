package com.startapp;

import com.startapp.sdk.ads.video.player.VideoPlayerInterface;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class b4 implements Runnable {
    public final /* synthetic */ int a;
    public final /* synthetic */ c4 b;

    public b4(c4 c4Var, int i) {
        this.b = c4Var;
        this.a = i;
    }

    @Override // java.lang.Runnable
    public void run() {
        VideoPlayerInterface.a aVar = this.b.b;
        if (aVar != null) {
            ((t4) aVar).a(this.a);
        }
    }
}
