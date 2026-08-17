package com.startapp.sdk.adsbase.remoteconfig;

import android.app.Activity;
import com.github.mikephil.charting.utils.Utils;
import com.startapp.aa;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Map;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class AdvertisingIdResolverMetadata implements Serializable {
    private static final long serialVersionUID = -1692502027621350736L;
    private boolean enabled = true;
    private double iep = Utils.DOUBLE_EPSILON;
    private int ief = 0;

    public int a() {
        return this.ief;
    }

    public double b() {
        return this.iep;
    }

    public boolean c() {
        return this.enabled;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || AdvertisingIdResolverMetadata.class != obj.getClass()) {
            return false;
        }
        AdvertisingIdResolverMetadata advertisingIdResolverMetadata = (AdvertisingIdResolverMetadata) obj;
        return this.enabled == advertisingIdResolverMetadata.enabled && Double.compare(this.iep, advertisingIdResolverMetadata.iep) == 0 && this.ief == advertisingIdResolverMetadata.ief;
    }

    public int hashCode() {
        Object[] objArr = {Boolean.valueOf(this.enabled), Double.valueOf(this.iep), Integer.valueOf(this.ief)};
        Map<Activity, Integer> map = aa.a;
        return Arrays.deepHashCode(objArr);
    }
}
