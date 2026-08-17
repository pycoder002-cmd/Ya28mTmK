package com.startapp;

import android.app.Activity;
import android.content.Context;
import android.os.SystemClock;
import com.startapp.sdk.ads.interstitials.OverlayAd;
import com.startapp.sdk.ads.interstitials.ReturnAd;
import com.startapp.sdk.ads.offerWall.offerWallHtml.OfferWallAd;
import com.startapp.sdk.ads.offerWall.offerWallJson.OfferWall3DAd;
import com.startapp.sdk.ads.splash.SplashAd;
import com.startapp.sdk.ads.video.VideoEnabledAd;
import com.startapp.sdk.adsbase.ActivityExtra;
import com.startapp.sdk.adsbase.Ad;
import com.startapp.sdk.adsbase.AdsCommonMetaData;
import com.startapp.sdk.adsbase.StartAppAd;
import com.startapp.sdk.adsbase.adlisteners.AdEventListener;
import com.startapp.sdk.adsbase.model.AdPreferences;
import com.startapp.sdk.components.ComponentLocator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class b7 {
    public final AdPreferences.Placement a;
    public Context b;
    public ActivityExtra c;
    public AdPreferences d;
    public long g;
    public int m;
    public Long o;
    public b p;
    public n5 e = null;
    public AtomicBoolean f = new AtomicBoolean(false);
    public String h = null;
    public boolean i = false;
    public y6 j = null;
    public w6 k = null;
    public final Map<AdEventListener, List<StartAppAd>> l = new ConcurrentHashMap();
    public boolean n = true;

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class a implements AdEventListener {
        public boolean a = false;
        public boolean b = false;

        public a() {
        }

        @Override // com.startapp.sdk.adsbase.adlisteners.AdEventListener
        public void onFailedToReceiveAd(Ad ad) {
            List<StartAppAd> a;
            ConcurrentHashMap concurrentHashMap;
            ConcurrentHashMap concurrentHashMap2 = null;
            if (!this.b) {
                synchronized (b7.this.l) {
                    concurrentHashMap = new ConcurrentHashMap(b7.this.l);
                    b7 b7Var = b7.this;
                    b7Var.e = null;
                    b7Var.l.clear();
                }
                concurrentHashMap2 = concurrentHashMap;
            }
            if (concurrentHashMap2 != null) {
                for (AdEventListener adEventListener : concurrentHashMap2.keySet()) {
                    if (adEventListener != null && (a = b7.this.a(concurrentHashMap2, adEventListener)) != null) {
                        for (StartAppAd startAppAd : a) {
                            if (ad != null) {
                                startAppAd.setErrorMessage(ad.getErrorMessage());
                            }
                            d.a(b7.this.b, adEventListener, startAppAd);
                        }
                    }
                }
            }
            this.b = true;
            b7.this.k.d();
            b7.this.j.e();
            b7.this.f.set(false);
        }

        @Override // com.startapp.sdk.adsbase.adlisteners.AdEventListener
        public void onReceiveAd(Ad ad) {
            n5 n5Var = b7.this.e;
            boolean z = n5Var != null && n5Var.a();
            if (!this.a && !z) {
                this.a = true;
                synchronized (b7.this.l) {
                    for (AdEventListener adEventListener : b7.this.l.keySet()) {
                        if (adEventListener != null) {
                            b7 b7Var = b7.this;
                            List<StartAppAd> a = b7Var.a(b7Var.l, adEventListener);
                            if (a != null) {
                                Iterator<StartAppAd> it = a.iterator();
                                while (it.hasNext()) {
                                    it.next().setErrorMessage(ad.getErrorMessage());
                                    d.b(b7.this.b, adEventListener, ad);
                                }
                            }
                        }
                    }
                    b7.this.l.clear();
                }
            }
            b7.this.j.d();
            b7.this.k.f();
            b7.this.f.set(false);
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public interface b {
    }

    public b7(Context context, AdPreferences.Placement placement, AdPreferences adPreferences) {
        this.a = placement;
        this.d = adPreferences;
        a(context);
        a();
    }

    public List<StartAppAd> a(Map<AdEventListener, List<StartAppAd>> map, AdEventListener adEventListener) {
        try {
            return map.get(adEventListener);
        } catch (Throwable th) {
            p7.a(this.b, th);
            return null;
        }
    }

    public final void a() {
        this.j = new y6(this);
        this.k = new w6(this);
    }

    public final void a(Context context) {
        if (context instanceof Activity) {
            this.b = y8.b(context);
            this.c = new ActivityExtra((Activity) context);
        } else {
            this.b = context;
            this.c = null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x001f  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0055  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(com.startapp.sdk.adsbase.StartAppAd r5, com.startapp.sdk.adsbase.adlisteners.AdEventListener r6, boolean r7, boolean r8) {
        /*
            r4 = this;
            java.util.Map<com.startapp.sdk.adsbase.adlisteners.AdEventListener, java.util.List<com.startapp.sdk.adsbase.StartAppAd>> r0 = r4.l
            monitor-enter(r0)
            boolean r1 = r4.c()     // Catch: java.lang.Throwable -> L60
            r2 = 1
            r3 = 0
            if (r1 == 0) goto L1c
            com.startapp.n5 r1 = r4.e     // Catch: java.lang.Throwable -> L60
            if (r1 != 0) goto L11
            r1 = 0
            goto L15
        L11:
            boolean r1 = r1.d()     // Catch: java.lang.Throwable -> L60
        L15:
            if (r1 != 0) goto L1c
            if (r7 == 0) goto L1a
            goto L1c
        L1a:
            r7 = 0
            goto L1d
        L1c:
            r7 = 1
        L1d:
            if (r7 == 0) goto L55
            if (r5 == 0) goto L3f
            if (r6 == 0) goto L3f
            java.util.Map<com.startapp.sdk.adsbase.adlisteners.AdEventListener, java.util.List<com.startapp.sdk.adsbase.StartAppAd>> r7 = r4.l     // Catch: java.lang.Throwable -> L60
            java.util.List r7 = r4.a(r7, r6)     // Catch: java.lang.Throwable -> L60
            if (r7 != 0) goto L3c
            java.util.ArrayList r7 = new java.util.ArrayList     // Catch: java.lang.Throwable -> L60
            r7.<init>()     // Catch: java.lang.Throwable -> L60
            java.util.Map<com.startapp.sdk.adsbase.adlisteners.AdEventListener, java.util.List<com.startapp.sdk.adsbase.StartAppAd>> r1 = r4.l     // Catch: java.lang.Throwable -> L60
            r1.put(r6, r7)     // Catch: java.lang.Throwable -> L36
            goto L3c
        L36:
            r6 = move-exception
            android.content.Context r1 = r4.b     // Catch: java.lang.Throwable -> L60
            com.startapp.p7.a(r1, r6)     // Catch: java.lang.Throwable -> L60
        L3c:
            r7.add(r5)     // Catch: java.lang.Throwable -> L60
        L3f:
            java.util.concurrent.atomic.AtomicBoolean r5 = r4.f     // Catch: java.lang.Throwable -> L60
            boolean r5 = r5.compareAndSet(r3, r2)     // Catch: java.lang.Throwable -> L60
            if (r5 == 0) goto L5e
            com.startapp.y6 r5 = r4.j     // Catch: java.lang.Throwable -> L60
            r5.e()     // Catch: java.lang.Throwable -> L60
            com.startapp.w6 r5 = r4.k     // Catch: java.lang.Throwable -> L60
            r5.e()     // Catch: java.lang.Throwable -> L60
            r4.b(r8)     // Catch: java.lang.Throwable -> L60
            goto L5e
        L55:
            if (r5 == 0) goto L5e
            if (r6 == 0) goto L5e
            android.content.Context r7 = r4.b     // Catch: java.lang.Throwable -> L60
            com.startapp.d.b(r7, r6, r5)     // Catch: java.lang.Throwable -> L60
        L5e:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L60
            return
        L60:
            r5 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L60
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.startapp.b7.a(com.startapp.sdk.adsbase.StartAppAd, com.startapp.sdk.adsbase.adlisteners.AdEventListener, boolean, boolean):void");
    }

    public final void a(boolean z) {
        n5 overlayAd;
        boolean z2;
        if (z) {
            Long h = AdsCommonMetaData.h.h();
            if (h == null || this.o == null || SystemClock.elapsedRealtime() - this.o.longValue() >= h.longValue()) {
                this.o = Long.valueOf(SystemClock.elapsedRealtime());
                z2 = false;
            } else {
                final Context context = this.b;
                final AdPreferences.Placement placement = this.a;
                d.a(this.b, new a(), new Ad(context, placement) { // from class: com.startapp.sdk.adsbase.cache.CachedAd$3
                    @Override // com.startapp.sdk.adsbase.Ad
                    public void a(AdPreferences adPreferences, AdEventListener adEventListener) {
                    }

                    @Override // com.startapp.sdk.adsbase.Ad
                    public String getAdId() {
                        return null;
                    }

                    @Override // com.startapp.sdk.adsbase.Ad
                    public String getBidToken() {
                        return null;
                    }

                    @Override // com.startapp.sdk.adsbase.Ad
                    public String getErrorMessage() {
                        return "explicit call: nofill [204]";
                    }
                });
                aa.a(this.b, true, "Failed to load " + this.a.name() + " ad: NO FILL", true);
                z2 = true;
            }
            if (z2) {
                return;
            }
        }
        int ordinal = this.a.ordinal();
        if (ordinal == 0) {
            overlayAd = new OverlayAd(this.b);
        } else if (ordinal == 7) {
            overlayAd = new ReturnAd(this.b);
        } else if (ordinal == 2) {
            boolean z3 = new Random().nextInt(100) < AdsCommonMetaData.h.w();
            boolean isForceOfferWall3D = this.d.isForceOfferWall3D();
            boolean isForceOfferWall2D = true ^ this.d.isForceOfferWall2D();
            Map<Activity, Integer> map = aa.a;
            overlayAd = ((z3 || isForceOfferWall3D) && isForceOfferWall2D) ? new OfferWall3DAd(this.b) : new OfferWallAd(this.b);
        } else if (ordinal == 3) {
            overlayAd = new SplashAd(this.b);
        } else if (ordinal != 4) {
            overlayAd = new OverlayAd(this.b);
        } else {
            Map<Activity, Integer> map2 = aa.a;
            overlayAd = new VideoEnabledAd(this.b);
        }
        this.e = overlayAd;
        overlayAd.setActivityExtra(this.c);
        this.d.setAutoLoadAmount(this.m);
        this.e.load(this.d, new a());
        this.g = System.currentTimeMillis();
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x004c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void b() {
        /*
            r6 = this;
            boolean r0 = r6.c()
            if (r0 == 0) goto L6c
            android.content.Context r0 = r6.b
            com.startapp.n5 r1 = r6.e
            com.startapp.sdk.adsbase.Ad r1 = (com.startapp.sdk.adsbase.Ad) r1
            r2 = 1
            r3 = 0
            if (r1 == 0) goto L49
            java.util.HashSet r4 = new java.util.HashSet
            r4.<init>()
            boolean r5 = r1 instanceof com.startapp.sdk.adsbase.HtmlAd
            if (r5 == 0) goto L31
            com.startapp.sdk.adsbase.HtmlAd r1 = (com.startapp.sdk.adsbase.HtmlAd) r1
            java.lang.String r1 = r1.j()
            java.util.List r1 = com.startapp.d.a(r1, r3)
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
            java.lang.Boolean r0 = com.startapp.d.a(r0, r1, r3, r4, r5)
            boolean r0 = r0.booleanValue()
            goto L4a
        L31:
            boolean r5 = r1 instanceof com.startapp.sdk.adsbase.JsonAd
            if (r5 == 0) goto L49
            com.startapp.sdk.adsbase.JsonAd r1 = (com.startapp.sdk.adsbase.JsonAd) r1
            java.util.List r1 = r1.g()
            java.util.List r0 = com.startapp.d.a(r0, r1, r3, r4, r3)
            java.util.ArrayList r0 = (java.util.ArrayList) r0
            int r0 = r0.size()
            if (r0 != 0) goto L49
            r0 = 1
            goto L4a
        L49:
            r0 = 0
        L4a:
            if (r0 != 0) goto L67
            com.startapp.n5 r0 = r6.e
            if (r0 != 0) goto L52
            r0 = 0
            goto L56
        L52:
            boolean r0 = r0.d()
        L56:
            if (r0 == 0) goto L59
            goto L67
        L59:
            java.util.concurrent.atomic.AtomicBoolean r0 = r6.f
            boolean r0 = r0.get()
            if (r0 != 0) goto L79
            com.startapp.y6 r0 = r6.j
            r0.d()
            goto L79
        L67:
            r0 = 0
            r6.a(r0, r0, r2, r3)
            goto L79
        L6c:
            java.util.concurrent.atomic.AtomicBoolean r0 = r6.f
            boolean r0 = r0.get()
            if (r0 != 0) goto L79
            com.startapp.w6 r0 = r6.k
            r0.d()
        L79:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.startapp.b7.b():void");
    }

    public final void b(boolean z) {
        n5 n5Var = this.e;
        if (n5Var != null) {
            n5Var.a(false);
        }
        if (!(this.i && this.h != null)) {
            a(z);
            return;
        }
        this.i = false;
        z6 z6Var = new z6(this, new a(), z);
        Context context = this.b;
        ComponentLocator.a(context).h().execute(new e7(context, this.h, z6Var, new a7(this)));
    }

    public boolean c() {
        n5 n5Var = this.e;
        return n5Var != null && n5Var.isReady();
    }
}
