package com.google.android.gms.internal;

import java.io.EOFException;
import java.io.IOException;
import java.io.Writer;

/* loaded from: classes.dex */
public final class zzapz {

    /* loaded from: classes.dex */
    private static final class zza extends Writer {
        private final C0027zza bpA;
        private final Appendable bpz;

        /* renamed from: com.google.android.gms.internal.zzapz$zza$zza, reason: collision with other inner class name */
        /* loaded from: classes.dex */
        static class C0027zza implements CharSequence {
            char[] bpB;

            C0027zza() {
            }

            @Override // java.lang.CharSequence
            public char charAt(int i) {
                return this.bpB[i];
            }

            @Override // java.lang.CharSequence
            public int length() {
                return this.bpB.length;
            }

            @Override // java.lang.CharSequence
            public CharSequence subSequence(int i, int i2) {
                return new String(this.bpB, i, i2 - i);
            }
        }

        private zza(Appendable appendable) {
            this.bpA = new C0027zza();
            this.bpz = appendable;
        }

        @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
        }

        @Override // java.io.Writer, java.io.Flushable
        public void flush() {
        }

        @Override // java.io.Writer
        public void write(int i) throws IOException {
            this.bpz.append((char) i);
        }

        @Override // java.io.Writer
        public void write(char[] cArr, int i, int i2) throws IOException {
            this.bpA.bpB = cArr;
            this.bpz.append(this.bpA, i, i2 + i);
        }
    }

    public static Writer zza(Appendable appendable) {
        return appendable instanceof Writer ? (Writer) appendable : new zza(appendable);
    }

    public static void zzb(zzaoy zzaoyVar, zzaqr zzaqrVar) throws IOException {
        zzaqn.bqY.zza(zzaqrVar, zzaoyVar);
    }

    public static zzaoy zzh(zzaqp zzaqpVar) throws zzapc {
        boolean z;
        try {
            try {
                zzaqpVar.bq();
                z = false;
            } catch (EOFException e) {
                e = e;
                z = true;
            }
            try {
                return zzaqn.bqY.zzb(zzaqpVar);
            } catch (EOFException e2) {
                e = e2;
                if (z) {
                    return zzapa.bou;
                }
                throw new zzaph(e);
            }
        } catch (zzaqs e3) {
            throw new zzaph(e3);
        } catch (IOException e4) {
            throw new zzaoz(e4);
        } catch (NumberFormatException e5) {
            throw new zzaph(e5);
        }
    }
}
