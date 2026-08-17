package com.startapp.sdk.adsbase.remoteconfig;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import com.blankj.utilcode.util.CacheUtils;
import com.startapp.a9;
import com.startapp.aa;
import com.startapp.b9;
import com.startapp.f;
import com.startapp.h9;
import com.startapp.p5;
import com.startapp.p7;
import com.startapp.q7;
import com.startapp.r8;
import com.startapp.s8;
import com.startapp.sdk.adsbase.AdsConstants;
import com.startapp.sdk.adsbase.consent.ConsentConfig;
import com.startapp.sdk.adsbase.model.AdPreferences;
import com.startapp.sdk.adsbase.remoteconfig.MetaDataRequest;
import com.startapp.sdk.common.Constants;
import com.startapp.sdk.components.ComponentLocator;
import com.startapp.sdk.insight.NetworkTestsMetaData;
import com.startapp.sdk.triggeredlinks.TriggeredLinksMetadata;
import com.startapp.t8;
import com.startapp.ya;
import com.startapp.z8;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class MetaData implements Serializable {
    public static final Object a = new Object();
    public static final AtomicBoolean b = new AtomicBoolean();
    public static final Set<String> c = new HashSet(Arrays.asList(Constants.a));
    public static final List<String> d = Arrays.asList("https://adsmetadata.startappservice.com/adsmetadata/api/v1.0/", "https://adsmetadata.mobileadexchange.net/adsmetadata/api/v1.0/", "https://d26xw8rp6mlgfg.cloudfront.net/adsmetadata/api/v1.0/");
    public static final String e = "https://req.startappservice.com/1.5/";
    public static final int[] f = {60, 60, 240};
    public static final Set<String> g = new HashSet(Arrays.asList("com.facebook.katana", "com.yandex.browser"));
    public static volatile MetaData h = new MetaData();
    public static s8 i = null;
    private static final long serialVersionUID = -738319725316091245L;
    private long IABDisplayImpressionDelayInSeconds;
    private long IABVideoImpressionDelayInSeconds;
    private boolean SupportIABViewability;
    private String adPlatformBannerHostSecured;
    public String adPlatformHostSecured;
    private String adPlatformNativeHostSecured;
    private String adPlatformOverlayHostSecured;
    private String adPlatformReturnHostSecured;
    private String adPlatformSplashHostSecured;

    @f(complex = true)
    private AdvertisingIdResolverMetadata air;
    private boolean alwaysSendToken;

    @f(complex = true)
    public AnalyticsConfig analytics;
    private String assetsBaseUrlSecured;

    @f(complex = true)
    private BluetoothConfig btConfig;
    private String calcProd;
    private boolean chromeCustomeTabsExternal;
    private boolean chromeCustomeTabsInternal;
    private boolean compressionEnabled;

    @f(complex = true)
    private ConsentConfig consentDetails;
    private boolean disableSendAdvertisingId;
    private boolean dns;
    private double ibt;
    private boolean inAppBrowser;

    @f(type = HashSet.class)
    private Set<String> installersList;

    @f(type = HashSet.class)
    private Set<Integer> invalidForRetry;
    private boolean isToken1Mandatory;
    public transient boolean j;
    public transient boolean k;
    public transient List<t8> l;
    private String metadataUpdateVersion;

    @f(complex = true)
    private MotionMetadata motion;

    @f(complex = true)
    private NetworkDiagnosticConfig netDiag;

    @f(complex = true)
    private NetworkTestsMetaData networkTests;
    private int notVisibleBannerReloadInterval;
    private boolean omSdkEnabled;
    private int[] periodicEventIntMin;
    private int[] periodicForegroundEventSec;
    private boolean periodicInfoEventEnabled;
    private boolean periodicMetaDataEnabled;
    private int periodicMetaDataIntervalInMinutes;

    @f(type = HashSet.class)
    private Set<String> preInstalledPackages;
    private String profileId;

    @f(complex = true)
    private RcdMetadata rcd;

    @f(complex = true)
    private RscMetadata rsc;

    @f(complex = true)
    private SensorsConfig sensorsConfig;
    private int sessionMaxBackgroundTime;
    private boolean simpleToken2;

    @f(complex = true)
    private StaleDcConfig staleDc;
    private int stopAutoLoadAmount;
    private int stopAutoLoadPreCacheAmount;

    @f(complex = true)
    private TelephonyMetadata telephony;
    private String trackDownloadHost;

    @f(complex = true)
    private TriggeredLinksMetadata triggeredLinks;
    private boolean trueNetEnabled;
    private String vastRecorderHost;
    private boolean webViewSecured;

    @f(complex = true)
    private SimpleTokenConfig SimpleToken = new SimpleTokenConfig();

    @f(type = ArrayList.class)
    public List<String> metaDataHosts = d;

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public static class a implements b9.b {
        public Context a;
        public String b;

        public a(Context context, String str) {
            this.a = context;
            this.b = str;
        }

        @Override // com.startapp.b9.b
        public void a(Bitmap bitmap, int i) {
            if (bitmap != null) {
                Context context = this.a;
                String str = this.b;
                Map<String, Bitmap> map = a9.a;
                ComponentLocator.a(context).h().execute(new z8(str, ".png", bitmap, context));
            }
        }
    }

    public MetaData() {
        String str = e;
        this.adPlatformHostSecured = str;
        this.trackDownloadHost = str;
        this.sessionMaxBackgroundTime = 1800;
        this.profileId = null;
        this.installersList = c;
        this.preInstalledPackages = g;
        this.simpleToken2 = true;
        this.alwaysSendToken = true;
        this.isToken1Mandatory = true;
        this.compressionEnabled = false;
        this.periodicMetaDataEnabled = false;
        this.periodicMetaDataIntervalInMinutes = 360;
        this.periodicInfoEventEnabled = false;
        this.periodicEventIntMin = f;
        this.inAppBrowser = true;
        this.SupportIABViewability = true;
        this.IABDisplayImpressionDelayInSeconds = 1L;
        this.IABVideoImpressionDelayInSeconds = 2L;
        this.sensorsConfig = new SensorsConfig();
        this.btConfig = new BluetoothConfig();
        this.assetsBaseUrlSecured = "";
        this.invalidForRetry = null;
        this.notVisibleBannerReloadInterval = CacheUtils.TIME_HOUR;
        this.analytics = new AnalyticsConfig();
        this.j = false;
        this.k = false;
        this.l = new ArrayList();
        this.metadataUpdateVersion = "4.9.1";
        this.dns = false;
        this.stopAutoLoadAmount = 3;
        this.stopAutoLoadPreCacheAmount = 3;
        this.trueNetEnabled = false;
        this.webViewSecured = true;
        this.omSdkEnabled = false;
        this.chromeCustomeTabsInternal = true;
        this.chromeCustomeTabsExternal = true;
        this.disableSendAdvertisingId = false;
        this.ibt = 1.0d;
        this.networkTests = new NetworkTestsMetaData();
        this.staleDc = new StaleDcConfig();
        this.telephony = new TelephonyMetadata();
    }

    public static int a(Context context, int[] iArr) {
        if (iArr == null || iArr.length < 3) {
            iArr = f;
        }
        if (ya.a(context, "android.permission.ACCESS_FINE_LOCATION")) {
            int i2 = iArr[0];
            return i2 <= 0 ? f[0] : i2;
        }
        if (!ya.a(context, "android.permission.ACCESS_COARSE_LOCATION")) {
            return iArr[2];
        }
        int i3 = iArr[1];
        return i3 <= 0 ? f[1] : i3;
    }

    public static void a(Context context, MetaData metaData, MetaDataRequest.RequestReason requestReason, boolean z) {
        ArrayList arrayList;
        synchronized (a) {
            if (h.l != null) {
                arrayList = new ArrayList(h.l);
                h.l.clear();
            } else {
                arrayList = null;
            }
            metaData.l = h.l;
            metaData.a();
            metaData.metadataUpdateVersion = "4.9.1";
            h9.a(context, "StartappMetadata", metaData);
            metaData.j = false;
            metaData.k = true;
            if (!aa.a(h, metaData)) {
                z = true;
            }
            h = metaData;
            if (aa.e(context)) {
                try {
                    p5 d2 = ComponentLocator.a(context).d();
                    int i2 = d2.getInt("totalSessions", 0);
                    p5.a edit = d2.edit();
                    int i3 = i2 + 1;
                    edit.a("totalSessions", (String) Integer.valueOf(i3));
                    edit.a.putInt("totalSessions", i3);
                    edit.apply();
                } catch (Throwable th) {
                    p7.a(context, th);
                }
            }
            i = null;
        }
        if (arrayList != null) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ((t8) it.next()).a(requestReason, z);
            }
        }
    }

    public static void a(Context context, String str) {
        if (str == null || str.equals("")) {
            return;
        }
        if (!a9.a(context, "close_button", ".png")) {
            Map<Activity, Integer> map = aa.a;
            new b9(context, str + "close_button.png", new a(context, "close_button"), 0).a();
        }
        Map<Activity, Integer> map2 = aa.a;
        for (String str2 : AdsConstants.i) {
            if (!a9.a(context, str2, ".png")) {
                new b9(context, str + str2 + ".png", new a(context, str2), 0).a();
            }
        }
        Map<Activity, Integer> map3 = aa.a;
        for (String str3 : AdsConstants.j) {
            if (!a9.a(context, str3, ".png")) {
                new b9(context, str + str3 + ".png", new a(context, str3), 0).a();
            }
        }
        if (a9.a(context, "logo", ".png")) {
            return;
        }
        new b9(context, str + "logo.png", new a(context, "logo"), 0).a();
    }

    public static void a(MetaDataRequest.RequestReason requestReason) {
        ArrayList arrayList;
        synchronized (a) {
            if (h.l != null) {
                arrayList = new ArrayList(h.l);
                h.l.clear();
            } else {
                arrayList = null;
            }
            h.j = false;
        }
        if (arrayList != null) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ((t8) it.next()).a(requestReason);
            }
        }
    }

    public static void c(Context context) {
        if (b.getAndSet(true)) {
            return;
        }
        MetaData metaData = (MetaData) h9.a(context, "StartappMetadata", MetaData.class);
        MetaData metaData2 = new MetaData();
        if (metaData != null) {
            boolean b2 = aa.b(metaData, metaData2);
            if (!(true ^ "4.9.1".equals(metaData.metadataUpdateVersion)) && b2) {
                p7 p7Var = new p7(q7.c);
                p7Var.d = "metadata_null";
                p7Var.a(context);
            }
            metaData.j = false;
            metaData.k = false;
            metaData.l = new ArrayList();
            h = metaData;
        } else {
            h = metaData2;
        }
        h.a();
    }

    public static MetaData q() {
        return h;
    }

    public RscMetadata A() {
        return this.rsc;
    }

    public SensorsConfig B() {
        return this.sensorsConfig;
    }

    public long C() {
        return TimeUnit.SECONDS.toMillis(this.sessionMaxBackgroundTime);
    }

    public SimpleTokenConfig D() {
        return this.SimpleToken;
    }

    public StaleDcConfig E() {
        return this.staleDc;
    }

    public int F() {
        return this.stopAutoLoadAmount;
    }

    public int G() {
        return this.stopAutoLoadPreCacheAmount;
    }

    public TelephonyMetadata H() {
        return this.telephony;
    }

    public String I() {
        String str = this.trackDownloadHost;
        return str != null ? str : c();
    }

    public TriggeredLinksMetadata J() {
        return this.triggeredLinks;
    }

    public String K() {
        return this.vastRecorderHost;
    }

    public boolean L() {
        return this.alwaysSendToken;
    }

    public boolean M() {
        return this.compressionEnabled;
    }

    public boolean N() {
        Map<Activity, Integer> map = aa.a;
        return this.inAppBrowser;
    }

    public boolean O() {
        return this.omSdkEnabled;
    }

    public boolean P() {
        return this.periodicForegroundEventSec != null;
    }

    public boolean Q() {
        return this.periodicInfoEventEnabled;
    }

    public boolean R() {
        return this.periodicMetaDataEnabled;
    }

    public boolean S() {
        return this.SupportIABViewability;
    }

    public boolean T() {
        return this.isToken1Mandatory;
    }

    public int a(Context context) {
        return a(context, this.periodicForegroundEventSec);
    }

    public String a(AdPreferences.Placement placement) {
        String str;
        int ordinal = placement.ordinal();
        if (ordinal == 1) {
            String str2 = this.adPlatformBannerHostSecured;
            return str2 != null ? str2 : c();
        }
        if (ordinal == 7) {
            String str3 = this.adPlatformReturnHostSecured;
            return str3 != null ? str3 : c();
        }
        if (ordinal == 3) {
            String str4 = this.adPlatformSplashHostSecured;
            return str4 != null ? str4 : c();
        }
        if (ordinal != 4) {
            return (ordinal == 5 && (str = this.adPlatformNativeHostSecured) != null) ? str : c();
        }
        String str5 = this.adPlatformOverlayHostSecured;
        return str5 != null ? str5 : c();
    }

    public final String a(String str, String str2) {
        return str != null ? str.replace("%AdPlatformProtocol%", "1.5") : str2;
    }

    public void a() {
        ArrayList arrayList;
        this.adPlatformHostSecured = a(this.adPlatformHostSecured, e);
        List<String> list = this.metaDataHosts;
        if (list != null) {
            arrayList = new ArrayList(list.size());
            Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                String a2 = a(it.next(), (String) null);
                if (a2 != null) {
                    arrayList.add(a2);
                }
            }
        } else {
            arrayList = null;
        }
        this.metaDataHosts = arrayList;
        this.adPlatformBannerHostSecured = a(this.adPlatformBannerHostSecured, (String) null);
        this.adPlatformSplashHostSecured = a(this.adPlatformSplashHostSecured, (String) null);
        this.adPlatformReturnHostSecured = a(this.adPlatformReturnHostSecured, (String) null);
        this.adPlatformOverlayHostSecured = a(this.adPlatformOverlayHostSecured, (String) null);
        this.adPlatformNativeHostSecured = a(this.adPlatformNativeHostSecured, (String) null);
    }

    public void a(Context context, AdPreferences adPreferences, MetaDataRequest.RequestReason requestReason, boolean z, t8 t8Var, boolean z2) {
        if (!z && t8Var != null) {
            t8Var.a(requestReason, false);
        }
        synchronized (a) {
            if (h.k && !z2) {
                if (!z || t8Var == null) {
                    return;
                }
                t8Var.a(requestReason, false);
                return;
            }
            if (!h.j || z2) {
                this.j = true;
                this.k = false;
                s8 s8Var = i;
                if (s8Var != null) {
                    s8Var.j = true;
                }
                s8 s8Var2 = new s8(context, adPreferences, requestReason);
                i = s8Var2;
                ComponentLocator.a(context).o().execute(new r8(s8Var2));
            }
            if (z && t8Var != null) {
                h.a(t8Var);
            }
        }
    }

    public void a(t8 t8Var) {
        synchronized (a) {
            this.l.add(t8Var);
        }
    }

    public int b(Context context) {
        return a(context, this.periodicEventIntMin);
    }

    public boolean b() {
        return !this.dns;
    }

    public String c() {
        String str = this.adPlatformHostSecured;
        return str != null ? str : e;
    }

    public AdvertisingIdResolverMetadata d() {
        return this.air;
    }

    public String e() {
        String str = this.assetsBaseUrlSecured;
        return str != null ? str : "";
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || MetaData.class != obj.getClass()) {
            return false;
        }
        MetaData metaData = (MetaData) obj;
        return this.sessionMaxBackgroundTime == metaData.sessionMaxBackgroundTime && this.simpleToken2 == metaData.simpleToken2 && this.alwaysSendToken == metaData.alwaysSendToken && this.isToken1Mandatory == metaData.isToken1Mandatory && this.compressionEnabled == metaData.compressionEnabled && this.periodicMetaDataEnabled == metaData.periodicMetaDataEnabled && this.periodicMetaDataIntervalInMinutes == metaData.periodicMetaDataIntervalInMinutes && this.periodicInfoEventEnabled == metaData.periodicInfoEventEnabled && this.inAppBrowser == metaData.inAppBrowser && this.SupportIABViewability == metaData.SupportIABViewability && this.IABDisplayImpressionDelayInSeconds == metaData.IABDisplayImpressionDelayInSeconds && this.IABVideoImpressionDelayInSeconds == metaData.IABVideoImpressionDelayInSeconds && this.notVisibleBannerReloadInterval == metaData.notVisibleBannerReloadInterval && this.dns == metaData.dns && this.stopAutoLoadAmount == metaData.stopAutoLoadAmount && this.stopAutoLoadPreCacheAmount == metaData.stopAutoLoadPreCacheAmount && this.trueNetEnabled == metaData.trueNetEnabled && this.webViewSecured == metaData.webViewSecured && this.omSdkEnabled == metaData.omSdkEnabled && this.chromeCustomeTabsInternal == metaData.chromeCustomeTabsInternal && this.chromeCustomeTabsExternal == metaData.chromeCustomeTabsExternal && this.disableSendAdvertisingId == metaData.disableSendAdvertisingId && Double.compare(this.ibt, metaData.ibt) == 0 && aa.a(this.SimpleToken, metaData.SimpleToken) && aa.a(this.consentDetails, metaData.consentDetails) && aa.a(this.calcProd, metaData.calcProd) && aa.a(this.metaDataHosts, metaData.metaDataHosts) && aa.a(this.adPlatformHostSecured, metaData.adPlatformHostSecured) && aa.a(this.trackDownloadHost, metaData.trackDownloadHost) && aa.a(this.vastRecorderHost, metaData.vastRecorderHost) && aa.a(this.adPlatformBannerHostSecured, metaData.adPlatformBannerHostSecured) && aa.a(this.adPlatformSplashHostSecured, metaData.adPlatformSplashHostSecured) && aa.a(this.adPlatformReturnHostSecured, metaData.adPlatformReturnHostSecured) && aa.a(this.adPlatformOverlayHostSecured, metaData.adPlatformOverlayHostSecured) && aa.a(this.adPlatformNativeHostSecured, metaData.adPlatformNativeHostSecured) && aa.a(this.profileId, metaData.profileId) && aa.a(this.installersList, metaData.installersList) && aa.a(this.preInstalledPackages, metaData.preInstalledPackages) && Arrays.equals(this.periodicEventIntMin, metaData.periodicEventIntMin) && Arrays.equals(this.periodicForegroundEventSec, metaData.periodicForegroundEventSec) && aa.a(this.sensorsConfig, metaData.sensorsConfig) && aa.a(this.btConfig, metaData.btConfig) && aa.a(this.assetsBaseUrlSecured, metaData.assetsBaseUrlSecured) && aa.a(this.invalidForRetry, metaData.invalidForRetry) && aa.a(this.analytics, metaData.analytics) && aa.a(this.metadataUpdateVersion, metaData.metadataUpdateVersion) && aa.a(this.networkTests, metaData.networkTests) && aa.a(this.triggeredLinks, metaData.triggeredLinks) && aa.a(this.rsc, metaData.rsc) && aa.a(this.rcd, metaData.rcd) && aa.a(this.netDiag, metaData.netDiag) && aa.a(this.staleDc, metaData.staleDc) && aa.a(this.motion, metaData.motion) && aa.a(this.air, metaData.air) && aa.a(this.telephony, metaData.telephony);
    }

    public BluetoothConfig f() {
        return this.btConfig;
    }

    public String g() {
        return this.calcProd;
    }

    public boolean h() {
        return this.chromeCustomeTabsExternal;
    }

    public int hashCode() {
        Object[] objArr = {this.SimpleToken, this.consentDetails, this.calcProd, this.metaDataHosts, this.adPlatformHostSecured, this.trackDownloadHost, this.vastRecorderHost, this.adPlatformBannerHostSecured, this.adPlatformSplashHostSecured, this.adPlatformReturnHostSecured, this.adPlatformOverlayHostSecured, this.adPlatformNativeHostSecured, Integer.valueOf(this.sessionMaxBackgroundTime), this.profileId, this.installersList, this.preInstalledPackages, Boolean.valueOf(this.simpleToken2), Boolean.valueOf(this.alwaysSendToken), Boolean.valueOf(this.isToken1Mandatory), Boolean.valueOf(this.compressionEnabled), Boolean.valueOf(this.periodicMetaDataEnabled), Integer.valueOf(this.periodicMetaDataIntervalInMinutes), Boolean.valueOf(this.periodicInfoEventEnabled), this.periodicEventIntMin, this.periodicForegroundEventSec, Boolean.valueOf(this.inAppBrowser), Boolean.valueOf(this.SupportIABViewability), Long.valueOf(this.IABDisplayImpressionDelayInSeconds), Long.valueOf(this.IABVideoImpressionDelayInSeconds), this.sensorsConfig, this.btConfig, this.assetsBaseUrlSecured, this.invalidForRetry, Integer.valueOf(this.notVisibleBannerReloadInterval), this.analytics, this.metadataUpdateVersion, Boolean.valueOf(this.dns), Integer.valueOf(this.stopAutoLoadAmount), Integer.valueOf(this.stopAutoLoadPreCacheAmount), Boolean.valueOf(this.trueNetEnabled), Boolean.valueOf(this.webViewSecured), Boolean.valueOf(this.omSdkEnabled), Boolean.valueOf(this.chromeCustomeTabsInternal), Boolean.valueOf(this.chromeCustomeTabsExternal), Boolean.valueOf(this.disableSendAdvertisingId), Double.valueOf(this.ibt), this.networkTests, this.triggeredLinks, this.rsc, this.rcd, this.netDiag, this.staleDc, this.motion, this.air, this.telephony};
        Map<Activity, Integer> map = aa.a;
        return Arrays.deepHashCode(objArr);
    }

    public boolean i() {
        return this.chromeCustomeTabsInternal;
    }

    public ConsentConfig j() {
        return this.consentDetails;
    }

    public boolean k() {
        return this.disableSendAdvertisingId;
    }

    public String l() {
        int indexOf;
        String c2 = h.c();
        String str = (Build.VERSION.SDK_INT > 26 || this.webViewSecured) ? "https" : "http";
        if (c2.startsWith(str + "://") || (indexOf = c2.indexOf(58)) == -1) {
            return c2;
        }
        return str + c2.substring(indexOf);
    }

    public long m() {
        return this.IABDisplayImpressionDelayInSeconds;
    }

    public long n() {
        return this.IABVideoImpressionDelayInSeconds;
    }

    public double o() {
        return this.ibt;
    }

    public Set<String> p() {
        return this.installersList;
    }

    public Set<Integer> r() {
        return this.invalidForRetry;
    }

    public MotionMetadata s() {
        return this.motion;
    }

    public NetworkDiagnosticConfig t() {
        return this.netDiag;
    }

    public NetworkTestsMetaData u() {
        return this.networkTests;
    }

    public int v() {
        return this.notVisibleBannerReloadInterval;
    }

    public int w() {
        return this.periodicMetaDataIntervalInMinutes;
    }

    public Set<String> x() {
        Set<String> set = this.preInstalledPackages;
        if (set == null) {
            set = g;
        }
        return Collections.unmodifiableSet(set);
    }

    public String y() {
        return this.profileId;
    }

    public RcdMetadata z() {
        return this.rcd;
    }
}
