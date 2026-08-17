package com.startapp;

import android.net.LinkProperties;
import android.net.NetworkCapabilities;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class o1 {
    public static String a(LinkProperties linkProperties) {
        if (linkProperties == null) {
            return "";
        }
        try {
            String a = a(linkProperties.toString().replaceAll("\\[ ", "\\[").replaceAll(" \\]", "\\]"), "PcscfAddresses:");
            if (a.isEmpty()) {
                return "";
            }
            String replace = a.replace("[", "").replace("]", "");
            return replace.lastIndexOf(",") == replace.length() + (-1) ? replace.substring(0, replace.length() - 1) : replace;
        } catch (Throwable th) {
            h1.a(th);
            return "";
        }
    }

    public static String a(NetworkCapabilities networkCapabilities) {
        if (networkCapabilities != null) {
            try {
                return a(networkCapabilities.toString(), "Capabilities:").replaceAll("&", ",").toLowerCase();
            } catch (Throwable th) {
                h1.a(th);
            }
        }
        return "";
    }

    private static String a(String str, String str2) throws Exception {
        String replaceAll = str.substring(str.indexOf(str2)).replaceAll(str2 + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR, "");
        return replaceAll.substring(0, replaceAll.contains(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR) ? replaceAll.indexOf(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR) : replaceAll.length() - 1);
    }

    public static int b(NetworkCapabilities networkCapabilities) {
        if (networkCapabilities != null) {
            try {
                return Integer.parseInt(a(networkCapabilities.toString(), "Specifier:").replaceAll("<", "").replaceAll(">", ""));
            } catch (Throwable th) {
                h1.a(th);
            }
        }
        return -1;
    }
}
