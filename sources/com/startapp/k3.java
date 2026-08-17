package com.startapp;

import android.content.Context;
import com.startapp.sdk.ads.nativead.NativeAdPreferences;
import com.startapp.sdk.adsbase.Ad;
import com.startapp.sdk.adsbase.adlisteners.AdEventListener;
import com.startapp.sdk.adsbase.model.AdPreferences;
import com.startapp.sdk.adsbase.model.GetAdRequest;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class k3 extends wc {
    public NativeAdPreferences i;

    public k3(Context context, Ad ad, AdPreferences adPreferences, AdEventListener adEventListener, NativeAdPreferences nativeAdPreferences) {
        super(context, ad, adPreferences, adEventListener, AdPreferences.Placement.INAPP_NATIVE);
        this.i = nativeAdPreferences;
    }

    @Override // com.startapp.wc
    public void a(Ad ad) {
    }

    @Override // com.startapp.k5
    public GetAdRequest c() {
        GetAdRequest c = super.c();
        if (c == null) {
            return null;
        }
        c.p0 = this.i.getAdsNumber();
        if (this.i.getImageSize() != null) {
            c.M = this.i.getImageSize().getWidth();
            c.N = this.i.getImageSize().getHeight();
        } else {
            int primaryImageSize = this.i.getPrimaryImageSize();
            if (primaryImageSize == -1) {
                primaryImageSize = 2;
            }
            c.J0 = Integer.toString(primaryImageSize);
            int secondaryImageSize = this.i.getSecondaryImageSize();
            c.K0 = Integer.toString(secondaryImageSize != -1 ? secondaryImageSize : 2);
        }
        if (this.i.isContentAd()) {
            c.L0 = this.i.isContentAd();
        }
        return c;
    }
}
