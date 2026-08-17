package com.google.android.gms.internal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.PowerManager;
import android.os.WorkSource;
import android.text.TextUtils;
import android.util.Log;

/* loaded from: classes.dex */
public class zzxr {
    private static boolean DEBUG = false;
    private static String TAG = "WakeLock";
    private static String aDy = "*gcore*:";
    private final String Gc;
    private final String Ge;
    private final int aDA;
    private final String aDB;
    private boolean aDC;
    private int aDD;
    private int aDE;
    private final PowerManager.WakeLock aDz;
    private WorkSource ajz;
    private final Context mContext;

    public zzxr(Context context, int i, String str) {
        this(context, i, str, null, context == null ? null : context.getPackageName());
    }

    @SuppressLint({"UnwrappedWakeLock"})
    public zzxr(Context context, int i, String str, String str2, String str3) {
        this(context, i, str, str2, str3, null);
    }

    @SuppressLint({"UnwrappedWakeLock"})
    public zzxr(Context context, int i, String str, String str2, String str3, String str4) {
        this.aDC = true;
        com.google.android.gms.common.internal.zzaa.zzh(str, "Wake lock name can NOT be empty");
        this.aDA = i;
        this.aDB = str2;
        this.Ge = str4;
        this.mContext = context.getApplicationContext();
        if ("com.google.android.gms".equals(context.getPackageName())) {
            this.Gc = str;
        } else {
            String valueOf = String.valueOf(aDy);
            String valueOf2 = String.valueOf(str);
            this.Gc = valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
        }
        this.aDz = ((PowerManager) context.getSystemService("power")).newWakeLock(i, str);
        if (com.google.android.gms.common.util.zzy.zzcm(this.mContext)) {
            this.ajz = com.google.android.gms.common.util.zzy.zzy(context, com.google.android.gms.common.util.zzv.zzij(str3) ? context.getPackageName() : str3);
            zzc(this.ajz);
        }
    }

