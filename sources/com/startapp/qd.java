package com.startapp;

import com.startapp.pc;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class qd implements Runnable {
    public final /* synthetic */ pc.a a;
    public final /* synthetic */ pc b;
    public final /* synthetic */ pd c;

    public qd(pd pdVar, pc.a aVar, pc pcVar) {
        this.c = pdVar;
        this.a = aVar;
        this.b = pcVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            this.c.h.run();
        } finally {
            this.a.a(this.b, false);
        }
    }
}
