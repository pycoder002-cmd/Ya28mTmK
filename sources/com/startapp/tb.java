package com.startapp;

import android.content.Context;
import com.startapp.sdk.adsbase.remoteconfig.BluetoothConfig;
import com.startapp.sdk.components.ComponentLocator;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class tb extends yb<String> implements oa {
    public final p5 j;
    public final k7 k;
    public final k9<BluetoothConfig> l;

    public tb(Context context, p5 p5Var, p5 p5Var2, k7 k7Var, l9 l9Var, k9<BluetoothConfig> k9Var) {
        super(context, p5Var2, l9Var, "26787005dc4a1477", "c8ef3e50475fc527");
        this.j = p5Var;
        this.k = k7Var;
        this.l = k9Var;
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
        BluetoothConfig call = this.l.call();
        if (call != null) {
            return call.b();
        }
        return 0L;
    }

    @Override // com.startapp.yb
    public boolean f() {
        BluetoothConfig call;
        return this.k.c() && (call = this.l.call()) != null && call.d();
    }

    @Override // com.startapp.yb
    public void g() {
        if (ya.a(this.a, "android.permission.BLUETOOTH")) {
            Context context = this.a;
            ComponentLocator.a(context).i().execute(new n8(context, this.j, this).d);
        }
    }
}
