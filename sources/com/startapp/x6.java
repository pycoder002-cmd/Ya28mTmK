package com.startapp;

import android.os.Handler;
import android.os.Looper;
import com.startapp.b7;
import com.startapp.sdk.adsbase.remoteconfig.MetaData;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public abstract class x6 {
    public b7 a;
    public Handler b = null;
    public Long c = null;
    public boolean d = false;

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            x6.this.c();
        }
    }

    public x6(b7 b7Var) {
        this.a = b7Var;
    }

    public abstract boolean a();

    public abstract long b();

    public void c() {
        this.c = null;
        this.d = false;
        b7 b7Var = this.a;
        if (b7Var.m < MetaData.h.F()) {
            b7Var.m++;
            b7Var.a(null, null, true, false);
        } else {
            b7.b bVar = b7Var.p;
            if (bVar != null) {
                ((u6) bVar).a(b7Var);
            }
        }
    }

    public void d() {
        if (this.d) {
            return;
        }
        if (this.c == null) {
            this.c = Long.valueOf(System.currentTimeMillis());
        }
        if (a()) {
            if (this.b == null) {
                Looper myLooper = Looper.myLooper();
                if (myLooper == null) {
                    myLooper = Looper.getMainLooper();
                }
                this.b = new Handler(myLooper);
            }
            long b = b();
            if (b >= 0) {
                this.d = true;
                this.b.postDelayed(new a(), b);
            }
        }
    }

    public void e() {
        Handler handler = this.b;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        this.c = null;
        this.d = false;
    }
}
