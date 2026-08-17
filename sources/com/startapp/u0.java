package com.startapp;

import com.liulishuo.filedownloader.model.FileDownloadStatus;
import java.nio.ByteBuffer;
import java.util.Random;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class u0 {
    private static final int a = 65507;
    public static final byte b = 8;
    public static final byte c = Byte.MIN_VALUE;
    private static final byte d = 0;
    public static final int e = 8;
    private final byte f;

    public u0(byte b2) {
        this.f = b2;
    }

    private static short a(byte[] bArr, int i) {
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3 += 2) {
            int i4 = i2 + ((bArr[i3] & FileDownloadStatus.error) << 8);
            i2 = (i4 >> 16) + (65535 & i4);
        }
        for (int i5 = 1; i5 < i; i5 += 2) {
            int i6 = i2 + (bArr[i5] & FileDownloadStatus.error);
            i2 = (i6 >> 16) + (i6 & 65535);
        }
        return (short) (((i2 & 65535) + (i2 >> 16)) ^ 65535);
    }

    public static byte[] a(int i) {
        byte[] bArr = new byte[i];
        new Random().nextBytes(bArr);
        return bArr;
    }

    public ByteBuffer a(short s, short s2, byte[] bArr) {
        if (bArr == null) {
            bArr = new byte[0];
        } else if (bArr.length > a) {
            bArr = a(a);
        }
        int length = bArr.length + 8;
        byte[] bArr2 = new byte[length];
        ByteBuffer wrap = ByteBuffer.wrap(bArr2);
        wrap.put(this.f);
        wrap.put((byte) 0);
        int position = wrap.position();
        wrap.position(position + 2);
        wrap.putShort(s2);
        wrap.putShort(s);
        wrap.put(bArr);
        wrap.putShort(position, a(bArr2, length));
        wrap.flip();
        return wrap;
    }
}
