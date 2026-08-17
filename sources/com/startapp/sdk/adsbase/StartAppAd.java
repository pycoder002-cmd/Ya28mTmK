package com.startapp.sdk.adsbase;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import com.blankj.utilcode.constant.MemoryConstants;
import com.startapp.aa;
import com.startapp.b6;
import com.startapp.b7;
import com.startapp.c6;
import com.startapp.d;
import com.startapp.d6;
import com.startapp.g5;
import com.startapp.h6;
import com.startapp.h9;
import com.startapp.la;
import com.startapp.m5;
import com.startapp.n5;
import com.startapp.p7;
import com.startapp.sdk.ads.interstitials.OverlayActivity;
import com.startapp.sdk.ads.splash.SplashConfig;
import com.startapp.sdk.ads.splash.SplashHideListener;
import com.startapp.sdk.adsbase.Ad;
import com.startapp.sdk.adsbase.StartAppSDKInternal;
import com.startapp.sdk.adsbase.adlisteners.AdDisplayListener;
import com.startapp.sdk.adsbase.adlisteners.AdEventListener;
import com.startapp.sdk.adsbase.adlisteners.NotDisplayedReason;
import com.startapp.sdk.adsbase.adrules.AdRulesResult;
import com.startapp.sdk.adsbase.cache.CacheKey;
import com.startapp.sdk.adsbase.model.AdPreferences;
import com.startapp.sdk.adsbase.remoteconfig.MetaData;
import com.startapp.sdk.components.ComponentLocator;
import com.startapp.v6;
import com.startapp.y8;
import java.io.Serializable;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class StartAppAd extends Ad {
    private static final long serialVersionUID = 1;
    public n5 ad;
    private CacheKey adKey;
    private AdMode adMode;
    private AdPreferences adPreferences;
    public AdDisplayListener callback;
    private final BroadcastReceiver callbackBroadcastReceiver;
    public com.startapp.sdk.adsbase.adlisteners.VideoListener videoListener;

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public enum AdMode {
        AUTOMATIC,
        FULLPAGE,
        OFFERWALL,
        REWARDED_VIDEO,
        VIDEO,
        OVERLAY
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class a extends BroadcastReceiver {
        public a() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action == null) {
                action = "";
            }
            if (action.equals("com.startapp.android.ShowFailedDisplayBroadcastListener")) {
                Bundle extras = intent.getExtras();
                if (extras == null) {
                    extras = Bundle.EMPTY;
                }
                if (extras.containsKey("showFailedReason")) {
                    StartAppAd.this.a((NotDisplayedReason) extras.getSerializable("showFailedReason"));
                }
                StartAppAd startAppAd = StartAppAd.this;
                d.a(context, startAppAd.callback, startAppAd);
                la.a(context).a(this);
            } else if (action.equals("com.startapp.android.ShowDisplayBroadcastListener")) {
                StartAppAd startAppAd2 = StartAppAd.this;
                AdDisplayListener adDisplayListener = startAppAd2.callback;
                g5.a(adDisplayListener == null ? null : new c6(adDisplayListener, startAppAd2, context));
            } else if (action.equals("com.startapp.android.OnClickCallback")) {
                StartAppAd startAppAd3 = StartAppAd.this;
                AdDisplayListener adDisplayListener2 = startAppAd3.callback;
                g5.a(adDisplayListener2 == null ? null : new d6(adDisplayListener2, startAppAd3, context));
            } else if (action.equals("com.startapp.android.OnVideoCompleted")) {
                com.startapp.sdk.adsbase.adlisteners.VideoListener videoListener = StartAppAd.this.videoListener;
                g5.a(videoListener == null ? null : new h6(videoListener, context));
            } else {
                StartAppAd startAppAd4 = StartAppAd.this;
                AdDisplayListener adDisplayListener3 = startAppAd4.callback;
                g5.a(adDisplayListener3 == null ? null : new b6(adDisplayListener3, startAppAd4, context));
                la.a(context).a(this);
            }
            StartAppAd.this.ad = null;
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public static class b extends BroadcastReceiver {
        public final /* synthetic */ Activity a;
        public final /* synthetic */ SplashHideListener b;

        public b(Activity activity, SplashHideListener splashHideListener) {
            this.a = activity;
            this.b = splashHideListener;
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            aa.a(this.a, false);
            SplashHideListener splashHideListener = this.b;
            if (splashHideListener != null) {
                splashHideListener.splashHidden();
            }
            la.a(this.a).a(this);
        }
    }

    public StartAppAd(Context context) {
        super(context, null);
        this.adKey = null;
        this.ad = null;
        this.adMode = AdMode.AUTOMATIC;
        this.adPreferences = null;
        this.videoListener = null;
        this.callback = null;
        this.callbackBroadcastReceiver = new a();
        try {
            ComponentLocator.a(context).q().a(8192);
        } catch (Throwable unused) {
        }
    }

    public static void a(Activity activity, Bundle bundle, SplashConfig splashConfig, AdPreferences adPreferences, SplashHideListener splashHideListener, boolean z) {
        if (activity != null && bundle == null && MetaData.h.b() && aa.e(activity) && ComponentLocator.a(activity).c().a()) {
            try {
                String str = StartAppSDKInternal.a;
                StartAppSDKInternal startAppSDKInternal = StartAppSDKInternal.c.a;
                if (!(!startAppSDKInternal.y) && z) {
                    startAppSDKInternal.y = false;
                }
                startAppSDKInternal.x = z;
                if (!z) {
                    if (adPreferences == null) {
                        adPreferences = new AdPreferences();
                    }
                    adPreferences.setAs(Boolean.TRUE);
                }
                splashConfig.setDefaults(activity);
                aa.a(activity, activity.getResources().getConfiguration().orientation, true);
                Intent intent = new Intent(activity, (Class<?>) OverlayActivity.class);
                intent.putExtra("SplashConfig", splashConfig);
                intent.putExtra("AdPreference", adPreferences);
                intent.putExtra("testMode", false);
                intent.putExtra("fullscreen", g5.a(activity));
                intent.putExtra("placement", AdPreferences.Placement.INAPP_SPLASH.a());
                intent.addFlags(67108864 | (Build.VERSION.SDK_INT >= 11 ? 32768 : 0) | MemoryConstants.GB);
                activity.startActivity(intent);
                la.a(activity).a(new b(activity, splashHideListener), new IntentFilter("com.startapp.android.splashHidden"));
            } catch (Throwable th) {
                p7.a(activity, th);
                if (splashHideListener != null) {
                    splashHideListener.splashHidden();
                }
            }
        }
    }

    public static void disableAutoInterstitial() {
        m5.a.a.a = false;
    }

    public static void disableSplash() {
        String str = StartAppSDKInternal.a;
        StartAppSDKInternal.c.a.y = true;
        v6.a.b(AdPreferences.Placement.INAPP_SPLASH);
    }

    public static void enableAutoInterstitial() {
        m5.a.a.a = true;
    }

    public static void enableConsent(Context context, boolean z) {
        ComponentLocator.a(context).f().e = z;
    }

    public static void init(Context context, String str, String str2) {
        StartAppSDK.init(context, str, str2);
    }

    public static void onBackPressed(Context context) {
        new StartAppAd(context).onBackPressed();
    }

    public static void setAutoInterstitialPreferences(AutoInterstitialPreferences autoInterstitialPreferences) {
        m5 m5Var = m5.a.a;
        m5Var.b = autoInterstitialPreferences;
        m5Var.c = -1L;
        m5Var.d = -1;
    }

    public static void setCommonAdsPreferences(Context context, SDKAdPreferences sDKAdPreferences) {
        Context a2 = y8.a(context);
        if (a2 != null) {
            String str = StartAppSDKInternal.a;
            StartAppSDKInternal.c.a.d = sDKAdPreferences;
            h9.a(a2, "shared_prefs_sdk_ad_prefs", sDKAdPreferences);
        }
    }

    public static void setReturnAdsPreferences(AdPreferences adPreferences) {
        String str = StartAppSDKInternal.a;
        StartAppSDKInternal startAppSDKInternal = StartAppSDKInternal.c.a;
        boolean z = !aa.a(startAppSDKInternal.u, adPreferences);
        startAppSDKInternal.u = adPreferences != null ? new AdPreferences(adPreferences) : null;
        if (z) {
            v6 v6Var = v6.a;
            AdPreferences.Placement placement = AdPreferences.Placement.INAPP_RETURN;
            if (v6Var.e) {
                return;
            }
            synchronized (v6Var.b) {
                for (b7 b7Var : v6Var.b.values()) {
                    if (b7Var.a == placement) {
                        b7Var.b();
                    }
                }
            }
        }
    }

    public static boolean showAd(Context context) {
        if (context == null) {
            return false;
        }
        try {
            return new StartAppAd(context).showAd();
        } catch (Throwable th) {
            p7.a(context, th);
            return false;
        }
    }

    public static void showSplash(Activity activity, Bundle bundle) {
        showSplash(activity, bundle, new SplashConfig());
    }

    public static void showSplash(Activity activity, Bundle bundle, SplashConfig splashConfig) {
        showSplash(activity, bundle, splashConfig, new AdPreferences());
    }

    public static void showSplash(Activity activity, Bundle bundle, SplashConfig splashConfig, AdPreferences adPreferences) {
        showSplash(activity, bundle, splashConfig, adPreferences, null);
    }

    public static void showSplash(Activity activity, Bundle bundle, SplashConfig splashConfig, AdPreferences adPreferences, SplashHideListener splashHideListener) {
        a(activity, bundle, splashConfig, adPreferences, splashHideListener, true);
    }

    public static void showSplash(Activity activity, Bundle bundle, AdPreferences adPreferences) {
        showSplash(activity, bundle, new SplashConfig(), adPreferences);
    }

    public AdRulesResult a(String str, AdPreferences.Placement placement) {
        return AdsCommonMetaData.h.b().a(placement, str);
    }

    @Override // com.startapp.sdk.adsbase.Ad
    public void a(AdPreferences adPreferences, AdEventListener adEventListener) {
    }

    public void close() {
        la.a(this.b).a(this.callbackBroadcastReceiver);
        la.a(this.b).a(new Intent("com.startapp.android.CloseAdActivity"));
    }

    @Override // com.startapp.sdk.adsbase.Ad
    public AdPreferences.Placement f() {
        CacheKey cacheKey;
        AdPreferences.Placement placement = this.placement;
        return (placement != null || (cacheKey = this.adKey) == null || v6.a.a(cacheKey) == null) ? placement : ((Ad) v6.a.a(this.adKey)).f();
    }

    @Override // com.startapp.sdk.adsbase.Ad
    public String getAdId() {
        Object a2 = v6.a.a(this.adKey);
        if (a2 instanceof HtmlAd) {
            return ((HtmlAd) a2).getAdId();
        }
        return null;
    }

    @Override // com.startapp.sdk.adsbase.Ad
    public String getBidToken() {
        Object a2 = v6.a.a(this.adKey);
        if (a2 instanceof HtmlAd) {
            return aa.a(((HtmlAd) a2).j(), "bidToken", "bidToken");
        }
        return null;
    }

    @Override // com.startapp.sdk.adsbase.Ad
    public Ad.AdState getState() {
        n5 a2 = v6.a.a(this.adKey);
        return a2 != null ? a2.getState() : Ad.AdState.UN_INITIALIZED;
    }

    @Override // com.startapp.sdk.adsbase.Ad
    public boolean isBelowMinCPM() {
        n5 a2 = v6.a.a(this.adKey);
        if (a2 != null) {
            return a2.isBelowMinCPM();
        }
        return false;
    }

    public boolean isNetworkAvailable() {
        return aa.g(this.b);
    }

    @Override // com.startapp.sdk.adsbase.Ad
    public boolean isReady() {
        n5 a2 = v6.a.a(this.adKey);
        if (a2 != null) {
            return a2.isReady();
        }
        return false;
    }

    @Override // com.startapp.sdk.adsbase.Ad, com.startapp.n5
    @Deprecated
    public boolean load(AdPreferences adPreferences, AdEventListener adEventListener) {
        if (MetaData.h.b()) {
            CacheKey a2 = v6.a.a(this.b, this, this.adMode, adPreferences, adEventListener);
            this.adKey = a2;
            return a2 != null;
        }
        if (adEventListener != null) {
            setErrorMessage("serving ads disabled");
            d.a(this.b, adEventListener, this);
        }
        return false;
    }

    public void loadAd() {
        loadAd(AdMode.AUTOMATIC, new AdPreferences(), null);
    }

    public void loadAd(AdMode adMode) {
        loadAd(adMode, new AdPreferences(), null);
    }

    public void loadAd(AdMode adMode, AdEventListener adEventListener) {
        loadAd(adMode, new AdPreferences(), adEventListener);
    }

    public void loadAd(AdMode adMode, AdPreferences adPreferences) {
        loadAd(adMode, adPreferences, null);
    }

    public void loadAd(AdMode adMode, AdPreferences adPreferences, AdEventListener adEventListener) {
        try {
            ComponentLocator.a(this.b).q().a(16384);
        } catch (Throwable unused) {
        }
        this.adMode = adMode;
        this.adPreferences = adPreferences;
        try {
            load(adPreferences, adEventListener);
        } catch (Throwable th) {
            p7.a(this.b, th);
            if (adEventListener != null) {
                d.a(this.b, adEventListener, this);
            }
        }
    }

    public void loadAd(AdEventListener adEventListener) {
        loadAd(AdMode.AUTOMATIC, new AdPreferences(), adEventListener);
    }

    public void loadAd(AdPreferences adPreferences) {
        loadAd(AdMode.AUTOMATIC, adPreferences, null);
    }

    public void loadAd(AdPreferences adPreferences, AdEventListener adEventListener) {
        loadAd(AdMode.AUTOMATIC, adPreferences, adEventListener);
    }

    public CacheKey loadSplash(AdPreferences adPreferences, AdEventListener adEventListener) {
        v6 v6Var = v6.a;
        Context context = this.b;
        AdPreferences.Placement placement = AdPreferences.Placement.INAPP_SPLASH;
        CacheKey a2 = v6Var.a(placement) ? v6Var.a(context, this, placement, adPreferences, adEventListener, false, 0) : null;
        this.adKey = a2;
        return a2;
    }

    public void onBackPressed() {
        showAd("exit_ad");
        String str = StartAppSDKInternal.a;
        StartAppSDKInternal startAppSDKInternal = StartAppSDKInternal.c.a;
        startAppSDKInternal.g = false;
        startAppSDKInternal.i = true;
    }

    public void onPause() {
    }

    public void onRestoreInstanceState(Bundle bundle) {
        int i = bundle.getInt("AdMode");
        this.adMode = AdMode.AUTOMATIC;
        if (i == 1) {
            this.adMode = AdMode.FULLPAGE;
        } else if (i == 2) {
            this.adMode = AdMode.OFFERWALL;
        } else if (i == 3) {
            this.adMode = AdMode.OVERLAY;
        } else if (i == 4) {
            this.adMode = AdMode.REWARDED_VIDEO;
        } else if (i == 5) {
            this.adMode = AdMode.VIDEO;
        }
        Serializable serializable = bundle.getSerializable("AdPrefs");
        if (serializable != null) {
            this.adPreferences = (AdPreferences) serializable;
        }
    }

    public void onResume() {
        if (isReady()) {
            return;
        }
        loadAd();
    }

    public void onSaveInstanceState(Bundle bundle) {
        int ordinal = this.adMode.ordinal();
        int i = 3;
        if (ordinal == 1) {
            i = 1;
        } else if (ordinal == 2) {
            i = 2;
        } else if (ordinal == 3) {
            i = 4;
        } else if (ordinal != 5) {
            i = 0;
        }
        AdPreferences adPreferences = this.adPreferences;
        if (adPreferences != null) {
            bundle.putSerializable("AdPrefs", adPreferences);
        }
        bundle.putInt("AdMode", i);
    }

    public void setVideoListener(com.startapp.sdk.adsbase.adlisteners.VideoListener videoListener) {
        this.videoListener = videoListener;
    }

    @Override // com.startapp.sdk.adsbase.Ad
    @Deprecated
    public boolean show() {
        return show(null, null);
    }

    /* JADX WARN: Code restructure failed: missing block: B:103:0x0166, code lost:
    
        if (r0 == false) goto L85;
     */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0184 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x01e2  */
    @java.lang.Deprecated
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean show(java.lang.String r12, com.startapp.sdk.adsbase.adlisteners.AdDisplayListener r13) {
        /*
            Method dump skipped, instructions count: 599
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.startapp.sdk.adsbase.StartAppAd.show(java.lang.String, com.startapp.sdk.adsbase.adlisteners.AdDisplayListener):boolean");
    }

    public boolean showAd() {
        return showAd(null, null);
    }

    public boolean showAd(AdDisplayListener adDisplayListener) {
        return showAd(null, adDisplayListener);
    }

    public boolean showAd(String str) {
        return showAd(str, null);
    }

    public boolean showAd(String str, AdDisplayListener adDisplayListener) {
        try {
            return show(str, adDisplayListener);
        } catch (Throwable th) {
            p7.a(this.b, th);
            a(NotDisplayedReason.INTERNAL_ERROR);
            d.a(this.b, this.callback, (Ad) null);
            return false;
        }
    }
}
