package com.startapp.sdk.adsbase.remoteconfig;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.text.TextUtils;
import android.util.Pair;
import com.startapp.aa;
import com.startapp.j5;
import com.startapp.k7;
import com.startapp.p5;
import com.startapp.p7;
import com.startapp.sdk.adsbase.SimpleTokenUtils;
import com.startapp.sdk.adsbase.StartAppSDKInternal;
import com.startapp.sdk.common.Constants;
import com.startapp.sdk.common.SDKException;
import com.startapp.sdk.components.ComponentLocator;
import com.startapp.u9;
import com.startapp.wa;
import com.startapp.ya;
import java.util.Map;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class MetaDataRequest extends j5 {
    public final p5 h0;
    public int i0;
    public int j0;
    public boolean k0;
    public float l0;
    public RequestReason m0;
    public String n0;
    public String o0;
    public Integer p0;
    public Pair<String, String> q0;
    public Integer r0;
    public Boolean s0;
    public long t0;

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public enum RequestReason {
        LAUNCH(1),
        APP_IDLE(2),
        IN_APP_PURCHASE(3),
        CUSTOM(4),
        PERIODIC(5),
        PAS(6),
        CONSENT(7),
        IMPLICIT_LAUNCH(8),
        EXTRAS(9);

        private int index;

        RequestReason(int i) {
            this.index = i;
        }
    }

    public MetaDataRequest(Context context, p5 p5Var, RequestReason requestReason) {
        super(2);
        this.h0 = p5Var;
        this.i0 = p5Var.getInt("totalSessions", 0);
        this.j0 = b();
        this.l0 = p5Var.getFloat("inAppPurchaseAmount", 0.0f);
        this.k0 = p5Var.getBoolean("payingUser", false);
        this.n0 = MetaData.q().y();
        this.m0 = requestReason;
        this.o0 = a(context, p5Var, StartAppSDKInternal.a().b());
        f(context);
        this.q0 = SimpleTokenUtils.a();
        this.t0 = SimpleTokenUtils.b();
        k7 f = ComponentLocator.a(context).f();
        this.r0 = f.b();
        this.s0 = f.a();
        a(ComponentLocator.a(context).b().a());
    }

    public static String a(Context context, p5 p5Var, boolean z) {
        String str = null;
        String string = p5Var.getString("shared_prefs_app_apk_hash", null);
        if (!TextUtils.isEmpty(string) && !z) {
            return string;
        }
        Map<Activity, Integer> map = aa.a;
        try {
            str = ya.a("SHA-256", context);
        } catch (Throwable th) {
            p7.a(context, th);
        }
        p5.a edit = p5Var.edit();
        edit.a("shared_prefs_app_apk_hash", str);
        edit.a.putString("shared_prefs_app_apk_hash", str);
        edit.apply();
        return str;
    }

    @Override // com.startapp.j5
    public void a(u9 u9Var) throws SDKException {
        super.a(u9Var);
        u9Var.a(wa.b, (Object) wa.a(), true, true);
        u9Var.a("totalSessions", (Object) Integer.valueOf(this.i0), true, true);
        u9Var.a("daysSinceFirstSession", (Object) Integer.valueOf(this.j0), true, true);
        u9Var.a("payingUser", (Object) Boolean.valueOf(this.k0), true, true);
        u9Var.a("profileId", (Object) this.n0, false, true);
        u9Var.a("paidAmount", (Object) Float.valueOf(this.l0), true, true);
        u9Var.a("reason", (Object) this.m0, true, true);
        u9Var.a("ct", (Object) this.r0, false, true);
        u9Var.a("apc", (Object) this.s0, false, true);
        String str = StartAppSDKInternal.a;
        u9Var.a("testAdsEnabled", (Object) (StartAppSDKInternal.c.a.F ? Boolean.TRUE : null), false, true);
        u9Var.a("apkHash", (Object) this.o0, false, true);
        u9Var.a("ian", (Object) this.p0, false, true);
        Pair<String, String> pair = this.q0;
        u9Var.a((String) pair.first, pair.second, false, true);
        if (Build.VERSION.SDK_INT >= 9) {
            long j = this.t0;
            if (j != 0) {
                u9Var.a("firstInstalledAppTS", (Object) Long.valueOf(j), false, true);
            }
        }
    }

    public final int b() {
        return (int) ((System.currentTimeMillis() - this.h0.getLong("firstSessionTime", System.currentTimeMillis())) / 86400000);
    }

    public void f(Context context) {
        SimpleTokenConfig D = MetaData.h.D();
        if (D == null || !D.a(context)) {
            return;
        }
        int i = ya.a;
        int i2 = 0;
        try {
            for (PackageInfo packageInfo : ya.a(context.getPackageManager())) {
                if (!ya.a(packageInfo) || packageInfo.packageName.equals(Constants.a)) {
                    i2++;
                }
            }
        } catch (Throwable unused) {
        }
        if (i2 > 0) {
            this.p0 = Integer.valueOf(i2);
        }
    }
}
