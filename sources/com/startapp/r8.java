package com.startapp;

import android.os.Handler;
import android.os.Looper;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class r8 implements Runnable {
    public final /* synthetic */ s8 a;

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class a implements Runnable {
        public final /* synthetic */ Boolean a;

        public a(Boolean bool) {
            this.a = bool;
        }

        @Override // java.lang.Runnable
        public void run() {
            r8.this.a.a(this.a);
        }
    }

    public r8(s8 s8Var) {
        this.a = s8Var;
    }

    @Override // java.lang.Runnable
    public void run() {
        new Handler(Looper.getMainLooper()).post(new a(this.a.a()));
    }
}
