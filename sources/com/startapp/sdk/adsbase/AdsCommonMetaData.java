package com.startapp.sdk.adsbase;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.ViewCompat;
import com.startapp.aa;
import com.startapp.f;
import com.startapp.h9;
import com.startapp.p7;
import com.startapp.q7;
import com.startapp.sdk.adsbase.adrules.AdRules;
import com.startapp.sdk.adsbase.remoteconfig.MetaDataStyle;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class AdsCommonMetaData implements Serializable {
    public static transient Object a = new Object();
    public static final Integer b = 18;
    public static final Integer c = -1;
    public static final Set<String> d = new HashSet(Arrays.asList("BOLD"));
    public static final Integer e = Integer.valueOf(ViewCompat.MEASURED_STATE_MASK);
    public static final Integer f = -14803426;
    public static final Integer g = -1;
    public static AdsCommonMetaData h = new AdsCommonMetaData();
    private static final long serialVersionUID = 1;
    private Long explicitLoadIntervalMillis;
    private String acMetadataUpdateVersion = "4.9.1";
    private Integer probability3D = 0;
    private Integer homeProbability3D = 80;
    private Integer fullpageOfferWallProbability = 100;
    private Integer fullpageOverlayProbability = 0;
    private Integer backgroundGradientTop = -14606047;
    private Integer backgroundGradientBottom = -14606047;
    private Integer maxAds = 10;
    private Integer titleBackgroundColor = -14803426;
    private String titleContent = "Recommended for you";
    private Integer titleTextSize = b;
    private Integer titleTextColor = c;

    @f(type = HashSet.class)
    private Set<String> titleTextDecoration = d;
    private Integer titleLineColor = e;
    private Integer itemGradientTop = -14014151;
    private Integer itemGradientBottom = -8750199;
    private Integer itemTitleTextSize = MetaDataStyle.a;
    private Integer itemTitleTextColor = MetaDataStyle.b;

    @f(type = HashSet.class)
    private Set<String> itemTitleTextDecoration = MetaDataStyle.c;
    private Integer itemDescriptionTextSize = MetaDataStyle.d;
    private Integer itemDescriptionTextColor = MetaDataStyle.e;

    @f(type = HashSet.class)
    private Set<String> itemDescriptionTextDecoration = MetaDataStyle.f;

    @f(type = HashMap.class, value = MetaDataStyle.class)
    private HashMap<String, MetaDataStyle> templates = new HashMap<>();

    @f(complex = true)
    private AdRules adRules = new AdRules();
    private Integer poweredByBackgroundColor = f;
    private Integer poweredByTextColor = g;
    private long returnAdMinBackgroundTime = 300;
    private boolean disableReturnAd = false;
    private boolean disableSplashAd = false;
    private int smartRedirectTimeout = 5;
    private long smartRedirectLoadedTimeout = 1000;
    private boolean enableSmartRedirect = true;
    private boolean autoInterstitialEnabled = true;
    private int defaultActivitiesBetweenAds = 1;
    private int defaultSecondsBetweenAds = 0;
    private boolean disableTwoClicks = false;
    private boolean appPresence = true;
    private boolean disableInAppStore = false;

    @f(complex = true)
    private VideoConfig video = new VideoConfig();
    private int forceExternalBrowserDaysInterval = 7;
    private boolean enableForceExternalBrowser = false;
    private boolean enforceForeground = false;

    public static void a(Context context) {
        AdsCommonMetaData adsCommonMetaData = (AdsCommonMetaData) h9.a(context, "StartappAdsMetadata", AdsCommonMetaData.class);
        AdsCommonMetaData adsCommonMetaData2 = new AdsCommonMetaData();
        if (adsCommonMetaData == null) {
            h = adsCommonMetaData2;
            return;
        }
        boolean b2 = aa.b(adsCommonMetaData, adsCommonMetaData2);
        if (!(!"4.9.1".equals(adsCommonMetaData.acMetadataUpdateVersion)) && b2) {
            p7 p7Var = new p7(q7.c);
            p7Var.d = "metadata_null";
            p7Var.a(context);
        }
        AdRules adRules = adsCommonMetaData.adRules;
        adRules.getClass();
        adRules.a = new HashSet();
        h = adsCommonMetaData;
    }

    public static void a(Context context, AdsCommonMetaData adsCommonMetaData) {
        synchronized (a) {
            adsCommonMetaData.acMetadataUpdateVersion = "4.9.1";
            h = adsCommonMetaData;
            h9.b(context, null, "StartappAdsMetadata", adsCommonMetaData);
        }
    }

    public static AdsCommonMetaData k() {
        return h;
    }

    public Integer A() {
        return this.titleBackgroundColor;
    }

    public String B() {
        return this.titleContent;
    }

    public Integer C() {
        return this.titleLineColor;
    }

    public Integer D() {
        return this.titleTextColor;
    }

    public Set<String> E() {
        return this.titleTextDecoration;
    }

    public Integer F() {
        return this.titleTextSize;
    }

    public VideoConfig G() {
        return this.video;
    }

    public boolean H() {
        return this.appPresence;
    }

    public boolean I() {
        return this.autoInterstitialEnabled;
    }

    public boolean J() {
        return this.disableInAppStore;
    }

    public boolean K() {
        return this.disableReturnAd;
    }

    public boolean L() {
        return this.disableSplashAd;
    }

    public boolean M() {
        return this.disableTwoClicks;
    }

    public boolean N() {
        return this.enableSmartRedirect;
    }

    public boolean O() {
        return this.enforceForeground;
    }

    public MetaDataStyle a(String str) {
        return this.templates.get(str);
    }

    public boolean a() {
        return this.enableForceExternalBrowser;
    }

    public AdRules b() {
        return this.adRules;
    }

    public int c() {
        return this.backgroundGradientBottom.intValue();
    }

    public int d() {
        return this.backgroundGradientTop.intValue();
    }

    public int e() {
        return this.forceExternalBrowserDaysInterval;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || AdsCommonMetaData.class != obj.getClass()) {
            return false;
        }
        AdsCommonMetaData adsCommonMetaData = (AdsCommonMetaData) obj;
        return this.returnAdMinBackgroundTime == adsCommonMetaData.returnAdMinBackgroundTime && this.disableReturnAd == adsCommonMetaData.disableReturnAd && this.disableSplashAd == adsCommonMetaData.disableSplashAd && this.smartRedirectTimeout == adsCommonMetaData.smartRedirectTimeout && this.smartRedirectLoadedTimeout == adsCommonMetaData.smartRedirectLoadedTimeout && this.enableSmartRedirect == adsCommonMetaData.enableSmartRedirect && this.autoInterstitialEnabled == adsCommonMetaData.autoInterstitialEnabled && this.defaultActivitiesBetweenAds == adsCommonMetaData.defaultActivitiesBetweenAds && this.defaultSecondsBetweenAds == adsCommonMetaData.defaultSecondsBetweenAds && this.disableTwoClicks == adsCommonMetaData.disableTwoClicks && this.appPresence == adsCommonMetaData.appPresence && this.disableInAppStore == adsCommonMetaData.disableInAppStore && this.forceExternalBrowserDaysInterval == adsCommonMetaData.forceExternalBrowserDaysInterval && this.enableForceExternalBrowser == adsCommonMetaData.enableForceExternalBrowser && this.enforceForeground == adsCommonMetaData.enforceForeground && aa.a(this.acMetadataUpdateVersion, adsCommonMetaData.acMetadataUpdateVersion) && aa.a(this.probability3D, adsCommonMetaData.probability3D) && aa.a(this.homeProbability3D, adsCommonMetaData.homeProbability3D) && aa.a(this.fullpageOfferWallProbability, adsCommonMetaData.fullpageOfferWallProbability) && aa.a(this.fullpageOverlayProbability, adsCommonMetaData.fullpageOverlayProbability) && aa.a(this.backgroundGradientTop, adsCommonMetaData.backgroundGradientTop) && aa.a(this.backgroundGradientBottom, adsCommonMetaData.backgroundGradientBottom) && aa.a(this.maxAds, adsCommonMetaData.maxAds) && aa.a(this.explicitLoadIntervalMillis, adsCommonMetaData.explicitLoadIntervalMillis) && aa.a(this.titleBackgroundColor, adsCommonMetaData.titleBackgroundColor) && aa.a(this.titleContent, adsCommonMetaData.titleContent) && aa.a(this.titleTextSize, adsCommonMetaData.titleTextSize) && aa.a(this.titleTextColor, adsCommonMetaData.titleTextColor) && aa.a(this.titleTextDecoration, adsCommonMetaData.titleTextDecoration) && aa.a(this.titleLineColor, adsCommonMetaData.titleLineColor) && aa.a(this.itemGradientTop, adsCommonMetaData.itemGradientTop) && aa.a(this.itemGradientBottom, adsCommonMetaData.itemGradientBottom) && aa.a(this.itemTitleTextSize, adsCommonMetaData.itemTitleTextSize) && aa.a(this.itemTitleTextColor, adsCommonMetaData.itemTitleTextColor) && aa.a(this.itemTitleTextDecoration, adsCommonMetaData.itemTitleTextDecoration) && aa.a(this.itemDescriptionTextSize, adsCommonMetaData.itemDescriptionTextSize) && aa.a(this.itemDescriptionTextColor, adsCommonMetaData.itemDescriptionTextColor) && aa.a(this.itemDescriptionTextDecoration, adsCommonMetaData.itemDescriptionTextDecoration) && aa.a(this.templates, adsCommonMetaData.templates) && aa.a(this.adRules, adsCommonMetaData.adRules) && aa.a(this.poweredByBackgroundColor, adsCommonMetaData.poweredByBackgroundColor) && aa.a(this.poweredByTextColor, adsCommonMetaData.poweredByTextColor) && aa.a(this.video, adsCommonMetaData.video);
    }

    public int f() {
        return this.defaultActivitiesBetweenAds;
    }

    public int g() {
        return this.defaultSecondsBetweenAds;
    }

    public Long h() {
        return this.explicitLoadIntervalMillis;
    }

    public int hashCode() {
        Object[] objArr = {this.acMetadataUpdateVersion, this.probability3D, this.homeProbability3D, this.fullpageOfferWallProbability, this.fullpageOverlayProbability, this.backgroundGradientTop, this.backgroundGradientBottom, this.maxAds, this.explicitLoadIntervalMillis, this.titleBackgroundColor, this.titleContent, this.titleTextSize, this.titleTextColor, this.titleTextDecoration, this.titleLineColor, this.itemGradientTop, this.itemGradientBottom, this.itemTitleTextSize, this.itemTitleTextColor, this.itemTitleTextDecoration, this.itemDescriptionTextSize, this.itemDescriptionTextColor, this.itemDescriptionTextDecoration, this.templates, this.adRules, this.poweredByBackgroundColor, this.poweredByTextColor, Long.valueOf(this.returnAdMinBackgroundTime), Boolean.valueOf(this.disableReturnAd), Boolean.valueOf(this.disableSplashAd), Integer.valueOf(this.smartRedirectTimeout), Long.valueOf(this.smartRedirectLoadedTimeout), Boolean.valueOf(this.enableSmartRedirect), Boolean.valueOf(this.autoInterstitialEnabled), Integer.valueOf(this.defaultActivitiesBetweenAds), Integer.valueOf(this.defaultSecondsBetweenAds), Boolean.valueOf(this.disableTwoClicks), Boolean.valueOf(this.appPresence), Boolean.valueOf(this.disableInAppStore), this.video, Integer.valueOf(this.forceExternalBrowserDaysInterval), Boolean.valueOf(this.enableForceExternalBrowser), Boolean.valueOf(this.enforceForeground)};
        Map<Activity, Integer> map = aa.a;
        return Arrays.deepHashCode(objArr);
    }

    public int i() {
        return this.fullpageOfferWallProbability.intValue();
    }

    public int j() {
        return this.fullpageOverlayProbability.intValue();
    }

    public Integer l() {
        return this.itemDescriptionTextColor;
    }

    public Set<String> m() {
        return this.itemDescriptionTextDecoration;
    }

    public Integer n() {
        return this.itemDescriptionTextSize;
    }

    public int o() {
        return this.itemGradientBottom.intValue();
    }

    public int p() {
        return this.itemGradientTop.intValue();
    }

    public Integer q() {
        return this.itemTitleTextColor;
    }

    public Set<String> r() {
        return this.itemTitleTextDecoration;
    }

    public Integer s() {
        return this.itemTitleTextSize;
    }

    public int t() {
        return this.maxAds.intValue();
    }

    public Integer u() {
        return this.poweredByBackgroundColor;
    }

    public Integer v() {
        return this.poweredByTextColor;
    }

    public int w() {
        return this.probability3D.intValue();
    }

    public long x() {
        return TimeUnit.SECONDS.toMillis(this.returnAdMinBackgroundTime);
    }

    public long y() {
        return this.smartRedirectLoadedTimeout;
    }

    public long z() {
        return TimeUnit.SECONDS.toMillis(this.smartRedirectTimeout);
    }
}
