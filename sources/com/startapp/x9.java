package com.startapp;

import android.app.Activity;
import android.content.Context;
import com.startapp.sdk.adsbase.model.AdPreferences;
import com.startapp.sdk.adsbase.remoteconfig.MetaData;
import com.startapp.sdk.adsbase.remoteconfig.MetaDataRequest;
import java.util.Map;
import java.util.UUID;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class x9 {
    public static final x9 a = new x9();
    public String b = "";
    public long c = 0;
    public MetaDataRequest.RequestReason d = MetaDataRequest.RequestReason.LAUNCH;

    public String a() {
        return this.b;
    }

    public synchronized void a(Context context, MetaDataRequest.RequestReason requestReason) {
        this.b = UUID.randomUUID().toString();
        this.c = System.currentTimeMillis();
        this.d = requestReason;
        Map<Activity, Integer> map = aa.a;
        j6 j6Var = j6.a;
        j6Var.b.clear();
        j6Var.c.clear();
        j6Var.d.clear();
        MetaData.h.a(context, new AdPreferences(), requestReason, false, null, true);
    }

    public long b() {
        return this.c;
    }
}
