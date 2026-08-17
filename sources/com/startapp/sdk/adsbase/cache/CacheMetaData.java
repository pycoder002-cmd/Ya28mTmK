package com.startapp.sdk.adsbase.cache;

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
public class CacheMetaData implements Serializable {
    public static volatile CacheMetaData a = new CacheMetaData();
    private static final long serialVersionUID = 1;

    @f(complex = true)
    private ACMConfig ACM = new ACMConfig();
    private float sendCacheSizeProb = 20.0f;
    private String cacheMetaDataUpdateVersion = "4.9.1";

    public static void a(Context context) {
        CacheMetaData cacheMetaData = (CacheMetaData) h9.a(context, "StartappCacheMetadata", CacheMetaData.class);
        CacheMetaData cacheMetaData2 = new CacheMetaData();
        if (cacheMetaData == null) {
            a = cacheMetaData2;
            return;
        }
        boolean b = aa.b(cacheMetaData, cacheMetaData2);
        if (!(!"4.9.1".equals(cacheMetaData.cacheMetaDataUpdateVersion)) && b) {
            p7 p7Var = new p7(q7.c);
            p7Var.d = "metadata_null";
            p7Var.a(context);
        }
        a = cacheMetaData;
    }

    public static void a(Context context, CacheMetaData cacheMetaData) {
        cacheMetaData.cacheMetaDataUpdateVersion = "4.9.1";
        a = cacheMetaData;
        h9.b(context, null, "StartappCacheMetadata", cacheMetaData);
    }

    public static CacheMetaData b() {
        return a;
    }

    public ACMConfig a() {
        return this.ACM;
    }

    public float c() {
        return this.sendCacheSizeProb;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || CacheMetaData.class != obj.getClass()) {
            return false;
        }
        CacheMetaData cacheMetaData = (CacheMetaData) obj;
        return Float.compare(cacheMetaData.sendCacheSizeProb, this.sendCacheSizeProb) == 0 && aa.a(this.ACM, cacheMetaData.ACM) && aa.a(this.cacheMetaDataUpdateVersion, cacheMetaData.cacheMetaDataUpdateVersion);
    }

    public int hashCode() {
        Object[] objArr = {this.ACM, Float.valueOf(this.sendCacheSizeProb), this.cacheMetaDataUpdateVersion};
        Map<Activity, Integer> map = aa.a;
        return Arrays.deepHashCode(objArr);
    }
}
