package com.startapp;

import android.content.Context;
import com.startapp.sdk.adsbase.cache.CacheKey;
import com.startapp.sdk.adsbase.cache.DiskAdCacheManager$DiskCacheKey;
import com.startapp.sdk.adsbase.cache.DiskAdCacheManager$DiskCachedAd;
import com.startapp.sdk.adsbase.model.AdPreferences;
import java.util.Map;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class t6 implements Runnable {
    public final /* synthetic */ Context a;
    public final /* synthetic */ v6 b;

    public t6(v6 v6Var, Context context) {
        this.b = v6Var;
        this.a = context;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            h9.a(this.a, d.d());
            h9.a(this.a, d.c());
            CacheKey cacheKey = null;
            for (Map.Entry<CacheKey, b7> entry : this.b.b.entrySet()) {
                CacheKey key = entry.getKey();
                if (key.a() == AdPreferences.Placement.INAPP_SPLASH) {
                    cacheKey = key;
                } else {
                    b7 value = entry.getValue();
                    Context context = this.a;
                    AdPreferences.Placement a = key.a();
                    AdPreferences adPreferences = value.d;
                    String b = this.b.b(key);
                    int i = value.m;
                    DiskAdCacheManager$DiskCacheKey diskAdCacheManager$DiskCacheKey = new DiskAdCacheManager$DiskCacheKey(a, adPreferences);
                    diskAdCacheManager$DiskCacheKey.a(i);
                    h9.a(context, d.d(), b, diskAdCacheManager$DiskCacheKey);
                    h9.a(this.a, d.c(), this.b.b(key), new DiskAdCacheManager$DiskCachedAd(value.e));
                }
            }
            if (cacheKey != null) {
                this.b.b.remove(cacheKey);
            }
        } catch (Throwable th) {
            p7.a(this.a, th);
        }
    }
}
