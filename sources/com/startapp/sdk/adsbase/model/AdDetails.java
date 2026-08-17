package com.startapp.sdk.adsbase.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.startapp.f;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class AdDetails implements Serializable, Parcelable {
    public static final Parcelable.Creator<AdDetails> CREATOR = new a();
    private static final long serialVersionUID = 1853332641062810893L;
    private String adId;
    private boolean app;
    private String appPresencePackage;
    private boolean belowMinCPM;
    private String bidToken;
    private String callToAction;
    private String category;
    private String clickUrl;
    private String closeUrl;
    private Long delayImpressionInSeconds;
    private String description;

    @f(type = ArrayList.class)
    private List<String> externalClickUrls;

    @f(type = ArrayList.class)
    private List<String> externalImpressionUrls;
    private String imageUrl;
    private String installs;
    private String intentDetails;
    private String intentPackageName;
    private int minAppVersion;
    private String packageName;
    private float rating;
    private String secondaryImageUrl;
    private Boolean sendRedirectHops;
    private boolean smartRedirect;
    private boolean startappBrowserEnabled;
    private String template;
    private String title;
    private String trackingClickUrl;
    private String trackingUrl;
    private Long ttl;

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public static class a implements Parcelable.Creator<AdDetails> {
        @Override // android.os.Parcelable.Creator
        public AdDetails createFromParcel(Parcel parcel) {
            return new AdDetails(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public AdDetails[] newArray(int i) {
            return new AdDetails[i];
        }
    }

    public AdDetails() {
        this.rating = 5.0f;
        this.belowMinCPM = false;
    }

    public AdDetails(Parcel parcel) {
        this.rating = 5.0f;
        this.belowMinCPM = false;
        this.bidToken = parcel.readString();
        this.adId = parcel.readString();
        this.clickUrl = parcel.readString();
        this.trackingUrl = parcel.readString();
        this.trackingClickUrl = parcel.readString();
        this.closeUrl = parcel.readString();
        this.title = parcel.readString();
        this.description = parcel.readString();
        this.imageUrl = parcel.readString();
        this.secondaryImageUrl = parcel.readString();
        this.rating = parcel.readFloat();
        int readInt = parcel.readInt();
        int readInt2 = parcel.readInt();
        this.smartRedirect = readInt == 1;
        this.startappBrowserEnabled = readInt2 != 0;
        this.template = parcel.readString();
        this.packageName = parcel.readString();
        this.appPresencePackage = parcel.readString();
        this.intentPackageName = parcel.readString();
        this.intentDetails = parcel.readString();
        this.minAppVersion = parcel.readInt();
        this.installs = parcel.readString();
        this.category = parcel.readString();
        this.app = parcel.readInt() == 1;
        this.belowMinCPM = parcel.readInt() == 1;
        Long valueOf = Long.valueOf(parcel.readLong());
        this.ttl = valueOf;
        if (valueOf.longValue() == -1) {
            this.ttl = null;
        }
        Long valueOf2 = Long.valueOf(parcel.readLong());
        this.delayImpressionInSeconds = valueOf2;
        if (valueOf2.longValue() == -1) {
            this.delayImpressionInSeconds = null;
        }
        int readInt3 = parcel.readInt();
        if (readInt3 == 0) {
            this.sendRedirectHops = null;
        } else {
            this.sendRedirectHops = Boolean.valueOf(readInt3 == 1);
        }
        this.externalImpressionUrls = parcel.readArrayList(String.class.getClassLoader());
        this.externalClickUrls = parcel.readArrayList(String.class.getClassLoader());
        this.callToAction = parcel.readString();
    }

    public boolean A() {
        return this.startappBrowserEnabled;
    }

    public Boolean B() {
        return this.sendRedirectHops;
    }

    public String a() {
        return this.adId;
    }

    public String b() {
        return this.appPresencePackage;
    }

    public String c() {
        return this.bidToken;
    }

    public String d() {
        return this.callToAction;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String e() {
        return this.category;
    }

    public String f() {
        return this.clickUrl;
    }

    public Long g() {
        return this.delayImpressionInSeconds;
    }

    public String h() {
        return this.description;
    }

    public String i() {
        return this.imageUrl;
    }

    public String j() {
        return this.installs;
    }

    public String k() {
        return this.intentDetails;
    }

    public String l() {
        return this.intentPackageName;
    }

    public boolean m() {
        return this.belowMinCPM;
    }

    public int n() {
        return this.minAppVersion;
    }

    public String o() {
        return this.packageName;
    }

    public float p() {
        return this.rating;
    }

    public String q() {
        return this.secondaryImageUrl;
    }

    public String r() {
        return this.template;
    }

    public String s() {
        return this.title;
    }

    public String[] t() {
        if (this.externalClickUrls == null) {
            return new String[]{this.trackingClickUrl};
        }
        ArrayList arrayList = new ArrayList(this.externalClickUrls);
        arrayList.add(this.trackingClickUrl);
        return (String[]) arrayList.toArray(new String[0]);
    }

    public String toString() {
        return super.toString();
    }

    public String u() {
        return this.closeUrl;
    }

    public String[] v() {
        if (this.externalImpressionUrls == null) {
            return new String[]{this.trackingUrl};
        }
        ArrayList arrayList = new ArrayList(this.externalImpressionUrls);
        arrayList.add(this.trackingUrl);
        return (String[]) arrayList.toArray(new String[0]);
    }

    public Long w() {
        return this.ttl;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.bidToken);
        parcel.writeString(this.adId);
        parcel.writeString(this.clickUrl);
        parcel.writeString(this.trackingUrl);
        parcel.writeString(this.trackingClickUrl);
        parcel.writeString(this.closeUrl);
        parcel.writeString(this.title);
        parcel.writeString(this.description);
        parcel.writeString(this.imageUrl);
        parcel.writeString(this.secondaryImageUrl);
        parcel.writeFloat(this.rating);
        boolean z = this.smartRedirect;
        boolean z2 = this.startappBrowserEnabled;
        parcel.writeInt(z ? 1 : 0);
        parcel.writeInt(z2 ? 1 : 0);
        parcel.writeString(this.template);
        parcel.writeString(this.packageName);
        parcel.writeString(this.appPresencePackage);
        parcel.writeString(this.intentPackageName);
        parcel.writeString(this.intentDetails);
        parcel.writeInt(this.minAppVersion);
        parcel.writeString(this.installs);
        parcel.writeString(this.category);
        parcel.writeInt(this.app ? 1 : 0);
        parcel.writeInt(this.belowMinCPM ? 1 : 0);
        Long l = this.ttl;
        if (l != null) {
            parcel.writeLong(l.longValue());
        } else {
            parcel.writeLong(-1L);
        }
        Long l2 = this.delayImpressionInSeconds;
        if (l2 != null) {
            parcel.writeLong(l2.longValue());
        } else {
            parcel.writeLong(-1L);
        }
        Boolean bool = this.sendRedirectHops;
        if (bool == null) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(bool.booleanValue() ? 1 : -1);
        }
        parcel.writeList(this.externalImpressionUrls);
        parcel.writeList(this.externalClickUrls);
        parcel.writeString(this.callToAction);
    }

    public boolean x() {
        return this.app;
    }

    public boolean y() {
        return this.intentPackageName != null;
    }

    public boolean z() {
        return this.smartRedirect;
    }
}
