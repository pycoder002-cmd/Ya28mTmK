package com.startapp;

import android.app.Activity;
import android.os.Bundle;
import com.startapp.m5;
import com.startapp.sdk.ads.interstitials.ReturnAd;
import com.startapp.sdk.ads.splash.SplashConfig;
import com.startapp.sdk.adsbase.AdsCommonMetaData;
import com.startapp.sdk.adsbase.AutoInterstitialPreferences;
import com.startapp.sdk.adsbase.SimpleTokenUtils;
import com.startapp.sdk.adsbase.StartAppAd;
import com.startapp.sdk.adsbase.StartAppSDKInternal;
import com.startapp.sdk.adsbase.adrules.AdRules;
import com.startapp.sdk.adsbase.adrules.AdRulesResult;
import com.startapp.sdk.adsbase.model.AdPreferences;
import com.startapp.sdk.adsbase.remoteconfig.MetaData;
import com.startapp.sdk.adsbase.remoteconfig.MetaDataRequest;
import com.startapp.sdk.components.ComponentLocator;
import java.util.Iterator;
import java.util.Map;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public final class y5 extends e9 {
    @Override // com.startapp.e9, android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
        boolean startsWith;
        StartAppSDKInternal startAppSDKInternal = StartAppSDKInternal.c.a;
        startAppSDKInternal.getClass();
        if (activity.getClass().getName().equals(aa.c(activity))) {
            startAppSDKInternal.A = true;
        }
        startAppSDKInternal.t = bundle;
        Map<Activity, Integer> map = aa.a;
        m5 m5Var = m5.a.a;
        boolean equals = activity.getClass().getName().equals(aa.c(activity));
        if (bundle == null) {
            String[] split = m5.class.getName().split("\\.");
            if (split.length < 3) {
                startsWith = false;
            } else {
                startsWith = activity.getClass().getName().startsWith(split[0] + "." + split[1] + "." + split[2]);
            }
            if (startsWith || equals) {
                return;
            }
            m5Var.d++;
            if (m5Var.a && AdsCommonMetaData.h.I()) {
                if (m5Var.b == null) {
                    m5Var.b = new AutoInterstitialPreferences();
                }
                boolean z = m5Var.c <= 0 || System.currentTimeMillis() >= m5Var.c + ((long) (m5Var.b.getSecondsBetweenAds() * 1000));
                int i = m5Var.d;
                if (z && (i <= 0 || i >= m5Var.b.getActivitiesBetweenAds())) {
                    if (m5Var.e == null) {
                        m5Var.e = new StartAppAd(activity);
                    }
                    m5Var.e.loadAd(StartAppAd.AdMode.AUTOMATIC, new AdPreferences().setAi(Boolean.TRUE), new l5(m5Var));
                }
            }
        }
    }

    @Override // com.startapp.e9, android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity) {
        StartAppSDKInternal startAppSDKInternal = StartAppSDKInternal.c.a;
        if (startAppSDKInternal.a(activity)) {
            startAppSDKInternal.z = false;
        }
        if (startAppSDKInternal.l.size() == 0) {
            startAppSDKInternal.g = false;
        }
    }

    @Override // com.startapp.e9, android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
        StartAppSDKInternal startAppSDKInternal = StartAppSDKInternal.c.a;
        startAppSDKInternal.getClass();
        startAppSDKInternal.j = System.currentTimeMillis();
        startAppSDKInternal.n = null;
    }

    @Override // com.startapp.e9, android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
        StartAppSDKInternal startAppSDKInternal = StartAppSDKInternal.c.a;
        if (startAppSDKInternal.e && startAppSDKInternal.h) {
            startAppSDKInternal.h = false;
            v6 v6Var = v6.a;
            if (!v6Var.e) {
                synchronized (v6Var.b) {
                    Iterator<b7> it = v6Var.b.values().iterator();
                    while (it.hasNext()) {
                        it.next().b();
                    }
                }
            }
        }
        if (startAppSDKInternal.o) {
            startAppSDKInternal.o = false;
            SimpleTokenUtils.f(activity);
        }
        startAppSDKInternal.n = activity;
    }

    @Override // com.startapp.e9, android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
        StartAppSDKInternal startAppSDKInternal = StartAppSDKInternal.c.a;
        startAppSDKInternal.getClass();
        activity.getClass().getName();
        boolean a = startAppSDKInternal.a(activity);
        boolean z = !startAppSDKInternal.z && a && startAppSDKInternal.t == null && startAppSDKInternal.l.size() == 0 && StartAppSDKInternal.c == StartAppSDKInternal.InitState.EXPLICIT;
        if (z) {
            ComponentLocator.a(activity).f().a(false, null, null, null);
        }
        Map<Activity, Integer> map = aa.a;
        if (!ComponentLocator.a(activity).f().d && !AdsCommonMetaData.h.L() && !startAppSDKInternal.y && !startAppSDKInternal.c("MoPub") && !startAppSDKInternal.c("AdMob") && !startAppSDKInternal.x && z) {
            StartAppAd.a(activity, startAppSDKInternal.t, new SplashConfig(), new AdPreferences(), null, false);
        }
        if (a) {
            startAppSDKInternal.A = false;
            startAppSDKInternal.z = true;
        }
        if (startAppSDKInternal.g) {
            if (MetaData.h.b() && startAppSDKInternal.w && !AdsCommonMetaData.h.K() && !startAppSDKInternal.q) {
                if (System.currentTimeMillis() - startAppSDKInternal.j > AdsCommonMetaData.h.x()) {
                    n5 c = v6.a.c(startAppSDKInternal.v);
                    startAppSDKInternal.B = c;
                    if (c != null && c.isReady()) {
                        AdRules b = AdsCommonMetaData.h.b();
                        AdPreferences.Placement placement = AdPreferences.Placement.INAPP_RETURN;
                        AdRulesResult a2 = b.a(placement, null);
                        if (!a2.b()) {
                            g5.a(activity, ((ReturnAd) startAppSDKInternal.B).trackingUrls, (String) null, 0, a2.a());
                        } else if (startAppSDKInternal.B.a((String) null)) {
                            j6.a.a(new i6(placement, null));
                        }
                    }
                }
            }
            if (System.currentTimeMillis() - startAppSDKInternal.j > MetaData.h.C()) {
                startAppSDKInternal.b(activity, MetaDataRequest.RequestReason.APP_IDLE);
            }
        }
        startAppSDKInternal.i = false;
        startAppSDKInternal.g = false;
        if (startAppSDKInternal.l.get(Integer.valueOf(activity.hashCode())) == null) {
            startAppSDKInternal.l.put(Integer.valueOf(activity.hashCode()), Integer.valueOf(new Integer(0).intValue() + 1));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x00e4  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00e7 A[SYNTHETIC] */
    @Override // com.startapp.e9, android.app.Application.ActivityLifecycleCallbacks
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onActivityStopped(android.app.Activity r9) {
        /*
            r8 = this;
            com.startapp.sdk.adsbase.StartAppSDKInternal r0 = com.startapp.sdk.adsbase.StartAppSDKInternal.c.a
            java.util.HashMap<java.lang.Integer, java.lang.Integer> r1 = r0.l
            int r2 = r9.hashCode()
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            java.lang.Object r1 = r1.get(r2)
            java.lang.Integer r1 = (java.lang.Integer) r1
            if (r1 == 0) goto Lec
            int r1 = r1.intValue()
            r2 = 1
            int r1 = r1 - r2
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            int r3 = r1.intValue()
            if (r3 != 0) goto L32
            java.util.HashMap<java.lang.Integer, java.lang.Integer> r1 = r0.l
            int r3 = r9.hashCode()
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r1.remove(r3)
            goto L3f
        L32:
            java.util.HashMap<java.lang.Integer, java.lang.Integer> r3 = r0.l
            int r4 = r9.hashCode()
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            r3.put(r4, r1)
        L3f:
            java.util.HashMap<java.lang.Integer, java.lang.Integer> r1 = r0.l
            int r1 = r1.size()
            if (r1 != 0) goto Lec
            boolean r1 = r0.i
            r3 = 0
            if (r1 != 0) goto L67
            r0.g = r2
            r0.e(r9)
            com.startapp.ma r1 = com.startapp.ma.a
            if (r1 == 0) goto L67
            com.startapp.ma r1 = com.startapp.ma.a
            r1.getClass()
            java.lang.String r4 = "phone"
            java.lang.Object r4 = r9.getSystemService(r4)
            android.telephony.TelephonyManager r4 = (android.telephony.TelephonyManager) r4
            android.telephony.PhoneStateListener r1 = r1.b
            r4.listen(r1, r3)
        L67:
            boolean r1 = r0.e
            if (r1 == 0) goto Lec
            android.content.Context r9 = com.startapp.y8.b(r9)
            com.startapp.v6 r1 = com.startapp.v6.a
            boolean r4 = r0.i
            boolean r5 = r1.d
            if (r5 != 0) goto L85
            com.startapp.sdk.adsbase.cache.CacheMetaData r5 = com.startapp.sdk.adsbase.cache.CacheMetaData.a
            com.startapp.sdk.adsbase.cache.ACMConfig r5 = r5.a()
            boolean r5 = r5.f()
            if (r5 == 0) goto L85
            r5 = 1
            goto L86
        L85:
            r5 = 0
        L86:
            if (r5 == 0) goto L98
            com.startapp.sdk.components.ComponentLocator r5 = com.startapp.sdk.components.ComponentLocator.a(r9)
            java.util.concurrent.Executor r5 = r5.h()
            com.startapp.t6 r6 = new com.startapp.t6
            r6.<init>(r1, r9)
            r5.execute(r6)
        L98:
            java.util.Map<com.startapp.sdk.adsbase.cache.CacheKey, com.startapp.b7> r9 = r1.b
            java.util.Collection r9 = r9.values()
            java.util.Iterator r9 = r9.iterator()
        La2:
            boolean r1 = r9.hasNext()
            if (r1 == 0) goto Lea
            java.lang.Object r1 = r9.next()
            com.startapp.b7 r1 = (com.startapp.b7) r1
            com.startapp.n5 r5 = r1.e
            r6 = 0
            if (r5 == 0) goto Ld3
            java.util.Map<android.app.Activity, java.lang.Integer> r7 = com.startapp.aa.a
            boolean r5 = r5 instanceof com.startapp.sdk.ads.interstitials.ReturnAd
            if (r5 == 0) goto Ld3
            if (r4 != 0) goto Ld3
            com.startapp.sdk.adsbase.cache.CacheMetaData r5 = com.startapp.sdk.adsbase.cache.CacheMetaData.a
            com.startapp.sdk.adsbase.cache.ACMConfig r5 = r5.a()
            boolean r5 = r5.g()
            if (r5 != 0) goto Lde
            com.startapp.y6 r5 = r1.j
            android.os.Handler r7 = r5.b
            if (r7 == 0) goto Ld0
            r7.removeCallbacksAndMessages(r6)
        Ld0:
            r5.d = r3
            goto Lde
        Ld3:
            com.startapp.y6 r5 = r1.j
            android.os.Handler r7 = r5.b
            if (r7 == 0) goto Ldc
            r7.removeCallbacksAndMessages(r6)
        Ldc:
            r5.d = r3
        Lde:
            com.startapp.w6 r1 = r1.k
            android.os.Handler r5 = r1.b
            if (r5 == 0) goto Le7
            r5.removeCallbacksAndMessages(r6)
        Le7:
            r1.d = r3
            goto La2
        Lea:
            r0.h = r2
        Lec:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.startapp.y5.onActivityStopped(android.app.Activity):void");
    }
}
