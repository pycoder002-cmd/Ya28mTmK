package com.startapp.sdk.ads.banner.banner3d;

import android.content.Context;
import com.startapp.n2;
import com.startapp.sdk.adsbase.JsonAd;
import com.startapp.sdk.adsbase.adlisteners.AdEventListener;
import com.startapp.sdk.adsbase.model.AdPreferences;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class Banner3DAd extends JsonAd {
    private static final long serialVersionUID = 1;
    private boolean fixedSize;
    private int offset;

    public Banner3DAd(Context context, int i) {
        super(context, AdPreferences.Placement.INAPP_BANNER);
        this.offset = i;
    }

    @Override // com.startapp.sdk.adsbase.Ad
    public void a(AdPreferences adPreferences, AdEventListener adEventListener) {
        new n2(this.b, this, this.offset, adPreferences, adEventListener).b();
        this.offset++;
    }

    public void b(boolean z) {
        this.fixedSize = z;
    }

    public int h() {
        return this.offset;
    }

    public boolean i() {
        return this.fixedSize;
    }
}