    private void zzd(WorkSource workSource) {
        try {
            this.aDz.setWorkSource(workSource);
        } catch (IllegalArgumentException e) {
            Log.wtf(TAG, e.toString());
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x001d, code lost:
    
        if (r12.aDE == 0) goto L13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0015, code lost:
    
        if (r0 == false) goto L9;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void zzk(java.lang.String r13, long r14) {
        /*
            r12 = this;
            boolean r0 = r12.zzoo(r13)
            java.lang.String r6 = r12.zzp(r13, r0)
            monitor-enter(r12)
            boolean r13 = r12.aDC     // Catch: java.lang.Throwable -> L44
            if (r13 == 0) goto L17
            int r13 = r12.aDD     // Catch: java.lang.Throwable -> L44
            int r1 = r13 + 1
            r12.aDD = r1     // Catch: java.lang.Throwable -> L44
            if (r13 == 0) goto L1f
            if (r0 != 0) goto L1f
        L17:
            boolean r13 = r12.aDC     // Catch: java.lang.Throwable -> L44
            if (r13 != 0) goto L42
            int r13 = r12.aDE     // Catch: java.lang.Throwable -> L44
            if (r13 != 0) goto L42
        L1f:
            com.google.android.gms.common.stats.zzg r1 = com.google.android.gms.common.stats.zzg.zzayg()     // Catch: java.lang.Throwable -> L44
            android.content.Context r2 = r12.mContext     // Catch: java.lang.Throwable -> L44
            android.os.PowerManager$WakeLock r13 = r12.aDz     // Catch: java.lang.Throwable -> L44
            java.lang.String r3 = com.google.android.gms.common.stats.zze.zza(r13, r6)     // Catch: java.lang.Throwable -> L44
            r4 = 7
            java.lang.String r5 = r12.Gc     // Catch: java.lang.Throwable -> L44
            java.lang.String r7 = r12.Ge     // Catch: java.lang.Throwable -> L44
            int r8 = r12.aDA     // Catch: java.lang.Throwable -> L44
            android.os.WorkSource r13 = r12.ajz     // Catch: java.lang.Throwable -> L44
            java.util.List r9 = com.google.android.gms.common.util.zzy.zzb(r13)     // Catch: java.lang.Throwable -> L44
            r10 = r14
            r1.zza(r2, r3, r4, r5, r6, r7, r8, r9, r10)     // Catch: java.lang.Throwable -> L44
            int r13 = r12.aDE     // Catch: java.lang.Throwable -> L44
            int r13 = r13 + 1
            r12.aDE = r13     // Catch: java.lang.Throwable -> L44
        L42:
            monitor-exit(r12)     // Catch: java.lang.Throwable -> L44
            return
        L44:
            r13 = move-exception
            monitor-exit(r12)     // Catch: java.lang.Throwable -> L44
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzxr.zzk(java.lang.String, long):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x001d, code lost:
    
        if (r11.aDE == 1) goto L13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0015, code lost:
    
        if (r0 == false) goto L9;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void zzon(java.lang.String r12) {
        /*
            r11 = this;
            boolean r0 = r11.zzoo(r12)
            java.lang.String r6 = r11.zzp(r12, r0)
            monitor-enter(r11)
            boolean r12 = r11.aDC     // Catch: java.lang.Throwable -> L43
            r10 = 1
            if (r12 == 0) goto L17
            int r12 = r11.aDD     // Catch: java.lang.Throwable -> L43
            int r12 = r12 - r10
            r11.aDD = r12     // Catch: java.lang.Throwable -> L43
            if (r12 == 0) goto L1f
            if (r0 != 0) goto L1f
        L17:
            boolean r12 = r11.aDC     // Catch: java.lang.Throwable -> L43
            if (r12 != 0) goto L41
            int r12 = r11.aDE     // Catch: java.lang.Throwable -> L43
            if (r12 != r10) goto L41
        L1f:
            com.google.android.gms.common.stats.zzg r1 = com.google.android.gms.common.stats.zzg.zzayg()     // Catch: java.lang.Throwable -> L43
            android.content.Context r2 = r11.mContext     // Catch: java.lang.Throwable -> L43
            android.os.PowerManager$WakeLock r12 = r11.aDz     // Catch: java.lang.Throwable -> L43
            java.lang.String r3 = com.google.android.gms.common.stats.zze.zza(r12, r6)     // Catch: java.lang.Throwable -> L43
            r4 = 8
            java.lang.String r5 = r11.Gc     // Catch: java.lang.Throwable -> L43
            java.lang.String r7 = r11.Ge     // Catch: java.lang.Throwable -> L43
            int r8 = r11.aDA     // Catch: java.lang.Throwable -> L43
            android.os.WorkSource r12 = r11.ajz     // Catch: java.lang.Throwable -> L43
            java.util.List r9 = com.google.android.gms.common.util.zzy.zzb(r12)     // Catch: java.lang.Throwable -> L43
            r1.zza(r2, r3, r4, r5, r6, r7, r8, r9)     // Catch: java.lang.Throwable -> L43
            int r12 = r11.aDE     // Catch: java.lang.Throwable -> L43
            int r12 = r12 - r10
            r11.aDE = r12     // Catch: java.lang.Throwable -> L43
        L41:
            monitor-exit(r11)     // Catch: java.lang.Throwable -> L43
            return
        L43:
            r12 = move-exception
            monitor-exit(r11)     // Catch: java.lang.Throwable -> L43
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzxr.zzon(java.lang.String):void");
    }

    private boolean zzoo(String str) {
        return (TextUtils.isEmpty(str) || str.equals(this.aDB)) ? false : true;
    }

    private String zzp(String str, boolean z) {
        return (this.aDC && z) ? str : this.aDB;
    }

    public void acquire(long j) {
        if (!com.google.android.gms.common.util.zzs.zzayq() && this.aDC) {
            String str = TAG;
            String valueOf = String.valueOf(this.Gc);
            Log.wtf(str, valueOf.length() != 0 ? "Do not acquire with timeout on reference counted WakeLocks before ICS. wakelock: ".concat(valueOf) : new String("Do not acquire with timeout on reference counted WakeLocks before ICS. wakelock: "));
        }
        zzk(null, j);
        this.aDz.acquire(j);
    }

    public boolean isHeld() {
        return this.aDz.isHeld();
    }

    public void release() {
        zzon(null);
        this.aDz.release();
    }

    public void setReferenceCounted(boolean z) {
        this.aDz.setReferenceCounted(z);
        this.aDC = z;
    }

    public void zzc(WorkSource workSource) {
        if (workSource == null || !com.google.android.gms.common.util.zzy.zzcm(this.mContext)) {
            return;
        }
        if (this.ajz != null) {
            this.ajz.add(workSource);
        } else {
            this.ajz = workSource;
        }
        zzd(this.ajz);
    }
}
