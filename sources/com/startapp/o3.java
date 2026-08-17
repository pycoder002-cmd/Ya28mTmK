package com.startapp;

import android.content.Context;
import com.startapp.sdk.ads.splash.SplashAd;
import com.startapp.sdk.adsbase.adlisteners.AdEventListener;
import com.startapp.sdk.adsbase.model.AdPreferences;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class o3 extends gc {
    public o3(Context context, SplashAd splashAd, AdPreferences adPreferences, AdEventListener adEventListener) {
        super(context, splashAd, adPreferences, adEventListener, AdPreferences.Placement.INAPP_SPLASH, true);
    }

    @Override // com.startapp.gc, com.startapp.k5
    public void a(Boolean bool) {
        super.a(bool);
        a(bool.booleanValue());
    }
}
