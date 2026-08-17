package com.startapp;

import android.os.Build;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class y9 implements Iterator<Throwable> {
    public Throwable a;
    public Throwable[] b;
    public int c;
    public boolean d;

    public y9(Throwable th) {
        this.a = th;
        if (Build.VERSION.SDK_INT >= 19) {
            this.b = th.getSuppressed();
        }
    }

    @Override // java.util.Iterator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public Throwable next() {
        int i;
        Throwable th = this.a;
        this.d = false;
        if (th != null) {
            this.a = th.getCause();
        } else {
            Throwable[] thArr = this.b;
            if (thArr != null && (i = this.c) < thArr.length) {
                this.d = i == 0;
                this.c = i + 1;
                th = thArr[i];
            }
        }
        if (th != null) {
            return th;
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        Throwable[] thArr;
        return this.a != null || ((thArr = this.b) != null && this.c < thArr.length);
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
