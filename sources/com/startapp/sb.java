package com.startapp;

import android.content.Context;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public abstract class sb<T> {
    public final Context a;
    public volatile T b;
    public volatile long c;
    public final long d;

    public sb(Context context) {
        this(context, 900000L);
    }

    public sb(Context context, long j) {
        this.a = context;
        this.d = j;
    }

    public T a() {
        return null;
    }

    public T a(boolean z) {
        return a();
    }

    /* JADX WARN: Code restructure failed: missing block: B:6:0x0016, code lost:
    
        if ((r8.c + r8.d < android.os.SystemClock.uptimeMillis()) != false) goto L9;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final T b() {
        /*
            r8 = this;
            T r0 = r8.b
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L18
            long r3 = r8.c
            long r5 = r8.d
            long r3 = r3 + r5
            long r5 = android.os.SystemClock.uptimeMillis()
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 >= 0) goto L15
            r3 = 1
            goto L16
        L15:
            r3 = 0
        L16:
            if (r3 == 0) goto L44
        L18:
            monitor-enter(r8)
            T r0 = r8.b     // Catch: java.lang.Throwable -> L4c
            long r3 = r8.c     // Catch: java.lang.Throwable -> L4c
            long r5 = r8.d     // Catch: java.lang.Throwable -> L4c
            long r3 = r3 + r5
            long r5 = android.os.SystemClock.uptimeMillis()     // Catch: java.lang.Throwable -> L4c
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 >= 0) goto L29
            goto L2a
        L29:
            r1 = 0
        L2a:
            if (r0 == 0) goto L2e
            if (r1 == 0) goto L43
        L2e:
            java.lang.Object r0 = r8.a(r1)     // Catch: java.lang.Throwable -> L33
            goto L39
        L33:
            r1 = move-exception
            android.content.Context r2 = r8.a     // Catch: java.lang.Throwable -> L4c
            com.startapp.p7.a(r2, r1)     // Catch: java.lang.Throwable -> L4c
        L39:
            if (r0 == 0) goto L43
            r8.b = r0     // Catch: java.lang.Throwable -> L4c
            long r1 = android.os.SystemClock.uptimeMillis()     // Catch: java.lang.Throwable -> L4c
            r8.c = r1     // Catch: java.lang.Throwable -> L4c
        L43:
            monitor-exit(r8)     // Catch: java.lang.Throwable -> L4c
        L44:
            if (r0 == 0) goto L47
            goto L4b
        L47:
            java.lang.Object r0 = r8.c()
        L4b:
            return r0
        L4c:
            r0 = move-exception
            monitor-exit(r8)     // Catch: java.lang.Throwable -> L4c
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.startapp.sb.b():java.lang.Object");
    }

    public abstract T c();
}
