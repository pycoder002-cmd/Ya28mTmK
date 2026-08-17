package com.startapp;

import android.content.Context;
import android.webkit.JavascriptInterface;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class p3 {
    public boolean a = false;
    public Runnable b;

    public p3(Context context, Runnable runnable) {
        this.b = null;
        this.b = runnable;
    }

    @JavascriptInterface
    public void closeSplash() {
        if (this.a) {
            return;
        }
        this.a = true;
        this.b.run();
    }
}
