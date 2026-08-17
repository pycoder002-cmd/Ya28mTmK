package com.startapp;

import android.os.Build;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import java.lang.reflect.Method;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class na extends PhoneStateListener {
    public final /* synthetic */ ma a;

    public na(ma maVar) {
        this.a = maVar;
    }

    @Override // android.telephony.PhoneStateListener
    public void onSignalStrengthsChanged(SignalStrength signalStrength) {
        try {
            if (Build.VERSION.SDK_INT >= 23) {
                this.a.c = String.valueOf(signalStrength.getLevel());
            } else {
                try {
                    Method method = SignalStrength.class.getMethod("getLevel", new Class[0]);
                    this.a.c = String.valueOf(method.invoke(signalStrength, new Object[0]));
                } catch (NoSuchMethodException unused) {
                    this.a.c = "e104";
                }
            }
        } catch (Exception unused2) {
            this.a.c = "e105";
        }
    }
}
