package com.startapp;

import android.content.Context;
import android.os.Bundle;
import com.startapp.pc;
import com.startapp.sdk.adsbase.StartAppSDKInternal;
import com.startapp.sdk.adsbase.remoteconfig.MetaData;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class ia extends pc {

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class a implements oa {
        public a() {
        }

        @Override // com.startapp.oa
        public void a(Object obj) {
            ia.this.callback.a(ia.this, false);
        }
    }

    public ia(Context context, pc.a aVar, Bundle bundle) {
        super(context, aVar, bundle);
    }

    @Override // com.startapp.pc, java.lang.Runnable
    public void run() {
        try {
            ma.a(this.context);
            MetaData.c(this.context);
            MetaData.h.k = true;
            if (MetaData.h.Q()) {
                StartAppSDKInternal.a(this.context, true, new a());
            } else {
                this.callback.a(this, false);
            }
        } catch (Throwable th) {
            p7.a(this.context, th);
        }
    }
}
