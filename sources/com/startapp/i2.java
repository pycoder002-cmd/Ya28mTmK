package com.startapp;

import android.content.Context;
import android.view.View;
import com.startapp.sdk.ads.banner.BannerListener;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public final class i2 implements Runnable {
    public final /* synthetic */ BannerListener a;
    public final /* synthetic */ View b;
    public final /* synthetic */ Context c;

    public i2(BannerListener bannerListener, View view, Context context) {
        this.a = bannerListener;
        this.b = view;
        this.c = context;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            this.a.onFailedToReceiveAd(this.b);
        } catch (Throwable th) {
            aa.a(this.c, this.a, th);
        }
    }
}
