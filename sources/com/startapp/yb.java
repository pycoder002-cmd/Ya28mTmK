package com.startapp;

import android.content.Context;
import io.sentry.DefaultSentryClientFactory;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public abstract class yb<T> extends sb<T> {
    public final p5 e;
    public final l9 f;
    public final String g;
    public final String h;
    public final Runnable i;

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            yb.this.g();
        }
    }

    public yb(Context context, p5 p5Var, l9 l9Var, String str, String str2) {
        super(context, 1000L);
        this.i = new a();
        this.e = p5Var;
        this.f = l9Var;
        this.g = str;
        this.h = str2;
    }

    @Override // com.startapp.sb
    public final T a() {
        T a2;
        if (!f()) {
            return null;
        }
        synchronized (this) {
            a2 = a(this.e.getString(this.g, null));
        }
        return a2;
    }

    public abstract T a(String str);

    public synchronized void b(T t) {
        if (t != null) {
            this.e.edit().a(this.g, c(t)).a(this.h, System.currentTimeMillis()).apply();
        }
        long max = Math.max(DefaultSentryClientFactory.BUFFER_FLUSHTIME_DEFAULT, d());
        synchronized (this) {
            if (f()) {
                this.f.a(this.i);
                this.f.a(this.i, max);
            }
        }
    }

    public String c(T t) {
        return t.toString();
    }

    public abstract long d();

    public synchronized void e() {
        long max = Math.max(0L, (this.e.getLong(this.h, 0L) + Math.max(DefaultSentryClientFactory.BUFFER_FLUSHTIME_DEFAULT, d())) - System.currentTimeMillis());
        synchronized (this) {
            if (f()) {
                this.f.a(this.i);
                this.f.a(this.i, max);
            }
        }
    }

    public abstract boolean f();

    public abstract void g();
}
