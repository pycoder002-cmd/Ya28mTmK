package com.startapp;

import android.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import com.iab.omid.library.startapp.adsession.AdEvents;
import com.iab.omid.library.startapp.adsession.AdSession;
import com.iab.omid.library.startapp.adsession.FriendlyObstructionPurpose;
import com.startapp.p2;
import com.startapp.sdk.ads.interstitials.InterstitialAd;
import com.startapp.sdk.adsbase.Ad;
import com.startapp.sdk.adsbase.AdsCommonMetaData;
import com.startapp.sdk.adsbase.StartAppSDKInternal;
import com.startapp.sdk.adsbase.adinformation.AdInformationObject;
import com.startapp.sdk.adsbase.adinformation.AdInformationView;
import com.startapp.sdk.adsbase.commontracking.CloseTrackingParams;
import com.startapp.sdk.adsbase.commontracking.TrackingParams;
import com.startapp.sdk.adsbase.remoteconfig.MetaData;
import com.startapp.sdk.components.ComponentLocator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class q2 extends p2 {
    public static final String v = "q2";
    public Long A;
    public r5 C;
    public boolean H;
    public WebView w;
    public AdSession x;
    public RelativeLayout y;
    public Long z;
    public long B = 0;
    public boolean D = true;
    public boolean E = false;
    public int F = 0;
    public boolean G = false;
    public Runnable I = new a();
    public Runnable J = new b();

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            q2.this.i();
            q2.this.b();
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            q2 q2Var = q2.this;
            q2Var.D = true;
            WebView webView = q2Var.w;
            q2Var.getClass();
            if (webView != null) {
                webView.setOnTouchListener(null);
            }
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class c implements View.OnLongClickListener {
        public c(q2 q2Var) {
        }

        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(View view) {
            return true;
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class d implements View.OnTouchListener {
        public d() {
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            q2.this.D = true;
            return motionEvent.getAction() == 2;
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class e implements Runnable {
        public e() {
        }

        @Override // java.lang.Runnable
        public void run() {
            q2.this.b();
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class f implements Runnable {
        public f() {
        }

        @Override // java.lang.Runnable
        public void run() {
            WebView webView = q2.this.w;
            if (webView != null) {
                ya.a(webView);
            }
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class g extends WebViewClient {
        public g() {
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView webView, String str) {
            q2.this.a(webView);
            q2 q2Var = q2.this;
            aa.a(q2Var.w, true, "gClientInterface.setMode", q2Var.h);
            aa.a(q2.this.w, true, "enableScheme", "externalLinks");
            q2.this.a((View) null);
            if (q2.this.q()) {
                la.a(webView.getContext()).a(new Intent("com.startapp.android.ShowDisplayBroadcastListener"));
            }
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            if (webView == null || str == null || aa.b(webView.getContext(), str)) {
                return true;
            }
            if (!q2.this.D) {
                p7 p7Var = new p7(q7.c);
                p7Var.d = "fake_click";
                p7Var.g = q2.this.a();
                p7Var.e = "jsTag=" + q2.this.G;
                p7Var.a(q2.this.b);
            }
            q2 q2Var = q2.this;
            if (!q2Var.G || q2Var.D) {
                return q2Var.a(str, false);
            }
            return false;
        }
    }

    @Override // com.startapp.p2
    public void a(Bundle bundle) {
        la.a(this.b).a(this.d, new IntentFilter("com.startapp.android.CloseAdActivity"));
        if (bundle == null) {
            this.H = true;
            if (this.a.hasExtra("lastLoadTime")) {
                this.z = (Long) this.a.getSerializableExtra("lastLoadTime");
            }
            if (this.a.hasExtra("adCacheTtl")) {
                this.A = (Long) this.a.getSerializableExtra("adCacheTtl");
                return;
            }
            return;
        }
        if (bundle.containsKey("postrollHtml")) {
            a(bundle.getString("postrollHtml"));
        }
        if (bundle.containsKey("lastLoadTime")) {
            this.z = (Long) bundle.getSerializable("lastLoadTime");
        }
        if (bundle.containsKey("adCacheTtl")) {
            this.A = (Long) bundle.getSerializable("adCacheTtl");
        }
        this.E = bundle.getBoolean("videoCompletedBroadcastSent", false);
        this.F = bundle.getInt("replayNum");
    }

    public void a(View view) {
        AdInformationView adInformationView;
        if (MetaData.h.O() && this.x == null) {
            AdSession a2 = com.startapp.d.a(this.w);
            this.x = a2;
            if (a2 == null || this.w == null) {
                return;
            }
            try {
                AdInformationObject adInformationObject = this.c;
                if (adInformationObject != null && (adInformationView = adInformationObject.b) != null) {
                    a2.addFriendlyObstruction(adInformationView, FriendlyObstructionPurpose.OTHER, null);
                }
                if (view != null) {
                    this.x.addFriendlyObstruction(view, FriendlyObstructionPurpose.OTHER, null);
                }
            } catch (RuntimeException e2) {
                Log.e(v, "OMSDK error", e2);
            }
            this.x.registerAdView(this.w);
            this.x.start();
            AdEvents.createAdEvents(this.x).impressionOccurred();
        }
    }

    public void a(WebView webView) {
    }

    public final void a(String str, int i, boolean z) {
        la.a(this.b).a(new Intent("com.startapp.android.OnClickCallback"));
        Context a2 = y8.a(this.b);
        if (a2 == null) {
            a2 = this.b;
        }
        boolean a3 = g5.a(a2, this.n);
        Activity activity = this.b;
        String[] strArr = this.j;
        g5.a(activity, str, i < strArr.length ? new String[]{strArr[i]} : null, n(), a(i) && !a3, z);
        b();
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0047  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x002c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean a(java.lang.String r6, boolean r7) {
        /*
            r5 = this;
            com.startapp.r5 r0 = r5.C
            r1 = 0
            r0.a(r1, r1)
            com.startapp.sdk.adsbase.Ad r0 = r5.l
            android.app.Activity r1 = r5.b
            android.content.Context r1 = com.startapp.y8.a(r1)
            if (r1 == 0) goto L11
            goto L13
        L11:
            android.app.Activity r1 = r5.b
        L13:
            com.startapp.sdk.adsbase.model.AdPreferences$Placement r2 = r5.n
            boolean r1 = com.startapp.g5.a(r1, r2)
            r2 = 1
            r3 = 0
            if (r1 == 0) goto L25
            java.util.Map<android.app.Activity, java.lang.Integer> r1 = com.startapp.aa.a
            boolean r0 = r0 instanceof com.startapp.sdk.ads.splash.SplashAd
            if (r0 != 0) goto L25
            r0 = 1
            goto L26
        L25:
            r0 = 0
        L26:
            boolean r1 = r5.b(r6)
            if (r1 == 0) goto L47
            int r1 = com.startapp.g5.a(r6)     // Catch: java.lang.Throwable -> L40
            boolean[] r4 = r5.f     // Catch: java.lang.Throwable -> L40
            boolean r4 = r4[r1]     // Catch: java.lang.Throwable -> L40
            if (r4 == 0) goto L3c
            if (r0 != 0) goto L3c
            r5.b(r6, r1, r7)     // Catch: java.lang.Throwable -> L40
            goto L56
        L3c:
            r5.a(r6, r1, r7)     // Catch: java.lang.Throwable -> L40
            goto L56
        L40:
            r6 = move-exception
            android.app.Activity r7 = r5.b
            com.startapp.p7.a(r7, r6)
            return r3
        L47:
            boolean[] r1 = r5.f
            boolean r1 = r1[r3]
            if (r1 == 0) goto L53
            if (r0 != 0) goto L53
            r5.b(r6, r3, r7)
            goto L56
        L53:
            r5.a(r6, r3, r7)
        L56:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.startapp.q2.a(java.lang.String, boolean):boolean");
    }

    @Override // com.startapp.p2
    public void b() {
        this.b.runOnUiThread(new p2.b());
        String str = StartAppSDKInternal.a;
        StartAppSDKInternal.c.a.q = false;
        r5 r5Var = this.C;
        if (r5Var != null) {
            r5Var.a("AD_CLOSED_TOO_QUICKLY", null);
        }
        this.b.runOnUiThread(new f());
    }

    @Override // com.startapp.p2
    public void b(Bundle bundle) {
        String str = this.m;
        if (str != null) {
            bundle.putString("postrollHtml", str);
        }
        Long l = this.z;
        if (l != null) {
            bundle.putLong("lastLoadTime", l.longValue());
        }
        Long l2 = this.A;
        if (l2 != null) {
            bundle.putLong("adCacheTtl", l2.longValue());
        }
        bundle.putBoolean("videoCompletedBroadcastSent", this.E);
        bundle.putInt("replayNum", this.F);
    }

    public void b(WebView webView) {
        this.D = false;
        webView.setOnTouchListener(new d());
    }

    public final void b(String str, int i, boolean z) {
        Activity activity = this.b;
        String[] strArr = this.j;
        String[] strArr2 = i < strArr.length ? new String[]{strArr[i]} : null;
        String[] strArr3 = this.k;
        String str2 = i < strArr3.length ? strArr3[i] : null;
        TrackingParams n = n();
        long z2 = AdsCommonMetaData.h.z();
        long y = AdsCommonMetaData.h.y();
        boolean a2 = a(i);
        Boolean[] boolArr = this.r;
        g5.a(activity, str, strArr2, str2, n, z2, y, a2, (boolArr == null || i < 0 || i >= boolArr.length) ? null : boolArr[i], z, new e());
    }

    public boolean b(String str) {
        return !this.G && str.contains("index=");
    }

    @Override // com.startapp.p2
    public boolean c() {
        i();
        String str = StartAppSDKInternal.a;
        StartAppSDKInternal.c.a.q = false;
        this.C.a("AD_CLOSED_TOO_QUICKLY", null);
        return false;
    }

    @Override // com.startapp.p2
    public void d() {
        if (this.d != null) {
            la.a(this.b).a(this.d);
        }
        this.d = null;
        AdSession adSession = this.x;
        if (adSession != null) {
            adSession.finish();
            this.x = null;
        }
        WebView webView = this.w;
        Map<Activity, Integer> map = aa.a;
        new Handler(Looper.getMainLooper()).postAtTime(null, webView, SystemClock.uptimeMillis() + 1000);
    }

    @Override // com.startapp.p2
    public void e() {
        r5 r5Var = this.C;
        if (r5Var != null) {
            r5Var.a();
        }
        WebView webView = this.w;
        if (webView != null) {
            ya.a(webView);
        }
    }

    @Override // com.startapp.p2
    public void f() {
        Ad ad = this.l;
        if (ad instanceof InterstitialAd ? ((InterstitialAd) ad).d() : false) {
            b();
            return;
        }
        String str = StartAppSDKInternal.a;
        StartAppSDKInternal.c.a.q = true;
        if (this.C == null) {
            this.C = new r5(this.b, this.i, m(), l());
        }
        WebView webView = this.w;
        if (webView == null) {
            RelativeLayout relativeLayout = new RelativeLayout(this.b);
            this.y = relativeLayout;
            relativeLayout.setContentDescription("StartApp Ad");
            this.y.setId(1475346432);
            this.b.setContentView(this.y);
            try {
                WebView a2 = ComponentLocator.a(this.b).t().a();
                this.w = a2;
                a2.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
                this.b.getWindow().getDecorView().findViewById(R.id.content).setBackgroundColor(7829367);
                this.w.setVerticalScrollBarEnabled(false);
                this.w.setHorizontalScrollBarEnabled(false);
                this.w.getSettings().setJavaScriptEnabled(true);
                WebView webView2 = this.w;
                if (Build.VERSION.SDK_INT >= 17) {
                    webView2.getSettings().setMediaPlaybackRequiresUserGesture(false);
                }
                if (this.u) {
                    ya.a(this.w, (Paint) null);
                }
                this.w.setOnLongClickListener(new c(this));
                this.w.setLongClickable(false);
                this.w.addJavascriptInterface(k(), "startappwall");
                p();
                b(this.w);
                aa.a(this.b, this.w, this.m);
                this.G = "true".equals(aa.a(this.m, "@jsTag@", "@jsTag@"));
                t();
                this.y.addView(this.w, new RelativeLayout.LayoutParams(-1, -1));
                RelativeLayout relativeLayout2 = this.y;
                AdInformationObject adInformationObject = new AdInformationObject(this.b, AdInformationObject.Size.LARGE, this.n, this.o, this.l.getConsentData());
                this.c = adInformationObject;
                adInformationObject.a(relativeLayout2);
            } catch (Throwable th) {
                p7.a(this.b, th);
                b();
            }
        } else {
            ya.b(webView);
            this.C.b();
        }
        this.B = SystemClock.uptimeMillis();
    }

    public void i() {
        String[] strArr = this.e;
        if (strArr == null || strArr.length <= 0 || strArr[0] == null) {
            return;
        }
        g5.a(this.b, strArr[0], n());
    }

    public long j() {
        return (SystemClock.uptimeMillis() - this.B) / 1000;
    }

    public hc k() {
        Activity activity = this.b;
        Runnable runnable = this.I;
        Runnable runnable2 = this.J;
        hc hcVar = new hc(activity, runnable, n(), a(0));
        hcVar.d = runnable;
        hcVar.e = runnable2;
        return hcVar;
    }

    public long l() {
        Long l = this.q;
        return l != null ? TimeUnit.SECONDS.toMillis(l.longValue()) : TimeUnit.SECONDS.toMillis(MetaData.h.m());
    }

    public TrackingParams m() {
        return new TrackingParams(this.p);
    }

    public TrackingParams n() {
        return new CloseTrackingParams(j(), this.p);
    }

    public boolean o() {
        return false;
    }

    public void p() {
        this.C.b();
    }

    public boolean q() {
        return this.H;
    }

    public void r() {
    }

    public void s() {
        if (o() && !this.E && this.F == 0) {
            this.E = true;
            la.a(this.b).a(new Intent("com.startapp.android.OnVideoCompleted"));
            r();
        }
    }

    public void t() {
        this.w.setWebViewClient(new g());
        this.w.setWebChromeClient(new WebChromeClient());
    }
}
