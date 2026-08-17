package com.startapp.sdk.adsbase.remoteconfig;

import android.app.Activity;
import com.startapp.aa;
import com.startapp.f;
import com.startapp.q7;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class TelephonyDataConfig implements Serializable {
    public static final TelephonyDataConfig a = new TelephonyDataConfig();
    private static final long serialVersionUID = -7175662234963204913L;
    private String param;
    private boolean enabled = true;

    @f(type = ArrayList.class)
    private List<String> categories = Collections.singletonList(q7.i.a());

    public List<String> a() {
        return this.categories;
    }

    public String b() {
        return this.param;
    }

    public boolean c() {
        return this.enabled;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || TelephonyDataConfig.class != obj.getClass()) {
            return false;
        }
        TelephonyDataConfig telephonyDataConfig = (TelephonyDataConfig) obj;
        return this.enabled == telephonyDataConfig.enabled && aa.a(this.categories, telephonyDataConfig.categories) && aa.a(this.param, telephonyDataConfig.param);
    }

    public int hashCode() {
        Object[] objArr = {Boolean.valueOf(this.enabled), this.categories, this.param};
        Map<Activity, Integer> map = aa.a;
        return Arrays.deepHashCode(objArr);
    }
}
