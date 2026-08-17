package com.startapp;

import android.content.Context;
import android.os.Bundle;
import com.startapp.pc;
import com.startapp.sdk.components.ComponentLocator;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class od extends pc {
    public od(Context context, pc.a aVar, Bundle bundle) {
        super(context, aVar, bundle);
    }

    @Override // com.startapp.pc, java.lang.Runnable
    public void run() {
        pd b = ComponentLocator.a(this.context).o.b();
        pc.a aVar = this.callback;
        if (aVar != null) {
            b.d.post(new qd(b, aVar, this));
        } else {
            b.d.post(b.h);
        }
    }
}
