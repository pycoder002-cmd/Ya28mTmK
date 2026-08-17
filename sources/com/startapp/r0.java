package com.startapp;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import com.startapp.networkTest.startapp.CoverageMapperManager;
import com.startapp.networkTest.startapp.NetworkTester;
import com.startapp.networkTest.threads.ThreadManager;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class r0 implements Application.ActivityLifecycleCallbacks, ComponentCallbacks2 {
    private static final String a = "r0";
    private static final boolean b = false;
    private Application c;
    private ScheduledFuture<?> d;
    private CoverageMapperManager h;
    private int e = -1;
    private int f = 0;
    private boolean g = false;
    private final Runnable i = new a();

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            r0.this.c();
            if (r0.this.f != 0 || r0.this.g) {
                return;
            }
            r0.this.d.cancel(false);
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class b implements NetworkTester.b {
        public b() {
        }

        @Override // com.startapp.networkTest.startapp.NetworkTester.b
        public void a(boolean z) {
        }
    }

    public r0(Context context, CoverageMapperManager coverageMapperManager) {
        this.c = (Application) context.getApplicationContext();
        this.h = coverageMapperManager;
    }

    private void a() {
        this.e = 1;
        if (s.b().FOREGROUND_TEST_CT_ENABLED()) {
            c();
            g();
        }
        if (s.b().FOREGROUND_TEST_NIR_ENABLED()) {
            f();
        }
    }

    private void b() {
        this.e = 0;
        i();
        j();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        NetworkTester.runTests(this.c, new b());
    }

    private void f() {
        this.h.c();
    }

    private void g() {
        j();
        long FOREGROUND_TEST_CT_SCHEDULE_INTERVAL = s.b().FOREGROUND_TEST_CT_SCHEDULE_INTERVAL();
        if (FOREGROUND_TEST_CT_SCHEDULE_INTERVAL > 0) {
            this.d = ThreadManager.b().d().scheduleWithFixedDelay(this.i, FOREGROUND_TEST_CT_SCHEDULE_INTERVAL, FOREGROUND_TEST_CT_SCHEDULE_INTERVAL, TimeUnit.MILLISECONDS);
        }
    }

    private void i() {
        this.h.f();
    }

    private void j() {
        ScheduledFuture<?> scheduledFuture = this.d;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
            this.d = null;
        }
    }

    public int d() {
        return this.e;
    }

    public void e() {
        this.e = 0;
        this.c.registerActivityLifecycleCallbacks(this);
        this.c.registerComponentCallbacks(this);
    }

    public void h() {
        this.c.unregisterActivityLifecycleCallbacks(this);
        this.c.unregisterComponentCallbacks(this);
        this.e = -1;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity) {
        if (!activity.isTaskRoot() || this.f >= 0) {
            return;
        }
        this.f = 0;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
        int i = this.f + 1;
        this.f = i;
        if (i != 1 || this.g) {
            return;
        }
        a();
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
        boolean isChangingConfigurations = activity.isChangingConfigurations();
        this.g = isChangingConfigurations;
        int i = this.f - 1;
        this.f = i;
        if (i != 0 || isChangingConfigurations) {
            return;
        }
        b();
    }

    @Override // android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
    }

    @Override // android.content.ComponentCallbacks
    public void onLowMemory() {
    }

    @Override // android.content.ComponentCallbacks2
    public void onTrimMemory(int i) {
    }
}
