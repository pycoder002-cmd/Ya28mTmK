package com.startapp;

import android.content.Context;
import com.startapp.sdk.ads.offerWall.offerWallJson.OfferWall3DAd;
import com.startapp.sdk.adsbase.Ad;
import com.startapp.sdk.adsbase.AdsCommonMetaData;
import com.startapp.sdk.adsbase.adlisteners.AdEventListener;
import com.startapp.sdk.adsbase.model.AdDetails;
import com.startapp.sdk.adsbase.model.AdPreferences;
import com.startapp.sdk.adsbase.model.GetAdRequest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class n3 extends wc {
    public n3(Context context, OfferWall3DAd offerWall3DAd, AdPreferences adPreferences, AdEventListener adEventListener) {
        super(context, offerWall3DAd, adPreferences, adEventListener, AdPreferences.Placement.INAPP_OFFER_WALL);
    }

    @Override // com.startapp.wc
    public void a(Ad ad) {
        OfferWall3DAd offerWall3DAd = (OfferWall3DAd) ad;
        List<AdDetails> g = offerWall3DAd.g();
        g3 a = h3.a.a(offerWall3DAd.h());
        a.getClass();
        a.b = new ArrayList();
        a.c = "";
        if (g != null) {
            Iterator<AdDetails> it = g.iterator();
            while (it.hasNext()) {
                a.a(it.next());
            }
        }
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
