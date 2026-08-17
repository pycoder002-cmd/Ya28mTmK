package com.startapp;

import java.net.InetAddress;
import java.net.UnknownHostException;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class y1 {
    private static final String a = "y1";

    public static String a(String str) {
        String str2;
        try {
            str2 = InetAddress.getByName(str).getCanonicalHostName();
        } catch (UnknownHostException e) {
            h1.b(e);
            str2 = null;
        }
        if (str2 != null && !str2.equals(str) && str2.contains("cloudfront")) {
            String[] split = str2.split("\\.");
            if (split.length > 0) {
                return b(split[1]);
            }
        }
        return "";
    }

    public static String b(String str) {
        return (str == null || str.length() <= 2) ? "" : str.substring(0, 3);
    }
}
