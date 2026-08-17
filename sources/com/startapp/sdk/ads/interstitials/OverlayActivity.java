package com.startapp.sdk.ads.interstitials;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import com.startapp.aa;
import com.startapp.g5;
import com.startapp.p2;
import com.startapp.sdk.adsbase.Ad;
import com.startapp.sdk.adsbase.model.AdPreferences;
import com.startapp.sdk.components.ComponentLocator;
import com.startapp.x8;
import com.startapp.ya;
import java.io.Serializable;
import java.util.Map;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class OverlayActivity extends Activity {
    public p2 a;
    public boolean b;
    public int c;
    public boolean d;
    public Bundle e;
    public int f = -1;

    /* JADX WARN: Removed duplicated region for block: B:16:0x00c4  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0113  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x011d  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0139  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x014e  */
    /* JADX WARN: Removed duplicated region for block: B:35:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void a() {
        /*
            Method dump skipped, instructions count: 338
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.startapp.sdk.ads.interstitials.OverlayActivity.a():void");
    }

    public final boolean b() {
        return this.b && Build.VERSION.SDK_INT != 26;
    }

    @Override // android.app.Activity
    public void finish() {
        p2 p2Var = this.a;
        if (p2Var != null) {
            p2Var.h();
        }
        super.finish();
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        p2 p2Var = this.a;
        if (p2Var == null || !p2Var.c()) {
            super.onBackPressed();
        }
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (b()) {
            a();
            p2 p2Var = this.a;
            if (p2Var != null) {
                p2Var.a(this.e);
                this.a.f();
            }
            this.b = false;
        }
        p2 p2Var2 = this.a;
        if (p2Var2 != null) {
            p2Var2.a(configuration);
        }
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        overridePendingTransition(0, 0);
        super.onCreate(bundle);
        int intExtra = getIntent().getIntExtra("placement", -1);
        Serializable serializableExtra = getIntent().getSerializableExtra("ad");
        if (intExtra >= 0 && (serializableExtra instanceof Ad)) {
            x8 r = ComponentLocator.a(getApplicationContext()).r();
            AdPreferences.Placement a = AdPreferences.Placement.a(intExtra);
            String adId = ((Ad) serializableExtra).getAdId();
            r.getClass();
            if (adId != null) {
                r.a.put(new x8.a(a, -1), adId);
            }
        }
        boolean booleanExtra = getIntent().getBooleanExtra("videoAd", false);
        requestWindowFeature(1);
        if (getIntent().getBooleanExtra("fullscreen", false) || booleanExtra) {
            getWindow().setFlags(1024, 1024);
        }
        this.d = getIntent().getBooleanExtra("activityShouldLockOrientation", true);
        if (bundle != null) {
            this.f = bundle.getInt("activityLockedOrientation", -1);
            this.d = bundle.getBoolean("activityShouldLockOrientation", true);
        }
        this.c = getIntent().getIntExtra("orientation", getResources().getConfiguration().orientation);
        this.b = getResources().getConfiguration().orientation != this.c;
        if (b()) {
            this.e = bundle;
            return;
        }
        a();
        p2 p2Var = this.a;
        if (p2Var != null) {
            p2Var.a(bundle);
        }
    }

    @Override // android.app.Activity
    public void onDestroy() {
        if (!b()) {
            p2 p2Var = this.a;
            if (p2Var != null) {
                p2Var.d();
                this.a = null;
            }
            Map<Activity, Integer> map = aa.a;
            aa.a((Activity) this, getResources().getConfiguration().orientation, false);
        }
        super.onDestroy();
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        p2 p2Var = this.a;
        if (p2Var == null || p2Var.a(i, keyEvent)) {
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.app.Activity
    public void onPause() {
        super.onPause();
        if (!b()) {
            p2 p2Var = this.a;
            if (p2Var != null) {
                p2Var.e();
            }
            g5.b(this);
        }
        overridePendingTransition(0, 0);
    }

    @Override // android.app.Activity
    public void onResume() {
        p2 p2Var;
        super.onResume();
        int i = this.f;
        if (i == -1) {
            this.f = aa.a(this, this.c, this.d);
        } else {
            int i2 = ya.a;
            try {
                setRequestedOrientation(i);
            } catch (Throwable unused) {
            }
        }
        if (b() || (p2Var = this.a) == null) {
            return;
        }
        p2Var.f();
    }

    @Override // android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (b()) {
            return;
        }
        p2 p2Var = this.a;
        if (p2Var != null) {
            p2Var.b(bundle);
        }
        bundle.putInt("activityLockedOrientation", this.f);
        bundle.putBoolean("activityShouldLockOrientation", this.d);
    }

    @Override // android.app.Activity
    public void onStop() {
        p2 p2Var;
        super.onStop();
        if (b() || (p2Var = this.a) == null) {
            return;
        }
        p2Var.g();
    }
}
