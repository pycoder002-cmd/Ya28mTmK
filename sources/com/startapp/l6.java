package com.startapp;

import android.app.Activity;
import android.app.Application;
import com.startapp.sdk.adsbase.StartAppSDKInternal;
import com.startapp.sdk.components.ComponentLocator;
import com.startapp.sdk.triggeredlinks.AppEventsMetadata;
import com.startapp.sdk.triggeredlinks.TriggeredLinksMetadata;
import java.util.Map;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class l6 extends e9 {
    public final m6 a;
    public int b;
    public boolean c;
    public boolean d;

    public l6(m6 m6Var) {
        this.a = m6Var;
    }

    @Override // com.startapp.e9, android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
        if (activity == null) {
            return;
        }
        int i = this.b + 1;
        this.b = i;
        if (i != 1 || this.c) {
            return;
        }
        if (!this.d) {
            this.d = true;
            StartAppSDKInternal startAppSDKInternal = (StartAppSDKInternal) this.a;
            StartAppSDKInternal.f(startAppSDKInternal.k);
            pd pdVar = startAppSDKInternal.D;
            if (pdVar != null) {
                TriggeredLinksMetadata a = pdVar.a();
                AppEventsMetadata a2 = a != null ? a.a() : null;
                Map<String, String> c = a2 != null ? a2.c() : null;
                if (c != null) {
                    pdVar.a(a, c, "Launch");
                }
            }
        }
        StartAppSDKInternal startAppSDKInternal2 = (StartAppSDKInternal) this.a;
        StartAppSDKInternal.f(startAppSDKInternal2.k);
        pd pdVar2 = startAppSDKInternal2.D;
        if (pdVar2 != null) {
            TriggeredLinksMetadata a3 = pdVar2.a();
            AppEventsMetadata a4 = a3 != null ? a3.a() : null;
            Map<String, String> a5 = a4 != null ? a4.a() : null;
            if (a5 != null) {
                pdVar2.a(a3, a5, "Active");
            }
        }
    }

    @Override // com.startapp.e9, android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
        if (activity == null) {
            return;
        }
        this.b--;
        boolean isChangingConfigurations = activity.isChangingConfigurations();
        this.c = isChangingConfigurations;
        if (this.b != 0 || isChangingConfigurations) {
            return;
        }
        StartAppSDKInternal startAppSDKInternal = (StartAppSDKInternal) this.a;
        StartAppSDKInternal.f(startAppSDKInternal.k);
        pd pdVar = startAppSDKInternal.D;
        if (pdVar != null) {
            TriggeredLinksMetadata a = pdVar.a();
            AppEventsMetadata a2 = a != null ? a.a() : null;
            Map<String, String> b = a2 != null ? a2.b() : null;
            if (b != null) {
                pdVar.a(a, b, "Inactive");
            }
        }
        Application application = startAppSDKInternal.k;
        if (application != null) {
            xc n = ComponentLocator.a(application).n();
            n.getClass();
            try {
                n.c();
            } catch (Throwable th) {
                p7.a(n.b, th);
            }
        }
    }
}
