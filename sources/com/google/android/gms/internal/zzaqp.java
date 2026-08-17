package com.google.android.gms.internal;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.io.Closeable;
import java.io.IOException;
import java.io.Reader;
import kotlin.text.Typography;
import org.jacoco.agent.rt.internal_b0d6a23.asm.Opcodes;

/* loaded from: classes.dex */
public class zzaqp implements Closeable {
    private static final char[] bro = ")]}'\n".toCharArray();
    private int[] brA;
    private long bru;
    private int brv;
    private String brw;
    private int bry;
    private String[] brz;
    private final Reader in;
    private boolean brp = false;
    private final char[] brq = new char[1024];
    private int pos = 0;
    private int limit = 0;
    private int brr = 0;
    private int brs = 0;
    private int brt = 0;
    private int[] brx = new int[32];

    static {
        zzapu.bph = new zzapu() { // from class: com.google.android.gms.internal.zzaqp.1
            /* JADX WARN: Unreachable blocks removed: 1, instructions: 1 */
            @Override // com.google.android.gms.internal.zzapu
            public void zzi(zzaqp zzaqpVar) throws IOException {
                int i;
                if (zzaqpVar instanceof zzaqf) {
                    ((zzaqf) zzaqpVar).bt();
                    return;
                }
                int i2 = zzaqpVar.brt;
                if (i2 == 0) {
                    i2 = zzaqpVar.bD();
                }
                if (i2 == 13) {
                    i = 9;
                } else if (i2 == 12) {
                    i = 8;
                } else {
                    if (i2 != 14) {
                        String valueOf = String.valueOf(zzaqpVar.bq());
                        int lineNumber = zzaqpVar.getLineNumber();
                        int columnNumber = zzaqpVar.getColumnNumber();
                        String path = zzaqpVar.getPath();
                        StringBuilder sb = new StringBuilder(70 + String.valueOf(valueOf).length() + String.valueOf(path).length());
                        sb.append("Expected a name but was ");
                        sb.append(valueOf);
                        sb.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
                        sb.append(" at line ");
                        sb.append(lineNumber);
                        sb.append(" column ");
                        sb.append(columnNumber);
                        sb.append(" path ");
                        sb.append(path);
                        throw new IllegalStateException(sb.toString());
                    }
                    i = 10;
                }
                zzaqpVar.brt = i;
            }
        };
    }

