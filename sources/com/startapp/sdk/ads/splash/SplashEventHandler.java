package com.startapp.sdk.ads.splash;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.startapp.g5;
import com.startapp.la;
import com.startapp.p7;
import com.startapp.s3;
import com.startapp.sdk.adsbase.adrules.AdRulesResult;
import com.startapp.sdk.adsbase.adrules.AdaptMetaData;
import com.startapp.sdk.adsbase.cache.CacheKey;
import com.startapp.sdk.adsbase.model.AdPreferences;
import com.startapp.v6;
import com.startapp.y8;
import java.lang.ref.WeakReference;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class SplashEventHandler {
    public final Context a;
    public final WeakReference<Activity> b;
    public boolean c;
    public boolean d;
    public boolean e;
    public boolean f;
    public boolean g;
    public boolean h;
    public SplashState i;
    public SplashHtml j;
    public final BroadcastReceiver k;

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public enum SplashState {
        LOADING,
        RECEIVED,
        DISPLAYED,
        HIDDEN,
        DO_NOT_DISPLAY
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class a implements s3 {
        public a() {
        }

        @Override // com.startapp.s3
        public void a() {
            SplashEventHandler.this.b();
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class b extends BroadcastReceiver {
        public b() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            SplashEventHandler.this.f = true;
        }
    }

    public SplashEventHandler(Activity activity) {
        this.c = false;
        this.d = true;
        this.e = false;
        this.f = false;
        this.g = false;
        this.h = false;
        this.i = SplashState.LOADING;
        this.j = null;
        this.k = new b();
        this.a = y8.b(activity);
        this.b = new WeakReference<>(activity);
    }

    public SplashEventHandler(Activity activity, SplashHtml splashHtml) {
        this(activity);
        this.j = splashHtml;
    }

    public void a() {
        this.i = SplashState.DO_NOT_DISPLAY;
        a(null);
    }

    public final void a(Runnable runnable) {
        if (this.c) {
            if (this.h || runnable == null) {
                SplashState splashState = this.i;
                if (splashState == SplashState.RECEIVED && runnable != null) {
                    this.d = false;
                    runnable.run();
                } else if (splashState != SplashState.LOADING) {
                    c();
                }
            }
        }
    }

    public void a(Runnable runnable, CacheKey cacheKey) {
        AdRulesResult a2 = AdaptMetaData.a.a().a(AdPreferences.Placement.INAPP_SPLASH, null);
        if (a2.b()) {
            a(runnable);
            return;
        }
        this.i = SplashState.DO_NOT_DISPLAY;
        if (cacheKey != null) {
            g5.a(this.a, g5.a(v6.a.a(cacheKey)), (String) null, 0, a2.a());
        }
        c();
    }

    public void b() {
        if (!this.e) {
            this.e = true;
            la.a(this.a).a(new Intent("com.startapp.android.splashHidden"));
        }
        try {
            la.a(this.a).a(this.k);
        } catch (Throwable th) {
            p7.a(this.a, th);
        }
        Activity activity = this.b.get();
        if (activity == null || activity.isFinishing()) {
            return;
        }
        try {
            activity.finish();
        } catch (Throwable th2) {
            p7.a(this.a, th2);
        }
    }

    public final void c() {
        SplashHtml splashHtml = this.j;
        a aVar = new a();
        if (splashHtml == null) {
            b();
        } else {
            splashHtml.callback = aVar;
            splashHtml.a();
        }
    }

    public void d() {
        this.c = true;
    }
}
