package com.startapp;

import android.content.Context;
import android.os.Bundle;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public abstract class pc implements Runnable {
    public final a callback;
    public final Context context;
    public final Bundle extras;

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public interface a {
        void a(pc pcVar, boolean z);
    }

    public pc(Context context, a aVar, Bundle bundle) {
        this.context = context;
        this.callback = aVar;
        this.extras = bundle;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.callback.a(this, runSync());
    }

    public boolean runSync() {
        return false;
    }
}
