package com.startapp;

import android.content.Context;
import com.startapp.sdk.adsbase.SDKAdPreferences;
import com.startapp.sdk.adsbase.StartAppSDKInternal;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class v5 implements Runnable {
    public final /* synthetic */ Context a;
    public final /* synthetic */ String b;
    public final /* synthetic */ String c;
    public final /* synthetic */ SDKAdPreferences d;
    public final /* synthetic */ boolean e;
    public final /* synthetic */ StartAppSDKInternal f;

    public v5(StartAppSDKInternal startAppSDKInternal, Context context, String str, String str2, SDKAdPreferences sDKAdPreferences, boolean z) {
        this.f = startAppSDKInternal;
        this.a = context;
        this.b = str;
        this.c = str2;
        this.d = sDKAdPreferences;
        this.e = z;
    }

    @Override // java.lang.Runnable
    public void run() {
        String str = StartAppSDKInternal.a;
        synchronized (StartAppSDKInternal.b) {
            StartAppSDKInternal.a(this.f, this.a, this.b, this.c, this.d, this.e);
        }
    }
}
