package com.google.android.gms.internal;

import com.liulishuo.filedownloader.model.FileDownloadStatus;
import java.io.IOException;
import kotlin.jvm.internal.ByteCompanionObject;

/* loaded from: classes.dex */
public final class zzars {
    private int btA;
    private int btC;
    private int btw;
    private int btx;
    private int bty;
    private int btz;
    private final byte[] buffer;
    private int btB = Integer.MAX_VALUE;
    private int btD = 64;
    private int btE = 67108864;

    private zzars(byte[] bArr, int i, int i2) {
        this.buffer = bArr;
        this.btw = i;
        this.btx = i2 + i;
        this.btz = i;
    }

    private void ch() {
        this.btx += this.bty;
        int i = this.btx;
        if (i <= this.btB) {
            this.bty = 0;
        } else {
            this.bty = i - this.btB;
            this.btx -= this.bty;
        }
    }

    public static int zzags(int i) {
        return (-(i & 1)) ^ (i >>> 1);
    }

    public static zzars zzb(byte[] bArr, int i, int i2) {
        return new zzars(bArr, i, i2);
    }

    public static zzars zzbd(byte[] bArr) {
        return zzb(bArr, 0, bArr.length);
    }

    public static long zzct(long j) {
        return (j >>> 1) ^ (-(j & 1));
    }

    public int bU() throws IOException {
        if (cj()) {
            this.btA = 0;
            return 0;
        }
        this.btA = cd();
        if (this.btA == 0) {
            throw zzarz.cu();
        }
        return this.btA;
    }

    public void bV() throws IOException {
        int bU;
        do {
            bU = bU();
            if (bU == 0) {
                return;
            }
        } while (zzagr(bU));
    }

    public long bW() throws IOException {
        return ce();
    }

    public long bX() throws IOException {
        return ce();
    }

    public int bY() throws IOException {
        return cd();
    }

    public long bZ() throws IOException {
        return cg();
    }

    public boolean ca() throws IOException {
        return cd() != 0;
    }

    public int cb() throws IOException {
        return zzags(cd());
    }

    public long cc() throws IOException {
        return zzct(ce());
    }

    public int cd() throws IOException {
        int i;
        byte ck = ck();
        if (ck >= 0) {
            return ck;
        }
        int i2 = ck & ByteCompanionObject.MAX_VALUE;
        byte ck2 = ck();
        if (ck2 >= 0) {
            i = ck2 << 7;
        } else {
            i2 |= (ck2 & ByteCompanionObject.MAX_VALUE) << 7;
            byte ck3 = ck();
            if (ck3 >= 0) {
                i = ck3 << 14;
            } else {
                i2 |= (ck3 & ByteCompanionObject.MAX_VALUE) << 14;
                byte ck4 = ck();
                if (ck4 < 0) {
                    int i3 = i2 | ((ck4 & ByteCompanionObject.MAX_VALUE) << 21);
                    byte ck5 = ck();
                    int i4 = i3 | (ck5 << 28);
                    if (ck5 >= 0) {
                        return i4;
                    }
                    for (int i5 = 0; i5 < 5; i5++) {
                        if (ck() >= 0) {
                            return i4;
                        }
                    }
                    throw zzarz.ct();
                }
                i = ck4 << 21;
            }
        }
        return i2 | i;
    }

    public long ce() throws IOException {
        int i = 0;
        long j = 0;
        while (i < 64) {
            long j2 = j | ((r3 & ByteCompanionObject.MAX_VALUE) << i);
            if ((ck() & 128) == 0) {
                return j2;
            }
            i += 7;
            j = j2;
        }
        throw zzarz.ct();
    }

    public int cf() throws IOException {
        return (ck() & FileDownloadStatus.error) | ((ck() & FileDownloadStatus.error) << 8) | ((ck() & FileDownloadStatus.error) << 16) | ((ck() & FileDownloadStatus.error) << 24);
    }

    public long cg() throws IOException {
        return (ck() & 255) | ((ck() & 255) << 8) | ((ck() & 255) << 16) | ((ck() & 255) << 24) | ((ck() & 255) << 32) | ((ck() & 255) << 40) | ((ck() & 255) << 48) | ((ck() & 255) << 56);
    }

