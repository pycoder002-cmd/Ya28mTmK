package com.startapp;

import java.io.ByteArrayOutputStream;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class g9 extends ByteArrayOutputStream {
    public g9(int i) {
        super(i);
    }

    public byte[] a() {
        return ((ByteArrayOutputStream) this).buf;
    }

    public int b() {
        return ((ByteArrayOutputStream) this).count;
    }
}
