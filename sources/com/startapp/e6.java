package com.startapp;

import android.content.Context;
import com.startapp.sdk.adsbase.Ad;
import com.startapp.sdk.adsbase.adlisteners.AdDisplayListener;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public final class e6 implements Runnable {
    public final /* synthetic */ AdDisplayListener a;
    public final /* synthetic */ Ad b;
    public final /* synthetic */ Context c;

    public e6(AdDisplayListener adDisplayListener, Ad ad, Context context) {
        this.a = adDisplayListener;
        this.b = ad;
        this.c = context;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            this.a.adNotDisplayed(this.b);
        } catch (Throwable th) {
            aa.a(this.c, this.a, th);
        }
    }
}
