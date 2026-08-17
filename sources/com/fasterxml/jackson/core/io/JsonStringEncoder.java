package com.fasterxml.jackson.core.io;

import com.fasterxml.jackson.core.util.BufferRecycler;
import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import com.fasterxml.jackson.core.util.TextBuffer;
import java.lang.ref.SoftReference;

/* loaded from: classes.dex */
public final class JsonStringEncoder {
    private static final int SURR1_FIRST = 55296;
    private static final int SURR1_LAST = 56319;
    private static final int SURR2_FIRST = 56320;
    private static final int SURR2_LAST = 57343;
    protected ByteArrayBuilder _bytes;
    protected final char[] _qbuf = new char[6];
    protected TextBuffer _text;
    private static final char[] HC = CharTypes.copyHexChars();
    private static final byte[] HB = CharTypes.copyHexBytes();
    protected static final ThreadLocal<SoftReference<JsonStringEncoder>> _threadEncoder = new ThreadLocal<>();

    public JsonStringEncoder() {
        this._qbuf[0] = '\\';
        this._qbuf[2] = '0';
        this._qbuf[3] = '0';
    }

    private int _appendByte(int i, int i2, ByteArrayBuilder byteArrayBuilder, int i3) {
        byteArrayBuilder.setCurrentSegmentLength(i3);
        byteArrayBuilder.append(92);
        if (i2 < 0) {
            byteArrayBuilder.append(117);
            if (i > 255) {
                int i4 = i >> 8;
                byteArrayBuilder.append(HB[i4 >> 4]);
                byteArrayBuilder.append(HB[i4 & 15]);
                i &= 255;
            } else {
                byteArrayBuilder.append(48);
                byteArrayBuilder.append(48);
            }
            byteArrayBuilder.append(HB[i >> 4]);
            byteArrayBuilder.append(HB[i & 15]);
        } else {
            byteArrayBuilder.append((byte) i2);
        }
        return byteArrayBuilder.getCurrentSegmentLength();
    }

    private int _appendNamed(int i, char[] cArr) {
        cArr[1] = (char) i;
        return 2;
    }

    private int _appendNumeric(int i, char[] cArr) {
        cArr[1] = 'u';
        cArr[4] = HC[i >> 4];
        cArr[5] = HC[i & 15];
        return 6;
    }

    private static int _convert(int i, int i2) {
        if (i2 >= 56320 && i2 <= 57343) {
            return 65536 + ((i - 55296) << 10) + (i2 - 56320);
        }
        throw new IllegalArgumentException("Broken surrogate pair: first char 0x" + Integer.toHexString(i) + ", second 0x" + Integer.toHexString(i2) + "; illegal combination");
    }

    private static void _illegal(int i) {
        throw new IllegalArgumentException(UTF8Writer.illegalSurrogateDesc(i));
    }

    public static JsonStringEncoder getInstance() {
        SoftReference<JsonStringEncoder> softReference = _threadEncoder.get();
        JsonStringEncoder jsonStringEncoder = softReference == null ? null : softReference.get();
        if (jsonStringEncoder != null) {
            return jsonStringEncoder;
        }
        JsonStringEncoder jsonStringEncoder2 = new JsonStringEncoder();
        _threadEncoder.set(new SoftReference<>(jsonStringEncoder2));
        return jsonStringEncoder2;
    }

