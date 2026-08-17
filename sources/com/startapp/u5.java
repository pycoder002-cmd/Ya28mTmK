package com.startapp;

import android.content.Context;
import com.startapp.sdk.adsbase.StartAppSDKInternal;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public final class u5 implements Runnable {
    public final /* synthetic */ Context a;

    public u5(Context context) {
        this.a = context;
    }

    @Override // java.lang.Runnable
    public void run() {
        String str = StartAppSDKInternal.a;
        synchronized (StartAppSDKInternal.b) {
            StartAppSDKInternal.a(this.a);
        }
    }
}
