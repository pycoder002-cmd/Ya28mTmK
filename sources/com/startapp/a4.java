package com.startapp;

import com.startapp.c4;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class a4 implements Runnable {
    public final /* synthetic */ c4.a a;
    public final /* synthetic */ String b;

    public a4(c4 c4Var, c4.a aVar, String str) {
        this.a = aVar;
        this.b = str;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.a.a(this.b);
    }
}
