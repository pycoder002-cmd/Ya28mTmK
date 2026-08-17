package com.startapp;

import android.os.Handler;
import android.webkit.WebView;
import com.startapp.q2;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class v2 extends q2 {

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class a implements Runnable {
        public final /* synthetic */ WebView a;

        public a(v2 v2Var, WebView webView) {
            this.a = webView;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                this.a.setBackgroundColor(0);
            } catch (Exception unused) {
            }
        }
    }

    @Override // com.startapp.q2
    public void a(WebView webView) {
        new Handler().postDelayed(new a(this, webView), 1000L);
    }

    @Override // com.startapp.q2
    public void b(WebView webView) {
        this.D = false;
        webView.setOnTouchListener(new q2.d());
        if (this.h.equals("interstitial")) {
            webView.setBackgroundColor(0);
        }
    }
}
