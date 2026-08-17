package com.startapp;

import android.app.Activity;
import android.content.Context;
import com.startapp.p5;
import com.startapp.sdk.adsbase.AdsCommonMetaData;
import com.startapp.sdk.adsbase.StartAppAd;
import com.startapp.sdk.adsbase.adlisteners.AdEventListener;
import com.startapp.sdk.adsbase.cache.CacheMetaData;
import com.startapp.sdk.adsbase.model.AdPreferences;
import com.startapp.sdk.adsbase.remoteconfig.MetaData;
import com.startapp.sdk.adsbase.remoteconfig.MetaDataRequest;
import com.startapp.sdk.components.ComponentLocator;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class s6 implements t8 {
    public final /* synthetic */ Context a;
    public final /* synthetic */ p5 b;
    public final /* synthetic */ v6 c;

    public s6(v6 v6Var, Context context, p5 p5Var) {
        this.c = v6Var;
        this.a = context;
        this.b = p5Var;
    }

    @Override // com.startapp.t8
    public void a(MetaDataRequest.RequestReason requestReason) {
    }

    @Override // com.startapp.t8
    public void a(MetaDataRequest.RequestReason requestReason, boolean z) {
        Set<StartAppAd.AdMode> b;
        if (z && (b = CacheMetaData.a.a().b()) != null) {
            v6 v6Var = this.c;
            p5 d = ComponentLocator.a(v6Var.h).d();
            Iterator<StartAppAd.AdMode> it = b.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                if (d.getInt(v6Var.a(it.next()), 0) >= MetaData.h.G()) {
                    it.remove();
                }
            }
            Map<Activity, Integer> map = aa.a;
            for (StartAppAd.AdMode adMode : b) {
                int i = AdsCommonMetaData.h.i();
                StartAppAd.AdMode adMode2 = StartAppAd.AdMode.FULLPAGE;
                if (adMode != adMode2) {
                    StartAppAd.AdMode adMode3 = StartAppAd.AdMode.OFFERWALL;
                    if (adMode != adMode3) {
                        this.c.a(this.a, (StartAppAd) null, adMode, new AdPreferences(), (AdEventListener) null);
                    } else if (i < 100) {
                        this.c.a(this.a, (StartAppAd) null, adMode3, new AdPreferences(), (AdEventListener) null);
                    }
                } else if (i > 0) {
                    this.c.a(this.a, (StartAppAd) null, adMode2, new AdPreferences(), (AdEventListener) null);
                }
                String a = this.c.a(adMode);
                if (a != null) {
                    int i2 = this.b.getInt(a, 0);
                    p5.a edit = this.b.edit();
                    int i3 = i2 + 1;
                    edit.a(a, (String) Integer.valueOf(i3));
                    edit.a.putInt(a, i3);
                    edit.apply();
                }
            }
        }
    }
}
