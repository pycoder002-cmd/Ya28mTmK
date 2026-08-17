package com.startapp.sdk.adsbase.commontracking;

import com.startapp.aa;
import com.startapp.sdk.adsbase.remoteconfig.MetaData;
import com.startapp.x9;
import java.io.Serializable;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class TrackingParams implements Serializable {
    private static final long serialVersionUID = 1;
    private String adTag;
    private String clientSessionId;
    private String nonImpressionReason;
    private int offset;
    private String profileId;

    public TrackingParams() {
        this(null);
    }

    public TrackingParams(String str) {
        this.adTag = str;
        x9 x9Var = x9.a;
        this.clientSessionId = x9.a.a();
        this.profileId = MetaData.q().y();
        this.offset = 0;
    }

    public TrackingParams a(int i) {
        this.offset = i;
        return this;
    }

    public TrackingParams a(String str) {
        this.nonImpressionReason = str;
        return this;
    }

    public String a() {
        return this.adTag;
    }

    public int b() {
        return this.offset;
    }

    public String c() {
        if (this.offset <= 0) {
            return "";
        }
        return "&offset=" + this.offset;
    }

    public String d() {
        return this.profileId;
    }

    public String e() {
        String str;
        String str2;
        String str3;
        StringBuilder sb = new StringBuilder();
        String str4 = this.adTag;
        String str5 = "";
        if (str4 == null || str4.equals("")) {
            str = "";
        } else {
            str = "&adTag=" + aa.b(this.adTag.substring(0, this.adTag.length() < 200 ? this.adTag.length() : 200));
        }
        sb.append(str);
        if (this.clientSessionId != null) {
            str2 = "&clientSessionId=" + aa.b(this.clientSessionId);
        } else {
            str2 = "";
        }
        sb.append(str2);
        if (this.profileId != null) {
            str3 = "&profileId=" + aa.b(this.profileId);
        } else {
            str3 = "";
        }
        sb.append(str3);
        sb.append(c());
        String str6 = this.nonImpressionReason;
        if (str6 != null && !str6.equals("")) {
            str5 = "&isShown=false&reason=" + aa.b(this.nonImpressionReason);
        }
        sb.append(str5);
        return sb.toString();
    }
}
