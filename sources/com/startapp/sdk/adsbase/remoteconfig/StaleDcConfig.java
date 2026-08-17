package com.startapp.sdk.adsbase.remoteconfig;

import android.app.Activity;
import com.startapp.aa;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Map;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class StaleDcConfig implements Serializable {
    private int ief = 0;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && StaleDcConfig.class == obj.getClass() && this.ief == ((StaleDcConfig) obj).ief;
    }

    public int hashCode() {
        Object[] objArr = {Integer.valueOf(this.ief)};
        Map<Activity, Integer> map = aa.a;
        return Arrays.deepHashCode(objArr);
    }
}
