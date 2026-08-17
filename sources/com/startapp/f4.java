package com.startapp;

import android.content.Context;
import com.startapp.sdk.adsbase.AdsCommonMetaData;
import com.startapp.sdk.adsbase.cache.CachedVideoAd;
import com.startapp.w4;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class f4 implements w4.b {
    public final /* synthetic */ w4.b a;
    public final /* synthetic */ CachedVideoAd b;
    public final /* synthetic */ Context c;
    public final /* synthetic */ h4 d;

    public f4(h4 h4Var, w4.b bVar, CachedVideoAd cachedVideoAd, Context context) {
        this.d = h4Var;
        this.a = bVar;
        this.b = cachedVideoAd;
        this.c = context;
    }

    @Override // com.startapp.w4.b
    public void a(String str) {
        w4.b bVar = this.a;
        if (bVar != null) {
            bVar.a(str);
        }
        if (str != null) {
            this.b.a(System.currentTimeMillis());
            this.b.a(str);
            h4 h4Var = this.d;
            Context context = this.c;
            CachedVideoAd cachedVideoAd = this.b;
            if (h4Var.b.contains(cachedVideoAd)) {
                h4Var.b.remove(cachedVideoAd);
            }
            h4Var.a(AdsCommonMetaData.h.G().b() - 1);
            h4Var.b.add(cachedVideoAd);
            h9.a(context, "CachedAds", h4Var.b);
        }
    }
}
