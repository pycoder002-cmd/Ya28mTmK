package com.startapp;

import com.startapp.b7;
import com.startapp.sdk.adsbase.Ad;
import com.startapp.sdk.adsbase.adlisteners.AdEventListener;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class z6 implements AdEventListener {
    public final /* synthetic */ b7.a a;
    public final /* synthetic */ boolean b;
    public final /* synthetic */ b7 c;

    public z6(b7 b7Var, b7.a aVar, boolean z) {
        this.c = b7Var;
        this.a = aVar;
        this.b = z;
    }

    @Override // com.startapp.sdk.adsbase.adlisteners.AdEventListener
    public void onFailedToReceiveAd(Ad ad) {
        b7 b7Var = this.c;
        b7Var.e = null;
        b7Var.a(this.b);
    }

    @Override // com.startapp.sdk.adsbase.adlisteners.AdEventListener
    public void onReceiveAd(Ad ad) {
        d.b(this.c.b, this.a, ad);
    }
}
