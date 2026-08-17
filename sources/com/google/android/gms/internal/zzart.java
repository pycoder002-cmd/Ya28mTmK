package com.google.android.gms.internal;

import java.io.IOException;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ReadOnlyBufferException;
import org.jacoco.agent.rt.internal_b0d6a23.asm.Opcodes;

/* loaded from: classes.dex */
public final class zzart {
    private final ByteBuffer btF;

    /* loaded from: classes.dex */
    public static class zza extends IOException {
        /* JADX WARN: Illegal instructions before constructor call */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        zza(int r3, int r4) {
            /*
                r2 = this;
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                r1 = 108(0x6c, float:1.51E-43)
                r0.<init>(r1)
                java.lang.String r1 = "CodedOutputStream was writing to a flat byte array and ran out of space (pos "
                r0.append(r1)
                r0.append(r3)
                java.lang.String r3 = " limit "
                r0.append(r3)
                r0.append(r4)
                java.lang.String r3 = ")."
                r0.append(r3)
                java.lang.String r3 = r0.toString()
                r2.<init>(r3)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzart.zza.<init>(int, int):void");
        }
    }

    private zzart(ByteBuffer byteBuffer) {
        this.btF = byteBuffer;
        this.btF.order(ByteOrder.LITTLE_ENDIAN);
    }

    private zzart(byte[] bArr, int i, int i2) {
        this(ByteBuffer.wrap(bArr, i, i2));
    }

    private static int zza(CharSequence charSequence, int i) {
        int length = charSequence.length();
        int i2 = 0;
        while (i < length) {
            char charAt = charSequence.charAt(i);
            if (charAt < 2048) {
                i2 += (127 - charAt) >>> 31;
            } else {
                i2 += 2;
                if (55296 <= charAt && charAt <= 57343) {
                    if (Character.codePointAt(charSequence, i) < 65536) {
                        StringBuilder sb = new StringBuilder(39);
                        sb.append("Unpaired surrogate at index ");
                        sb.append(i);
                        throw new IllegalArgumentException(sb.toString());
                    }
                    i++;
                }
            }
            i++;
        }
        return i2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x001d, code lost:
    
        return r10 + r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static int zza(java.lang.CharSequence r8, byte[] r9, int r10, int r11) {
        /*
            Method dump skipped, instructions count: 247
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzart.zza(java.lang.CharSequence, byte[], int, int):int");
    }

    private static void zza(CharSequence charSequence, ByteBuffer byteBuffer) {
        if (byteBuffer.isReadOnly()) {
            throw new ReadOnlyBufferException();
        }
        if (!byteBuffer.hasArray()) {
            zzb(charSequence, byteBuffer);
            return;
        }
        try {
            byteBuffer.position(zza(charSequence, byteBuffer.array(), byteBuffer.arrayOffset() + byteBuffer.position(), byteBuffer.remaining()) - byteBuffer.arrayOffset());
        } catch (ArrayIndexOutOfBoundsException e) {
            BufferOverflowException bufferOverflowException = new BufferOverflowException();
            bufferOverflowException.initCause(e);
            throw bufferOverflowException;
        }
    }

    public static int zzagz(int i) {
        if (i >= 0) {
            return zzahe(i);
        }
        return 10;
    }

    public static int zzah(int i, int i2) {
        return zzahc(i) + zzagz(i2);
    }

    public static int zzaha(int i) {
        return zzahe(zzahg(i));
    }

    public static int zzahc(int i) {
        return zzahe(zzasd.zzak(i, 0));
    }

    public static int zzahe(int i) {
        if ((i & (-128)) == 0) {
            return 1;
        }
        if ((i & (-16384)) == 0) {
            return 2;
        }
        if (((-2097152) & i) == 0) {
            return 3;
        }
        return (i & (-268435456)) == 0 ? 4 : 5;
    }

    public static int zzahg(int i) {
        return (i >> 31) ^ (i << 1);
    }

    public static int zzai(int i, int i2) {
        return zzahc(i) + zzaha(i2);
    }

    public static int zzb(int i, double d) {
        return zzahc(i) + 8;
    }

    public static int zzb(int i, zzasa zzasaVar) {
        return (zzahc(i) * 2) + zzd(zzasaVar);
    }

    private static int zzb(CharSequence charSequence) {
        int length = charSequence.length();
        int i = 0;
        while (i < length && charSequence.charAt(i) < 128) {
            i++;
        }
        int i2 = length;
        while (true) {
            if (i < length) {
                char charAt = charSequence.charAt(i);
                if (charAt >= 2048) {
                    i2 += zza(charSequence, i);
                    break;
                }
                i2 += (127 - charAt) >>> 31;
                i++;
            } else {
                break;
            }
        }
        if (i2 >= length) {
            return i2;
        }
        StringBuilder sb = new StringBuilder(54);
        sb.append("UTF-8 length does not fit in int: ");
        sb.append(i2 + 4294967296L);
        throw new IllegalArgumentException(sb.toString());
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v13 */
    private static void zzb(CharSequence charSequence, ByteBuffer byteBuffer) {
        int i;
        int length = charSequence.length();
        int i2 = 0;
        while (i2 < length) {
            char charAt = charSequence.charAt(i2);
            char c = charAt;
            if (charAt >= 128) {
                if (charAt < 2048) {
                    i = 960 | (charAt >>> 6);
                } else {
                    if (charAt >= 55296 && 57343 >= charAt) {
                        int i3 = i2 + 1;
                        if (i3 != charSequence.length()) {
                            char charAt2 = charSequence.charAt(i3);
                            if (Character.isSurrogatePair(charAt, charAt2)) {
                                int codePoint = Character.toCodePoint(charAt, charAt2);
                                byteBuffer.put((byte) (240 | (codePoint >>> 18)));
                                byteBuffer.put((byte) (((codePoint >>> 12) & 63) | 128));
                                byteBuffer.put((byte) (((codePoint >>> 6) & 63) | 128));
                                byteBuffer.put((byte) ((codePoint & 63) | 128));
                                i2 = i3;
                                i2++;
                            } else {
                                i2 = i3;
                            }
                        }
                        StringBuilder sb = new StringBuilder(39);
                        sb.append("Unpaired surrogate at index ");
                        sb.append(i2 - 1);
                        throw new IllegalArgumentException(sb.toString());
                    }
                    byteBuffer.put((byte) (480 | (charAt >>> '\f')));
                    i = ((charAt >>> 6) & 63) | 128;
                }
                byteBuffer.put((byte) i);
                c = (charAt & '?') | 128;
            }
            byteBuffer.put((byte) c);
            i2++;
        }
    }

    public static zzart zzbe(byte[] bArr) {
        return zzc(bArr, 0, bArr.length);
    }

    public static int zzbg(byte[] bArr) {
        return zzahe(bArr.length) + bArr.length;
    }

    public static int zzc(int i, zzasa zzasaVar) {
        return zzahc(i) + zze(zzasaVar);
    }

    public static int zzc(int i, byte[] bArr) {
        return zzahc(i) + zzbg(bArr);
    }

    public static zzart zzc(byte[] bArr, int i, int i2) {
        return new zzart(bArr, i, i2);
    }

    public static int zzcy(long j) {
        return zzdc(j);
    }

    public static int zzcz(long j) {
        return zzdc(j);
    }

    public static int zzd(int i, float f) {
        return zzahc(i) + 4;
    }

    public static int zzd(zzasa zzasaVar) {
        return zzasaVar.cz();
    }

    public static int zzda(long j) {
        return zzdc(zzde(j));
    }

    public static int zzdc(long j) {
        if ((j & (-128)) == 0) {
            return 1;
        }
        if ((j & (-16384)) == 0) {
            return 2;
        }
        if ((j & (-2097152)) == 0) {
            return 3;
        }
        if ((j & (-268435456)) == 0) {
            return 4;
        }
        if ((j & (-34359738368L)) == 0) {
            return 5;
        }
        if ((j & (-4398046511104L)) == 0) {
            return 6;
        }
        if ((j & (-562949953421312L)) == 0) {
            return 7;
        }
        if ((j & (-72057594037927936L)) == 0) {
            return 8;
        }
        return (j & Long.MIN_VALUE) == 0 ? 9 : 10;
    }

    public static long zzde(long j) {
        return (j << 1) ^ (j >> 63);
    }

    public static int zze(int i, long j) {
        return zzahc(i) + zzcy(j);
    }

    public static int zze(zzasa zzasaVar) {
        int cz2 = zzasaVar.cz();
        return zzahe(cz2) + cz2;
    }

    public static int zzf(int i, long j) {
        return zzahc(i) + zzcz(j);
    }

    public static int zzg(int i, long j) {
        return zzahc(i) + 8;
    }

    public static int zzh(int i, long j) {
        return zzahc(i) + zzda(j);
    }

    public static int zzh(int i, boolean z) {
        return zzahc(i) + 1;
    }

    public static int zzr(int i, String str) {
        return zzahc(i) + zzuy(str);
    }

    public static int zzuy(String str) {
        int zzb = zzb(str);
        return zzahe(zzb) + zzb;
    }

    public int cl() {
        return this.btF.remaining();
    }

    public void cm() {
        if (cl() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }

    public void zza(int i, double d) throws IOException {
        zzaj(i, 1);
        zzn(d);
    }

    public void zza(int i, long j) throws IOException {
        zzaj(i, 0);
        zzcu(j);
    }

    public void zza(int i, zzasa zzasaVar) throws IOException {
        zzaj(i, 2);
        zzc(zzasaVar);
    }

    public void zzaf(int i, int i2) throws IOException {
        zzaj(i, 0);
        zzagx(i2);
    }

    public void zzag(int i, int i2) throws IOException {
        zzaj(i, 0);
        zzagy(i2);
    }

    public void zzagx(int i) throws IOException {
        if (i >= 0) {
            zzahd(i);
        } else {
            zzdb(i);
        }
    }

    public void zzagy(int i) throws IOException {
        zzahd(zzahg(i));
    }

    public void zzahb(int i) throws IOException {
        zzc((byte) i);
    }

    public void zzahd(int i) throws IOException {
        while ((i & (-128)) != 0) {
            zzahb((i & Opcodes.LAND) | 128);
            i >>>= 7;
        }
        zzahb(i);
    }

    public void zzahf(int i) throws IOException {
        if (this.btF.remaining() < 4) {
            throw new zza(this.btF.position(), this.btF.limit());
        }
        this.btF.putInt(i);
    }

    public void zzaj(int i, int i2) throws IOException {
        zzahd(zzasd.zzak(i, i2));
    }

    public void zzb(int i, long j) throws IOException {
        zzaj(i, 0);
        zzcv(j);
    }

    public void zzb(int i, byte[] bArr) throws IOException {
        zzaj(i, 2);
        zzbf(bArr);
    }

    public void zzb(zzasa zzasaVar) throws IOException {
        zzasaVar.zza(this);
    }

    public void zzbf(byte[] bArr) throws IOException {
        zzahd(bArr.length);
        zzbh(bArr);
    }

    public void zzbh(byte[] bArr) throws IOException {
        zzd(bArr, 0, bArr.length);
    }

    public void zzc(byte b) throws IOException {
        if (!this.btF.hasRemaining()) {
            throw new zza(this.btF.position(), this.btF.limit());
        }
        this.btF.put(b);
    }

    public void zzc(int i, float f) throws IOException {
        zzaj(i, 5);
        zzk(f);
    }

    public void zzc(int i, long j) throws IOException {
        zzaj(i, 1);
        zzcw(j);
    }

    public void zzc(zzasa zzasaVar) throws IOException {
        zzahd(zzasaVar.cy());
        zzasaVar.zza(this);
    }

    public void zzcu(long j) throws IOException {
        zzdb(j);
    }

    public void zzcv(long j) throws IOException {
        zzdb(j);
    }

    public void zzcw(long j) throws IOException {
        zzdd(j);
    }

    public void zzcx(long j) throws IOException {
        zzdb(zzde(j));
    }

    public void zzd(int i, long j) throws IOException {
        zzaj(i, 0);
        zzcx(j);
    }

    public void zzd(byte[] bArr, int i, int i2) throws IOException {
        if (this.btF.remaining() < i2) {
            throw new zza(this.btF.position(), this.btF.limit());
        }
        this.btF.put(bArr, i, i2);
    }

    public void zzdb(long j) throws IOException {
        while ((j & (-128)) != 0) {
            zzahb((((int) j) & Opcodes.LAND) | 128);
            j >>>= 7;
        }
        zzahb((int) j);
    }

    public void zzdd(long j) throws IOException {
        if (this.btF.remaining() < 8) {
            throw new zza(this.btF.position(), this.btF.limit());
        }
        this.btF.putLong(j);
    }

    public void zzdm(boolean z) throws IOException {
        zzahb(z ? 1 : 0);
    }

    public void zzg(int i, boolean z) throws IOException {
        zzaj(i, 0);
        zzdm(z);
    }

    public void zzk(float f) throws IOException {
        zzahf(Float.floatToIntBits(f));
    }

    public void zzn(double d) throws IOException {
        zzdd(Double.doubleToLongBits(d));
    }

    public void zzq(int i, String str) throws IOException {
        zzaj(i, 2);
        zzux(str);
    }

    public void zzux(String str) throws IOException {
        try {
            int zzahe = zzahe(str.length());
            if (zzahe != zzahe(str.length() * 3)) {
                zzahd(zzb(str));
                zza(str, this.btF);
                return;
            }
            int position = this.btF.position();
            if (this.btF.remaining() < zzahe) {
                throw new zza(position + zzahe, this.btF.limit());
            }
            this.btF.position(position + zzahe);
            zza(str, this.btF);
            int position2 = this.btF.position();
            this.btF.position(position);
            zzahd((position2 - position) - zzahe);
            this.btF.position(position2);
        } catch (BufferOverflowException e) {
            zza zzaVar = new zza(this.btF.position(), this.btF.limit());
            zzaVar.initCause(e);
            throw zzaVar;
        }
    }
}
