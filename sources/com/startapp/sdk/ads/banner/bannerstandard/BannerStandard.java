package com.startapp.sdk.ads.banner.bannerstandard;

import android.R;
import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.iab.omid.library.startapp.adsession.AdSession;
import com.iab.omid.library.startapp.adsession.FriendlyObstructionPurpose;
import com.startapp.a8;
import com.startapp.aa;
import com.startapp.ad;
import com.startapp.b8;
import com.startapp.c8;
import com.startapp.d;
import com.startapp.d8;
import com.startapp.e8;
import com.startapp.ea;
import com.startapp.f8;
import com.startapp.g5;
import com.startapp.h2;
import com.startapp.hc;
import com.startapp.j2;
import com.startapp.l2;
import com.startapp.p7;
import com.startapp.q7;
import com.startapp.r5;
import com.startapp.sdk.ads.banner.BannerBase;
import com.startapp.sdk.ads.banner.BannerInterface;
import com.startapp.sdk.ads.banner.BannerListener;
import com.startapp.sdk.ads.banner.BannerMetaData;
import com.startapp.sdk.ads.banner.BannerOptions;
import com.startapp.sdk.ads.banner.bannerstandard.CloseableLayout;
import com.startapp.sdk.adsbase.Ad;
import com.startapp.sdk.adsbase.adinformation.AdInformationObject;
import com.startapp.sdk.adsbase.adlisteners.AdEventListener;
import com.startapp.sdk.adsbase.commontracking.TrackingParams;
import com.startapp.sdk.adsbase.model.AdPreferences;
import com.startapp.sdk.adsbase.mraid.bridge.MraidState;
import com.startapp.sdk.adsbase.remoteconfig.MetaData;
import com.startapp.sdk.components.ComponentLocator;
import com.startapp.x8;
import com.startapp.ya;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class BannerStandard extends BannerBase implements AdEventListener, BannerInterface {
    public static final String r = "BannerStandard";
    public BannerOptions A;
    public AdPreferences B;
    public final l2 C;
    public BannerListener D;
    public boolean E;
    public AdInformationObject F;
    public RelativeLayout G;
    public RelativeLayout H;
    public CloseableLayout I;
    public r5 J;
    public ad K;
    public ea L;
    public ea M;
    public MraidBannerController N;
    public MraidBannerController O;
    public ViewGroup P;
    public final r5.a Q;
    public final Runnable R;
    public final Runnable S;
    public BannerStandardAd s;
    public boolean t;
    public WebView twoPartWebView;
    public boolean u;
    public boolean v;
    public boolean w;
    public WebView webView;
    public boolean x;
    public final Handler y;
    public long z;

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class MraidBannerController extends a8 {
        private WebView activeWebView;
        private MraidState mraidState;
        private boolean mraidVisibility;
        private d8 nativeFeatureManager;
        private e8 orientationProperties;
        private f8 resizeProperties;

        /* compiled from: StartAppSDK */
        /* loaded from: classes3.dex */
        public class BannerWebViewClient extends c8 {
            public BannerWebViewClient(b8 b8Var) {
                super(b8Var);
            }

            @Override // android.webkit.WebViewClient
            public void onPageFinished(WebView webView, String str) {
                super.onPageFinished(webView, str);
                if (MraidBannerController.this.mraidState == MraidState.LOADING) {
                    aa.a(webView, true, "mraid.setPlacementType", "inline");
                    d.a(BannerStandard.this.getContext(), webView, MraidBannerController.this.nativeFeatureManager);
                    MraidBannerController.this.updateDisplayMetrics(webView);
                    MraidBannerController.this.mraidState = MraidState.DEFAULT;
                    d.a(MraidBannerController.this.mraidState, webView);
                    aa.a(webView, true, "mraid.fireReadyEvent", new Object[0]);
                }
                BannerStandard bannerStandard = BannerStandard.this;
                bannerStandard.s();
                if (MetaData.h.O()) {
                    try {
                        bannerStandard.b(webView);
                    } catch (Throwable th) {
                        p7.a(bannerStandard.getContext(), th);
                    }
                }
            }
        }

        public MraidBannerController(WebView webView, a8.a aVar) {
            super(aVar);
            this.mraidState = MraidState.LOADING;
            this.mraidVisibility = false;
            this.activeWebView = webView;
            webView.setWebViewClient(new BannerWebViewClient(this));
            this.nativeFeatureManager = new d8(BannerStandard.this.getContext());
            this.orientationProperties = new e8();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void fireViewableChangeEvent(boolean z) {
            if (this.mraidVisibility == z) {
                return;
            }
            this.mraidVisibility = z;
            aa.a(this.activeWebView, true, "mraid.fireViewableChangeEvent", Boolean.valueOf(z));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void updateDisplayMetrics(WebView webView) {
            Context context = BannerStandard.this.getContext();
            try {
                DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
                int i = displayMetrics.widthPixels;
                int i2 = displayMetrics.heightPixels;
                int[] iArr = new int[2];
                BannerStandard.this.getLocationOnScreen(iArr);
                int i3 = iArr[0];
                int i4 = iArr[1];
                d.b(context, i, i2, webView);
                Point point = BannerStandard.this.C.a;
                d.b(context, i3, i4, point.x, point.y, webView);
                d.a(context, i, i2, webView);
                Point point2 = BannerStandard.this.C.a;
                d.a(context, i3, i4, point2.x, point2.y, webView);
            } catch (Throwable th) {
                p7.a(context, th);
            }
        }

        @Override // com.startapp.a8, com.startapp.b8
        public void close() {
            BannerStandard.a(BannerStandard.this);
        }

        @Override // com.startapp.a8, com.startapp.b8
        public void expand(String str) {
            final BannerStandard bannerStandard = BannerStandard.this;
            String str2 = BannerStandard.r;
            bannerStandard.b();
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
            boolean z = (str == null || TextUtils.isEmpty(str)) ? false : true;
            if (z) {
                bannerStandard.u = false;
                if (bannerStandard.twoPartWebView == null) {
                    bannerStandard.twoPartWebView = ComponentLocator.a(bannerStandard.getContext()).t().a();
                }
                bannerStandard.O = new MraidBannerController(bannerStandard.twoPartWebView, new a8.a() { // from class: com.startapp.sdk.ads.banner.bannerstandard.BannerStandard.12
                    @Override // com.startapp.a8.a
                    public boolean onClickEvent(String str3) {
                        if (!BannerStandard.this.u) {
                            p7 p7Var = new p7(q7.c);
                            p7Var.d = "fake_click";
                            p7Var.g = g5.a(str3, (String) null);
                            p7Var.e = "jsTag=" + BannerStandard.this.v;
                            p7Var.a(BannerStandard.this.getContext());
                        }
                        BannerStandard bannerStandard2 = BannerStandard.this;
                        if ((!bannerStandard2.v || bannerStandard2.u) && str3 != null) {
                            return BannerStandard.a(bannerStandard2, str3);
                        }
                        return false;
                    }
                });
                bannerStandard.M = new ea(bannerStandard.twoPartWebView, bannerStandard.g(), new ea.b() { // from class: com.startapp.sdk.ads.banner.bannerstandard.BannerStandard.13
                    @Override // com.startapp.ea.b
                    public boolean onUpdate(boolean z2) {
                        BannerStandard.this.N.fireViewableChangeEvent(z2);
                        BannerStandard.this.O.fireViewableChangeEvent(z2);
                        return BannerStandard.this.s.r();
                    }
                });
                bannerStandard.twoPartWebView.setId(159868226);
                bannerStandard.a(bannerStandard.twoPartWebView);
                bannerStandard.twoPartWebView.loadUrl(str);
            }
            if (bannerStandard.N.getState() == MraidState.DEFAULT) {
                if (z) {
                    bannerStandard.I.addView(bannerStandard.twoPartWebView, layoutParams);
                } else {
                    RelativeLayout relativeLayout = bannerStandard.H;
                    if (relativeLayout != null) {
                        relativeLayout.removeView(bannerStandard.webView);
                        bannerStandard.H.setVisibility(4);
                    }
                    bannerStandard.I.addView(bannerStandard.webView, layoutParams);
                }
                if (bannerStandard.P == null) {
                    bannerStandard.P = bannerStandard.w();
                }
                bannerStandard.P.addView(bannerStandard.I, new FrameLayout.LayoutParams(-1, -1));
            } else if (bannerStandard.N.getState() == MraidState.RESIZED && z) {
                bannerStandard.I.removeView(bannerStandard.webView);
                RelativeLayout relativeLayout2 = bannerStandard.H;
                if (relativeLayout2 != null) {
                    relativeLayout2.addView(bannerStandard.webView, layoutParams);
                    bannerStandard.H.setVisibility(4);
                }
                bannerStandard.I.addView(bannerStandard.twoPartWebView, layoutParams);
            }
            bannerStandard.I.setLayoutParams(layoutParams);
            bannerStandard.N.setState(MraidState.EXPANDED);
        }

        public f8 getResizeProperties() {
            return this.resizeProperties;
        }

        public MraidState getState() {
            return this.mraidState;
        }

        @Override // com.startapp.a8
        public boolean isFeatureSupported(String str) {
            return this.nativeFeatureManager.b.contains(str);
        }

        @Override // com.startapp.a8, com.startapp.b8
        public void resize() {
            BannerStandard bannerStandard = BannerStandard.this;
            f8 resizeProperties = bannerStandard.N.getResizeProperties();
            if (resizeProperties == null) {
                d.a(bannerStandard.webView, "requires: setResizeProperties first", "resize");
                return;
            }
            bannerStandard.b();
            if (bannerStandard.N.getState() == MraidState.LOADING || bannerStandard.N.getState() == MraidState.HIDDEN) {
                return;
            }
            if (bannerStandard.N.getState() == MraidState.EXPANDED) {
                d.a(bannerStandard.webView, "Not allowed to resize from an already expanded ad", "resize");
                return;
            }
            int i = resizeProperties.a;
            int i2 = resizeProperties.b;
            int i3 = resizeProperties.c;
            int i4 = resizeProperties.d;
            int[] iArr = new int[2];
            bannerStandard.webView.getLocationOnScreen(iArr);
            Context context = bannerStandard.getContext();
            int b = d.b(context, iArr[0]) + i3;
            int b2 = d.b(context, iArr[1]) + i4;
            Rect rect = new Rect(b, b2, i + b, i2 + b2);
            ViewGroup w = bannerStandard.w();
            int b3 = d.b(context, w.getWidth());
            int b4 = d.b(context, w.getHeight());
            int[] iArr2 = new int[2];
            w.getLocationOnScreen(iArr2);
            int b5 = d.b(context, iArr2[0]);
            int b6 = d.b(context, iArr2[1]);
            if (!resizeProperties.f) {
                if (rect.width() > b3 || rect.height() > b4) {
                    d.a(bannerStandard.webView, "Not enough room for the ad", "resize");
                    return;
                }
                rect.offsetTo(Math.max(b5, Math.min(rect.left, (b5 + b3) - rect.width())), Math.max(b6, Math.min(rect.top, (b6 + b4) - rect.height())));
            }
            Rect rect2 = new Rect();
            try {
                CloseableLayout.ClosePosition a = CloseableLayout.ClosePosition.a(resizeProperties.e, CloseableLayout.ClosePosition.TOP_RIGHT);
                int i5 = bannerStandard.I.f;
                Gravity.apply(a.a(), i5, i5, rect, rect2);
                if (!new Rect(b5, b6, b3 + b5, b4 + b6).contains(rect2)) {
                    d.a(bannerStandard.webView, "The close region to appear within the max allowed size", "resize");
                    return;
                }
                if (!rect.contains(rect2)) {
                    d.a(bannerStandard.webView, "The close region to appear within the max allowed size", "resize");
                    return;
                }
                bannerStandard.I.setCloseVisible(false);
                bannerStandard.I.setClosePosition(a);
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(rect.width(), rect.height());
                layoutParams.leftMargin = rect.left - b5;
                layoutParams.topMargin = rect.top - b6;
                if (bannerStandard.N.getState() == MraidState.DEFAULT) {
                    RelativeLayout relativeLayout = bannerStandard.H;
                    if (relativeLayout != null) {
                        relativeLayout.removeView(bannerStandard.webView);
                        bannerStandard.H.setVisibility(4);
                    }
                    bannerStandard.I.addView(bannerStandard.webView, new FrameLayout.LayoutParams(-1, -1));
                    if (bannerStandard.P == null) {
                        bannerStandard.P = bannerStandard.w();
                    }
                    bannerStandard.P.addView(bannerStandard.I, layoutParams);
                } else if (bannerStandard.N.getState() == MraidState.RESIZED) {
                    bannerStandard.I.setLayoutParams(layoutParams);
                }
                bannerStandard.I.setClosePosition(a);
                bannerStandard.N.setState(MraidState.RESIZED);
            } catch (Exception e) {
                d.a(bannerStandard.webView, e.getMessage(), "resize");
            }
        }

        @Override // com.startapp.a8, com.startapp.b8
        public void setExpandProperties(Map<String, String> map) {
            String str = map.get("useCustomClose");
            if (str != null) {
                BannerStandard.a(BannerStandard.this, Boolean.parseBoolean(str));
            }
        }

        @Override // com.startapp.a8, com.startapp.b8
        public void setOrientationProperties(Map<String, String> map) {
            boolean parseBoolean = Boolean.parseBoolean(map.get("allowOrientationChange"));
            String str = map.get("forceOrientation");
            e8 e8Var = this.orientationProperties;
            if (e8Var.b == parseBoolean && e8Var.c == e8.a(str)) {
                return;
            }
            e8 e8Var2 = this.orientationProperties;
            e8Var2.b = parseBoolean;
            e8Var2.c = e8.a(str);
            applyOrientationProperties((Activity) BannerStandard.this.getContext(), this.orientationProperties);
        }

        @Override // com.startapp.a8, com.startapp.b8
        public void setResizeProperties(Map<String, String> map) {
            boolean z;
            try {
                int parseInt = Integer.parseInt(map.get("width"));
                int parseInt2 = Integer.parseInt(map.get("height"));
                int parseInt3 = Integer.parseInt(map.get("offsetX"));
                int parseInt4 = Integer.parseInt(map.get("offsetY"));
                String str = map.get("allowOffscreen");
                String str2 = map.get("customClosePosition");
                if (str != null && !Boolean.parseBoolean(str)) {
                    z = false;
                    this.resizeProperties = new f8(parseInt, parseInt2, parseInt3, parseInt4, str2, z);
                }
                z = true;
                this.resizeProperties = new f8(parseInt, parseInt2, parseInt3, parseInt4, str2, z);
            } catch (Exception unused) {
                d.a(this.activeWebView, "wrong format", "setResizeProperties");
            }
        }

        public void setState(MraidState mraidState) {
            this.mraidState = mraidState;
            d.a(mraidState, this.activeWebView);
        }

        @Override // com.startapp.a8, com.startapp.b8
        public void useCustomClose(String str) {
            BannerStandard.a(BannerStandard.this, Boolean.parseBoolean(str));
        }
    }

    public BannerStandard(Activity activity) {
        this((Context) activity);
    }

    public BannerStandard(Activity activity, AttributeSet attributeSet) {
        this((Context) activity, attributeSet);
    }

    public BannerStandard(Activity activity, AttributeSet attributeSet, int i) {
        this((Context) activity, attributeSet, i);
    }

    public BannerStandard(Activity activity, BannerListener bannerListener) {
        this((Context) activity, bannerListener);
    }

    public BannerStandard(Activity activity, AdPreferences adPreferences) {
        this((Context) activity, adPreferences);
    }

    public BannerStandard(Activity activity, AdPreferences adPreferences, BannerListener bannerListener) {
        this((Context) activity, adPreferences, bannerListener);
    }

    public BannerStandard(Activity activity, boolean z) {
        this((Context) activity, z);
    }

    public BannerStandard(Activity activity, boolean z, AdPreferences adPreferences) {
        this((Context) activity, z, adPreferences);
    }

    @Deprecated
    public BannerStandard(Context context) {
        this(context, true, (AdPreferences) null);
    }

    @Deprecated
    public BannerStandard(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    @Deprecated
    public BannerStandard(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.t = false;
        this.u = true;
        this.v = false;
        this.w = true;
        this.x = true;
        this.y = new Handler(Looper.getMainLooper());
        this.C = new l2(j(), f());
        this.E = false;
        this.F = null;
        this.G = null;
        this.Q = new r5.a() { // from class: com.startapp.sdk.ads.banner.bannerstandard.BannerStandard.1
            @Override // com.startapp.r5.a
            public void onSent() {
                BannerStandard bannerStandard = BannerStandard.this;
                Context context2 = bannerStandard.getContext();
                BannerListener bannerListener = bannerStandard.D;
                g5.a(bannerListener == null ? null : new j2(bannerListener, bannerStandard, context2));
                bannerStandard.z = System.currentTimeMillis() - bannerStandard.z;
                bannerStandard.p();
            }
        };
        this.R = new Runnable() { // from class: com.startapp.sdk.ads.banner.bannerstandard.BannerStandard.2
            @Override // java.lang.Runnable
            public void run() {
                BannerStandard bannerStandard = BannerStandard.this;
                RelativeLayout relativeLayout = bannerStandard.H;
                if (relativeLayout != null) {
                    relativeLayout.setVisibility(0);
                }
                if (bannerStandard.s != null) {
                    x8 r2 = ComponentLocator.a(bannerStandard.getContext()).r();
                    AdPreferences.Placement placement = AdPreferences.Placement.INAPP_BANNER;
                    int v = bannerStandard.v();
                    String adId = bannerStandard.s.getAdId();
                    r2.getClass();
                    if (adId != null) {
                        r2.a.put(new x8.a(placement, v), adId);
                    }
                }
            }
        };
        this.S = new Runnable() { // from class: com.startapp.sdk.ads.banner.bannerstandard.BannerStandard.3
            @Override // java.lang.Runnable
            public void run() {
                RelativeLayout relativeLayout = BannerStandard.this.H;
                if (relativeLayout != null) {
                    relativeLayout.setVisibility(4);
                }
            }
        };
        try {
            k();
        } catch (Throwable th) {
            p7.a(context, th);
        }
    }

    @Deprecated
    public BannerStandard(Context context, BannerListener bannerListener) {
        this(context, true, (AdPreferences) null);
        setBannerListener(bannerListener);
    }

    @Deprecated
    public BannerStandard(Context context, AdPreferences adPreferences) {
        this(context, true, adPreferences);
    }

    @Deprecated
    public BannerStandard(Context context, AdPreferences adPreferences, BannerListener bannerListener) {
        this(context, true, adPreferences);
        setBannerListener(bannerListener);
    }

    @Deprecated
    public BannerStandard(Context context, boolean z) {
        this(context, z, (AdPreferences) null);
    }

    @Deprecated
    public BannerStandard(Context context, boolean z, AdPreferences adPreferences) {
        super(context);
        this.t = false;
        this.u = true;
        this.v = false;
        this.w = true;
        this.x = true;
        this.y = new Handler(Looper.getMainLooper());
        this.C = new l2(j(), f());
        this.E = false;
        this.F = null;
        this.G = null;
        this.Q = new r5.a() { // from class: com.startapp.sdk.ads.banner.bannerstandard.BannerStandard.1
            @Override // com.startapp.r5.a
            public void onSent() {
                BannerStandard bannerStandard = BannerStandard.this;
                Context context2 = bannerStandard.getContext();
                BannerListener bannerListener = bannerStandard.D;
                g5.a(bannerListener == null ? null : new j2(bannerListener, bannerStandard, context2));
                bannerStandard.z = System.currentTimeMillis() - bannerStandard.z;
                bannerStandard.p();
            }
        };
        this.R = new Runnable() { // from class: com.startapp.sdk.ads.banner.bannerstandard.BannerStandard.2
            @Override // java.lang.Runnable
            public void run() {
                BannerStandard bannerStandard = BannerStandard.this;
                RelativeLayout relativeLayout = bannerStandard.H;
                if (relativeLayout != null) {
                    relativeLayout.setVisibility(0);
                }
                if (bannerStandard.s != null) {
                    x8 r2 = ComponentLocator.a(bannerStandard.getContext()).r();
                    AdPreferences.Placement placement = AdPreferences.Placement.INAPP_BANNER;
                    int v = bannerStandard.v();
                    String adId = bannerStandard.s.getAdId();
                    r2.getClass();
                    if (adId != null) {
                        r2.a.put(new x8.a(placement, v), adId);
                    }
                }
            }
        };
        this.S = new Runnable() { // from class: com.startapp.sdk.ads.banner.bannerstandard.BannerStandard.3
            @Override // java.lang.Runnable
            public void run() {
                RelativeLayout relativeLayout = BannerStandard.this.H;
                if (relativeLayout != null) {
                    relativeLayout.setVisibility(4);
                }
            }
        };
        try {
            this.w = z;
            this.B = adPreferences;
            k();
        } catch (Throwable th) {
            p7.a(context, th);
        }
    }

    public static void a(BannerStandard bannerStandard) {
        if (bannerStandard.N.getState() != MraidState.LOADING) {
            MraidState state = bannerStandard.N.getState();
            MraidState mraidState = MraidState.HIDDEN;
            if (state == mraidState) {
                return;
            }
            if (bannerStandard.N.getState() == MraidState.RESIZED || bannerStandard.N.getState() == MraidState.EXPANDED) {
                if (bannerStandard.O != null) {
                    bannerStandard.I.removeView(bannerStandard.twoPartWebView);
                    bannerStandard.M.a();
                    bannerStandard.M = null;
                    bannerStandard.O = null;
                    bannerStandard.twoPartWebView.stopLoading();
                    bannerStandard.twoPartWebView = null;
                } else {
                    bannerStandard.I.removeView(bannerStandard.webView);
                    bannerStandard.a((View) bannerStandard.webView);
                    g5.a(bannerStandard.R);
                }
                CloseableLayout closeableLayout = bannerStandard.I;
                if (closeableLayout != null && closeableLayout.getParent() != null && (closeableLayout.getParent() instanceof ViewGroup)) {
                    ((ViewGroup) closeableLayout.getParent()).removeView(closeableLayout);
                }
                bannerStandard.N.setState(MraidState.DEFAULT);
            } else if (bannerStandard.N.getState() == MraidState.DEFAULT) {
                g5.a(bannerStandard.S);
                bannerStandard.N.setState(mraidState);
            }
            bannerStandard.p();
        }
    }

    public static void a(BannerStandard bannerStandard, boolean z) {
        if (z == (!bannerStandard.I.d.isVisible())) {
            return;
        }
        bannerStandard.I.setCloseVisible(!z);
    }

    /* JADX WARN: Removed duplicated region for block: B:64:0x00e2 A[Catch: all -> 0x0105, TryCatch #0 {all -> 0x0105, blocks: (B:41:0x0055, B:43:0x005b, B:45:0x0064, B:46:0x0086, B:48:0x008d, B:51:0x0093, B:54:0x009b, B:56:0x00a2, B:57:0x00ab, B:59:0x00ae, B:60:0x00b0, B:62:0x00db, B:64:0x00e2, B:65:0x00e8, B:69:0x00fd), top: B:40:0x0055 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean a(com.startapp.sdk.ads.banner.bannerstandard.BannerStandard r22, java.lang.String r23) {
        /*
            Method dump skipped, instructions count: 480
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.startapp.sdk.ads.banner.bannerstandard.BannerStandard.a(com.startapp.sdk.ads.banner.bannerstandard.BannerStandard, java.lang.String):boolean");
    }

    @Override // com.startapp.sdk.ads.banner.BannerBase
    public void a(int i) {
        this.i = i;
    }

    public final void a(Point point, int i) {
        if (point.y <= 0) {
            point.y = i;
        }
    }

    public final void a(View view) {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(d.a(getContext(), this.C.a.x), d.a(getContext(), this.C.a.y));
        layoutParams.addRule(13);
        this.H.addView(view, layoutParams);
    }

    public final void a(WebView webView) {
        webView.setBackgroundColor(0);
        webView.setHorizontalScrollBarEnabled(false);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setVerticalScrollBarEnabled(false);
        webView.setOnTouchListener(new View.OnTouchListener() { // from class: com.startapp.sdk.ads.banner.bannerstandard.BannerStandard.4
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                BannerStandard.this.u = true;
                return motionEvent.getAction() == 2;
            }
        });
        webView.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.startapp.sdk.ads.banner.bannerstandard.BannerStandard.5
            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View view) {
                return true;
            }
        });
        webView.setLongClickable(false);
    }

    public final void a(String str) {
        setErrorMessage(str);
        if (this.D == null || this.E) {
            return;
        }
        this.E = true;
        d.a(getContext(), this.D, this);
    }

    public final boolean a(int i, int i2) {
        Point u = u();
        if (u.x < i || u.y < i2) {
            Point point = new Point(0, 0);
            ViewGroup.LayoutParams layoutParams = this.webView.getLayoutParams();
            if (layoutParams == null) {
                layoutParams = new RelativeLayout.LayoutParams(point.x, point.y);
            } else {
                layoutParams.width = point.x;
                layoutParams.height = point.y;
            }
            this.webView.setLayoutParams(layoutParams);
            return false;
        }
        Point point2 = this.C.a;
        point2.x = i;
        point2.y = i2;
        int a = d.a(getContext(), this.C.a.x);
        int a2 = d.a(getContext(), this.C.a.y);
        this.H.setMinimumWidth(a);
        this.H.setMinimumHeight(a2);
        ViewGroup.LayoutParams layoutParams2 = this.webView.getLayoutParams();
        if (layoutParams2 == null) {
            layoutParams2 = new RelativeLayout.LayoutParams(a, a2);
        } else {
            layoutParams2.width = a;
            layoutParams2.height = a2;
        }
        this.webView.setLayoutParams(layoutParams2);
        return true;
    }

    public final void b(Point point, int i) {
        if (point.x <= 0) {
            point.x = i;
        }
    }

    public final void b(WebView webView) {
        ad adVar = this.K;
        if (adVar == null) {
            adVar = new ad(webView);
            this.K = adVar;
        }
        AdSession adSession = adVar.a;
        if (adSession != null) {
            try {
                RelativeLayout relativeLayout = this.G;
                if (relativeLayout != null) {
                    FriendlyObstructionPurpose friendlyObstructionPurpose = FriendlyObstructionPurpose.OTHER;
                    if (adSession != null) {
                        adSession.addFriendlyObstruction(relativeLayout, friendlyObstructionPurpose, null);
                    }
                }
                CloseableLayout closeableLayout = this.I;
                if (closeableLayout != null) {
                    FriendlyObstructionPurpose friendlyObstructionPurpose2 = FriendlyObstructionPurpose.CLOSE_AD;
                    AdSession adSession2 = adVar.a;
                    if (adSession2 != null) {
                        adSession2.addFriendlyObstruction(closeableLayout, friendlyObstructionPurpose2, null);
                    }
                }
            } catch (RuntimeException e) {
                Log.e(r, "OMSDK error", e);
            }
            AdSession adSession3 = adVar.a;
            if (adSession3 != null) {
                adSession3.registerAdView(webView);
            }
            AdSession adSession4 = adVar.a;
            if (adSession4 != null) {
                adSession4.start();
            }
            if (adVar.b == null || !adVar.c.compareAndSet(false, true)) {
                return;
            }
            adVar.b.impressionOccurred();
        }
    }

    @Override // com.startapp.sdk.ads.banner.BannerBase
    public int c() {
        return Math.max(this.A.i() - ((int) this.z), 0);
    }

    @Override // com.startapp.sdk.ads.banner.BannerBase
    public int d() {
        return this.i;
    }

    @Override // com.startapp.sdk.ads.banner.BannerBase
    public String e() {
        return "StartApp Banner";
    }

    @Override // com.startapp.sdk.ads.banner.BannerBase
    public int f() {
        return 50;
    }

    @Override // com.startapp.sdk.ads.banner.BannerBase
    public String getBidToken() {
        BannerStandardAd bannerStandardAd = this.s;
        if (bannerStandardAd != null) {
            return aa.a(bannerStandardAd.j(), "bidToken", "bidToken");
        }
        return null;
    }

    @Override // com.startapp.sdk.ads.banner.BannerBase
    public int h() {
        return this.A.i();
    }

    @Override // com.startapp.sdk.ads.banner.BannerBase, com.startapp.sdk.ads.banner.BannerInterface
    public void hideBanner() {
        this.x = false;
        g5.a(this.S);
    }

    @Override // com.startapp.sdk.ads.banner.BannerBase
    public View i() {
        RelativeLayout relativeLayout = this.H;
        return relativeLayout != null ? relativeLayout : this;
    }

    @Override // com.startapp.sdk.ads.banner.BannerBase
    public int j() {
        return 300;
    }

    @Override // com.startapp.sdk.ads.banner.BannerBase
    public void l() {
        try {
            Context context = getContext();
            CloseableLayout closeableLayout = new CloseableLayout(context);
            this.I = closeableLayout;
            closeableLayout.setOnCloseListener(new CloseableLayout.a() { // from class: com.startapp.sdk.ads.banner.bannerstandard.BannerStandard.6
                @Override // com.startapp.sdk.ads.banner.bannerstandard.CloseableLayout.a
                public void onClose() {
                    BannerStandard.a(BannerStandard.this);
                }
            });
            WebView a = ComponentLocator.a(context).t().a();
            this.webView = a;
            this.N = new MraidBannerController(a, new a8.a() { // from class: com.startapp.sdk.ads.banner.bannerstandard.BannerStandard.7
                @Override // com.startapp.a8.a
                public boolean onClickEvent(String str) {
                    if (!BannerStandard.this.u) {
                        p7 p7Var = new p7(q7.c);
                        p7Var.d = "fake_click";
                        p7Var.g = g5.a(str, (String) null);
                        p7Var.e = "jsTag=" + BannerStandard.this.v;
                        p7Var.a(BannerStandard.this.getContext());
                    }
                    BannerStandard bannerStandard = BannerStandard.this;
                    if ((!bannerStandard.v || bannerStandard.u) && str != null) {
                        return BannerStandard.a(bannerStandard, str);
                    }
                    return false;
                }
            });
            this.A = new BannerOptions();
            BannerStandardAd bannerStandardAd = this.s;
            this.s = new BannerStandardAd(context, bannerStandardAd == null ? 0 : bannerStandardAd.v());
            if (this.B == null) {
                this.B = new AdPreferences();
            }
            if (getId() == -1) {
                setId(this.i);
            }
            this.webView.setId(159868225);
            a(this.webView);
            this.A = BannerMetaData.b.b();
            a(this.B);
            setMinimumWidth(d.a(getContext(), this.C.a.x));
            setMinimumHeight(d.a(getContext(), this.C.a.y));
            WebView webView = this.webView;
            Context context2 = getContext();
            Runnable runnable = new Runnable() { // from class: com.startapp.sdk.ads.banner.bannerstandard.BannerStandard.8
                @Override // java.lang.Runnable
                public void run() {
                }
            };
            TrackingParams trackingParams = new TrackingParams(this.j);
            boolean a2 = this.s.a(0);
            hc hcVar = new hc(context2, runnable, trackingParams);
            hcVar.b = a2;
            webView.addJavascriptInterface(hcVar, "startappwall");
            this.H = new RelativeLayout(getContext());
            a(this.webView);
            g5.a(this.S);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams.addRule(13);
            addView(this.H, layoutParams);
            if (this.w) {
                getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.startapp.sdk.ads.banner.bannerstandard.BannerStandard.9
                    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                    public void onGlobalLayout() {
                        ViewTreeObserver viewTreeObserver = BannerStandard.this.getViewTreeObserver();
                        int i = ya.a;
                        if (Build.VERSION.SDK_INT >= 16) {
                            viewTreeObserver.removeOnGlobalLayoutListener(this);
                        } else {
                            viewTreeObserver.removeGlobalOnLayoutListener(this);
                        }
                        BannerStandard bannerStandard = BannerStandard.this;
                        if (bannerStandard.t) {
                            return;
                        }
                        bannerStandard.n();
                    }
                });
            }
        } catch (Throwable th) {
            p7.a(getContext(), th);
            hideBanner();
            a("BannerStandard.init - webview failed");
        }
    }

    public void loadHtml() {
        String j;
        BannerStandardAd bannerStandardAd = this.s;
        if (bannerStandardAd == null || (j = bannerStandardAd.j()) == null) {
            return;
        }
        String str = this.j;
        if (str != null && str.length() > 0) {
            j = j.replaceAll("startapp_adtag_placeholder", this.j);
        }
        this.y.postDelayed(new Runnable() { // from class: com.startapp.sdk.ads.banner.bannerstandard.BannerStandard.10
            @Override // java.lang.Runnable
            public void run() {
                BannerStandard bannerStandard = BannerStandard.this;
                String str2 = BannerStandard.r;
                bannerStandard.m();
            }
        }, this.A.i());
        this.z = System.currentTimeMillis();
        aa.a(getContext(), this.webView, j);
    }

    @Override // com.startapp.sdk.ads.banner.BannerBase
    public void o() {
        t();
        if (this.B == null) {
            this.B = new AdPreferences();
        }
        if (this.s != null) {
            Point point = this.f;
            if (point == null) {
                point = u();
            }
            this.s.a(point.x, point.y);
            this.s.setState(Ad.AdState.UN_INITIALIZED);
            this.s.c(v());
            this.s.load(this.B, this);
        }
    }

    @Override // com.startapp.sdk.ads.banner.BannerBase, android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        WebView webView = this.webView;
        if (webView != null) {
            ya.b(webView);
        }
        WebView webView2 = this.twoPartWebView;
        if (webView2 != null) {
            ya.b(webView2);
        }
    }

    @Override // com.startapp.sdk.ads.banner.BannerBase, android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        WebView webView = this.webView;
        if (webView != null) {
            ya.a(webView);
        }
        WebView webView2 = this.twoPartWebView;
        if (webView2 != null) {
            ya.a(webView2);
        }
        r5 r5Var = this.J;
        if (r5Var != null) {
            r5Var.a("AD_CLOSED_TOO_QUICKLY", null);
        }
        ea eaVar = this.L;
        if (eaVar != null) {
            eaVar.a();
        }
        ea eaVar2 = this.M;
        if (eaVar2 != null) {
            eaVar2.a();
        }
        s();
        t();
        WebView webView3 = this.webView;
        Map<Activity, Integer> map = aa.a;
        new Handler(Looper.getMainLooper()).postAtTime(null, webView3, SystemClock.uptimeMillis() + 1000);
    }

    @Override // com.startapp.sdk.adsbase.adlisteners.AdEventListener
    public void onFailedToReceiveAd(Ad ad) {
        if (ad != null) {
            a(ad.getErrorMessage());
        }
    }

    @Override // com.startapp.sdk.adsbase.adlisteners.AdEventListener
    public void onReceiveAd(Ad ad) {
        this.u = false;
        removeView(this.G);
        BannerStandardAd bannerStandardAd = this.s;
        if (bannerStandardAd == null || bannerStandardAd.j() == null || this.s.j().compareTo("") == 0) {
            a("No Banner received");
            return;
        }
        this.v = "true".equals(aa.a(this.s.j(), "@jsTag@", "@jsTag@"));
        loadHtml();
        try {
            if (!a(Integer.parseInt(aa.a(this.s.j(), "@width@", "@width@")), Integer.parseInt(aa.a(this.s.j(), "@height@", "@height@")))) {
                a("Banner cannot be displayed (not enough room)");
                return;
            }
            this.t = true;
            q();
            x();
            a();
            r();
            if (this.x) {
                g5.a(this.R);
            }
            if (this.D == null || this.E) {
                return;
            }
            this.E = true;
            Context context = getContext();
            BannerListener bannerListener = this.D;
            g5.a(bannerListener == null ? null : new h2(bannerListener, this, context));
        } catch (NumberFormatException unused) {
            a("Error Casting width & height from HTML");
        } catch (Throwable th) {
            p7.a(getContext(), th);
            a(th.getMessage());
        }
    }

    @Override // android.view.View
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        int a = d.a(getContext(), this.C.a.x);
        int a2 = d.a(getContext(), this.C.a.y);
        if (i < a || i2 < a2) {
            g5.a(this.S);
        } else if (this.x && this.t) {
            g5.a(this.R);
        }
    }

    @Override // com.startapp.sdk.ads.banner.BannerBase, android.view.View
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z) {
            WebView webView = this.webView;
            if (webView != null) {
                ya.b(webView);
            }
            WebView webView2 = this.twoPartWebView;
            if (webView2 != null) {
                ya.b(webView2);
                return;
            }
            return;
        }
        WebView webView3 = this.webView;
        if (webView3 != null) {
            ya.a(webView3);
        }
        WebView webView4 = this.twoPartWebView;
        if (webView4 != null) {
            ya.a(webView4);
        }
    }

    @Override // com.startapp.sdk.ads.banner.BannerBase
    public void p() {
        r5 r5Var = this.J;
        if (r5Var == null || !r5Var.k.get()) {
            return;
        }
        super.p();
    }

    public final void q() {
        if (this.F == null && this.G == null) {
            this.G = new RelativeLayout(getContext());
            AdInformationObject adInformationObject = new AdInformationObject(getContext(), AdInformationObject.Size.SMALL, AdPreferences.Placement.INAPP_BANNER, this.s.getAdInfoOverride(), this.s.getConsentData());
            this.F = adInformationObject;
            adInformationObject.a(this.G);
        }
        try {
            ViewGroup viewGroup = (ViewGroup) this.G.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(this.G);
            }
        } catch (Exception unused) {
        }
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(13);
        this.webView.addView(this.G, layoutParams);
    }

    public final void r() {
        BannerStandardAd bannerStandardAd = this.s;
        if (bannerStandardAd == null || !bannerStandardAd.r()) {
            return;
        }
        this.L = new ea(this.webView, g(), new ea.b() { // from class: com.startapp.sdk.ads.banner.bannerstandard.BannerStandard.11
            @Override // com.startapp.ea.b
            public boolean onUpdate(boolean z) {
                BannerStandard.this.N.fireViewableChangeEvent(z);
                return BannerStandard.this.s.r();
            }
        });
    }

    public final void s() {
        this.y.removeCallbacksAndMessages(null);
    }

    @Override // com.startapp.sdk.ads.banner.BannerBase
    public void setAdTag(String str) {
        this.j = str;
    }

    @Override // com.startapp.sdk.ads.banner.BannerInterface
    public void setBannerListener(BannerListener bannerListener) {
        this.D = bannerListener;
    }

    @Override // com.startapp.sdk.ads.banner.BannerInterface
    public void showBanner() {
        try {
            ComponentLocator.a(getContext()).q().a(2048);
        } catch (Throwable unused) {
        }
        this.x = true;
        g5.a(this.R);
    }

    public final void t() {
        ad adVar = this.K;
        this.K = null;
        if (adVar != null) {
            try {
                AdSession adSession = adVar.a;
                if (adSession != null) {
                    adSession.finish();
                }
            } catch (Throwable th) {
                p7.a(getContext(), th);
            }
        }
    }

    public final Point u() {
        BannerStandard bannerStandard;
        BannerStandard bannerStandard2;
        View view;
        Point point = new Point();
        if (getLayoutParams() != null && getLayoutParams().width > 0) {
            point.x = d.b(getContext(), getLayoutParams().width + 1);
        }
        if (getLayoutParams() != null && getLayoutParams().height > 0) {
            point.y = d.b(getContext(), getLayoutParams().height + 1);
        }
        if (getLayoutParams() != null && getLayoutParams().width > 0 && getLayoutParams().height > 0) {
            this.s.b(true);
        }
        if (getLayoutParams() == null || getLayoutParams().width <= 0 || getLayoutParams().height <= 0) {
            DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
            try {
                if (getParent() instanceof View) {
                    view = (View) getParent();
                    bannerStandard2 = this;
                } else {
                    bannerStandard2 = this;
                    view = null;
                }
                while (view != null) {
                    try {
                        if (view.getMeasuredWidth() > 0 && view.getMeasuredHeight() > 0) {
                            break;
                        }
                        if (view.getMeasuredWidth() > 0) {
                            bannerStandard2.b(point, d.b(bannerStandard2.getContext(), (view.getMeasuredWidth() - view.getPaddingLeft()) - view.getPaddingRight()));
                        }
                        if (view.getMeasuredHeight() > 0) {
                            bannerStandard2.a(point, d.b(bannerStandard2.getContext(), (view.getMeasuredHeight() - view.getPaddingBottom()) - view.getPaddingTop()));
                        }
                        view = view.getParent() instanceof View ? (View) view.getParent() : null;
                    } catch (Throwable th) {
                        bannerStandard = bannerStandard2;
                        th = th;
                        p7.a(bannerStandard.getContext(), th);
                        bannerStandard.b(point, d.b(bannerStandard.getContext(), displayMetrics.widthPixels));
                        bannerStandard.a(point, d.b(bannerStandard.getContext(), displayMetrics.heightPixels));
                        return point;
                    }
                }
                if (view == null) {
                    bannerStandard2.b(point, d.b(bannerStandard2.getContext(), displayMetrics.widthPixels));
                    bannerStandard2.a(point, d.b(bannerStandard2.getContext(), displayMetrics.heightPixels));
                } else {
                    bannerStandard2.b(point, d.b(bannerStandard2.getContext(), (view.getMeasuredWidth() - view.getPaddingLeft()) - view.getPaddingRight()));
                    bannerStandard2.a(point, d.b(bannerStandard2.getContext(), (view.getMeasuredHeight() - view.getPaddingBottom()) - view.getPaddingTop()));
                }
            } catch (Throwable th2) {
                th = th2;
                bannerStandard = this;
            }
        }
        return point;
    }

    public int v() {
        return 0;
    }

    public final ViewGroup w() {
        View rootView;
        ViewGroup viewGroup = this.P;
        if (viewGroup != null) {
            return viewGroup;
        }
        Context context = getContext();
        RelativeLayout relativeLayout = this.H;
        View view = null;
        View findViewById = !(context instanceof Activity) ? null : ((Activity) context).getWindow().getDecorView().findViewById(R.id.content);
        if (relativeLayout != null && (rootView = relativeLayout.getRootView()) != null && (view = rootView.findViewById(R.id.content)) == null) {
            view = rootView;
        }
        if (findViewById == null) {
            findViewById = view;
        }
        return findViewById instanceof ViewGroup ? (ViewGroup) findViewById : this.H;
    }

    public void x() {
        r5 r5Var = new r5(getContext(), this.s.trackingUrls, new TrackingParams(this.j), this.s.h() != null ? TimeUnit.SECONDS.toMillis(this.s.h().longValue()) : TimeUnit.SECONDS.toMillis(MetaData.h.m()));
        this.J = r5Var;
        r5Var.l = new WeakReference<>(this.Q);
        a(this.J);
    }
}
