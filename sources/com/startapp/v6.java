package com.startapp;

import android.app.Activity;
import android.content.Context;
import com.startapp.b7;
import com.startapp.sdk.adsbase.Ad;
import com.startapp.sdk.adsbase.AdsCommonMetaData;
import com.startapp.sdk.adsbase.AdsConstants;
import com.startapp.sdk.adsbase.StartAppAd;
import com.startapp.sdk.adsbase.StartAppSDKInternal;
import com.startapp.sdk.adsbase.adlisteners.AdEventListener;
import com.startapp.sdk.adsbase.cache.CacheKey;
import com.startapp.sdk.adsbase.cache.CacheMetaData;
import com.startapp.sdk.adsbase.model.AdPreferences;
import java.util.Iterator;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import kotlin.jvm.internal.LongCompanionObject;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class v6 {
    public static v6 a = new v6();
    public final Map<CacheKey, b7> b = new ConcurrentHashMap();
    public Map<String, String> c = new WeakHashMap();
    public boolean d = false;
    public boolean e = false;
    public Queue<a> f = new ConcurrentLinkedQueue();
    public b7.b g;
    public Context h;

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class a {
        public StartAppAd a;
        public AdPreferences.Placement b;
        public AdPreferences c;
        public AdEventListener d;

        public a(v6 v6Var, StartAppAd startAppAd, AdPreferences.Placement placement, AdPreferences adPreferences, AdEventListener adEventListener) {
            this.a = startAppAd;
            this.b = placement;
            this.c = adPreferences;
            this.d = adEventListener;
        }
    }

    public n5 a(CacheKey cacheKey) {
        b7 b7Var = cacheKey != null ? this.b.get(cacheKey) : null;
        if (b7Var != null) {
            return b7Var.e;
        }
        return null;
    }

    public CacheKey a(Context context, StartAppAd startAppAd, StartAppAd.AdMode adMode, AdPreferences adPreferences, AdEventListener adEventListener) {
        AdPreferences.Placement placement;
        if (adPreferences == null) {
            adPreferences = new AdPreferences();
        }
        AdPreferences adPreferences2 = adPreferences;
        int ordinal = adMode.ordinal();
        if (ordinal != 0) {
            if (ordinal != 1) {
                if (ordinal == 2) {
                    Map<Activity, Integer> map = aa.a;
                    placement = AdPreferences.Placement.INAPP_OFFER_WALL;
                } else if (ordinal != 3 && ordinal != 4 && ordinal != 5) {
                    placement = AdPreferences.Placement.INAPP_FULL_SCREEN;
                }
            }
            placement = AdPreferences.Placement.INAPP_OVERLAY;
        } else {
            Map<Activity, Integer> map2 = aa.a;
            if (new Random().nextInt(100) < AdsCommonMetaData.h.i()) {
                placement = ((new Random().nextInt(100) < AdsCommonMetaData.h.j() || adPreferences2.isForceFullpage()) && !adPreferences2.isForceOverlay()) ? AdPreferences.Placement.INAPP_FULL_SCREEN : AdPreferences.Placement.INAPP_OVERLAY;
            } else {
                placement = AdPreferences.Placement.INAPP_FULL_SCREEN;
            }
        }
        AdPreferences.Placement placement2 = placement;
        if (adMode.equals(StartAppAd.AdMode.REWARDED_VIDEO)) {
            adPreferences2.setType(Ad.AdType.REWARDED_VIDEO);
        } else if (adMode.equals(StartAppAd.AdMode.VIDEO)) {
            adPreferences2.setType(Ad.AdType.VIDEO);
        }
        return a(context, startAppAd, placement2, adPreferences2, adEventListener, false, 0);
    }

    public CacheKey a(Context context, StartAppAd startAppAd, AdPreferences.Placement placement, AdPreferences adPreferences, AdEventListener adEventListener) {
        return a(context, null, placement, adPreferences, null, false, 0);
    }

    public CacheKey a(Context context, StartAppAd startAppAd, AdPreferences.Placement placement, AdPreferences adPreferences, AdEventListener adEventListener, boolean z, int i) {
        b7 b7Var;
        this.h = y8.b(context);
        if (adPreferences == null) {
            adPreferences = new AdPreferences();
        }
        AdPreferences adPreferences2 = adPreferences;
        CacheKey cacheKey = new CacheKey(placement, adPreferences2);
        if (this.e && !z) {
            this.f.add(new a(this, startAppAd, placement, adPreferences2, adEventListener));
            return cacheKey;
        }
        AdPreferences adPreferences3 = new AdPreferences(adPreferences2);
        synchronized (this.b) {
            b7Var = this.b.get(cacheKey);
            if (b7Var == null) {
                if (placement.ordinal() != 3) {
                    b7Var = new b7(this.h, placement, adPreferences3);
                } else {
                    b7Var = new b7(this.h, placement, adPreferences3);
                    b7Var.n = false;
                }
                if (this.g == null) {
                    this.g = new u6(this);
                }
                b7Var.p = this.g;
                if (z) {
                    b7Var.h = b(cacheKey);
                    b7Var.i = true;
                    b7Var.m = i;
                }
                a(cacheKey, b7Var, this.h);
            } else {
                b7Var.d = adPreferences3;
            }
        }
        b7Var.a(startAppAd, adEventListener, false, true);
        return cacheKey;
    }

    public String a(StartAppAd.AdMode adMode) {
        if (adMode == null) {
            return null;
        }
        return "autoLoadNotShownAdPrefix" + adMode.name();
    }

    public final void a(CacheKey cacheKey, b7 b7Var, Context context) {
        synchronized (this.b) {
            int d = CacheMetaData.a.a().d();
            if (d != 0 && this.b.size() >= d) {
                long j = LongCompanionObject.MAX_VALUE;
                CacheKey cacheKey2 = null;
                for (CacheKey cacheKey3 : this.b.keySet()) {
                    b7 b7Var2 = this.b.get(cacheKey3);
                    if (b7Var2.a == b7Var.a) {
                        long j2 = b7Var2.g;
                        if (j2 < j) {
                            cacheKey2 = cacheKey3;
                            j = j2;
                        }
                    }
                }
                if (cacheKey2 != null) {
                    this.b.remove(cacheKey2);
                }
            }
            this.b.put(cacheKey, b7Var);
            if (Math.random() * 100.0d < CacheMetaData.a.c()) {
                p7 p7Var = new p7(q7.b);
                p7Var.d = "Cache Size";
                p7Var.e = String.valueOf(this.b.size());
                p7Var.a(context);
            }
        }
    }

    public boolean a(AdPreferences.Placement placement) {
        int ordinal = placement.ordinal();
        if (ordinal == 3) {
            String str = StartAppSDKInternal.a;
            return (StartAppSDKInternal.c.a.y ^ true) && !AdsCommonMetaData.h.L();
        }
        if (ordinal != 7) {
            return true;
        }
        String str2 = StartAppSDKInternal.a;
        return StartAppSDKInternal.c.a.w && !AdsCommonMetaData.h.K();
    }

    public String b(CacheKey cacheKey) {
        return String.valueOf(cacheKey.hashCode()).replace('-', '_');
    }

    public void b(AdPreferences.Placement placement) {
        synchronized (this.b) {
            Iterator<Map.Entry<CacheKey, b7>> it = this.b.entrySet().iterator();
            while (it.hasNext()) {
                if (it.next().getKey().a() == placement) {
                    it.remove();
                }
            }
        }
    }

    public n5 c(CacheKey cacheKey) {
        b7 b7Var;
        if (cacheKey == null || (b7Var = this.b.get(cacheKey)) == null || !b7Var.c()) {
            return null;
        }
        n5 n5Var = b7Var.e;
        b7Var.m = 0;
        b7Var.o = null;
        if (!AdsConstants.g.booleanValue() && b7Var.n) {
            b7Var.a(null, null, true, true);
        } else if (!b7Var.n) {
            b7.b bVar = b7Var.p;
            if (bVar != null) {
                ((u6) bVar).a(b7Var);
            }
            y6 y6Var = b7Var.j;
            if (y6Var != null) {
                y6Var.e();
            }
        }
        return n5Var;
    }
}
