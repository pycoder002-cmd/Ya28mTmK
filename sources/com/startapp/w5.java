package com.startapp;

import android.content.Context;
import com.startapp.sdk.adsbase.StartAppSDKInternal;
import com.startapp.sdk.adsbase.cache.CacheMetaData;
import com.startapp.sdk.adsbase.remoteconfig.MetaData;
import com.startapp.sdk.components.ComponentLocator;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public final class w5 implements Runnable {
    public final /* synthetic */ Context a;

    public w5(Context context) {
        this.a = context;
    }

    @Override // java.lang.Runnable
    public void run() {
        StartAppSDKInternal startAppSDKInternal = StartAppSDKInternal.c.a;
        Context context = this.a;
        if (startAppSDKInternal.e) {
            if (startAppSDKInternal.r || !CacheMetaData.a.a().f()) {
                v6 v6Var = v6.a;
                v6Var.d = true;
                ComponentLocator.a(context).h().execute(new c7(context, new r6(v6Var)));
            } else if (startAppSDKInternal.f) {
                v6 v6Var2 = v6.a;
                v6Var2.getClass();
                Context b = y8.b(context);
                v6Var2.h = b;
                if (!v6Var2.d && CacheMetaData.a.a().f()) {
                    v6Var2.e = true;
                    ComponentLocator.a(b).h().execute(new d7(b, new q6(v6Var2, b)));
                }
            }
            startAppSDKInternal.e(context);
            v6 v6Var3 = v6.a;
            v6Var3.getClass();
            s6 s6Var = new s6(v6Var3, context, ComponentLocator.a(context).d());
            Object obj = MetaData.a;
            synchronized (MetaData.a) {
                MetaData.h.a(s6Var);
            }
        }
    }
}
