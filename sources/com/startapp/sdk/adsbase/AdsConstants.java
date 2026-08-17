package com.startapp.sdk.adsbase;

import com.startapp.sdk.adsbase.model.AdPreferences;
import com.startapp.sdk.adsbase.remoteconfig.MetaData;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class AdsConstants {
    public static final String a;
    public static final String b;
    public static final String c;
    public static final String d;
    public static final String e;
    public static final String f;
    public static final Boolean g;
    public static final Boolean h;
    public static final String[] i;
    public static final String[] j;

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public enum AdApiType {
        HTML,
        JSON
    }

    static {
        String str = "get";
        a = str;
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append("ads");
        b = sb.toString();
        StringBuilder sb2 = new StringBuilder();
        sb2.append(str);
        sb2.append("htmlad");
        c = sb2.toString();
        d = "trackdownload";
        StringBuilder sb3 = new StringBuilder();
        sb3.append(str);
        sb3.append("adsmetadata");
        e = sb3.toString();
        f = "https://imp.startappservice.com/tracking/adImpression";
        Boolean bool = Boolean.FALSE;
        g = bool;
        h = bool;
        i = new String[]{"back_", "back_dark", "browser_icon_dark", "forward_", "forward_dark", "x_dark"};
        j = new String[]{"empty_star", "filled_star", "half_star"};
    }

    public static String a(AdApiType adApiType, AdPreferences.Placement placement) {
        String str;
        String a2;
        String str2;
        int ordinal = adApiType.ordinal();
        String str3 = null;
        if (ordinal == 0) {
            str = c;
            a2 = MetaData.h.a(placement);
        } else {
            if (ordinal != 1) {
                str2 = null;
                return str3 + str2;
            }
            str = b;
            a2 = MetaData.h.a(placement);
        }
        String str4 = str;
        str3 = a2;
        str2 = str4;
        return str3 + str2;
    }
}
