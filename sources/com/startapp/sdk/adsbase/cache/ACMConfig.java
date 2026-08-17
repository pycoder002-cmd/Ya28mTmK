package com.startapp.sdk.adsbase.cache;

import android.app.Activity;
import com.startapp.aa;
import com.startapp.f;
import com.startapp.sdk.adsbase.StartAppAd;
import java.io.Serializable;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class ACMConfig implements Serializable {
    private static final long serialVersionUID = 1;
    private long adCacheTTL = 3600;
    private long returnAdCacheTTL = 3600;

    @f(type = EnumSet.class, value = StartAppAd.AdMode.class)
    private Set<StartAppAd.AdMode> autoLoad = EnumSet.of(StartAppAd.AdMode.FULLPAGE);
    private boolean localCache = true;
    private boolean returnAdShouldLoadInBg = true;

    @f(complex = true)
    private FailuresHandler failuresHandler = new FailuresHandler();
    private int maxCacheSize = 7;

    public long a() {
        return TimeUnit.SECONDS.toMillis(this.adCacheTTL);
    }

    public Set<StartAppAd.AdMode> b() {
        return this.autoLoad;
    }

    public FailuresHandler c() {
        return this.failuresHandler;
    }

    public int d() {
        return this.maxCacheSize;
    }

    public long e() {
        return TimeUnit.SECONDS.toMillis(this.returnAdCacheTTL);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || ACMConfig.class != obj.getClass()) {
            return false;
        }
        ACMConfig aCMConfig = (ACMConfig) obj;
        return this.adCacheTTL == aCMConfig.adCacheTTL && this.returnAdCacheTTL == aCMConfig.returnAdCacheTTL && this.localCache == aCMConfig.localCache && this.returnAdShouldLoadInBg == aCMConfig.returnAdShouldLoadInBg && this.maxCacheSize == aCMConfig.maxCacheSize && aa.a(this.autoLoad, aCMConfig.autoLoad) && aa.a(this.failuresHandler, aCMConfig.failuresHandler);
    }

    public boolean f() {
        return this.localCache;
    }

    public boolean g() {
        return this.returnAdShouldLoadInBg;
    }

    public int hashCode() {
        Object[] objArr = {Long.valueOf(this.adCacheTTL), Long.valueOf(this.returnAdCacheTTL), this.autoLoad, Boolean.valueOf(this.localCache), Boolean.valueOf(this.returnAdShouldLoadInBg), this.failuresHandler, Integer.valueOf(this.maxCacheSize)};
        Map<Activity, Integer> map = aa.a;
        return Arrays.deepHashCode(objArr);
    }
}
