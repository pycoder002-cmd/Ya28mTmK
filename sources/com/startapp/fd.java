package com.startapp;

import android.util.Pair;
import java.util.List;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class fd {
    public final ja a;
    public final List<Pair<nd, Boolean>> b;
    public final int c;
    public final int[] d;
    public final Integer e;
    public final Integer f;
    public final int g;
    public final int h;

    public fd(ja jaVar, List<Pair<nd, Boolean>> list, int i, int[] iArr, Integer num, Integer num2, int i2, int i3) {
        this.a = jaVar;
        this.b = list;
        this.c = i;
        this.d = iArr;
        this.e = num;
        this.f = num2;
        this.g = i2;
        this.h = i3;
    }

    public boolean a(int i) {
        return (i & this.h) != 0;
    }
}
