package com.startapp;

import android.content.Context;
import com.startapp.p5;
import com.startapp.sdk.adsbase.remoteconfig.MetaData;
import io.sentry.DefaultSentryClientFactory;
import java.util.concurrent.TimeUnit;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class n8 extends m8 {
    public final p5 e;

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class a implements Runnable {
        public final /* synthetic */ p6 a;

        public a(p6 p6Var) {
            this.a = p6Var;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.a.c();
            n8.this.b.a(this.a.b());
        }
    }

    public n8(Context context, p5 p5Var, oa oaVar) {
        super(context, oaVar);
        this.e = p5Var;
    }

    @Override // com.startapp.m8
    public void a() {
        try {
            long millis = TimeUnit.SECONDS.toMillis(MetaData.h.f().c());
            p6 p6Var = new p6(this.a, this.b);
            this.c.postDelayed(new a(p6Var), millis);
            p6Var.a(b());
        } catch (Throwable th) {
            p7.a(this.a, th);
            this.b.a(null);
        }
    }

    public final boolean b() {
        long currentTimeMillis = System.currentTimeMillis();
        boolean z = currentTimeMillis - this.e.getLong("lastBtDiscoveringTime", 0L) >= ((long) MetaData.h.f().a()) * DefaultSentryClientFactory.BUFFER_FLUSHTIME_DEFAULT;
        if (z) {
            p5.a edit = this.e.edit();
            edit.a("lastBtDiscoveringTime", (String) Long.valueOf(currentTimeMillis));
            edit.a.putLong("lastBtDiscoveringTime", currentTimeMillis);
            edit.apply();
        }
        return z;
    }
}
