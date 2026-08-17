package com.startapp;

import com.startapp.sdk.ads.splash.SplashEventHandler;
import com.startapp.sdk.ads.splash.SplashScreen;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class w3 implements Runnable {
    public final /* synthetic */ SplashScreen a;

    public w3(SplashScreen splashScreen) {
        this.a = splashScreen;
    }

    @Override // java.lang.Runnable
    public void run() {
        SplashScreen splashScreen = this.a;
        SplashEventHandler splashEventHandler = splashScreen.b;
        SplashScreen.SplashStartAppAd splashStartAppAd = splashScreen.h;
        if (splashEventHandler.i != SplashEventHandler.SplashState.DISPLAYED || splashEventHandler.f) {
            return;
        }
        splashStartAppAd.close();
        splashEventHandler.i = SplashEventHandler.SplashState.HIDDEN;
        splashEventHandler.b();
    }
}
