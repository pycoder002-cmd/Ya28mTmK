package com.startapp;

import android.content.DialogInterface;
import android.webkit.WebView;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public final class h5 implements DialogInterface.OnCancelListener {
    public final /* synthetic */ WebView a;

    public h5(WebView webView) {
        this.a = webView;
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialogInterface) {
        this.a.stopLoading();
    }
}