    public int ci() {
        if (this.btB == Integer.MAX_VALUE) {
            return -1;
        }
        return this.btB - this.btz;
    }

    public boolean cj() {
        return this.btz == this.btx;
    }

    public byte ck() throws IOException {
        if (this.btz == this.btx) {
            throw zzarz.cr();
        }
        byte[] bArr = this.buffer;
        int i = this.btz;
        this.btz = i + 1;
        return bArr[i];
    }

    public int getPosition() {
        return this.btz - this.btw;
    }

    public byte[] readBytes() throws IOException {
        int cd = cd();
        if (cd < 0) {
            throw zzarz.cs();
        }
        if (cd == 0) {
            return zzasd.btY;
        }
        if (cd > this.btx - this.btz) {
            throw zzarz.cr();
        }
        byte[] bArr = new byte[cd];
        System.arraycopy(this.buffer, this.btz, bArr, 0, cd);
        this.btz += cd;
        return bArr;
    }

    public double readDouble() throws IOException {
        return Double.longBitsToDouble(cg());
    }

    public float readFloat() throws IOException {
        return Float.intBitsToFloat(cf());
    }

    public String readString() throws IOException {
        int cd = cd();
        if (cd < 0) {
            throw zzarz.cs();
        }
        if (cd > this.btx - this.btz) {
            throw zzarz.cr();
        }
        String str = new String(this.buffer, this.btz, cd, zzary.UTF_8);
        this.btz += cd;
        return str;
    }

    public void zza(zzasa zzasaVar) throws IOException {
        int cd = cd();
        if (this.btC >= this.btD) {
            throw zzarz.cx();
        }
        int zzagt = zzagt(cd);
        this.btC++;
        zzasaVar.zzb(this);
        zzagq(0);
        this.btC--;
        zzagu(zzagt);
    }

    public void zza(zzasa zzasaVar, int i) throws IOException {
        if (this.btC >= this.btD) {
            throw zzarz.cx();
        }
        this.btC++;
        zzasaVar.zzb(this);
        zzagq(zzasd.zzak(i, 4));
        this.btC--;
    }

    public byte[] zzae(int i, int i2) {
        if (i2 == 0) {
            return zzasd.btY;
        }
        byte[] bArr = new byte[i2];
        System.arraycopy(this.buffer, this.btw + i, bArr, 0, i2);
        return bArr;
    }

    public void zzagq(int i) throws zzarz {
        if (this.btA != i) {
            throw zzarz.cv();
        }
    }

    public boolean zzagr(int i) throws IOException {
        switch (zzasd.zzahk(i)) {
            case 0:
                bY();
                return true;
            case 1:
                cg();
                return true;
            case 2:
                zzagw(cd());
                return true;
            case 3:
                bV();
                zzagq(zzasd.zzak(zzasd.zzahl(i), 4));
                return true;
            case 4:
                return false;
            case 5:
                cf();
                return true;
            default:
                throw zzarz.cw();
        }
    }

    public int zzagt(int i) throws zzarz {
        if (i < 0) {
            throw zzarz.cs();
        }
        int i2 = i + this.btz;
        int i3 = this.btB;
        if (i2 > i3) {
            throw zzarz.cr();
        }
        this.btB = i2;
        ch();
        return i3;
    }

    public void zzagu(int i) {
        this.btB = i;
        ch();
    }

    public void zzagv(int i) {
        if (i > this.btz - this.btw) {
            int i2 = this.btz - this.btw;
            StringBuilder sb = new StringBuilder(50);
            sb.append("Position ");
            sb.append(i);
            sb.append(" is beyond current ");
            sb.append(i2);
            throw new IllegalArgumentException(sb.toString());
        }
        if (i >= 0) {
            this.btz = this.btw + i;
            return;
        }
        StringBuilder sb2 = new StringBuilder(24);
        sb2.append("Bad position ");
        sb2.append(i);
        throw new IllegalArgumentException(sb2.toString());
    }

    public void zzagw(int i) throws IOException {
        if (i < 0) {
            throw zzarz.cs();
        }
        if (this.btz + i > this.btB) {
            zzagw(this.btB - this.btz);
            throw zzarz.cr();
        }
        if (i > this.btx - this.btz) {
            throw zzarz.cr();
        }
        this.btz += i;
    }
}
