package com.startapp;

import android.content.Context;
import com.startapp.aa;
import com.startapp.sdk.ads.interstitials.InterstitialAd;
import com.startapp.sdk.adsbase.adlisteners.AdEventListener;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public final class f7 implements aa.a {
    public final /* synthetic */ Context a;
    public final /* synthetic */ AdEventListener b;
    public final /* synthetic */ InterstitialAd c;

    public f7(Context context, AdEventListener adEventListener, InterstitialAd interstitialAd) {
        this.a = context;
        this.b = adEventListener;
        this.c = interstitialAd;
    }

    @Override // com.startapp.aa.a
    public void a() {
        d.b(this.a, this.b, this.c);
    }

    @Override // com.startapp.aa.a
    public void a(String str) {
        d.b(this.a, this.b, this.c);
    }
}
