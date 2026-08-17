package com.startapp;

import com.startapp.sdk.ads.splash.SplashEventHandler;
import com.startapp.sdk.ads.splash.SplashScreen;
import com.startapp.sdk.adsbase.cache.CacheKey;
import com.startapp.sdk.adsbase.remoteconfig.MetaData;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class v3 implements Runnable {
    public final /* synthetic */ SplashScreen a;

    public v3(SplashScreen splashScreen) {
        this.a = splashScreen;
    }

    @Override // java.lang.Runnable
    public void run() {
        SplashScreen splashScreen = this.a;
        SplashEventHandler splashEventHandler = splashScreen.b;
        Runnable runnable = splashScreen.k;
        CacheKey cacheKey = splashScreen.d;
        splashEventHandler.c = true;
        if (splashEventHandler.i == SplashEventHandler.SplashState.DO_NOT_DISPLAY) {
            splashEventHandler.c();
            return;
        }
        r3 r3Var = new r3(splashEventHandler, runnable, cacheKey);
        Object obj = MetaData.a;
        synchronized (MetaData.a) {
            if (MetaData.h.k) {
                r3Var.a(null, false);
            } else {
                MetaData.h.a(r3Var);
            }
        }
    }
}
