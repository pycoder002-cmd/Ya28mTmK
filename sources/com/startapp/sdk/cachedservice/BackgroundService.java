package com.startapp.sdk.cachedservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public final class BackgroundService extends Service {
    /* JADX WARN: Code restructure failed: missing block: B:15:0x001c, code lost:
    
        if (r0 >= 26) goto L9;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void a(android.content.Context r4, boolean r5) {
        /*
            int r0 = com.startapp.ya.a
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 0
            r2 = 26
            if (r0 < r2) goto L1e
            android.content.pm.PackageManager r0 = r4.getPackageManager()     // Catch: java.lang.Throwable -> L1a
            java.lang.String r3 = r4.getPackageName()     // Catch: java.lang.Throwable -> L1a
            android.content.pm.PackageInfo r0 = r0.getPackageInfo(r3, r1)     // Catch: java.lang.Throwable -> L1a
            android.content.pm.ApplicationInfo r0 = r0.applicationInfo     // Catch: java.lang.Throwable -> L1a
            int r0 = r0.targetSdkVersion     // Catch: java.lang.Throwable -> L1a
            goto L1c
        L1a:
            int r0 = android.os.Build.VERSION.SDK_INT
        L1c:
            if (r0 >= r2) goto L1f
        L1e:
            r1 = 1
        L1f:
            if (r1 != 0) goto L22
            return
        L22:
            android.content.Intent r0 = new android.content.Intent
            java.lang.Class<com.startapp.sdk.cachedservice.BackgroundService> r1 = com.startapp.sdk.cachedservice.BackgroundService.class
            r0.<init>(r4, r1)
            if (r5 == 0) goto L2f
            r4.startService(r0)
            goto L32
        L2f:
            r4.stopService(r0)
        L32:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.startapp.sdk.cachedservice.BackgroundService.a(android.content.Context, boolean):void");
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        return 3;
    }
}
