package com.startapp;

import android.content.Context;
import com.startapp.sdk.adsbase.remoteconfig.MetaData;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class o8 extends m8 {

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class a implements Runnable {
        public final /* synthetic */ w8 a;

        public a(w8 w8Var) {
            this.a = w8Var;
        }

        @Override // java.lang.Runnable
        public void run() {
            JSONArray jSONArray;
            w8 w8Var = this.a;
            w8Var.c.unregisterListener(w8Var.f);
            oa oaVar = o8.this.b;
            w8 w8Var2 = this.a;
            w8Var2.getClass();
            try {
                jSONArray = w8Var2.b.a();
            } catch (Exception unused) {
                jSONArray = null;
            }
            oaVar.a(jSONArray);
        }
    }

    public o8(Context context, oa oaVar) {
        super(context, oaVar);
    }

    @Override // com.startapp.m8
    public void a() {
        try {
            long millis = TimeUnit.SECONDS.toMillis(MetaData.h.B().k());
            w8 w8Var = new w8(this.a, this.b);
            this.c.postDelayed(new a(w8Var), millis);
            w8Var.b();
        } catch (Throwable th) {
            p7.a(this.a, th);
            this.b.a(null);
        }
    }
}
