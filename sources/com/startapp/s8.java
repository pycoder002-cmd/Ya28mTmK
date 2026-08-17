package com.startapp;

import android.app.Activity;
import android.content.Context;
import com.startapp.sdk.ads.banner.BannerMetaData;
import com.startapp.sdk.ads.splash.SplashMetaData;
import com.startapp.sdk.adsbase.AdsCommonMetaData;
import com.startapp.sdk.adsbase.AdsConstants;
import com.startapp.sdk.adsbase.adinformation.AdInformationMetaData;
import com.startapp.sdk.adsbase.cache.CacheMetaData;
import com.startapp.sdk.adsbase.model.AdPreferences;
import com.startapp.sdk.adsbase.remoteconfig.MetaData;
import com.startapp.sdk.adsbase.remoteconfig.MetaDataRequest;
import com.startapp.sdk.components.ComponentLocator;
import com.startapp.za;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class s8 {
    public final Context a;
    public final AdPreferences b;
    public final MetaDataRequest.RequestReason c;
    public MetaData d = null;
    public BannerMetaData e = null;
    public SplashMetaData f = null;
    public CacheMetaData g = null;
    public AdInformationMetaData h = null;
    public AdsCommonMetaData i = null;
    public boolean j = false;
    public boolean k = false;

    public s8(Context context, AdPreferences adPreferences, MetaDataRequest.RequestReason requestReason) {
        this.a = context;
        this.b = adPreferences;
        this.c = requestReason;
    }

    public static za.a a(Context context, MetaDataRequest metaDataRequest) {
        za.a aVar;
        List<String> list = MetaData.h.metaDataHosts;
        if (list == null || list.size() < 1) {
            list = MetaData.d;
        }
        for (String str : Collections.unmodifiableList(list)) {
            n7 j = ComponentLocator.a(context).j();
            String str2 = str + AdsConstants.e;
            j.getClass();
            try {
                aVar = j.a(str2, metaDataRequest, null);
            } catch (Throwable th) {
                p7.a(j.a, th);
                aVar = null;
            }
            if (aVar != null) {
                return aVar;
            }
            if (!ComponentLocator.a(context).e().a()) {
                break;
            }
        }
        return null;
    }

    public Boolean a() {
        String str;
        try {
            MetaDataRequest metaDataRequest = new MetaDataRequest(this.a, ComponentLocator.a(this.a).d(), this.c);
            metaDataRequest.a(this.a, this.b);
            za.a a = a(this.a, metaDataRequest);
            if (a != null && (str = a.a) != null) {
                MetaData metaData = (MetaData) aa.a(str, MetaData.class);
                this.d = metaData;
                if (metaData.g() != null) {
                    i5 c = ComponentLocator.a(this.a).c();
                    String g = this.d.g();
                    c.getClass();
                    if (g != null) {
                        g = g.trim();
                    }
                    synchronized (c.a) {
                        c.b.edit().putString("31721150b470a3b9", g).commit();
                    }
                }
                Map<Activity, Integer> map = aa.a;
                this.i = (AdsCommonMetaData) aa.a(str, AdsCommonMetaData.class);
                this.e = (BannerMetaData) aa.a(str, BannerMetaData.class);
                this.f = (SplashMetaData) aa.a(str, SplashMetaData.class);
                this.g = (CacheMetaData) aa.a(str, CacheMetaData.class);
                this.h = (AdInformationMetaData) aa.a(str, AdInformationMetaData.class);
                Object obj = MetaData.a;
                synchronized (MetaData.a) {
                    if (!this.j && this.d != null && this.a != null) {
                        try {
                            if (!aa.a(AdsCommonMetaData.h, this.i)) {
                                this.k = true;
                                AdsCommonMetaData.a(this.a, this.i);
                            }
                        } catch (Throwable th) {
                            p7.a(this.a, th);
                        }
                        Map<Activity, Integer> map2 = aa.a;
                        try {
                            if (!aa.a(BannerMetaData.b, this.e)) {
                                this.k = true;
                                BannerMetaData.a(this.a, this.e);
                            }
                        } catch (Throwable th2) {
                            p7.a(this.a, th2);
                        }
                        Map<Activity, Integer> map3 = aa.a;
                        this.f.a().setDefaults(this.a);
                        try {
                            if (!aa.a(SplashMetaData.a, this.f)) {
                                this.k = true;
                                SplashMetaData.a(this.a, this.f);
                            }
                        } catch (Throwable th3) {
                            p7.a(this.a, th3);
                        }
                        Map<Activity, Integer> map4 = aa.a;
                        try {
                            if (!aa.a(CacheMetaData.a, this.g)) {
                                this.k = true;
                                CacheMetaData.a(this.a, this.g);
                            }
                        } catch (Throwable th4) {
                            p7.a(this.a, th4);
                        }
                        Map<Activity, Integer> map5 = aa.a;
                        try {
                            if (!aa.a(AdInformationMetaData.a, this.h)) {
                                this.k = true;
                                AdInformationMetaData.a(this.a, this.h);
                            }
                        } catch (Throwable th5) {
                            p7.a(this.a, th5);
                        }
                        try {
                            MetaData.a(this.a, this.d.e());
                        } catch (Exception unused) {
                        }
                    }
                }
                return Boolean.TRUE;
            }
            return Boolean.FALSE;
        } catch (Throwable th6) {
            p7.a(this.a, th6);
            return Boolean.FALSE;
        }
    }

    public void a(Boolean bool) {
        MetaData metaData;
        Context context;
        Object obj = MetaData.a;
        synchronized (MetaData.a) {
            if (!this.j) {
                if (!bool.booleanValue() || (metaData = this.d) == null || (context = this.a) == null) {
                    MetaData.a(this.c);
                } else {
                    try {
                        MetaData.a(context, metaData, this.c, this.k);
                    } catch (Throwable th) {
                        p7.a(this.a, th);
                    }
                }
            }
        }
    }
}
