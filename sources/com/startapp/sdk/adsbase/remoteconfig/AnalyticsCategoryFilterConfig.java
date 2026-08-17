package com.startapp.sdk.adsbase.remoteconfig;

import android.app.Activity;
import com.startapp.aa;
import com.startapp.f;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class AnalyticsCategoryFilterConfig implements Serializable {
    private static final long serialVersionUID = -706642555040875333L;

    @f(type = ArrayList.class)
    private List<String> excludeAppActivity;

    @f(type = ArrayList.class)
    private List<String> excludeValues;

    @f(type = ArrayList.class)
    private List<String> fields;

    @f(type = ArrayList.class)
    private List<String> includeAppActivity;

    @f(type = ArrayList.class)
    private List<String> includeValues;
    private String interval;

    public List<String> a() {
        return this.excludeAppActivity;
    }

    public List<String> b() {
        return this.excludeValues;
    }

    public List<String> c() {
        return this.fields;
    }

    public List<String> d() {
        return this.includeAppActivity;
    }

    public List<String> e() {
        return this.includeValues;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || AnalyticsCategoryFilterConfig.class != obj.getClass()) {
            return false;
        }
        AnalyticsCategoryFilterConfig analyticsCategoryFilterConfig = (AnalyticsCategoryFilterConfig) obj;
        return aa.a(this.includeValues, analyticsCategoryFilterConfig.includeValues) && aa.a(this.excludeValues, analyticsCategoryFilterConfig.excludeValues) && aa.a(this.includeAppActivity, analyticsCategoryFilterConfig.includeAppActivity) && aa.a(this.excludeAppActivity, analyticsCategoryFilterConfig.excludeAppActivity) && aa.a(this.fields, analyticsCategoryFilterConfig.fields) && aa.a(this.interval, analyticsCategoryFilterConfig.interval);
    }

    public String f() {
        return this.interval;
    }

    public int hashCode() {
        Object[] objArr = {this.includeValues, this.excludeValues, this.includeAppActivity, this.excludeAppActivity, this.fields, this.interval};
        Map<Activity, Integer> map = aa.a;
        return Arrays.deepHashCode(objArr);
    }
}
