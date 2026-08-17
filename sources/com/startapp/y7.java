package com.startapp;

import android.util.Pair;
import com.startapp.sdk.adsbase.SimpleTokenUtils;
import com.startapp.sdk.common.SDKException;
import java.util.Map;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class y7 extends j5 {
    public final p7 h0;
    public String i0;
    public String j0;
    public String k0;
    public String l0;
    public String m0;
    public String n0;
    public String o0;
    public Map<String, String> p0;

    public y7(p7 p7Var) {
        super(8);
        this.h0 = p7Var;
    }

    @Override // com.startapp.j5
    public void a(u9 u9Var) throws SDKException {
        super.a(u9Var);
        p7 p7Var = this.h0;
        Long l = p7Var.h;
        String l2 = l != null ? l.toString() : wa.a();
        u9Var.a(wa.b, (Object) l2, true, true);
        u9Var.a(wa.c, wa.a(l2), true, true);
        u9Var.a("category", p7Var.a.o, true, true);
        u9Var.a("value", p7Var.d, false, true);
        u9Var.a("d", p7Var.g, false, true);
        u9Var.a("appActivity", p7Var.i, false, true);
        u9Var.a("details", p7Var.e, false, true);
        u9Var.a("details_json", p7Var.f, false, true);
        u9Var.a("isService", Boolean.valueOf(p7Var.j), false, true);
        u9Var.a("orientation", this.i0, false, true);
        u9Var.a("usedRam", this.j0, false, true);
        u9Var.a("freeRam", this.k0, false, true);
        u9Var.a("sessionTime", (Object) null, false, true);
        u9Var.a("cellScanRes", this.l0, false, true);
        u9Var.a("sens", this.m0, false, true);
        u9Var.a("bt", this.n0, false, true);
        u9Var.a("packagingType", this.o0, false, true);
        Pair<String, String> a = SimpleTokenUtils.a();
        Pair pair = SimpleTokenUtils.e != null ? new Pair(((SimpleTokenUtils.TokenType) SimpleTokenUtils.e.first).toString(), SimpleTokenUtils.e.second) : new Pair(SimpleTokenUtils.TokenType.T2.toString(), "");
        u9Var.a((String) a.first, a.second, false, true);
        u9Var.a((String) pair.first, pair.second, false, true);
        Map<String, String> map = this.p0;
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                u9Var.a(entry.getKey(), entry.getValue(), false, true);
            }
        }
        u9Var.a("rcd", (Object) null, false, false);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(9:1|(8:5|(1:7)(1:(1:28))|8|9|10|(3:12|(1:14)|(1:16))|18|19)|29|8|9|10|(0)|18|19) */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0064, code lost:
    
        r11 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x006b, code lost:
    
        if (com.startapp.aa.a(r11, (java.lang.Class<? extends java.lang.Throwable>) java.lang.SecurityException.class) == false) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0076, code lost:
    
        com.startapp.p7.a(r10, r11);
     */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0031 A[Catch: all -> 0x0064, TryCatch #0 {all -> 0x0064, blocks: (B:10:0x0027, B:12:0x0031, B:14:0x004d, B:16:0x0055), top: B:9:0x0027 }] */
    @Override // com.startapp.j5
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void b(android.content.Context r10, com.startapp.sdk.adsbase.model.AdPreferences r11) {
        /*
            r9 = this;
            super.b(r10, r11)
            android.content.res.Resources r11 = r10.getResources()
            r0 = 1
            if (r11 == 0) goto L1d
            android.content.res.Configuration r11 = r11.getConfiguration()
            if (r11 == 0) goto L1d
            int r11 = r11.orientation
            r1 = 2
            if (r11 != r1) goto L18
            java.lang.String r11 = "landscape"
            goto L1f
        L18:
            if (r11 != r0) goto L1d
            java.lang.String r11 = "portrait"
            goto L1f
        L1d:
            java.lang.String r11 = "undefined"
        L1f:
            r9.i0 = r11
            r11 = 0
            java.lang.String[] r1 = new java.lang.String[]{r11, r11}
            r2 = 0
            java.lang.String r3 = "activity"
            java.lang.Object r3 = r10.getSystemService(r3)     // Catch: java.lang.Throwable -> L64
            android.app.ActivityManager r3 = (android.app.ActivityManager) r3     // Catch: java.lang.Throwable -> L64
            if (r3 == 0) goto L79
            android.app.ActivityManager$MemoryInfo r4 = new android.app.ActivityManager$MemoryInfo     // Catch: java.lang.Throwable -> L64
            r4.<init>()     // Catch: java.lang.Throwable -> L64
            r3.getMemoryInfo(r4)     // Catch: java.lang.Throwable -> L64
            long r5 = r4.availMem     // Catch: java.lang.Throwable -> L64
            r7 = 1048576(0x100000, double:5.180654E-318)
            long r5 = r5 / r7
            java.lang.String r3 = java.lang.Long.toString(r5)     // Catch: java.lang.Throwable -> L64
            r1[r2] = r3     // Catch: java.lang.Throwable -> L64
            int r3 = com.startapp.ya.a     // Catch: java.lang.Throwable -> L64
            int r3 = android.os.Build.VERSION.SDK_INT     // Catch: java.lang.Throwable -> L64
            r5 = 16
            if (r3 < r5) goto L53
            long r5 = r4.totalMem     // Catch: java.lang.Throwable -> L64
            java.lang.Long r11 = java.lang.Long.valueOf(r5)     // Catch: java.lang.Throwable -> L64
        L53:
            if (r11 == 0) goto L79
            long r5 = r11.longValue()     // Catch: java.lang.Throwable -> L64
            long r3 = r4.availMem     // Catch: java.lang.Throwable -> L64
            long r5 = r5 - r3
            long r5 = r5 / r7
            java.lang.String r11 = java.lang.Long.toString(r5)     // Catch: java.lang.Throwable -> L64
            r1[r0] = r11     // Catch: java.lang.Throwable -> L64
            goto L79
        L64:
            r11 = move-exception
            java.lang.Class<java.lang.SecurityException> r3 = java.lang.SecurityException.class
            boolean r3 = com.startapp.aa.a(r11, r3)
            if (r3 != 0) goto L79
            java.lang.Class<android.os.RemoteException> r3 = android.os.RemoteException.class
            boolean r3 = com.startapp.aa.a(r11, r3)
            if (r3 == 0) goto L76
            goto L79
        L76:
            com.startapp.p7.a(r10, r11)
        L79:
            r10 = r1[r2]
            r9.k0 = r10
            r10 = r1[r0]
            r9.j0 = r10
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.startapp.y7.b(android.content.Context, com.startapp.sdk.adsbase.model.AdPreferences):void");
    }

    public String toString() {
        return this.h0.toString();
    }
}
