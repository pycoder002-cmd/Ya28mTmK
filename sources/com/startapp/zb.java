package com.startapp;

import android.content.Context;
import com.startapp.sdk.adsbase.remoteconfig.SensorsConfig;
import com.startapp.sdk.components.ComponentLocator;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class zb extends yb<String> implements oa {
    public final k7 j;
    public final k9<SensorsConfig> k;

    public zb(Context context, p5 p5Var, k7 k7Var, l9 l9Var, k9<SensorsConfig> k9Var) {
        super(context, p5Var, l9Var, "cc8b2544ce91bcdf", "7099d13208ad24ae");
        this.j = k7Var;
        this.k = k9Var;
    }

    @Override // com.startapp.yb
    public String a(String str) {
        return str;
    }

    @Override // com.startapp.oa
    public void a(Object obj) {
        b(obj != null ? obj.toString() : null);
    }

    @Override // com.startapp.sb
    public /* bridge */ /* synthetic */ Object c() {
        return "";
    }

    @Override // com.startapp.yb
    public long d() {
        SensorsConfig call = this.k.call();
        if (call != null) {
            return call.h();
        }
        return 0L;
    }

    @Override // com.startapp.yb
    public boolean f() {
        SensorsConfig call;
        return this.j.c() && (call = this.k.call()) != null && call.l();
    }

    @Override // com.startapp.yb
    public void g() {
        Context context = this.a;
        ComponentLocator.a(context).i().execute(new o8(context, this).d);
    }
}
