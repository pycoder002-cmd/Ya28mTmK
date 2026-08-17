package com.startapp.sdk.adsbase;

import android.app.Activity;
import android.content.Context;
import com.startapp.aa;
import com.startapp.d;
import com.startapp.sdk.adsbase.adinformation.AdInformationOverrides;
import com.startapp.sdk.adsbase.adlisteners.AdEventListener;
import com.startapp.sdk.adsbase.adlisteners.NotDisplayedReason;
import com.startapp.sdk.adsbase.cache.CacheMetaData;
import com.startapp.sdk.adsbase.consent.ConsentData;
import com.startapp.sdk.adsbase.model.AdPreferences;
import com.startapp.sdk.adsbase.remoteconfig.MetaData;
import com.startapp.sdk.adsbase.remoteconfig.MetaDataRequest;
import com.startapp.sdk.components.ComponentLocator;
import com.startapp.t8;
import com.startapp.x9;
import java.io.Serializable;
import java.util.Map;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public abstract class Ad implements Serializable {
    public static boolean a = false;
    private static final long serialVersionUID = 1;
    public ActivityExtra activityExtra;
    private AdInformationOverrides adInfoOverride;
    public transient Context b;
    public ConsentData consentData;
    public String errorMessage;
    private NotDisplayedReason notDisplayedReason;
    public AdPreferences.Placement placement;
    private AdType type;
    private boolean videoCancelCallBack;
    public Serializable extraData = null;
    public Long adCacheTtl = null;
    private AdState state = AdState.UN_INITIALIZED;
    private Long lastLoadTime = null;
    public boolean belowMinCPM = false;

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public enum AdState {
        UN_INITIALIZED,
        PROCESSING,
        READY
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public enum AdType {
        INTERSTITIAL,
        RICH_TEXT,
        VIDEO,
        REWARDED_VIDEO,
        NON_VIDEO,
        VIDEO_NO_VAST
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class a implements AdEventListener {
        public final /* synthetic */ AdEventListener a;

        public a(AdEventListener adEventListener) {
            this.a = adEventListener;
        }

        @Override // com.startapp.sdk.adsbase.adlisteners.AdEventListener
        public void onFailedToReceiveAd(Ad ad) {
            d.a(Ad.this.b, this.a, ad);
            String errorMessage = ad != null ? ad.getErrorMessage() : null;
            if (errorMessage == null) {
                errorMessage = "";
            } else if (errorMessage.contains("204")) {
                errorMessage = "NO FILL";
            }
            Context context = Ad.this.b;
            StringBuilder sb = new StringBuilder();
            sb.append("Failed to load ");
            sb.append(ad != null ? aa.a(ad) : "");
            sb.append(" ad: ");
            sb.append(errorMessage);
            aa.a(context, true, sb.toString(), true);
        }

        @Override // com.startapp.sdk.adsbase.adlisteners.AdEventListener
        public void onReceiveAd(Ad ad) {
            Ad.a(Ad.this, Long.valueOf(System.currentTimeMillis()));
            d.b(Ad.this.b, this.a, ad);
            ConsentData consentData = ad.getConsentData();
            if (consentData != null) {
                ComponentLocator.a(Ad.this.b).f().a(consentData.f(), consentData.e(), consentData.a(), false, true);
            }
            aa.a(Ad.this.b, false, "Loaded " + aa.a(ad) + " ad with creative ID - " + ad.getAdId(), true);
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class b implements t8 {
        public final /* synthetic */ AdPreferences a;
        public final /* synthetic */ AdEventListener b;

        public b(AdPreferences adPreferences, AdEventListener adEventListener) {
            this.a = adPreferences;
            this.b = adEventListener;
        }

        @Override // com.startapp.t8
        public void a(MetaDataRequest.RequestReason requestReason) {
            Ad.this.a(this.a, this.b);
        }

        @Override // com.startapp.t8
        public void a(MetaDataRequest.RequestReason requestReason, boolean z) {
            Ad.this.a(this.a, this.b);
        }
    }

    public Ad(Context context, AdPreferences.Placement placement) {
        this.b = context;
        this.placement = placement;
        Map<Activity, Integer> map = aa.a;
        this.adInfoOverride = AdInformationOverrides.a();
    }

    public static void a(Ad ad, Long l) {
        ad.lastLoadTime = l;
    }

    public void a(NotDisplayedReason notDisplayedReason) {
        this.notDisplayedReason = notDisplayedReason;
    }

    public abstract void a(AdPreferences adPreferences, AdEventListener adEventListener);

    public void a(boolean z) {
        this.videoCancelCallBack = z;
    }

    public boolean a() {
        return this.videoCancelCallBack;
    }

    public Long b() {
        return this.lastLoadTime;
    }

    public Long c() {
        long e = e();
        Long l = this.adCacheTtl;
        if (l != null) {
            e = Math.min(l.longValue(), e);
        }
        return Long.valueOf(e);
    }

    public boolean d() {
        return this.lastLoadTime != null && System.currentTimeMillis() - this.lastLoadTime.longValue() > c().longValue();
    }

    public long e() {
        return CacheMetaData.a.a().a();
    }

    public AdPreferences.Placement f() {
        return this.placement;
    }

    public abstract String getAdId();

    public AdInformationOverrides getAdInfoOverride() {
        return this.adInfoOverride;
    }

    public abstract String getBidToken();

    public final ConsentData getConsentData() {
        return this.consentData;
    }

    public Context getContext() {
        return this.b;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public Serializable getExtraData() {
        return this.extraData;
    }

    public NotDisplayedReason getNotDisplayedReason() {
        return this.notDisplayedReason;
    }

    public AdState getState() {
        return this.state;
    }

    public AdType getType() {
        return this.type;
    }

    public boolean isBelowMinCPM() {
        return this.belowMinCPM;
    }

    public boolean isReady() {
        return this.state == AdState.READY && !d();
    }

    @Deprecated
    public boolean load() {
        return load(new AdPreferences(), null);
    }

    @Deprecated
    public boolean load(AdEventListener adEventListener) {
        return load(new AdPreferences(), adEventListener);
    }

    @Deprecated
    public boolean load(AdPreferences adPreferences) {
        return load(adPreferences, null);
    }

    @Deprecated
    public boolean load(AdPreferences adPreferences, AdEventListener adEventListener) {
        return load(adPreferences, adEventListener, true);
    }

    public boolean load(AdPreferences adPreferences, AdEventListener adEventListener, boolean z) {
        String str;
        boolean z2;
        a aVar = new a(adEventListener);
        if (!a) {
            SimpleTokenUtils.f(this.b);
            a = true;
        }
        if (this.state != AdState.UN_INITIALIZED) {
            str = "load() was already called.";
            z2 = true;
        } else {
            str = "";
            z2 = false;
        }
        if (!aa.g(this.b)) {
            str = "network not available.";
            z2 = true;
        }
        if (!MetaData.h.b()) {
            str = "serving ads disabled";
            z2 = true;
        }
        if (z2) {
            setErrorMessage("Ad wasn't loaded: " + str);
            d.a(this.b, aVar, this);
            return false;
        }
        setState(AdState.PROCESSING);
        b bVar = new b(adPreferences, aVar);
        if (adPreferences.getType() != null) {
            this.type = adPreferences.getType();
        }
        MetaData metaData = MetaData.h;
        Context context = this.b;
        x9 x9Var = x9.a;
        metaData.a(context, adPreferences, x9.a.d, z, bVar, false);
        return true;
    }

    public void setActivityExtra(ActivityExtra activityExtra) {
        this.activityExtra = activityExtra;
    }

    public void setAdInfoOverride(AdInformationOverrides adInformationOverrides) {
        this.adInfoOverride = adInformationOverrides;
    }

    public void setContext(Context context) {
        this.b = context;
    }

    public void setErrorMessage(String str) {
        this.errorMessage = str;
    }

    public void setExtraData(Serializable serializable) {
        this.extraData = serializable;
    }

    public void setState(AdState adState) {
        this.state = adState;
    }

    @Deprecated
    public boolean show() {
        return false;
    }
}
