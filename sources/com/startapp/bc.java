package com.startapp;

import android.content.Context;
import com.startapp.p5;
import com.startapp.sdk.adsbase.remoteconfig.StaleDcConfig;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class bc extends sb<ac> {
    public final p5 e;
    public final k9<StaleDcConfig> f;

    public bc(Context context, p5 p5Var, k9<StaleDcConfig> k9Var) {
        super(context, 86400000L);
        this.e = p5Var;
        this.f = k9Var;
    }

    @Override // com.startapp.sb
    public ac a(boolean z) {
        ac acVar = new ac();
        String string = z ? null : this.e.getString("a83b59c2138cbf65", null);
        if (string == null) {
            Context context = this.a;
            context.getPackageName();
            string = aa.b(context);
            p5.a edit = this.e.edit();
            edit.a("a83b59c2138cbf65", string);
            edit.a.putString("a83b59c2138cbf65", string);
            edit.apply();
        }
        acVar.a = string;
        return acVar;
    }

    @Override // com.startapp.sb
    public ac c() {
        return new ac();
    }
}
