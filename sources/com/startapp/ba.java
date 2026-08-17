package com.startapp;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.startapp.aa;
import com.startapp.sdk.adsbase.AdsConstants;
import java.lang.ref.WeakReference;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class ba {
    public final Context a;
    public final ca b;
    public final Queue<WeakReference<WebView>> c = new LinkedList();

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class a extends WebViewClient {
        public final /* synthetic */ Handler a;
        public final /* synthetic */ AtomicBoolean b;
        public final /* synthetic */ WebView c;
        public final /* synthetic */ aa.a d;
        public final /* synthetic */ int e;

        /* compiled from: StartAppSDK */
        /* renamed from: com.startapp.ba$a$a, reason: collision with other inner class name */
        /* loaded from: classes3.dex */
        public class RunnableC0057a implements Runnable {
            public RunnableC0057a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                if (a.this.b.compareAndSet(false, true)) {
                    a aVar = a.this;
                    ba.this.a(aVar.c);
                    a.this.d.a();
                }
            }
        }

        /* compiled from: StartAppSDK */
        /* loaded from: classes3.dex */
        public class b implements Runnable {
            public final /* synthetic */ String a;

            public b(String str) {
                this.a = str;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (a.this.b.compareAndSet(false, true)) {
                    a aVar = a.this;
                    ba.this.a(aVar.c);
                    a.this.d.a(this.a);
                }
            }
        }

        public a(Handler handler, AtomicBoolean atomicBoolean, WebView webView, aa.a aVar, int i) {
            this.a = handler;
            this.b = atomicBoolean;
            this.c = webView;
            this.d = aVar;
            this.e = i;
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            this.a.removeCallbacksAndMessages(null);
            this.a.postDelayed(new RunnableC0057a(), this.e);
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(WebView webView, int i, String str, String str2) {
            super.onReceivedError(webView, i, str, str2);
            this.a.removeCallbacksAndMessages(null);
            this.a.post(new b(str));
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            if (webView == null || str == null || aa.b(webView.getContext(), str)) {
                return true;
            }
            return super.shouldOverrideUrlLoading(webView, str);
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class b implements Runnable {
        public final /* synthetic */ AtomicBoolean a;
        public final /* synthetic */ WebView b;
        public final /* synthetic */ aa.a c;

        public b(AtomicBoolean atomicBoolean, WebView webView, aa.a aVar) {
            this.a = atomicBoolean;
            this.b = webView;
            this.c = aVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.a.compareAndSet(false, true)) {
                ba.this.a(this.b);
                this.c.a();
            }
        }
    }

    public ba(Context context, ca caVar) {
        this.a = context;
        this.b = caVar;
    }

    public void a(WebView webView) {
        webView.stopLoading();
        webView.loadUrl("about:blank");
        if (this.c.size() < 3) {
            this.c.add(new WeakReference<>(webView));
        } else {
            webView.destroy();
        }
    }

    public void a(String str, aa.a aVar) {
        int i;
        if ("true".equals(aa.a(str, "@doNotRender@", "@doNotRender@"))) {
            aVar.a();
            return;
        }
        WebView webView = null;
        while (webView == null) {
            try {
                if (this.c.size() <= 0) {
                    break;
                }
                WeakReference<WebView> poll = this.c.poll();
                if (poll != null) {
                    webView = poll.get();
                }
            } catch (Throwable th) {
                p7.a(this.a, th);
                aVar.a("WebView instantiation Error");
                return;
            }
        }
        if (webView == null) {
            webView = this.b.a();
        }
        WebView webView2 = webView;
        AtomicBoolean atomicBoolean = new AtomicBoolean();
        Handler handler = new Handler(Looper.getMainLooper());
        if (AdsConstants.g.booleanValue()) {
            webView2.getSettings().setBlockNetworkImage(false);
            webView2.getSettings().setLoadsImagesAutomatically(true);
            webView2.getSettings().setJavaScriptEnabled(true);
            i = 25000;
        } else {
            i = 0;
        }
        webView2.setWebChromeClient(new WebChromeClient());
        webView2.setWebViewClient(new a(handler, atomicBoolean, webView2, aVar, i));
        aa.a(this.a, webView2, str);
        handler.postDelayed(new b(atomicBoolean, webView2, aVar), 25000L);
    }
}
