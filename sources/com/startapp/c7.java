package com.startapp;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public final class c7 implements Runnable {
    public final /* synthetic */ Context a;
    public final /* synthetic */ i7 b;

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ((r6) c7.this.b).a.d = false;
        }
    }

    public c7(Context context, i7 i7Var) {
        this.a = context;
        this.b = i7Var;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            h9.a(this.a, "startapp_ads");
            new Handler(Looper.getMainLooper()).post(new a());
        } catch (Throwable th) {
            p7.a(this.a, th);
        }
    }
}
