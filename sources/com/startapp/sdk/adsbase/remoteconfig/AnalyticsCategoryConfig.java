package com.startapp.sdk.adsbase.remoteconfig;

import android.app.Activity;
import com.startapp.aa;
import com.startapp.f;
import com.startapp.z9;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class AnalyticsCategoryConfig implements Serializable {
    private static final long serialVersionUID = 5410570404581113345L;
    private Double enabled;

    @f(parser = FiltersParser.class, type = ArrayList.class, value = AnalyticsCategoryFilterConfig.class)
    private List<AnalyticsCategoryFilterConfig> filters;
    private String firstDelay;
    private Integer flags;
    private Integer priority;
    private Boolean sendViaDb;
    private String ttl;

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public static class FiltersParser extends z9<AnalyticsCategoryFilterConfig> {
        public FiltersParser() {
            super(AnalyticsCategoryFilterConfig.class);
        }
    }

    public Double a() {
        return this.enabled;
    }

    public List<AnalyticsCategoryFilterConfig> b() {
        return this.filters;
    }

    public String c() {
        return this.firstDelay;
    }

    public Integer d() {
        return this.flags;
    }

    public Integer e() {
        return this.priority;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || AnalyticsCategoryConfig.class != obj.getClass()) {
            return false;
        }
        AnalyticsCategoryConfig analyticsCategoryConfig = (AnalyticsCategoryConfig) obj;
        return aa.a(this.enabled, analyticsCategoryConfig.enabled) && aa.a(this.flags, analyticsCategoryConfig.flags) && aa.a(this.priority, analyticsCategoryConfig.priority) && aa.a(this.sendViaDb, analyticsCategoryConfig.sendViaDb) && aa.a(this.ttl, analyticsCategoryConfig.ttl) && aa.a(this.firstDelay, analyticsCategoryConfig.firstDelay) && aa.a(this.filters, analyticsCategoryConfig.filters);
    }

    public Boolean f() {
        return this.sendViaDb;
    }

    public String g() {
        return this.ttl;
    }

    public int hashCode() {
        Object[] objArr = {this.enabled, this.flags, this.priority, this.sendViaDb, this.ttl, this.firstDelay, this.filters};
        Map<Activity, Integer> map = aa.a;
        return Arrays.deepHashCode(objArr);
    }
}
