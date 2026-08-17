package com.startapp;

import android.view.View;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class t2 implements View.OnClickListener {
    public final /* synthetic */ r2 a;

    public t2(r2 r2Var) {
        this.a = r2Var;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (this.a.u()) {
            this.a.L.close();
        }
    }
}
