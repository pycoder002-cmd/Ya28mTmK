package com.startapp;

import android.content.Context;
import com.startapp.sdk.ads.banner.BannerMetaData;
import com.startapp.sdk.ads.banner.banner3d.Banner3DAd;
import com.startapp.sdk.adsbase.Ad;
import com.startapp.sdk.adsbase.adlisteners.AdEventListener;
import com.startapp.sdk.adsbase.model.AdPreferences;
import com.startapp.sdk.adsbase.model.GetAdRequest;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class n2 extends wc {
    public int i;

    public n2(Context context, Banner3DAd banner3DAd, int i, AdPreferences adPreferences, AdEventListener adEventListener) {
        super(context, banner3DAd, adPreferences, adEventListener, AdPreferences.Placement.INAPP_BANNER);
        this.i = 0;
        this.i = i;
    }

    @Override // com.startapp.wc
    public void a(Ad ad) {
    }

    @Override // com.startapp.k5
    public GetAdRequest c() {
        Banner3DAd banner3DAd = (Banner3DAd) this.b;
        f2 f2Var = new f2();
        a((GetAdRequest) f2Var);
        f2Var.p0 = BannerMetaData.b.a().a();
        f2Var.y0 = this.i;
        f2Var.S0 = banner3DAd.i();
        f2Var.f(this.a);
        return f2Var;
    }
}
