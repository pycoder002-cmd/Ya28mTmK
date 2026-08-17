package com.startapp.sdk.ads.banner;

import android.app.Activity;
import android.content.Context;
import com.startapp.aa;
import com.startapp.f;
import com.startapp.h9;
import com.startapp.p7;
import com.startapp.q7;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Map;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class BannerMetaData implements Serializable {
    public static Object a = new Object();
    public static volatile BannerMetaData b = new BannerMetaData();
    private static final long serialVersionUID = 1;

    @f(complex = true)
    private BannerOptions BannerOptions = new BannerOptions();
    private String bannerMetadataUpdateVersion = "4.9.1";

    public static void a(Context context) {
        BannerMetaData bannerMetaData = (BannerMetaData) h9.a(context, "StartappBannerMetadata", BannerMetaData.class);
        BannerMetaData bannerMetaData2 = new BannerMetaData();
        if (bannerMetaData == null) {
            b = bannerMetaData2;
            return;
        }
        boolean b2 = aa.b(bannerMetaData, bannerMetaData2);
        if (!(!"4.9.1".equals(bannerMetaData.bannerMetadataUpdateVersion)) && b2) {
            p7 p7Var = new p7(q7.c);
            p7Var.d = "metadata_null";
            p7Var.a(context);
        }
        b = bannerMetaData;
    }

    public static void a(Context context, BannerMetaData bannerMetaData) {
        synchronized (a) {
            bannerMetaData.bannerMetadataUpdateVersion = "4.9.1";
            b = bannerMetaData;
            h9.b(context, null, "StartappBannerMetadata", bannerMetaData);
        }
    }

    public BannerOptions a() {
        return this.BannerOptions;
    }

    public BannerOptions b() {
        return new BannerOptions(this.BannerOptions);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || BannerMetaData.class != obj.getClass()) {
            return false;
        }
        BannerMetaData bannerMetaData = (BannerMetaData) obj;
        return aa.a(this.BannerOptions, bannerMetaData.BannerOptions) && aa.a(this.bannerMetadataUpdateVersion, bannerMetaData.bannerMetadataUpdateVersion);
    }

    public int hashCode() {
        Object[] objArr = {this.BannerOptions, this.bannerMetadataUpdateVersion};
        Map<Activity, Integer> map = aa.a;
        return Arrays.deepHashCode(objArr);
    }
}