    public zzaqp(Reader reader) {
        this.bry = 0;
        int[] iArr = this.brx;
        int i = this.bry;
        this.bry = i + 1;
        iArr[i] = 6;
        this.brz = new String[32];
        this.brA = new int[32];
        if (reader == null) {
            throw new NullPointerException("in == null");
        }
        this.in = reader;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int bD() throws IOException {
        int i;
        int zzdi;
        int i2;
        int i3 = this.brx[this.bry - 1];
        if (i3 == 1) {
            this.brx[this.bry - 1] = 2;
        } else {
            if (i3 != 2) {
                if (i3 == 3 || i3 == 5) {
                    this.brx[this.bry - 1] = 4;
                    if (i3 == 5 && (zzdi = zzdi(true)) != 44) {
                        if (zzdi != 59) {
                            if (zzdi != 125) {
                                throw zzuv("Unterminated object");
                            }
                            this.brt = 2;
                            return 2;
                        }
                        bI();
                    }
                    int zzdi2 = zzdi(true);
                    if (zzdi2 == 34) {
                        i = 13;
                    } else if (zzdi2 == 39) {
                        bI();
                        i = 12;
                    } else {
                        if (zzdi2 == 125) {
                            if (i3 == 5) {
                                throw zzuv("Expected name");
                            }
                            this.brt = 2;
                            return 2;
                        }
                        bI();
                        this.pos--;
                        if (!zzc((char) zzdi2)) {
                            throw zzuv("Expected name");
                        }
                        i = 14;
                    }
                } else if (i3 == 4) {
                    this.brx[this.bry - 1] = 5;
                    int zzdi3 = zzdi(true);
                    if (zzdi3 != 58) {
                        if (zzdi3 != 61) {
                            throw zzuv("Expected ':'");
                        }
                        bI();
                        if ((this.pos < this.limit || zzago(1)) && this.brq[this.pos] == '>') {
                            i2 = this.pos + 1;
                            this.pos = i2;
                        }
                    }
                } else if (i3 == 6) {
                    if (this.brp) {
                        bL();
                    }
                    this.brx[this.bry - 1] = 7;
                } else if (i3 == 7) {
                    if (zzdi(false) == -1) {
                        i = 17;
                    } else {
                        bI();
                        i2 = this.pos - 1;
                        this.pos = i2;
                    }
                } else if (i3 == 8) {
                    throw new IllegalStateException("JsonReader is closed");
                }
                this.brt = i;
                return i;
            }
            int zzdi4 = zzdi(true);
            if (zzdi4 != 44) {
                if (zzdi4 != 59) {
                    if (zzdi4 != 93) {
                        throw zzuv("Unterminated array");
                    }
                    this.brt = 4;
                    return 4;
                }
                bI();
            }
        }
        int zzdi5 = zzdi(true);
        if (zzdi5 != 34) {
            if (zzdi5 == 39) {
                bI();
                this.brt = 8;
                return 8;
            }
            if (zzdi5 != 44 && zzdi5 != 59) {
                if (zzdi5 == 91) {
                    this.brt = 3;
                    return 3;
                }
                if (zzdi5 != 93) {
                    if (zzdi5 == 123) {
                        this.brt = 1;
                        return 1;
                    }
                    this.pos--;
                    if (this.bry == 1) {
                        bI();
                    }
                    int bE = bE();
                    if (bE != 0) {
                        return bE;
                    }
                    int bF = bF();
                    if (bF != 0) {
                        return bF;
                    }
                    if (!zzc(this.brq[this.pos])) {
                        throw zzuv("Expected value");
                    }
                    bI();
                    i = 10;
                } else if (i3 == 1) {
                    this.brt = 4;
                    return 4;
                }
            }
            if (i3 != 1 && i3 != 2) {
                throw zzuv("Unexpected value");
            }
            bI();
            this.pos--;
            this.brt = 7;
            return 7;
        }
        if (this.bry == 1) {
            bI();
        }
        i = 9;
        this.brt = i;
        return i;
    }

    private int bE() throws IOException {
        String str;
        String str2;
        int i;
        char c = this.brq[this.pos];
        if (c == 't' || c == 'T') {
            str = "true";
            str2 = "TRUE";
            i = 5;
        } else if (c == 'f' || c == 'F') {
            str = "false";
            str2 = "FALSE";
            i = 6;
        } else {
            if (c != 'n' && c != 'N') {
                return 0;
            }
            str = "null";
            str2 = "NULL";
            i = 7;
        }
        int length = str.length();
        for (int i2 = 1; i2 < length; i2++) {
            if (this.pos + i2 >= this.limit && !zzago(i2 + 1)) {
                return 0;
            }
            char c2 = this.brq[this.pos + i2];
            if (c2 != str.charAt(i2) && c2 != str2.charAt(i2)) {
                return 0;
            }
        }
        if ((this.pos + length < this.limit || zzago(length + 1)) && zzc(this.brq[this.pos + length])) {
            return 0;
        }
        this.pos += length;
        this.brt = i;
        return i;
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0099, code lost:
    
        if (r9 != 2) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x009b, code lost:
    
        if (r10 == false) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x00a1, code lost:
    
        if (r11 != Long.MIN_VALUE) goto L58;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x00a3, code lost:
    
        if (r13 == false) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x00a5, code lost:
    
        if (r13 == false) goto L60;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x00a8, code lost:
    
        r11 = -r11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x00a9, code lost:
    
        r21.bru = r11;
        r21.pos += r3;
        r1 = 15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x00b2, code lost:
    
        r21.brt = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x00b4, code lost:
    
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x00b5, code lost:
    
        if (r9 == 2) goto L72;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x00b8, code lost:
    
        if (r9 == 4) goto L72;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x00bb, code lost:
    
        if (r9 != 7) goto L70;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x00be, code lost:
    
        return 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x00c0, code lost:
    
        r21.brv = r3;
        r1 = 16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x0097, code lost:
    
        if (zzc(r14) != false) goto L73;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x00c5, code lost:
    
        return 0;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:36:0x003a. Please report as an issue. */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private int bF() throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 252
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzaqp.bF():int");
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x002a, code lost:
    
        r0 = r1;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:29:0x0012. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0052  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.String bG() throws java.io.IOException {
        /*
            r5 = this;
            r0 = 0
            r1 = 0
            r2 = r1
        L3:
            r1 = r0
        L4:
            int r3 = r5.pos
            int r3 = r3 + r1
            int r4 = r5.limit
            if (r3 >= r4) goto L1c
            char[] r3 = r5.brq
            int r4 = r5.pos
            int r4 = r4 + r1
            char r3 = r3[r4]
            switch(r3) {
                case 9: goto L2a;
                case 10: goto L2a;
                case 12: goto L2a;
                case 13: goto L2a;
                case 32: goto L2a;
                case 35: goto L18;
                case 44: goto L2a;
                case 47: goto L18;
                case 58: goto L2a;
                case 59: goto L18;
                case 61: goto L18;
                case 91: goto L2a;
                case 92: goto L18;
                case 93: goto L2a;
                case 123: goto L2a;
                case 125: goto L2a;
                default: goto L15;
            }
        L15:
            int r1 = r1 + 1
            goto L4
        L18:
            r5.bI()
            goto L2a
        L1c:
            char[] r3 = r5.brq
            int r3 = r3.length
            if (r1 >= r3) goto L2c
            int r3 = r1 + 1
            boolean r3 = r5.zzago(r3)
            if (r3 == 0) goto L2a
            goto L4
        L2a:
            r0 = r1
            goto L46
        L2c:
            if (r2 != 0) goto L33
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
        L33:
            char[] r3 = r5.brq
            int r4 = r5.pos
            r2.append(r3, r4, r1)
            int r3 = r5.pos
            int r3 = r3 + r1
            r5.pos = r3
            r1 = 1
            boolean r1 = r5.zzago(r1)
            if (r1 != 0) goto L3
        L46:
            if (r2 != 0) goto L52
            java.lang.String r1 = new java.lang.String
            char[] r2 = r5.brq
            int r3 = r5.pos
            r1.<init>(r2, r3, r0)
            goto L5d
        L52:
            char[] r1 = r5.brq
            int r3 = r5.pos
            r2.append(r1, r3, r0)
            java.lang.String r1 = r2.toString()
        L5d:
            int r2 = r5.pos
            int r2 = r2 + r0
            r5.pos = r2
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzaqp.bG():java.lang.String");
    }

    /* JADX WARN: Removed duplicated region for block: B:4:0x0008  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void bH() throws java.io.IOException {
        /*
            r3 = this;
        L0:
            r0 = 0
        L1:
            int r1 = r3.pos
            int r1 = r1 + r0
            int r2 = r3.limit
            if (r1 >= r2) goto L1e
            char[] r1 = r3.brq
            int r2 = r3.pos
            int r2 = r2 + r0
            char r1 = r1[r2]
            switch(r1) {
                case 9: goto L18;
                case 10: goto L18;
                case 12: goto L18;
                case 13: goto L18;
                case 32: goto L18;
                case 35: goto L15;
                case 44: goto L18;
                case 47: goto L15;
                case 58: goto L18;
                case 59: goto L15;
                case 61: goto L15;
                case 91: goto L18;
                case 92: goto L15;
                case 93: goto L18;
                case 123: goto L18;
                case 125: goto L18;
                default: goto L12;
            }
        L12:
            int r0 = r0 + 1
            goto L1
        L15:
            r3.bI()
        L18:
            int r1 = r3.pos
            int r1 = r1 + r0
            r3.pos = r1
            return
        L1e:
            int r1 = r3.pos
            int r1 = r1 + r0
            r3.pos = r1
            r0 = 1
            boolean r0 = r3.zzago(r0)
            if (r0 != 0) goto L0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzaqp.bH():void");
    }

    private void bI() throws IOException {
        if (!this.brp) {
            throw zzuv("Use JsonReader.setLenient(true) to accept malformed JSON");
        }
    }

    private void bJ() throws IOException {
        char c;
        do {
            if (this.pos >= this.limit && !zzago(1)) {
                return;
            }
            char[] cArr = this.brq;
            int i = this.pos;
            this.pos = i + 1;
            c = cArr[i];
            if (c == '\n') {
                this.brr++;
                this.brs = this.pos;
                return;
            }
        } while (c != '\r');
    }

    private char bK() throws IOException {
        int i;
        int i2;
        if (this.pos == this.limit && !zzago(1)) {
            throw zzuv("Unterminated escape sequence");
        }
        char[] cArr = this.brq;
        int i3 = this.pos;
        this.pos = i3 + 1;
        char c = cArr[i3];
        if (c == '\n') {
            this.brr++;
            this.brs = this.pos;
            return c;
        }
        if (c == 'b') {
            return '\b';
        }
        if (c == 'f') {
            return '\f';
        }
        if (c == 'n') {
            return '\n';
        }
        if (c == 'r') {
            return '\r';
        }
        switch (c) {
            case 't':
                return '\t';
            case 'u':
                if (this.pos + 4 > this.limit && !zzago(4)) {
                    throw zzuv("Unterminated escape sequence");
                }
                char c2 = 0;
                int i4 = this.pos;
                int i5 = i4 + 4;
                while (i4 < i5) {
                    char c3 = this.brq[i4];
                    char c4 = (char) (c2 << 4);
                    if (c3 < '0' || c3 > '9') {
                        if (c3 >= 'a' && c3 <= 'f') {
                            i = c3 - 'a';
                        } else {
                            if (c3 < 'A' || c3 > 'F') {
                                String valueOf = String.valueOf(new String(this.brq, this.pos, 4));
                                throw new NumberFormatException(valueOf.length() != 0 ? "\\u".concat(valueOf) : new String("\\u"));
                            }
                            i = c3 - 'A';
                        }
                        i2 = i + 10;
                    } else {
                        i2 = c3 - '0';
                    }
                    c2 = (char) (c4 + i2);
                    i4++;
                }
                this.pos += 4;
                return c2;
            default:
                return c;
        }
    }

    private void bL() throws IOException {
        zzdi(true);
        this.pos--;
        if (this.pos + bro.length <= this.limit || zzago(bro.length)) {
            for (int i = 0; i < bro.length; i++) {
                if (this.brq[this.pos + i] != bro[i]) {
                    return;
                }
            }
            this.pos += bro.length;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getColumnNumber() {
        return (this.pos - this.brs) + 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getLineNumber() {
        return this.brr + 1;
    }

    private void zzagn(int i) {
        if (this.bry == this.brx.length) {
            int[] iArr = new int[this.bry * 2];
            int[] iArr2 = new int[this.bry * 2];
            String[] strArr = new String[this.bry * 2];
            System.arraycopy(this.brx, 0, iArr, 0, this.bry);
            System.arraycopy(this.brA, 0, iArr2, 0, this.bry);
            System.arraycopy(this.brz, 0, strArr, 0, this.bry);
            this.brx = iArr;
            this.brA = iArr2;
            this.brz = strArr;
        }
        int[] iArr3 = this.brx;
        int i2 = this.bry;
        this.bry = i2 + 1;
        iArr3[i2] = i;
    }

    private boolean zzago(int i) throws IOException {
        char[] cArr = this.brq;
        this.brs -= this.pos;
        if (this.limit != this.pos) {
            this.limit -= this.pos;
            System.arraycopy(cArr, this.pos, cArr, 0, this.limit);
        } else {
            this.limit = 0;
        }
        this.pos = 0;
        do {
            int read = this.in.read(cArr, this.limit, cArr.length - this.limit);
            if (read == -1) {
                return false;
            }
            this.limit += read;
            if (this.brr == 0 && this.brs == 0 && this.limit > 0 && cArr[0] == 65279) {
                this.pos++;
                this.brs++;
                i++;
            }
        } while (this.limit < i);
        return true;
    }

    private boolean zzc(char c) throws IOException {
        switch (c) {
            case '\t':
            case '\n':
            case '\f':
            case '\r':
            case ' ':
            case ',':
            case ':':
            case '[':
            case ']':
            case Opcodes.LSHR /* 123 */:
            case Opcodes.LUSHR /* 125 */:
                return false;
            case '#':
            case '/':
            case ';':
            case '=':
            case '\\':
                bI();
                return false;
            default:
                return true;
        }
    }

    private String zzd(char c) throws IOException {
        char[] cArr = this.brq;
        StringBuilder sb = new StringBuilder();
        while (true) {
            int i = this.pos;
            int i2 = this.limit;
            while (true) {
                if (i < i2) {
                    int i3 = i + 1;
                    char c2 = cArr[i];
                    if (c2 == c) {
                        this.pos = i3;
                        sb.append(cArr, i, (i3 - i) - 1);
                        return sb.toString();
                    }
                    if (c2 == '\\') {
                        this.pos = i3;
                        sb.append(cArr, i, (i3 - i) - 1);
                        sb.append(bK());
                        break;
                    }
                    if (c2 == '\n') {
                        this.brr++;
                        this.brs = i3;
                    }
                    i = i3;
                } else {
                    sb.append(cArr, i, i - i);
                    this.pos = i;
                    if (!zzago(1)) {
                        throw zzuv("Unterminated string");
                    }
                }
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:28:0x006e, code lost:
    
        if (r1 != '/') goto L57;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0070, code lost:
    
        r7.pos = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0073, code lost:
    
        if (r4 != r2) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0075, code lost:
    
        r7.pos--;
        r2 = zzago(2);
        r7.pos++;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0083, code lost:
    
        if (r2 != false) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0085, code lost:
    
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0086, code lost:
    
        bI();
        r2 = r0[r7.pos];
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x008f, code lost:
    
        if (r2 == '*') goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x009e, code lost:
    
        r7.pos++;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00a9, code lost:
    
        if (zzuu("*\/") != false) goto L42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00b1, code lost:
    
        throw zzuv("Unterminated comment");
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0091, code lost:
    
        if (r2 == '/') goto L36;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0094, code lost:
    
        r7.pos++;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0093, code lost:
    
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x00b9, code lost:
    
        if (r1 != '#') goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x00bb, code lost:
    
        r7.pos = r4;
        bI();
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x00c1, code lost:
    
        r7.pos = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x00c3, code lost:
    
        return r1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private int zzdi(boolean r8) throws java.io.IOException {
        /*
            r7 = this;
            char[] r0 = r7.brq
        L2:
            int r1 = r7.pos
        L4:
            int r2 = r7.limit
        L6:
            r3 = 1
            if (r1 != r2) goto L4f
            r7.pos = r1
            boolean r1 = r7.zzago(r3)
            if (r1 != 0) goto L4b
            if (r8 == 0) goto L49
            java.io.EOFException r8 = new java.io.EOFException
            java.lang.String r0 = "End of input at line "
            java.lang.String r0 = java.lang.String.valueOf(r0)
            int r1 = r7.getLineNumber()
            int r2 = r7.getColumnNumber()
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r4 = 30
            java.lang.String r5 = java.lang.String.valueOf(r0)
            int r5 = r5.length()
            int r4 = r4 + r5
            r3.<init>(r4)
            r3.append(r0)
            r3.append(r1)
            java.lang.String r0 = " column "
            r3.append(r0)
            r3.append(r2)
            java.lang.String r0 = r3.toString()
            r8.<init>(r0)
            throw r8
        L49:
            r8 = -1
            return r8
        L4b:
            int r1 = r7.pos
            int r2 = r7.limit
        L4f:
            int r4 = r1 + 1
            char r1 = r0[r1]
            r5 = 10
            if (r1 != r5) goto L5f
            int r1 = r7.brr
            int r1 = r1 + r3
            r7.brr = r1
            r7.brs = r4
            goto Lc4
        L5f:
            r5 = 32
            if (r1 == r5) goto Lc4
            r5 = 13
            if (r1 == r5) goto Lc4
            r5 = 9
            if (r1 != r5) goto L6c
            goto Lc4
        L6c:
            r5 = 47
            if (r1 != r5) goto Lb7
            r7.pos = r4
            r6 = 2
            if (r4 != r2) goto L86
            int r2 = r7.pos
            int r2 = r2 - r3
            r7.pos = r2
            boolean r2 = r7.zzago(r6)
            int r4 = r7.pos
            int r4 = r4 + r3
            r7.pos = r4
            if (r2 != 0) goto L86
            return r1
        L86:
            r7.bI()
            int r2 = r7.pos
            char r2 = r0[r2]
            r4 = 42
            if (r2 == r4) goto L9e
            if (r2 == r5) goto L94
            return r1
        L94:
            int r1 = r7.pos
            int r1 = r1 + r3
            r7.pos = r1
        L99:
            r7.bJ()
            goto L2
        L9e:
            int r1 = r7.pos
            int r1 = r1 + r3
            r7.pos = r1
        */
        //  java.lang.String r1 = "*/"
        /*
            boolean r1 = r7.zzuu(r1)
            if (r1 != 0) goto Lb2
            java.lang.String r8 = "Unterminated comment"
            java.io.IOException r8 = r7.zzuv(r8)
            throw r8
        Lb2:
            int r1 = r7.pos
            int r1 = r1 + r6
            goto L4
        Lb7:
            r2 = 35
            if (r1 != r2) goto Lc1
            r7.pos = r4
            r7.bI()
            goto L99
        Lc1:
            r7.pos = r4
            return r1
        Lc4:
            r1 = r4
            goto L6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzaqp.zzdi(boolean):int");
    }

    private void zze(char c) throws IOException {
        char[] cArr = this.brq;
        while (true) {
            int i = this.pos;
            int i2 = this.limit;
            while (true) {
                if (i < i2) {
                    int i3 = i + 1;
                    char c2 = cArr[i];
                    if (c2 == c) {
                        this.pos = i3;
                        return;
                    }
                    if (c2 == '\\') {
                        this.pos = i3;
                        bK();
                        break;
                    } else {
                        if (c2 == '\n') {
                            this.brr++;
                            this.brs = i3;
                        }
                        i = i3;
                    }
                } else {
                    this.pos = i;
                    if (!zzago(1)) {
                        throw zzuv("Unterminated string");
                    }
                }
            }
        }
    }

    private boolean zzuu(String str) throws IOException {
        while (true) {
            if (this.pos + str.length() > this.limit && !zzago(str.length())) {
                return false;
            }
            if (this.brq[this.pos] != '\n') {
                for (int i = 0; i < str.length(); i++) {
                    if (this.brq[this.pos + i] != str.charAt(i)) {
                        break;
                    }
                }
                return true;
            }
            this.brr++;
            this.brs = this.pos + 1;
            this.pos++;
        }
    }

    private IOException zzuv(String str) throws IOException {
        int lineNumber = getLineNumber();
        int columnNumber = getColumnNumber();
        String path = getPath();
        StringBuilder sb = new StringBuilder(45 + String.valueOf(str).length() + String.valueOf(path).length());
        sb.append(str);
        sb.append(" at line ");
        sb.append(lineNumber);
        sb.append(" column ");
        sb.append(columnNumber);
        sb.append(" path ");
        sb.append(path);
        throw new zzaqs(sb.toString());
    }

    public void beginArray() throws IOException {
        int i = this.brt;
        if (i == 0) {
            i = bD();
        }
        if (i == 3) {
            zzagn(1);
            this.brA[this.bry - 1] = 0;
            this.brt = 0;
            return;
        }
        String valueOf = String.valueOf(bq());
        int lineNumber = getLineNumber();
        int columnNumber = getColumnNumber();
        String path = getPath();
        StringBuilder sb = new StringBuilder(74 + String.valueOf(valueOf).length() + String.valueOf(path).length());
        sb.append("Expected BEGIN_ARRAY but was ");
        sb.append(valueOf);
        sb.append(" at line ");
        sb.append(lineNumber);
        sb.append(" column ");
        sb.append(columnNumber);
        sb.append(" path ");
        sb.append(path);
        throw new IllegalStateException(sb.toString());
    }

    public void beginObject() throws IOException {
        int i = this.brt;
        if (i == 0) {
            i = bD();
        }
        if (i == 1) {
            zzagn(3);
            this.brt = 0;
            return;
        }
        String valueOf = String.valueOf(bq());
        int lineNumber = getLineNumber();
        int columnNumber = getColumnNumber();
        String path = getPath();
        StringBuilder sb = new StringBuilder(75 + String.valueOf(valueOf).length() + String.valueOf(path).length());
        sb.append("Expected BEGIN_OBJECT but was ");
        sb.append(valueOf);
        sb.append(" at line ");
        sb.append(lineNumber);
        sb.append(" column ");
        sb.append(columnNumber);
        sb.append(" path ");
        sb.append(path);
        throw new IllegalStateException(sb.toString());
    }

    public zzaqq bq() throws IOException {
        int i = this.brt;
        if (i == 0) {
            i = bD();
        }
        switch (i) {
            case 1:
                return zzaqq.BEGIN_OBJECT;
            case 2:
                return zzaqq.END_OBJECT;
            case 3:
                return zzaqq.BEGIN_ARRAY;
            case 4:
                return zzaqq.END_ARRAY;
            case 5:
            case 6:
                return zzaqq.BOOLEAN;
            case 7:
                return zzaqq.NULL;
            case 8:
            case 9:
            case 10:
            case 11:
                return zzaqq.STRING;
            case 12:
            case 13:
            case 14:
                return zzaqq.NAME;
            case 15:
            case 16:
                return zzaqq.NUMBER;
            case 17:
                return zzaqq.END_DOCUMENT;
            default:
                throw new AssertionError();
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.brt = 0;
        this.brx[0] = 8;
        this.bry = 1;
        this.in.close();
    }

    public void endArray() throws IOException {
        int i = this.brt;
        if (i == 0) {
            i = bD();
        }
        if (i == 4) {
            this.bry--;
            int[] iArr = this.brA;
            int i2 = this.bry - 1;
            iArr[i2] = iArr[i2] + 1;
            this.brt = 0;
            return;
        }
        String valueOf = String.valueOf(bq());
        int lineNumber = getLineNumber();
        int columnNumber = getColumnNumber();
        String path = getPath();
        StringBuilder sb = new StringBuilder(72 + String.valueOf(valueOf).length() + String.valueOf(path).length());
        sb.append("Expected END_ARRAY but was ");
        sb.append(valueOf);
        sb.append(" at line ");
        sb.append(lineNumber);
        sb.append(" column ");
        sb.append(columnNumber);
        sb.append(" path ");
        sb.append(path);
        throw new IllegalStateException(sb.toString());
    }

    public void endObject() throws IOException {
        int i = this.brt;
        if (i == 0) {
            i = bD();
        }
        if (i == 2) {
            this.bry--;
            this.brz[this.bry] = null;
            int[] iArr = this.brA;
            int i2 = this.bry - 1;
            iArr[i2] = iArr[i2] + 1;
            this.brt = 0;
            return;
        }
        String valueOf = String.valueOf(bq());
        int lineNumber = getLineNumber();
        int columnNumber = getColumnNumber();
        String path = getPath();
        StringBuilder sb = new StringBuilder(73 + String.valueOf(valueOf).length() + String.valueOf(path).length());
        sb.append("Expected END_OBJECT but was ");
        sb.append(valueOf);
        sb.append(" at line ");
        sb.append(lineNumber);
        sb.append(" column ");
        sb.append(columnNumber);
        sb.append(" path ");
        sb.append(path);
        throw new IllegalStateException(sb.toString());
    }

    public String getPath() {
        StringBuilder sb = new StringBuilder();
        sb.append(Typography.dollar);
        int i = this.bry;
        for (int i2 = 0; i2 < i; i2++) {
            switch (this.brx[i2]) {
                case 1:
                case 2:
                    sb.append('[');
                    sb.append(this.brA[i2]);
                    sb.append(']');
                    break;
                case 3:
                case 4:
                case 5:
                    sb.append('.');
                    if (this.brz[i2] != null) {
                        sb.append(this.brz[i2]);
                        break;
                    } else {
                        break;
                    }
            }
        }
        return sb.toString();
    }

    public boolean hasNext() throws IOException {
        int i = this.brt;
        if (i == 0) {
            i = bD();
        }
        return (i == 2 || i == 4) ? false : true;
    }

    public final boolean isLenient() {
        return this.brp;
    }

    public boolean nextBoolean() throws IOException {
        int i = this.brt;
        if (i == 0) {
            i = bD();
        }
        if (i == 5) {
            this.brt = 0;
            int[] iArr = this.brA;
            int i2 = this.bry - 1;
            iArr[i2] = iArr[i2] + 1;
            return true;
        }
        if (i == 6) {
            this.brt = 0;
            int[] iArr2 = this.brA;
            int i3 = this.bry - 1;
            iArr2[i3] = iArr2[i3] + 1;
            return false;
        }
        String valueOf = String.valueOf(bq());
        int lineNumber = getLineNumber();
        int columnNumber = getColumnNumber();
        String path = getPath();
        StringBuilder sb = new StringBuilder(72 + String.valueOf(valueOf).length() + String.valueOf(path).length());
        sb.append("Expected a boolean but was ");
        sb.append(valueOf);
        sb.append(" at line ");
        sb.append(lineNumber);
        sb.append(" column ");
        sb.append(columnNumber);
        sb.append(" path ");
        sb.append(path);
        throw new IllegalStateException(sb.toString());
    }

    public double nextDouble() throws IOException {
        String zzd;
        int i = this.brt;
        if (i == 0) {
            i = bD();
        }
        if (i == 15) {
            this.brt = 0;
            int[] iArr = this.brA;
            int i2 = this.bry - 1;
            iArr[i2] = iArr[i2] + 1;
            return this.bru;
        }
        if (i == 16) {
            this.brw = new String(this.brq, this.pos, this.brv);
            this.pos += this.brv;
        } else {
            if (i == 8 || i == 9) {
                zzd = zzd(i == 8 ? '\'' : '\"');
            } else if (i == 10) {
                zzd = bG();
            } else if (i != 11) {
                String valueOf = String.valueOf(bq());
                int lineNumber = getLineNumber();
                int columnNumber = getColumnNumber();
                String path = getPath();
                StringBuilder sb = new StringBuilder(71 + String.valueOf(valueOf).length() + String.valueOf(path).length());
                sb.append("Expected a double but was ");
                sb.append(valueOf);
                sb.append(" at line ");
                sb.append(lineNumber);
                sb.append(" column ");
                sb.append(columnNumber);
                sb.append(" path ");
                sb.append(path);
                throw new IllegalStateException(sb.toString());
            }
            this.brw = zzd;
        }
        this.brt = 11;
        double parseDouble = Double.parseDouble(this.brw);
        if (this.brp || !(Double.isNaN(parseDouble) || Double.isInfinite(parseDouble))) {
            this.brw = null;
            this.brt = 0;
            int[] iArr2 = this.brA;
            int i3 = this.bry - 1;
            iArr2[i3] = iArr2[i3] + 1;
            return parseDouble;
        }
        int lineNumber2 = getLineNumber();
        int columnNumber2 = getColumnNumber();
        String path2 = getPath();
        StringBuilder sb2 = new StringBuilder(102 + String.valueOf(path2).length());
        sb2.append("JSON forbids NaN and infinities: ");
        sb2.append(parseDouble);
        sb2.append(" at line ");
        sb2.append(lineNumber2);
        sb2.append(" column ");
        sb2.append(columnNumber2);
        sb2.append(" path ");
        sb2.append(path2);
        throw new zzaqs(sb2.toString());
    }

    public int nextInt() throws IOException {
        int i = this.brt;
        if (i == 0) {
            i = bD();
        }
        if (i == 15) {
            int i2 = (int) this.bru;
            if (this.bru == i2) {
                this.brt = 0;
                int[] iArr = this.brA;
                int i3 = this.bry - 1;
                iArr[i3] = iArr[i3] + 1;
                return i2;
            }
            long j = this.bru;
            int lineNumber = getLineNumber();
            int columnNumber = getColumnNumber();
            String path = getPath();
            StringBuilder sb = new StringBuilder(89 + String.valueOf(path).length());
            sb.append("Expected an int but was ");
            sb.append(j);
            sb.append(" at line ");
            sb.append(lineNumber);
            sb.append(" column ");
            sb.append(columnNumber);
            sb.append(" path ");
            sb.append(path);
            throw new NumberFormatException(sb.toString());
        }
        if (i == 16) {
            this.brw = new String(this.brq, this.pos, this.brv);
            this.pos += this.brv;
        } else {
            if (i != 8 && i != 9) {
                String valueOf = String.valueOf(bq());
                int lineNumber2 = getLineNumber();
                int columnNumber2 = getColumnNumber();
                String path2 = getPath();
                StringBuilder sb2 = new StringBuilder(69 + String.valueOf(valueOf).length() + String.valueOf(path2).length());
                sb2.append("Expected an int but was ");
                sb2.append(valueOf);
                sb2.append(" at line ");
                sb2.append(lineNumber2);
                sb2.append(" column ");
                sb2.append(columnNumber2);
                sb2.append(" path ");
                sb2.append(path2);
                throw new IllegalStateException(sb2.toString());
            }
            this.brw = zzd(i == 8 ? '\'' : '\"');
            try {
                int parseInt = Integer.parseInt(this.brw);
                this.brt = 0;
                int[] iArr2 = this.brA;
                int i4 = this.bry - 1;
                iArr2[i4] = iArr2[i4] + 1;
                return parseInt;
            } catch (NumberFormatException unused) {
            }
        }
        this.brt = 11;
        double parseDouble = Double.parseDouble(this.brw);
        int i5 = (int) parseDouble;
        if (i5 == parseDouble) {
            this.brw = null;
            this.brt = 0;
            int[] iArr3 = this.brA;
            int i6 = this.bry - 1;
            iArr3[i6] = iArr3[i6] + 1;
            return i5;
        }
        String str = this.brw;
        int lineNumber3 = getLineNumber();
        int columnNumber3 = getColumnNumber();
        String path3 = getPath();
        StringBuilder sb3 = new StringBuilder(69 + String.valueOf(str).length() + String.valueOf(path3).length());
        sb3.append("Expected an int but was ");
        sb3.append(str);
        sb3.append(" at line ");
        sb3.append(lineNumber3);
        sb3.append(" column ");
        sb3.append(columnNumber3);
        sb3.append(" path ");
        sb3.append(path3);
        throw new NumberFormatException(sb3.toString());
    }

    public long nextLong() throws IOException {
        int i = this.brt;
        if (i == 0) {
            i = bD();
        }
        if (i == 15) {
            this.brt = 0;
            int[] iArr = this.brA;
            int i2 = this.bry - 1;
            iArr[i2] = iArr[i2] + 1;
            return this.bru;
        }
        if (i == 16) {
            this.brw = new String(this.brq, this.pos, this.brv);
            this.pos += this.brv;
        } else {
            if (i != 8 && i != 9) {
                String valueOf = String.valueOf(bq());
                int lineNumber = getLineNumber();
                int columnNumber = getColumnNumber();
                String path = getPath();
                StringBuilder sb = new StringBuilder(69 + String.valueOf(valueOf).length() + String.valueOf(path).length());
                sb.append("Expected a long but was ");
                sb.append(valueOf);
                sb.append(" at line ");
                sb.append(lineNumber);
                sb.append(" column ");
                sb.append(columnNumber);
                sb.append(" path ");
                sb.append(path);
                throw new IllegalStateException(sb.toString());
            }
            this.brw = zzd(i == 8 ? '\'' : '\"');
            try {
                long parseLong = Long.parseLong(this.brw);
                this.brt = 0;
                int[] iArr2 = this.brA;
                int i3 = this.bry - 1;
                iArr2[i3] = iArr2[i3] + 1;
                return parseLong;
            } catch (NumberFormatException unused) {
            }
        }
        this.brt = 11;
        double parseDouble = Double.parseDouble(this.brw);
        long j = (long) parseDouble;
        if (j == parseDouble) {
            this.brw = null;
            this.brt = 0;
            int[] iArr3 = this.brA;
            int i4 = this.bry - 1;
            iArr3[i4] = iArr3[i4] + 1;
            return j;
        }
        String str = this.brw;
        int lineNumber2 = getLineNumber();
        int columnNumber2 = getColumnNumber();
        String path2 = getPath();
        StringBuilder sb2 = new StringBuilder(69 + String.valueOf(str).length() + String.valueOf(path2).length());
        sb2.append("Expected a long but was ");
        sb2.append(str);
        sb2.append(" at line ");
        sb2.append(lineNumber2);
        sb2.append(" column ");
        sb2.append(columnNumber2);
        sb2.append(" path ");
        sb2.append(path2);
        throw new NumberFormatException(sb2.toString());
    }

    public String nextName() throws IOException {
        char c;
        String zzd;
        int i = this.brt;
        if (i == 0) {
            i = bD();
        }
        if (i == 14) {
            zzd = bG();
        } else {
            if (i == 12) {
                c = '\'';
            } else {
                if (i != 13) {
                    String valueOf = String.valueOf(bq());
                    int lineNumber = getLineNumber();
                    int columnNumber = getColumnNumber();
                    String path = getPath();
                    StringBuilder sb = new StringBuilder(69 + String.valueOf(valueOf).length() + String.valueOf(path).length());
                    sb.append("Expected a name but was ");
                    sb.append(valueOf);
                    sb.append(" at line ");
                    sb.append(lineNumber);
                    sb.append(" column ");
                    sb.append(columnNumber);
                    sb.append(" path ");
                    sb.append(path);
                    throw new IllegalStateException(sb.toString());
                }
                c = '\"';
            }
            zzd = zzd(c);
        }
        this.brt = 0;
        this.brz[this.bry - 1] = zzd;
        return zzd;
    }

    public void nextNull() throws IOException {
        int i = this.brt;
        if (i == 0) {
            i = bD();
        }
        if (i == 7) {
            this.brt = 0;
            int[] iArr = this.brA;
            int i2 = this.bry - 1;
            iArr[i2] = iArr[i2] + 1;
            return;
        }
        String valueOf = String.valueOf(bq());
        int lineNumber = getLineNumber();
        int columnNumber = getColumnNumber();
        String path = getPath();
        StringBuilder sb = new StringBuilder(67 + String.valueOf(valueOf).length() + String.valueOf(path).length());
        sb.append("Expected null but was ");
        sb.append(valueOf);
        sb.append(" at line ");
        sb.append(lineNumber);
        sb.append(" column ");
        sb.append(columnNumber);
        sb.append(" path ");
        sb.append(path);
        throw new IllegalStateException(sb.toString());
    }

    public String nextString() throws IOException {
        String str;
        char c;
        int i = this.brt;
        if (i == 0) {
            i = bD();
        }
        if (i == 10) {
            str = bG();
        } else {
            if (i == 8) {
                c = '\'';
            } else if (i == 9) {
                c = '\"';
            } else if (i == 11) {
                str = this.brw;
                this.brw = null;
            } else if (i == 15) {
                str = Long.toString(this.bru);
            } else {
                if (i != 16) {
                    String valueOf = String.valueOf(bq());
                    int lineNumber = getLineNumber();
                    int columnNumber = getColumnNumber();
                    String path = getPath();
                    StringBuilder sb = new StringBuilder(71 + String.valueOf(valueOf).length() + String.valueOf(path).length());
                    sb.append("Expected a string but was ");
                    sb.append(valueOf);
                    sb.append(" at line ");
                    sb.append(lineNumber);
                    sb.append(" column ");
                    sb.append(columnNumber);
                    sb.append(" path ");
                    sb.append(path);
                    throw new IllegalStateException(sb.toString());
                }
                str = new String(this.brq, this.pos, this.brv);
                this.pos += this.brv;
            }
            str = zzd(c);
        }
        this.brt = 0;
        int[] iArr = this.brA;
        int i2 = this.bry - 1;
        iArr[i2] = iArr[i2] + 1;
        return str;
    }

    public final void setLenient(boolean z) {
        this.brp = z;
    }

    public void skipValue() throws IOException {
        char c;
        int i = 0;
        do {
            int i2 = this.brt;
            if (i2 == 0) {
                i2 = bD();
            }
            if (i2 == 3) {
                zzagn(1);
            } else if (i2 == 1) {
                zzagn(3);
            } else {
                if (i2 == 4 || i2 == 2) {
                    this.bry--;
                    i--;
                } else if (i2 == 14 || i2 == 10) {
                    bH();
                } else {
                    if (i2 == 8 || i2 == 12) {
                        c = '\'';
                    } else if (i2 == 9 || i2 == 13) {
                        c = '\"';
                    } else if (i2 == 16) {
                        this.pos += this.brv;
                    }
                    zze(c);
                }
                this.brt = 0;
            }
            i++;
            this.brt = 0;
        } while (i != 0);
        int[] iArr = this.brA;
        int i3 = this.bry - 1;
        iArr[i3] = iArr[i3] + 1;
        this.brz[this.bry - 1] = "null";
    }

    public String toString() {
        String valueOf = String.valueOf(getClass().getSimpleName());
        int lineNumber = getLineNumber();
        int columnNumber = getColumnNumber();
        StringBuilder sb = new StringBuilder(39 + String.valueOf(valueOf).length());
        sb.append(valueOf);
        sb.append(" at line ");
        sb.append(lineNumber);
        sb.append(" column ");
        sb.append(columnNumber);
        return sb.toString();
    }
}
