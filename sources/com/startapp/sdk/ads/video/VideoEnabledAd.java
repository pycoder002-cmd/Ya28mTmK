package com.startapp.sdk.ads.video;

import android.content.Context;
import com.startapp.a5;
import com.startapp.aa;
import com.startapp.c;
import com.startapp.sdk.ads.interstitials.InterstitialAd;
import com.startapp.sdk.ads.splash.SplashConfig;
import com.startapp.sdk.adsbase.VideoConfig;
import com.startapp.sdk.adsbase.adlisteners.AdEventListener;
import com.startapp.sdk.adsbase.model.AdPreferences;
import com.startapp.z3;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class VideoEnabledAd extends InterstitialAd {
    private static final long serialVersionUID = 1;
    private VideoAdDetails videoAdDetails;

    public VideoEnabledAd(Context context) {
        super(context, AdPreferences.Placement.INAPP_OVERLAY);
        this.videoAdDetails = null;
    }

    public void a(a5 a5Var, VideoConfig videoConfig, boolean z) {
        this.videoAdDetails = new VideoAdDetails(a5Var, videoConfig, z);
        Integer num = a5Var.p;
        if (num == null || a5Var.q == null) {
            return;
        }
        if (num.intValue() > a5Var.q.intValue()) {
            a(SplashConfig.Orientation.LANDSCAPE);
        } else {
            a(SplashConfig.Orientation.PORTRAIT);
        }
    }

    @Override // com.startapp.sdk.adsbase.Ad
    public void a(AdPreferences adPreferences, AdEventListener adEventListener) {
        new z3(this.b, this, adPreferences, adEventListener).b();
    }

    @Override // com.startapp.sdk.adsbase.HtmlAd
    public void c(String str) {
        super.c(str);
        String a = aa.a(str, "@videoJson@", "@videoJson@");
        if (a != null) {
            VideoAdDetails videoAdDetails = (VideoAdDetails) c.a(a, VideoAdDetails.class);
            this.videoAdDetails = videoAdDetails;
            if (videoAdDetails != null) {
                videoAdDetails.l();
            }
        }
    }

    @Override // com.startapp.sdk.ads.interstitials.InterstitialAd
    public boolean u() {
        return this.videoAdDetails != null;
    }

    public void v() {
        this.videoAdDetails = null;
    }

    public VideoAdDetails w() {
        return this.videoAdDetails;
    }
}
