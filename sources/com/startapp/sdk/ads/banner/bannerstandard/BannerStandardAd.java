package com.startapp.sdk.ads.banner.bannerstandard;

import android.content.Context;
import com.startapp.o2;
import com.startapp.sdk.adsbase.HtmlAd;
import com.startapp.sdk.adsbase.adlisteners.AdEventListener;
import com.startapp.sdk.adsbase.model.AdPreferences;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class BannerStandardAd extends HtmlAd {
    private static final long serialVersionUID = 1;
    private int bannerType;
    private boolean fixedSize;
    private int offset;

    public BannerStandardAd(Context context, int i) {
        super(context, AdPreferences.Placement.INAPP_BANNER);
        this.offset = 0;
        this.offset = i;
    }

    @Override // com.startapp.sdk.adsbase.Ad
    public void a(AdPreferences adPreferences, AdEventListener adEventListener) {
        new o2(this.b, this, this.offset, adPreferences, adEventListener).b();
        this.offset++;
    }

    public void b(boolean z) {
        this.fixedSize = z;
    }

    public void c(int i) {
        this.bannerType = i;
    }

    public int u() {
        return this.bannerType;
    }

    public int v() {
        return this.offset;
    }

    public boolean w() {
        return this.fixedSize;
    }
}
