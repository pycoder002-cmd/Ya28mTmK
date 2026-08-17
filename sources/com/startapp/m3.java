package com.startapp;

import android.content.Context;
import com.startapp.sdk.ads.offerWall.offerWallHtml.OfferWallAd;
import com.startapp.sdk.adsbase.AdsCommonMetaData;
import com.startapp.sdk.adsbase.adlisteners.AdEventListener;
import com.startapp.sdk.adsbase.model.AdPreferences;
import com.startapp.sdk.adsbase.model.GetAdRequest;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class m3 extends gc {
    public m3(Context context, OfferWallAd offerWallAd, AdPreferences adPreferences, AdEventListener adEventListener) {
        super(context, offerWallAd, adPreferences, adEventListener, AdPreferences.Placement.INAPP_OFFER_WALL, true);
    }

    @Override // com.startapp.gc, com.startapp.k5
    public void a(Boolean bool) {
        super.a(bool);
        a(bool.booleanValue());
    }

    @Override // com.startapp.k5
    public GetAdRequest c() {
        GetAdRequest c = super.c();
        if (c == null) {
            return null;
        }
        c.p0 = AdsCommonMetaData.h.t();
        return c;
    }
}
