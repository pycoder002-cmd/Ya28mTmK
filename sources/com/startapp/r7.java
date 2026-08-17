package com.startapp;

import com.startapp.sdk.adsbase.remoteconfig.AnalyticsCategoryConfig;
import io.sentry.DefaultSentryClientFactory;
import java.util.ArrayList;
import java.util.List;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class r7 {
    public final double a;
    public final int b;
    public final int c;
    public final boolean d;
    public final long e;
    public final long f;
    public final List<t7> g;

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public static class a {
        public int a;
        public int b;
        public boolean c;
        public String d;
        public String e;
        public List<t7> f;

        public a a(t7 t7Var) {
            if (this.f == null) {
                this.f = new ArrayList();
            }
            this.f.add(t7Var);
            return this;
        }

        public List<t7> a() {
            return this.f;
        }

        public String b() {
            return this.e;
        }

        public int c() {
            return this.a;
        }

        public int d() {
            return this.b;
        }

        public String e() {
            return this.d;
        }

        public boolean f() {
            return this.c;
        }
    }

    public r7(a aVar) {
        this.a = 1.0d;
        this.b = aVar.c();
        this.c = aVar.d();
        this.d = aVar.f();
        this.e = Math.max(DefaultSentryClientFactory.BUFFER_FLUSHTIME_DEFAULT, aa.e(aVar.e()));
        this.f = Math.max(0L, aa.e(aVar.b()));
        this.g = aa.b(aVar.a());
    }

    public r7(r7 r7Var, AnalyticsCategoryConfig analyticsCategoryConfig) {
        this.a = ((Double) a(Double.valueOf(r7Var.a()), analyticsCategoryConfig.a())).doubleValue();
        this.b = ((Integer) a(Integer.valueOf(r7Var.d()), analyticsCategoryConfig.d())).intValue();
        this.c = ((Integer) a(Integer.valueOf(r7Var.e()), analyticsCategoryConfig.e())).intValue();
        this.d = ((Boolean) a(Boolean.valueOf(r7Var.g()), analyticsCategoryConfig.f())).booleanValue();
        this.e = analyticsCategoryConfig.g() == null ? r7Var.f() : Math.max(DefaultSentryClientFactory.BUFFER_FLUSHTIME_DEFAULT, aa.e(analyticsCategoryConfig.g()));
        this.f = analyticsCategoryConfig.c() == null ? r7Var.c() : Math.max(0L, aa.e(analyticsCategoryConfig.c()));
        this.g = (List) a(r7Var.b(), t7.a(analyticsCategoryConfig.b()));
    }

    public static <T> T a(T t, T t2) {
        return t2 != null ? t2 : t;
    }

    public double a() {
        return this.a;
    }

    public List<t7> b() {
        return this.g;
    }

    public long c() {
        return this.f;
    }

    public int d() {
        return this.b;
    }

    public int e() {
        return this.c;
    }

    public long f() {
        return this.e;
    }

    public boolean g() {
        return this.d;
    }
}
