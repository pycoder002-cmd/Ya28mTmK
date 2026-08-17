package com.startapp;

import com.github.mikephil.charting.utils.Utils;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class g implements l {
    public final double a;
    public final double b;
    public final double c;
    public final double d;
    public final double e;
    public final double f;
    public double g;
    public long h;
    public double i;

    public g(double d, double d2, double d3, double d4, double d5) {
        double d6 = d + d2;
        this.a = d / d6;
        this.b = d2 / d6;
        this.c = d3;
        this.d = d4;
        this.e = d5;
        this.f = d.a(Utils.DOUBLE_EPSILON, d4, d5);
    }

    public static double a(long j, long j2, double d, double d2, double d3, double d4) {
        double max = Math.max(0L, j - j2) / d;
        return d3 > Utils.DOUBLE_EPSILON ? d.a(max, d2, d3, d4) : d3 < Utils.DOUBLE_EPSILON ? d.a(max, d2, d3) / d4 : d.a(max, d2, d3);
    }

    @Override // com.startapp.l
    public double a() {
        return this.i;
    }

    public void a(long j, double d) {
        double a = this.g * a(j, this.h, this.c, this.d, this.e, this.f);
        double d2 = (this.a * a) + (this.b * d);
        this.i = d2;
        if (a < d2) {
            this.g = d2;
            this.h = j;
        }
    }
}
