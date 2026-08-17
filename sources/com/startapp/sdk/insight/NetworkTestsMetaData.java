package com.startapp.sdk.insight;

import com.github.mikephil.charting.utils.Utils;
import com.google.android.gms.search.SearchAuth;
import java.io.Serializable;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class NetworkTestsMetaData implements Serializable {
    private static final long serialVersionUID = 1;
    private boolean enabledCachedProcess;
    private String hostCt;
    private String hostLt;
    private String hostNir;
    private int numberOfRecordsMin = 5000;
    private int numberOfRecordsMax = SearchAuth.StatusCodes.AUTH_DISABLED;
    private boolean enabled = false;
    private String projectId = "20050";
    private String connectivityTestHostname = "d2to8y50b3n6dq.cloudfront.net";
    private String connectivityTestFilename = "/favicon.ico";
    private boolean connectivityTestEnabled = true;
    private boolean nirCollectCellinfoEnabled = true;
    private boolean ctCollectCellinfoEnabled = true;
    private String connectivityTestCdnconfigUrl = "https://d2to8y50b3n6dq.cloudfront.net/truststores/[PROJECTID]/cdnconfig.zip";
    private String geoipUrl = "https://geoip.api.p3insight.de/geoip/";
    private long ctltIntervalMilli = 900000;
    private long sendIntervalMilli = 7200000;
    private long guardDiffMilli = 120000;
    private long ttl = 86400000;
    private double iep = Utils.DOUBLE_EPSILON;

    public String a() {
        return this.connectivityTestCdnconfigUrl;
    }

    public String b() {
        return this.connectivityTestFilename;
    }

    public String c() {
        return this.connectivityTestHostname;
    }

    public long d() {
        return this.ctltIntervalMilli;
    }

    public String e() {
        return this.geoipUrl;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || NetworkTestsMetaData.class != obj.getClass()) {
            return false;
        }
        NetworkTestsMetaData networkTestsMetaData = (NetworkTestsMetaData) obj;
        if (this.numberOfRecordsMin != networkTestsMetaData.numberOfRecordsMin || this.numberOfRecordsMax != networkTestsMetaData.numberOfRecordsMax || this.enabled != networkTestsMetaData.enabled || this.enabledCachedProcess != networkTestsMetaData.enabledCachedProcess || this.connectivityTestEnabled != networkTestsMetaData.connectivityTestEnabled || this.nirCollectCellinfoEnabled != networkTestsMetaData.nirCollectCellinfoEnabled || this.ctCollectCellinfoEnabled != networkTestsMetaData.ctCollectCellinfoEnabled || this.ctltIntervalMilli != networkTestsMetaData.ctltIntervalMilli || this.sendIntervalMilli != networkTestsMetaData.sendIntervalMilli || this.ttl != networkTestsMetaData.ttl) {
            return false;
        }
        String str = this.hostCt;
        if (str == null ? networkTestsMetaData.hostCt != null : !str.equals(networkTestsMetaData.hostCt)) {
            return false;
        }
        String str2 = this.hostLt;
        if (str2 == null ? networkTestsMetaData.hostLt != null : !str2.equals(networkTestsMetaData.hostLt)) {
            return false;
        }
        String str3 = this.hostNir;
        if (str3 == null ? networkTestsMetaData.hostNir != null : !str3.equals(networkTestsMetaData.hostNir)) {
            return false;
        }
        String str4 = this.projectId;
        if (str4 == null ? networkTestsMetaData.projectId != null : !str4.equals(networkTestsMetaData.projectId)) {
            return false;
        }
        String str5 = this.connectivityTestHostname;
        if (str5 == null ? networkTestsMetaData.connectivityTestHostname != null : !str5.equals(networkTestsMetaData.connectivityTestHostname)) {
            return false;
        }
        String str6 = this.connectivityTestFilename;
        if (str6 == null ? networkTestsMetaData.connectivityTestFilename != null : !str6.equals(networkTestsMetaData.connectivityTestFilename)) {
            return false;
        }
        String str7 = this.connectivityTestCdnconfigUrl;
        if (str7 == null ? networkTestsMetaData.connectivityTestCdnconfigUrl != null : !str7.equals(networkTestsMetaData.connectivityTestCdnconfigUrl)) {
            return false;
        }
        String str8 = this.geoipUrl;
        String str9 = networkTestsMetaData.geoipUrl;
        return str8 != null ? str8.equals(str9) : str9 == null;
    }

    public long f() {
        return this.guardDiffMilli;
    }

    public String g() {
        return this.hostCt;
    }

    public String h() {
        return this.hostLt;
    }

    public int hashCode() {
        String str = this.hostCt;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.hostLt;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.hostNir;
        int hashCode3 = (((((((((hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31) + this.numberOfRecordsMin) * 31) + this.numberOfRecordsMax) * 31) + (this.enabled ? 1 : 0)) * 31) + (this.enabledCachedProcess ? 1 : 0)) * 31;
        String str4 = this.projectId;
        int hashCode4 = (hashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31;
        String str5 = this.connectivityTestHostname;
        int hashCode5 = (hashCode4 + (str5 != null ? str5.hashCode() : 0)) * 31;
        String str6 = this.connectivityTestFilename;
        int hashCode6 = (((((((hashCode5 + (str6 != null ? str6.hashCode() : 0)) * 31) + (this.connectivityTestEnabled ? 1 : 0)) * 31) + (this.nirCollectCellinfoEnabled ? 1 : 0)) * 31) + (this.ctCollectCellinfoEnabled ? 1 : 0)) * 31;
        String str7 = this.connectivityTestCdnconfigUrl;
        int hashCode7 = (hashCode6 + (str7 != null ? str7.hashCode() : 0)) * 31;
        String str8 = this.geoipUrl;
        int hashCode8 = (hashCode7 + (str8 != null ? str8.hashCode() : 0)) * 31;
        long j = this.ctltIntervalMilli;
        int i = (hashCode8 + ((int) (j ^ (j >>> 32)))) * 31;
        long j2 = this.sendIntervalMilli;
        int i2 = (i + ((int) (j2 ^ (j2 >>> 32)))) * 31;
        long j3 = this.ttl;
        return i2 + ((int) (j3 ^ (j3 >>> 32)));
    }

    public String i() {
        return this.hostNir;
    }

    public double j() {
        return this.iep;
    }

    public String k() {
        return this.projectId;
    }

    public boolean l() {
        return this.connectivityTestEnabled;
    }

    public boolean m() {
        return this.ctCollectCellinfoEnabled;
    }

    public boolean n() {
        return this.enabled;
    }

    public boolean o() {
        return this.enabledCachedProcess;
    }

    public boolean p() {
        return this.nirCollectCellinfoEnabled;
    }
}
