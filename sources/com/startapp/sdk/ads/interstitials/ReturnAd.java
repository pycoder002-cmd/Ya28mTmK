package com.startapp.sdk.ads.interstitials;

import android.content.Context;
import com.startapp.sdk.adsbase.adlisteners.AdEventListener;
import com.startapp.sdk.adsbase.cache.CacheMetaData;
import com.startapp.sdk.adsbase.model.AdPreferences;
import com.startapp.x2;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class ReturnAd extends InterstitialAd {
    private static final long serialVersionUID = 1;

    public ReturnAd(Context context) {
        super(context, AdPreferences.Placement.INAPP_RETURN);
    }

    @Override // com.startapp.sdk.adsbase.Ad
    public void a(AdPreferences adPreferences, AdEventListener adEventListener) {
        new x2(this.b, this, adPreferences, adEventListener).b();
    }

    @Override // com.startapp.sdk.adsbase.Ad
    public long e() {
        return CacheMetaData.a.a().e();
    }
}
