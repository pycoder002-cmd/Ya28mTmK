package com.startapp;

import android.content.IntentFilter;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;
import com.startapp.sdk.ads.splash.SplashConfig;
import com.startapp.sdk.ads.splash.SplashEventHandler;
import com.startapp.sdk.ads.splash.SplashScreen;
import com.startapp.sdk.adsbase.model.AdPreferences;
import java.io.Serializable;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class t3 extends p2 {
    public SplashScreen w;
    public SplashConfig v = null;
    public boolean x = false;
    public boolean y = false;

    @Override // com.startapp.p2
    public void a(Bundle bundle) {
        this.v = (SplashConfig) this.a.getSerializableExtra("SplashConfig");
    }

    @Override // com.startapp.p2
    public boolean a(int i, KeyEvent keyEvent) {
        if (this.x) {
            if (i == 25) {
                if (!this.y) {
                    this.y = true;
                    SplashScreen splashScreen = this.w;
                    splashScreen.g = true;
                    splashScreen.b.g = true;
                    Toast.makeText(this.b, "Test Mode", 0).show();
                    return true;
                }
            } else if (i == 24 && this.y) {
                this.b.finish();
                return true;
            }
        }
        return i == 4;
    }

    @Override // com.startapp.p2
    public void d() {
    }

    @Override // com.startapp.p2
    public void e() {
        SplashScreen splashScreen = this.w;
        if (splashScreen != null) {
            splashScreen.getClass();
        }
    }

    @Override // com.startapp.p2
    public void f() {
        if (this.v != null) {
            Serializable serializableExtra = this.a.getSerializableExtra("AdPreference");
            AdPreferences adPreferences = serializableExtra != null ? (AdPreferences) serializableExtra : new AdPreferences();
            this.x = this.a.getBooleanExtra("testMode", false);
            SplashScreen splashScreen = new SplashScreen(this.b, this.v, adPreferences);
            this.w = splashScreen;
            SplashEventHandler splashEventHandler = splashScreen.b;
            la.a(splashEventHandler.a).a(splashEventHandler.k, new IntentFilter("com.startapp.android.adInfoWasClickedBroadcastListener"));
            if (splashScreen.c()) {
                splashScreen.f.postDelayed(splashScreen.j, 100L);
            } else {
                splashScreen.f.post(splashScreen.j);
            }
        }
    }

    @Override // com.startapp.p2
    public void g() {
        SplashEventHandler.SplashState splashState;
        SplashScreen splashScreen = this.w;
        if (splashScreen != null) {
            splashScreen.f.removeCallbacks(splashScreen.j);
            SplashEventHandler splashEventHandler = splashScreen.b;
            SplashEventHandler.SplashState splashState2 = splashEventHandler.i;
            if (splashState2 == SplashEventHandler.SplashState.DISPLAYED || splashState2 == (splashState = SplashEventHandler.SplashState.DO_NOT_DISPLAY)) {
                return;
            }
            splashEventHandler.i = splashState;
            if (splashEventHandler.d) {
                splashEventHandler.b();
            }
        }
    }

    @Override // com.startapp.p2
    public void h() {
    }
}
