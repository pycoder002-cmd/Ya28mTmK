package com.startapp;

import android.view.View;
import com.startapp.sdk.ads.nativead.NativeAdDetails;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class l3 implements View.OnAttachStateChangeListener {
    public final /* synthetic */ NativeAdDetails a;

    public l3(NativeAdDetails nativeAdDetails) {
        this.a = nativeAdDetails;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewAttachedToWindow(View view) {
        this.a.b();
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewDetachedFromWindow(View view) {
        NativeAdDetails nativeAdDetails = this.a;
        da daVar = nativeAdDetails.i;
        if (daVar != null) {
            daVar.a();
            nativeAdDetails.i = null;
        }
        view.removeOnAttachStateChangeListener(this.a.k);
    }
}
