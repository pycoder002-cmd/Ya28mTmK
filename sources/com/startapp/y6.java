package com.startapp;

import com.startapp.sdk.adsbase.StartAppSDKInternal;
import com.startapp.sdk.adsbase.cache.CacheMetaData;
import com.startapp.sdk.adsbase.model.AdPreferences;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class y6 extends x6 {
    public y6(b7 b7Var) {
        super(b7Var);
    }

    @Override // com.startapp.x6
    public boolean a() {
        String str = StartAppSDKInternal.a;
        StartAppSDKInternal startAppSDKInternal = StartAppSDKInternal.c.a;
        return startAppSDKInternal.f && !startAppSDKInternal.i && (!startAppSDKInternal.g || (this.a.a == AdPreferences.Placement.INAPP_RETURN && CacheMetaData.a.a().g()));
    }

    @Override // com.startapp.x6
    public long b() {
        n5 n5Var = this.a.e;
        if (n5Var == null) {
            return -1L;
        }
        Long c = n5Var.c();
        Long b = n5Var.b();
        if (c == null || b == null) {
            return -1L;
        }
        long longValue = c.longValue() - (System.currentTimeMillis() - b.longValue());
        if (longValue >= 0) {
            return longValue;
        }
        return 0L;
    }
}
