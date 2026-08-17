package com.startapp.sdk.ads.banner;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import com.startapp.sdk.ads.banner.bannerstandard.BannerStandard;
import com.startapp.sdk.adsbase.model.AdPreferences;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class Banner extends BannerStandard {
    public Banner(Activity activity) {
        super(activity);
    }

    public Banner(Activity activity, AttributeSet attributeSet) {
        super(activity, attributeSet);
    }

    public Banner(Activity activity, AttributeSet attributeSet, int i) {
        super(activity, attributeSet, i);
    }

    public Banner(Activity activity, BannerListener bannerListener) {
        super(activity, bannerListener);
    }

    public Banner(Activity activity, AdPreferences adPreferences) {
        super(activity, adPreferences);
    }

    public Banner(Activity activity, AdPreferences adPreferences, BannerListener bannerListener) {
        super(activity, adPreferences, bannerListener);
    }

    @Deprecated
    public Banner(Context context) {
        super(context);
    }

    @Deprecated
    public Banner(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Deprecated
    public Banner(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Deprecated
    public Banner(Context context, BannerListener bannerListener) {
        super(context, bannerListener);
    }

    @Deprecated
    public Banner(Context context, AdPreferences adPreferences) {
        super(context, adPreferences);
    }

    @Deprecated
    public Banner(Context context, AdPreferences adPreferences, BannerListener bannerListener) {
        super(context, adPreferences, bannerListener);
    }
}
