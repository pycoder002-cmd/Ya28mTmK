package com.startapp;

import android.content.Context;
import android.webkit.WebView;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class ca {
    public final Context a;
    public final m7 b;
    public WebView c;
    public final Runnable d;

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ca caVar = ca.this;
            if (caVar.c == null) {
                try {
                    caVar.c = new WebView(caVar.a);
                } catch (Throwable unused) {
                }
            }
        }
    }

    public ca(Context context, m7 m7Var) {
        a aVar = new a();
        this.d = aVar;
        this.a = context;
        this.b = m7Var;
        m7Var.a(aVar);
    }

    public WebView a() {
        WebView webView = this.c;
        if (webView == null) {
            return new WebView(this.a);
        }
        this.c = null;
        m7 m7Var = this.b;
        Runnable runnable = this.d;
        synchronized (m7Var.h) {
            m7Var.h.add(runnable);
        }
        return webView;
    }
}
