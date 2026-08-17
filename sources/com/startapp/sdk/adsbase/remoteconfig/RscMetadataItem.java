package com.startapp.sdk.adsbase.remoteconfig;

import android.app.Activity;
import com.startapp.aa;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Map;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class RscMetadataItem implements Serializable {
    private static final long serialVersionUID = 1691586261519008915L;
    private String config;
    private Integer ief;
    private Integer limit;
    private int noCache;
    private Integer output;
    private Integer ppid;
    private int[] sortBy;
    private int triggers;
    private Integer ttl;

    public String a() {
        return this.config;
    }

    public Integer b() {
        return this.ief;
    }

    public Integer c() {
        return this.limit;
    }

    public int d() {
        return this.noCache;
    }

    public Integer e() {
        return this.output;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || RscMetadataItem.class != obj.getClass()) {
            return false;
        }
        RscMetadataItem rscMetadataItem = (RscMetadataItem) obj;
        return this.triggers == rscMetadataItem.triggers && this.noCache == rscMetadataItem.noCache && aa.a(this.config, rscMetadataItem.config) && aa.a(this.ttl, rscMetadataItem.ttl) && Arrays.equals(this.sortBy, rscMetadataItem.sortBy) && aa.a(this.limit, rscMetadataItem.limit) && aa.a(this.ppid, rscMetadataItem.ppid) && aa.a(this.output, rscMetadataItem.output) && aa.a(this.ief, rscMetadataItem.ief);
    }

    public Integer f() {
        return this.ppid;
    }

    public int[] g() {
        return this.sortBy;
    }

    public int h() {
        return this.triggers;
    }

    public int hashCode() {
        Object[] objArr = {this.config, Integer.valueOf(this.triggers), Integer.valueOf(this.noCache), this.ttl, this.sortBy, this.limit, this.ppid, this.output, this.ief};
        Map<Activity, Integer> map = aa.a;
        return Arrays.deepHashCode(objArr);
    }

    public Integer i() {
        return this.ttl;
    }
}
