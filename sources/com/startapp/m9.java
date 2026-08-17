package com.startapp;

import android.os.Handler;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class m9 implements l9 {
    public final Handler a;

    public m9(Handler handler) {
        this.a = handler;
    }

    @Override // com.startapp.l9
    public void a(Runnable runnable) {
        this.a.removeCallbacks(runnable);
    }

    @Override // com.startapp.l9
    public void a(Runnable runnable, long j) {
        this.a.postDelayed(runnable, j);
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        this.a.post(runnable);
    }
}
