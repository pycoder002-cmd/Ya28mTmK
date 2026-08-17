package com.google.android.gms.internal;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public final class zzaqj implements zzapl {
    private final zzaps bod;
    private final zzapt bom;
    private final zzaor boo;

    /* loaded from: classes.dex */
    public static final class zza<T> extends zzapk<T> {
        private final zzapx<T> bpK;
        private final Map<String, zzb> bqd;

        private zza(zzapx<T> zzapxVar, Map<String, zzb> map) {
            this.bpK = zzapxVar;
            this.bqd = map;
        }

        @Override // com.google.android.gms.internal.zzapk
        public void zza(zzaqr zzaqrVar, T t) throws IOException {
            if (t == null) {
                zzaqrVar.bA();
                return;
            }
            zzaqrVar.by();
            try {
                for (zzb zzbVar : this.bqd.values()) {
                    if (zzbVar.zzcs(t)) {
                        zzaqrVar.zzus(zzbVar.name);
                        zzbVar.zza(zzaqrVar, t);
                    }
                }
                zzaqrVar.bz();
            } catch (IllegalAccessException unused) {
                throw new AssertionError();
            }
        }

        @Override // com.google.android.gms.internal.zzapk
        public T zzb(zzaqp zzaqpVar) throws IOException {
            if (zzaqpVar.bq() == zzaqq.NULL) {
                zzaqpVar.nextNull();
                return null;
            }
            T bj = this.bpK.bj();
            try {
                zzaqpVar.beginObject();
                while (zzaqpVar.hasNext()) {
                    zzb zzbVar = this.bqd.get(zzaqpVar.nextName());
                    if (zzbVar != null && zzbVar.bqf) {
                        zzbVar.zza(zzaqpVar, bj);
                    }
                    zzaqpVar.skipValue();
                }
                zzaqpVar.endObject();
                return bj;
            } catch (IllegalAccessException e) {
                throw new AssertionError(e);
            } catch (IllegalStateException e2) {
                throw new zzaph(e2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static abstract class zzb {
        final boolean bqe;
        final boolean bqf;
        final String name;

        protected zzb(String str, boolean z, boolean z2) {
            this.name = str;
            this.bqe = z;
            this.bqf = z2;
        }

        abstract void zza(zzaqp zzaqpVar, Object obj) throws IOException, IllegalAccessException;

        abstract void zza(zzaqr zzaqrVar, Object obj) throws IOException, IllegalAccessException;

        abstract boolean zzcs(Object obj) throws IOException, IllegalAccessException;
    }

    public zzaqj(zzaps zzapsVar, zzaor zzaorVar, zzapt zzaptVar) {
        this.bod = zzapsVar;
        this.boo = zzaorVar;
        this.bom = zzaptVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public zzapk<?> zza(zzaos zzaosVar, Field field, zzaqo<?> zzaqoVar) {
        zzapk<?> zza2;
        zzapm zzapmVar = (zzapm) field.getAnnotation(zzapm.class);
        return (zzapmVar == null || (zza2 = zzaqe.zza(this.bod, zzaosVar, zzaqoVar, zzapmVar)) == null) ? zzaosVar.zza(zzaqoVar) : zza2;
    }

    private zzb zza(final zzaos zzaosVar, final Field field, String str, final zzaqo<?> zzaqoVar, boolean z, boolean z2) {
        final boolean zzk = zzapy.zzk(zzaqoVar.bB());
        return new zzb(str, z, z2) { // from class: com.google.android.gms.internal.zzaqj.1
            final zzapk<?> bpX;

            {
                this.bpX = zzaqj.this.zza(zzaosVar, field, (zzaqo<?>) zzaqoVar);
            }

            @Override // com.google.android.gms.internal.zzaqj.zzb
            void zza(zzaqp zzaqpVar, Object obj) throws IOException, IllegalAccessException {
                Object zzb2 = this.bpX.zzb(zzaqpVar);
                if (zzb2 == null && zzk) {
                    return;
                }
                field.set(obj, zzb2);
            }

            @Override // com.google.android.gms.internal.zzaqj.zzb
            void zza(zzaqr zzaqrVar, Object obj) throws IOException, IllegalAccessException {
                new zzaqm(zzaosVar, this.bpX, zzaqoVar.bC()).zza(zzaqrVar, field.get(obj));
            }

            @Override // com.google.android.gms.internal.zzaqj.zzb
            public boolean zzcs(Object obj) throws IOException, IllegalAccessException {
                return this.bqe && field.get(obj) != obj;
            }
        };
    }

    static List<String> zza(zzaor zzaorVar, Field field) {
        zzapn zzapnVar = (zzapn) field.getAnnotation(zzapn.class);
        LinkedList linkedList = new LinkedList();
        if (zzapnVar == null) {
            linkedList.add(zzaorVar.zzc(field));
            return linkedList;
        }
        linkedList.add(zzapnVar.value());
        String[] bh = zzapnVar.bh();
        for (String str : bh) {
            linkedList.add(str);
        }
        return linkedList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v1, types: [int] */
    /* JADX WARN: Type inference failed for: r3v5 */
    private Map<String, zzb> zza(zzaos zzaosVar, zzaqo<?> zzaqoVar, Class<?> cls) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        if (cls.isInterface()) {
            return linkedHashMap;
        }
        Type bC = zzaqoVar.bC();
        zzaqo<?> zzaqoVar2 = zzaqoVar;
        Class<?> cls2 = cls;
        while (cls2 != Object.class) {
            Field[] declaredFields = cls2.getDeclaredFields();
            boolean z = false;
            int length = declaredFields.length;
            int i = 0;
            while (i < length) {
                Field field = declaredFields[i];
                boolean zza2 = zza(field, true);
                boolean zza3 = zza(field, z);
                if (zza2 || zza3) {
                    field.setAccessible(true);
                    Type zza4 = zzapr.zza(zzaqoVar2.bC(), cls2, field.getGenericType());
                    List<String> zzd = zzd(field);
                    zzb zzbVar = null;
                    ?? r3 = z;
                    while (r3 < zzd.size()) {
                        String str = zzd.get(r3);
                        boolean z2 = r3 != 0 ? z : zza2;
                        zzb zzbVar2 = zzbVar;
                        int i2 = r3;
                        List<String> list = zzd;
                        Type type = zza4;
                        Field field2 = field;
                        zzbVar = zzbVar2 == null ? (zzb) linkedHashMap.put(str, zza(zzaosVar, field, str, zzaqo.zzl(zza4), z2, zza3)) : zzbVar2;
                        zza2 = z2;
                        zza4 = type;
                        zzd = list;
                        field = field2;
                        z = false;
                        r3 = i2 + 1;
                    }
                    zzb zzbVar3 = zzbVar;
                    if (zzbVar3 != null) {
                        String valueOf = String.valueOf(bC);
                        String str2 = zzbVar3.name;
                        StringBuilder sb = new StringBuilder(37 + String.valueOf(valueOf).length() + String.valueOf(str2).length());
                        sb.append(valueOf);
                        sb.append(" declares multiple JSON fields named ");
                        sb.append(str2);
                        throw new IllegalArgumentException(sb.toString());
                    }
                }
                i++;
                z = false;
            }
            zzaqoVar2 = zzaqo.zzl(zzapr.zza(zzaqoVar2.bC(), cls2, cls2.getGenericSuperclass()));
            cls2 = zzaqoVar2.bB();
        }
        return linkedHashMap;
    }

    static boolean zza(Field field, boolean z, zzapt zzaptVar) {
        return (zzaptVar.zza(field.getType(), z) || zzaptVar.zza(field, z)) ? false : true;
    }

    private List<String> zzd(Field field) {
        return zza(this.boo, field);
    }

    @Override // com.google.android.gms.internal.zzapl
    public <T> zzapk<T> zza(zzaos zzaosVar, zzaqo<T> zzaqoVar) {
        Class<? super T> bB = zzaqoVar.bB();
        if (Object.class.isAssignableFrom(bB)) {
            return new zza(this.bod.zzb(zzaqoVar), zza(zzaosVar, (zzaqo<?>) zzaqoVar, (Class<?>) bB));
        }
        return null;
    }

    public boolean zza(Field field, boolean z) {
        return zza(field, z, this.bom);
    }
}
