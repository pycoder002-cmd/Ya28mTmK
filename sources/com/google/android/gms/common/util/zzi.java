package com.google.android.gms.common.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;

/* loaded from: classes.dex */
public final class zzi {
    private static Boolean Gr;
    private static Boolean Gs;
    private static Boolean Gt;
    private static Boolean Gu;

    public static boolean zzaym() {
        boolean z = com.google.android.gms.common.zze.xb;
        return "user".equals(Build.TYPE);
    }

    public static boolean zzb(Resources resources) {
        if (resources == null) {
            return false;
        }
        if (Gr == null) {
            Gr = Boolean.valueOf((zzs.zzayn() && ((resources.getConfiguration().screenLayout & 15) > 3)) || zzc(resources));
        }
        return Gr.booleanValue();
    }

    @TargetApi(13)
    private static boolean zzc(Resources resources) {
        if (Gs == null) {
            Configuration configuration = resources.getConfiguration();
            Gs = Boolean.valueOf(zzs.zzayp() && (configuration.screenLayout & 15) <= 3 && configuration.smallestScreenWidthDp >= 600);
        }
        return Gs.booleanValue();
    }

    @TargetApi(20)
    public static boolean zzci(Context context) {
        if (Gt == null) {
            Gt = Boolean.valueOf(zzs.zzayv() && context.getPackageManager().hasSystemFeature("android.hardware.type.watch"));
        }
        return Gt.booleanValue();
    }

    @TargetApi(21)
    public static boolean zzcj(Context context) {
        if (Gu == null) {
            Gu = Boolean.valueOf(zzs.zzayx() && context.getPackageManager().hasSystemFeature("cn.google"));
        }
        return Gu.booleanValue();
    }
}
