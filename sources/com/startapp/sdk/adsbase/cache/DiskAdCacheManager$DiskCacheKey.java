package com.startapp.sdk.adsbase.cache;

import com.startapp.sdk.adsbase.model.AdPreferences;
import java.io.Serializable;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class DiskAdCacheManager$DiskCacheKey implements Serializable {
    private static final long serialVersionUID = 1;
    public AdPreferences adPreferences;
    private int numberOfLoadedAd;
    public AdPreferences.Placement placement;

    public DiskAdCacheManager$DiskCacheKey(AdPreferences.Placement placement, AdPreferences adPreferences) {
        this.placement = placement;
        this.adPreferences = adPreferences;
    }

    public int a() {
        return this.numberOfLoadedAd;
    }

    public void a(int i) {
        this.numberOfLoadedAd = i;
    }
}
