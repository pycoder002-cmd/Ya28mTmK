package com.startapp;

import android.os.Handler;
import android.os.Looper;
import com.startapp.sdk.ads.splash.SplashEventHandler;
import com.startapp.sdk.adsbase.cache.CacheKey;
import com.startapp.sdk.adsbase.remoteconfig.MetaDataRequest;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class r3 implements t8 {
    public final /* synthetic */ Runnable a;
    public final /* synthetic */ CacheKey b;
    public final /* synthetic */ SplashEventHandler c;

    public r3(SplashEventHandler splashEventHandler, Runnable runnable, CacheKey cacheKey) {
        this.c = splashEventHandler;
        this.a = runnable;
        this.b = cacheKey;
    }

    @Override // com.startapp.t8
    public void a(MetaDataRequest.RequestReason requestReason) {
        SplashEventHandler splashEventHandler = this.c;
        Runnable runnable = this.a;
        CacheKey cacheKey = this.b;
        splashEventHandler.getClass();
        new Handler(Looper.getMainLooper()).post(new q3(splashEventHandler, runnable, cacheKey));
    }

    @Override // com.startapp.t8
    public void a(MetaDataRequest.RequestReason requestReason, boolean z) {
        SplashEventHandler splashEventHandler = this.c;
        Runnable runnable = this.a;
        CacheKey cacheKey = this.b;
        splashEventHandler.getClass();
        new Handler(Looper.getMainLooper()).post(new q3(splashEventHandler, runnable, cacheKey));
    }
}
