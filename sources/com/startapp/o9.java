package com.startapp;

import com.startapp.p5;
import com.startapp.sdk.adsbase.remoteconfig.MetaData;
import com.startapp.sdk.adsbase.remoteconfig.MetaDataRequest;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class o9 implements t8 {
    public final p5 a;

    public o9(p5 p5Var) {
        this.a = p5Var;
        MetaData.q().a(this);
    }

    @Override // com.startapp.t8
    public void a(MetaDataRequest.RequestReason requestReason) {
        MetaData.h.a(this);
    }

    @Override // com.startapp.t8
    public void a(MetaDataRequest.RequestReason requestReason, boolean z) {
        if (z) {
            boolean z2 = Math.random() < MetaData.h.o();
            p5.a edit = this.a.edit();
            edit.a("0115fe86041c10c0", (String) Boolean.valueOf(z2));
            edit.a.putBoolean("0115fe86041c10c0", z2);
            edit.apply();
        }
        MetaData.h.a(this);
    }
}
