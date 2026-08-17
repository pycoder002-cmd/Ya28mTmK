package com.startapp.sdk.adsbase.remoteconfig;

import android.app.Activity;
import com.github.mikephil.charting.utils.Utils;
import com.startapp.aa;
import com.startapp.f;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class AnalyticsConfig implements Serializable {
    public static final String a = "https://infoevent.startappservice.com/tracking/infoEvent";
    private static final long serialVersionUID = -5497097103874215198L;

    @f(type = HashMap.class, value = AnalyticsCategoryConfig.class)
    private Map<String, AnalyticsCategoryConfig> categories;
    public boolean dns;
    public String hostPeriodic;
    public String hostSecured;
    private String noNetworkTimeout;
    private double oomSave;
    private double oomUpload;
    private String oomUrl;
    private int retryNum;
    private int retryTime;
    private boolean sendHopsOnFirstSucceededSmartRedirect;
    private boolean sendViewabilityInfo;
    private float succeededSmartRedirectInfoProbability;

    public AnalyticsConfig() {
        String str = a;
        this.hostSecured = str;
        this.hostPeriodic = str;
        this.dns = false;
        this.retryNum = 3;
        this.retryTime = 10;
        this.succeededSmartRedirectInfoProbability = 0.01f;
        this.sendHopsOnFirstSucceededSmartRedirect = false;
        this.oomSave = Utils.DOUBLE_EPSILON;
        this.oomUpload = Utils.DOUBLE_EPSILON;
        this.sendViewabilityInfo = false;
    }

    public Map<String, AnalyticsCategoryConfig> a() {
        return this.categories;
    }

    public String b() {
        return this.noNetworkTimeout;
    }

    public double c() {
        return this.oomSave;
    }

    public double d() {
        return this.oomUpload;
    }

    public String e() {
        return this.oomUrl;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || AnalyticsConfig.class != obj.getClass()) {
            return false;
        }
        AnalyticsConfig analyticsConfig = (AnalyticsConfig) obj;
        return this.dns == analyticsConfig.dns && this.retryNum == analyticsConfig.retryNum && this.retryTime == analyticsConfig.retryTime && Float.compare(this.succeededSmartRedirectInfoProbability, analyticsConfig.succeededSmartRedirectInfoProbability) == 0 && this.sendHopsOnFirstSucceededSmartRedirect == analyticsConfig.sendHopsOnFirstSucceededSmartRedirect && Double.compare(this.oomSave, analyticsConfig.oomSave) == 0 && Double.compare(this.oomUpload, analyticsConfig.oomUpload) == 0 && this.sendViewabilityInfo == analyticsConfig.sendViewabilityInfo && aa.a(this.hostSecured, analyticsConfig.hostSecured) && aa.a(this.hostPeriodic, analyticsConfig.hostPeriodic) && aa.a(this.noNetworkTimeout, analyticsConfig.noNetworkTimeout) && aa.a(this.categories, analyticsConfig.categories) && aa.a(this.oomUrl, analyticsConfig.oomUrl);
    }

    public int f() {
        return this.retryNum;
    }

    public long g() {
        return TimeUnit.SECONDS.toMillis(this.retryTime);
    }

    public float h() {
        return this.succeededSmartRedirectInfoProbability;
    }

    public int hashCode() {
        Object[] objArr = {this.hostSecured, this.hostPeriodic, Boolean.valueOf(this.dns), Integer.valueOf(this.retryNum), Integer.valueOf(this.retryTime), Float.valueOf(this.succeededSmartRedirectInfoProbability), Boolean.valueOf(this.sendHopsOnFirstSucceededSmartRedirect), this.noNetworkTimeout, this.categories, Double.valueOf(this.oomSave), Double.valueOf(this.oomUpload), this.oomUrl, Boolean.valueOf(this.sendViewabilityInfo)};
        Map<Activity, Integer> map = aa.a;
        return Arrays.deepHashCode(objArr);
    }

    public boolean i() {
        return this.sendHopsOnFirstSucceededSmartRedirect;
    }

    public boolean j() {
        return this.sendViewabilityInfo;
    }
}
