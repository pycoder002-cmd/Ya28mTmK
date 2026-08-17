package com.startapp.sdk.adsbase.model;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.util.Pair;
import com.startapp.aa;
import com.startapp.j5;
import com.startapp.j6;
import com.startapp.sdk.adsbase.Ad;
import com.startapp.sdk.adsbase.AdsCommonMetaData;
import com.startapp.sdk.adsbase.SDKAdPreferences;
import com.startapp.sdk.adsbase.StartAppSDKInternal;
import com.startapp.sdk.adsbase.model.AdPreferences;
import com.startapp.sdk.adsbase.remoteconfig.MetaData;
import com.startapp.sdk.common.SDKException;
import com.startapp.sdk.components.ComponentLocator;
import com.startapp.u9;
import com.startapp.wa;
import com.startapp.x8;
import com.startapp.x9;
import com.startapp.ya;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class GetAdRequest extends j5 {
    public Set<String> A0;
    public Set<String> B0;
    public Set<String> C0;
    public Set<String> D0;
    public Pair<String, String> E0;
    public boolean F0;
    public long G0;
    public int H0;
    public String I0;
    public String J0;
    public String K0;
    public boolean L0;
    public Boolean M0;
    public Boolean N0;
    public String O0;
    public String P0;
    public String Q0;
    public Ad.AdType R0;
    public AdPreferences.Placement h0;
    public boolean i0;
    public Integer j0;
    public Long k0;
    public Boolean l0;
    public SDKAdPreferences.Gender m0;
    public String n0;
    public String o0;
    public int p0;
    public boolean q0;
    public Boolean r0;
    public boolean s0;
    public Double t0;
    public String u0;
    public String v0;
    public Integer w0;
    public boolean x0;
    public int y0;
    public Set<String> z0;

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public enum VideoRequestMode {
        INTERSTITIAL,
        REWARDED
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public enum VideoRequestType {
        ENABLED,
        DISABLED,
        FORCED,
        FORCED_NONVAST
    }

    public GetAdRequest() {
        super(4);
        this.p0 = 1;
        this.q0 = true;
        this.s0 = AdsCommonMetaData.k().M();
        this.x0 = true;
        this.y0 = 0;
        this.z0 = null;
        this.A0 = null;
        this.B0 = null;
        this.C0 = null;
        this.D0 = null;
        this.F0 = true;
        this.P0 = null;
        this.Q0 = null;
        this.R0 = null;
        long currentTimeMillis = System.currentTimeMillis();
        x9 x9Var = x9.a;
        this.G0 = currentTimeMillis - x9.a.b();
        Map<Activity, Integer> map = aa.a;
        this.H0 = j6.a().b();
        this.I0 = MetaData.q().y();
    }

    public void a(Context context, AdPreferences adPreferences, AdPreferences.Placement placement, Pair<String, String> pair) {
        this.h0 = placement;
        this.E0 = pair;
        this.M0 = adPreferences.getAi();
        this.N0 = adPreferences.getAs();
        this.m0 = adPreferences.getGender(context);
        this.n0 = adPreferences.getKeywords();
        this.i0 = adPreferences.isTestMode();
        this.z0 = adPreferences.getCategories();
        this.A0 = adPreferences.getCategoriesExclude();
        this.q0 = adPreferences.b();
        this.w0 = adPreferences.a();
        int i = ya.a;
        boolean z = false;
        if (Build.VERSION.SDK_INT < 17 ? Settings.System.getInt(context.getContentResolver(), "auto_time", 0) > 0 : Settings.Global.getInt(context.getContentResolver(), "auto_time", 0) > 0) {
            z = true;
        }
        this.r0 = Boolean.valueOf(z);
        this.t0 = adPreferences.getMinCpm();
        this.u0 = adPreferences.getAdTag();
        Object obj = MetaData.a;
        this.x0 = !context.getFileStreamPath("StartappMetadata").exists();
        this.P0 = adPreferences.country;
        this.Q0 = adPreferences.advertiserId;
        this.o0 = adPreferences.template;
        this.R0 = adPreferences.type;
        this.c = adPreferences.getCustomProductId();
        this.C0 = adPreferences.packageInclude;
    }

    @Override // com.startapp.j5
    public void a(u9 u9Var) throws SDKException {
        super.a(u9Var);
        u9Var.a("placement", this.h0.name(), true, true);
        u9Var.a("testMode", Boolean.toString(this.i0), false, true);
        u9Var.a("gender", this.m0, false, true);
        u9Var.a("keywords", this.n0, false, true);
        u9Var.a("template", this.o0, false, true);
        u9Var.a("adsNumber", Integer.toString(this.p0), false, true);
        u9Var.a("category", this.z0, false, true);
        u9Var.a("categoryExclude", this.A0, false, true);
        u9Var.a("packageExclude", this.B0, false, true);
        u9Var.a("campaignExclude", this.D0, false, true);
        u9Var.a("offset", Integer.toString(this.y0), false, true);
        u9Var.a("ai", this.M0, false, true);
        u9Var.a("as", this.N0, false, true);
        Double d = this.t0;
        Map<Activity, Integer> map = aa.a;
        u9Var.a("minCPM", d != null ? String.format(Locale.US, "%.2f", d) : null, false, true);
        u9Var.a("adTag", this.u0, false, true);
        u9Var.a("previousAdId", this.v0, false, true);
        u9Var.a("twoClicks", Boolean.valueOf(!this.s0), false, true);
        u9Var.a("engInclude", Boolean.toString(this.F0), false, true);
        Object obj = this.R0;
        if (obj == Ad.AdType.INTERSTITIAL || obj == Ad.AdType.RICH_TEXT) {
            u9Var.a("type", obj, false, true);
        }
        u9Var.a("timeSinceSessionStart", Long.valueOf(this.G0), true, true);
        u9Var.a("adsDisplayed", Integer.valueOf(this.H0), true, true);
        u9Var.a("profileId", this.I0, false, true);
        u9Var.a("hardwareAccelerated", Boolean.valueOf(this.q0), false, true);
        u9Var.a("autoLoadAmount", this.w0, false, true);
        u9Var.a("dts", this.r0, false, true);
        u9Var.a("downloadingMode", "CACHE", false, true);
        u9Var.a("primaryImg", this.J0, false, true);
        u9Var.a("moreImg", this.K0, false, true);
        u9Var.a("contentAd", Boolean.toString(this.L0), false, true);
        u9Var.a("ct", this.j0, false, true);
        u9Var.a("tsc", this.k0, false, true);
        u9Var.a("apc", this.l0, false, true);
        String str = StartAppSDKInternal.a;
        u9Var.a("testAdsEnabled", StartAppSDKInternal.c.a.F ? Boolean.TRUE : null, false, true);
        String a = wa.a();
        u9Var.a(wa.b, (Object) a, true, true);
        String str2 = wa.d;
        StringBuilder sb = new StringBuilder();
        sb.append(this.c);
        sb.append(this.h0.name());
        String str3 = this.R;
        if (str3 == null) {
            str3 = "";
        }
        sb.append(str3);
        sb.append(this.d);
        sb.append(a);
        u9Var.a(str2, wa.a(sb.toString()), true, false);
        Object obj2 = this.P0;
        if (obj2 != null) {
            u9Var.a("country", obj2, false, true);
        }
        Object obj3 = this.Q0;
        if (obj3 != null) {
            u9Var.a("advertiserId", obj3, false, true);
        }
        Set<String> set = this.C0;
        if (set != null) {
            u9Var.a("packageInclude", set, false, true);
        }
        u9Var.a("defaultMetaData", Boolean.valueOf(this.x0), true, true);
        Pair<String, String> pair = this.E0;
        u9Var.a((String) pair.first, pair.second, false, true);
        Object obj4 = this.O0;
        if (obj4 != null) {
            u9Var.a("trv", obj4, false, false);
        }
    }

    @Override // com.startapp.j5
    public boolean a() {
        return true;
    }

    public boolean b() {
        Ad.AdType adType = this.R0;
        return adType == Ad.AdType.VIDEO || adType == Ad.AdType.REWARDED_VIDEO;
    }

    public void f(Context context) {
        x8 r = ComponentLocator.a(context).r();
        AdPreferences.Placement placement = this.h0;
        r.getClass();
        this.v0 = placement == null ? null : r.a.get(new x8.a(placement, -1));
    }
}
