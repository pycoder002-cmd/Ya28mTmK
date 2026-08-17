package com.startapp;

import com.startapp.sdk.adsbase.StartAppSDKInternal;
import com.startapp.sdk.adsbase.cache.CacheMetaData;
import com.startapp.sdk.adsbase.cache.FailuresHandler;
import java.util.concurrent.TimeUnit;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class w6 extends x6 {
    public final FailuresHandler e;
    public int f;
    public boolean g;

    public w6(b7 b7Var) {
        super(b7Var);
        this.e = CacheMetaData.b().a().c();
        this.f = 0;
        this.g = false;
    }

    @Override // com.startapp.x6
    public boolean a() {
        String str = StartAppSDKInternal.a;
        StartAppSDKInternal startAppSDKInternal = StartAppSDKInternal.c.a;
        if (!((!startAppSDKInternal.f || startAppSDKInternal.g || startAppSDKInternal.i) ? false : true)) {
            return false;
        }
        FailuresHandler failuresHandler = this.e;
        if (!((failuresHandler == null || failuresHandler.a() == null) ? false : true)) {
            return false;
        }
        if (this.g) {
            return this.e.b();
        }
        return true;
    }

    @Override // com.startapp.x6
    public long b() {
        Long l;
        if (this.f >= this.e.a().size() || (l = this.c) == null) {
            return -1L;
        }
        long millis = TimeUnit.SECONDS.toMillis(this.e.a().get(this.f).intValue()) - (System.currentTimeMillis() - l.longValue());
        if (millis >= 0) {
            return millis;
        }
        return 0L;
    }

    @Override // com.startapp.x6
    public void c() {
        if (this.f == this.e.a().size() - 1) {
            this.g = true;
        } else {
            this.f++;
        }
        super.c();
    }

    public void f() {
        e();
        this.f = 0;
        this.g = false;
    }
}
