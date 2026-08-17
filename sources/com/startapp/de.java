package com.startapp;

import com.startapp.simple.bloomfilter.algo.OpenBitSet;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class de {
    public final ee a = new ee();

    public String a(OpenBitSet openBitSet) {
        ByteArrayOutputStream byteArrayOutputStream;
        int a = openBitSet.a();
        int b = openBitSet.b();
        ByteArrayOutputStream byteArrayOutputStream2 = null;
        try {
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
            } catch (Exception e) {
                e = e;
            }
        } catch (Throwable th) {
            th = th;
        }
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            for (int i = 0; i < b; i++) {
                long[] a2 = openBitSet.a(i);
                int i2 = 0;
                while (true) {
                    if (i2 < 4096) {
                        int i3 = a - 1;
                        if (a <= 0) {
                            a = i3;
                            break;
                        }
                        dataOutputStream.writeLong(a2[i2]);
                        i2++;
                        a = i3;
                    }
                }
            }
            try {
                byteArrayOutputStream.close();
            } catch (IOException unused) {
            }
            ee eeVar = this.a;
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            eeVar.getClass();
            char[] cArr = new char[byteArray.length * 2];
            for (int i4 = 0; i4 < byteArray.length; i4++) {
                int i5 = i4 * 2;
                char[] cArr2 = ee.a;
                cArr[i5] = cArr2[(byteArray[i4] & 240) >>> 4];
                cArr[i5 + 1] = cArr2[byteArray[i4] & 15];
            }
            return new String(cArr);
        } catch (Exception e2) {
            e = e2;
            byteArrayOutputStream2 = byteArrayOutputStream;
            throw new RuntimeException("problem serializing bitSet", e);
        } catch (Throwable th2) {
            th = th2;
            byteArrayOutputStream2 = byteArrayOutputStream;
            if (byteArrayOutputStream2 != null) {
                try {
                    byteArrayOutputStream2.close();
                } catch (IOException unused2) {
                }
            }
            throw th;
        }
    }
}
