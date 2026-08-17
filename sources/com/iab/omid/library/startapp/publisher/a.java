package com.iab.omid.library.startapp.publisher;

import android.webkit.WebView;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class a extends AdSessionStatePublisher {
    public a(WebView webView) {
        if (webView != null && !webView.getSettings().getJavaScriptEnabled()) {
            webView.getSettings().setJavaScriptEnabled(true);
        }
        a(webView);
    }
}
