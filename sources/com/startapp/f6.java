package com.startapp;

import android.content.Context;
import com.startapp.sdk.adsbase.Ad;
import com.startapp.sdk.adsbase.adlisteners.AdEventListener;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public final class f6 implements Runnable {
    public final /* synthetic */ AdEventListener a;
    public final /* synthetic */ Ad b;
    public final /* synthetic */ Context c;

    public f6(AdEventListener adEventListener, Ad ad, Context context) {
        this.a = adEventListener;
        this.b = ad;
        this.c = context;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            this.a.onReceiveAd(this.b);
        } catch (Throwable th) {
            aa.a(this.c, this.a, th);
        }
    }
}
