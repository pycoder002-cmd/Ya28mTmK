package com.startapp;

import com.github.mikephil.charting.utils.Utils;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class p {
    public final b a = new b();
    public final long b;
    public final double c;
    public final l d;
    public double e;
    public double f;
    public double g;
    public long h;
    public long i;
    public double j;
    public long k;
    public long l;
    public a m;
    public a n;
    public int o;
    public double p;
    public double q;
    public double r;

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public static class a {
        public a a;
        public long b;
        public long c;
        public int d;
        public double e;
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public static class b {
        public a a;
    }

    public p(double d, double d2, l lVar) {
        this.b = (long) (d * 1.0E9d);
        this.c = d2;
        this.d = lVar;
    }

    public void a(long j, double d) {
        a aVar;
        this.e = this.f;
        this.f = this.g;
        this.g = d;
        this.h = this.i;
        this.i = j;
        long j2 = j - this.b;
        while (true) {
            a aVar2 = this.m;
            if (aVar2 == null || aVar2.b >= j2 || (aVar = aVar2.a) == null || aVar.b >= j2) {
                break;
            }
            this.m = aVar;
            this.o -= aVar2.d;
            this.r -= aVar2.e;
            b bVar = this.a;
            aVar2.a = bVar.a;
            bVar.a = aVar2;
        }
        b bVar2 = this.a;
        a aVar3 = bVar2.a;
        if (aVar3 == null) {
            aVar3 = new a();
        } else {
            bVar2.a = aVar3.a;
            aVar3.a = null;
            aVar3.b = 0L;
            aVar3.c = 0L;
            aVar3.d = 0;
            aVar3.e = Utils.DOUBLE_EPSILON;
        }
        aVar3.b = this.i;
        aVar3.c = this.l;
        double a2 = this.d.a();
        double d2 = this.e;
        double d3 = this.f;
        if (d2 < d3 && d3 > this.g) {
            double d4 = d3 - a2;
            if (d4 > this.c) {
                this.j = d4;
                this.k = this.h;
            }
        }
        if (d3 > a2 && a2 > this.g && this.k > this.l) {
            this.l = this.i;
            aVar3.d = 1;
            aVar3.e = this.j;
        }
        int i = this.o + aVar3.d;
        this.o = i;
        double d5 = this.r + aVar3.e;
        this.r = d5;
        a aVar4 = this.n;
        if (aVar4 != null) {
            aVar4.a = aVar3;
        }
        this.n = aVar3;
        if (this.m == null) {
            this.m = aVar3;
        }
        long j3 = this.l - this.m.c;
        if (j3 > 0) {
            this.p = i / (j3 / 1.0E9d);
        }
        if (i > 0) {
            this.q = d5 / i;
        } else {
            this.q = Utils.DOUBLE_EPSILON;
        }
    }
}
