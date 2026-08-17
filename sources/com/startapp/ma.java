package com.startapp;

import android.content.Context;
import android.os.Looper;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class ma {
    public static volatile ma a;
    public volatile PhoneStateListener b = null;
    public volatile String c = "e106";

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public static class a implements Runnable {
        public final /* synthetic */ Context a;

        public a(Context context) {
            this.a = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            Looper.prepare();
            try {
                ma maVar = ma.a;
                Context context = this.a;
                maVar.getClass();
                maVar.b = new na(maVar);
                ((TelephonyManager) context.getSystemService("phone")).listen(maVar.b, 256);
            } catch (Throwable unused) {
                ma.a.c = "e107";
            }
            Looper.loop();
        }
    }

    public static synchronized void a(Context context) {
        synchronized (ma.class) {
            if (a == null) {
                a = new ma();
                new Thread(new a(context)).start();
            }
        }
    }
}
