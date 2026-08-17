package com.google.android.gms.internal;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.Writer;

/* loaded from: classes.dex */
public class zzaqr implements Closeable, Flushable {
    private static final String[] brM = new String[128];
    private static final String[] brN;
    private boolean boe;
    private boolean bof;
    private String brO;
    private String brP;
    private boolean brp;
    private int[] brx = new int[32];
    private int bry = 0;
    private final Writer out;
    private String separator;

    static {
        for (int i = 0; i <= 31; i++) {
            brM[i] = String.format("\\u%04x", Integer.valueOf(i));
        }
        brM[34] = "\\\"";
        brM[92] = "\\\\";
        brM[9] = "\\t";
        brM[8] = "\\b";
        brM[10] = "\\n";
        brM[13] = "\\r";
        brM[12] = "\\f";
        brN = (String[]) brM.clone();
        brN[60] = "\\u003c";
        brN[62] = "\\u003e";
        brN[38] = "\\u0026";
        brN[61] = "\\u003d";
        brN[39] = "\\u0027";
    }

    public zzaqr(Writer writer) {
        zzagn(6);
        this.separator = ":";
        this.boe = true;
        if (writer == null) {
            throw new NullPointerException("out == null");
        }
        this.out = writer;
    }

    private int bO() {
        if (this.bry == 0) {
            throw new IllegalStateException("JsonWriter is closed.");
        }
        return this.brx[this.bry - 1];
    }

    private void bP() throws IOException {
        if (this.brP != null) {
            bR();
            zzuw(this.brP);
            this.brP = null;
        }
    }

    private void bQ() throws IOException {
        if (this.brO == null) {
            return;
        }
        this.out.write("\n");
        int i = this.bry;
        for (int i2 = 1; i2 < i; i2++) {
            this.out.write(this.brO);
        }
    }

    private void bR() throws IOException {
        int bO = bO();
        if (bO == 5) {
            this.out.write(44);
        } else if (bO != 3) {
            throw new IllegalStateException("Nesting problem.");
        }
        bQ();
        zzagp(4);
    }

    private void zzagn(int i) {
        if (this.bry == this.brx.length) {
            int[] iArr = new int[this.bry * 2];
            System.arraycopy(this.brx, 0, iArr, 0, this.bry);
            this.brx = iArr;
        }
        int[] iArr2 = this.brx;
        int i2 = this.bry;
        this.bry = i2 + 1;
        iArr2[i2] = i;
    }

    private void zzagp(int i) {
        this.brx[this.bry - 1] = i;
    }

