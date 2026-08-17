package com.startapp;

import com.startapp.sdk.ads.splash.SplashScreen;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class u3 implements Runnable {
    public final /* synthetic */ SplashScreen a;

    public u3(SplashScreen splashScreen) {
        this.a = splashScreen;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x002a  */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void run() {
        /*
            r7 = this;
            com.startapp.sdk.ads.splash.SplashScreen r0 = r7.a
            com.startapp.sdk.ads.splash.SplashEventHandler r1 = r0.b
            java.lang.Runnable r2 = r0.k
            com.startapp.sdk.adsbase.cache.CacheKey r0 = r0.d
            boolean r3 = r1.g
            r4 = 1
            r5 = 0
            if (r3 != 0) goto L27
            com.startapp.sdk.ads.splash.SplashEventHandler$SplashState r3 = r1.i
            com.startapp.sdk.ads.splash.SplashEventHandler$SplashState r6 = com.startapp.sdk.ads.splash.SplashEventHandler.SplashState.LOADING
            if (r3 != r6) goto L1e
            r1.d = r5
            com.startapp.sdk.ads.splash.SplashEventHandler$SplashState r0 = com.startapp.sdk.ads.splash.SplashEventHandler.SplashState.DO_NOT_DISPLAY
            r1.i = r0
            r1.c()
            goto L28
        L1e:
            com.startapp.sdk.ads.splash.SplashEventHandler$SplashState r6 = com.startapp.sdk.ads.splash.SplashEventHandler.SplashState.RECEIVED
            if (r3 != r6) goto L27
            r1.h = r4
            r1.a(r2, r0)
        L27:
            r4 = 0
        L28:
            if (r4 == 0) goto L31
            com.startapp.sdk.ads.splash.SplashScreen r0 = r7.a
            r1 = 0
            r0.h = r1
            r0.d = r1
        L31:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.startapp.u3.run():void");
    }
}
