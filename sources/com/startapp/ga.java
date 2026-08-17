package com.startapp;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.res.Resources;
import android.os.Build;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class ga {
    public static final String a = aa.a(71, 13, -10, 14, -3, -6, -5, -54, 66, -11, 13, -5, -4, 10, 0, -10, 6, -1, -64, 19, 2, 0, 2, 14, 0, 12);
    public static final String b = aa.a(66, 3, 5, -9);
    public static final String c = aa.a(61, 12, -14, 17, 1, -14);
    public static final String d = aa.a(56, -1, 2, 8, -4, 11, -3, 6, -7, -10);
    public static final String e = aa.a(86, -19, 3, -12, -2, 19, -11, 6, -1);
    public final Context f;
    public final AtomicInteger g = new AtomicInteger(0);

    public ga(Context context) {
        this.f = context;
    }

    public String a() {
        Context context = this.f;
        StringBuilder sb = new StringBuilder();
        String str = a;
        sb.append(str);
        sb.append(c);
        String str2 = e;
        sb.append(str2);
        String[] strArr = {sb.toString(), str + b + str2, str + d + str2};
        int[] iArr = new int[3];
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 4096);
            String[] strArr2 = packageInfo.requestedPermissions;
            if (strArr2 != null) {
                int length = strArr2.length;
                for (int i = 0; i < length; i++) {
                    for (int i2 = 0; i2 < 3; i2++) {
                        if (strArr[i2].equals(packageInfo.requestedPermissions[i])) {
                            if (Build.VERSION.SDK_INT < 16) {
                                iArr[i2] = 2;
                            } else if ((packageInfo.requestedPermissionsFlags[i] & 2) == 2) {
                                iArr[i2] = 2;
                            } else {
                                iArr[i2] = 1;
                            }
                        }
                    }
                }
            }
        } catch (Throwable unused) {
        }
        StringBuilder sb2 = new StringBuilder(3);
        for (int i3 = 0; i3 < 3; i3++) {
            sb2.append(iArr[i3]);
        }
        return sb2.toString();
    }

    public void a(int i, boolean z) {
        Locale locale = Locale.ENGLISH;
        Object[] objArr = new Object[4];
        int i2 = 0;
        objArr[0] = Integer.valueOf(this.g.incrementAndGet());
        Context context = this.f;
        try {
            Resources resources = context.getResources();
            int identifier = resources.getIdentifier("com_startapp_sdk_aar", "integer", context.getPackageName());
            if (identifier != 0) {
                i2 = resources.getInteger(identifier);
            }
        } catch (Throwable unused) {
        }
        objArr[1] = Integer.valueOf(i2);
        objArr[2] = Integer.valueOf(i);
        objArr[3] = Integer.valueOf(z ? 1 : 0);
        String format = String.format(locale, "cnt=%d,aar=%d,mds=%d,ibt=%d", objArr);
        p7 p7Var = new p7(q7.b);
        p7Var.d = "initialize";
        p7Var.e = format;
        p7Var.a(this.f);
    }
}
