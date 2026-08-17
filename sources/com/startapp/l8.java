package com.startapp;

import android.net.Uri;
import android.os.Build;
import android.os.SystemClock;
import com.startapp.sdk.adsbase.remoteconfig.NetworkDiagnosticConfig;
import com.startapp.sdk.common.SDKException;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class l8 {
    public final i8 a;
    public final long b = a();

    public l8(i8 i8Var) {
        this.a = i8Var;
    }

    public static long a() {
        return Build.VERSION.SDK_INT < 17 ? SystemClock.elapsedRealtime() * 1000000 : SystemClock.elapsedRealtimeNanos();
    }

    public void a(String str, String str2, SDKException sDKException) {
        String str3;
        int i;
        long elapsedRealtime = Build.VERSION.SDK_INT < 17 ? SystemClock.elapsedRealtime() * 1000000 : SystemClock.elapsedRealtimeNanos();
        i8 i8Var = this.a;
        long j = elapsedRealtime - this.b;
        NetworkDiagnosticConfig a = i8Var.a();
        if (a == null) {
            return;
        }
        if (sDKException == null) {
            str3 = "Success";
            i = 4;
        } else if (sDKException.getCause() != null) {
            str3 = "Failure: " + sDKException.getCause().getClass().getName();
            i = 2;
        } else {
            str3 = "Error: " + sDKException.a();
            i = 1;
        }
        if ((a.d() & i) != 0) {
            Uri b = sDKException != null ? sDKException.b() : null;
            if (b == null) {
                b = Uri.parse(str2).buildUpon().query(null).build();
            }
            i8Var.d.execute(new j8(i8Var, str + ' ' + b, str3, j));
        }
        if (i == 4) {
            i8Var.d.execute(i8Var.f);
        }
    }
}
