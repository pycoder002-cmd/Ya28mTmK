package com.startapp;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.startapp.sdk.adsbase.Ad;
import com.startapp.sdk.adsbase.adlisteners.AdEventListener;
import com.startapp.sdk.adsbase.model.AdPreferences;
import com.startapp.sdk.adsbase.model.GetAdRequest;
import com.startapp.sdk.components.ComponentLocator;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public abstract class k5 {
    public final Context a;
    public final Ad b;
    public final AdPreferences c;
    public final AdEventListener d;
    public AdPreferences.Placement e;
    public String f = null;

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class a implements Runnable {

        /* compiled from: StartAppSDK */
        /* renamed from: com.startapp.k5$a$a, reason: collision with other inner class name */
        /* loaded from: classes3.dex */
        public class RunnableC0058a implements Runnable {
            public final /* synthetic */ Boolean a;

            public RunnableC0058a(Boolean bool) {
                this.a = bool;
            }

            @Override // java.lang.Runnable
            public void run() {
                k5.this.a(this.a);
            }
        }

        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            new Handler(Looper.getMainLooper()).post(new RunnableC0058a(k5.this.a()));
        }
    }

    public k5(Context context, Ad ad, AdPreferences adPreferences, AdEventListener adEventListener, AdPreferences.Placement placement) {
        this.a = context;
        this.b = ad;
        this.c = adPreferences;
        this.d = adEventListener;
        this.e = placement;
    }

    /* JADX WARN: Code restructure failed: missing block: B:60:0x0067, code lost:
    
        if (r1.equals(r6.second) != false) goto L37;
     */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0042  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x004f A[Catch: all -> 0x007a, TryCatch #3 {all -> 0x007a, blocks: (B:44:0x0028, B:46:0x002e, B:50:0x0037, B:53:0x004a, B:57:0x004f, B:59:0x0061, B:61:0x0043, B:65:0x003c, B:66:0x006b, B:68:0x006f, B:70:0x0075), top: B:43:0x0028, outer: #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0043 A[Catch: all -> 0x007a, TryCatch #3 {all -> 0x007a, blocks: (B:44:0x0028, B:46:0x002e, B:50:0x0037, B:53:0x004a, B:57:0x004f, B:59:0x0061, B:61:0x0043, B:65:0x003c, B:66:0x006b, B:68:0x006f, B:70:0x0075), top: B:43:0x0028, outer: #2 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.startapp.sdk.adsbase.model.GetAdRequest a(com.startapp.sdk.adsbase.model.GetAdRequest r11) {
        /*
            Method dump skipped, instructions count: 316
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.startapp.k5.a(com.startapp.sdk.adsbase.model.GetAdRequest):com.startapp.sdk.adsbase.model.GetAdRequest");
    }

    public Boolean a() {
        try {
            return Boolean.valueOf(a(d()));
        } catch (Throwable th) {
            p7.a(this.a, th);
            return Boolean.FALSE;
        }
    }

    public void a(Boolean bool) {
        b(bool);
        if (bool.booleanValue()) {
            return;
        }
        this.b.setErrorMessage(this.f);
        d.a(this.a, this.d, this.b);
    }

    public abstract boolean a(Object obj);

    public void b() {
        ComponentLocator.a(this.a).o().execute(new a());
    }

    public void b(Boolean bool) {
        this.b.setState(bool.booleanValue() ? Ad.AdState.READY : Ad.AdState.UN_INITIALIZED);
    }

    public GetAdRequest c() {
        GetAdRequest a2 = a(new GetAdRequest());
        if (a2 != null) {
            a2.f(this.a);
        }
        return a2;
    }

    public abstract Object d();
}
