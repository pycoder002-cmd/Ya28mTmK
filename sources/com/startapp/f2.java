package com.startapp;

import android.content.Context;
import com.startapp.sdk.adsbase.model.AdPreferences;
import com.startapp.sdk.adsbase.model.GetAdRequest;
import com.startapp.sdk.common.SDKException;
import com.startapp.sdk.components.ComponentLocator;
import com.startapp.x8;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class f2 extends GetAdRequest {
    public boolean S0;
    public int T0;

    @Override // com.startapp.sdk.adsbase.model.GetAdRequest, com.startapp.j5
    public void a(u9 u9Var) throws SDKException {
        super.a(u9Var);
        u9Var.a("fixedSize", (Object) Boolean.valueOf(this.S0), false, true);
        u9Var.a("bnrt", (Object) Integer.valueOf(this.T0), false, true);
    }

    @Override // com.startapp.sdk.adsbase.model.GetAdRequest
    public void f(Context context) {
        x8 r = ComponentLocator.a(context).r();
        AdPreferences.Placement placement = this.h0;
        int i = this.T0;
        r.getClass();
        this.v0 = placement == null ? null : r.a.get(new x8.a(placement, i));
    }
}
