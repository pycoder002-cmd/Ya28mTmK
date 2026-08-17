package com.startapp;

import android.graphics.Bitmap;
import com.startapp.b9;
import com.startapp.sdk.adsbase.adinformation.ImageResourceConfig;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class a6 implements b9.b {
    public final /* synthetic */ ImageResourceConfig a;

    public a6(ImageResourceConfig imageResourceConfig) {
        this.a = imageResourceConfig;
    }

    @Override // com.startapp.b9.b
    public void a(Bitmap bitmap, int i) {
        ImageResourceConfig imageResourceConfig = this.a;
        imageResourceConfig.a = bitmap;
        if (bitmap != null) {
            imageResourceConfig.c = bitmap;
        }
    }
}
