package com.startapp;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class h1 {
    private static a a;

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public interface a {
        void a(Throwable th);
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public static class b {
        public static StackTraceElement[] a() {
            return Thread.currentThread().getStackTrace();
        }
    }

    private static StackTraceElement a(int i) {
        if (i < 0) {
            i = 0;
        }
        StackTraceElement[] a2 = b.a();
        if (a2 == null) {
            return null;
        }
        String name = b.class.getName();
        int length = a2.length;
        for (int i2 = 0; i2 < length; i2++) {
            StackTraceElement stackTraceElement = a2[i2];
            if (stackTraceElement != null && name.equals(stackTraceElement.getClassName())) {
                int i3 = i2 + 3 + i;
                if (i3 < length) {
                    return a2[i3];
                }
                return null;
            }
        }
        return null;
    }

    public static void a(a aVar) {
        a = aVar;
    }

    public static void a(Throwable th) {
        a(th, true, false);
    }

    private static void a(Throwable th, boolean z, boolean z2) {
    }

    public static void b(Throwable th) {
        a(th, true, true);
    }

    public static void c(Throwable th) {
        a aVar = a;
        if (aVar == null) {
            a(th, false, false);
        } else {
            try {
                aVar.a(th);
            } catch (Throwable unused) {
            }
        }
    }
}
