package com.startapp.sdk.ads.interstitials;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.startapp.aa;
import com.startapp.g5;
import com.startapp.k7;
import com.startapp.n5;
import com.startapp.p7;
import com.startapp.sdk.ads.splash.SplashAd;
import com.startapp.sdk.adsbase.ActivityExtra;
import com.startapp.sdk.adsbase.Ad;
import com.startapp.sdk.adsbase.AdsCommonMetaData;
import com.startapp.sdk.adsbase.AdsConstants;
import com.startapp.sdk.adsbase.HtmlAd;
import com.startapp.sdk.adsbase.VideoConfig;
import com.startapp.sdk.adsbase.adlisteners.NotDisplayedReason;
import com.startapp.sdk.adsbase.model.AdPreferences;
import com.startapp.sdk.components.ComponentLocator;
import java.io.Serializable;
import java.util.Map;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public abstract class InterstitialAd extends HtmlAd implements n5 {
    private static final long serialVersionUID = -8158520010577551438L;

    public InterstitialAd(Context context, AdPreferences.Placement placement) {
        super(context, placement);
    }

    @Override // com.startapp.sdk.adsbase.Ad, com.startapp.n5
    public void a(boolean z) {
        super.a(z);
    }

    @Override // com.startapp.sdk.adsbase.Ad, com.startapp.n5
    public boolean a() {
        return super.a();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v7, types: [java.lang.Boolean[], java.io.Serializable] */
    @Override // com.startapp.n5
    public boolean a(String str) {
        String a = g5.a();
        if (u() && AdsCommonMetaData.h.G().a().equals(VideoConfig.BackMode.DISABLED) && a.equals("back")) {
            a(NotDisplayedReason.VIDEO_BACK);
            return false;
        }
        if (!AdsConstants.g.booleanValue()) {
            setState(Ad.AdState.UN_INITIALIZED);
        }
        if (j() == null) {
            a(NotDisplayedReason.INTERNAL_ERROR);
            return false;
        }
        if (super.d()) {
            a(NotDisplayedReason.AD_EXPIRED);
            return false;
        }
        ActivityExtra activityExtra = this.activityExtra;
        boolean z = activityExtra != null && activityExtra.a();
        Intent intent = new Intent(this.b, (Class<?>) OverlayActivity.class);
        intent.putExtra("fileUrl", "exit.html");
        String[] strArr = this.trackingUrls;
        String str2 = "&position=" + g5.a();
        for (int i = 0; i < strArr.length; i++) {
            if (strArr[i] != null && !"".equals(strArr[i])) {
                strArr[i] = strArr[i] + str2;
            }
        }
        intent.putExtra("tracking", strArr);
        intent.putExtra("trackingClickUrl", o());
        intent.putExtra("packageNames", m());
        intent.putExtra("htmlUuid", k());
        intent.putExtra("smartRedirect", this.smartRedirect);
        intent.putExtra("browserEnabled", this.inAppBrowserEnabled);
        intent.putExtra("placement", this.placement.a());
        intent.putExtra("adInfoOverride", getAdInfoOverride());
        intent.putExtra("ad", this);
        intent.putExtra("videoAd", u());
        intent.putExtra("fullscreen", z);
        intent.putExtra("orientation", l() == 0 ? this.b.getResources().getConfiguration().orientation : l());
        intent.putExtra("adTag", str);
        intent.putExtra("lastLoadTime", super.b());
        intent.putExtra("adCacheTtl", super.c());
        intent.putExtra("closingUrl", g());
        intent.putExtra("rewardDuration", n());
        intent.putExtra("rewardedHideTimer", s());
        if (h() != null) {
            intent.putExtra("delayImpressionSeconds", h());
        }
        intent.putExtra("sendRedirectHops", (Serializable) t());
        intent.putExtra("mraidAd", r());
        if (r()) {
            intent.putExtra("activityShouldLockOrientation", false);
        }
        Map<Activity, Integer> map = aa.a;
        if (this instanceof SplashAd) {
            intent.putExtra("isSplash", true);
        }
        intent.putExtra("position", a);
        intent.addFlags(343932928);
        k7 f = ComponentLocator.a(this.b).f();
        if (f.d) {
            f.c = intent;
        } else {
            try {
                this.b.startActivity(intent);
            } catch (Throwable th) {
                p7.a(this.b, th);
                return false;
            }
        }
        return true;
    }

    @Override // com.startapp.sdk.adsbase.Ad, com.startapp.n5
    public Long b() {
        return super.b();
    }

    @Override // com.startapp.sdk.adsbase.Ad, com.startapp.n5
    public Long c() {
        return super.c();
    }

    @Override // com.startapp.sdk.adsbase.Ad, com.startapp.n5
    public boolean d() {
        return super.d();
    }

    public boolean u() {
        return false;
    }
}
