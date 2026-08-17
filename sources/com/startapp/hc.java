package com.startapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import com.startapp.sdk.adsbase.commontracking.TrackingParams;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class hc {
    public boolean a;
    public boolean b;
    public Runnable c;
    public Runnable d;
    public Runnable e;
    public Context f;
    public TrackingParams g;

    public hc(Context context, Runnable runnable, TrackingParams trackingParams) {
        this.a = false;
        this.b = true;
        this.c = null;
        this.d = null;
        this.e = null;
        this.c = runnable;
        this.f = context;
        this.g = trackingParams;
    }

    public hc(Context context, Runnable runnable, TrackingParams trackingParams, boolean z) {
        this(context, runnable, trackingParams);
        this.b = z;
    }

    public hc(Context context, Runnable runnable, Runnable runnable2, TrackingParams trackingParams) {
        this(context, runnable, trackingParams);
        this.d = runnable2;
    }

    @JavascriptInterface
    public void closeAd() {
        if (this.a) {
            return;
        }
        this.a = true;
        this.c.run();
    }

    @JavascriptInterface
    public void enableScroll(String str) {
        Runnable runnable = this.e;
        if (runnable != null) {
            runnable.run();
        }
    }

    @JavascriptInterface
    public void externalLinks(String str) {
        if (!this.b) {
            g5.b(this.f, str, (String) null);
        } else {
            Map<Activity, Integer> map = aa.a;
            g5.a(this.f, str, (String) null);
        }
    }

    @JavascriptInterface
    public void openApp(String str, String str2, String str3) {
        if (str != null && !TextUtils.isEmpty(str)) {
            g5.a(this.f, str, this.g);
        }
        Intent a = aa.a(this.f, str2);
        if (str3 != null) {
            try {
                JSONObject jSONObject = new JSONObject(str3);
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String valueOf = String.valueOf(keys.next());
                    a.putExtra(valueOf, String.valueOf(jSONObject.get(valueOf)));
                }
            } catch (JSONException unused) {
            }
        }
        try {
            this.f.startActivity(a);
        } catch (Throwable th) {
            p7.a(this.f, th);
        }
        Runnable runnable = this.d;
        if (runnable != null) {
            runnable.run();
        }
    }
}
