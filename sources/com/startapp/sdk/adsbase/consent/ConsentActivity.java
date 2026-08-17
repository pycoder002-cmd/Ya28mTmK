package com.startapp.sdk.adsbase.consent;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import com.startapp.aa;
import com.startapp.k7;
import com.startapp.p7;
import com.startapp.q7;
import com.startapp.sdk.adsbase.remoteconfig.MetaData;
import com.startapp.sdk.components.ComponentLocator;
import com.startapp.ya;
import java.net.URI;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class ConsentActivity extends Activity {
    public WebView a;
    public String b;
    public boolean c;

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class a extends WebViewClient {
        public a() {
        }

        /* JADX WARN: Can't wrap try/catch for region: R(9:10|(2:11|12)|(7:14|15|16|17|(1:19)|21|22)|27|16|17|(0)|21|22) */
        /* JADX WARN: Code restructure failed: missing block: B:24:0x005e, code lost:
        
            r12 = move-exception;
         */
        /* JADX WARN: Code restructure failed: missing block: B:25:0x005f, code lost:
        
            com.startapp.p7.a(r11.a, r12);
         */
        /* JADX WARN: Removed duplicated region for block: B:19:0x0055 A[Catch: all -> 0x005e, TRY_LEAVE, TryCatch #2 {all -> 0x005e, blocks: (B:17:0x004f, B:19:0x0055), top: B:16:0x004f }] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final boolean a(android.net.Uri r12) {
            /*
                r11 = this;
                java.lang.String r0 = r12.getScheme()
                java.lang.String r1 = r12.getHost()
                com.startapp.sdk.adsbase.remoteconfig.MetaData r2 = com.startapp.sdk.adsbase.remoteconfig.MetaData.h
                com.startapp.sdk.adsbase.consent.ConsentConfig r2 = r2.j()
                r3 = 0
                if (r0 == 0) goto L90
                java.lang.String r4 = "startappad"
                boolean r0 = r0.equalsIgnoreCase(r4)
                if (r0 == 0) goto L90
                boolean r0 = android.text.TextUtils.isEmpty(r1)
                if (r0 != 0) goto L90
                if (r2 != 0) goto L22
                goto L90
            L22:
                java.lang.String r0 = "setconsent"
                boolean r0 = r1.equalsIgnoreCase(r0)
                r4 = 1
                if (r0 == 0) goto L7d
                java.lang.String r0 = "status"
                java.lang.String r0 = r12.getQueryParameter(r0)
                java.lang.String r1 = "apc"
                java.lang.String r12 = r12.getQueryParameter(r1)
                r1 = 0
                boolean r3 = android.text.TextUtils.isEmpty(r0)     // Catch: java.lang.Throwable -> L48 java.lang.NumberFormatException -> L4e
                if (r3 != 0) goto L4e
                int r0 = java.lang.Integer.parseInt(r0)     // Catch: java.lang.Throwable -> L48 java.lang.NumberFormatException -> L4e
                java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch: java.lang.Throwable -> L48 java.lang.NumberFormatException -> L4e
                r6 = r0
                goto L4f
            L48:
                r0 = move-exception
                com.startapp.sdk.adsbase.consent.ConsentActivity r3 = com.startapp.sdk.adsbase.consent.ConsentActivity.this
                com.startapp.p7.a(r3, r0)
            L4e:
                r6 = r1
            L4f:
                boolean r0 = android.text.TextUtils.isEmpty(r12)     // Catch: java.lang.Throwable -> L5e
                if (r0 != 0) goto L64
                boolean r12 = java.lang.Boolean.parseBoolean(r12)     // Catch: java.lang.Throwable -> L5e
                java.lang.Boolean r1 = java.lang.Boolean.valueOf(r12)     // Catch: java.lang.Throwable -> L5e
                goto L64
            L5e:
                r12 = move-exception
                com.startapp.sdk.adsbase.consent.ConsentActivity r0 = com.startapp.sdk.adsbase.consent.ConsentActivity.this
                com.startapp.p7.a(r0, r12)
            L64:
                r8 = r1
                com.startapp.sdk.adsbase.consent.ConsentActivity r12 = com.startapp.sdk.adsbase.consent.ConsentActivity.this
                com.startapp.sdk.components.ComponentLocator r12 = com.startapp.sdk.components.ComponentLocator.a(r12)
                com.startapp.k7 r5 = r12.f()
                long r0 = r2.i()
                java.lang.Long r7 = java.lang.Long.valueOf(r0)
                r9 = 1
                r10 = 1
                r5.a(r6, r7, r8, r9, r10)
                return r4
            L7d:
                java.lang.String r12 = "close"
                boolean r12 = r1.equalsIgnoreCase(r12)
                if (r12 == 0) goto L90
                com.startapp.sdk.adsbase.consent.ConsentActivity r12 = com.startapp.sdk.adsbase.consent.ConsentActivity.this
                com.startapp.sdk.adsbase.consent.ConsentActivity.a(r12, r4)
                com.startapp.sdk.adsbase.consent.ConsentActivity r12 = com.startapp.sdk.adsbase.consent.ConsentActivity.this
                r12.finish()
                return r4
            L90:
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.startapp.sdk.adsbase.consent.ConsentActivity.a.a(android.net.Uri):boolean");
        }

        /* JADX WARN: Removed duplicated region for block: B:19:0x0089  */
        /* JADX WARN: Removed duplicated region for block: B:22:0x009c  */
        /* JADX WARN: Removed duplicated region for block: B:27:0x00b9  */
        /* JADX WARN: Removed duplicated region for block: B:32:0x00d6  */
        /* JADX WARN: Removed duplicated region for block: B:37:0x0103  */
        /* JADX WARN: Removed duplicated region for block: B:40:0x0116  */
        /* JADX WARN: Removed duplicated region for block: B:43:0x012d  */
        /* JADX WARN: Removed duplicated region for block: B:46:0x0144  */
        /* JADX WARN: Removed duplicated region for block: B:49:0x0165  */
        /* JADX WARN: Removed duplicated region for block: B:52:0x017d  */
        /* JADX WARN: Removed duplicated region for block: B:55:0x0195  */
        /* JADX WARN: Removed duplicated region for block: B:58:0x01b2  */
        /* JADX WARN: Removed duplicated region for block: B:61:0x01c9  */
        @Override // android.webkit.WebViewClient
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void onPageFinished(android.webkit.WebView r9, java.lang.String r10) {
            /*
                Method dump skipped, instructions count: 485
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.startapp.sdk.adsbase.consent.ConsentActivity.a.onPageFinished(android.webkit.WebView, java.lang.String):void");
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
            return a(webResourceRequest.getUrl());
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            return a(Uri.parse(str));
        }
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        WebView webView = this.a;
        if (webView == null) {
            this.c = true;
            super.onBackPressed();
            return;
        }
        String url = webView.getUrl();
        String str = this.b;
        if (str != null && url != null && url.contains(str)) {
            this.a.loadUrl("javascript:startappBackPressed();");
        } else if (this.a.canGoBack()) {
            this.a.goBack();
        } else {
            this.c = true;
            super.onBackPressed();
        }
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        RelativeLayout relativeLayout = new RelativeLayout(this);
        ViewGroup.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        String dataString = getIntent().getDataString();
        if (!TextUtils.isEmpty(dataString)) {
            try {
                URI uri = new URI(dataString);
                this.b = new URI(uri.getScheme(), uri.getAuthority(), uri.getPath(), null, null).toString();
                WebView a2 = ComponentLocator.a(this).t().a();
                this.a = a2;
                a2.setWebViewClient(new a());
                this.a.getSettings().setJavaScriptEnabled(true);
                this.a.setHorizontalScrollBarEnabled(false);
                this.a.setVerticalScrollBarEnabled(false);
                if (Build.VERSION.SDK_INT >= 15) {
                    this.a.getSettings().setTextZoom(100);
                } else {
                    this.a.getSettings().setTextSize(WebSettings.TextSize.NORMAL);
                }
                this.a.loadUrl(dataString);
                this.a.setBackgroundColor(0);
                ya.a(this.a, (Paint) null);
                RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -1);
                layoutParams2.addRule(13);
                relativeLayout.addView(this.a, layoutParams2);
            } catch (Throwable th) {
                p7.a(this, th);
            }
        }
        setContentView(relativeLayout, layoutParams);
    }

    @Override // android.app.Activity
    public void onStop() {
        super.onStop();
        ConsentConfig j = MetaData.h.j();
        if (!this.c && j != null && j.j() && aa.g(this) && aa.e(this)) {
            p7 p7Var = new p7(q7.b);
            p7Var.d = "ConsentActivityHasBeenCovered";
            p7Var.a(this);
            finish();
            try {
                startActivity(getIntent());
            } catch (Throwable th) {
                p7.a(this, th);
            }
        }
        k7 f = ComponentLocator.a(this).f();
        f.d = false;
        Intent intent = f.c;
        if (intent != null) {
            try {
                f.a.startActivity(intent);
            } catch (Throwable th2) {
                p7.a(f.a, th2);
            }
        }
    }
}
