package com.startapp;

import android.net.TrafficStats;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class c2 {
    private static final String a = "c2";
    private static String[] b = null;
    private static String[] c = null;
    private static boolean d = true;
    private static Method e;
    private static Method f;
    private static Method g;

    static {
        try {
            Method declaredMethod = TrafficStats.class.getDeclaredMethod("getRxBytes", String.class);
            e = declaredMethod;
            declaredMethod.setAccessible(true);
        } catch (Throwable th) {
            h1.b(th);
        }
        try {
            Method declaredMethod2 = TrafficStats.class.getDeclaredMethod("getTxBytes", String.class);
            f = declaredMethod2;
            declaredMethod2.setAccessible(true);
        } catch (Throwable th2) {
            h1.b(th2);
        }
        try {
            Method declaredMethod3 = TrafficStats.class.getDeclaredMethod("getMobileIfaces", new Class[0]);
            g = declaredMethod3;
            declaredMethod3.setAccessible(true);
        } catch (Throwable th3) {
            h1.b(th3);
        }
    }

    public static long a(String str) {
        Method method = e;
        if (method != null) {
            try {
                return ((Long) method.invoke(null, str)).longValue();
            } catch (Throwable th) {
                h1.a(th);
            }
        }
        return c("/sys/class/net/" + str + "/statistics/rx_bytes");
    }

    private static void a() {
        ArrayList arrayList = new ArrayList();
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            if (networkInterfaces != null) {
                while (networkInterfaces.hasMoreElements()) {
                    NetworkInterface nextElement = networkInterfaces.nextElement();
                    if (nextElement != null && nextElement.getName().startsWith("eth")) {
                        String[] strArr = b;
                        if (strArr != null && strArr.length > 0) {
                            for (String str : strArr) {
                                if (nextElement.getName().equals(str)) {
                                    break;
                                }
                            }
                        }
                        Enumeration<InetAddress> inetAddresses = nextElement.getInetAddresses();
                        boolean z = false;
                        if (inetAddresses != null) {
                            while (inetAddresses.hasMoreElements()) {
                                InetAddress nextElement2 = inetAddresses.nextElement();
                                if (nextElement2 != null && nextElement2.isLinkLocalAddress() && !nextElement2.isLoopbackAddress()) {
                                    z = true;
                                }
                            }
                        }
                        if (z) {
                            arrayList.add(b2.a(nextElement.getName()));
                        }
                    }
                }
            }
            c = (String[]) arrayList.toArray(new String[arrayList.size()]);
            d = false;
        } catch (Throwable th) {
            try {
                h1.a(th);
                c = (String[]) arrayList.toArray(new String[arrayList.size()]);
                d = false;
            } catch (Throwable th2) {
                c = (String[]) arrayList.toArray(new String[arrayList.size()]);
                d = false;
                throw th2;
            }
        }
    }

    public static synchronized long b() {
        long j;
        String[] strArr;
        synchronized (c2.class) {
            j = 0;
            if (d && ((strArr = c) == null || strArr.length <= 0)) {
                a();
            }
            String[] strArr2 = c;
            if (strArr2 != null && strArr2.length > 0) {
                for (String str : strArr2) {
                    long a2 = a(str);
                    if (a2 > -1) {
                        j += a2;
                    }
                }
            }
        }
        return j;
    }

    public static long b(String str) {
        Method method = f;
        if (method != null) {
            try {
                return ((Long) method.invoke(null, str)).longValue();
            } catch (Throwable th) {
                h1.a(th);
            }
        }
        return c("/sys/class/net/" + str + "/statistics/tx_bytes");
    }

    public static synchronized long c() {
        long j;
        String[] strArr;
        synchronized (c2.class) {
            j = 0;
            if (d && ((strArr = c) == null || strArr.length <= 0)) {
                a();
            }
            String[] strArr2 = c;
            if (strArr2 != null && strArr2.length > 0) {
                for (String str : strArr2) {
                    long b2 = b(str);
                    if (b2 > -1) {
                        j += b2;
                    }
                }
            }
        }
        return j;
    }

    private static long c(String str) {
        String[] a2 = z1.a(str);
        if (a2.length > 0) {
            return Long.parseLong(a2[0]);
        }
        return -1L;
    }

    private static void d() {
        Method method = g;
        if (method == null) {
            return;
        }
        try {
            String[] strArr = (String[]) method.invoke(null, new Object[0]);
            if (strArr != null) {
                b = strArr;
            }
        } catch (Throwable th) {
            h1.a(th);
        }
    }

    public static synchronized long e() {
        long j;
        synchronized (c2.class) {
            try {
                j = TrafficStats.getMobileRxBytes();
            } catch (Throwable th) {
                h1.a(th);
                j = 0;
            }
            if (j <= 0) {
                String[] strArr = b;
                if (strArr != null) {
                    for (String str : strArr) {
                        long c2 = c("/sys/class/net/" + str + "/statistics/rx_bytes");
                        if (c2 > -1) {
                            j += c2;
                        }
                    }
                }
            } else if (b == null) {
                d();
            }
        }
        return j;
    }

    public static synchronized long f() {
        long j;
        synchronized (c2.class) {
            try {
                j = TrafficStats.getMobileTxBytes();
            } catch (Throwable th) {
                h1.a(th);
                j = 0;
            }
            if (j <= 0) {
                String[] strArr = b;
                if (strArr != null) {
                    for (String str : strArr) {
                        long c2 = c("/sys/class/net/" + str + "/statistics/tx_bytes");
                        if (c2 > -1) {
                            j += c2;
                        }
                    }
                }
            } else if (b == null) {
                d();
            }
        }
        return j;
    }

    public static synchronized long g() {
        long totalRxBytes;
        synchronized (c2.class) {
            totalRxBytes = TrafficStats.getTotalRxBytes() - TrafficStats.getMobileRxBytes();
        }
        return totalRxBytes;
    }

    public static synchronized long h() {
        long totalTxBytes;
        synchronized (c2.class) {
            totalTxBytes = TrafficStats.getTotalTxBytes() - TrafficStats.getMobileTxBytes();
        }
        return totalTxBytes;
    }
}
