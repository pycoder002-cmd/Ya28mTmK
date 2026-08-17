package com.startapp.sdk.ads.banner;

import android.view.View;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public interface BannerListener {
    void onClick(View view);

    void onFailedToReceiveAd(View view);

    void onImpression(View view);

    void onReceiveAd(View view);
}
