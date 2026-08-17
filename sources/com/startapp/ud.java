package com.startapp;

import com.liulishuo.filedownloader.model.FileDownloadStatus;
import com.startapp.vd;
import java.nio.charset.Charset;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class ud extends vd {
    public static final byte[] e = {13, 10};
    public static final byte[] f = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47};
    public static final byte[] g = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 45, 95};
    public static final byte[] h = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, 62, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, FileDownloadStatus.toFileDownloadService, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, 63, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51};
    public final byte[] i;
    public final byte[] j;
    public final int k;

    public ud(int i, byte[] bArr, boolean z) {
        super(3, 4, i, bArr.length);
        if (a(bArr)) {
            throw new IllegalArgumentException("lineSeparator must not contain base64 characters: [" + d.a(bArr) + "]");
        }
        if (i > 0) {
            this.k = bArr.length + 4;
            byte[] bArr2 = new byte[bArr.length];
            this.j = bArr2;
            System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        } else {
            this.k = 4;
            this.j = null;
        }
        this.i = z ? g : f;
    }

    public static String b(byte[] bArr) {
        if (bArr != null && bArr.length != 0) {
            ud udVar = new ud(0, e, false);
            long length = (((bArr.length + 3) - 1) / 3) * 4;
            int i = udVar.c;
            if (i > 0) {
                long j = i;
                length += (((length + j) - 1) / j) * udVar.d;
            }
            if (length > Integer.MAX_VALUE) {
                throw new IllegalArgumentException("Input array too big, the output array would be bigger (" + length + ") than the specified maximum size of 2147483647");
            }
            if (bArr.length != 0) {
                vd.a aVar = new vd.a();
                udVar.a(bArr, 0, bArr.length, aVar);
                udVar.a(bArr, 0, -1, aVar);
                int i2 = aVar.c - aVar.d;
                byte[] bArr2 = new byte[i2];
                if (aVar.b != null) {
                    int min = Math.min(i2, i2);
                    System.arraycopy(aVar.b, aVar.d, bArr2, 0, min);
                    int i3 = aVar.d + min;
                    aVar.d = i3;
                    if (i3 >= aVar.c) {
                        aVar.b = null;
                    }
                }
                bArr = bArr2;
            }
        }
        Charset charset = wd.a;
        if (bArr == null) {
            return null;
        }
        return new String(bArr, charset);
    }

    public void a(byte[] bArr, int i, int i2, vd.a aVar) {
        if (aVar.e) {
            return;
        }
        if (i2 >= 0) {
            int i3 = 0;
            while (i3 < i2) {
                byte[] a = a(this.k, aVar);
                int i4 = (aVar.g + 1) % 3;
                aVar.g = i4;
                int i5 = i + 1;
                int i6 = bArr[i];
                if (i6 < 0) {
                    i6 += 256;
                }
                int i7 = (aVar.a << 8) + i6;
                aVar.a = i7;
                if (i4 == 0) {
                    int i8 = aVar.c;
                    int i9 = i8 + 1;
                    aVar.c = i9;
                    byte[] bArr2 = this.i;
                    a[i8] = bArr2[(i7 >> 18) & 63];
                    int i10 = i9 + 1;
                    aVar.c = i10;
                    a[i9] = bArr2[(i7 >> 12) & 63];
                    int i11 = i10 + 1;
                    aVar.c = i11;
                    a[i10] = bArr2[(i7 >> 6) & 63];
                    int i12 = i11 + 1;
                    aVar.c = i12;
                    a[i11] = bArr2[i7 & 63];
                    int i13 = aVar.f + 4;
                    aVar.f = i13;
                    int i14 = this.c;
                    if (i14 > 0 && i14 <= i13) {
                        byte[] bArr3 = this.j;
                        System.arraycopy(bArr3, 0, a, i12, bArr3.length);
                        aVar.c += this.j.length;
                        aVar.f = 0;
                    }
                }
                i3++;
                i = i5;
            }
            return;
        }
        aVar.e = true;
        if (aVar.g == 0 && this.c == 0) {
            return;
        }
        byte[] a2 = a(this.k, aVar);
        int i15 = aVar.c;
        int i16 = aVar.g;
        if (i16 != 0) {
            if (i16 == 1) {
                int i17 = i15 + 1;
                aVar.c = i17;
                byte[] bArr4 = this.i;
                int i18 = aVar.a;
                a2[i15] = bArr4[(i18 >> 2) & 63];
                int i19 = i17 + 1;
                aVar.c = i19;
                a2[i17] = bArr4[(i18 << 4) & 63];
                if (bArr4 == f) {
                    int i20 = i19 + 1;
                    aVar.c = i20;
                    a2[i19] = 61;
                    aVar.c = i20 + 1;
                    a2[i20] = 61;
                }
            } else {
                if (i16 != 2) {
                    throw new IllegalStateException("Impossible modulus " + aVar.g);
                }
                int i21 = i15 + 1;
                aVar.c = i21;
                byte[] bArr5 = this.i;
                int i22 = aVar.a;
                a2[i15] = bArr5[(i22 >> 10) & 63];
                int i23 = i21 + 1;
                aVar.c = i23;
                a2[i21] = bArr5[(i22 >> 4) & 63];
                int i24 = i23 + 1;
                aVar.c = i24;
                a2[i23] = bArr5[(i22 << 2) & 63];
                if (bArr5 == f) {
                    aVar.c = i24 + 1;
                    a2[i24] = 61;
                }
            }
        }
        int i25 = aVar.f;
        int i26 = aVar.c;
        int i27 = i25 + (i26 - i15);
        aVar.f = i27;
        if (this.c <= 0 || i27 <= 0) {
            return;
        }
        byte[] bArr6 = this.j;
        System.arraycopy(bArr6, 0, a2, i26, bArr6.length);
        aVar.c += this.j.length;
    }
}
