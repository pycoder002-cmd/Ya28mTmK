package com.startapp;

import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class a0 {
    private static final String a = "a0";
    private static final String b = "SHA-256";
    private static final int c = 8192;

    public static byte[] a(File file) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(b);
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] bArr = new byte[8192];
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read <= 1) {
                    fileInputStream.close();
                    return messageDigest.digest();
                }
                messageDigest.update(bArr, 0, read);
            }
        } catch (Throwable th) {
            h1.a(th);
            return null;
        }
    }

    public static byte[] a(byte[] bArr) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(b);
            messageDigest.update(bArr);
            return messageDigest.digest();
        } catch (Throwable th) {
            h1.a(th);
            return null;
        }
    }
}
