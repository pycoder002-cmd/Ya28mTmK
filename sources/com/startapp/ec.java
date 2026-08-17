package com.startapp;

import android.os.Build;
import android.telephony.TelephonyManager;
import com.startapp.fc;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class ec implements Runnable {
    public final /* synthetic */ fc a;

    public ec(fc fcVar) {
        this.a = fcVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        TelephonyManager telephonyManager;
        fc fcVar = this.a;
        fcVar.getClass();
        try {
            if (fcVar.a() == null || (telephonyManager = (TelephonyManager) fcVar.a.getSystemService("phone")) == null) {
                return;
            }
            if (Build.VERSION.SDK_INT < 31) {
                fcVar.e = new fc.d();
            } else {
                fcVar.e = new fc.a();
            }
            fcVar.e.a(telephonyManager);
        } catch (Throwable th) {
            if (fcVar.a(1)) {
                p7.a(fcVar.a, th);
            }
        }
    }
}
