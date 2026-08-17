package com.startapp;

import android.content.Context;
import com.startapp.sdk.ads.banner.BannerMetaData;
import com.startapp.sdk.ads.banner.bannerstandard.BannerStandardAd;
import com.startapp.sdk.adsbase.HtmlAd;
import com.startapp.sdk.adsbase.adlisteners.AdEventListener;
import com.startapp.sdk.adsbase.model.AdPreferences;
import com.startapp.sdk.adsbase.model.GetAdRequest;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class o2 extends gc {
    public int l;

    public o2(Context context, HtmlAd htmlAd, int i, AdPreferences adPreferences, AdEventListener adEventListener) {
        super(context, htmlAd, adPreferences, adEventListener, AdPreferences.Placement.INAPP_BANNER, false);
        this.l = 0;
        this.l = i;
    }

    @Override // com.startapp.gc, com.startapp.k5
    public void a(Boolean bool) {
        super.a(bool);
        a(bool.booleanValue());
    }

    @Override // com.startapp.k5
    public GetAdRequest c() {
        BannerStandardAd bannerStandardAd = (BannerStandardAd) this.b;
        f2 f2Var = new f2();
        a((GetAdRequest) f2Var);
        f2Var.M = bannerStandardAd.p();
        f2Var.N = bannerStandardAd.i();
        f2Var.y0 = this.l;
        f2Var.p0 = BannerMetaData.b.a().f();
        f2Var.S0 = bannerStandardAd.w();
        f2Var.T0 = bannerStandardAd.u();
        f2Var.f(this.a);
        return f2Var;
    }
}
