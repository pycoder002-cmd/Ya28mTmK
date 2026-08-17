package com.google.android.gms.internal;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.BitSet;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.UUID;
import org.slf4j.Marker;

/* loaded from: classes.dex */
public final class zzaqn {
    public static final zzapk<Class> bqj = new zzapk<Class>() { // from class: com.google.android.gms.internal.zzaqn.1
        @Override // com.google.android.gms.internal.zzapk
        public void zza(zzaqr zzaqrVar, Class cls) throws IOException {
            if (cls == null) {
                zzaqrVar.bA();
                return;
            }
            String valueOf = String.valueOf(cls.getName());
            StringBuilder sb = new StringBuilder(76 + String.valueOf(valueOf).length());
            sb.append("Attempted to serialize java.lang.Class: ");
            sb.append(valueOf);
            sb.append(". Forgot to register a type adapter?");
            throw new UnsupportedOperationException(sb.toString());
        }

        @Override // com.google.android.gms.internal.zzapk
        /* renamed from: zzo, reason: merged with bridge method [inline-methods] */
        public Class zzb(zzaqp zzaqpVar) throws IOException {
            if (zzaqpVar.bq() != zzaqq.NULL) {
                throw new UnsupportedOperationException("Attempted to deserialize a java.lang.Class. Forgot to register a type adapter?");
            }
            zzaqpVar.nextNull();
            return null;
        }
    };
    public static final zzapl bqk = zza(Class.class, bqj);
    public static final zzapk<BitSet> bql = new zzapk<BitSet>() { // from class: com.google.android.gms.internal.zzaqn.12
        @Override // com.google.android.gms.internal.zzapk
        public void zza(zzaqr zzaqrVar, BitSet bitSet) throws IOException {
            if (bitSet == null) {
                zzaqrVar.bA();
                return;
            }
            zzaqrVar.bw();
            for (int i = 0; i < bitSet.length(); i++) {
                zzaqrVar.zzcs(bitSet.get(i) ? 1L : 0L);
            }
            zzaqrVar.bx();
        }

        /* JADX WARN: Code restructure failed: missing block: B:14:0x0059, code lost:
        
            if (java.lang.Integer.parseInt(r1) != 0) goto L27;
         */
        /* JADX WARN: Code restructure failed: missing block: B:15:0x005c, code lost:
        
            r5 = false;
         */
        /* JADX WARN: Code restructure failed: missing block: B:30:0x0084, code lost:
        
            if (r7.nextInt() != 0) goto L27;
         */
        /* JADX WARN: Failed to find 'out' block for switch in B:10:0x0028. Please report as an issue. */
        @Override // com.google.android.gms.internal.zzapk
        /* renamed from: zzx, reason: merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public java.util.BitSet zzb(com.google.android.gms.internal.zzaqp r7) throws java.io.IOException {
            /*
                r6 = this;
                com.google.android.gms.internal.zzaqq r0 = r7.bq()
                com.google.android.gms.internal.zzaqq r1 = com.google.android.gms.internal.zzaqq.NULL
                if (r0 != r1) goto Ld
                r7.nextNull()
                r7 = 0
                return r7
            Ld:
                java.util.BitSet r0 = new java.util.BitSet
                r0.<init>()
                r7.beginArray()
                com.google.android.gms.internal.zzaqq r1 = r7.bq()
                r2 = 0
                r3 = r2
            L1b:
                com.google.android.gms.internal.zzaqq r4 = com.google.android.gms.internal.zzaqq.END_ARRAY
                if (r1 == r4) goto L92
                int[] r4 = com.google.android.gms.internal.zzaqn.AnonymousClass26.bpW
                int r5 = r1.ordinal()
                r4 = r4[r5]
                r5 = 1
                switch(r4) {
                    case 1: goto L80;
                    case 2: goto L7b;
                    case 3: goto L51;
                    default: goto L2b;
                }
            L2b:
                com.google.android.gms.internal.zzaph r7 = new com.google.android.gms.internal.zzaph
                java.lang.String r0 = java.lang.String.valueOf(r1)
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r2 = 27
                java.lang.String r3 = java.lang.String.valueOf(r0)
                int r3 = r3.length()
                int r2 = r2 + r3
                r1.<init>(r2)
                java.lang.String r2 = "Invalid bitset value type: "
                r1.append(r2)
                r1.append(r0)
                java.lang.String r0 = r1.toString()
                r7.<init>(r0)
                throw r7
            L51:
                java.lang.String r1 = r7.nextString()
                int r4 = java.lang.Integer.parseInt(r1)     // Catch: java.lang.NumberFormatException -> L5e
                if (r4 == 0) goto L5c
                goto L86
            L5c:
                r5 = r2
                goto L86
            L5e:
                com.google.android.gms.internal.zzaph r7 = new com.google.android.gms.internal.zzaph
                java.lang.String r0 = "Error: Expecting: bitset number value (1, 0), Found: "
                java.lang.String r1 = java.lang.String.valueOf(r1)
                int r2 = r1.length()
                if (r2 == 0) goto L71
                java.lang.String r0 = r0.concat(r1)
                goto L77
            L71:
                java.lang.String r1 = new java.lang.String
                r1.<init>(r0)
                r0 = r1
            L77:
                r7.<init>(r0)
                throw r7
            L7b:
                boolean r5 = r7.nextBoolean()
                goto L86
            L80:
                int r1 = r7.nextInt()
                if (r1 == 0) goto L5c
            L86:
                if (r5 == 0) goto L8b
                r0.set(r3)
            L8b:
                int r3 = r3 + 1
                com.google.android.gms.internal.zzaqq r1 = r7.bq()
                goto L1b
            L92:
                r7.endArray()
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzaqn.AnonymousClass12.zzb(com.google.android.gms.internal.zzaqp):java.util.BitSet");
        }
    };
    public static final zzapl bqm = zza(BitSet.class, bql);
    public static final zzapk<Boolean> bqn = new zzapk<Boolean>() { // from class: com.google.android.gms.internal.zzaqn.23
        @Override // com.google.android.gms.internal.zzapk
        public void zza(zzaqr zzaqrVar, Boolean bool) throws IOException {
            if (bool == null) {
                zzaqrVar.bA();
            } else {
                zzaqrVar.zzdh(bool.booleanValue());
            }
        }

        @Override // com.google.android.gms.internal.zzapk
        /* renamed from: zzae, reason: merged with bridge method [inline-methods] */
        public Boolean zzb(zzaqp zzaqpVar) throws IOException {
            if (zzaqpVar.bq() != zzaqq.NULL) {
                return zzaqpVar.bq() == zzaqq.STRING ? Boolean.valueOf(Boolean.parseBoolean(zzaqpVar.nextString())) : Boolean.valueOf(zzaqpVar.nextBoolean());
            }
            zzaqpVar.nextNull();
            return null;
        }
    };
    public static final zzapk<Boolean> bqo = new zzapk<Boolean>() { // from class: com.google.android.gms.internal.zzaqn.27
        @Override // com.google.android.gms.internal.zzapk
        public void zza(zzaqr zzaqrVar, Boolean bool) throws IOException {
            zzaqrVar.zzut(bool == null ? "null" : bool.toString());
        }

        @Override // com.google.android.gms.internal.zzapk
        /* renamed from: zzae, reason: merged with bridge method [inline-methods] */
        public Boolean zzb(zzaqp zzaqpVar) throws IOException {
            if (zzaqpVar.bq() != zzaqq.NULL) {
                return Boolean.valueOf(zzaqpVar.nextString());
            }
            zzaqpVar.nextNull();
            return null;
        }
    };
    public static final zzapl bqp = zza(Boolean.TYPE, Boolean.class, bqn);
    public static final zzapk<Number> bqq = new zzapk<Number>() { // from class: com.google.android.gms.internal.zzaqn.28
        @Override // com.google.android.gms.internal.zzapk
        public void zza(zzaqr zzaqrVar, Number number) throws IOException {
            zzaqrVar.zza(number);
        }

        @Override // com.google.android.gms.internal.zzapk
        /* renamed from: zzg, reason: merged with bridge method [inline-methods] */
        public Number zzb(zzaqp zzaqpVar) throws IOException {
            if (zzaqpVar.bq() == zzaqq.NULL) {
                zzaqpVar.nextNull();
                return null;
            }
            try {
                return Byte.valueOf((byte) zzaqpVar.nextInt());
            } catch (NumberFormatException e) {
                throw new zzaph(e);
            }
        }
    };
    public static final zzapl bqr = zza(Byte.TYPE, Byte.class, bqq);
    public static final zzapk<Number> bqs = new zzapk<Number>() { // from class: com.google.android.gms.internal.zzaqn.29
        @Override // com.google.android.gms.internal.zzapk
        public void zza(zzaqr zzaqrVar, Number number) throws IOException {
            zzaqrVar.zza(number);
        }

        @Override // com.google.android.gms.internal.zzapk
        /* renamed from: zzg, reason: merged with bridge method [inline-methods] */
        public Number zzb(zzaqp zzaqpVar) throws IOException {
            if (zzaqpVar.bq() == zzaqq.NULL) {
                zzaqpVar.nextNull();
                return null;
            }
            try {
                return Short.valueOf((short) zzaqpVar.nextInt());
            } catch (NumberFormatException e) {
                throw new zzaph(e);
            }
        }
    };
    public static final zzapl bqt = zza(Short.TYPE, Short.class, bqs);
    public static final zzapk<Number> bqu = new zzapk<Number>() { // from class: com.google.android.gms.internal.zzaqn.30
        @Override // com.google.android.gms.internal.zzapk
        public void zza(zzaqr zzaqrVar, Number number) throws IOException {
            zzaqrVar.zza(number);
        }

        @Override // com.google.android.gms.internal.zzapk
        /* renamed from: zzg, reason: merged with bridge method [inline-methods] */
        public Number zzb(zzaqp zzaqpVar) throws IOException {
            if (zzaqpVar.bq() == zzaqq.NULL) {
                zzaqpVar.nextNull();
                return null;
            }
            try {
                return Integer.valueOf(zzaqpVar.nextInt());
            } catch (NumberFormatException e) {
                throw new zzaph(e);
            }
        }
    };
    public static final zzapl bqv = zza(Integer.TYPE, Integer.class, bqu);
    public static final zzapk<Number> bqw = new zzapk<Number>() { // from class: com.google.android.gms.internal.zzaqn.31
        @Override // com.google.android.gms.internal.zzapk
        public void zza(zzaqr zzaqrVar, Number number) throws IOException {
            zzaqrVar.zza(number);
        }

        @Override // com.google.android.gms.internal.zzapk
        /* renamed from: zzg, reason: merged with bridge method [inline-methods] */
        public Number zzb(zzaqp zzaqpVar) throws IOException {
            if (zzaqpVar.bq() == zzaqq.NULL) {
                zzaqpVar.nextNull();
                return null;
            }
            try {
                return Long.valueOf(zzaqpVar.nextLong());
            } catch (NumberFormatException e) {
                throw new zzaph(e);
            }
        }
    };
    public static final zzapk<Number> bqx = new zzapk<Number>() { // from class: com.google.android.gms.internal.zzaqn.32
        @Override // com.google.android.gms.internal.zzapk
        public void zza(zzaqr zzaqrVar, Number number) throws IOException {
            zzaqrVar.zza(number);
        }

        @Override // com.google.android.gms.internal.zzapk
        /* renamed from: zzg, reason: merged with bridge method [inline-methods] */
        public Number zzb(zzaqp zzaqpVar) throws IOException {
            if (zzaqpVar.bq() != zzaqq.NULL) {
                return Float.valueOf((float) zzaqpVar.nextDouble());
            }
            zzaqpVar.nextNull();
            return null;
        }
    };
    public static final zzapk<Number> bqy = new zzapk<Number>() { // from class: com.google.android.gms.internal.zzaqn.2
        @Override // com.google.android.gms.internal.zzapk
        public void zza(zzaqr zzaqrVar, Number number) throws IOException {
            zzaqrVar.zza(number);
        }

        @Override // com.google.android.gms.internal.zzapk
        /* renamed from: zzg, reason: merged with bridge method [inline-methods] */
        public Number zzb(zzaqp zzaqpVar) throws IOException {
            if (zzaqpVar.bq() != zzaqq.NULL) {
                return Double.valueOf(zzaqpVar.nextDouble());
            }
            zzaqpVar.nextNull();
            return null;
        }
    };
    public static final zzapk<Number> bqz = new zzapk<Number>() { // from class: com.google.android.gms.internal.zzaqn.3
        @Override // com.google.android.gms.internal.zzapk
        public void zza(zzaqr zzaqrVar, Number number) throws IOException {
            zzaqrVar.zza(number);
        }

        @Override // com.google.android.gms.internal.zzapk
        /* renamed from: zzg, reason: merged with bridge method [inline-methods] */
        public Number zzb(zzaqp zzaqpVar) throws IOException {
            zzaqq bq = zzaqpVar.bq();
            int i = AnonymousClass26.bpW[bq.ordinal()];
            if (i == 1) {
                return new zzapv(zzaqpVar.nextString());
            }
            if (i == 4) {
                zzaqpVar.nextNull();
                return null;
            }
            String valueOf = String.valueOf(bq);
            StringBuilder sb = new StringBuilder(23 + String.valueOf(valueOf).length());
            sb.append("Expecting number, got: ");
            sb.append(valueOf);
            throw new zzaph(sb.toString());
        }
    };
    public static final zzapl bqA = zza(Number.class, bqz);
    public static final zzapk<Character> bqB = new zzapk<Character>() { // from class: com.google.android.gms.internal.zzaqn.4
        @Override // com.google.android.gms.internal.zzapk
        public void zza(zzaqr zzaqrVar, Character ch) throws IOException {
            zzaqrVar.zzut(ch == null ? null : String.valueOf(ch));
        }

        @Override // com.google.android.gms.internal.zzapk
        /* renamed from: zzp, reason: merged with bridge method [inline-methods] */
        public Character zzb(zzaqp zzaqpVar) throws IOException {
            if (zzaqpVar.bq() == zzaqq.NULL) {
                zzaqpVar.nextNull();
                return null;
            }
            String nextString = zzaqpVar.nextString();
            if (nextString.length() == 1) {
                return Character.valueOf(nextString.charAt(0));
            }
            String valueOf = String.valueOf(nextString);
            throw new zzaph(valueOf.length() != 0 ? "Expecting character, got: ".concat(valueOf) : new String("Expecting character, got: "));
        }
    };
    public static final zzapl bqC = zza(Character.TYPE, Character.class, bqB);
    public static final zzapk<String> bqD = new zzapk<String>() { // from class: com.google.android.gms.internal.zzaqn.5
        @Override // com.google.android.gms.internal.zzapk
        public void zza(zzaqr zzaqrVar, String str) throws IOException {
            zzaqrVar.zzut(str);
        }

        @Override // com.google.android.gms.internal.zzapk
        /* renamed from: zzq, reason: merged with bridge method [inline-methods] */
        public String zzb(zzaqp zzaqpVar) throws IOException {
            zzaqq bq = zzaqpVar.bq();
            if (bq != zzaqq.NULL) {
                return bq == zzaqq.BOOLEAN ? Boolean.toString(zzaqpVar.nextBoolean()) : zzaqpVar.nextString();
            }
            zzaqpVar.nextNull();
            return null;
        }
    };
    public static final zzapk<BigDecimal> bqE = new zzapk<BigDecimal>() { // from class: com.google.android.gms.internal.zzaqn.6
        @Override // com.google.android.gms.internal.zzapk
        public void zza(zzaqr zzaqrVar, BigDecimal bigDecimal) throws IOException {
            zzaqrVar.zza(bigDecimal);
        }

        @Override // com.google.android.gms.internal.zzapk
        /* renamed from: zzr, reason: merged with bridge method [inline-methods] */
        public BigDecimal zzb(zzaqp zzaqpVar) throws IOException {
            if (zzaqpVar.bq() == zzaqq.NULL) {
                zzaqpVar.nextNull();
                return null;
            }
            try {
                return new BigDecimal(zzaqpVar.nextString());
            } catch (NumberFormatException e) {
                throw new zzaph(e);
            }
        }
    };
    public static final zzapk<BigInteger> bqF = new zzapk<BigInteger>() { // from class: com.google.android.gms.internal.zzaqn.7
        @Override // com.google.android.gms.internal.zzapk
        public void zza(zzaqr zzaqrVar, BigInteger bigInteger) throws IOException {
            zzaqrVar.zza(bigInteger);
        }

        @Override // com.google.android.gms.internal.zzapk
        /* renamed from: zzs, reason: merged with bridge method [inline-methods] */
        public BigInteger zzb(zzaqp zzaqpVar) throws IOException {
            if (zzaqpVar.bq() == zzaqq.NULL) {
                zzaqpVar.nextNull();
                return null;
            }
            try {
                return new BigInteger(zzaqpVar.nextString());
            } catch (NumberFormatException e) {
                throw new zzaph(e);
            }
        }
    };
    public static final zzapl bqG = zza(String.class, bqD);
    public static final zzapk<StringBuilder> bqH = new zzapk<StringBuilder>() { // from class: com.google.android.gms.internal.zzaqn.8
        @Override // com.google.android.gms.internal.zzapk
        public void zza(zzaqr zzaqrVar, StringBuilder sb) throws IOException {
            zzaqrVar.zzut(sb == null ? null : sb.toString());
        }

        @Override // com.google.android.gms.internal.zzapk
        /* renamed from: zzt, reason: merged with bridge method [inline-methods] */
        public StringBuilder zzb(zzaqp zzaqpVar) throws IOException {
            if (zzaqpVar.bq() != zzaqq.NULL) {
                return new StringBuilder(zzaqpVar.nextString());
            }
            zzaqpVar.nextNull();
            return null;
        }
    };
    public static final zzapl bqI = zza(StringBuilder.class, bqH);
    public static final zzapk<StringBuffer> bqJ = new zzapk<StringBuffer>() { // from class: com.google.android.gms.internal.zzaqn.9
        @Override // com.google.android.gms.internal.zzapk
        public void zza(zzaqr zzaqrVar, StringBuffer stringBuffer) throws IOException {
            zzaqrVar.zzut(stringBuffer == null ? null : stringBuffer.toString());
        }

        @Override // com.google.android.gms.internal.zzapk
        /* renamed from: zzu, reason: merged with bridge method [inline-methods] */
        public StringBuffer zzb(zzaqp zzaqpVar) throws IOException {
            if (zzaqpVar.bq() != zzaqq.NULL) {
                return new StringBuffer(zzaqpVar.nextString());
            }
            zzaqpVar.nextNull();
            return null;
        }
    };
    public static final zzapl bqK = zza(StringBuffer.class, bqJ);
    public static final zzapk<URL> bqL = new zzapk<URL>() { // from class: com.google.android.gms.internal.zzaqn.10
        @Override // com.google.android.gms.internal.zzapk
        public void zza(zzaqr zzaqrVar, URL url) throws IOException {
            zzaqrVar.zzut(url == null ? null : url.toExternalForm());
        }

        @Override // com.google.android.gms.internal.zzapk
        /* renamed from: zzv, reason: merged with bridge method [inline-methods] */
        public URL zzb(zzaqp zzaqpVar) throws IOException {
            if (zzaqpVar.bq() == zzaqq.NULL) {
                zzaqpVar.nextNull();
                return null;
            }
            String nextString = zzaqpVar.nextString();
            if ("null".equals(nextString)) {
                return null;
            }
            return new URL(nextString);
        }
    };
    public static final zzapl bqM = zza(URL.class, bqL);
    public static final zzapk<URI> bqN = new zzapk<URI>() { // from class: com.google.android.gms.internal.zzaqn.11
        @Override // com.google.android.gms.internal.zzapk
        public void zza(zzaqr zzaqrVar, URI uri) throws IOException {
            zzaqrVar.zzut(uri == null ? null : uri.toASCIIString());
        }

        @Override // com.google.android.gms.internal.zzapk
        /* renamed from: zzw, reason: merged with bridge method [inline-methods] */
        public URI zzb(zzaqp zzaqpVar) throws IOException {
            if (zzaqpVar.bq() == zzaqq.NULL) {
                zzaqpVar.nextNull();
                return null;
            }
            try {
                String nextString = zzaqpVar.nextString();
                if ("null".equals(nextString)) {
                    return null;
                }
                return new URI(nextString);
            } catch (URISyntaxException e) {
                throw new zzaoz(e);
            }
        }
    };
    public static final zzapl bqO = zza(URI.class, bqN);
    public static final zzapk<InetAddress> bqP = new zzapk<InetAddress>() { // from class: com.google.android.gms.internal.zzaqn.13
        @Override // com.google.android.gms.internal.zzapk
        public void zza(zzaqr zzaqrVar, InetAddress inetAddress) throws IOException {
            zzaqrVar.zzut(inetAddress == null ? null : inetAddress.getHostAddress());
        }

        @Override // com.google.android.gms.internal.zzapk
        /* renamed from: zzy, reason: merged with bridge method [inline-methods] */
        public InetAddress zzb(zzaqp zzaqpVar) throws IOException {
            if (zzaqpVar.bq() != zzaqq.NULL) {
                return InetAddress.getByName(zzaqpVar.nextString());
            }
            zzaqpVar.nextNull();
            return null;
        }
    };
    public static final zzapl bqQ = zzb(InetAddress.class, bqP);
    public static final zzapk<UUID> bqR = new zzapk<UUID>() { // from class: com.google.android.gms.internal.zzaqn.14
        @Override // com.google.android.gms.internal.zzapk
        public void zza(zzaqr zzaqrVar, UUID uuid) throws IOException {
            zzaqrVar.zzut(uuid == null ? null : uuid.toString());
        }

        @Override // com.google.android.gms.internal.zzapk
        /* renamed from: zzz, reason: merged with bridge method [inline-methods] */
        public UUID zzb(zzaqp zzaqpVar) throws IOException {
            if (zzaqpVar.bq() != zzaqq.NULL) {
                return UUID.fromString(zzaqpVar.nextString());
            }
            zzaqpVar.nextNull();
            return null;
        }
    };
    public static final zzapl bqS = zza(UUID.class, bqR);
    public static final zzapl bqT = new zzapl() { // from class: com.google.android.gms.internal.zzaqn.15
        @Override // com.google.android.gms.internal.zzapl
        public <T> zzapk<T> zza(zzaos zzaosVar, zzaqo<T> zzaqoVar) {
            if (zzaqoVar.bB() != Timestamp.class) {
                return null;
            }
            final zzapk<T> zzk = zzaosVar.zzk(Date.class);
            return (zzapk<T>) new zzapk<Timestamp>() { // from class: com.google.android.gms.internal.zzaqn.15.1
                @Override // com.google.android.gms.internal.zzapk
                public void zza(zzaqr zzaqrVar, Timestamp timestamp) throws IOException {
                    zzk.zza(zzaqrVar, timestamp);
                }

                @Override // com.google.android.gms.internal.zzapk
                /* renamed from: zzaa, reason: merged with bridge method [inline-methods] */
                public Timestamp zzb(zzaqp zzaqpVar) throws IOException {
                    Date date = (Date) zzk.zzb(zzaqpVar);
                    if (date != null) {
                        return new Timestamp(date.getTime());
                    }
                    return null;
                }
            };
        }
    };
    public static final zzapk<Calendar> bqU = new zzapk<Calendar>() { // from class: com.google.android.gms.internal.zzaqn.16
        @Override // com.google.android.gms.internal.zzapk
        public void zza(zzaqr zzaqrVar, Calendar calendar) throws IOException {
            if (calendar == null) {
                zzaqrVar.bA();
                return;
            }
            zzaqrVar.by();
            zzaqrVar.zzus("year");
            zzaqrVar.zzcs(calendar.get(1));
            zzaqrVar.zzus("month");
            zzaqrVar.zzcs(calendar.get(2));
            zzaqrVar.zzus("dayOfMonth");
            zzaqrVar.zzcs(calendar.get(5));
            zzaqrVar.zzus("hourOfDay");
            zzaqrVar.zzcs(calendar.get(11));
            zzaqrVar.zzus("minute");
            zzaqrVar.zzcs(calendar.get(12));
            zzaqrVar.zzus("second");
            zzaqrVar.zzcs(calendar.get(13));
            zzaqrVar.bz();
        }

        @Override // com.google.android.gms.internal.zzapk
        /* renamed from: zzab, reason: merged with bridge method [inline-methods] */
        public Calendar zzb(zzaqp zzaqpVar) throws IOException {
            if (zzaqpVar.bq() == zzaqq.NULL) {
                zzaqpVar.nextNull();
                return null;
            }
            zzaqpVar.beginObject();
            int i = 0;
            int i2 = 0;
            int i3 = 0;
            int i4 = 0;
            int i5 = 0;
            int i6 = 0;
            while (zzaqpVar.bq() != zzaqq.END_OBJECT) {
                String nextName = zzaqpVar.nextName();
                int nextInt = zzaqpVar.nextInt();
                if ("year".equals(nextName)) {
                    i = nextInt;
                } else if ("month".equals(nextName)) {
                    i2 = nextInt;
                } else if ("dayOfMonth".equals(nextName)) {
                    i3 = nextInt;
                } else if ("hourOfDay".equals(nextName)) {
                    i4 = nextInt;
                } else if ("minute".equals(nextName)) {
                    i5 = nextInt;
                } else if ("second".equals(nextName)) {
                    i6 = nextInt;
                }
            }
            zzaqpVar.endObject();
            return new GregorianCalendar(i, i2, i3, i4, i5, i6);
        }
    };
    public static final zzapl bqV = zzb(Calendar.class, GregorianCalendar.class, bqU);
    public static final zzapk<Locale> bqW = new zzapk<Locale>() { // from class: com.google.android.gms.internal.zzaqn.17
        @Override // com.google.android.gms.internal.zzapk
        public void zza(zzaqr zzaqrVar, Locale locale) throws IOException {
            zzaqrVar.zzut(locale == null ? null : locale.toString());
        }

        @Override // com.google.android.gms.internal.zzapk
        /* renamed from: zzac, reason: merged with bridge method [inline-methods] */
        public Locale zzb(zzaqp zzaqpVar) throws IOException {
            if (zzaqpVar.bq() == zzaqq.NULL) {
                zzaqpVar.nextNull();
                return null;
            }
            StringTokenizer stringTokenizer = new StringTokenizer(zzaqpVar.nextString(), "_");
            String nextToken = stringTokenizer.hasMoreElements() ? stringTokenizer.nextToken() : null;
            String nextToken2 = stringTokenizer.hasMoreElements() ? stringTokenizer.nextToken() : null;
            String nextToken3 = stringTokenizer.hasMoreElements() ? stringTokenizer.nextToken() : null;
            return (nextToken2 == null && nextToken3 == null) ? new Locale(nextToken) : nextToken3 == null ? new Locale(nextToken, nextToken2) : new Locale(nextToken, nextToken2, nextToken3);
        }
    };
    public static final zzapl bqX = zza(Locale.class, bqW);
    public static final zzapk<zzaoy> bqY = new zzapk<zzaoy>() { // from class: com.google.android.gms.internal.zzaqn.18
        @Override // com.google.android.gms.internal.zzapk
        public void zza(zzaqr zzaqrVar, zzaoy zzaoyVar) throws IOException {
            if (zzaoyVar == null || zzaoyVar.aY()) {
                zzaqrVar.bA();
                return;
            }
            if (zzaoyVar.aX()) {
                zzape bb = zzaoyVar.bb();
                if (bb.be()) {
                    zzaqrVar.zza(bb.aT());
                    return;
                } else if (bb.bd()) {
                    zzaqrVar.zzdh(bb.getAsBoolean());
                    return;
                } else {
                    zzaqrVar.zzut(bb.aU());
                    return;
                }
            }
            if (zzaoyVar.aV()) {
                zzaqrVar.bw();
                Iterator<zzaoy> it = zzaoyVar.ba().iterator();
                while (it.hasNext()) {
                    zza(zzaqrVar, it.next());
                }
                zzaqrVar.bx();
                return;
            }
            if (!zzaoyVar.aW()) {
                String valueOf = String.valueOf(zzaoyVar.getClass());
                StringBuilder sb = new StringBuilder(15 + String.valueOf(valueOf).length());
                sb.append("Couldn't write ");
                sb.append(valueOf);
                throw new IllegalArgumentException(sb.toString());
            }
            zzaqrVar.by();
            for (Map.Entry<String, zzaoy> entry : zzaoyVar.aZ().entrySet()) {
                zzaqrVar.zzus(entry.getKey());
                zza(zzaqrVar, entry.getValue());
            }
            zzaqrVar.bz();
        }

        @Override // com.google.android.gms.internal.zzapk
        /* renamed from: zzad, reason: merged with bridge method [inline-methods] */
        public zzaoy zzb(zzaqp zzaqpVar) throws IOException {
            switch (AnonymousClass26.bpW[zzaqpVar.bq().ordinal()]) {
                case 1:
                    return new zzape((Number) new zzapv(zzaqpVar.nextString()));
                case 2:
                    return new zzape(Boolean.valueOf(zzaqpVar.nextBoolean()));
                case 3:
                    return new zzape(zzaqpVar.nextString());
                case 4:
                    zzaqpVar.nextNull();
                    return zzapa.bou;
                case 5:
                    zzaov zzaovVar = new zzaov();
                    zzaqpVar.beginArray();
                    while (zzaqpVar.hasNext()) {
                        zzaovVar.zzc((zzaoy) zzb(zzaqpVar));
                    }
                    zzaqpVar.endArray();
                    return zzaovVar;
                case 6:
                    zzapb zzapbVar = new zzapb();
                    zzaqpVar.beginObject();
                    while (zzaqpVar.hasNext()) {
                        zzapbVar.zza(zzaqpVar.nextName(), (zzaoy) zzb(zzaqpVar));
                    }
                    zzaqpVar.endObject();
                    return zzapbVar;
                default:
                    throw new IllegalArgumentException();
            }
        }
    };
    public static final zzapl bqZ = zzb(zzaoy.class, bqY);
    public static final zzapl bra = new zzapl() { // from class: com.google.android.gms.internal.zzaqn.19
        @Override // com.google.android.gms.internal.zzapl
        public <T> zzapk<T> zza(zzaos zzaosVar, zzaqo<T> zzaqoVar) {
            Class<? super T> bB = zzaqoVar.bB();
            if (!Enum.class.isAssignableFrom(bB) || bB == Enum.class) {
                return null;
            }
            if (!bB.isEnum()) {
                bB = bB.getSuperclass();
            }
            return new zza(bB);
        }
    };

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.google.android.gms.internal.zzaqn$26, reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass26 {
        static final /* synthetic */ int[] bpW = new int[zzaqq.values().length];

        static {
            try {
                bpW[zzaqq.NUMBER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                bpW[zzaqq.BOOLEAN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                bpW[zzaqq.STRING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                bpW[zzaqq.NULL.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                bpW[zzaqq.BEGIN_ARRAY.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                bpW[zzaqq.BEGIN_OBJECT.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                bpW[zzaqq.END_DOCUMENT.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                bpW[zzaqq.NAME.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                bpW[zzaqq.END_OBJECT.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                bpW[zzaqq.END_ARRAY.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
        }
    }

    /* loaded from: classes.dex */
    private static final class zza<T extends Enum<T>> extends zzapk<T> {
        private final Map<String, T> brk = new HashMap();
        private final Map<T, String> brl = new HashMap();

        public zza(Class<T> cls) {
            try {
                for (T t : cls.getEnumConstants()) {
                    String name = t.name();
                    zzapn zzapnVar = (zzapn) cls.getField(name).getAnnotation(zzapn.class);
                    if (zzapnVar != null) {
                        name = zzapnVar.value();
                        for (String str : zzapnVar.bh()) {
                            this.brk.put(str, t);
                        }
                    }
                    this.brk.put(name, t);
                    this.brl.put(t, name);
                }
            } catch (NoSuchFieldException unused) {
                throw new AssertionError();
            }
        }

        @Override // com.google.android.gms.internal.zzapk
        public void zza(zzaqr zzaqrVar, T t) throws IOException {
            zzaqrVar.zzut(t == null ? null : this.brl.get(t));
        }

        @Override // com.google.android.gms.internal.zzapk
        /* renamed from: zzaf, reason: merged with bridge method [inline-methods] */
        public T zzb(zzaqp zzaqpVar) throws IOException {
            if (zzaqpVar.bq() != zzaqq.NULL) {
                return this.brk.get(zzaqpVar.nextString());
            }
            zzaqpVar.nextNull();
            return null;
        }
    }

    public static <TT> zzapl zza(final zzaqo<TT> zzaqoVar, final zzapk<TT> zzapkVar) {
        return new zzapl() { // from class: com.google.android.gms.internal.zzaqn.20
            @Override // com.google.android.gms.internal.zzapl
            public <T> zzapk<T> zza(zzaos zzaosVar, zzaqo<T> zzaqoVar2) {
                if (zzaqoVar2.equals(zzaqo.this)) {
                    return zzapkVar;
                }
                return null;
            }
        };
    }

    public static <TT> zzapl zza(final Class<TT> cls, final zzapk<TT> zzapkVar) {
        return new zzapl() { // from class: com.google.android.gms.internal.zzaqn.21
            public String toString() {
                String valueOf = String.valueOf(cls.getName());
                String valueOf2 = String.valueOf(zzapkVar);
                StringBuilder sb = new StringBuilder(23 + String.valueOf(valueOf).length() + String.valueOf(valueOf2).length());
                sb.append("Factory[type=");
                sb.append(valueOf);
                sb.append(",adapter=");
                sb.append(valueOf2);
                sb.append("]");
                return sb.toString();
            }

            @Override // com.google.android.gms.internal.zzapl
            public <T> zzapk<T> zza(zzaos zzaosVar, zzaqo<T> zzaqoVar) {
                if (zzaqoVar.bB() == cls) {
                    return zzapkVar;
                }
                return null;
            }
        };
    }

    public static <TT> zzapl zza(final Class<TT> cls, final Class<TT> cls2, final zzapk<? super TT> zzapkVar) {
        return new zzapl() { // from class: com.google.android.gms.internal.zzaqn.22
            public String toString() {
                String valueOf = String.valueOf(cls2.getName());
                String valueOf2 = String.valueOf(cls.getName());
                String valueOf3 = String.valueOf(zzapkVar);
                StringBuilder sb = new StringBuilder(24 + String.valueOf(valueOf).length() + String.valueOf(valueOf2).length() + String.valueOf(valueOf3).length());
                sb.append("Factory[type=");
                sb.append(valueOf);
                sb.append(Marker.ANY_NON_NULL_MARKER);
                sb.append(valueOf2);
                sb.append(",adapter=");
                sb.append(valueOf3);
                sb.append("]");
                return sb.toString();
            }

            @Override // com.google.android.gms.internal.zzapl
            public <T> zzapk<T> zza(zzaos zzaosVar, zzaqo<T> zzaqoVar) {
                Class<? super T> bB = zzaqoVar.bB();
                if (bB == cls || bB == cls2) {
                    return zzapkVar;
                }
                return null;
            }
        };
    }

    public static <TT> zzapl zzb(final Class<TT> cls, final zzapk<TT> zzapkVar) {
        return new zzapl() { // from class: com.google.android.gms.internal.zzaqn.25
            public String toString() {
                String valueOf = String.valueOf(cls.getName());
                String valueOf2 = String.valueOf(zzapkVar);
                StringBuilder sb = new StringBuilder(32 + String.valueOf(valueOf).length() + String.valueOf(valueOf2).length());
                sb.append("Factory[typeHierarchy=");
                sb.append(valueOf);
                sb.append(",adapter=");
                sb.append(valueOf2);
                sb.append("]");
                return sb.toString();
            }

            @Override // com.google.android.gms.internal.zzapl
            public <T> zzapk<T> zza(zzaos zzaosVar, zzaqo<T> zzaqoVar) {
                if (cls.isAssignableFrom(zzaqoVar.bB())) {
                    return zzapkVar;
                }
                return null;
            }
        };
    }

    public static <TT> zzapl zzb(final Class<TT> cls, final Class<? extends TT> cls2, final zzapk<? super TT> zzapkVar) {
        return new zzapl() { // from class: com.google.android.gms.internal.zzaqn.24
            public String toString() {
                String valueOf = String.valueOf(cls.getName());
                String valueOf2 = String.valueOf(cls2.getName());
                String valueOf3 = String.valueOf(zzapkVar);
                StringBuilder sb = new StringBuilder(24 + String.valueOf(valueOf).length() + String.valueOf(valueOf2).length() + String.valueOf(valueOf3).length());
                sb.append("Factory[type=");
                sb.append(valueOf);
                sb.append(Marker.ANY_NON_NULL_MARKER);
                sb.append(valueOf2);
                sb.append(",adapter=");
                sb.append(valueOf3);
                sb.append("]");
                return sb.toString();
            }

            @Override // com.google.android.gms.internal.zzapl
            public <T> zzapk<T> zza(zzaos zzaosVar, zzaqo<T> zzaqoVar) {
                Class<? super T> bB = zzaqoVar.bB();
                if (bB == cls || bB == cls2) {
                    return zzapkVar;
                }
                return null;
            }
        };
    }
}
