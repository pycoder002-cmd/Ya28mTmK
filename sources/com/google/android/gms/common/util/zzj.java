package com.google.android.gms.common.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.PowerManager;
import android.os.SystemClock;
import io.sentry.DefaultSentryClientFactory;
import io.sentry.marshaller.json.JsonMarshaller;

/* loaded from: classes.dex */
public final class zzj {
    private static IntentFilter Gv = new IntentFilter("android.intent.action.BATTERY_CHANGED");
    private static long Gw = 0;
    private static float Gx = Float.NaN;

    @TargetApi(20)
    public static boolean zzb(PowerManager powerManager) {
        return zzs.zzayv() ? powerManager.isInteractive() : powerManager.isScreenOn();
    }

    @TargetApi(20)
    public static int zzck(Context context) {
        if (context == null || context.getApplicationContext() == null) {
            return -1;
        }
        Intent registerReceiver = context.getApplicationContext().registerReceiver(null, Gv);
        int i = ((registerReceiver == null ? 0 : registerReceiver.getIntExtra("plugged", 0)) & 7) != 0 ? 1 : 0;
        PowerManager powerManager = (PowerManager) context.getSystemService("power");
        if (powerManager == null) {
            return -1;
        }
        return ((zzb(powerManager) ? 1 : 0) << 1) | i;
    }

    public static synchronized float zzcl(Context context) {
        synchronized (zzj.class) {
            if (SystemClock.elapsedRealtime() - Gw < DefaultSentryClientFactory.BUFFER_FLUSHTIME_DEFAULT && !Float.isNaN(Gx)) {
                return Gx;
            }
            if (context.getApplicationContext().registerReceiver(null, Gv) != null) {
                Gx = r7.getIntExtra(JsonMarshaller.LEVEL, -1) / r7.getIntExtra("scale", -1);
            }
            Gw = SystemClock.elapsedRealtime();
            return Gx;
        }
    }
}
