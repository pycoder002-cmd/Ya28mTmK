package com.startapp;

import android.content.Context;
import android.os.Bundle;
import com.startapp.pc;
import com.startapp.sdk.adsbase.SimpleTokenUtils;
import com.startapp.sdk.adsbase.model.AdPreferences;
import com.startapp.sdk.adsbase.remoteconfig.MetaData;
import com.startapp.sdk.adsbase.remoteconfig.MetaDataRequest;
import com.startapp.sdk.components.ComponentLocator;
import com.startapp.za;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class u8 extends pc {
    public static final String LOG_TAG = "u8";

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class a extends s8 {
        public MetaData l;
        public final /* synthetic */ Context m;
        public final /* synthetic */ AdPreferences n;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(Context context, AdPreferences adPreferences, MetaDataRequest.RequestReason requestReason, Context context2, AdPreferences adPreferences2) {
            super(context, adPreferences, requestReason);
            this.m = context2;
            this.n = adPreferences2;
        }

        @Override // com.startapp.s8
        public Boolean a() {
            try {
                SimpleTokenUtils.e(this.m);
                MetaDataRequest metaDataRequest = new MetaDataRequest(this.m, ComponentLocator.a(this.m).d(), MetaDataRequest.RequestReason.PERIODIC);
                metaDataRequest.a(this.m, this.n);
                za.a a = s8.a(this.m, metaDataRequest);
                if (a != null) {
                    this.l = (MetaData) aa.a(a.a, MetaData.class);
                }
                return Boolean.TRUE;
            } catch (Throwable th) {
                p7.a(this.m, th);
                return Boolean.FALSE;
            }
        }

        @Override // com.startapp.s8
        public void a(Boolean bool) {
            MetaData metaData;
            Context context;
            try {
                if (bool.booleanValue() && (metaData = this.l) != null && (context = this.m) != null) {
                    MetaData.a(context, metaData, MetaDataRequest.RequestReason.PERIODIC, this.k);
                }
                u8.this.callback.a(u8.this, false);
            } catch (Throwable th) {
                p7.a(this.m, th);
            }
        }
    }

    public u8(Context context, pc.a aVar, Bundle bundle) {
        super(context, aVar, bundle);
    }

    private void sendMetaDataRequest(Context context) {
        AdPreferences adPreferences = new AdPreferences();
        ComponentLocator.a(context).o().execute(new r8(new a(context, adPreferences, MetaDataRequest.RequestReason.PERIODIC, context, adPreferences)));
    }

    @Override // com.startapp.pc, java.lang.Runnable
    public void run() {
        try {
            ma.a(this.context);
            MetaData.c(this.context);
            if (MetaData.h.R()) {
                sendMetaDataRequest(this.context);
            } else {
                this.callback.a(this, false);
            }
        } catch (Throwable th) {
            p7.a(this.context, th);
        }
    }
}
