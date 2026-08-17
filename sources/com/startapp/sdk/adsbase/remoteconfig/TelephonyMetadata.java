package com.startapp.sdk.adsbase.remoteconfig;

import android.app.Activity;
import com.github.mikephil.charting.utils.Utils;
import com.startapp.aa;
import com.startapp.f;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class TelephonyMetadata implements Serializable {
    private static final long serialVersionUID = -644112324647697192L;

    @f(type = HashMap.class, value = TelephonyDataConfig.class)
    private Map<String, TelephonyDataConfig> data;
    private boolean enabled = true;
    private int ief = 0;
    private double iep = Utils.DOUBLE_EPSILON;

    public int a() {
        return this.ief;
    }

    public TelephonyDataConfig a(String str) {
        Map<String, TelephonyDataConfig> map = this.data;
        TelephonyDataConfig telephonyDataConfig = map != null ? map.get(str) : null;
        return telephonyDataConfig != null ? telephonyDataConfig : TelephonyDataConfig.a;
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
        if (obj == null || TelephonyMetadata.class != obj.getClass()) {
            return false;
        }
        TelephonyMetadata telephonyMetadata = (TelephonyMetadata) obj;
        return this.enabled == telephonyMetadata.enabled && this.ief == telephonyMetadata.ief && Double.compare(this.iep, telephonyMetadata.iep) == 0 && aa.a(this.data, telephonyMetadata.data);
    }

    public int hashCode() {
        Object[] objArr = {Boolean.valueOf(this.enabled), Integer.valueOf(this.ief), Double.valueOf(this.iep), this.data};
        Map<Activity, Integer> map = aa.a;
        return Arrays.deepHashCode(objArr);
    }
}
