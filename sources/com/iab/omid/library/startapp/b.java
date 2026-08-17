package com.iab.omid.library.startapp;

import android.content.Context;
import com.iab.omid.library.startapp.b.d;
import com.iab.omid.library.startapp.b.f;
import com.iab.omid.library.startapp.d.e;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class b {
    private boolean a;

    private void b(Context context) {
        e.a(context, "Application Context cannot be null");
    }

    public String a() {
        return "1.3.21-Startapp";
    }

    public void a(Context context) {
        b(context);
        if (b()) {
            return;
        }
        a(true);
        f.a().a(context);
        com.iab.omid.library.startapp.b.b.a().a(context);
        com.iab.omid.library.startapp.d.b.a(context);
        d.a().a(context);
    }

    public void a(boolean z) {
        this.a = z;
    }

    public boolean b() {
        return this.a;
    }
}
