package com.startapp.sdk.adsbase;

import android.app.Activity;
import android.app.Application;
import android.app.Service;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import com.startapp.aa;
import com.startapp.cd;
import com.startapp.dd;
import com.startapp.ed;
import com.startapp.fd;
import com.startapp.g5;
import com.startapp.h1;
import com.startapp.h9;
import com.startapp.ha;
import com.startapp.i5;
import com.startapp.ia;
import com.startapp.kc;
import com.startapp.l6;
import com.startapp.lc;
import com.startapp.m6;
import com.startapp.mc;
import com.startapp.n5;
import com.startapp.networkTest.startapp.NetworkTester;
import com.startapp.o5;
import com.startapp.oa;
import com.startapp.p5;
import com.startapp.p7;
import com.startapp.pc;
import com.startapp.pd;
import com.startapp.q7;
import com.startapp.qc;
import com.startapp.sdk.adsbase.cache.CacheKey;
import com.startapp.sdk.adsbase.model.AdPreferences;
import com.startapp.sdk.adsbase.remoteconfig.MetaData;
import com.startapp.sdk.adsbase.remoteconfig.MetaDataRequest;
import com.startapp.sdk.adsbase.remoteconfig.MotionMetadata;
import com.startapp.sdk.cachedservice.BackgroundService;
import com.startapp.sdk.components.ComponentLocator;
import com.startapp.sdk.insight.NetworkTestsMetaData;
import com.startapp.sdk.jobs.JobRequest;
import com.startapp.t8;
import com.startapp.tc;
import com.startapp.u7;
import com.startapp.u8;
import com.startapp.v5;
import com.startapp.v6;
import com.startapp.w5;
import com.startapp.x5;
import com.startapp.x9;
import com.startapp.xc;
import com.startapp.y8;
import com.startapp.ya;
import com.startapp.yc;
import io.sentry.DefaultSentryClientFactory;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class StartAppSDKInternal implements m6 {
    public static final String a = "StartAppSDKInternal";
    public static final Object b = new Object();
    public static volatile InitState c = InitState.UNSET;
    public boolean A;
    public n5 B;
    public l6 C;
    public pd D;
    public boolean E;
    public boolean F;
    public SDKAdPreferences d;
    public boolean e;
    public final boolean f;
    public boolean g;
    public boolean h;
    public boolean i;
    public long j;
    public Application k;
    public HashMap<Integer, Integer> l;
    public Object m;
    public Activity n;
    public boolean o;
    public boolean p;
    public boolean q;
    public boolean r;
    public Map<String, String> s;
    public Bundle t;
    public AdPreferences u;
    public CacheKey v;
    public boolean w;
    public boolean x;
    public boolean y;
    public boolean z;

    /* loaded from: classes3.dex */
    public enum InitState {
        UNSET,
        IMPLICIT,
        EXPLICIT
    }

    /* loaded from: classes3.dex */
    public static class a implements u7 {
        public final /* synthetic */ oa a;

        public a(oa oaVar) {
            this.a = oaVar;
        }

        @Override // com.startapp.u7
        public void a(p7 p7Var, int i) {
            oa oaVar = this.a;
            if (oaVar != null) {
                oaVar.a(Boolean.valueOf(i == 1));
            }
        }
    }

    /* loaded from: classes3.dex */
    public class b implements t8 {
        public final /* synthetic */ Context a;
        public final /* synthetic */ ComponentLocator b;

        public b(Context context, ComponentLocator componentLocator) {
            this.a = context;
            this.b = componentLocator;
        }

        @Override // com.startapp.t8
        public void a(MetaDataRequest.RequestReason requestReason) {
            this.b.b().a(0, StartAppSDKInternal.this.E);
        }

        @Override // com.startapp.t8
        public void a(MetaDataRequest.RequestReason requestReason, boolean z) {
            StartAppSDKInternal startAppSDKInternal = StartAppSDKInternal.this;
            Context context = this.a;
            startAppSDKInternal.getClass();
            qc l = ComponentLocator.a(context).l();
            if (MetaData.h.R()) {
                long w = MetaData.h.w() * DefaultSentryClientFactory.BUFFER_FLUSHTIME_DEFAULT;
                tc.a aVar = new tc.a(u8.class);
                aVar.e = Long.valueOf(w);
                aVar.b = JobRequest.Network.ANY;
                l.a(new tc(aVar));
            } else {
                l.a(JobRequest.a((Class<? extends pc>[]) new Class[]{u8.class}));
            }
            StartAppSDKInternal startAppSDKInternal2 = StartAppSDKInternal.this;
            Context context2 = this.a;
            startAppSDKInternal2.getClass();
            qc l2 = ComponentLocator.a(context2).l();
            if (MetaData.h.Q()) {
                long b = MetaData.h.b(context2) * DefaultSentryClientFactory.BUFFER_FLUSHTIME_DEFAULT;
                tc.a aVar2 = new tc.a(ia.class);
                aVar2.e = Long.valueOf(b);
                aVar2.b = JobRequest.Network.ANY;
                l2.a(new tc(aVar2));
            } else {
                l2.a(JobRequest.a((Class<? extends pc>[]) new Class[]{ia.class}));
            }
            StartAppSDKInternal startAppSDKInternal3 = StartAppSDKInternal.this;
            Context context3 = this.a;
            startAppSDKInternal3.getClass();
            qc l3 = ComponentLocator.a(context3).l();
            MetaData metaData = MetaData.h;
            if (metaData.Q() && metaData.P()) {
                long millis = TimeUnit.SECONDS.toMillis(metaData.a(context3));
                tc.a aVar3 = new tc.a(ha.class);
                aVar3.e = Long.valueOf(millis);
                aVar3.b = JobRequest.Network.ANY;
                aVar3.d = true;
                l3.a(new tc(aVar3));
            } else {
                l3.a(JobRequest.a((Class<? extends pc>[]) new Class[]{ha.class}));
            }
            StartAppSDKInternal startAppSDKInternal4 = StartAppSDKInternal.this;
            Context context4 = this.a;
            startAppSDKInternal4.getClass();
            ComponentLocator a = ComponentLocator.a(context4);
            p5 d = a.d();
            if (d.getBoolean("shared_prefs_first_init", true)) {
                p5.a edit = d.edit();
                edit.a("totalSessions", (String) 0);
                edit.a.putInt("totalSessions", 0);
                long currentTimeMillis = System.currentTimeMillis();
                edit.a("firstSessionTime", (String) Long.valueOf(currentTimeMillis));
                edit.a.putLong("firstSessionTime", currentTimeMillis);
                edit.apply();
                a.o().execute(new x5(startAppSDKInternal4, context4, a, d));
            }
            StartAppSDKInternal.f(this.a);
            StartAppSDKInternal.this.getClass();
            Context context5 = this.a;
            NetworkTestsMetaData u = MetaData.h.u();
            if (Build.VERSION.SDK_INT >= 14) {
                qc l4 = ComponentLocator.a(context5).l();
                boolean z2 = ya.a(context5, "android.permission.ACCESS_FINE_LOCATION") || ya.a(context5, "android.permission.ACCESS_COARSE_LOCATION");
                if (u != null && u.n() && z2) {
                    if (Math.random() < u.j()) {
                        h1.a(new lc(context5));
                    }
                    try {
                        BackgroundService.a(context5, u.o());
                        NetworkTester.Config config = new NetworkTester.Config();
                        config.PROJECT_ID = u.k();
                        config.CONNECTIVITY_TEST_HOSTNAME = u.c();
                        config.CONNECTIVITY_TEST_FILENAME = u.b();
                        config.CONNECTIVITY_TEST_ENABLED = u.l();
                        config.NIR_COLLECT_CELLINFO = u.p();
                        config.CT_COLLECT_CELLINFO = u.m();
                        config.CONNECTIVITY_TEST_CDNCONFIG_URL = u.a();
                        config.GEOIP_URL = u.e();
                        kc kcVar = new kc(y8.b(context5));
                        NetworkTester.init(context5, config);
                        NetworkTester.setOnConnectivityLatencyListener(kcVar);
                        NetworkTester.setOnNetworkInfoListener(kcVar);
                        NetworkTester.startListening(u.d(), u.f());
                        tc.a aVar4 = new tc.a(mc.class);
                        aVar4.e = Long.valueOf(u.d());
                        aVar4.b = JobRequest.Network.ANY;
                        l4.a(new tc(aVar4));
                    } catch (Throwable th) {
                        p7.a(context5, th);
                    }
                } else {
                    l4.a(JobRequest.a((Class<? extends pc>[]) new Class[]{mc.class}));
                    NetworkTester.stopListening();
                    BackgroundService.a(context5, false);
                }
            }
            StartAppSDKInternal startAppSDKInternal5 = StartAppSDKInternal.this;
            Context context6 = this.a;
            if (startAppSDKInternal5.D == null) {
                pd b2 = ComponentLocator.a(context6).o.b();
                startAppSDKInternal5.D = b2;
                b2.b();
            }
            cd q = this.b.q();
            q.getClass();
            try {
                if (!q.b()) {
                    q.c.execute(new dd(q));
                }
            } catch (Throwable th2) {
                p7.a(q.b, th2);
            }
            ed p = this.b.p();
            List<fd> a2 = p.a();
            if (p.a(1024)) {
                p7 p7Var = new p7(q7.b);
                p7Var.d = "RSC init";
                StringBuilder sb = new StringBuilder();
                sb.append("targets: ");
                sb.append(a2 != null ? Integer.valueOf(a2.size()) : null);
                p7Var.e = sb.toString();
                p7Var.a(p.a);
            }
            xc n = this.b.n();
            MotionMetadata a3 = n.a();
            if (a3 != null) {
                n.g = Math.random() < a3.k();
            }
            n.e.post(new yc(n));
            this.b.b().a(z ? 1 : 2, StartAppSDKInternal.this.E);
        }
    }

    /* loaded from: classes3.dex */
    public static class c {
        public static final StartAppSDKInternal a = new StartAppSDKInternal();
    }

    public StartAppSDKInternal() {
        Map<Activity, Integer> map = aa.a;
        this.e = true;
        this.f = ya.a();
        this.g = false;
        this.h = false;
        this.i = false;
        this.l = new HashMap<>();
        this.o = false;
        this.p = true;
        this.q = false;
        this.r = false;
        this.t = null;
        this.x = false;
        this.y = false;
        this.z = false;
        this.A = false;
        this.B = null;
    }

    public static StartAppSDKInternal a() {
        return c.a;
    }

    public static String a(String str) {
        try {
            return (String) Class.forName(str + ".StartAppConstants").getField("WRAPPER_VERSION").get(null);
        } catch (Exception unused) {
            return "0";
        }
    }

    public static void a(Context context) {
        String str = new o5(context).b;
        ComponentLocator a2 = ComponentLocator.a(context);
        if (TextUtils.isEmpty(str)) {
            if (c == InitState.UNSET) {
                c = InitState.IMPLICIT;
                c.a.a(context, a2.c().a() ? MetaDataRequest.RequestReason.LAUNCH : MetaDataRequest.RequestReason.IMPLICIT_LAUNCH);
                return;
            }
            return;
        }
        StartAppSDKInternal startAppSDKInternal = c.a;
        startAppSDKInternal.getClass();
        Context b2 = y8.b(context);
        a(b2, new v5(startAppSDKInternal, b2, null, str, null, false));
        if (a2.d().getBoolean("shared_prefs_first_init", true)) {
            p7 p7Var = new p7(q7.b);
            p7Var.d = "ManifestInit";
            p7Var.a(context);
        }
    }

    public static void a(Context context, Runnable runnable) {
        ComponentLocator a2 = ComponentLocator.a(context);
        if (a2.c.b().a.getBoolean("0115fe86041c10c0", true)) {
            a2.A.b().execute(runnable);
        } else {
            g5.a(runnable);
        }
    }

    public static void a(Context context, boolean z, oa oaVar) {
        p7 p7Var = new p7(q7.i);
        p7Var.j = z;
        p7Var.a(context, new a(oaVar));
    }

    public static void a(StartAppSDKInternal startAppSDKInternal, Context context, String str, String str2, SDKAdPreferences sDKAdPreferences, boolean z) {
        startAppSDKInternal.getClass();
        InitState initState = c;
        InitState initState2 = InitState.EXPLICIT;
        if (initState == initState2) {
            return;
        }
        boolean a2 = ComponentLocator.a(context).c().a();
        if (TextUtils.isEmpty(str2)) {
            IllegalArgumentException illegalArgumentException = new IllegalArgumentException("\n+-------------------------------------------------------------+\n|                S   T   A   R   T   A   P   P                |\n| - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - |\n| Invalid App ID passed to init, please provide valid App ID  |\n|                                                             |\n|   https://support.start.io/hc/en-us/articles/360014774799   |\n+-------------------------------------------------------------+\n");
            if (aa.f(context)) {
                throw illegalArgumentException;
            }
            if (ya.c(context)) {
                throw illegalArgumentException;
            }
            Log.w("StartAppSDK", illegalArgumentException);
        }
        i5 c2 = ComponentLocator.a(context).c();
        c2.getClass();
        if (str != null) {
            str = str.trim();
        }
        if (str2 != null) {
            str2 = str2.trim();
        }
        synchronized (c2.a) {
            c2.c = str;
            c2.d = str2;
            c2.b.edit().putString("c88d4eab540fab77", str).putString("2696a7f502faed4b", str2).commit();
        }
        new Handler(Looper.getMainLooper()).postDelayed(new w5(context), 3000L);
        startAppSDKInternal.d = sDKAdPreferences;
        h9.a(context, "shared_prefs_sdk_ad_prefs", sDKAdPreferences);
        startAppSDKInternal.a(z);
        if (c == InitState.IMPLICIT && !a2) {
            startAppSDKInternal.b(context, MetaDataRequest.RequestReason.LAUNCH);
        } else if (c == InitState.UNSET) {
            startAppSDKInternal.a(context, MetaDataRequest.RequestReason.LAUNCH);
        }
        c = initState2;
        try {
            ComponentLocator.a(context).q().a(256);
        } catch (Throwable unused) {
        }
    }

    public static boolean b(String str) {
        try {
            Class.forName(str);
            return true;
        } catch (ClassNotFoundException | Exception unused) {
            return false;
        }
    }

    public static boolean c() {
        boolean z;
        synchronized (b) {
            z = c == InitState.EXPLICIT;
        }
        return z;
    }

    public static void f(Context context) {
        if (context != null) {
            a(context, false, null);
        }
    }

    public static void i(Context context) {
        TreeMap treeMap = new TreeMap();
        if (b("org.apache.cordova.CordovaPlugin")) {
            treeMap.put("Cordova", "4.9.1");
        }
        if (b("com.startapp.android.mediation.admob.StartAppCustomEvent")) {
            treeMap.put("AdMob", a("com.startapp.android.mediation.admob"));
        }
        if (b("com.mopub.mobileads.StartAppCustomEventInterstitial")) {
            treeMap.put("MoPub", a("com.mopub.mobileads"));
        }
        if (b("anywheresoftware.b4a.BA") && !c.a.s.containsKey("B4A")) {
            treeMap.put("MoPub", "0");
        }
        if (treeMap.isEmpty()) {
            return;
        }
        p5 d = ComponentLocator.a(context).d();
        Map<Activity, Integer> map = aa.a;
        String jSONObject = new JSONObject(treeMap).toString();
        p5.a edit = d.edit();
        edit.a("sharedPrefsWrappers", jSONObject);
        edit.a.putString("sharedPrefsWrappers", jSONObject);
        edit.apply();
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0073, code lost:
    
        if (r4 != false) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x008c, code lost:
    
        d(r8);
        com.startapp.ma.a(r8);
        com.startapp.sdk.adsbase.remoteconfig.MetaData.c(r8);
        r1 = com.startapp.aa.a;
        com.startapp.sdk.adsbase.AdsCommonMetaData.a(r8);
        com.startapp.sdk.ads.banner.BannerMetaData.a(r8);
        com.startapp.sdk.ads.splash.SplashMetaData.a(r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x00a2, code lost:
    
        if (r7.e == false) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x00a4, code lost:
    
        com.startapp.sdk.adsbase.cache.CacheMetaData.a(r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x00a7, code lost:
    
        com.startapp.sdk.adsbase.adinformation.AdInformationMetaData.a(r8);
        com.startapp.sdk.adsbase.SimpleTokenUtils.c(r8);
        com.startapp.sdk.adsbase.remoteconfig.MetaData.h.a(r0.f());
        r0.u.b().e();
        r0.v.b().e();
        r0.s().e();
        r0 = r0.i.b();
        r0.b.execute(new com.startapp.ec(r0));
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x00e9, code lost:
    
        if (android.os.Build.VERSION.SDK_INT < 9) goto L40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x00eb, code lost:
    
        com.startapp.d.b = new java.net.CookieManager(new com.startapp.va(r8), java.net.CookiePolicy.ACCEPT_ALL);
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x00f9, code lost:
    
        g(r8);
        b(r8, r9);
        b(r8);
        h(r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0109, code lost:
    
        if (com.startapp.ya.a() == false) goto L52;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x010d, code lost:
    
        if ((r8 instanceof android.app.Application) == false) goto L52;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x010f, code lost:
    
        r9 = (android.app.Application) r8;
        r7.k = r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0114, code lost:
    
        r0 = r7.m;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0116, code lost:
    
        if (r0 == null) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0118, code lost:
    
        r9.unregisterActivityLifecycleCallbacks((android.app.Application.ActivityLifecycleCallbacks) r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x011e, code lost:
    
        r9 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x011f, code lost:
    
        com.startapp.p7.a(r8, r9);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void a(android.content.Context r8, com.startapp.sdk.adsbase.remoteconfig.MetaDataRequest.RequestReason r9) {
        /*
            Method dump skipped, instructions count: 322
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.startapp.sdk.adsbase.StartAppSDKInternal.a(android.content.Context, com.startapp.sdk.adsbase.remoteconfig.MetaDataRequest$RequestReason):void");
    }

    public void a(boolean z) {
        if (z && ya.a()) {
            this.w = true;
        } else {
            this.w = false;
            v6.a.b(AdPreferences.Placement.INAPP_RETURN);
        }
    }

    public final boolean a(Activity activity) {
        return this.A || activity.getClass().getName().equals(aa.c(activity));
    }

    /* JADX WARN: Code restructure failed: missing block: B:29:0x00eb, code lost:
    
        if (r0.isEmpty() == false) goto L66;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00c4, code lost:
    
        if (r9.contains("com.android.chrome") != false) goto L56;
     */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00e7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void b(android.content.Context r14) {
        /*
            Method dump skipped, instructions count: 269
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.startapp.sdk.adsbase.StartAppSDKInternal.b(android.content.Context):void");
    }

    public void b(Context context, MetaDataRequest.RequestReason requestReason) {
        x9 x9Var = x9.a;
        x9.a.a(context, requestReason);
    }

    public boolean b() {
        return this.r;
    }

    public SDKAdPreferences c(Context context) {
        if (this.d == null) {
            SDKAdPreferences sDKAdPreferences = (SDKAdPreferences) h9.a(context, "shared_prefs_sdk_ad_prefs", SDKAdPreferences.class);
            if (sDKAdPreferences == null) {
                this.d = new SDKAdPreferences();
            } else {
                this.d = sDKAdPreferences;
            }
        }
        return this.d;
    }

    public boolean c(String str) {
        Map<String, String> map = this.s;
        return (map == null ? null : map.get(str)) != null;
    }

    public final void d(Context context) {
        if (Build.VERSION.SDK_INT >= 14) {
            Context a2 = y8.a(context);
            Application application = a2 instanceof Application ? (Application) a2 : context instanceof Application ? (Application) context : context instanceof Activity ? ((Activity) context).getApplication() : context instanceof Service ? ((Service) context).getApplication() : null;
            if (application == null || this.C != null) {
                return;
            }
            l6 l6Var = new l6(this);
            this.C = l6Var;
            application.registerActivityLifecycleCallbacks(l6Var);
            try {
                Application.ActivityLifecycleCallbacks activityLifecycleCallbacks = ComponentLocator.a(context).q().f;
                if (activityLifecycleCallbacks == null) {
                    throw new RuntimeException();
                }
                application.registerActivityLifecycleCallbacks(activityLifecycleCallbacks);
            } catch (Throwable unused) {
            }
        }
    }

    public boolean d() {
        return this.p;
    }

    public final void e(Context context) {
        if (!this.w || AdsCommonMetaData.h.K()) {
            return;
        }
        v6 v6Var = v6.a;
        AdPreferences adPreferences = this.u;
        AdPreferences adPreferences2 = adPreferences != null ? new AdPreferences(adPreferences) : new AdPreferences();
        AdPreferences.Placement placement = AdPreferences.Placement.INAPP_RETURN;
        this.v = v6Var.a(placement) ? v6Var.a(context, null, placement, adPreferences2, null, false, 0) : null;
    }

    public final void g(Context context) {
        p5 d = ComponentLocator.a(context).d();
        int i = d.getInt("shared_prefs_app_version_id", -1);
        int i2 = ya.a;
        int i3 = 0;
        try {
            i3 = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (Throwable unused) {
        }
        if (i > 0 && i3 > i) {
            this.r = true;
        }
        p5.a edit = d.edit();
        edit.a("shared_prefs_app_version_id", (String) Integer.valueOf(i3));
        edit.a.putInt("shared_prefs_app_version_id", i3);
        edit.apply();
    }

    public final void h(Context context) {
        ComponentLocator a2 = ComponentLocator.a(context);
        p5.a edit = a2.d().edit();
        Boolean bool = Boolean.FALSE;
        edit.a("periodicInfoEventPaused", (String) bool);
        edit.a.putBoolean("periodicInfoEventPaused", false);
        edit.a("periodicMetadataPaused", (String) bool);
        edit.a.putBoolean("periodicMetadataPaused", false);
        edit.apply();
        b bVar = new b(context, a2);
        if (MetaData.h.k) {
            bVar.a(null, false);
        } else {
            MetaData.h.a(bVar);
        }
    }
}
