package com.startapp.sdk.adsbase.consent;

import android.app.Activity;
import com.startapp.aa;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Map;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public final class ConsentTypeInfoConfig implements Serializable {
    private static final long serialVersionUID = 1;
    private Integer falseClick;
    private Integer impression;
    private Integer trueClick;

    public Integer a() {
        return this.falseClick;
    }

    public Integer b() {
        return this.impression;
    }

    public Integer c() {
        return this.trueClick;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || ConsentTypeInfoConfig.class != obj.getClass()) {
            return false;
        }
        ConsentTypeInfoConfig consentTypeInfoConfig = (ConsentTypeInfoConfig) obj;
        return aa.a(this.impression, consentTypeInfoConfig.impression) && aa.a(this.trueClick, consentTypeInfoConfig.trueClick) && aa.a(this.falseClick, consentTypeInfoConfig.falseClick);
    }

    public int hashCode() {
        Object[] objArr = {this.impression, this.trueClick, this.falseClick};
        Map<Activity, Integer> map = aa.a;
        return Arrays.deepHashCode(objArr);
    }
}
