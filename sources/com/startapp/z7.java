package com.startapp;

import android.content.Context;
import android.os.Build;
import com.startapp.sdk.adsbase.remoteconfig.AnalyticsConfig;
import com.startapp.sdk.adsbase.remoteconfig.MetaData;
import com.startapp.sdk.components.ComponentLocator;
import com.startapp.sdk.insight.NetworkTestsMetaData;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class z7 implements Runnable, Comparable<z7> {
    public final Context a;
    public final p7 b;
    public final r7 c;
    public final u7 d;
    public final Exception e = new Exception();

    public z7(Context context, p7 p7Var, r7 r7Var, u7 u7Var) {
        this.a = context;
        this.b = p7Var;
        this.c = r7Var;
        this.d = u7Var;
    }

    /* JADX WARN: Removed duplicated region for block: B:107:0x015c  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x015f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.startapp.y7 a() {
        /*
            Method dump skipped, instructions count: 652
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.startapp.z7.a():com.startapp.y7");
    }

    public Throwable a(Throwable th) {
        if (Build.VERSION.SDK_INT >= 19) {
            th.addSuppressed(this.e);
        }
        return th;
    }

    public final boolean a(y7 y7Var) {
        String str;
        AnalyticsConfig analyticsConfig = MetaData.h.analytics;
        NetworkTestsMetaData u = MetaData.h.u();
        q7 q7Var = this.b.a;
        String str2 = null;
        if (q7Var == q7.l) {
            if (u != null) {
                str = u.g();
            }
            str = null;
        } else if (q7Var == q7.m) {
            if (u != null) {
                str = u.h();
            }
            str = null;
        } else if (q7Var == q7.n) {
            if (u != null) {
                str = u.i();
            }
            str = null;
        } else {
            if (q7Var == q7.i) {
                str = analyticsConfig.hostPeriodic;
                if (str == null) {
                    str = AnalyticsConfig.a;
                }
            }
            str = null;
        }
        if (str == null && (str = analyticsConfig.hostSecured) == null) {
            str = AnalyticsConfig.a;
        }
        String str3 = str;
        n7 j = ComponentLocator.a(this.a).j();
        j.getClass();
        try {
            str2 = j.a(str3, y7Var, null, false, null);
        } catch (Throwable th) {
            p7.a(j.a, th);
        }
        return str2 != null;
    }

    @Override // java.lang.Comparable
    public int compareTo(z7 z7Var) {
        return z7Var.c.c - this.c.c;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v3, types: [int] */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v5, types: [com.startapp.u7] */
    /* JADX WARN: Type inference failed for: r1v8 */
    /* JADX WARN: Type inference failed for: r1v9 */
    @Override // java.lang.Runnable
    public void run() {
        ?? r1;
        ?? r0 = 0;
        r0 = 0;
        try {
            boolean a = a(a());
            u7 u7Var = this.d;
            r0 = a;
            r1 = u7Var;
            if (u7Var == null) {
            }
        } catch (OutOfMemoryError unused) {
        } catch (Throwable th) {
            try {
                if (this.b.a != q7.d) {
                    p7.a(this.a, a(th));
                }
                u7 u7Var2 = this.d;
                r1 = u7Var2;
                if (u7Var2 == null) {
                }
            } finally {
                u7 u7Var3 = this.d;
                if (u7Var3 != null) {
                    u7Var3.a(this.b, 0);
                }
            }
        }
    }
}
