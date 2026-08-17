package com.startapp;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class h implements l {
    public final i a;
    public final i b;
    public final i c;
    public double d;

    public h(i iVar, i iVar2, i iVar3) {
        this.a = iVar;
        this.b = iVar2;
        this.c = iVar3;
    }

    @Override // com.startapp.l
    public double a() {
        return this.d;
    }

    public void a(double d, double d2, double d3) {
        this.a.a(d);
        this.b.a(d2);
        this.c.a(d3);
        double d4 = this.a.c;
        double d5 = this.b.c;
        double d6 = (d4 * d4) + (d5 * d5);
        double d7 = this.c.c;
        this.d = Math.sqrt(d6 + (d7 * d7));
    }

    public i b() {
        return this.a;
    }

    public i c() {
        return this.b;
    }

    public i d() {
        return this.c;
    }
}
