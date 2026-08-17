package com.startapp;

import com.github.mikephil.charting.utils.Utils;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class k {
    public final h a;
    public final h b;
    public final i c;
    public final o d;
    public final n e;
    public final i f;
    public final g g;
    public final m h;
    public final g i;
    public final j j;
    public final g k;
    public long l;
    public long m;

    public k(double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8, double d9, double d10, double d11, double d12, double d13, double d14, double d15, double d16) {
        h hVar = new h(new i(d), new i(d), new i(d));
        this.a = hVar;
        this.b = new h(new i(d2), new i(d2), new i(d2));
        this.c = new i(d3);
        this.d = new o(new p(d5, d6, hVar.b()), new p(d5, d6, hVar.c()), new p(d5, d6, hVar.d()));
        this.e = new n();
        this.f = new i(d4);
        this.g = new g(d12, d13, d14, d15, d16);
        this.h = new m();
        this.i = new g(d12, d13, d14, d15, d16);
        this.j = new j(5.0d, false);
        this.k = new g(d7, d8, d9, d10, d11);
    }

    public void a(double d, long j) {
        this.k.g = Math.min(Math.max(Utils.DOUBLE_EPSILON, d), 1.0d);
        this.k.h = j;
    }

    public void a(long j, long j2, double d, double d2, double d3) {
        double d4;
        double d5;
        double d6;
        double d7;
        double exp;
        if (this.m >= j2) {
            return;
        }
        if (this.l <= 0) {
            this.l = j2;
        }
        h hVar = this.a;
        double d8 = hVar.a.c;
        double d9 = hVar.b.c;
        double d10 = hVar.c.c;
        hVar.a(d, d2, d3);
        h hVar2 = this.a;
        double d11 = hVar2.a.c - d8;
        double d12 = hVar2.b.c - d9;
        double d13 = hVar2.c.c - d10;
        double d14 = (d11 * d11) + (d12 * d12) + (d13 * d13);
        this.g.a(j, d.a(d14, 1.5d, 4.0d));
        double d15 = 1.0d - this.g.i;
        this.i.a(j, d.a(d14, 0.01d, 1000.0d));
        h hVar3 = this.b;
        h hVar4 = this.a;
        hVar3.a(d - hVar4.a.c, d2 - hVar4.b.c, d3 - hVar4.c.c);
        this.c.a(this.b.d);
        o oVar = this.d;
        oVar.a.a(j2, d);
        oVar.b.a(j2, d2);
        oVar.c.a(j2, d3);
        p pVar = oVar.a;
        double d16 = pVar.q;
        p pVar2 = oVar.b;
        double d17 = pVar2.q;
        p pVar3 = oVar.c;
        double d18 = pVar3.q;
        double d19 = d16 + d17 + d18;
        if (d19 > Utils.DOUBLE_EPSILON) {
            oVar.d = ((pVar.p * d16) / d19) + ((pVar2.p * d17) / d19) + ((pVar3.p * d18) / d19);
            oVar.e = d19 / 3.0d;
        } else {
            oVar.d = Utils.DOUBLE_EPSILON;
            oVar.e = Utils.DOUBLE_EPSILON;
        }
        double a = d.a((j2 - this.l) / 1.0E9d, 10.0d, 1.0d);
        n nVar = this.e;
        o oVar2 = this.d;
        double d20 = oVar2.d;
        double d21 = oVar2.e;
        double d22 = this.c.c;
        nVar.a = (nVar.a * Utils.DOUBLE_EPSILON) + (Math.min(Math.exp((-Math.pow(d20 - 7.0d, 2.0d)) / 1.0d) * 2.0d, 1.0d) * 1.0d);
        double d23 = nVar.b * Utils.DOUBLE_EPSILON;
        if (d21 < Utils.DOUBLE_EPSILON) {
            d4 = 1.0d;
            d5 = Utils.DOUBLE_EPSILON;
        } else {
            if (d21 < 0.5d) {
                d5 = Math.pow(d21 * 2.0d, 4.0d);
            } else if (d21 > 2.0d) {
                d5 = Math.exp((2.0d - d21) * 6.0d);
            } else {
                d4 = 1.0d;
                d5 = 1.0d;
            }
            d4 = 1.0d;
        }
        nVar.b = d23 + (d5 * d4);
        double d24 = nVar.c * Utils.DOUBLE_EPSILON;
        if (d22 < Utils.DOUBLE_EPSILON) {
            d6 = 1.0d;
            d7 = Utils.DOUBLE_EPSILON;
        } else {
            if (d22 < 0.5d) {
                exp = Math.pow(d22 * 2.0d, 4.0d);
            } else if (d22 > 5.0d) {
                exp = Math.exp((5.0d - d22) * 4.0d);
            } else {
                d6 = 1.0d;
                d7 = 1.0d;
            }
            d7 = exp;
            d6 = 1.0d;
        }
        double d25 = d24 + (d7 * d6);
        nVar.c = d25;
        nVar.d = ((nVar.a * 100.0d) / 270.0d) + ((nVar.b * 70.0d) / 270.0d) + ((d25 * 100.0d) / 270.0d);
        this.f.a(a * d15 * this.e.d);
        double d26 = this.f.c;
        m mVar = this.h;
        o oVar3 = this.d;
        double d27 = oVar3.d;
        double d28 = oVar3.e;
        this.j.getClass();
        this.j.getClass();
        mVar.e = (mVar.e * 0.95d) + (d27 * 0.050000000000000044d);
        mVar.f = (mVar.f * 0.95d) + (d28 * 0.050000000000000044d);
        mVar.g = (mVar.g * 0.995d) + (Math.abs(Utils.DOUBLE_EPSILON) * 0.0050000000000000044d);
        mVar.h = (mVar.h * 0.995d) + (Math.abs(Utils.DOUBLE_EPSILON) * 0.0050000000000000044d);
        mVar.i = (mVar.i * 0.9995d) + ((((d.a(mVar.e, 8.0d, 2.0d, m.a) * 2000.0d) / 5000.0d) + ((d.a(mVar.f, 0.2d, 20.0d, m.b) * 1000.0d) / 5000.0d) + ((d.a(mVar.g, 0.2d, 6.0d, m.c) * 1000.0d) / 5000.0d) + ((d.a(mVar.h, 0.4d, 5.0d, m.d) * 1000.0d) / 5000.0d)) * 4.999999999999449E-4d);
        g gVar = this.k;
        double d29 = this.i.i;
        gVar.a(j, (d26 * d29) + (a * this.h.i * (1.0d - d29)));
        this.m = j2;
    }
}
