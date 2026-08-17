package com.startapp.sdk.ads.splash;

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
public class SplashMetaData implements Serializable {
    public static volatile SplashMetaData a = new SplashMetaData();
    public static Object b = new Object();
    private static final long serialVersionUID = 1;

    @f(complex = true)
    private SplashConfig SplashConfig = new SplashConfig();
    private String splashMetadataUpdateVersion = "4.9.1";

    public static void a(Context context) {
        SplashMetaData splashMetaData = (SplashMetaData) h9.a(context, "StartappSplashMetadata", SplashMetaData.class);
        SplashMetaData splashMetaData2 = new SplashMetaData();
        if (splashMetaData == null) {
            a = splashMetaData2;
            return;
        }
        boolean b2 = aa.b(splashMetaData, splashMetaData2);
        if (!(!"4.9.1".equals(splashMetaData.splashMetadataUpdateVersion)) && b2) {
            p7 p7Var = new p7(q7.c);
            p7Var.d = "metadata_null";
            p7Var.a(context);
        }
        a = splashMetaData;
    }

    public static void a(Context context, SplashMetaData splashMetaData) {
        synchronized (b) {
            splashMetaData.splashMetadataUpdateVersion = "4.9.1";
            a = splashMetaData;
            h9.b(context, null, "StartappSplashMetadata", splashMetaData);
        }
    }

    public SplashConfig a() {
        return this.SplashConfig;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || SplashMetaData.class != obj.getClass()) {
            return false;
        }
        SplashMetaData splashMetaData = (SplashMetaData) obj;
        return aa.a(this.SplashConfig, splashMetaData.SplashConfig) && aa.a(this.splashMetadataUpdateVersion, splashMetaData.splashMetadataUpdateVersion);
    }

    public int hashCode() {
        Object[] objArr = {this.SplashConfig, this.splashMetadataUpdateVersion};
        Map<Activity, Integer> map = aa.a;
        return Arrays.deepHashCode(objArr);
    }
}