    private zzaqr zzc(int i, int i2, String str) throws IOException {
        int bO = bO();
        if (bO != i2 && bO != i) {
            throw new IllegalStateException("Nesting problem.");
        }
        if (this.brP != null) {
            String valueOf = String.valueOf(this.brP);
            throw new IllegalStateException(valueOf.length() != 0 ? "Dangling name: ".concat(valueOf) : new String("Dangling name: "));
        }
        this.bry--;
        if (bO == i2) {
            bQ();
        }
        this.out.write(str);
        return this;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0004. Please report as an issue. */
    private void zzdl(boolean z) throws IOException {
        int i;
        switch (bO()) {
            case 1:
                zzagp(2);
                bQ();
                return;
            case 2:
                this.out.append(',');
                bQ();
                return;
            case 3:
            case 5:
            default:
                throw new IllegalStateException("Nesting problem.");
            case 4:
                this.out.append((CharSequence) this.separator);
                i = 5;
                zzagp(i);
                return;
            case 7:
                if (!this.brp) {
                    throw new IllegalStateException("JSON must have only one top-level value.");
                }
            case 6:
                if (!this.brp && !z) {
                    throw new IllegalStateException("JSON must start with an array or an object.");
                }
                i = 7;
                zzagp(i);
                return;
        }
    }

    private zzaqr zzp(int i, String str) throws IOException {
        zzdl(true);
        zzagn(i);
        this.out.write(str);
        return this;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0034  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void zzuw(java.lang.String r8) throws java.io.IOException {
        /*
            r7 = this;
            boolean r0 = r7.bof
            if (r0 == 0) goto L7
            java.lang.String[] r0 = com.google.android.gms.internal.zzaqr.brN
            goto L9
        L7:
            java.lang.String[] r0 = com.google.android.gms.internal.zzaqr.brM
        L9:
            java.io.Writer r1 = r7.out
            java.lang.String r2 = "\""
            r1.write(r2)
            int r1 = r8.length()
            r2 = 0
            r3 = r2
        L16:
            if (r2 >= r1) goto L45
            char r4 = r8.charAt(r2)
            r5 = 128(0x80, float:1.794E-43)
            if (r4 >= r5) goto L25
            r4 = r0[r4]
            if (r4 != 0) goto L32
            goto L42
        L25:
            r5 = 8232(0x2028, float:1.1535E-41)
            if (r4 != r5) goto L2c
            java.lang.String r4 = "\\u2028"
            goto L32
        L2c:
            r5 = 8233(0x2029, float:1.1537E-41)
            if (r4 != r5) goto L42
            java.lang.String r4 = "\\u2029"
        L32:
            if (r3 >= r2) goto L3b
            java.io.Writer r5 = r7.out
            int r6 = r2 - r3
            r5.write(r8, r3, r6)
        L3b:
            java.io.Writer r3 = r7.out
            r3.write(r4)
            int r3 = r2 + 1
        L42:
            int r2 = r2 + 1
            goto L16
        L45:
            if (r3 >= r1) goto L4d
            java.io.Writer r0 = r7.out
            int r1 = r1 - r3
            r0.write(r8, r3, r1)
        L4d:
            java.io.Writer r8 = r7.out
            java.lang.String r0 = "\""
            r8.write(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzaqr.zzuw(java.lang.String):void");
    }

    public zzaqr bA() throws IOException {
        if (this.brP != null) {
            if (!this.boe) {
                this.brP = null;
                return this;
            }
            bP();
        }
        zzdl(false);
        this.out.write("null");
        return this;
    }

    public final boolean bM() {
        return this.bof;
    }

    public final boolean bN() {
        return this.boe;
    }

    public zzaqr bw() throws IOException {
        bP();
        return zzp(1, "[");
    }

    public zzaqr bx() throws IOException {
        return zzc(1, 2, "]");
    }

    public zzaqr by() throws IOException {
        bP();
        return zzp(3, "{");
    }

    public zzaqr bz() throws IOException {
        return zzc(3, 5, "}");
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.out.close();
        int i = this.bry;
        if (i > 1 || (i == 1 && this.brx[i - 1] != 7)) {
            throw new IOException("Incomplete document");
        }
        this.bry = 0;
    }

    public void flush() throws IOException {
        if (this.bry == 0) {
            throw new IllegalStateException("JsonWriter is closed.");
        }
        this.out.flush();
    }

    public boolean isLenient() {
        return this.brp;
    }

    /* JADX WARN: Unreachable blocks removed: 1, instructions: 1 */
    public final void setIndent(String str) {
        String str2;
        if (str.length() == 0) {
            this.brO = null;
            str2 = ":";
        } else {
            this.brO = str;
            str2 = ": ";
        }
        this.separator = str2;
    }

    public final void setLenient(boolean z) {
        this.brp = z;
    }

    public zzaqr zza(Number number) throws IOException {
        if (number == null) {
            return bA();
        }
        bP();
        String obj = number.toString();
        if (this.brp || !(obj.equals("-Infinity") || obj.equals("Infinity") || obj.equals("NaN"))) {
            zzdl(false);
            this.out.append((CharSequence) obj);
            return this;
        }
        String valueOf = String.valueOf(number);
        StringBuilder sb = new StringBuilder(39 + String.valueOf(valueOf).length());
        sb.append("Numeric values must be finite, but was ");
        sb.append(valueOf);
        throw new IllegalArgumentException(sb.toString());
    }

    public zzaqr zzcs(long j) throws IOException {
        bP();
        zzdl(false);
        this.out.write(Long.toString(j));
        return this;
    }

    public zzaqr zzdh(boolean z) throws IOException {
        bP();
        zzdl(false);
        this.out.write(z ? "true" : "false");
        return this;
    }

    public final void zzdj(boolean z) {
        this.bof = z;
    }

    public final void zzdk(boolean z) {
        this.boe = z;
    }

    public zzaqr zzus(String str) throws IOException {
        if (str == null) {
            throw new NullPointerException("name == null");
        }
        if (this.brP != null) {
            throw new IllegalStateException();
        }
        if (this.bry == 0) {
            throw new IllegalStateException("JsonWriter is closed.");
        }
        this.brP = str;
        return this;
    }

    public zzaqr zzut(String str) throws IOException {
        if (str == null) {
            return bA();
        }
        bP();
        zzdl(false);
        zzuw(str);
        return this;
    }
}
