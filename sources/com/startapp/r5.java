package com.startapp;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.startapp.sdk.adsbase.commontracking.TrackingParams;
import com.startapp.sdk.adsbase.remoteconfig.MetaData;
import java.lang.ref.WeakReference;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONObject;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class r5 {
    public static final boolean a = MetaData.h.S();
    public long c;
    public Context d;
    public long f;
    public boolean g;
    public boolean h;
    public String[] i;
    public TrackingParams j;
    public Handler b = new Handler(Looper.getMainLooper());
    public long e = -1;
    public AtomicBoolean k = new AtomicBoolean(false);
    public WeakReference<a> l = new WeakReference<>(null);

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public interface a {
        void onSent();
    }

    public r5(Context context, String[] strArr, TrackingParams trackingParams, long j) {
        this.d = y8.b(context);
        this.i = strArr;
        this.j = trackingParams;
        this.c = j;
    }

    public void a() {
        if (this.g && this.h) {
            this.b.removeCallbacksAndMessages(null);
            long currentTimeMillis = System.currentTimeMillis();
            this.e = currentTimeMillis;
            this.c -= currentTimeMillis - this.f;
            this.h = false;
        }
    }

    public void a(String str, JSONObject jSONObject) {
        b(str, jSONObject);
        this.g = false;
        this.b.removeCallbacksAndMessages(null);
        this.h = false;
        this.e = -1L;
        this.f = 0L;
    }

    public void b() {
        if (this.k.get()) {
            return;
        }
        if (!a) {
            b(null, null);
            return;
        }
        long j = this.c;
        if (this.h) {
            return;
        }
        this.h = true;
        if (!this.g) {
            this.g = true;
        }
        this.f = System.currentTimeMillis();
        this.b.postDelayed(new q5(this), j);
    }

    public void b(String str, JSONObject jSONObject) {
        if (this.k.compareAndSet(false, true)) {
            if (str != null) {
                g5.a(this.d, this.i, this.j.a(), 0, str, jSONObject);
                return;
            }
            Context context = this.d;
            String[] strArr = this.i;
            TrackingParams trackingParams = this.j;
            if (strArr != null) {
                for (String str2 : strArr) {
                    if (str2 != null && !str2.equalsIgnoreCase("")) {
                        aa.a(context, false, "Sending impression", true);
                        g5.b(context, str2, trackingParams);
                    }
                }
            }
            a aVar = this.l.get();
            if (aVar != null) {
                aVar.onSent();
            }
        }
    }
}
