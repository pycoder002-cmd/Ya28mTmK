package com.startapp;

import com.startapp.sdk.adsbase.Ad;
import com.startapp.sdk.adsbase.adlisteners.AdEventListener;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class l5 implements AdEventListener {
    public final /* synthetic */ m5 a;

    public l5(m5 m5Var) {
        this.a = m5Var;
    }

    @Override // com.startapp.sdk.adsbase.adlisteners.AdEventListener
    public void onFailedToReceiveAd(Ad ad) {
    }

    @Override // com.startapp.sdk.adsbase.adlisteners.AdEventListener
    public void onReceiveAd(Ad ad) {
        if (this.a.e.showAd()) {
            m5 m5Var = this.a;
            m5Var.getClass();
            m5Var.c = System.currentTimeMillis();
            m5Var.d = 0;
        }
    }
}
