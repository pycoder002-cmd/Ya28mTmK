package com.startapp;

import android.content.Context;
import com.startapp.sdk.adsbase.adlisteners.VideoListener;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public final class h6 implements Runnable {
    public final /* synthetic */ VideoListener a;
    public final /* synthetic */ Context b;

    public h6(VideoListener videoListener, Context context) {
        this.a = videoListener;
        this.b = context;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            this.a.onVideoCompleted();
        } catch (Throwable th) {
            aa.a(this.b, this.a, th);
        }
    }
}
