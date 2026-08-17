package com.startapp;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public abstract class ab<T> {
    public volatile T a;

    public abstract T a();

    public T b() {
        T t = this.a;
        if (t == null) {
            synchronized (this) {
                t = this.a;
                if (t == null) {
                    t = a();
                    this.a = t;
                }
            }
        }
        return t;
    }
}
