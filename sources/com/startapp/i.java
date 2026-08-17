package com.startapp;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class i implements l {
    public final double a;
    public final double b;
    public double c;

    public i(double d) {
        double d2 = d + 1.0d;
        this.a = d / d2;
        this.b = 1.0d / d2;
    }

    @Override // com.startapp.l
    public double a() {
        return this.c;
    }

    public void a(double d) {
        this.c = (this.a * this.c) + (d * this.b);
    }
}
