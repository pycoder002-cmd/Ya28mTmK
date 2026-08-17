package com.startapp;

import android.content.Context;
import android.os.Build;
import com.startapp.sdk.adsbase.SimpleTokenUtils;
import com.startapp.sdk.common.SDKException;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class s5 extends j5 {
    public q8 h0;
    public String i0;

    public s5(Context context) {
        super(1);
        this.h0 = p8.a(context);
        this.i0 = ya.a(context);
    }

    @Override // com.startapp.j5
    public void a(u9 u9Var) throws SDKException {
        super.a(u9Var);
        u9Var.a("placement", "INAPP_DOWNLOAD", true, true);
        q8 q8Var = this.h0;
        if (q8Var != null) {
            u9Var.a("install_referrer", (Object) q8Var.a.getString("install_referrer"), true, true);
            u9Var.a("referrer_click_timestamp_seconds", (Object) Long.valueOf(this.h0.a.getLong("referrer_click_timestamp_seconds")), true, true);
            u9Var.a("install_begin_timestamp_seconds", (Object) Long.valueOf(this.h0.a.getLong("install_begin_timestamp_seconds")), true, true);
        }
        u9Var.a("apkSig", (Object) this.i0, true, true);
        if (Build.VERSION.SDK_INT >= 9) {
            long j = SimpleTokenUtils.c;
            if (j != 0) {
                u9Var.a("firstInstalledAppTS", (Object) Long.valueOf(j), false, true);
            }
        }
    }
}
