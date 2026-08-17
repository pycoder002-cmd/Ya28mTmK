package com.startapp;

import com.liulishuo.filedownloader.model.FileDownloadStatus;
import com.startapp.networkTest.enums.ThreeStateShort;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class r1 {
    private static final char[] a = "0123456789abcdef".toCharArray();

    public static int a(ThreeStateShort threeStateShort) {
        int ordinal = threeStateShort.ordinal();
        if (ordinal != 0) {
            return ordinal != 1 ? -1 : 0;
        }
        return 1;
    }

    public static String a(byte[] bArr) {
        char[] cArr = new char[bArr.length * 2];
        for (int i = 0; i < bArr.length; i++) {
            int i2 = bArr[i] & FileDownloadStatus.error;
            int i3 = i * 2;
            char[] cArr2 = a;
            cArr[i3] = cArr2[i2 >>> 4];
            cArr[i3 + 1] = cArr2[i2 & 15];
        }
        return new String(cArr);
    }

    public static byte[] a(String str) {
        int length = str.length() / 2;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i += 2) {
            bArr[i / 2] = (byte) ((Character.digit(str.charAt(i), 16) << 4) + Character.digit(str.charAt(i + 1), 16));
        }
        return bArr;
    }
}