    public byte[] encodeAsUTF8(String str) {
        int i;
        ByteArrayBuilder byteArrayBuilder = this._bytes;
        if (byteArrayBuilder == null) {
            byteArrayBuilder = new ByteArrayBuilder((BufferRecycler) null);
            this._bytes = byteArrayBuilder;
        }
        int length = str.length();
        byte[] resetAndGetFirstSegment = byteArrayBuilder.resetAndGetFirstSegment();
        int length2 = resetAndGetFirstSegment.length;
        byte[] bArr = resetAndGetFirstSegment;
        int i2 = 0;
        int i3 = length2;
        int i4 = 0;
        loop0: while (true) {
            if (i2 >= length) {
                break;
            }
            int i5 = i2 + 1;
            int charAt = str.charAt(i2);
            while (charAt <= 127) {
                if (i4 >= i3) {
                    byte[] finishCurrentSegment = byteArrayBuilder.finishCurrentSegment();
                    i3 = finishCurrentSegment.length;
                    bArr = finishCurrentSegment;
                    i4 = 0;
                }
                int i6 = i4 + 1;
                bArr[i4] = (byte) charAt;
                if (i5 >= length) {
                    i4 = i6;
                    break loop0;
                }
                char charAt2 = str.charAt(i5);
                i5++;
                charAt = charAt2;
                i4 = i6;
            }
            if (i4 >= i3) {
                bArr = byteArrayBuilder.finishCurrentSegment();
                i3 = bArr.length;
                i4 = 0;
            }
            if (charAt < 2048) {
                bArr[i4] = (byte) (192 | (charAt >> 6));
                i = i4 + 1;
            } else if (charAt < 55296 || charAt > 57343) {
                int i7 = i4 + 1;
                bArr[i4] = (byte) (224 | (charAt >> 12));
                if (i7 >= i3) {
                    bArr = byteArrayBuilder.finishCurrentSegment();
                    i7 = 0;
                    i3 = bArr.length;
                }
                i = i7 + 1;
                bArr[i7] = (byte) (((charAt >> 6) & 63) | 128);
            } else {
                if (charAt > 56319) {
                    _illegal(charAt);
                }
                if (i5 >= length) {
                    _illegal(charAt);
                }
                int i8 = i5 + 1;
                charAt = _convert(charAt, str.charAt(i5));
                if (charAt > 1114111) {
                    _illegal(charAt);
                }
                int i9 = i4 + 1;
                bArr[i4] = (byte) (240 | (charAt >> 18));
                if (i9 >= i3) {
                    bArr = byteArrayBuilder.finishCurrentSegment();
                    i3 = bArr.length;
                    i9 = 0;
                }
                int i10 = i9 + 1;
                bArr[i9] = (byte) (((charAt >> 12) & 63) | 128);
                if (i10 >= i3) {
                    byte[] finishCurrentSegment2 = byteArrayBuilder.finishCurrentSegment();
                    i3 = finishCurrentSegment2.length;
                    bArr = finishCurrentSegment2;
                    i10 = 0;
                }
                bArr[i10] = (byte) (((charAt >> 6) & 63) | 128);
                i = i10 + 1;
                i5 = i8;
            }
            if (i >= i3) {
                byte[] finishCurrentSegment3 = byteArrayBuilder.finishCurrentSegment();
                i3 = finishCurrentSegment3.length;
                bArr = finishCurrentSegment3;
                i = 0;
            }
            bArr[i] = (byte) ((charAt & 63) | 128);
            i2 = i5;
            i4 = i + 1;
        }
        return this._bytes.completeAndCoalesce(i4);
    }

