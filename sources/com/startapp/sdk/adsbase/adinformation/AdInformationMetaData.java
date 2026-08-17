package com.startapp.sdk.adsbase.adinformation;

import android.app.Activity;
import android.content.Context;
import com.startapp.aa;
import com.startapp.f;
import com.startapp.h9;
import com.startapp.p7;
import com.startapp.q7;
import com.startapp.sdk.adsbase.adinformation.AdInformationConfig;
import java.io.Serializable;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class AdInformationMetaData implements Serializable {
    public static volatile AdInformationMetaData a = new AdInformationMetaData();
    public static final Object b = new Object();
    private static final long serialVersionUID = 1;

    @f(complex = true)
    private AdInformationConfig AdInformation = AdInformationConfig.a();
    private String adInformationMetadataUpdateVersion = "4.9.1";

    public static void a(Context context) {
        AdInformationMetaData adInformationMetaData = (AdInformationMetaData) h9.a(context, "StartappAdInfoMetadata", AdInformationMetaData.class);
        AdInformationMetaData adInformationMetaData2 = new AdInformationMetaData();
        if (adInformationMetaData != null) {
            boolean b2 = aa.b(adInformationMetaData, adInformationMetaData2);
            if (!(!"4.9.1".equals(adInformationMetaData.adInformationMetadataUpdateVersion)) && b2) {
                p7 p7Var = new p7(q7.c);
                p7Var.d = "metadata_null";
                p7Var.a(context);
            }
            AdInformationConfig adInformationConfig = adInformationMetaData.AdInformation;
            adInformationConfig.getClass();
            adInformationConfig.b = new EnumMap<>(AdInformationConfig.ImageResourceType.class);
            a = adInformationMetaData;
        } else {
            a = adInformationMetaData2;
        }
        a.AdInformation.a(context);
    }

    public static void a(Context context, AdInformationMetaData adInformationMetaData) {
        synchronized (b) {
            adInformationMetaData.adInformationMetadataUpdateVersion = "4.9.1";
            a = adInformationMetaData;
            AdInformationConfig.a(a.AdInformation);
            a.AdInformation.a(context);
            h9.b(context, null, "StartappAdInfoMetadata", adInformationMetaData);
        }
    }

    public AdInformationConfig a() {
        return this.AdInformation;
    }

    public String b() {
        return this.AdInformation.d();
    }

    public String c() {
        AdInformationConfig adInformationConfig = this.AdInformation;
        EnumMap<AdInformationConfig.ImageResourceType, ImageResourceConfig> enumMap = adInformationConfig.b;
        AdInformationConfig.ImageResourceType imageResourceType = AdInformationConfig.ImageResourceType.INFO_L;
        return (!enumMap.containsKey(imageResourceType) || adInformationConfig.b.get(imageResourceType).b().equals("")) ? "https://info.startappservice.com/InApp/resources/info_l.png" : adInformationConfig.b.get(imageResourceType).b();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || AdInformationMetaData.class != obj.getClass()) {
            return false;
        }
        AdInformationMetaData adInformationMetaData = (AdInformationMetaData) obj;
        return aa.a(this.AdInformation, adInformationMetaData.AdInformation) && aa.a(this.adInformationMetadataUpdateVersion, adInformationMetaData.adInformationMetadataUpdateVersion);
    }

    public int hashCode() {
        Object[] objArr = {this.AdInformation, this.adInformationMetadataUpdateVersion};
        Map<Activity, Integer> map = aa.a;
        return Arrays.deepHashCode(objArr);
    }
}
