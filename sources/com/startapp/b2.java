package com.startapp;

import com.startapp.networkTest.enums.AnonymizationLevel;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class b2 {
    private static final String a = "b2";

    public static String a(InputStream inputStream, String str) {
        StringWriter stringWriter = new StringWriter();
        char[] cArr = new char[1024];
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, str));
            while (true) {
                int read = bufferedReader.read(cArr);
                if (read == -1) {
                    bufferedReader.close();
                    return stringWriter.toString();
                }
                stringWriter.write(cArr, 0, read);
            }
        } catch (Throwable th) {
            h1.a(th);
            return null;
        }
    }

    public static String a(String str) {
        return str == null ? "" : str.replaceAll("[\u0000-\u001f]", "").trim();
    }

    public static String a(String str, AnonymizationLevel anonymizationLevel) {
        if (str == null) {
            str = "";
        }
        int ordinal = anonymizationLevel.ordinal();
        if (ordinal == 0) {
            return str;
        }
        if (ordinal != 1) {
            return "";
        }
        if (str.length() <= 3) {
            return "***";
        }
        return str.substring(0, str.length() - 3) + "***";
    }

    public static String a(InetAddress inetAddress) {
        String hostAddress = inetAddress.getHostAddress();
        if (inetAddress instanceof Inet4Address) {
            return hostAddress.substring(0, hostAddress.lastIndexOf(46)) + ".xxx";
        }
        String str = "";
        if (!(inetAddress instanceof Inet6Address)) {
            return "";
        }
        String[] split = hostAddress.split(":");
        int length = split.length - 4;
        if (length < 0) {
            length = 0;
        }
        for (int i = 0; i < length; i++) {
            str = str + split[i] + ":";
        }
        return str + "x:x:x:x";
    }

    public static String a(List<String> list) {
        if (list == null || list.size() == 0) {
            return "";
        }
        int i = 0;
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            i += it.next().length();
        }
        StringBuffer stringBuffer = new StringBuffer(i);
        Iterator<String> it2 = list.iterator();
        while (it2.hasNext()) {
            stringBuffer.append(it2.next());
        }
        return stringBuffer.toString();
    }

    public static String[] a(String[] strArr) {
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            if (str != null && str.length() > 0) {
                arrayList.add(str);
            }
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    public static String b(String str) {
        if (str.length() != 32) {
            return str;
        }
        return str.substring(0, 7) + "-" + str.substring(7, 11) + "-" + str.substring(11, 15) + "-" + str.substring(15, 19) + "-" + str.substring(19, 31);
    }

    public static boolean c(String str) {
        return str == null || str.length() == 0;
    }

    public static String d(String str) {
        return (str.length() > 1 && str.startsWith("\"") && str.endsWith("\"")) ? str.substring(1, str.length() - 1) : str;
    }
}
