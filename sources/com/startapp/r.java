package com.startapp;

import java.security.PublicKey;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class r {
    public PublicKey a;
    public q b;

    private r() {
    }

    public static r a(byte[] bArr) throws Exception {
        String str = new String(bArr);
        r rVar = new r();
        rVar.b = (q) v1.a(str, q.class);
        return rVar;
    }
}
