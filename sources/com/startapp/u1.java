package com.startapp;

import com.startapp.networkTest.data.TimeInfo;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class u1 {
    public static String a(TimeInfo timeInfo, String str) {
        byte[] bArr;
        if (timeInfo == null || str == null || str.length() == 0) {
            return null;
        }
        try {
            bArr = a0.a((str + timeInfo.TimestampMillis).getBytes("UTF-8"));
        } catch (Throwable th) {
            h1.a(th);
            bArr = null;
        }
        if (bArr == null) {
            return null;
        }
        return r1.a(bArr);
    }
}