    public void quoteAsString(CharSequence charSequence, StringBuilder sb) {
        int[] iArr = CharTypes.get7BitOutputEscapes();
        int length = iArr.length;
        int length2 = charSequence.length();
        int i = 0;
        while (i < length2) {
            do {
                char charAt = charSequence.charAt(i);
                if (charAt >= length || iArr[charAt] == 0) {
                    sb.append(charAt);
                    i++;
                } else {
                    int i2 = i + 1;
                    char charAt2 = charSequence.charAt(i);
                    int i3 = iArr[charAt2];
                    sb.append(this._qbuf, 0, i3 < 0 ? _appendNumeric(charAt2, this._qbuf) : _appendNamed(i3, this._qbuf));
                    i = i2;
                }
            } while (i < length2);
            return;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0029, code lost:
    
        r8 = r1 + 1;
        r1 = r12.charAt(r1);
        r9 = r2[r1];
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0031, code lost:
    
        if (r9 >= 0) goto L14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0033, code lost:
    
        r1 = _appendNumeric(r1, r11._qbuf);
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0040, code lost:
    
        r9 = r6 + r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0043, code lost:
    
        if (r9 <= r7.length) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0045, code lost:
    
        r9 = r7.length - r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0047, code lost:
    
        if (r9 <= 0) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0049, code lost:
    
        java.lang.System.arraycopy(r11._qbuf, 0, r7, r6, r9);
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x004e, code lost:
    
        r6 = r0.finishCurrentSegment();
        r1 = r1 - r9;
        java.lang.System.arraycopy(r11._qbuf, r9, r6, 0, r1);
        r7 = r6;
        r6 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x005b, code lost:
    
        java.lang.System.arraycopy(r11._qbuf, 0, r7, r6, r1);
        r6 = r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x003a, code lost:
    
        r1 = _appendNamed(r9, r11._qbuf);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public char[] quoteAsString(java.lang.String r12) {
        /*
            r11 = this;
            com.fasterxml.jackson.core.util.TextBuffer r0 = r11._text
            if (r0 != 0) goto Lc
            com.fasterxml.jackson.core.util.TextBuffer r0 = new com.fasterxml.jackson.core.util.TextBuffer
            r1 = 0
            r0.<init>(r1)
            r11._text = r0
        Lc:
            char[] r1 = r0.emptyAndGetCurrentSegment()
            int[] r2 = com.fasterxml.jackson.core.io.CharTypes.get7BitOutputEscapes()
            r3 = 0
            int r4 = r2.length
            int r5 = r12.length()
            r7 = r1
            r1 = r3
            r6 = r1
        L1d:
            if (r1 >= r5) goto L78
        L1f:
            char r8 = r12.charAt(r1)
            if (r8 >= r4) goto L63
            r9 = r2[r8]
            if (r9 == 0) goto L63
            int r8 = r1 + 1
            char r1 = r12.charAt(r1)
            r9 = r2[r1]
            if (r9 >= 0) goto L3a
            char[] r9 = r11._qbuf
            int r1 = r11._appendNumeric(r1, r9)
            goto L40
        L3a:
            char[] r1 = r11._qbuf
            int r1 = r11._appendNamed(r9, r1)
        L40:
            int r9 = r6 + r1
            int r10 = r7.length
            if (r9 <= r10) goto L5b
            int r9 = r7.length
            int r9 = r9 - r6
            if (r9 <= 0) goto L4e
            char[] r10 = r11._qbuf
            java.lang.System.arraycopy(r10, r3, r7, r6, r9)
        L4e:
            char[] r6 = r0.finishCurrentSegment()
            int r1 = r1 - r9
            char[] r7 = r11._qbuf
            java.lang.System.arraycopy(r7, r9, r6, r3, r1)
            r7 = r6
            r6 = r1
            goto L61
        L5b:
            char[] r10 = r11._qbuf
            java.lang.System.arraycopy(r10, r3, r7, r6, r1)
            r6 = r9
        L61:
            r1 = r8
            goto L1d
        L63:
            int r9 = r7.length
            if (r6 < r9) goto L6c
            char[] r6 = r0.finishCurrentSegment()
            r7 = r6
            r6 = r3
        L6c:
            int r9 = r6 + 1
            r7[r6] = r8
            int r1 = r1 + 1
            if (r1 < r5) goto L76
            r6 = r9
            goto L78
        L76:
            r6 = r9
            goto L1f
        L78:
            r0.setCurrentLength(r6)
            char[] r12 = r0.contentsAsArray()
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.io.JsonStringEncoder.quoteAsString(java.lang.String):char[]");
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x0043, code lost:
    
        if (r4 < r5.length) goto L23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0045, code lost:
    
        r5 = r0.finishCurrentSegment();
        r4 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x004a, code lost:
    
        r7 = r2 + 1;
        r2 = r12.charAt(r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0050, code lost:
    
        if (r2 > 127) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0052, code lost:
    
        r4 = _appendByte(r2, r6[r2], r0, r4);
        r5 = r0.getCurrentSegment();
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0062, code lost:
    
        if (r2 > 2047) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0064, code lost:
    
        r5[r4] = (byte) (192 | (r2 >> 6));
        r2 = (r2 & '?') | 128;
        r4 = r4 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00f3, code lost:
    
        if (r4 < r5.length) goto L57;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00f5, code lost:
    
        r5 = r0.finishCurrentSegment();
        r4 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00fb, code lost:
    
        r5[r4] = (byte) r2;
        r4 = r4 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0077, code lost:
    
        if (r2 < 55296) goto L50;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x007c, code lost:
    
        if (r2 <= 57343) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0082, code lost:
    
        if (r2 <= 56319) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0084, code lost:
    
        _illegal(r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x0087, code lost:
    
        if (r7 < r1) goto L40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x0089, code lost:
    
        _illegal(r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x008c, code lost:
    
        r6 = r7 + 1;
        r2 = _convert(r2, r12.charAt(r7));
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0099, code lost:
    
        if (r2 <= 1114111) goto L43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x009b, code lost:
    
        _illegal(r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x009e, code lost:
    
        r7 = r4 + 1;
        r5[r4] = (byte) (240 | (r2 >> 18));
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x00a9, code lost:
    
        if (r7 < r5.length) goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x00ab, code lost:
    
        r5 = r0.finishCurrentSegment();
        r7 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x00b0, code lost:
    
        r4 = r7 + 1;
        r5[r7] = (byte) (((r2 >> 12) & 63) | 128);
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x00bb, code lost:
    
        if (r4 < r5.length) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x00bd, code lost:
    
        r5 = r0.finishCurrentSegment();
        r4 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x00c3, code lost:
    
        r5[r4] = (byte) (((r2 >> 6) & 63) | 128);
        r2 = (r2 & 63) | 128;
        r4 = r4 + 1;
        r7 = r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x00d3, code lost:
    
        r6 = r4 + 1;
        r5[r4] = (byte) (224 | (r2 >> '\f'));
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x00de, code lost:
    
        if (r6 < r5.length) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x00e0, code lost:
    
        r5 = r0.finishCurrentSegment();
        r6 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x00e5, code lost:
    
        r4 = r6 + 1;
        r5[r6] = (byte) (((r2 >> 6) & 63) | 128);
        r2 = (r2 & '?') | 128;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public byte[] quoteAsUTF8(java.lang.String r12) {
        /*
            Method dump skipped, instructions count: 266
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.io.JsonStringEncoder.quoteAsUTF8(java.lang.String):byte[]");
    }
}
