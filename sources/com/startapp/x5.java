package com.startapp;

import android.content.Context;
import android.text.TextUtils;
import com.startapp.p5;
import com.startapp.sdk.adsbase.AdsConstants;
import com.startapp.sdk.adsbase.StartAppSDKInternal;
import com.startapp.sdk.adsbase.model.AdPreferences;
import com.startapp.sdk.adsbase.remoteconfig.MetaData;
import com.startapp.sdk.components.ComponentLocator;
import com.startapp.za;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class x5 implements Runnable {
    public final /* synthetic */ Context a;
    public final /* synthetic */ ComponentLocator b;
    public final /* synthetic */ p5 c;

    public x5(StartAppSDKInternal startAppSDKInternal, Context context, ComponentLocator componentLocator, p5 p5Var) {
        this.a = context;
        this.b = componentLocator;
        this.c = p5Var;
    }

    @Override // java.lang.Runnable
    public void run() {
        za.a aVar;
        try {
            s5 s5Var = new s5(this.a);
            s5Var.a(this.a, new AdPreferences());
            n7 j = this.b.j();
            String str = MetaData.h.I() + AdsConstants.d;
            j.getClass();
            try {
                aVar = j.a(str, s5Var, null);
            } catch (Throwable th) {
                p7.a(j.a, th);
                aVar = null;
            }
            if (aVar != null) {
                String str2 = aVar.a;
                if (!TextUtils.isEmpty(str2)) {
                    String a = aa.a(str2, "@ct@", "@ct@");
                    String a2 = aa.a(str2, "@tsc@", "@tsc@");
                    String a3 = aa.a(str2, "@apc@", "@apc@");
                    try {
                        Integer valueOf = !TextUtils.isEmpty(a) ? Integer.valueOf(Integer.parseInt(a)) : null;
                        Long valueOf2 = !TextUtils.isEmpty(a2) ? Long.valueOf(Long.parseLong(a2)) : null;
                        Boolean valueOf3 = TextUtils.isEmpty(a3) ? null : Boolean.valueOf(Boolean.parseBoolean(a3));
                        if (valueOf != null || valueOf2 != null || valueOf3 != null) {
                            this.b.f().a(valueOf, valueOf2, valueOf3, false, true);
                        }
                    } catch (Throwable th2) {
                        p7.a(this.a, th2);
                    }
                }
            }
            p5.a edit = this.c.edit();
            edit.a("shared_prefs_first_init", (String) Boolean.FALSE);
            edit.a.putBoolean("shared_prefs_first_init", false);
            edit.apply();
        } catch (Throwable th3) {
            p7.a(this.a, th3);
        }
    }
}
