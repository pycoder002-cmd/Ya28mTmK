package com.startapp;

import android.content.Context;
import android.os.Bundle;
import android.os.Debug;
import com.startapp.pc;
import com.startapp.sc;
import com.startapp.sdk.adsbase.remoteconfig.AnalyticsConfig;
import com.startapp.sdk.adsbase.remoteconfig.MetaData;
import com.startapp.sdk.jobs.JobRequest;
import java.io.File;
import java.lang.Thread;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class l7 implements Thread.UncaughtExceptionHandler {
    public final Context a;
    public final qc b;
    public Thread.UncaughtExceptionHandler c;

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public static class a extends pc {
        public a(Context context, pc.a aVar, Bundle bundle) {
            super(context, aVar, bundle);
        }

        /* JADX WARN: Removed duplicated region for block: B:22:0x006b A[ORIG_RETURN, RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:24:? A[RETURN, SYNTHETIC] */
        @Override // com.startapp.pc
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public boolean runSync() {
            /*
                r10 = this;
                android.content.Context r0 = r10.context
                com.startapp.sdk.components.ComponentLocator r0 = com.startapp.sdk.components.ComponentLocator.a(r0)
                com.startapp.ab<com.startapp.l7> r0 = r0.I
                java.lang.Object r0 = r0.b()
                com.startapp.l7 r0 = (com.startapp.l7) r0
                r0.getClass()
                r1 = 1
                r2 = 0
                java.io.File r3 = new java.io.File     // Catch: java.lang.Throwable -> L66
                android.content.Context r4 = r0.a     // Catch: java.lang.Throwable -> L66
                java.io.File r4 = r4.getCacheDir()     // Catch: java.lang.Throwable -> L66
                java.lang.String r5 = "StartApp-06ae7b241cf967e9"
                r3.<init>(r4, r5)     // Catch: java.lang.Throwable -> L66
                boolean r4 = r3.exists()     // Catch: java.lang.Throwable -> L66
                if (r4 == 0) goto L66
                boolean r4 = r3.isFile()     // Catch: java.lang.Throwable -> L66
                if (r4 == 0) goto L66
                com.startapp.sdk.adsbase.remoteconfig.MetaData r4 = com.startapp.sdk.adsbase.remoteconfig.MetaData.h     // Catch: java.lang.Throwable -> L66
                com.startapp.sdk.adsbase.remoteconfig.AnalyticsConfig r4 = r4.analytics     // Catch: java.lang.Throwable -> L66
                if (r4 != 0) goto L33
                goto L66
            L33:
                double r5 = java.lang.Math.random()     // Catch: java.lang.Throwable -> L66
                double r7 = r4.d()     // Catch: java.lang.Throwable -> L66
                int r9 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
                if (r9 < 0) goto L40
                goto L66
            L40:
                java.lang.String r4 = r4.e()     // Catch: java.lang.Throwable -> L66
                if (r4 != 0) goto L47
                goto L66
            L47:
                com.startapp.p7 r4 = new com.startapp.p7     // Catch: java.lang.Throwable -> L66
                com.startapp.q7 r5 = com.startapp.q7.b     // Catch: java.lang.Throwable -> L66
                r4.<init>(r5)     // Catch: java.lang.Throwable -> L66
                java.lang.String r5 = "AEH: oom uploaded"
                r4.d = r5     // Catch: java.lang.Throwable -> L66
                long r5 = r3.length()     // Catch: java.lang.Throwable -> L66
                java.lang.String r5 = java.lang.String.valueOf(r5)     // Catch: java.lang.Throwable -> L66
                r4.e = r5     // Catch: java.lang.Throwable -> L66
                android.content.Context r0 = r0.a     // Catch: java.lang.Throwable -> L66
                r4.a(r0)     // Catch: java.lang.Throwable -> L66
                r3.delete()     // Catch: java.lang.Throwable -> L66
                r0 = 1
                goto L67
            L66:
                r0 = 0
            L67:
                r3 = 2
                if (r0 != r3) goto L6b
                goto L6c
            L6b:
                r1 = 0
            L6c:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.startapp.l7.a.runSync():boolean");
        }
    }

    public l7(Context context, qc qcVar) {
        this.a = context;
        this.b = qcVar;
    }

    public void a() {
        try {
            Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
            if (!(defaultUncaughtExceptionHandler instanceof l7)) {
                Thread.setDefaultUncaughtExceptionHandler(this);
                this.c = defaultUncaughtExceptionHandler;
            }
            qc qcVar = this.b;
            sc.a aVar = new sc.a(a.class);
            aVar.b = JobRequest.Network.UNMETERED;
            aVar.c = Boolean.TRUE;
            qcVar.a(new sc(aVar));
        } catch (Throwable th) {
            p7.a(this.a, th);
        }
    }

    public final void a(Thread thread, Throwable th) {
        try {
            Thread.UncaughtExceptionHandler uncaughtExceptionHandler = this.c;
            if (uncaughtExceptionHandler != null) {
                uncaughtExceptionHandler.uncaughtException(thread, th);
            }
        } catch (Throwable th2) {
            th2.printStackTrace();
        }
    }

    public final void b() {
        try {
            AnalyticsConfig analyticsConfig = MetaData.h.analytics;
            if (analyticsConfig != null && Math.random() < analyticsConfig.c()) {
                File file = new File(this.a.getCacheDir(), "StartApp-06ae7b241cf967e9");
                Debug.dumpHprofData(file.getAbsolutePath());
                p7 p7Var = new p7(q7.b);
                p7Var.d = "AEH: oom saved";
                p7Var.e = String.valueOf(file.length());
                p7Var.a(this.a);
            }
        } catch (Throwable unused) {
        }
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
        boolean z;
        try {
            if (aa.a(th) != null) {
                p7.a(this.a, th, q7.f);
            }
            Throwable th2 = th;
            while (true) {
                if (th2 == null) {
                    z = false;
                    break;
                } else {
                    if (th2 instanceof OutOfMemoryError) {
                        z = true;
                        break;
                    }
                    th2 = th2.getCause();
                }
            }
            if (z) {
                b();
            }
        } catch (Throwable unused) {
        }
        a(thread, th);
    }
}
