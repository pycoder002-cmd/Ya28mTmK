package com.startapp;

import com.startapp.sdk.ads.splash.SplashEventHandler;
import com.startapp.sdk.adsbase.cache.CacheKey;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class q3 implements Runnable {
    public final /* synthetic */ Runnable a;
    public final /* synthetic */ CacheKey b;
    public final /* synthetic */ SplashEventHandler c;

    public q3(SplashEventHandler splashEventHandler, Runnable runnable, CacheKey cacheKey) {
        this.c = splashEventHandler;
        this.a = runnable;
        this.b = cacheKey;
    }

    @Override // java.lang.Runnable
    public void run() {
        SplashEventHandler splashEventHandler = this.c;
        splashEventHandler.h = true;
        if (splashEventHandler.i != SplashEventHandler.SplashState.DO_NOT_DISPLAY) {
            splashEventHandler.a(this.a, this.b);
        }
    }
}
