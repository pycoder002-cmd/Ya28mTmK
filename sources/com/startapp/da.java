package com.startapp;

import android.os.Handler;
import android.os.Looper;
import android.view.View;
import com.startapp.sdk.ads.nativead.NativeAdDetails;
import com.startapp.sdk.ads.nativead.NativeAdDisplayListener;
import com.startapp.sdk.adsbase.adlisteners.NotDisplayedReason;
import java.lang.ref.WeakReference;
import java.util.concurrent.atomic.AtomicReference;
import org.json.JSONObject;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class da implements Runnable {
    public NotDisplayedReason a;
    public JSONObject b;
    public a c;
    public final WeakReference<View> e;
    public final r5 f;
    public final int g;
    public final Handler d = new Handler(Looper.getMainLooper());
    public boolean h = true;

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public interface a {
    }

    public da(View view, r5 r5Var, int i) {
        this.e = new WeakReference<>(view);
        this.f = r5Var;
        this.g = i;
    }

    public da(WeakReference<View> weakReference, r5 r5Var, int i) {
        this.e = weakReference;
        this.f = r5Var;
        this.g = i;
    }

    public void a() {
        NotDisplayedReason notDisplayedReason;
        try {
            r5 r5Var = this.f;
            if (r5Var != null && (notDisplayedReason = this.a) != null) {
                r5Var.a(notDisplayedReason.toString(), this.b);
            }
            this.d.removeCallbacksAndMessages(null);
        } catch (Throwable unused) {
        }
    }

    public boolean b() {
        r5 r5Var = this.f;
        return (r5Var == null || r5Var.k.get() || this.e.get() == null) ? false : true;
    }

    @Override // java.lang.Runnable
    public void run() {
        NativeAdDetails.f fVar;
        NativeAdDetails nativeAdDetails;
        NativeAdDisplayListener nativeAdDisplayListener;
        NotDisplayedReason notDisplayedReason;
        try {
            if (!b()) {
                a();
                return;
            }
            AtomicReference atomicReference = new AtomicReference();
            NotDisplayedReason a2 = d.a(this.e.get(), this.g, (AtomicReference<JSONObject>) atomicReference);
            if (a2 != null && ((notDisplayedReason = this.a) == null || notDisplayedReason.a() <= a2.a())) {
                this.a = a2;
                this.b = (JSONObject) atomicReference.get();
            }
            boolean z = a2 == null;
            if (z && this.h) {
                this.h = false;
                this.f.b();
                a aVar = this.c;
                if (aVar != null) {
                    ((NativeAdDetails.f) aVar).getClass();
                }
            } else if (!z && !this.h) {
                this.h = true;
                this.f.a();
                a aVar2 = this.c;
                if (aVar2 != null && (nativeAdDisplayListener = (nativeAdDetails = NativeAdDetails.this).l) != null && !nativeAdDetails.f) {
                    nativeAdDisplayListener.adHidden(nativeAdDetails);
                    NativeAdDetails.this.f = true;
                }
            }
            this.d.postDelayed(this, 100L);
        } catch (Throwable unused) {
            this.a = NotDisplayedReason.INTERNAL_ERROR;
            a();
        }
    }
}
