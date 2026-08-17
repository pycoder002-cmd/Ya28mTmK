package com.startapp;

import java.util.Comparator;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class d9<T> implements Comparator<T> {
    public final Comparator<T> a;
    public final Comparator<T> b;

    public d9(Comparator<T> comparator, Comparator<T> comparator2) {
        this.a = comparator;
        this.b = comparator2;
    }

    @Override // java.util.Comparator
    public int compare(T t, T t2) {
        int compare = this.a.compare(t, t2);
        return compare == 0 ? this.b.compare(t, t2) : compare;
    }
}
