package com.startapp;

import android.view.View;
import com.startapp.sdk.ads.video.VideoMode;
import com.startapp.ya;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public final class xa implements View.OnLayoutChangeListener {
    public final /* synthetic */ ya.b a;

    public xa(ya.b bVar) {
        this.a = bVar;
    }

    @Override // android.view.View.OnLayoutChangeListener
    public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        v4 v4Var = (v4) this.a;
        VideoMode videoMode = v4Var.a;
        videoMode.W = true;
        if (videoMode.V && videoMode.B()) {
            v4Var.a.y();
        }
    }
}
