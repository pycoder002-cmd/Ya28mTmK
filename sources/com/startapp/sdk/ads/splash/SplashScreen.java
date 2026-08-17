package com.startapp.sdk.ads.splash;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import com.startapp.p7;
import com.startapp.s3;
import com.startapp.sdk.ads.splash.SplashConfig;
import com.startapp.sdk.ads.splash.SplashEventHandler;
import com.startapp.sdk.adsbase.Ad;
import com.startapp.sdk.adsbase.StartAppAd;
import com.startapp.sdk.adsbase.adlisteners.AdDisplayListener;
import com.startapp.sdk.adsbase.adlisteners.AdEventListener;
import com.startapp.sdk.adsbase.adrules.AdRulesResult;
import com.startapp.sdk.adsbase.cache.CacheKey;
import com.startapp.sdk.adsbase.model.AdPreferences;
import com.startapp.u3;
import com.startapp.v3;
import com.startapp.w3;
import com.startapp.y8;
import com.startapp.ya;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class SplashScreen {
    public Activity a;
    public SplashEventHandler b;
    public SplashConfig c;
    public CacheKey d;
    public SplashStartAppAd h;
    public AdPreferences i;
    public SplashHtml e = null;
    public Handler f = new Handler();
    public boolean g = false;
    public Runnable j = new a();
    public Runnable k = new b();
    public AdEventListener l = new c();

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public static class SplashStartAppAd extends StartAppAd {
        private static final long serialVersionUID = 1;

        public SplashStartAppAd(Context context) {
            super(context);
            this.placement = AdPreferences.Placement.INAPP_SPLASH;
        }

        @Override // com.startapp.sdk.adsbase.StartAppAd
        public AdRulesResult a(String str, AdPreferences.Placement placement) {
            return new AdRulesResult(true, "");
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            boolean z;
            SplashScreen splashScreen = SplashScreen.this;
            if (!splashScreen.c.b(splashScreen.a)) {
                throw new IllegalArgumentException(splashScreen.c.getErrorMessage());
            }
            View view = null;
            if (splashScreen.b()) {
                view = splashScreen.c.a((Context) splashScreen.a);
            } else {
                SplashHtml splashHtml = splashScreen.e;
                if (splashHtml != null) {
                    view = splashHtml.b();
                }
            }
            if (view != null) {
                splashScreen.a.setContentView(view, new ViewGroup.LayoutParams(-1, -1));
                z = true;
            } else {
                z = false;
            }
            if (!z) {
                SplashScreen.this.a.finish();
                return;
            }
            SplashScreen splashScreen2 = SplashScreen.this;
            Context a = y8.a(splashScreen2.a);
            if (a == null) {
                a = splashScreen2.a;
            }
            SplashStartAppAd splashStartAppAd = new SplashStartAppAd(a);
            splashScreen2.h = splashStartAppAd;
            splashScreen2.d = splashStartAppAd.loadSplash(splashScreen2.i, splashScreen2.l);
            SplashScreen splashScreen3 = SplashScreen.this;
            splashScreen3.f.postDelayed(new u3(splashScreen3), splashScreen3.c.a().longValue());
            splashScreen3.f.postDelayed(new v3(splashScreen3), splashScreen3.c.getMinSplashTime().getIndex());
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class b implements Runnable {

        /* compiled from: StartAppSDK */
        /* loaded from: classes3.dex */
        public class a implements s3 {

            /* compiled from: StartAppSDK */
            /* renamed from: com.startapp.sdk.ads.splash.SplashScreen$b$a$a, reason: collision with other inner class name */
            /* loaded from: classes3.dex */
            public class C0062a implements AdDisplayListener {
                public C0062a() {
                }

                @Override // com.startapp.sdk.adsbase.adlisteners.AdDisplayListener
                public void adClicked(Ad ad) {
                    SplashScreen.this.b.f = true;
                }

                @Override // com.startapp.sdk.adsbase.adlisteners.AdDisplayListener
                public void adDisplayed(Ad ad) {
                    SplashScreen.this.b.i = SplashEventHandler.SplashState.DISPLAYED;
                }

                @Override // com.startapp.sdk.adsbase.adlisteners.AdDisplayListener
                public void adHidden(Ad ad) {
                    SplashEventHandler splashEventHandler = SplashScreen.this.b;
                    splashEventHandler.i = SplashEventHandler.SplashState.HIDDEN;
                    splashEventHandler.b();
                }

                @Override // com.startapp.sdk.adsbase.adlisteners.AdDisplayListener
                public void adNotDisplayed(Ad ad) {
                }
            }

            public a() {
            }

            @Override // com.startapp.s3
            public void a() {
                SplashStartAppAd splashStartAppAd;
                SplashScreen splashScreen = SplashScreen.this;
                if (splashScreen.g || (splashStartAppAd = splashScreen.h) == null) {
                    return;
                }
                splashStartAppAd.showAd(new C0062a());
                SplashScreen splashScreen2 = SplashScreen.this;
                if (splashScreen2.c.getMaxAdDisplayTime() != SplashConfig.MaxAdDisplayTime.FOR_EVER) {
                    splashScreen2.f.postDelayed(new w3(splashScreen2), splashScreen2.c.getMaxAdDisplayTime().getIndex());
                }
                SplashScreen.this.a.finish();
            }
        }

        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            SplashScreen splashScreen = SplashScreen.this;
            SplashEventHandler splashEventHandler = splashScreen.b;
            SplashHtml splashHtml = splashScreen.e;
            a aVar = new a();
            splashEventHandler.getClass();
            if (splashHtml == null) {
                aVar.a();
            } else {
                splashHtml.callback = aVar;
                splashHtml.a();
            }
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class c implements AdEventListener {
        public c() {
        }

        @Override // com.startapp.sdk.adsbase.adlisteners.AdEventListener
        public void onFailedToReceiveAd(Ad ad) {
            SplashScreen splashScreen = SplashScreen.this;
            if (splashScreen.h != null) {
                SplashEventHandler splashEventHandler = splashScreen.b;
                splashEventHandler.i = SplashEventHandler.SplashState.DO_NOT_DISPLAY;
                splashEventHandler.a(null);
            }
        }

        @Override // com.startapp.sdk.adsbase.adlisteners.AdEventListener
        public void onReceiveAd(Ad ad) {
            SplashScreen splashScreen = SplashScreen.this;
            SplashEventHandler splashEventHandler = splashScreen.b;
            Runnable runnable = splashScreen.k;
            if (splashEventHandler.i == SplashEventHandler.SplashState.LOADING) {
                splashEventHandler.i = SplashEventHandler.SplashState.RECEIVED;
            }
            splashEventHandler.a(runnable);
        }
    }

    public SplashScreen(Activity activity, SplashConfig splashConfig, AdPreferences adPreferences) {
        this.a = activity;
        this.c = splashConfig;
        this.i = adPreferences;
        try {
            a();
            this.b = new SplashEventHandler(activity, this.e);
        } catch (Throwable th) {
            SplashEventHandler splashEventHandler = new SplashEventHandler(activity);
            this.b = splashEventHandler;
            splashEventHandler.d();
            this.b.a();
            p7.a(activity, th);
        }
    }

    public final void a() {
        SplashConfig splashConfig = this.c;
        Activity activity = this.a;
        if (splashConfig.getLogo() == null && splashConfig.getLogoRes() == -1 && splashConfig.getLogoByteArray() != null) {
            byte[] logoByteArray = splashConfig.getLogoByteArray();
            splashConfig.f = new BitmapDrawable(activity.getResources(), BitmapFactory.decodeByteArray(logoByteArray, 0, logoByteArray.length));
        }
        if (b()) {
            return;
        }
        this.e = this.c.a(this.a);
    }

    public final boolean b() {
        return !this.c.isHtmlSplash() || this.c.b();
    }

    public final boolean c() {
        int i = this.a.getResources().getConfiguration().orientation;
        if (this.c.getOrientation() == SplashConfig.Orientation.AUTO) {
            if (i == 2) {
                this.c.setOrientation(SplashConfig.Orientation.LANDSCAPE);
            } else {
                this.c.setOrientation(SplashConfig.Orientation.PORTRAIT);
            }
        }
        int ordinal = this.c.getOrientation().ordinal();
        if (ordinal == 0) {
            boolean z = i == 2;
            Activity activity = this.a;
            int i2 = ya.a;
            try {
                if (Build.VERSION.SDK_INT >= 9) {
                    activity.setRequestedOrientation(7);
                } else {
                    activity.setRequestedOrientation(1);
                }
                return z;
            } catch (Throwable unused) {
                return z;
            }
        }
        if (ordinal != 1) {
            return false;
        }
        boolean z2 = i == 1;
        Activity activity2 = this.a;
        int i3 = ya.a;
        try {
            if (Build.VERSION.SDK_INT >= 9) {
                activity2.setRequestedOrientation(6);
            } else {
                activity2.setRequestedOrientation(0);
            }
        } catch (Throwable unused2) {
        }
        return z2;
    }
}
