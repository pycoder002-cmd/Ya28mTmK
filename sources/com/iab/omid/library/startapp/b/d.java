package com.iab.omid.library.startapp.b;

import android.content.Context;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class d {
    private static d a = new d();
    private Context b;

    private d() {
    }

    public static d a() {
        return a;
    }

    public void a(Context context) {
        this.b = context != null ? context.getApplicationContext() : null;
    }

    public Context b() {
        return this.b;
    }
}
