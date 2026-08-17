package com.startapp;

import android.webkit.WebView;
import com.iab.omid.library.startapp.adsession.AdEvents;
import com.iab.omid.library.startapp.adsession.AdSession;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class ad {
    public final AdSession a;
    public final AdEvents b;
    public final AtomicBoolean c = new AtomicBoolean();

    public ad(WebView webView) {
        AdSession b = d.b(webView);
        this.a = b;
        this.b = d.a(webView.getContext(), b);
    }
}
